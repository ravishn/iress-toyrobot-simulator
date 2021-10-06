package org.iress.toyrobot.interfaces;

import org.iress.toyrobot.impl.ToyRobotPositionAndDiretion;

public interface Boundary {

    boolean isToyRobotInsideTheTableBoundary(ToyRobotPositionAndDiretion position);
}