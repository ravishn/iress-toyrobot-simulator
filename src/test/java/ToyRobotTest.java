import org.iress.toyrobot.exception.ToyRobotException;
import org.iress.toyrobot.constants.Directions;
import org.iress.toyrobot.impl.ToyRobotPositionAndDiretion;
import org.iress.toyrobot.impl.ToyRobot;
import org.junit.Assert;
import org.junit.Test;

public class ToyRobotTest {

    @Test
    public void testToyRobotMovementAroundTheTableBoundary() throws ToyRobotException {

        ToyRobot toyRobot = new ToyRobot(new ToyRobotPositionAndDiretion(0, 0, Directions.NORTH));

        Assert.assertTrue(toyRobot.moveToyRobotForward());
        Assert.assertEquals(0, toyRobot.getToyRobotPosition().getX());
        Assert.assertEquals(1, toyRobot.getToyRobotPosition().getY());
        Assert.assertEquals(Directions.NORTH, toyRobot.getToyRobotPosition().getDirection());


        toyRobot.isToyRobotSetPosition(new ToyRobotPositionAndDiretion(1, 2, Directions.EAST));
        toyRobot.moveToyRobotForward();
        toyRobot.moveToyRobotForward();
        toyRobot.rotateToyRobotToLeft();
        toyRobot.moveToyRobotForward();

        Assert.assertEquals(3, toyRobot.getToyRobotPosition().getX());
        Assert.assertEquals(3, toyRobot.getToyRobotPosition().getY());
        Assert.assertEquals(Directions.NORTH, toyRobot.getToyRobotPosition().getDirection());

        toyRobot.isToyRobotSetPosition(new ToyRobotPositionAndDiretion(0, 0, Directions.NORTH));
        toyRobot.rotateToyRobotToRight();
        Assert.assertEquals(Directions.EAST, toyRobot.getToyRobotPosition().getDirection());
        toyRobot.rotateToyRobotToRight();
        Assert.assertEquals(Directions.SOUTH, toyRobot.getToyRobotPosition().getDirection());
        toyRobot.rotateToyRobotToRight();
        Assert.assertEquals(Directions.WEST, toyRobot.getToyRobotPosition().getDirection());
        toyRobot.rotateToyRobotToRight();
        Assert.assertEquals(Directions.NORTH, toyRobot.getToyRobotPosition().getDirection());
        toyRobot.rotateToyRobotToRight();
        Assert.assertEquals(Directions.EAST, toyRobot.getToyRobotPosition().getDirection());
    }

}