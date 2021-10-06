package org.iress.toyrobot.service;

import org.iress.toyrobot.exception.ToyRobotException;
import org.iress.toyrobot.constants.Commands;
import org.iress.toyrobot.constants.Directions;
import org.iress.toyrobot.impl.ToyRobotPositionAndDiretion;
import org.iress.toyrobot.impl.ToyRobot;
import org.iress.toyrobot.interfaces.Boundary;

/**
 * Service class to compute the position of the toy robot
 */
public class EvaluateToyPositionService {

    Boundary tableBoundary;
    ToyRobot toyRobot;

    public EvaluateToyPositionService(Boundary tableBoundary, ToyRobot toyRobot) {

        this.tableBoundary = tableBoundary;
        this.toyRobot = toyRobot;
    }

    /**
     * Determine the position of the toy robot on the table at X,Y and facing in the direction NORTH | SOUTH | EAST | WEST
     *
     * @param toyRobotPositionAndDiretion Robot position
     * @return true if placed successfully
     * @throws ToyRobotException
     */
    public boolean positionToyRobot(ToyRobotPositionAndDiretion toyRobotPositionAndDiretion) throws ToyRobotException {

        if (tableBoundary == null) {

            throw new ToyRobotException("Invalid boundary");
        }

        if (toyRobotPositionAndDiretion == null) {

            throw new ToyRobotException("Invalid position");
        }

        if (toyRobotPositionAndDiretion.getDirection() == null) {

            throw new ToyRobotException("Invalid direction");
        }

        if (!tableBoundary.isToyRobotInsideTheTableBoundary(toyRobotPositionAndDiretion)) {

            return false;
        }

        toyRobot.isToyRobotSetPosition(toyRobotPositionAndDiretion);
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
        } catch (IllegalArgumentException e) {

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

        switch (command) {
            case PLACE:
                resultingPosition = String.valueOf(positionToyRobot(new ToyRobotPositionAndDiretion(x, y, commandDirection)));
                break;

            case MOVE:
                ToyRobotPositionAndDiretion newToyRobotPositionPosition = toyRobot.getToyRobotPosition().computePosition();
                if (!tableBoundary.isToyRobotInsideTheTableBoundary(newToyRobotPositionPosition)) {
                    resultingPosition = String.valueOf(false);
                }
                else {
                    resultingPosition = String.valueOf(toyRobot.moveToyRobotForward(newToyRobotPositionPosition));
                }
                break;

            case LEFT:
                resultingPosition = String.valueOf(toyRobot.rotateToyRobotToLeft());
                break;

            case RIGHT:
                resultingPosition = String.valueOf(toyRobot.rotateToyRobotToRight());
                break;

            case REPORT:
                resultingPosition = report();
                break;

            default:
                throw new ToyRobotException("Unrecognised command");
        }
        return resultingPosition;
    }

    /**
     * Method to return the current position of the toy robot in X,Y,DIRECTION format
     */
    public String report() {
        if (toyRobot.getToyRobotPosition() == null) {

            return null;
        }

        return "Toy Robot's current position is " +toyRobot.getToyRobotPosition().getX() + ","
                                                  + toyRobot.getToyRobotPosition().getY() + ","
                                                  + toyRobot.getToyRobotPosition().getDirection();
    }
}
