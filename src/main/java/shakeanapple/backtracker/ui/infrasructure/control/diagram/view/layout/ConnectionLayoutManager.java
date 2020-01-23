package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.layout;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Path;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.DiagramStyles;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.ConnectionView;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.GraphView;

import java.util.*;

public class ConnectionLayoutManager {

    private DirectedLinesMap verticals;
    private DirectedLinesMap horizontals;

    GraphView graphView;

    private double padding;

    Map<String, Integer> blockOutputsCount = new HashMap<>();

    public ConnectionLayoutManager(GraphView graphView, double padding) {
        this.verticals = new DirectedLinesMap(true, padding);
        this.horizontals = new DirectedLinesMap(false, padding);

        this.graphView = graphView;
        this.padding = padding;
    }

//    public void draw(ConnectionView connection) {
//        double fromX = connection.getStartX().doubleValue();
//        double fromY = connection.getStartY().doubleValue();
//
//        double toX = connection.getEndX().doubleValue();
//        double toY = connection.getEndY().doubleValue();
//
//        List<ConnectionLineInterval> connectionLines = this.inferConnectionLines(fromX, fromY, toX, toY);
//        List<Integer> linesToFixNumbers;
//        do {
//            linesToFixNumbers = new ArrayList<>();
//            for (int i = 0; i < connectionLines.size(); i++) {
//                ConnectionLineInterval conLine = connectionLines.get(i);
//                if (conLine.getType() == LineType.VERTICAL && this.verticals.overlapExists(conLine)
//                        || conLine.getType() == LineType.HORIZONTAL && this.horizontals.overlapExists(conLine)) {
//                    linesToFixNumbers.add(i);
//                }
//            }
//            if (linesToFixNumbers.size() > 0){
//                connectionLines = this.fixLines(connectionLines, linesToFixNumbers);
//            }
//        } while (linesToFixNumbers.size() > 0);
//
//        for (ConnectionLineInterval conn : connectionLines){
//            if (conn.getType() == LineType.HORIZONTAL){
//                this.horizontals.add(conn);
//            } else{
//                this.verticals.add(conn);
//            }
//        }
//
//        connection.setView(connectionLines);
//    }

    public void draw(ConnectionView connection) {
        String fromBlockName = connection.getFrom().getOwner().getName();
        if (!this.blockOutputsCount.containsKey(fromBlockName)){
            this.blockOutputsCount.put(fromBlockName, 1);
        }

        int blockOutputsCount = this.blockOutputsCount.get(fromBlockName);
        double fromX = connection.getStartX().doubleValue();
        double fromY = connection.getStartY().doubleValue();

        double toX = connection.getEndX().doubleValue();
        double toY = connection.getEndY().doubleValue();

        List<ConnectionLineInterval> connectionLines = this.inferConnectionLines(fromX, fromY, toX, toY, blockOutputsCount);
//        List<Integer> linesToFixNumbers;
//        do {
//            linesToFixNumbers = new ArrayList<>();
//            for (int i = 0; i < connectionLines.size(); i++) {
//                ConnectionLineInterval conLine = connectionLines.get(i);
//                if (conLine.getType() == LineType.VERTICAL && this.verticals.overlapExists(conLine)
//                        || conLine.getType() == LineType.HORIZONTAL && this.horizontals.overlapExists(conLine)) {
//                    linesToFixNumbers.add(i);
//                }
//            }
//            if (linesToFixNumbers.size() > 0){
//                connectionLines = this.fixLines(connectionLines, linesToFixNumbers);
//            }
//        } while (linesToFixNumbers.size() > 0);
//
//        for (ConnectionLineInterval conn : connectionLines){
//            if (conn.getType() == LineType.HORIZONTAL){
//                this.horizontals.add(conn);
//            } else{
//                this.verticals.add(conn);
//            }
//        }
        this.blockOutputsCount.replace(fromBlockName, this.blockOutputsCount.get(fromBlockName) + 1);
        connection.setView(connectionLines);
    }

