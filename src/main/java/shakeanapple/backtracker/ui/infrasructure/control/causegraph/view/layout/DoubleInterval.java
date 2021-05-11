package shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.layout;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DoubleInterval {
    private double from;
    private double to;

    public DoubleInterval(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return this.from;
    }

    public double getTo() {
        return this.to;
    }

    public DoubleInterval addPadding(double padding){
        return this.from > this.to ? new DoubleInterval(this.from + padding, this.to - padding) :
                new DoubleInterval(this.from - padding, this.to + padding);
    }

    public double length(){
        return Math.abs(this.to - this.from);
    }

    public boolean overlaps(DoubleInterval other){
        return other.from >= this.from && other.from <= this.to ||
                other.to >= this.from && other.to <= this.to  ||
                this.from >= other.from && this.from <= other.to;

    }

    public double overlappingLength(DoubleInterval other) {
        List<Double> endPoints = Arrays.asList(this.from, this.to, other.from, other.to);
        endPoints.sort(Comparator.comparing(d -> d));
        // since we know that they intersect, we can do this
        return Math.abs(endPoints.get(1) - endPoints.get(2));
    }
}
