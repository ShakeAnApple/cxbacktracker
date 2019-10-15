package shakeanapple.backtracker.ui.basiccomponentsconstructor.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ConstantInput extends InputVariable {
    @JacksonXmlProperty(isAttribute=true)
    private String value;

    public ConstantInput(long id, VarType type, String name, String value, int order) {
        super(id, type, name, order);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s; %s; %s; %s", super.getId(), super.getName(), super.getType(), this.value);
    }

    @Override
    public shakeanapple.backtracker.parser.basiccomponents.xmlmodel.InputVariable translate() {
        return new shakeanapple.backtracker.parser.basiccomponents.xmlmodel.ConstantInput(super.getId(), shakeanapple.backtracker.parser.basiccomponents.xmlmodel.VarType.valueOf(super.getType().name()), super.getName(), this.value, super.getOrder());
    }
}
