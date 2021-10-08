import org.iress.toyrobot.service.EvaluateToyPositionService;
import org.iress.toyrobot.exception.ToyRobotException;
import org.iress.toyrobot.constants.Directions;
import org.iress.toyrobot.impl.ToyRobotPositionAndDiretion;
import org.iress.toyrobot.impl.TableBoundary;
import org.iress.toyrobot.impl.ToyRobotMovement;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EvaluateToyToyRobotMovementPositionAndDiretionTest {

    // Update this if width and depth in application.properties is updated
    final int TABLE_WIDTH = 5;
    final int TABLE_DEPTH = 5;

    @Rule
    public org.junit.rules.ExpectedException thrown = ExpectedException.none();

    @Test
    public void testInitialPlacingOfTheToyRobot() throws ToyRobotException {

        TableBoundary tableBoundary = new TableBoundary(TABLE_DEPTH, TABLE_WIDTH);
        ToyRobotMovement toyRobot = new ToyRobotMovement();
        EvaluateToyPositionService evaluateToyPosition = new EvaluateToyPositionService(tableBoundary, toyRobot);

        Assert.assertTrue(evaluateToyPosition.positionToyRobot(new ToyRobotPositionAndDiretion(0, 1, Directions.NORTH)));
        Assert.assertTrue(evaluateToyPosition.positionToyRobot(new ToyRobotPositionAndDiretion(2, 2, Directions.SOUTH)));
        Assert.assertFalse(evaluateToyPosition.positionToyRobot(new ToyRobotPositionAndDiretion(6, 6, Directions.WEST)));
        Assert.assertFalse(evaluateToyPosition.positionToyRobot(new ToyRobotPositionAndDiretion(-1, 5, Directions.EAST)));
    }

    @Test
    public void testToyRobotPositionAndDirectionExceptions() throws ToyRobotException {

        TableBoundary board = new TableBoundary(TABLE_DEPTH, TABLE_WIDTH);
        ToyRobotMovement toyRobot = new ToyRobotMovement();
        EvaluateToyPositionService evaluateToyPosition = new EvaluateToyPositionService(board, toyRobot);

        thrown.expect(ToyRobotException.class);
        evaluateToyPosition.positionToyRobot(null);
        thrown.expect(ToyRobotException.class);
        evaluateToyPosition.positionToyRobot(new ToyRobotPositionAndDiretion(0, 1, null));
    }

    @Test
    public void testEvaluateToyRobotPositionService() throws ToyRobotException {

        TableBoundary board = new TableBoundary(TABLE_DEPTH, TABLE_WIDTH);
        ToyRobotMovement toyRobot = new ToyRobotMovement();
        EvaluateToyPositionService evaluateToyPosition = new EvaluateToyPositionService(board, toyRobot);

        evaluateToyPosition.evaluateRobotPosition("PLACE 0,0,NORTH");
        Assert.assertEquals("Toy Robot's current position is 0,0,NORTH", evaluateToyPosition.evaluateRobotPosition("REPORT"));

        evaluateToyPosition.evaluateRobotPosition("MOVE");
        evaluateToyPosition.evaluateRobotPosition("RIGHT");
        evaluateToyPosition.evaluateRobotPosition("MOVE");
        Assert.assertEquals("Toy Robot's current position is 1,1,EAST", evaluateToyPosition.evaluateRobotPosition("REPORT"));

        // test to assert if the commands are ignored if the toy robot goes outside of the table boundaries
        for (int i = 0; i < 100; i++) {

            evaluateToyPosition.evaluateRobotPosition("MOVE");
        }
        Assert.assertEquals("Toy Robot's current position is 5,1,EAST", evaluateToyPosition.evaluateRobotPosition("REPORT"));

        // test to assert if the rotation function is working as expected
        for (int i = 0; i < 4; i++) {

            evaluateToyPosition.evaluateRobotPosition("LEFT");
        }
        Assert.assertEquals("Toy Robot's current position is 5,1,EAST", evaluateToyPosition.evaluateRobotPosition("REPORT"));

        // invalid command scenarios
        thrown.expect(ToyRobotException.class);
        Assert.assertEquals("Invalid command", evaluateToyPosition.evaluateRobotPosition("PLACE,NORTH,4,2"));
        thrown.expect(ToyRobotException.class);
        Assert.assertEquals("Invalid command", evaluateToyPosition.evaluateRobotPosition("LETF"));
        thrown.expect(ToyRobotException.class);
        Assert.assertEquals("Invalid command", evaluateToyPosition.evaluateRobotPosition("RIHGT"));
        thrown.expect(ToyRobotException.class);
        Assert.assertEquals("Invalid command", evaluateToyPosition.evaluateRobotPosition("EATS"));
        thrown.expect(ToyRobotException.class);
        Assert.assertEquals("Invalid command", evaluateToyPosition.evaluateRobotPosition("WETS"));
    }
}
