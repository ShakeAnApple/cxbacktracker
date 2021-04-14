package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.arithmetic;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.BlockVariableHistoryItem;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BasicBlocksIdGenerator;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic.LessEqFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BinOpFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.changecausetree.ChangeCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlusFunctionBlockBasic extends BinOpFunctionBlockBasic {
    public PlusFunctionBlockBasic(boolean generateId,InputVariable<IntegerValueHolder> left, InputVariable<IntegerValueHolder> right, OutputVariable<IntegerValueHolder> output, String pathInSystem) {
        super("Plus"+ (generateId ? BasicBlocksIdGenerator.next("Plus") : ""), left, right, output,pathInSystem);
    }

    private PlusFunctionBlockBasic(String name, InputVariable<IntegerValueHolder> left, InputVariable<IntegerValueHolder> right, OutputVariable<IntegerValueHolder> output, String pathInSystem) {
        super(name, left, right, output,pathInSystem);
    }

    @Override
    public void executeImpl() {
        super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(
                new IntegerValueHolder(((IntegerValueHolder)super.getLeft().getValue()).getValue() + ((IntegerValueHolder)super.getRight().getValue()).getValue())
        );
    }

    @Override
    public FunctionBlockBase clone() {
        return new PlusFunctionBlockBasic(this.getName(), this.getLeft().clone(), this.getRight().clone(), this.getOutput().clone(),this.getStringPathInSystem());
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
