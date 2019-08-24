package shakeanapple.backtracker.common.graph;

public class Transition {
    private final Node from;
    private final Node to;
    private final String label;

    public Transition(Node from, Node to, String label) {
        this.from = from;
        this.to = to;
        this.label = label;
    }

    public Node getFrom() {
        return this.from;
    }

    public Node getTo() {
        return this.to;
    }

    public String getLabel() {
        return this.label;
    }
}
