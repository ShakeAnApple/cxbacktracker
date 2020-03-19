package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BasicBlocksIdGenerator;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BinOpFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.List;
import java.util.stream.Collectors;

public class LessEqFunctionBlockBasic extends BinOpFunctionBlockBasic {
    public LessEqFunctionBlockBasic(boolean generateId,InputVariable left, InputVariable right, OutputVariable res) {
        super("LessEq"+ (generateId ? BasicBlocksIdGenerator.next("LessEq") : ""), left, right, res);
    }

    public LessEqFunctionBlockBasic(String name, InputVariable left, InputVariable right, OutputVariable res) {
        super(name, left, right, res);
    }

    @Override
    public void executeImpl() {
        super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(
                new BooleanValueHolder(((IntegerValueHolder)super.getLeft().getValue()).getValue() <= ((IntegerValueHolder)super.getRight().getValue()).getValue())
        );
    }

    @Override
    public FunctionBlockBase clone() {
        return new LessEqFunctionBlockBasic(this.getName(), this.getLeft().clone(), this.getRight().clone(), this.getOutput().clone());
    }

    @Override
    protected List<CauseNode> explainBasicImpl(OutputGate output, Integer timestamp) {
        return super.fbInterface().getInputs().values().stream()
                .map(in -> new CauseNode(in, super.history().getVariableValueForStep(in.getName(), timestamp), timestamp))
                .collect(Collectors.toList());
    }
}

