package org.iress.toyrobot.constants;

/**
 * ENUM of all valid commands the toy robot can compute
 */
public enum Commands {

    // Position the toy robot against X and Y axes
    PLACE,

    // Move the toy robot by 1 position in the direction specified
    MOVE,

    // Rotate the toy robot by 90deg to the left
    LEFT,

    // Rotate the toy robot by 90deg to the right
    RIGHT,

    // Return the current position of the toy robot
    REPORT
}
