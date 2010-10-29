import java.util.ArrayList;

/**
 * Игровое поле
 */
public class GameField {
    final static int WIDTH = 5;
    final static int HEIGHT = 5;
    final static int BARRIERS_COUNT = 0;

    private Point[][] fieldData = new Point[WIDTH][HEIGHT];

    public GameField() {
        int x,y;
        for(x=0;x<WIDTH;x++){
            for(y=0;y< HEIGHT;y++){
                fieldData[x][y] = new Point();
            }
        }
        fieldData[0][0] = new Point();
        fieldData[WIDTH-1][HEIGHT-1] = new Point();
        makeBarriers(BARRIERS_COUNT);
    }

    public void makeBarriers(int barriersCount) {
        for (int i=0;i<barriersCount;i++) {
            setRandomBarrier();
        }
    }

    private void setRandomBarrier() {
        Point point = getRandomPoint();
        if (point.isFree()) {
            point.setBarrierStatus();
        } else {
            setRandomBarrier();
        }
    }

    private Point getRandomPoint() {
        Coords randCoords = Coords.createRandomPositiveCoords(WIDTH-1, HEIGHT-1);
        return getPoint(randCoords);
    }

    public boolean isFree(Coords coords) {
        boolean result = false;
        try {
            return getPoint(coords).isFree();
        } catch (ArrayIndexOutOfBoundsException e) {
           return false;
        }
    }

    public boolean isFreeAndUntagged (Coords coords) {
        boolean result = false;
        try {
            Point point = getPoint(coords);
            return point.isFree() && !point.isTagged();
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public Point getPoint (Coords coords) {
        return fieldData[coords.getX()][coords.getY()];
    }

    public void setRoute(ArrayList<Coords> route) {
        for (Coords point : route) {
            fieldData[point.getX()][point.getY()].setRouteStatus();
        }
    }

    public void print() {
        int x,y;
        for(y=0;y<WIDTH;y++){
            for(x=0;x< HEIGHT;x++){
                System.out.print(fieldData[x][y]);
            }
            System.out.println();
        }
    }

    public void printTags() {
        int x,y;
        for(y=0;y<WIDTH;y++){
            for(x=0;x< HEIGHT;x++){
                if (fieldData[x][y].isTagged()) {
                    System.out.print(fieldData[x][y].getTag());
                } else {
                    System.out.print("#");
                }
                System.out.print(",");
            }
            System.out.println();
        }
    }

}