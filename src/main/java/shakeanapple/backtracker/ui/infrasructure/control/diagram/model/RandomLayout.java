package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;

import java.util.List;
import java.util.Random;

public class RandomLayout extends Layout {

    Panel panel;

    Random rnd = new Random();

    public RandomLayout(Panel panel) {

        this.panel = panel;

    }

    public void execute() {

        List<Cell> cells = panel.getGraph().getAllCells();

        for (Cell cell : cells) {

            double x = rnd.nextDouble() * 500;
            double y = rnd.nextDouble() * 500;

            cell.relocate(x, y);

        }

    }

}
