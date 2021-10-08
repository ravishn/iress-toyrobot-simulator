import org.iress.toyrobot.constants.Directions;
import org.junit.Assert;
import org.junit.Test;


public class ToyRobotRotationTest {

    @Test
    public void testRotateToyRobotInAllPossibleDirections() {

        Directions direction = Directions.EAST;

        direction = direction.rotateLeft();
        Assert.assertEquals(direction, Directions.NORTH);

        direction = direction.rotateLeft();
        Assert.assertEquals(direction, Directions.WEST);

        direction = direction.rotateLeft();
        Assert.assertEquals(direction, Directions.SOUTH);

        direction = direction.rotateLeft();
        Assert.assertEquals(direction, Directions.EAST);

        direction = direction.rotateLeft();
        Assert.assertEquals(direction, Directions.NORTH);

        direction = direction.rotateRight();
        Assert.assertEquals(direction, Directions.EAST);

        direction = direction.rotateRight();
        Assert.assertEquals(direction, Directions.SOUTH);

        direction = direction.rotateRight();
        Assert.assertEquals(direction, Directions.WEST);

        direction = direction.rotateRight();
        Assert.assertEquals(direction, Directions.NORTH);

        direction = direction.rotateRight();
        Assert.assertEquals(direction, Directions.EAST);
    }
}
