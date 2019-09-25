package shakeanapple.backtracker.ui.basiccomponentsconstructor.model;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.Connection;

import java.util.List;

public class Block {
    private List<InputVariable> inputs;

    private List<OutputVariable> outputs;

    private List<BasicComponentAbstract> components;

    private List<Connection> connections;

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


    public List<InputVariable> getInputs() {
        return this.inputs;
    }


    public List<OutputVariable> getOutputs() {
        return this.outputs;
    }


    public List<BasicComponentAbstract> getComponents() {
        return this.components;
    }


    public List<Connection> getConnections() {
        return this.connections;
    }

}
