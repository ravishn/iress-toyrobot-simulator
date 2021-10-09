package org.iress.toyrobot.impl;

import org.iress.toyrobot.constants.Directions;
import org.iress.toyrobot.exception.ToyRobotException;

/**
 * Class to set the position and direction of the toy robot
 */
public class ToyRobotPositionAndDirection {

    int x;
    int y;
    Directions direction;

    /**
     * Constructor to store the position and direction of the toy robot
     * @param position
     */
    public ToyRobotPositionAndDirection(ToyRobotPositionAndDirection position) {

        this.x = position.getX();
        this.y = position.getY();
        this.direction = position.getDirection();
    }

    /**
     * for unit tests
     * @param x
     * @param y
     * @param direction
     */
    public ToyRobotPositionAndDirection(int x, int y, Directions direction) {

        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {

        return this.x;
    }

    public int getY() {

        return this.y;
    }

    public Directions getDirection() {

        return this.direction;
    }

    public void setDirection(Directions direction) {

        this.direction = direction;
    }

    /**
     * update the new position of the toy robot computed upon user commands
     * @param x
     * @param y
     */
    public void updateToyRobotPosition(int x, int y) {

        this.x = this.x + x;
        this.y = this.y + y;
    }

    /**
     * Method to compute the position of the toy robot based on user input
     * @return
     * @throws ToyRobotException
     */
    public ToyRobotPositionAndDirection computePosition() throws ToyRobotException {

        if (this.direction == null) {

            throw new ToyRobotException("Invalid robot direction");
        }

        ToyRobotPositionAndDirection updatePosition = new ToyRobotPositionAndDirection(this);

        switch (this.direction) {

            case NORTH:
                updatePosition.updateToyRobotPosition(0, 1);
                break;

            case EAST:
                updatePosition.updateToyRobotPosition(1, 0);
                break;

            case SOUTH:
                updatePosition.updateToyRobotPosition(0, -1);
                break;

            case WEST:
                updatePosition.updateToyRobotPosition(-1, 0);
                break;
        }

        return updatePosition;
    }
}
