package shakeanapple.backtracker.ui.explainer.model.graph;

import shakeanapple.backtracker.ui.explainer.model.graph.cell.InputVarCell;
import shakeanapple.backtracker.ui.explainer.model.graph.cell.OutputVarCell;
import shakeanapple.backtracker.ui.explainer.view.Offset;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.*;

import java.util.*;

public class HierarchicalLayout extends Layout {

    private final Panel panel;

    public HierarchicalLayout(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void execute() {
        List<Cell> cells = this.panel.getGraph().getAllCells();
        double width = this.panel.getWidth();
        double height = this.panel.getHeight() - 50;

        List<Cell> inputCells = new ArrayList<>();
        List<Cell> outputCells = new ArrayList<>();
        List<Cell> blockCells = new ArrayList<>();

        for (Cell cell : cells) {
            if (cell instanceof OutputVarCell) {
                outputCells.add(cell);
            } else if (cell instanceof InputVarCell) {
                inputCells.add(cell);
            } else {
                blockCells.add(cell);
            }
        }

        double minLevelsDIstanceX = 50;

        Map<Integer, List<Cell>> hierarchyLevels = new HashMap<>();
        hierarchyLevels.put(0, inputCells);

        Map<Long, Integer> cellLevels = new HashMap<>();

        for (Cell cell : inputCells) {
            cellLevels.put(cell.getCellId(), 0);
        }

        int maxLevel = 0;
        for (Cell cell : blockCells) {
            int cellLevel = this.defineLevels(cell, cellLevels);
            if (!cellLevels.containsKey(cell.getCellId())) {
                cellLevels.put(cell.getCellId(), cellLevel);
            }
            if (!hierarchyLevels.containsKey(cellLevel)) {
                hierarchyLevels.put(cellLevel, new ArrayList<>());
            }
            hierarchyLevels.get(cellLevel).add(cell);
            maxLevel = Math.max(cellLevel, maxLevel);
        }

        hierarchyLevels.put(++maxLevel, outputCells);
        double prefLevelDistance = width / maxLevel;
        prefLevelDistance = Math.max(prefLevelDistance, minLevelsDIstanceX);

        // TODO "adjust" feels uncomfortable
        double curX = prefLevelDistance;
        for (int i = 0; i <= maxLevel; i++) {
            List<Cell> levelCells = hierarchyLevels.get(i);
            int c = 0;
            for (double y = height / (levelCells.size() + 1); y < height && c <levelCells.size(); y += height / (levelCells.size() + 1)) {
                Cell cell = levelCells.get(c);
                cell.relocate(curX, y);
                Offset offset = this.panel.adjustCoords(cell);
                cell.relocate(curX + offset.left, y + offset.top);
                c++;
            }
            curX += prefLevelDistance;
        }
    }

    // TODO watch cycles!
    private Integer defineLevels(Cell cell, Map<Long, Integer> cellLevels) {
        if (cellLevels.containsKey(cell.getCellId())) {
            if (cellLevels.get(cell.getCellId()) == Integer.MIN_VALUE) {
                return 0;
            }
            return cellLevels.get(cell.getCellId());
        }
        cellLevels.put(cell.getCellId(), Integer.MIN_VALUE);
        int maxParentLevel = 0;
        for (Cell parentCell : cell.getCellParents()) {
            int parentLevel = this.defineLevels(parentCell, cellLevels);
            if (!cellLevels.containsKey(parentCell.getCellId())) {
                cellLevels.put(parentCell.getCellId(), parentLevel);
            } else if (cellLevels.get(parentCell.getCellId()) == Integer.MIN_VALUE) {
                cellLevels.replace(parentCell.getCellId(), parentLevel);
            }
            maxParentLevel = Math.max(parentLevel, maxParentLevel);
        }
        return maxParentLevel + 1;
    }
}
