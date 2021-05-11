package shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.layout;

import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.layout.ConnectionLineInterval;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.layout.DoubleInterval;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.layout.LineType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DirectedLinesMap {
    private Map<Double, List<DoubleInterval>> occupiedIntervals = new HashMap<>();
    private boolean isVerticals;

    private double padding;

    public DirectedLinesMap(boolean isVerticals, double padding) {
        this.isVerticals = isVerticals;
        this.padding = padding;
    }

    public boolean overlapExists(ConnectionLineInterval connectionInterval){
        Double stableCoord = connectionInterval.getType() == LineType.VERTICAL ? connectionInterval.getStart().getX() : connectionInterval.getStart().getY();
        List<Double> keysToCheck = this.occupiedIntervals.keySet().stream().filter(key -> Math.abs(key - stableCoord) < this.padding).collect(Collectors.toList());
        DoubleInterval interval = connectionInterval.getType() == LineType.VERTICAL ? connectionInterval.getVerticalInterval() : connectionInterval.getHorizontalInterval();

        for (Double key: keysToCheck){
            for (DoubleInterval intervalToCheck : this.occupiedIntervals.get(key)) {
                if (intervalToCheck.overlaps(interval) && intervalToCheck.overlappingLength(interval) > 0) {
                    return true;
                }
            }
        }
//        if (this.isVerticals){
//            DoubleInterval verticalConnectionInterval = connectionInterval.getVerticalInterval().addPadding(this.padding);
//            if (this.occupiedIntervals.containsKey(connectionInterval.getStart().getX())) {
//                for (DoubleInterval interval : this.occupiedIntervals.get(connectionInterval.getStart().getX())) {
//                    if (verticalConnectionInterval.intersects(interval)) {
//                        return true;
//                    }
//                }
//            }
//        } else{
//            DoubleInterval horizontalConnectionInterval = connectionInterval.getHorizontalInterval().addPadding(this.padding);
//            if (this.occupiedIntervals.containsKey(connectionInterval.getStart().getY())) {
//                for (DoubleInterval interval : this.occupiedIntervals.get(connectionInterval.getStart().getY())) {
//                    if (horizontalConnectionInterval.intersects(interval)) {
//                        return true;
//                    }
//                }
//            }
//        }
        return false;
    }

    public void add(ConnectionLineInterval lineInterval){
        if (this.isVerticals && lineInterval.getType() == LineType.VERTICAL){
            if (!this.occupiedIntervals.containsKey(lineInterval.getStart().getX())){
                this.occupiedIntervals.put(lineInterval.getStart().getX(), new ArrayList<>());
            }
            this.occupiedIntervals.get(lineInterval.getStart().getX()).add(lineInterval.getVerticalInterval());
        } else if (!this.isVerticals && lineInterval.getType() == LineType.HORIZONTAL){
            if (!this.occupiedIntervals.containsKey(lineInterval.getStart().getY())){
                this.occupiedIntervals.put(lineInterval.getStart().getY(), new ArrayList<>());
            }
            this.occupiedIntervals.get(lineInterval.getStart().getY()).add(lineInterval.getHorizontalInterval());
        } else{
            throw new RuntimeException("Don't know where to put the thing");
        }
    }

    public DoubleInterval getOverlappedInterval(ConnectionLineInterval connInterval) {
        Double stableCoord = connInterval.getType() == LineType.VERTICAL ? connInterval.getStart().getX() : connInterval.getStart().getY();
        List<Double> keysToCheck = this.occupiedIntervals.keySet().stream().filter(key -> Math.abs(key - stableCoord) < this.padding).collect(Collectors.toList());
        DoubleInterval interval = connInterval.getType() == LineType.VERTICAL ? connInterval.getVerticalInterval() : connInterval.getHorizontalInterval();

        for (Double key: keysToCheck){
            for (DoubleInterval intervalToCheck : this.occupiedIntervals.get(key)) {
                if (intervalToCheck.overlaps(interval) && intervalToCheck.overlappingLength(interval) > 0) {
                    return intervalToCheck;
                }
            }
        }

//        if (this.isVerticals){
//            DoubleInterval verticalConnectionInterval = connInterval.getVerticalInterval().addPadding(this.padding);
//            for (DoubleInterval interval: this.occupiedIntervals.get(connInterval.getStart().getX())){
//                if (verticalConnectionInterval.intersects(interval)){
//                    return interval;
//                }
//            }
//        } else{
//            DoubleInterval horizontalConnectionInterval = connInterval.getHorizontalInterval().addPadding(this.padding);
//            for (DoubleInterval interval: this.occupiedIntervals.get(connInterval.getStart().getY())){
//                if (horizontalConnectionInterval.intersects(interval)){
//                    return interval;
//                }
//            }
//        }
        return null;

    }
}
