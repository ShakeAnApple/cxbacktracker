package shakeanapple.backtracker.ui.basiccomponentsconstructor.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class InputVariable {
    private long id;
    private VarType type;
    private String name;
    private int order;

    public InputVariable(long id, VarType type, String name, int order) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.order = order;
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

    public int getOrder() {
        return this.order;
    }

    @Override
    public String toString() {
        return String.format("%s; %s; %s", this.id, this.name, this.type);
    }

    public shakeanapple.backtracker.parser.basiccomponents.xmlmodel.InputVariable translate() {
        return new shakeanapple.backtracker.parser.basiccomponents.xmlmodel.InputVariable(this.id, shakeanapple.backtracker.parser.basiccomponents.xmlmodel.VarType.valueOf(this.type.name()), this.name, this.order);
    }
}
