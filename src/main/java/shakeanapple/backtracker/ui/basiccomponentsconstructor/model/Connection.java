package shakeanapple.backtracker.ui.basiccomponentsconstructor.model;

public class Connection {
    private final String color;
    private final long fromId;
    private final long toId;

    private boolean isInverted;

    public Connection(String color, long fromId, long toId, boolean isInverted) {
        this.color = color;
        this.fromId = fromId;
        this.toId = toId;
        this.isInverted = isInverted;
    }

    public Connection(String color, long fromId, long toId) {
        this.color = color;
        this.fromId = fromId;
        this.toId = toId;
        this.isInverted = false;
    }

    public String getColor() {
        return this.color.toLowerCase();
    }

    public long getFromId() {
        return this.fromId;
    }

    public long getToId() {
        return this.toId;
    }

    public boolean isInverted() {
        return this.isInverted;
    }

    public void isInverted(boolean inverted) {
        this.isInverted = inverted;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public shakeanapple.backtracker.parser.basiccomponents.xmlmodel.Connection translate() {
        return new shakeanapple.backtracker.parser.basiccomponents.xmlmodel.Connection(this.fromId, this.toId, this.isInverted ? "true" : "false");
    }
}
