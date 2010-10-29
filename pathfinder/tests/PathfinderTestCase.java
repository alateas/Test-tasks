import org.junit.Test;

import static org.junit.Assert.*;

public class PathfinderTestCase {
    private GameField field;

    @Test
    public void testPointInit() {
        Point point = new Point();
        assertTrue(point.isFree());
        assertFalse(point.isTagged());
    }

    @Test
    public void testPointSetBarrier() {
        Point point = new Point();
        point.setBarrierStatus();
        assertFalse(point.isFree());
    }

    @Test
    public void testRandomCoordsCreation() {
        Coords coords;
        for (int i=0;i<1000;i++) {
            coords = Coords.createRandomPositiveCoords(3, 2);
            assertTrue(coords.getX()<=3 && coords.getX()>=0);
            assertTrue(coords.getY()<=2 && coords.getY()>=0);
        }
    }

    @Test
    public void testMakingBarriers() {
        GameField field = new GameField();
        int expectedBarriersCount = 10;

        field.makeBarriers(expectedBarriersCount);

        Point point;
        int barriersCount = 0;
        for (int y=0;y<GameField.HEIGHT;y++) {
            for(int x=0;x<GameField.WIDTH;x++) {
                point = field.getPoint(new Coords(x, y));
                if (!point.isFree()) {
                    barriersCount++;
                }
            }
        }
        assertEquals("Barriers count", expectedBarriersCount, barriersCount);
    }
}