package shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.tree;

import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.ILtlFormulaVisitor;

import java.util.ArrayList;
import java.util.List;

public class IntNode extends FormulaNode{
    private int value;

    public IntNode(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor) {
        return visitor.visitInt(this);
    }

    @Override
    public List<FormulaNode> getChildren() {
        return new ArrayList<>();
    }

    @Override
    public String getName() {
        return String.valueOf(this.value);
    }
}
