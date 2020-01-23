package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.layout;

public class ConnectionLineInterval {
    private Point start;
    private Point end;

    private DoubleInterval verticalInterval;
    private DoubleInterval horizontalInterval;

    private LineType type;

    public ConnectionLineInterval(Point start, Point end) {
        this.start = start;
        this.end = end;

        this.verticalInterval = new DoubleInterval(start.getY(), end.getY());
        this.horizontalInterval = new DoubleInterval(start.getX(), end.getX());

        this.type = this.verticalInterval.length() == 0 ? LineType.HORIZONTAL : LineType.VERTICAL;
    }

    public Point getStart() {
        return this.start;
    }

    public Point getEnd() {
        return this.end;
    }

    public DoubleInterval getHorizontalInterval() {
        return this.horizontalInterval;
    }

    public LineType getType() {
        return this.type;
    }

    public DoubleInterval getVerticalInterval(){
        return this.verticalInterval;
    }
}
