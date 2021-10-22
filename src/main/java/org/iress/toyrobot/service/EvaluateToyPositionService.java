package org.iress.toyrobot.service;

import org.iress.toyrobot.exception.ToyRobotException;
import org.iress.toyrobot.constants.Commands;
import org.iress.toyrobot.constants.Directions;
import org.iress.toyrobot.impl.ToyRobotPositionAndDirection;
import org.iress.toyrobot.impl.ToyRobotMovement;
import org.iress.toyrobot.interfaces.Boundary;

import java.util.Optional;

/**
 * Service class to compute the position of the toy robot
 */
public class EvaluateToyPositionService {

    Boundary tableBoundary;
    ToyRobotMovement moveToyRobotForward;

    public EvaluateToyPositionService(Boundary tableBoundary, ToyRobotMovement moveToyRobotForward) {

        this.tableBoundary = tableBoundary;
        this.moveToyRobotForward = moveToyRobotForward;
    }

    /**
     * Determine the position of the toy robot on the table at X,Y and facing in the direction NORTH | SOUTH | EAST | WEST
     *
     * @param toyRobotPositionAndDirection Robot position
     * @return true if placed successfully
     * @throws ToyRobotException
     */
    public boolean positionToyRobot(ToyRobotPositionAndDirection toyRobotPositionAndDirection) throws ToyRobotException, NullPointerException {

        tableBoundary = Optional.ofNullable(tableBoundary).orElseThrow(() -> new ToyRobotException("Invalid boundary"));
        toyRobotPositionAndDirection = Optional.of(toyRobotPositionAndDirection).orElseThrow(() -> new ToyRobotException("Invalid position"));
        toyRobotPositionAndDirection = Optional.of(toyRobotPositionAndDirection).orElseThrow(() -> new ToyRobotException("Invalid direction"));

        if (!tableBoundary.isToyRobotInsideTheTableBoundary(toyRobotPositionAndDirection)) {

            return false;
        }

        moveToyRobotForward.isToyRobotSetPosition(toyRobotPositionAndDirection);
        return true;
    }

    /**
     * Evaluate the toy robot position based on user input captured and matched against String args
     *
     * @param inputString command string
     * @return string value of the executed command
     * @throws ToyRobotException
     *
     */
    public String evaluateRobotPosition(String inputString) throws ToyRobotException {

        String[] args = inputString.split(" ");

        /**
         * Assign and verify user input to Commands enum accepted values
         */
        Commands command;

        try {

            command = Commands.valueOf(args[0]);
        } catch (Exception exception) {

            throw new ToyRobotException("Unrecognised/Invalid command");
        }

        if (command == Commands.PLACE && args.length < 2) {

            throw new ToyRobotException("Invalid/Incomplete command");
        }

        // validate PLACE params
        String[] params;
        int x = 0;
        int y = 0;
        Directions commandDirection = null;

        if (command == Commands.PLACE) {

            params = args[1].split(",");

            try {
                x = Integer.parseInt(params[0]);
                y = Integer.parseInt(params[1]);

                commandDirection = Directions.valueOf(params[2]);
            } catch (Exception exception) {

                throw new ToyRobotException("Invalid/Incomplete command");
            }
        }

        String resultingPosition;

        try {
            switch (command) {
                case PLACE:
                    resultingPosition = String.valueOf(positionToyRobot(new ToyRobotPositionAndDirection(x, y, commandDirection)));
                    break;

                case MOVE:
                    ToyRobotPositionAndDirection newToyRobotPositionPosition = moveToyRobotForward.getToyRobotPosition().computePosition();
                    if (!tableBoundary.isToyRobotInsideTheTableBoundary(newToyRobotPositionPosition)) {
                        resultingPosition = String.valueOf(false);
                    } else {
                        resultingPosition = String.valueOf(moveToyRobotForward.moveToyRobotForward(newToyRobotPositionPosition));
                    }
                    break;

                case LEFT:
                    resultingPosition = String.valueOf(moveToyRobotForward.rotateToyRobotToLeft());
                    break;

                case RIGHT:
                    resultingPosition = String.valueOf(moveToyRobotForward.rotateToyRobotToRight());
                    break;

                case REPORT:
                    resultingPosition = report();
                    break;

                default:
                    throw new ToyRobotException("Unrecognised command");
            }
        } catch (Exception exception) {

            throw new ToyRobotException("Please place the toy robot on the table");
        }
        return resultingPosition;
    }

    /**
     * Method to return the current position of the toy robot in X,Y,DIRECTION format
     */
    public String report() throws ToyRobotException {

        ToyRobotPositionAndDirection toyRobotPosition = moveToyRobotForward.getToyRobotPosition();
        toyRobotPosition = Optional.ofNullable(toyRobotPosition)
                .orElseThrow(() -> new ToyRobotException("Please place the toy robot on the table"));

        return "Toy Robot's current position is " + toyRobotPosition.getX() + ","
                + toyRobotPosition.getY() + ","
                + toyRobotPosition.getDirection();
    }
}
