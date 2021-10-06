import org.iress.toyrobot.impl.ToyRobotPositionAndDiretion;
import org.iress.toyrobot.impl.TableBoundary;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TableBoundaryTest {

    @Test
    public void testIsValidPosition() throws Exception {
        ToyRobotPositionAndDiretion mockPosition = mock(ToyRobotPositionAndDiretion.class);
        when(mockPosition.getX()).thenReturn(6);
        when(mockPosition.getY()).thenReturn(7);

        TableBoundary board = new TableBoundary(4, 5);
        Assert.assertFalse(board.isToyRobotInsideTheTableBoundary(mockPosition));


        when(mockPosition.getX()).thenReturn(1);
        when(mockPosition.getY()).thenReturn(1);
        Assert.assertTrue(board.isToyRobotInsideTheTableBoundary(mockPosition));


        when(mockPosition.getX()).thenReturn(-1);
        when(mockPosition.getY()).thenReturn(-1);
        Assert.assertFalse(board.isToyRobotInsideTheTableBoundary(mockPosition));
    }

}
