import org.iress.toyrobot.constants.Directions;
import org.iress.toyrobot.impl.ToyRobotPositionAndDiretion;
import org.junit.Assert;
import org.junit.Test;

public class ToyRobotPositionAndDiretionTest {

    @Test
    public void testToyRobotNewPositionAfterDirectionAndPositionChange() throws Exception {

        ToyRobotPositionAndDiretion toyRobotPositionAndDiretion = new ToyRobotPositionAndDiretion(0, 0, Directions.EAST);

        ToyRobotPositionAndDiretion positionAndDiretion = toyRobotPositionAndDiretion.computePosition();
        Assert.assertEquals(positionAndDiretion.getX(), 1);
        Assert.assertEquals(positionAndDiretion.getY(), 0);
        Assert.assertEquals(positionAndDiretion.getDirection(), Directions.EAST);

        positionAndDiretion = positionAndDiretion.computePosition();
        Assert.assertNotEquals(positionAndDiretion.getX(), 1);
        Assert.assertEquals(positionAndDiretion.getY(), 0);
        Assert.assertEquals(positionAndDiretion.getDirection(), Directions.EAST);

        positionAndDiretion.setDirection(Directions.NORTH);
        positionAndDiretion = positionAndDiretion.computePosition();
        Assert.assertNotEquals(positionAndDiretion.getX(), 1);
        Assert.assertEquals(positionAndDiretion.getY(), 1);
        Assert.assertNotEquals(positionAndDiretion.getDirection(), Directions.EAST);
    }
}
