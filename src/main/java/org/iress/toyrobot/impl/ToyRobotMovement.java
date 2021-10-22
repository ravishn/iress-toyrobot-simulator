package org.iress.toyrobot.impl;

import org.iress.toyrobot.constants.Directions;
import org.iress.toyrobot.exception.ToyRobotException;

import java.util.Optional;

public class ToyRobotMovement {

    private ToyRobotPositionAndDirection toyRobotPosition;

    /**
     * Empty constructor for unit tests
     */
    public ToyRobotMovement() {

    }

    public ToyRobotMovement(ToyRobotPositionAndDirection toyRobotPosition) {

        this.toyRobotPosition = toyRobotPosition;
    }

    public boolean isToyRobotSetPosition(ToyRobotPositionAndDirection toyRobotPosition) {

        Optional<ToyRobotPositionAndDirection> toyRobotPositionAndDirectionOptional = Optional.ofNullable(toyRobotPosition);

        if (!toyRobotPositionAndDirectionOptional.isPresent()) {

            return false;
        }
        this.toyRobotPosition = toyRobotPosition;

        return true;
    }

    /**
     * method with no argument for unit tests
     * @return
     * @throws ToyRobotException
     */
    public boolean moveToyRobotForward() throws ToyRobotException {

        return moveToyRobotForward(toyRobotPosition.computePosition());
    }

    /**
     * Moves the robot one unit forward in the direction it is currently facing
     *
     * @return true if moved successfully
     */
    public boolean moveToyRobotForward(ToyRobotPositionAndDirection newToyRobotPosition) {

        Optional<ToyRobotPositionAndDirection> newToyRobotPositionAndDirectionOptional = Optional.ofNullable(newToyRobotPosition);

        if (!newToyRobotPositionAndDirectionOptional.isPresent()) {

            return false;
        }
        this.toyRobotPosition = newToyRobotPosition;

        return true;
    }

    public ToyRobotPositionAndDirection getToyRobotPosition() {

        return this.toyRobotPosition;
    }

    /**
     * Rotates the robot 90 degrees LEFT
     *
     * @return true if rotated successfully
     */
    public boolean rotateToyRobotToLeft() {
        Optional<Directions> directionsOptional = Optional.ofNullable(this.toyRobotPosition.direction);

        if (!directionsOptional.isPresent()) {

            return false;
        }
        this.toyRobotPosition.direction = directionsOptional.get().rotateLeft();
        return true;
    }

    /**
     * Rotates the robot 90 degrees RIGHT
     *
     * @return true if rotated successfully
     */
    public boolean rotateToyRobotToRight() {

        Optional<Directions> directionsOptional = Optional.ofNullable(this.toyRobotPosition.direction);

        if (!directionsOptional.isPresent()) {

            return false;
        }
        this.toyRobotPosition.direction = directionsOptional.get().rotateRight();

        return true;
    }
}
