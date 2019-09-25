package shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model;

import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.tree.*;

public interface ILtlFormulaVisitor<TRes> {
    TRes visitAnd(AndNode andNode);

    TRes visitDiv(DivNode divNode);

    TRes visitEq(EqNode eqNode);

    TRes visitF(FNode fNode);

    TRes visitG(GNode gNode);

    TRes visitGreaterEq(GreaterEqNode greaterEqNode);

    TRes visitGreater(GreaterNode greaterNode);

    TRes visitImpl(ImplNode implNode);

    TRes visitInt(IntNode intNode);

    TRes visitLessEq(LessEqNode lessEqNode);

    TRes visitLess(LessNode lessNode);

    TRes visitMinus(MinusNode minusNode);

    TRes visitM(MNode mNode);

    TRes visitMul(MulNode mulNode);

    TRes visitNotEq(NotEqNode notEqNode);

    TRes visitNot(NotNode notNode);

    TRes visitOr(OrNode orNode);

    TRes visitPlus(PlusNode plusNode);

    TRes visitR(RNode rNode);

    TRes visitU(UNode uNode);

    TRes visitVar(VarNode varNode);

    TRes visitW(WNode wNode);

    TRes visitX(XNode xNode);
}