    private List<ConnectionLineInterval> fixLines(List<ConnectionLineInterval> connectionLines, List<Integer> linesToFixNumbers) {
        int shift = 0;
        List<ConnectionLineInterval> res = new ArrayList<>();
        boolean nextNeedsFix = false;
        for (int i = 0; i < connectionLines.size(); i++) {
            ConnectionLineInterval connInterv = connectionLines.get(i);
            if (nextNeedsFix){
                connInterv = new ConnectionLineInterval(
                        new Point(connInterv.getStart().getX() + padding, connInterv.getStart().getY()),
                        new Point(connInterv.getEnd().getX(), connInterv.getEnd().getY())
                );
                nextNeedsFix = false;
            }
            if (linesToFixNumbers.contains(i)) {
                if (connInterv.getType() == LineType.HORIZONTAL) {
                    DoubleInterval overlappedInterval = this.horizontals.getOverlappedInterval(connInterv);
                    // means that vertical fix fixed the horiz line
                    if (overlappedInterval == null){
                        res.add(connInterv);
                        continue;
                    }
                    if (i > 0){
                        ConnectionLineInterval prevLine = res.get(i + shift - 1);
                        res.set(i + shift - 1, new ConnectionLineInterval(
                                new Point(prevLine.getStart().getX(), prevLine.getStart().getY()),
                                new Point(prevLine.getEnd().getX(), prevLine.getEnd().getY() + padding)
                        ));
                    } else {
                        ConnectionLineInterval prevLine = new ConnectionLineInterval(
                                new Point(connInterv.getStart().getX(), connInterv.getStart().getY()),
                                new Point(connInterv.getStart().getX(), connInterv.getStart().getY() + padding)
                        );
                        res.add(0, prevLine);
                        shift++;
                    }

                    ConnectionLineInterval newHorizontal = new ConnectionLineInterval(
                            new Point(connInterv.getStart().getX(), connInterv.getStart().getY() + padding),
                            new Point((overlappedInterval.getTo() < connInterv.getEnd().getX() ? overlappedInterval.getTo() : connInterv.getEnd().getX()), connInterv.getStart().getY() + padding)
                    );
                    res.add(newHorizontal);
                    ConnectionLineInterval newVertical = new ConnectionLineInterval(
                            new Point(newHorizontal.getEnd().getX(), newHorizontal.getEnd().getY()),
                            new Point(newHorizontal.getEnd().getX(), newHorizontal.getEnd().getY() - padding)
                    );
                    res.add(newVertical);
                    shift++;
                    ConnectionLineInterval lastHoriz = new ConnectionLineInterval(
                            new Point(newVertical.getEnd().getX(), newVertical.getEnd().getY()),
                            new Point(connInterv.getEnd().getX(), connInterv.getEnd().getY())
                    );
                    res.add(lastHoriz);
                    shift++;
                } else {
                    if (i > 0) {
                        ConnectionLineInterval prevLine = res.get(i + shift - 1);
                        res.set(i + shift - 1, new ConnectionLineInterval(
                                new Point(prevLine.getStart().getX(), prevLine.getStart().getY()),
                                new Point(prevLine.getEnd().getX() + padding, prevLine.getEnd().getY())
                        ));
                    } else {
                        ConnectionLineInterval prevLine = new ConnectionLineInterval(
                                new Point(connInterv.getStart().getX(), connInterv.getStart().getY()),
                                new Point(connInterv.getStart().getX() + padding, connInterv.getStart().getY())
                        );
                        res.add(0, prevLine);
                        shift++;
                    }
                    res.add(new ConnectionLineInterval(
                            new Point(connInterv.getStart().getX() + padding, connInterv.getStart().getY()),
                            new Point(connInterv.getEnd().getX() + padding, connInterv.getEnd().getY())
                    ));
                    nextNeedsFix = true;
                }
            } else {
                res.add(connInterv);
            }
        }
        return res;
    }


//    private List<ConnectionLineInterval> inferConnectionLines(double fromX, double fromY, double toX, double toY) {
//        List<ConnectionLineInterval> res = new ArrayList<>();
//        ConnectionLineInterval vert = new ConnectionLineInterval(new Point(fromX, fromY), new Point(fromX, toY));
//        ConnectionLineInterval hor = new ConnectionLineInterval(new Point(fromX, toY), new Point(toX, toY));
//        res.add(vert);
//        res.add(hor);
//        return res;
//    }

    private List<ConnectionLineInterval> inferConnectionLines(double fromX, double fromY, double toX, double toY, int blockOutputsCount) {
        List<ConnectionLineInterval> res = new ArrayList<>();
        ConnectionLineInterval prefix = new ConnectionLineInterval(new Point(fromX, fromY), new Point(fromX + blockOutputsCount*this.padding, fromY));
        ConnectionLineInterval vert = new ConnectionLineInterval(prefix.getEnd(), new Point(fromX + blockOutputsCount*this.padding, toY));
        ConnectionLineInterval hor = new ConnectionLineInterval(new Point(fromX + blockOutputsCount*this.padding, toY), new Point(toX, toY));
        res.add(prefix);
        res.add(vert);
        res.add(hor);
        return res;
    }

}
