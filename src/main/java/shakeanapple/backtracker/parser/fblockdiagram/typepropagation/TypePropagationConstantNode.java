package shakeanapple.backtracker.parser.fblockdiagram.typepropagation;

import shakeanapple.backtracker.parser.fblockdiagram.model.ParsingVariableType;

public class TypePropagationConstantNode extends TypePropagationNode {
    private ParsingVariableType type;

    public TypePropagationConstantNode(ParsingVariableType type) {
        this.type = type;
    }

    @Override
    public void propagateType() {
        for (TypePropagationNode node : super.getConnectedVars().values()) {
            node.defineType(this.type)
                    .propagateType();
        }
    }

    @Override
    public TypePropagationNode defineType(ParsingVariableType type) {
        this.type = type;
        return this;
    }
}
