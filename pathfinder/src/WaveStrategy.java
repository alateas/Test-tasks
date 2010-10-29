import java.util.ArrayList;
import java.util.Arrays;

/**
* Волновой алгоритм
*/
public class WaveStrategy extends RouteFinderStrategy {
    public WaveStrategy(GameField field) {
        super(field);
    }

    protected void implementRouting() {
        dataPreparation();
        move(startPoint);

        while (!currentPosition.equals(finishPoint)) {
            Point currentPoint = field.getPoint(currentPosition);
            Coords nextStep = currentPosition;
            for (Coords neighbor : getFreeNeighbours(currentPosition)) {
                if (field.getPoint(neighbor).getTag() == currentPoint.getTag() - 1) {
                    nextStep = neighbor;
                    break;
                }
            }
            move(nextStep);
        }
    }

    private void dataPreparation() {
        Point point = field.getPoint(finishPoint);
        point.setTag(0);
        waveStep(finishPoint, 0);
    }

    private void waveStep(Coords point, int tag) {
        waveStep(new ArrayList<Coords>(Arrays.asList(point)), tag);
    }

    private void waveStep(ArrayList<Coords> points, int tag) {
        ArrayList<Coords> nextStepPoints = getNextStepPoints(points);
        if (!nextStepPoints.isEmpty()) {
            tagPoints(nextStepPoints, tag + 1);
            waveStep(nextStepPoints, tag + 1);
        }
    }

    private ArrayList<Coords> getNextStepPoints(ArrayList<Coords> points) {
        ArrayList<Coords> nextStepPoints = new ArrayList<Coords>();
        for (Coords point : points) {
            for (Coords coords : getFreeUntaggedNeighbours(point)) {
                if (!nextStepPoints.contains(coords)) {
                    nextStepPoints.add(coords);
                }
            }
        }
        return nextStepPoints;
    }

    private ArrayList<Coords> getFreeUntaggedNeighbours(Coords coords) {
        ArrayList<Coords> allowedNeighbours = new ArrayList<Coords>();
        for (Coords neighbor : getAllNeighbours(coords)) {
            if(field.isFreeAndUntagged(neighbor)) {
                allowedNeighbours.add(neighbor);
            }
        }
        return allowedNeighbours;
    }

    private ArrayList<Coords> getFreeNeighbours(Coords coords) {
        ArrayList<Coords> allowedNeighbours = new ArrayList<Coords>();
        for (Coords neighbor : getAllNeighbours(coords)) {
            if(field.isFree(neighbor)) {
                allowedNeighbours.add(neighbor);
            }
        }
        return allowedNeighbours;
    }

    private ArrayList<Coords> getAllNeighbours(Coords coords) {
        ArrayList<Coords> allNeighbours = new ArrayList<Coords>();
        allNeighbours.add(coords.getLeft());
        allNeighbours.add(coords.getRight());
        allNeighbours.add(coords.getTop());
        allNeighbours.add(coords.getBottom());
        return allNeighbours;
    }

    private void tagPoints(ArrayList<Coords> coordsList, int tag) {
        for(Coords coords : coordsList) {
            field.getPoint(coords).setTag(tag);
        }
    }

}