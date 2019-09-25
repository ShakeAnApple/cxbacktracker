package shakeanapple.backtracker.parser.basiccomponents.xmlmodel;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "kind")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConstantInput.class, name = "constant"),
        @JsonSubTypes.Type(value = InputVariable.class, name = "variable")}
    )
public class InputVariable {
    @JacksonXmlProperty(isAttribute = true)
    private long id;
    @JacksonXmlProperty(isAttribute = true)
    private VarType type;
    @JacksonXmlProperty(isAttribute = true)
    private String name;

    public InputVariable() {
    }

    public InputVariable(long id, VarType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
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
}
