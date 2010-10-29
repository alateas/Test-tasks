import java.util.ArrayList;

/**
 *  Базовый класс для алгоритмов поиска пути
 */
public abstract class RouteFinderStrategy {
    protected GameField field;
    protected Coords startPoint;
    protected Coords finishPoint;
    protected Coords currentPosition;
    private ArrayList<Coords> route = new ArrayList<Coords>();

    public RouteFinderStrategy(GameField field) {
        this.field = field;
        this.startPoint = new Coords(0, 0);
        this.finishPoint = new Coords(GameField.WIDTH - 1, GameField.HEIGHT - 1);
    }

    protected abstract void implementRouting();

    public ArrayList<Coords> getRoute() {
        implementRouting();
        return route;
    }

    protected void move(Coords destination) {
        currentPosition = destination;
        updateRoute();
    }

    protected void updateRoute() {
        route.add(currentPosition);
    }
}