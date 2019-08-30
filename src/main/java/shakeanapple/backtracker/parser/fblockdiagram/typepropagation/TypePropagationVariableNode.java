package shakeanapple.backtracker.parser.fblockdiagram.typepropagation;


import shakeanapple.backtracker.parser.fblockdiagram.model.ParsingVariableInfo;
import shakeanapple.backtracker.parser.fblockdiagram.model.ParsingVariableType;

public class TypePropagationVariableNode extends TypePropagationNode {

    private ParsingVariableInfo variable;

    public TypePropagationVariableNode(ParsingVariableInfo variable) {
        super();
        this.variable = variable;
    }

    @Override
    public void propagateType() {
        ParsingVariableType propagatingType = this.variable.getType();
        for (TypePropagationNode node : super.getConnectedVars().values()) {
            node.defineType(propagatingType)
                    .propagateType();
        }
    }

    @Override
    public TypePropagationNode defineType(ParsingVariableType type) {
        this.variable.defineType(type);
        return this;
    }
}
