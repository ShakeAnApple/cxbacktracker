package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.arithmetic;

import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BasicBlocksIdGenerator;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BinOpFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic.LessEqFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.List;
import java.util.stream.Collectors;

public class MulFunctionBlockBasic extends BinOpFunctionBlockBasic {
    public MulFunctionBlockBasic(boolean generateId,InputVariable<IntegerValueHolder> left, InputVariable<IntegerValueHolder> right, OutputVariable<IntegerValueHolder> output) {
        super("Mul"+ (generateId ? BasicBlocksIdGenerator.next("Mul") : ""), left, right, output);
    }

    private MulFunctionBlockBasic(String name, InputVariable<IntegerValueHolder> left, InputVariable<IntegerValueHolder> right, OutputVariable<IntegerValueHolder> output) {
        super(name, left, right, output);
    }

    @Override
    public void executeImpl() {
        super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(
                new IntegerValueHolder(((IntegerValueHolder)super.getLeft().getValue()).getValue() * ((IntegerValueHolder)super.getRight().getValue()).getValue())
        );
    }

    @Override
    public FunctionBlockBase clone() {
        return new MulFunctionBlockBasic(this.getName(), this.getLeft().clone(), this.getRight().clone(), this.getOutput().clone());
    }

    @Override
    protected List<CauseNode> explainBasicImpl(OutputGate output, Integer timestamp) {
        return super.fbInterface().getInputs().values().stream()
                .map(in -> new CauseNode(in, super.history().getVariableValueForStep(in.getName(), timestamp), timestamp))
                .collect(Collectors.toList());
    }
}
