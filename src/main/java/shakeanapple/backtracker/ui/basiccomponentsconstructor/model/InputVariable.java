package shakeanapple.backtracker.ui.basiccomponentsconstructor.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class InputVariable {
    private long id;
    private VarType type;
    private String name;

    public InputVariable(long id, VarType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
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

    @Override
    public String toString() {
        return String.format("%s; %s; %s", this.id, this.name, this.type);
    }

    public shakeanapple.backtracker.parser.basiccomponents.xmlmodel.InputVariable translate() {
        return new shakeanapple.backtracker.parser.basiccomponents.xmlmodel.InputVariable(this.id, shakeanapple.backtracker.parser.basiccomponents.xmlmodel.VarType.valueOf(this.type.name()), this.name);
    }
}
