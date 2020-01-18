package shakeanapple.backtracker.ui.infrasructure.control.diagram.view;

import shakeanapple.backtracker.ui.explainer.model.graph.cell.InputVarCell;
import shakeanapple.backtracker.ui.explainer.model.graph.cell.OutputVarCell;
import shakeanapple.backtracker.ui.explainer.view.Offset;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.DiagramCell;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.DiagramCellView;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.NodeView;
import shakeanapple.backtracker.ui.infrasructure.control.diagramold.model.Cell;
import shakeanapple.backtracker.ui.infrasructure.control.diagramold.model.Panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HierarchicalLayout extends Layout {

    private final Canvas panel;

    public HierarchicalLayout(Canvas panel) {
        this.panel = panel;
    }

    @Override
    public void execute() {
        List<DiagramCellView> cells = this.panel.getGraph().getNodes();
        double width = this.panel.getWidth();
        double height = this.panel.getHeight() - 50;

        List<DiagramCellView> inputCells = new ArrayList<>();
        List<DiagramCellView> outputCells = new ArrayList<>();
        List<DiagramCellView> blockCells = new ArrayList<>();

        for (DiagramCellView cell : cells) {
            if (cell instanceof OutputVarCell) {
                outputCells.add(cell);
            } else if (cell instanceof InputVarCell) {
                inputCells.add(cell);
            } else {
                blockCells.add(cell);
            }
        }

        double minLevelsDIstanceX = 50;

        Map<Integer, List<DiagramCellView>> hierarchyLevels = new HashMap<>();
        hierarchyLevels.put(0, inputCells);

        Map<Long, Integer> cellLevels = new HashMap<>();

        for (DiagramCellView cell : inputCells) {
            cellLevels.put(((NodeView)cell).getViewId(), 0);
        }

        int maxLevel = 0;
        for (DiagramCellView cell : blockCells) {
            int cellLevel = this.defineLevels(cell, cellLevels);
            if (!cellLevels.containsKey(((NodeView)cell).getViewId())) {
                cellLevels.put(((NodeView)cell).getViewId(), cellLevel);
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
            List<DiagramCellView> levelCells = hierarchyLevels.get(i);
            int c = 0;
            for (double y = height / (levelCells.size() + 1); y < height && c <levelCells.size(); y += height / (levelCells.size() + 1)) {
                DiagramCellView cell = levelCells.get(c);
                cell.getView().relocate(curX, y);
//                Offset offset = this.panel.adjustCoords(cell);
//                cell.relocate(curX + offset.left, y + offset.top);
                c++;
            }
            curX += prefLevelDistance;
        }
    }

    // TODO watch cycles!
    private Integer defineLevels(DiagramCellView cell, Map<Long, Integer> cellLevels) {
        if (cellLevels.containsKey(((NodeView)cell).getViewId())) {
            if (cellLevels.get(((NodeView)cell).getViewId()) == Integer.MIN_VALUE) {
                return 0;
            }
            return cellLevels.get(((NodeView)cell).getViewId());
        }
        cellLevels.put(((NodeView)cell).getViewId(), Integer.MIN_VALUE);
        int maxParentLevel = 0;
        for (DiagramCellView parentCell : cell.getParents()) {
            int parentLevel = this.defineLevels(parentCell, cellLevels);
            if (!cellLevels.containsKey(((NodeView)parentCell).getViewId())) {
                cellLevels.put(((NodeView)parentCell).getViewId(), parentLevel);
            } else if (cellLevels.get(((NodeView)parentCell).getViewId()) == Integer.MIN_VALUE) {
                cellLevels.replace(((NodeView)parentCell).getViewId(), parentLevel);
            }
            maxParentLevel = Math.max(parentLevel, maxParentLevel);
        }
        return maxParentLevel + 1;
    }
}
