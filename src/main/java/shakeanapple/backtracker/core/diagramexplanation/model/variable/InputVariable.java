package shakeanapple.backtracker.core.diagramexplanation.model.variable;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.Connection;
import shakeanapple.backtracker.core.diagramexplanation.model.DiagramElement;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;

public class InputVariable<TVal extends ValueHolder> extends FBVariable<TVal> {

    private Connection<TVal> incomingConnection;
    private int order;

    public InputVariable(DynamicVariable<TVal> variable, int order) {
        super(variable);
        this.order = order;
    }

    public int getOrder() {
        return this.order;
    }

    public void connect(OutputVariable fromVar, DiagramElement from, DiagramElement to, boolean isInverted){
        this.incomingConnection = new Connection<TVal>(isInverted, from, fromVar, to, this);
    }

    public Connection<TVal> getIncomingConnection() {
        return this.incomingConnection;
    }
}
