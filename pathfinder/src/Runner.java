/**
* Объект, совершающий перемещение по полю
*/
public class Runner {
    private GameField field;
    private RouteFinderStrategy strategy;

    public Runner() {
        this.field = new GameField();
        this.strategy = new WaveStrategy(this.field);
    }

    public Runner(RouteFinderStrategy strategy) {
        this.field = new GameField();
        this.strategy = strategy;
    }

    public void start() {
        field.setRoute(strategy.getRoute());
        field.print();
    }
}