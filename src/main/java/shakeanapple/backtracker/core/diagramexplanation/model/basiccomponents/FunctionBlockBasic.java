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

    protected FunctionBlockBasic(String name, List<InputVariable> inputs, List<OutputVariable> outputs) {
        super(name + BasicBlocksIdGenerator.next(name), "BASIC", inputs, outputs);
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public static FunctionBlockBasic instance(ComponentDefinitionType type, List<InputVariable> inputs, List<OutputVariable> outputs) {
        switch (type) {
            case AND:
                return new AndFunctionBlockBasic(inputs, outputs.get(0));
            case OR:
                return new OrFunctionBlockBasic(inputs, outputs.get(0));
            case ASSIGN:
                return new AssignFunctionBlockBasic(inputs.get(0), outputs.get(0));
            case MINUS:
                return new MinusFunctionBlockBasic(inputs.get(0), inputs.get(1), outputs.get(0));
            case PLUS:
                return new PlusFunctionBlockBasic(inputs.get(0), inputs.get(1), outputs.get(0));
            case MUL:
                return new MulFunctionBlockBasic(inputs.get(0), inputs.get(1), outputs.get(0));
            case DIV:
                return new DivFunctionBlockBasic(inputs.get(0), inputs.get(1), outputs.get(0));
            case MOD:
                return new ModFunctionBlockBasic(inputs.get(0), inputs.get(1), outputs.get(0));
            case EQ:
                return new EqFunctionBlockBasic(inputs.get(0), inputs.get(1), outputs.get(0));
            case GREATER:
                return new GreaterFunctionBlockBasic(inputs.get(0), inputs.get(1), outputs.get(0));
            case GREATER_EQ:
                return new GreaterEqFunctionBlockBasic(inputs.get(0), inputs.get(1), outputs.get(0));
            case LESS:
                return new LessFunctionBlockBasic(inputs.get(0), inputs.get(1), outputs.get(0));
            case LESS_EQ:
                return new LessEqFunctionBlockBasic(inputs.get(0), inputs.get(1), outputs.get(0));
            case COUNT:
                return new CountFunctionBlockBasic(inputs, outputs.get(0));
            default:
                throw new RuntimeException("Not implemented block type " + type);
        }
    }

    public static FunctionBlockBasic choiceInstance(List<Choice> choices, OutputVariable output){
        return new ChoiceFunctionBlockBasic(choices, output);
    }

    public static FunctionBlockBasic delayInstance(InputVariable input, InputVariable defValue, OutputVariable output, int delay){
        if (defValue == null){
            return new DelayFunctionBlockBasic(input, output, delay);
        }
        return new DelayFunctionBlockBasic(input, defValue, output, delay);
    }

    public abstract void executeImpl();

    public List<InputVariable> getInputs(){
        return this.inputs;
    }

    public List<OutputVariable> getOutputs(){
        return this.outputs;
    }

    protected abstract List<CauseNode> explainBasicImpl(OutputGate output, Integer timestamp);

    @Override
    public ExplanationItem explainImpl(OutputGate output, Integer timestamp){
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
