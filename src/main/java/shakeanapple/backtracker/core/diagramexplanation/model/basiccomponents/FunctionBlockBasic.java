package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.arithmetic.*;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice.Choice;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice.ChoiceFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic.*;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.ComponentDefinitionType;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.List;
import java.util.stream.Collectors;

public abstract class FunctionBlockBasic extends FunctionBlockBase {
    private final List<InputVariable> inputs;
    private final List<OutputVariable> outputs;

    protected FunctionBlockBasic(String name, List<InputVariable> inputs, List<OutputVariable> outputs, String pathInSystem) {
        super(name, "BASIC", inputs, outputs, pathInSystem);
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public static FunctionBlockBasic instance(ComponentDefinitionType type, List<InputVariable> inputs, List<OutputVariable> outputs, String pathInSystem) {
        switch (type) {
            case AND:
                return new AndFunctionBlockBasic(true, inputs, outputs.get(0),pathInSystem);
            case OR:
                return new OrFunctionBlockBasic(true, inputs, outputs.get(0),pathInSystem);
            case ASSIGN:
                return new AssignFunctionBlockBasic(true, inputs.get(0), outputs.get(0),pathInSystem);
            case MINUS:
                return new MinusFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0),pathInSystem);
            case PLUS:
                return new PlusFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0),pathInSystem);
            case MUL:
                return new MulFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0),pathInSystem);
            case DIV:
                return new DivFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0),pathInSystem);
            case MOD:
                return new ModFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0),pathInSystem);
            case EQ:
                return new EqFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0),pathInSystem);
            case GREATER:
                return new GreaterFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0),pathInSystem);
            case GREATER_EQ:
                return new GreaterEqFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0),pathInSystem);
            case LESS:
                return new LessFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0),pathInSystem);
            case LESS_EQ:
                return new LessEqFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0),pathInSystem);
            case COUNT:
                return new CountFunctionBlockBasic(true, inputs, outputs.get(0),pathInSystem);
            default:
                throw new RuntimeException("Not implemented block type " + type);
        }
    }

    public static FunctionBlockBasic choiceInstance(List<Choice> choices, OutputVariable output, String pathInSystem) {
        return new ChoiceFunctionBlockBasic(true, choices, output,pathInSystem);
    }

    public static FunctionBlockBasic delayInstance(InputVariable input, InputVariable defValue, OutputVariable output, int delay, String pathInSystem) {
        if (defValue == null) {
            return new DelayFunctionBlockBasic(true, input, output, delay,pathInSystem);
        }
        return new DelayFunctionBlockBasic(true, input, defValue, output, delay,pathInSystem);
    }

    public abstract void executeImpl();

    public List<InputVariable> getInputs() {
        return this.inputs;
    }

    public List<OutputVariable> getOutputs() {
        return this.outputs;
    }

    protected abstract List<CauseNode> explainBasicImpl(OutputGate output, Integer timestamp);

    @Override
    public ExplanationItem explainImpl(OutputGate output, Integer timestamp) {
        CauseNode root = new CauseNode(output, output.getValue(), timestamp);
        //root.isRoot(true);
        CausePathTree pathTree = new CausePathTree(root);
        List<CauseNode> causes = this.explainBasicImpl(output, timestamp);
        root.addChildren(causes);
        ExplanationItem res = new ExplanationItem(pathTree, causes);
//        for (CauseNode cause: causes){
//            res.recordAddChildrenActionForNode(cause, ch -> { cause.addChildren(ch); return true;});
//        }
        return res;
    }

}