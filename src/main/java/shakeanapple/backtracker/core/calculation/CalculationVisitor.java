package shakeanapple.backtracker.core.calculation;

import shakeanapple.backtracker.core.model.ltlformula.model.ILtlFormulaVisitor;
import shakeanapple.backtracker.core.model.ltlformula.model.LtlFormula;
import shakeanapple.backtracker.core.model.ltlformula.model.tree.*;

public class CalculationVisitor implements ILtlFormulaVisitor<Result> {

    private CalculationCursor cursor;
    private LtlFormula ltlFormula;


    public CalculationVisitor(CalculationCursor cursor, LtlFormula ltlFormula){
        this.cursor = cursor;
        this.ltlFormula = ltlFormula;
    }



    @Override
    public Result visitAnd(AndNode andNode) {
        return null;
    }

    @Override
    public Result visitDiv(DivNode divNode) {
        return null;
    }

    @Override
    public Result visitEq(EqNode eqNode) {
        return null;
    }

    @Override
    public Result visitF(FNode fNode) {
        return null;
    }

    @Override
    public Result visitG(GNode gNode) {
        return null;
    }

    @Override
    public Result visitGreaterEq(GreaterEqNode greaterEqNode) {
        return null;
    }

    @Override
    public Result visitGreater(GreaterNode greaterNode) {
        return null;
    }

    @Override
    public Result visitImpl(ImplNode implNode) {
        return null;
    }

    @Override
    public Result visitInt(IntNode intNode) {
        return null;
    }

    @Override
    public Result visitLessEq(LessEqNode lessEqNode) {
        return null;
    }

    @Override
    public Result visitLess(LessNode lessNode) {
        return null;
    }

    @Override
    public Result visitMinus(MinusNode minusNode) {
        return null;
    }

    @Override
    public Result visitM(MNode mNode) {
        return null;
    }

    @Override
    public Result visitMul(MulNode mulNode) {
        return null;
    }

    @Override
    public Result visitNotEq(NotEqNode notEqNode) {
        return null;
    }

    @Override
    public Result visitNot(NotNode notNode) {
        return null;
    }

    @Override
    public Result visitOr(OrNode orNode) {
        return null;
    }

    @Override
    public Result visitPlus(PlusNode plusNode) {
        return null;
    }

    @Override
    public Result visitR(RNode rNode) {
        return null;
    }

    @Override
    public Result visitU(UNode uNode) {
        return null;
    }

    @Override
    public Result visitVar(VarNode varNode) {
        return null;
    }

    @Override
    public Result visitW(WNode wNode) {
        return null;
    }

    @Override
    public Result visitX(XNode xNode) {
        return null;
    }
}
