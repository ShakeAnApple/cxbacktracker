package shakeanapple.backtracker.parser.basiccomponents.xmlmodel;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "kind")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChoiceComponent.class, name = "choice"),
        @JsonSubTypes.Type(value = BasicComponent.class, name = "basic")})
public abstract class BasicComponentAbstract {
    @JacksonXmlProperty(isAttribute = true)
    private ComponentType type;

    public BasicComponentAbstract() {
    }

    public BasicComponentAbstract(ComponentType type) {
        this.type = type;
    }

    public ComponentType getType() {
        return this.type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }
}
