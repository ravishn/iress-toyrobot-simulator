package org.iress.toyrobot;

import org.iress.toyrobot.exception.ToyRobotException;
import org.iress.toyrobot.impl.TableBoundary;
import org.iress.toyrobot.impl.ToyRobotMovement;
import org.iress.toyrobot.service.EvaluateToyPositionService;

import java.io.*;
import java.util.Properties;

/**
 * Entry point to the application
 */
public class ToyRobotApplication {

    public static void main(String[] args) throws IOException {

        ToyRobotApplication application = new ToyRobotApplication();
        TableBoundary tableBoundary = application.getTableDimensions();

        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        if (userInputReader == null) {

            System.err.println("No input");
            System.exit(1);
        }

        ToyRobotMovement moveToyRobotForward = new ToyRobotMovement();
        EvaluateToyPositionService evaluateToyPosition = new EvaluateToyPositionService(tableBoundary, moveToyRobotForward);

        System.out.println("Position your toy robot");
        System.out.println("PLACE X,Y,NORTH | SOUTH | EAST | WEST, MOVE, LEFT, RIGHT, REPORT | EXIT");

        boolean isRunning = true;
        while (isRunning) {

            String consoleInput = String.valueOf(userInputReader.readLine());

            if ("EXIT".equalsIgnoreCase(consoleInput)) {

                isRunning = false;
            } else {

                try {
                    String commandEvaluationResult = evaluateToyPosition.evaluateRobotPosition(consoleInput);
                    if (commandEvaluationResult.equals("true")) {

                        System.out.println("Command acknowledged and executed");
                    } else if (commandEvaluationResult.equals("false")) {

                        System.out.println("Invalid position / Out of table boundary. Command ignored");
                    } else {

                        System.out.println(commandEvaluationResult);
                    }
                } catch (ToyRobotException toyRobotException) {

                    System.out.println(toyRobotException.getMessage());
                }
            }
        }
    }

    /**
     * Method to return table boundary defined in the properties
     * @return
     * @throws IOException
     */
    public TableBoundary getTableDimensions() throws IOException {

        InputStream inputStream = ToyRobotApplication.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();

        if (inputStream == null) {

            System.out.println("Sorry, unable to find application.properties file");
        }

        properties.load(inputStream);
        String tableWidth = properties.getProperty("table.width");
        String tableDepth = properties.getProperty("table.depth");

        TableBoundary tableBoundary = new TableBoundary(Integer.valueOf(tableWidth), Integer.valueOf(tableDepth));

        return tableBoundary;
    }
}