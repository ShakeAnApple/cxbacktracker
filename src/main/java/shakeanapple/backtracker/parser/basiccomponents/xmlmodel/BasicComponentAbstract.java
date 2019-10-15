package shakeanapple.backtracker.parser.basiccomponents.xmlmodel;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.BasicComponentDefinitionAbstract;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "kind")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChoiceComponent.class, name = "choice"),
        @JsonSubTypes.Type(value = DelayComponent.class, name = "delay"),
        @JsonSubTypes.Type(value = BasicComponent.class, name = "basic")})
public abstract class BasicComponentAbstract {
    @JacksonXmlProperty(isAttribute = true)
    private ComponentType type;

    @JacksonXmlProperty(isAttribute = true)
    private Long id;

    public BasicComponentAbstract() {
    }

    public BasicComponentAbstract(ComponentType type, long id) {
        this.type = type;
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    public ComponentType getType() {
        return this.type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public abstract BasicComponentDefinitionAbstract translate();
}
