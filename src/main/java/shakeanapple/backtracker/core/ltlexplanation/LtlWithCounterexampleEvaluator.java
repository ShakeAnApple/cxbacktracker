package shakeanapple.backtracker.core.ltlexplanation;

import shakeanapple.backtracker.core.counterexample.CounterexampleCursor;
import shakeanapple.backtracker.core.ltlexplanation.model.*;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.ILtlFormulaVisitor;
import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.LtlFormula;
import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.tree.*;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;

public class LtlWithCounterexampleEvaluator implements ILtlFormulaVisitor<CalculationResult>, LtlSequentialEvaluator {

    private final CounterexampleCursor cursor;
    private final LtlFormula ltlFormula;

    private final History history;
    private final CalculatedFormula calculatedFormula;

    public LtlWithCounterexampleEvaluator(Counterexample counterexample, LtlFormula ltlFormula) {
        this.cursor = new CounterexampleCursor(counterexample);
        this.ltlFormula = ltlFormula;

        this.history = new History();
        this.calculatedFormula = CalculatedFormula.fromBasicLtl(this.ltlFormula);
    }

    private LtlWithCounterexampleEvaluator(History history, CounterexampleCursor cursor, LtlFormula ltlFormula){
        this.cursor = cursor;
        this.history = history;
        this.ltlFormula = ltlFormula;
        this.calculatedFormula = CalculatedFormula.fromBasicLtl(ltlFormula);
    }

    @Override
    public ICalculatedFormula moveNext() {
        if (this.cursor.hasNext()) {
            this.cursor.moveNext();
            this.ltlFormula.applyVisitor(this);
            this.calculatedFormula.applyHistory(history, this.cursor.getCurStateNum());
        }
        return this.calculatedFormula;
    }

    @Override
    public ICalculatedFormula getForStep(int stepNum) {
        if (this.cursor.getCurStateNum() >= stepNum){
            this.calculatedFormula.applyHistory(this.history, stepNum);
        }
        else {
            while (this.cursor.getCurStateNum() < stepNum) {
                this.cursor.moveNext();
                this.ltlFormula.applyVisitor(this);
                this.calculatedFormula.applyHistory(this.history, this.cursor.getCurStateNum());
            }
        }
        return this.calculatedFormula;
    }

//    public State getStateFalsified() {
//        CalculationResult res = this.ltlFormula.applyVisitor(this);
//        while (res.getValue() != LogicalResultKind.FALSE) {
//            this.cursor.moveNext();
//            res = this.ltlFormula.applyVisitor(this);
//        }
//        return this.cursor.getCurState();
//    }

    private void registerNodeInHistory(FormulaNode node) {
        this.history.registerNode(node);
    }

    private CalculationResult addResToHistoryAndReturn(FormulaNode node, CalculationResult res){
        this.history.addResForNode(node, res);
        return res;
    }

    private LtlWithCounterexampleEvaluator branch() {
        return new LtlWithCounterexampleEvaluator(this.history, this.cursor.branch(), this.ltlFormula);
    }

    /////////////////////////////////////// implementation ////////////////////////////////////////////////////


    ///////////////// LTL

