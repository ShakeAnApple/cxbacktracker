package shakeanapple.backtracker.ui.basiccomponentsconstructor.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public abstract class BasicComponentAbstract {
    private ComponentType type;
    private long id;

    public BasicComponentAbstract(ComponentType type, long id) {
        this.type = type;
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public ComponentType getType() {
        return this.type;
    }

    public abstract shakeanapple.backtracker.parser.basiccomponents.xmlmodel.BasicComponentAbstract translate();
}
