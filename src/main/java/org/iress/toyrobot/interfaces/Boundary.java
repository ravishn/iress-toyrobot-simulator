package org.iress.toyrobot.interfaces;

import org.iress.toyrobot.impl.ToyRobotPositionAndDirection;

public interface Boundary {

    boolean isToyRobotInsideTheTableBoundary(ToyRobotPositionAndDirection position);
}