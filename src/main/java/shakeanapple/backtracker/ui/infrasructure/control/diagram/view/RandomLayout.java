package shakeanapple.backtracker.ui.infrasructure.control.diagram.view;


import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.CellView;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.DiagramCellView;

import java.util.List;
import java.util.Random;

public class RandomLayout extends Layout {

    Canvas canvas;

    Random rnd = new Random();

    public RandomLayout(Canvas canvas) {

        this.canvas = canvas;

    }

    public void execute() {

        List<DiagramCellView> cells = canvas.getGraph().getNodes();

        for (DiagramCellView cell : cells) {

            double x = rnd.nextDouble() * 500;
            double y = rnd.nextDouble() * 500;

            cell.getView().relocate(x, y);

        }

    }

}
