package org.iress.toyrobot.impl;

import org.iress.toyrobot.interfaces.Boundary;

/**
 * Class to set the table boundaries
 */
public class TableBoundary implements Boundary {

    int width;
    int depth;

    public TableBoundary(int width, int depth) {
        this.width = width;
        this.depth = depth;
    }

    /**
     * Overridden interface method to validate whether or not the toy robot is within the boundary of the table
     * @param position
     * @return
     */
    public boolean isToyRobotInsideTheTableBoundary(ToyRobotPositionAndDirection position) {

        return !(position.getX() > this.depth ||
                position.getX() < 0 ||
                position.getY() > this.width ||
                position.getY() < 0
        );
    }
}
