package shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.layout;

import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.Canvas;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.DiagramStyles;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.graph.GraphNodeView;

import java.util.*;

public class HierarchicalLayout extends Layout {

    private final Canvas panel;

    public HierarchicalLayout(Canvas panel) {
        this.panel = panel;
    }

    @Override
    public void execute() {
        GraphNodeView root = this.panel.getGraph().getRoot();
        Map<Integer, Map<Integer, GraphNodeView>> hierarchyLevels = new HashMap<>();
        hierarchyLevels.put(0, new HashMap<>() {{
            put(0, root);
        }});
        Set<GraphNodeView> processedNodes = new HashSet<>() {{
            add(root);
        }};

        this.addChildren(hierarchyLevels, 0, processedNodes);
        Map.Entry<Integer, Map<Integer, GraphNodeView>> largestLevel = hierarchyLevels.entrySet().stream().max(Comparator.comparing(entry -> entry.getValue().values().size())).orElse(null);
        if (largestLevel == null) {
            throw new RuntimeException("Largest level is null");
        }

        double maxHeight = largestLevel.getValue().size() * DiagramStyles.DIAGRAM_HEIGHT_PADDING + largestLevel.getValue().values().stream().mapToDouble(GraphNodeView::getHeight).sum();

        // draw from left to right
        double curX = DiagramStyles.DIAGRAM_PADDING_LEFT;
        for (int levelNum = hierarchyLevels.values().size() - 1; levelNum >= 0; levelNum--) {
            Map<Integer, GraphNodeView> currentLevel = hierarchyLevels.get(levelNum);
            double spacePerNode = (maxHeight - currentLevel.values().size() * DiagramStyles.DIAGRAM_HEIGHT_PADDING - currentLevel.values().stream().mapToDouble(GraphNodeView::getHeight).sum()) / currentLevel.values().size();
            if (spacePerNode < 0){
                spacePerNode = 0;
            }

            double curY = spacePerNode / 2;
            for(int nodeNum = 0; nodeNum < currentLevel.size(); nodeNum++){
                GraphNodeView currentNode = currentLevel.get(nodeNum);
                currentNode.getView().relocate(curX, curY);
                curY += currentNode.getHeight() + DiagramStyles.DIAGRAM_HEIGHT_PADDING + spacePerNode;
            }

            double maxWidth = currentLevel.values().stream().map(GraphNodeView::getWidth).max(Comparator.comparing(n -> n)).get();
            curX += maxWidth + DiagramStyles.DIAGRAM_WIDTH_PADDING;
        }
    }

    // children should be displayed after the last parent! (so you need to move the existing node and its children forward)
    private void addChildren(Map<Integer, Map<Integer, GraphNodeView>> hierarchyLevels, int lastLevelNum, Set<GraphNodeView> processedNodes) {
        Map<Integer, GraphNodeView> lastLevelNodes = hierarchyLevels.get(lastLevelNum);
        Map<Integer, GraphNodeView> newLevelNodes = new HashMap<>();
        int nextNodeNum = -1;
        for (int i = 0; i < lastLevelNodes.values().size(); i++) {
            GraphNodeView parent = lastLevelNodes.get(i);
            for (GraphNodeView child : parent.getChildren()) {
                if (!processedNodes.contains(child)) {
                    newLevelNodes.put(++nextNodeNum, child);
                    processedNodes.add(child);
                }
            }
        }
        if (newLevelNodes.size() > 0) {
            hierarchyLevels.put(++lastLevelNum, newLevelNodes);
            this.addChildren(hierarchyLevels, lastLevelNum, processedNodes);
        }
    }
}
