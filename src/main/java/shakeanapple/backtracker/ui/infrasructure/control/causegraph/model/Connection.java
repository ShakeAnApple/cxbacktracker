package shakeanapple.backtracker.ui.infrasructure.control.causegraph.model;

public class Connection {
    private final GraphNode from;
    private final GraphNode to;

    public Connection(GraphNode from, GraphNode to) {
        this.from = from;
        this.to = to;
    }

    public GraphNode from() {
        return this.from;
    }

    public GraphNode to() {
        return this.to;
    }
}
