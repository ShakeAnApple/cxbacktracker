package shakeanapple.backtracker.core.ltl.evaluation;

import shakeanapple.backtracker.core.counterexample.CounterexampleCursor;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.ltl.evaluation.model.*;
import shakeanapple.backtracker.core.ltl.formula.ILtlFormulaVisitor;
import shakeanapple.backtracker.core.ltl.formula.model.LtlFormula;
import shakeanapple.backtracker.core.ltl.formula.model.tree.*;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;

public class LtlWithCounterexampleEvaluator implements ILtlFormulaVisitor<CalculationResult>, LtlEvaluator {

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

    private LtlWithCounterexampleEvaluator(History history, CounterexampleCursor cursor, LtlFormula ltlFormula) {
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
            this.calculatedFormula.applyHistory(history, this.cursor);
        }
        return this.calculatedFormula;
    }

    @Override
    public ICalculatedFormula calculateRootForStep(int stepNum) {
        if (this.cursor.getCurStateNum() >= stepNum) {
            CounterexampleCursor timeBranch = this.cursor.branch();
            timeBranch.goTo(stepNum);
            this.calculatedFormula.applyHistory(this.history, timeBranch);
        } else {
            while (this.cursor.getCurStateNum() < stepNum) {
                this.cursor.moveNext();
                this.ltlFormula.applyVisitor(this);
                this.calculatedFormula.applyHistory(this.history, this.cursor);
            }
        }
        return this.calculatedFormula;
    }

    public CalculationResult calculateNodeForStep(int stepNum, FormulaNode node) {
        if (this.history.getResultForNodeByStep(node, stepNum) != null) {
            return this.history.getResultForNodeByStep(node, stepNum);
        }

        if (this.cursor.goTo(stepNum)) {
            return node.apply(this);
        }
        throw new RuntimeException("Counterexample doesn't have step " + stepNum);
    }

    private void registerNodeInHistory(FormulaNode node) {
        this.history.registerNode(node);
    }

    private CalculationResult addResToHistoryAndReturn(FormulaNode node, CalculationResult res) {
        this.history.addResForNode(node, res);
        return res;
    }

    private void registerAttemptInHistory(FormulaNode node, int stepNum) {
        this.history.addAttemptForNode(node, stepNum);
    }

    public boolean attemptForNodeExists(FormulaNode node, int stepNum) {
        return this.history.attemptForNodeExists(node, stepNum);
    }

    private LtlWithCounterexampleEvaluator branchNext() {
        LtlWithCounterexampleEvaluator branch = new LtlWithCounterexampleEvaluator(this.history, this.cursor.branch(), this.ltlFormula);
        branch.cursor.moveNext();
        return branch;
    }

    /////////////////////////////////////// implementation ////////////////////////////////////////////////////


    ///////////////// LTL
    @Override
    public CalculationResult visitR(RNode rNode) {
        this.registerNodeInHistory(rNode);

        // TODO check convertions
        if (this.attemptForNodeExists(rNode, this.cursor.getCurStateNum())) {
            return addResToHistoryAndReturn(rNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        this.registerAttemptInHistory(rNode, this.cursor.getCurStateNum());

        LogicalResult leftRes = (LogicalResult) rNode.getLeft().apply(this);
        LogicalResult rightRes = (LogicalResult) rNode.getRight().apply(this);

        if (rightRes.getValue().equals(LogicalResultKind.FALSE)) {
            return addResToHistoryAndReturn(rNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
        }

        if (leftRes.getValue().equals(LogicalResultKind.TRUE)) {
            return addResToHistoryAndReturn(rNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        return addResToHistoryAndReturn(rNode,
                new LogicalResult((LogicalResultKind) rNode.apply(this.branchNext()).getValue(),
                        this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitU(UNode uNode) {
        this.registerNodeInHistory(uNode);

        // TODO check convertions
        if (this.attemptForNodeExists(uNode, this.cursor.getCurStateNum())) {
            return addResToHistoryAndReturn(uNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
        }

        this.registerAttemptInHistory(uNode, this.cursor.getCurStateNum());

        LogicalResult leftRes = (LogicalResult) uNode.getLeft().apply(this);
        LogicalResult rightRes = (LogicalResult) uNode.getRight().apply(this);

        if (rightRes.getValue().equals(LogicalResultKind.TRUE)) {
            return addResToHistoryAndReturn(uNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        if (leftRes.getValue().equals(LogicalResultKind.FALSE) && rightRes.getValue().equals(LogicalResultKind.FALSE)) {
            return addResToHistoryAndReturn(uNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
        }

        return addResToHistoryAndReturn(uNode,
                new LogicalResult((LogicalResultKind) uNode.apply(this.branchNext()).getValue(),
                        this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitX(XNode xNode) {
        this.registerNodeInHistory(xNode);

        LogicalResult nextStepRes = (LogicalResult) xNode.getChild().apply(this.branchNext());
        return this.addResToHistoryAndReturn(xNode, new LogicalResult(nextStepRes.getValue(), this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitF(FNode fNode) {
        this.registerNodeInHistory(fNode);
        // TODO check convertions

        CalculationResult res = this.history.getResultForNodeByStep(fNode, this.cursor.getCurStateNum());
        if (res != null) {
            return res;
        }

        // in case of recursion
        if (this.attemptForNodeExists(fNode, this.cursor.getCurStateNum())) {
            return addResToHistoryAndReturn(fNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
        }

        this.registerAttemptInHistory(fNode, this.cursor.getCurStateNum());

        LogicalResult childRes = (LogicalResult) fNode.getChild().apply(this);

        if (childRes.getValue().equals(LogicalResultKind.TRUE)) {
            return addResToHistoryAndReturn(fNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        if (this.cursor.isEndOfPath()) {
            return addResToHistoryAndReturn(fNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
        }

        return addResToHistoryAndReturn(fNode,
                new LogicalResult((LogicalResultKind) fNode.apply(this.branchNext()).getValue(),
                        this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitG(GNode gNode) {
        this.registerNodeInHistory(gNode);

        CalculationResult res = this.history.getResultForNodeByStep(gNode, this.cursor.getCurStateNum());
        if (res != null) {
            return res;
        }

        // in case of recursion
        if (this.attemptForNodeExists(gNode, this.cursor.getCurStateNum())) {
            return addResToHistoryAndReturn(gNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        this.registerAttemptInHistory(gNode, this.cursor.getCurStateNum());

        LogicalResult childRes = (LogicalResult) gNode.getChild().apply(this);
        if (childRes.getValue().equals(LogicalResultKind.FALSE)) {
            return addResToHistoryAndReturn(gNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
        }

        if (this.cursor.isEndOfPath()) {
            return addResToHistoryAndReturn(gNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        return addResToHistoryAndReturn(gNode, new LogicalResult((LogicalResultKind) gNode.apply(this.branchNext()).getValue(),
                this.cursor.getCurStateNum()));
    }


    ///////////// var const

    @Override
    public CalculationResult visitVar(VarNode varNode) {
        this.registerNodeInHistory(varNode);

        CalculationResult res = null;
        ValueHolder valueHolder = this.cursor.getCurState().getVarByName(varNode.getName()).getValue();
        if (valueHolder instanceof BooleanValueHolder) {
            res = new LogicalResult(((BooleanValueHolder) valueHolder).getValue() ? LogicalResultKind.TRUE : LogicalResultKind.FALSE, this.cursor.getCurStateNum());
        } else if (valueHolder instanceof IntegerValueHolder) {
            res = new ArithmeticResult(((IntegerValueHolder) valueHolder).getValue(), this.cursor.getCurStateNum());
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

        if (left.getValue().equals(LogicalResultKind.TRUE) && right.getValue().equals(LogicalResultKind.TRUE)) {
            return addResToHistoryAndReturn(implNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        if (left.getValue().equals(LogicalResultKind.TRUE) && right.getValue().equals(LogicalResultKind.FALSE)) {
            return addResToHistoryAndReturn(implNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
        }

        if (left.getValue().equals(LogicalResultKind.FALSE) && right.getValue().equals(LogicalResultKind.TRUE)) {
            return addResToHistoryAndReturn(implNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
        }

        return addResToHistoryAndReturn(implNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitAnd(AndNode andNode) {
        this.registerNodeInHistory(andNode);

        //TODO check convertions
        LogicalResult left = (LogicalResult) andNode.getLeft().apply(this);
        LogicalResult right = (LogicalResult) andNode.getRight().apply(this);

        if (left.getValue() == LogicalResultKind.TRUE && right.getValue() == LogicalResultKind.TRUE)
            return addResToHistoryAndReturn(andNode, new LogicalResult(LogicalResultKind.TRUE, this.cursor.getCurStateNum()));

        return addResToHistoryAndReturn(andNode, new LogicalResult(LogicalResultKind.FALSE, this.cursor.getCurStateNum()));
    }

    @Override
    public CalculationResult visitNot(NotNode notNode) {
        this.registerNodeInHistory(notNode);

        //TODO check convertions
        LogicalResult child = (LogicalResult) notNode.getChild().apply(this);

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

        if ((Integer) left.getValue().getValue() >= (Integer) right.getValue().getValue()) {
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

        if ((Integer) left.getValue().getValue() > (Integer) right.getValue().getValue()) {
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

        if ((Integer) left.getValue().getValue() <= (Integer) right.getValue().getValue()) {
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

        if ((Integer) left.getValue().getValue() < (Integer) right.getValue().getValue()) {
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

        return addResToHistoryAndReturn(minusNode, new ArithmeticResult((Integer) left.getValue().getValue() - (Integer) right.getValue().getValue(), this.cursor.getCurStateNum()));
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
    public CalculationResult visitW(WNode wNode) {
        throw new RuntimeException("W not implemented");
    }

    @Override
    public CalculationResult visitM(MNode mNode) {
        throw new RuntimeException("M not implemented");
    }


}
