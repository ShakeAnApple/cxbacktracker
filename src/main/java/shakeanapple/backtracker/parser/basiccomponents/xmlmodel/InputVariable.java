package shakeanapple.backtracker.parser.basiccomponents.xmlmodel;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.ConstantInputDefinition;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.InputDefinition;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.VarDefinitionType;

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
    @JacksonXmlProperty(isAttribute = true)
    private int order;

    public InputVariable() {
    }

    public InputVariable(long id, VarType type, String name, int order) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.order = order;
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

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public InputDefinition translate() {
        if (this instanceof ConstantInput){
            return new ConstantInputDefinition(this.id, VarDefinitionType.valueOf(this.type.name()), this.name, ((ConstantInput)this).getValue(), this.order);
        } else {
            return new InputDefinition(this.id, VarDefinitionType.valueOf(this.type.name()), this.name, this.order);
        }
    }
}
