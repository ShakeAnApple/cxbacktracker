package shakeanapple.backtracker.ui.infrasructure.control.diagramold;


import javafx.scene.paint.Color;

public class Connection {
    private long fromId;
    private long toId;
    private String label;
    private Color color;

    public Connection(long fromId, long toId, String label, Color color) {
        this.fromId = fromId;
        this.toId = toId;
        this.label = label;
        this.color = color;
    }

    public long getFromId() {
        return this.fromId;
    }

    public long getToId() {
        return this.toId;
    }

    public String getLabel() {
        return this.label;
    }

    public Color getColor() {
        return this.color;
    }
}
