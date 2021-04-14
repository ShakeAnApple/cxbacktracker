package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.BlockVariableHistoryItem;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BasicBlocksIdGenerator;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.InputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.changecausetree.ChangeCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class AndFunctionBlockBasic extends FunctionBlockBasic {

    private final OutputVariable result;

    public AndFunctionBlockBasic(boolean generateId, List<InputVariable> inputs, OutputVariable res, String pathInSystem) {
        super("And" + (generateId ? BasicBlocksIdGenerator.next("And") : ""), inputs, new ArrayList<OutputVariable>() {{
            add(res);
        }}, pathInSystem);

        this.result = res;
    }

    private AndFunctionBlockBasic(String name, List<InputVariable> inputs, OutputVariable res, String pathInSystem) {
        super(name, inputs, new ArrayList<OutputVariable>() {{
            add(res);
        }},pathInSystem);

        this.result = res;
    }

    @Override
    public void executeImpl() {
        boolean res = true;
        for (InputVariable in : super.getInputs()){
            res = res && ((BooleanValueHolder)in.getValue()).getValue();
            if (!res)
                break;
        }
        super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(
                new BooleanValueHolder(res)
        );
    }

    @Override
    public FunctionBlockBase clone() {
        return new AndFunctionBlockBasic(this.getName(), this.getInputs().stream().map(InputVariable::clone).collect(Collectors.toList()), this.getOutputs().get(0).clone(),this.getStringPathInSystem());
    }

    @Override
    protected List<CauseNode> explainBasicImpl(OutputGate output, Integer timestamp) {
        BooleanValueHolder val = (BooleanValueHolder) this.history().getVariableValueForStep(output.getName(), timestamp);
        if (val.getValue()){
            return this.explainTrue(timestamp);
        } else{
            return this.explainFalse(timestamp);
        }
    }

    private List<CauseNode> explainTrue(int timestamp){
        return super.fbInterface().getInputs().values().stream()
                .map(in -> new CauseNode(in, super.history().getVariableValueForStep(in.getName(), timestamp), timestamp))
                .collect(Collectors.toList());
    }

    private List<CauseNode> explainFalse(int timestamp){
        List<CauseNode> causeNodes = new ArrayList<>();
        for (InputGate in : super.fbInterface().getInputs().values()){
            BooleanValueHolder val = (BooleanValueHolder) super.history().getVariableValueForStep(in.getName(), timestamp);
            if (!val.getValue()){
                causeNodes.add(new CauseNode(in, val, timestamp));
            }
        }
        return causeNodes;
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
