package shakeanapple.backtracker.parser.basiccomponents.xmlmodel;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class Block {
    @JacksonXmlElementWrapper(localName = "inputs")
    @JacksonXmlProperty(localName = "input")
    private List<InputVariable> inputs;

    @JacksonXmlElementWrapper(localName = "outputs")
    @JacksonXmlProperty(localName = "output")
    private List<OutputVariable> outputs;

    @JacksonXmlElementWrapper(localName = "components")
    @JacksonXmlProperty(localName = "component")
    private List<BasicComponentAbstract> components;

    @JacksonXmlElementWrapper(localName = "connections")
    @JacksonXmlProperty(localName = "connection")
    private List<Connection> connections;

    @JacksonXmlProperty(isAttribute=true)
    private String name;

    public Block() {
    }

    public Block(String name, List<InputVariable> inputs, List<OutputVariable> outputs, List<BasicComponentAbstract> components, List<Connection> connections) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.components = components;
        this.connections = connections;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InputVariable> getInputs() {
        return this.inputs;
    }

    public void setInputs(List<InputVariable> inputs) {
        this.inputs = inputs;
    }

    public List<OutputVariable> getOutputs() {
        return this.outputs;
    }

    public void setOutputs(List<OutputVariable> outputs) {
        this.outputs = outputs;
    }

    public List<BasicComponentAbstract> getComponents() {
        return this.components;
    }

    public void setComponents(List<BasicComponentAbstract> components) {
        this.components = components;
    }

    public List<Connection> getConnections() {
        return this.connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }
}