    @Override
    public CalculationResult visitU(UNode uNode) {
        this.registerNodeInHistory(uNode);

        // TODO check convertions

        // once falsified can't be true again and inverse
        LogicalResultKind prevRes = ((LogicalResult) this.history.getResultForNodeByStep(uNode, this.cursor.getCurStateNum() - 1)).getValue();
        if (!prevRes.equals(LogicalResultKind.UNKNOWN)) {
            return addResToHistoryAndReturn(uNode, new LogicalResult(prevRes, this.cursor.getCurStateNum()));
        }

        LogicalResult left = (LogicalResult) uNode.getLeft().apply(this);
        LogicalResult right = (LogicalResult) uNode.getRight().apply(this);

        if (left.getValue().equals(LogicalResultKind.UNKNOWN) || right.getValue().equals(LogicalResultKind.UNKNOWN)) {
            return addResToHistoryAndReturn(uNode, new LogicalResult(LogicalResultKind.UNKNOWN, this.cursor.getCurStateNum()));
        }

        if (left.getValue().equals(LogicalResultKind.FALSE) && right.getValue().equals(LogicalResultKind.FALSE)) {
            return addResToHistoryAndReturn(uNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
        }

        // TODO what if right is unknown? can it be?
        if (right.getValue().equals(LogicalResultKind.TRUE)){
            return addResToHistoryAndReturn(uNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        return addResToHistoryAndReturn(uNode, new LogicalResult(LogicalResultKind.UNKNOWN, this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitX(XNode xNode) {
        this.registerNodeInHistory(xNode);

//        CalculationResult prevRes = this.history.getResultForNodeByStep(xNode, this.cursor.getCurStateNum());
//        if (prevRes != null){
//            return prevRes;
//        }

//        LogicalResult curStep = (LogicalResult) xNode.getChild().apply(this);
//        CalculationResult res = this.addResToHistoryAndReturn(xNode, new LogicalResult(LogicalResultKind.UNKNOWN));

        LtlWithCounterexampleEvaluator timeBranch = this.branch();
        timeBranch.cursor.moveNext();
        LogicalResult nextStep = (LogicalResult) xNode.getChild().apply(timeBranch);
        //this.addResToHistoryAndReturn(xNode, new LogicalResult(nextStep.getValue(), this.cursor.getCurStateNum()));

        return this.addResToHistoryAndReturn(xNode, new LogicalResult(nextStep.getValue(), this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitF(FNode fNode) {
        this.registerNodeInHistory(fNode);
        // TODO check convertions

        LogicalResultKind prevRes = ((LogicalResult) this.history.getResultForNodeByStep(fNode, this.cursor.getCurStateNum() - 1)).getValue();
        if (!prevRes.equals(LogicalResultKind.UNKNOWN)) {
            return addResToHistoryAndReturn(fNode, new LogicalResult(prevRes, this.cursor.getCurStateNum()));
        }

        LogicalResult child = (LogicalResult) fNode.getChild().apply(this);

        if (child.getValue().equals(LogicalResultKind.UNKNOWN)) {
            return addResToHistoryAndReturn(fNode, new LogicalResult(LogicalResultKind.UNKNOWN, this.cursor.getCurStateNum()));
        }

        if (child.getValue().equals(LogicalResultKind.TRUE)) {
            return addResToHistoryAndReturn(fNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        if (child.getValue().equals(LogicalResultKind.FALSE)) {
            if (!this.cursor.hasNext()) {
                return addResToHistoryAndReturn(fNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
            } else {
                return addResToHistoryAndReturn(fNode, new LogicalResult(LogicalResultKind.UNKNOWN, this.cursor.getCurStateNum()));
            }
        }
        return addResToHistoryAndReturn(fNode, new LogicalResult(LogicalResultKind.UNDEFINED, this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitG(GNode gNode) {
        this.registerNodeInHistory(gNode);

        LogicalResult child = (LogicalResult) gNode.getChild().apply(this);

        if (this.cursor.getCurStateNum() > 1) {
            LogicalResultKind prevRes = ((LogicalResult) this.history.getResultForNodeByStep(gNode, this.cursor.getCurStateNum() - 1)).getValue();
            if (prevRes.equals(LogicalResultKind.FALSE)) {
                return addResToHistoryAndReturn(gNode, new LogicalResult(prevRes, this.cursor.getCurStateNum()));
            }
        }
        return addResToHistoryAndReturn(gNode, new LogicalResult(child.getValue(), this.cursor.getCurStateNum()));
    }


    ///////////// var const

    @Override
    public CalculationResult visitVar(VarNode varNode) {
        this.registerNodeInHistory(varNode);

        CalculationResult res = null;
        ValueHolder valueHolder = this.cursor.getCurState().getVarByName(varNode.getName()).getValue();
        if (valueHolder instanceof BooleanValueHolder){
            res = new LogicalResult(((BooleanValueHolder)valueHolder).getValue() ? LogicalResultKind.TRUE : LogicalResultKind.FALSE, this.cursor.getCurStateNum());
        } else if (valueHolder instanceof IntegerValueHolder){
            res = new ArithmeticResult(((IntegerValueHolder)valueHolder).getValue(), this.cursor.getCurStateNum());
        }
        return addResToHistoryAndReturn(varNode, res);
    }

    @Override
    public CalculationResult visitInt(IntNode intNode) {
        this.registerNodeInHistory(intNode);

        return addResToHistoryAndReturn(intNode, new ArithmeticResult(new IntegerValueHolder(intNode.getValue()), this.cursor.getCurStateNum()));
    }

    //////////// logical

    @Override
    public CalculationResult visitImpl(ImplNode implNode) {
        this.registerNodeInHistory(implNode);

        LogicalResult left = (LogicalResult) implNode.getLeft().apply(this);
        LogicalResult right = (LogicalResult) implNode.getRight().apply(this);

        if (left.getValue().equals(LogicalResultKind.UNKNOWN) || right.getValue().equals(LogicalResultKind.UNKNOWN)){
            return addResToHistoryAndReturn(implNode, new LogicalResult(LogicalResultKind.UNKNOWN, this.cursor.getCurStateNum()));
        }

        if (left.getValue().equals(LogicalResultKind.TRUE) && right.getValue().equals(LogicalResultKind.TRUE)){
            return addResToHistoryAndReturn(implNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        if (left.getValue().equals(LogicalResultKind.TRUE) && right.getValue().equals(LogicalResultKind.FALSE)){
            return addResToHistoryAndReturn(implNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
        }

        if (left.getValue().equals(LogicalResultKind.FALSE) && right.getValue().equals(LogicalResultKind.TRUE)){
            return addResToHistoryAndReturn(implNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        if (left.getValue().equals(LogicalResultKind.FALSE) && right.getValue().equals(LogicalResultKind.FALSE)){
            return addResToHistoryAndReturn(implNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        return addResToHistoryAndReturn(implNode, new LogicalResult(LogicalResultKind.UNDEFINED, this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitAnd(AndNode andNode) {
        this.registerNodeInHistory(andNode);

        //TODO check convertions
        LogicalResult left = (LogicalResult) andNode.getLeft().apply(this);
        LogicalResult right = (LogicalResult) andNode.getRight().apply(this);

        if (left.getValue() == LogicalResultKind.UNKNOWN || right.getValue() == LogicalResultKind.UNKNOWN)
            return addResToHistoryAndReturn(andNode, new LogicalResult(LogicalResultKind.UNKNOWN, this.cursor.getCurStateNum()));

        if (left.getValue() == LogicalResultKind.TRUE && right.getValue() == LogicalResultKind.TRUE)
            return addResToHistoryAndReturn(andNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));

        return addResToHistoryAndReturn(andNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitNot(NotNode notNode) {
        this.registerNodeInHistory(notNode);

        //TODO check convertions
        LogicalResult child = (LogicalResult) notNode.getChild().apply(this);

        if (child.getValue().equals(LogicalResultKind.UNKNOWN))
            return addResToHistoryAndReturn(notNode, new LogicalResult(LogicalResultKind.UNKNOWN, this.cursor.getCurStateNum()));

        if (child.getValue().equals(LogicalResultKind.FALSE))
            return addResToHistoryAndReturn(notNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));

        return addResToHistoryAndReturn(notNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitOr(OrNode orNode) {
        this.registerNodeInHistory(orNode);

        //TODO check convertions
        LogicalResult left = (LogicalResult) orNode.getLeft().apply(this);
        LogicalResult right = (LogicalResult) orNode.getRight().apply(this);

        if (left.getValue() == LogicalResultKind.UNKNOWN || right.getValue() == LogicalResultKind.UNKNOWN)
            return addResToHistoryAndReturn(orNode, new LogicalResult(LogicalResultKind.UNKNOWN, this.cursor.getCurStateNum()));

        if (left.getValue() == LogicalResultKind.TRUE || right.getValue() == LogicalResultKind.TRUE)
            return addResToHistoryAndReturn(orNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));

        return addResToHistoryAndReturn(orNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
    }

    ////////////// eq noteq

    @Override
    public CalculationResult visitEq(EqNode eqNode) {
        this.registerNodeInHistory(eqNode);

        CalculationResult left = eqNode.getLeft().apply(this);
        CalculationResult right = eqNode.getRight().apply(this);

        if (left.getValue() == LogicalResultKind.UNKNOWN || right.getValue() == LogicalResultKind.UNKNOWN)
            return addResToHistoryAndReturn(eqNode, new LogicalResult(LogicalResultKind.UNKNOWN, this.cursor.getCurStateNum()));

        if (left.getValue().equals(right.getValue()))
            return addResToHistoryAndReturn(eqNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));

        return addResToHistoryAndReturn(eqNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitNotEq(NotEqNode notEqNode) {
        this.registerNodeInHistory(notEqNode);

        //TODO check convertions
        CalculationResult left = notEqNode.getLeft().apply(this);
        CalculationResult right = notEqNode.getRight().apply(this);

        if (!left.getValue().equals(right.getValue())) {
            return addResToHistoryAndReturn(notEqNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        return addResToHistoryAndReturn(notEqNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
    }

    //////////////////////// arithm args boolean result

    @Override
    public CalculationResult visitGreaterEq(GreaterEqNode greaterEqNode) {
        this.registerNodeInHistory(greaterEqNode);

        //TODO check convertions
        ArithmeticResult left = (ArithmeticResult) greaterEqNode.getLeft().apply(this);
        ArithmeticResult right = (ArithmeticResult) greaterEqNode.getRight().apply(this);

        if ((Integer)left.getValue().getValue() >= (Integer) right.getValue().getValue()) {
            return addResToHistoryAndReturn(greaterEqNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        return addResToHistoryAndReturn(greaterEqNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitGreater(GreaterNode greaterNode) {
        this.registerNodeInHistory(greaterNode);

        //TODO check convertions
        ArithmeticResult left = (ArithmeticResult) greaterNode.getLeft().apply(this);
        ArithmeticResult right = (ArithmeticResult) greaterNode.getRight().apply(this);

        if ((Integer)left.getValue().getValue() > (Integer)right.getValue().getValue()) {
            return addResToHistoryAndReturn(greaterNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        return addResToHistoryAndReturn(greaterNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitLessEq(LessEqNode lessEqNode) {
        this.registerNodeInHistory(lessEqNode);

        //TODO check convertions
        ArithmeticResult left = (ArithmeticResult) lessEqNode.getLeft().apply(this);
        ArithmeticResult right = (ArithmeticResult) lessEqNode.getRight().apply(this);

        if ((Integer)left.getValue().getValue() <= (Integer)right.getValue().getValue()) {
            return addResToHistoryAndReturn(lessEqNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        return addResToHistoryAndReturn(lessEqNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitLess(LessNode lessNode) {
        this.registerNodeInHistory(lessNode);

        //TODO check convertions
        ArithmeticResult left = (ArithmeticResult) lessNode.getLeft().apply(this);
        ArithmeticResult right = (ArithmeticResult) lessNode.getRight().apply(this);

        if ((Integer)left.getValue().getValue() < (Integer)right.getValue().getValue()) {
            return addResToHistoryAndReturn(lessNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        return addResToHistoryAndReturn(lessNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
    }


    ///////////////////////// pure arithmetic

    @Override
    public CalculationResult visitMinus(MinusNode minusNode) {
        this.registerNodeInHistory(minusNode);

        //TODO check convertions
        ArithmeticResult left = (ArithmeticResult) minusNode.getLeft().apply(this);
        ArithmeticResult right = (ArithmeticResult) minusNode.getRight().apply(this);

        return addResToHistoryAndReturn(minusNode, new ArithmeticResult((Integer)left.getValue().getValue() - (Integer)right.getValue().getValue(), this.cursor.getCurStateNum()));
    }


    @Override
    public CalculationResult visitDiv(DivNode divNode) {
        this.registerNodeInHistory(divNode);

        //TODO check convertions
        ArithmeticResult left = (ArithmeticResult) divNode.getLeft().apply(this);
        ArithmeticResult right = (ArithmeticResult) divNode.getRight().apply(this);

        return addResToHistoryAndReturn(divNode, new ArithmeticResult((Integer) left.getValue().getValue() / (Integer) right.getValue().getValue(), this.cursor.getCurStateNum()));

    }

    @Override
    public CalculationResult visitMul(MulNode mulNode) {
        this.registerNodeInHistory(mulNode);

        //TODO check convertions
        ArithmeticResult left = (ArithmeticResult) mulNode.getLeft().apply(this);
        ArithmeticResult right = (ArithmeticResult) mulNode.getRight().apply(this);

        return addResToHistoryAndReturn(mulNode, new ArithmeticResult((Integer) left.getValue().getValue() * (Integer) right.getValue().getValue(), this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitPlus(PlusNode plusNode) {
        this.registerNodeInHistory(plusNode);

        //TODO check convertions
        ArithmeticResult left = (ArithmeticResult) plusNode.getLeft().apply(this);
        ArithmeticResult right = (ArithmeticResult) plusNode.getRight().apply(this);

        return addResToHistoryAndReturn(plusNode, new ArithmeticResult((Integer) left.getValue().getValue() + (Integer) right.getValue().getValue(), this.cursor.getCurStateNum()));
    }

    //////////////////////////// not implemented
    @Override
    public CalculationResult visitR(RNode rNode) {
        throw new RuntimeException("R not implemented");
    }

    @Override
    public CalculationResult visitW(WNode wNode) {
        throw new RuntimeException("W not implemented");
    }

    @Override
    public CalculationResult visitM(MNode mNode) {
        throw new RuntimeException("M not implemented");
    }


}
