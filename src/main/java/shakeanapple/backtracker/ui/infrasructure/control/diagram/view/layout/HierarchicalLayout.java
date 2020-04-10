package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.layout;

import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.Canvas;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.DiagramStyles;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.DiagramCellView;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.InputInterfaceCellView;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.NodeView;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.OutputInterfaceCellView;

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
            if (cell instanceof OutputInterfaceCellView) {
                outputCells.add(cell);
            } else if (cell instanceof InputInterfaceCellView) {
                inputCells.add(cell);
            } else {
                blockCells.add(cell);
            }
        }

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
        prefLevelDistance = Math.max(prefLevelDistance, DiagramStyles.DIAGRAM_BLOCK_PADDING);

        double curX = 0;
        for (int i = 0; i <= maxLevel; i++) {
            List<DiagramCellView> levelCells = hierarchyLevels.get(i);
            int c = 0;
            double cellMaxWidth = 0;
            double curY = 50;
            for (DiagramCellView cell: levelCells){
                cell.getView().relocate(curX, curY);
                cellMaxWidth = Math.max(cellMaxWidth, cell.getWidth());
                curY += (30 + cell.getHeight());
            }
            curX += (prefLevelDistance + cellMaxWidth);

//            for (double y = height / (levelCells.size() + 1); y < height && c <levelCells.size(); y += height / (levelCells.size() + 1)) {
//                DiagramCellView cell = levelCells.get(c);
//                cell.getView().relocate(curX, y);
//                cellMaxWidth = Math.max(cellMaxWidth, cell.getWidth());
////                Offset offset = this.panel.adjustCoords(cell);
////                cell.relocate(curX + offset.left, y + offset.top);
//                c++;
//            }
//            curX += (prefLevelDistance + cellMaxWidth);
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
            } else if ((cellLevels.get(((NodeView)parentCell).getViewId()) == Integer.MIN_VALUE) || cellLevels.get(((NodeView)parentCell).getViewId()) == 0) {
                cellLevels.replace(((NodeView)parentCell).getViewId(), parentLevel);
            }
            maxParentLevel = Math.max(parentLevel, maxParentLevel);
        }
        return maxParentLevel + 1;
    }
}
