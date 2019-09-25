package shakeanapple.backtracker.parser.basiccomponents.xmlmodel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Connection {
    @JacksonXmlProperty(isAttribute=true)
    private long fromVarId;
    @JacksonXmlProperty(isAttribute=true)
    private long toVarId;

    @JacksonXmlProperty(isAttribute=true)
    private String isInverted;

    public Connection() {
    }

    public Connection(long fromVarId, long toVarId) {
        this.fromVarId = fromVarId;
        this.toVarId = toVarId;
    }

    public Connection(long fromVarId, long toVarId, String isInverted) {
        this(fromVarId, toVarId);
        this.isInverted = isInverted;
    }

    public long getFromVarId() {
        return this.fromVarId;
    }

    public void setFromVarId(long fromVarId) {
        this.fromVarId = fromVarId;
    }

    public long getToVarId() {
        return this.toVarId;
    }

    public void setToVarId(long toVarId) {
        this.toVarId = toVarId;
    }

    public String isInverted() {
        return this.isInverted;
    }

    public void setInverted(String inverted) {
        this.isInverted = inverted;
    }
}
