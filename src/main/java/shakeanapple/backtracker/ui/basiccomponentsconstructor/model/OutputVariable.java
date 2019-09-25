package shakeanapple.backtracker.ui.basiccomponentsconstructor.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class OutputVariable {
    private long id;
    private VarType type;
    private String name;
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

    public VarType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    @Override
    public String toString() {
        return String.format("%s; %s; %s; %s", this.id, this.name, this.type, this.defaultValue);
    }

    public shakeanapple.backtracker.parser.basiccomponents.xmlmodel.OutputVariable translate() {
        return new shakeanapple.backtracker.parser.basiccomponents.xmlmodel.OutputVariable(this.id, shakeanapple.backtracker.parser.basiccomponents.xmlmodel.VarType.valueOf(this.type.name()), this.name, this.defaultValue);
    }
}
