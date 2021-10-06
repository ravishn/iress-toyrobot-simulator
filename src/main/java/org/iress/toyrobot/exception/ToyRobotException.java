package org.iress.toyrobot.exception;

public class ToyRobotException extends Exception {

    /**
     * Generic exception for error handling throughout the application
     * @param errorMessage
     */
    public ToyRobotException(String errorMessage) {

        super(errorMessage);
    }
}
