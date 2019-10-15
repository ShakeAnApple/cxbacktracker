package shakeanapple.backtracker.parser.fblockdiagramold.typepropagation;

import shakeanapple.backtracker.parser.fblockdiagramold.model.ParsingVariableType;

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
