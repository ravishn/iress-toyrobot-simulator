import org.iress.toyrobot.impl.ToyRobotPositionAndDirection;
import org.iress.toyrobot.impl.TableBoundary;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TableBoundaryTest {

    @Test
    public void testIsValidPosition() {
        ToyRobotPositionAndDirection mockToyRobotPosition = mock(ToyRobotPositionAndDirection.class);
        when(mockToyRobotPosition.getX()).thenReturn(6);
        when(mockToyRobotPosition.getY()).thenReturn(7);

        TableBoundary tableBoundary = new TableBoundary(4, 5);
        Assert.assertFalse(tableBoundary.isToyRobotInsideTheTableBoundary(mockToyRobotPosition));

        when(mockToyRobotPosition.getX()).thenReturn(1);
        when(mockToyRobotPosition.getY()).thenReturn(1);
        Assert.assertTrue(tableBoundary.isToyRobotInsideTheTableBoundary(mockToyRobotPosition));

        when(mockToyRobotPosition.getX()).thenReturn(-1);
        when(mockToyRobotPosition.getY()).thenReturn(-1);
        Assert.assertFalse(tableBoundary.isToyRobotInsideTheTableBoundary(mockToyRobotPosition));
    }

}
