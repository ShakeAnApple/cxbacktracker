package shakeanapple.backtracker.parser.basiccomponents.xmlmodel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class OutputVariable {
    @JacksonXmlProperty(isAttribute=true)
    private long id;
    @JacksonXmlProperty(isAttribute=true)
    private VarType type;
    @JacksonXmlProperty(isAttribute=true)
    private String name;
    @JacksonXmlProperty(isAttribute=true)
    private String defaultValue;

    public OutputVariable() {
    }

    public OutputVariable(long id, VarType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public OutputVariable(long id, VarType type, String name, String defaultValue) {
        this(id, type, name);
        this.defaultValue = defaultValue;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VarType getType() {
        return this.type;
    }

    public void setType(VarType type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
