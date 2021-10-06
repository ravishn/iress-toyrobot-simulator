package org.iress.toyrobot.impl;

import org.iress.toyrobot.exception.ToyRobotException;

public class ToyRobot {

    private ToyRobotPositionAndDiretion toyRobotPosition;

    /**
     * Empty constructor for unit tests
     */
    public ToyRobot() {
        
    }

    public ToyRobot(ToyRobotPositionAndDiretion toyRobotPosition) {

        this.toyRobotPosition = toyRobotPosition;
    }

    public boolean isToyRobotSetPosition(ToyRobotPositionAndDiretion toyRobotPosition) {

        if (toyRobotPosition == null) {

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
    public boolean moveToyRobotForward(ToyRobotPositionAndDiretion newToyRobotPosition) {

        if (newToyRobotPosition == null) {

            return false;
        }

        // change position
        this.toyRobotPosition = newToyRobotPosition;

        return true;
    }

    public ToyRobotPositionAndDiretion getToyRobotPosition() {

        return this.toyRobotPosition;
    }

    /**
     * Rotates the robot 90 degrees LEFT
     *
     * @return true if rotated successfully
     */
    public boolean rotateToyRobotToLeft() {
        if (this.toyRobotPosition.direction == null) {

            return false;
        }

        this.toyRobotPosition.direction = this.toyRobotPosition.direction.rotateLeft();
        return true;
    }

    /**
     * Rotates the robot 90 degrees RIGHT
     *
     * @return true if rotated successfully
     */
    public boolean rotateToyRobotToRight() {

        if (this.toyRobotPosition.direction == null) {

            return false;
        }

        this.toyRobotPosition.direction = this.toyRobotPosition.direction.rotateRight();

        return true;
    }
}
