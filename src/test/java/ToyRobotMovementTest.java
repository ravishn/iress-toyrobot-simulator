import org.iress.toyrobot.exception.ToyRobotException;
import org.iress.toyrobot.constants.Directions;
import org.iress.toyrobot.impl.ToyRobotPositionAndDiretion;
import org.iress.toyrobot.impl.ToyRobotMovement;
import org.junit.Assert;
import org.junit.Test;

public class ToyRobotMovementTest {

    @Test
    public void testToyRobotMovementAroundTheTableBoundary() throws ToyRobotException {

        ToyRobotMovement moveToyRobot = new ToyRobotMovement(new ToyRobotPositionAndDiretion(0, 0, Directions.NORTH));

        Assert.assertTrue(moveToyRobot.moveToyRobotForward());
        Assert.assertEquals(0, moveToyRobot.getToyRobotPosition().getX());
        Assert.assertEquals(1, moveToyRobot.getToyRobotPosition().getY());
        Assert.assertEquals(Directions.NORTH, moveToyRobot.getToyRobotPosition().getDirection());

        moveToyRobot.isToyRobotSetPosition(new ToyRobotPositionAndDiretion(1, 2, Directions.EAST));
        moveToyRobot.moveToyRobotForward();
        moveToyRobot.moveToyRobotForward();
        moveToyRobot.rotateToyRobotToLeft();
        moveToyRobot.moveToyRobotForward();

        Assert.assertEquals(3, moveToyRobot.getToyRobotPosition().getX());
        Assert.assertEquals(3, moveToyRobot.getToyRobotPosition().getY());
        Assert.assertEquals(Directions.NORTH, moveToyRobot.getToyRobotPosition().getDirection());

        moveToyRobot.isToyRobotSetPosition(new ToyRobotPositionAndDiretion(0, 0, Directions.NORTH));
        moveToyRobot.rotateToyRobotToRight();
        Assert.assertEquals(Directions.EAST, moveToyRobot.getToyRobotPosition().getDirection());
        moveToyRobot.rotateToyRobotToRight();
        Assert.assertEquals(Directions.SOUTH, moveToyRobot.getToyRobotPosition().getDirection());
        moveToyRobot.rotateToyRobotToRight();
        Assert.assertEquals(Directions.WEST, moveToyRobot.getToyRobotPosition().getDirection());
        moveToyRobot.rotateToyRobotToRight();
        Assert.assertEquals(Directions.NORTH, moveToyRobot.getToyRobotPosition().getDirection());
        moveToyRobot.rotateToyRobotToRight();
        Assert.assertEquals(Directions.EAST, moveToyRobot.getToyRobotPosition().getDirection());
    }

}