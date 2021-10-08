# Toy Robot Code Challenge

This is a Java commandline based application built using Maven that simulates a Toy Robot that can move around a table
within a specified boundary. 

## Description
- The application is a simulation of a toy robot moving on a square table-top, of dimensions 5 units x 5 units. There are no
other obstructions on the table surface. The robot is free to roam around the surface of the table, but must be prevented
from falling to destruction. Any movement that would result in the robot falling from the table must be prevented,
however further valid movement commands must still be allowed.


- Create a console application that can read in commands of the following form

  PLACE X,Y,F

  MOVE

  LEFT

  RIGHT

  REPORT

    
    PLACE will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST. The origin (0,0)
    can be considered to be the SOUTH WEST most corner.

    It is required that the first command to the robot is a PLACE command, after that, any sequence of commands may 
    be issued, in any order, including another PLACE command. The
    application should discard all commands in the sequence until a valid PLACE command has been executed.
    MOVE will move the toy robot one unit forward in the direction it is currently facing.
    LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without changing the position of the robot.
    REPORT will announce the X,Y and F of the robot. This can be in any form, but standard output is sufficient.
    A robot that is not on the table can choose to ignore the MOVE, LEFT, RIGHT and REPORT commands.
    Input can be from a file, or from standard input, as the developer chooses.
    Provide test data to exercise the application.
    It is not required to provide any graphical output showing the movement of the toy robot.
    The application should handle error states appropriately and be robust to user input.

## Constraints
  The toy robot must not fall off the table during movement. This also includes the initial placement of the toy robot. Any
  move that would cause the robot to fall must be ignored.

## Example Input and Output:
a)

    PLACE 0,0,NORTH
    MOVE
    REPORT
    Output: 0,1,NORTH


b)

    PLACE 0,0,NORTH
    LEFT
    REPORT
    Output: 0,0,WEST
c)

    PLACE 1,2,EAST
    MOVE
    MOVE
    LEFT
    MOVE
    REPORT
    Output: 3,3,NORTH


## Prerequisite
- Requires Java 8 to compile and test
- Requires Maven version 3.6.x with settings.xml pointing to global Maven repo
- Dependencies are resolved from Maven global repo
  NOTE: If you have a local Maven settings.xml file pointing to your artifactory, make sure versions of the packages resolve
- Requires JUnit and Mockito for compiling and running unit tests

## Solution
- Entry point to the application is ToyRobotApplication.java class which contains a main() method.
- Application accepts console inputs from user. Follow the prompts to move the toy robot around the table
- Exception handling is managed by ToyRobotException.java class across all the classes
- Service class EvaluateToyPositionService.java determines whether the toy robot can move to the position from the 
  command or ignore if it is has reached the table boundary
- test package contains all the relevant unit tests to cover all possible scenarios and methods being implemented
- pom.xml is the Maven file that contains all the required libraries and dependencies and steps for building and packaging
  the application

## Compiling the application
- Use this Maven command to compile the application `mvn compile`
- Alternatively, use `mvn clean compile` to clean up generated artifacts and perform a clean compile

## Testing the application
- Use this Maven command for running all the tests `mvn test`

## Execute the application
- Use this command to execute the application `mvn exec:java`

## Build and package
- Use this command to create the executable jar file inside *target/* folder `mvn package`
- Alternatively, user `mvn clean package` to clean up all the generated artifacts and package a fresh copy of the jar file