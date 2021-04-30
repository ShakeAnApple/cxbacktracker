package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BasicBlocksIdGenerator;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BinOpFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GreaterFunctionBlockBasic extends BinOpFunctionBlockBasic {
    public GreaterFunctionBlockBasic(boolean generateId, InputVariable left, InputVariable right, OutputVariable res, String pathInSystem) {
        super("Greater" + (generateId ? BasicBlocksIdGenerator.next("Greater") : ""), left, right, res,pathInSystem);
    }

    public GreaterFunctionBlockBasic(String name, InputVariable left, InputVariable right, OutputVariable res, String pathInSystem) {
        super(name, left, right, res,pathInSystem);
    }

    @Override
    public void executeImpl() {
        super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(
                new BooleanValueHolder(((IntegerValueHolder) super.getLeft().getValue()).getValue() > ((IntegerValueHolder) super.getRight().getValue()).getValue())
        );
    }

    @Override
    public FunctionBlockBase clone() {
        return new GreaterFunctionBlockBasic(this.getName(), this.getLeft().clone(), this.getRight().clone(), this.getOutput().clone(),this.getStringPathInSystem());
    }

    @Override
    protected List<CauseNode> explainBasicImpl(OutputGate output, Integer timestamp) {
        return super.fbInterface().getInputs().values().stream()
                .map(in -> new CauseNode(in, super.history().getVariableValueForStep(in.getName(), timestamp), timestamp))
                .collect(Collectors.toList());
    }

    @Override
    protected List<ChangeCauseNode> explainChangeBasicImpl(OutputGate output, Integer changeStep) {
        return this.fbInterface().getInputs().keySet().stream().map(name -> {
            ValueHolder changeValue = this.history().getVariableValueForStep(name, changeStep);
            ValueHolder nextValue = this.history().getVariableValueForStep(name, changeStep + 1);
            if (!changeValue.equals(nextValue)) {
                return new ChangeCauseNode(this.fbInterface().getInputs().get(name), nextValue, changeStep + 1, changeValue, changeStep);
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }
}

