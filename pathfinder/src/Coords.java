import java.util.Random;

/**
 * Математическая точка в двухмерной декартовой системе координат
 */
public class Coords {
    private int x,y;

    static public Coords createRandomPositiveCoords(int xLimit, int yLimit) {
        Random rand = new Random();
        return new Coords(rand.nextInt(xLimit + 1), rand.nextInt(yLimit + 1));
    }

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override public String toString() {
        return "(" + Integer.toString(x) + "," + Integer.toString(y) + ")";
    }

    @Override public boolean equals(Object obj) {
        if ( this ==  obj ) {
            return true;
        }
        if ( !( obj instanceof Coords) ) {
            return false;
        }

        Coords point = (Coords)obj;

        return x==point.x && y==point.y;
    }

    public Coords getRight() {
        return new Coords(x + 1, y);
    }

    public Coords getLeft() {
        return new Coords(x - 1, y);
    }

    public Coords getTop() {
        return new Coords(x, y - 1);
    }

    public Coords getBottom() {
        return new Coords(x, y + 1);
    }
}