package org.iress.toyrobot.constants;


import java.util.HashMap;
import java.util.Map;

/**
 * ENUM of the directions which the toy robot takes based on commands
 */
public enum Directions {

    // Assign 0 for NORTH
    NORTH(0),

    // Assign 1 for EAST
    EAST(1),

    // Assign 2 for SOUTH
    SOUTH(2),

    // Assign 3 for WEST
    WEST(3);

    private static Map<Integer, Directions> directionsMap = new HashMap<>();

    static {
        for (Directions directionEnum : Directions.values()) {

            directionsMap.put(directionEnum.directionIndex, directionEnum);
        }
    }

    private int directionIndex;

    Directions(int direction) {

        this.directionIndex = direction;
    }

    public static Directions valueOf(int directionNum) {

        return directionsMap.get(directionNum);
    }

    /**
     * Rotate and return the direction of toy robot facing 90deg to the left
     * Offset the direction by -1 so that rotating left will point the toy robot in the order of integers assigned
     */
    public Directions rotateLeft() {

        return rotate(-1);
    }

    /**
     * Rotate and return the direction of toy robot facing 90deg to the right
     * Offset the direction by +1 so that rotating right will point the toy robot in the order of integers assigned
     */
    public Directions rotateRight() {

        return rotate(1);
    }

    /**
     * Compute the resulting rotation based on step
     * @param rotateFactor
     * @return Directions object with updated index
     */
    private Directions rotate(int rotateFactor) {

        int updatedIndex = (this.directionIndex + rotateFactor) < 0 ? directionsMap.size() - 1 : (this.directionIndex + rotateFactor) % directionsMap.size();

        return Directions.valueOf(updatedIndex);
    }
}
