import java.util.Random;

/**
 * Точка игрового поля
 */
public class Point {
    private final int FREE = 0;
    private final int BARRIER = 1;
    private final int ROUTE = 2;

    private int status = FREE;
    private int tag;
    private boolean tagged = false;

    private int generateStatus(int percentProbability) {
        Random rand = new Random();
        if (rand.nextInt(100) < percentProbability) {
            return BARRIER;
        }
        return FREE;
    }

    public void setBarrierStatus() {
        if (status != BARRIER) {
            status = BARRIER;
        } else {
            throw new RuntimeException ("This point has already barrier");
        }
    }

    public void setRouteStatus () {
        if (isFree()) {
            status = ROUTE;
        } else {
            throw new RuntimeException ("This point is not free");
        }
    }

    public void setTag (int tag) {
        if (!tagged) {
            this.tag = tag;
            tagged = true;
        } else {
            throw new RuntimeException ("This point has already been tagged");
        }
    }

    public int getTag() {
        return tag;
    }

    public boolean isTagged() {
        return tagged;
    }
    public boolean isFree() {
        return status == FREE;
    }


    @Override public String toString() {
        switch (status) {
            case FREE:
                return ".";
            case ROUTE:
                return "*";
            case BARRIER:
            default:
                return "#";
        }
    }
}