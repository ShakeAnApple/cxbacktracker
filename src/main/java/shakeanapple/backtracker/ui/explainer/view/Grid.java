package shakeanapple.backtracker.ui.explainer.view;

import javafx.scene.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Grid {

    private final Map<GridPoint, DiagramObjectType> occupiedPoints = new HashMap<>();
    private final double cellSize;

    public Grid(double cellSize) {
        this.cellSize = cellSize;
    }

    public Offset adjustCoords(Node fxNode, double scale){
        double leftX = fxNode.getBoundsInParent().getMinX();
        double topY = fxNode.getBoundsInParent().getMinY();
        
        double scaledCellSize = this.cellSize;

        double modX = leftX % scaledCellSize;
        double modY = topY % scaledCellSize;

        double offsetTop = 0;
        double offsetLeft = 0;
        if (modX > 0 ){
            offsetLeft = Math.abs(modX) >= scaledCellSize / 2 ?
                    (scaledCellSize - Math.abs(modX)) : -Math.abs(modX);
        } else {
            offsetLeft = Math.abs(modX) >= scaledCellSize / 2 ?
                    -(scaledCellSize - Math.abs(modX)) : Math.abs(modX);
        }

        if (modY > 0){
            offsetTop = Math.abs(modY) >= scaledCellSize / 2 ?
                    (scaledCellSize - Math.abs(modY)) : -Math.abs(modY);
        } else{
            offsetTop = Math.abs(modY) >= scaledCellSize / 2 ?
                    -(scaledCellSize - Math.abs(modY)) : Math.abs(modY);
        }

        return new Offset(offsetTop, offsetLeft);
    }

    public DiagramObjectType objectTypeOnThePoint(GridPoint point){
        if (this.occupiedPoints.get(point) == null){
            return DiagramObjectType.NONE;
        }
        return this.occupiedPoints.get(point);
    }

    private void occupyPoint(GridPoint point, DiagramObjectType objectType){
        if (this.occupiedPoints.get(point) == null){
            this.occupiedPoints.put(point, objectType);
        } else{
            this.occupiedPoints.replace(point, objectType);
        }
    }

    public void placeNode(Node fxNode){
        GridPoint topLeft = new GridPoint(fxNode.getBoundsInParent().getMinX(), fxNode.getBoundsInParent().getMinY());
        this.occupyPoint(topLeft, DiagramObjectType.BLOCK);

        GridPoint topRight = new GridPoint(fxNode.getBoundsInParent().getMaxX(), fxNode.getBoundsInParent().getMinY());
        this.occupyPoint(topRight, DiagramObjectType.BLOCK);

        GridPoint bottomLeft = new GridPoint(fxNode.getBoundsInParent().getMinX(), fxNode.getBoundsInParent().getMaxY());
        this.occupyPoint(bottomLeft, DiagramObjectType.BLOCK);

        GridPoint bottomRight = new GridPoint(fxNode.getBoundsInParent().getMaxX(), fxNode.getBoundsInParent().getMaxY());
        this.occupyPoint(bottomRight, DiagramObjectType.BLOCK);

        for (long x = topLeft.x + 1; x < topRight.x; x++){
            for (long y = topLeft.y + 1; y <= bottomLeft.y; y++){
                this.occupyPoint(new GridPoint(x, y), DiagramObjectType.BLOCK);
            }
        }
    }

    public void removeNode(Node fxNode) {
        GridPoint topLeft = new GridPoint(fxNode.getBoundsInParent().getMinX(), fxNode.getBoundsInParent().getMinY());
        this.occupiedPoints.remove(topLeft);

        GridPoint topRight = new GridPoint(fxNode.getBoundsInParent().getMaxX(), fxNode.getBoundsInParent().getMinY());
        this.occupiedPoints.remove(topRight);

        GridPoint bottomLeft = new GridPoint(fxNode.getBoundsInParent().getMinX(), fxNode.getBoundsInParent().getMaxY());
        this.occupiedPoints.remove(bottomLeft);

        GridPoint bottomRight = new GridPoint(fxNode.getBoundsInParent().getMaxX(), fxNode.getBoundsInParent().getMaxY());
        this.occupiedPoints.remove(bottomRight);

        for (long x = topLeft.x + 1; x < topRight.x; x++){
            for (long y = topLeft.y + 1; y <= bottomLeft.y; y++){
                this.occupiedPoints.remove(new GridPoint(x, y));
            }
        }
    }

    class GridPoint{
        private final long x;
        private final long y;

        public GridPoint(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public GridPoint(double sceneX, double sceneY){
            this.x = (int)(sceneX / cellSize);
            this.y = (int)(sceneY / cellSize);
        }

        public long getX() {
            return this.x;
        }

        public long getY() {
            return this.y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            GridPoint gridPoint = (GridPoint) o;
            return x == gridPoint.x &&
                    y == gridPoint.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

}
