package shakeanapple.backtracker.ui.explainer.view;

import java.util.List;

public class CanvasMirror {
    private Grid grid;
    private static final double GRID_CELL_SIZE_PX = 10;



    public CanvasMirror() {
        this.grid = new Grid(GRID_CELL_SIZE_PX);
    }

    public DiagramObjectType objectTypeOn(int gridX, int gridY){

        return DiagramObjectType.NONE;
    }

    public void putWire(List<Point> points){
    }

    public void putBlock(List<Point> points){

    }
}
