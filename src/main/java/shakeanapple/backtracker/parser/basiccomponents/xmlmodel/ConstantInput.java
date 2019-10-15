package shakeanapple.backtracker.parser.basiccomponents.xmlmodel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ConstantInput extends InputVariable {
    @JacksonXmlProperty(isAttribute=true)
    private String value;

    public ConstantInput() {
    }

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
}
