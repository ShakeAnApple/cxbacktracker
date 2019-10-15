package shakeanapple.backtracker.core.diagramexplanation.model.variable;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.Connection;
import shakeanapple.backtracker.core.diagramexplanation.model.DiagramElement;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;

import java.util.ArrayList;
import java.util.List;

public class OutputVariable<TVal extends ValueHolder> extends FBVariable<TVal> {

    private List<Connection<TVal>> outgoingConnections;

    private ValueHolder defaultValue;

    public OutputVariable(DynamicVariable<TVal> variable) {
        super(variable);
        this.outgoingConnections = new ArrayList<>();
    }

    public OutputVariable(DynamicVariable<TVal> variable, ValueHolder defaultValue) {
        this(variable);
        this.defaultValue = defaultValue;
    }

    public void assignValue(TVal value){
        super.setValue(value);
        for (Connection connection: outgoingConnections) {
            if (connection.isInverted()){
                value.invert();
            }
            connection.toVar().setValue(value);
        }
    }

    public ValueHolder getDefaultValue() {
        return this.defaultValue;
    }

    public List<Connection<TVal>> getOutgoingConnections() {
        return this.outgoingConnections;
    }

    public void connect(InputVariable toVar, DiagramElement from, DiagramElement to, boolean isInverted){
        this.outgoingConnections.add(new Connection<TVal>(isInverted, from, this, to, toVar));
    }

}
