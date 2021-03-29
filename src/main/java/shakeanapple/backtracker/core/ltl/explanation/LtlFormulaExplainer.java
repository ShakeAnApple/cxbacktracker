package shakeanapple.backtracker.core.ltl.explanation;

import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.CounterexampleCursor;
import shakeanapple.backtracker.core.ltl.evaluation.LtlEvaluator;
import shakeanapple.backtracker.core.ltl.evaluation.model.LogicalResult;
import shakeanapple.backtracker.core.ltl.evaluation.model.LogicalResultKind;
import shakeanapple.backtracker.core.ltl.explanation.model.FormulaCause;
import shakeanapple.backtracker.core.ltl.explanation.model.ExplanationResult;
import shakeanapple.backtracker.core.ltl.explanation.model.History;
import shakeanapple.backtracker.core.ltl.formula.ILtlFormulaVisitor;
import shakeanapple.backtracker.core.ltl.formula.model.LtlFormula;
import shakeanapple.backtracker.core.ltl.formula.model.tree.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LtlFormulaExplainer implements ILtlFormulaVisitor<ExplanationResult>, ILtlFormulaExplainer {

    private final LtlFormula formula;
    private int stepNum;

    private final CounterexampleCursor cursor;
    private final LtlEvaluator evaluator;
    private final History history;

    Map<Integer, LtlFormulaExplainer> timeBranches = new HashMap<>();

    public LtlFormulaExplainer(LtlFormula formula, Counterexample counterexample, LtlEvaluator evaluator) {
        this.formula = formula;
        this.evaluator = evaluator;
        this.cursor = new CounterexampleCursor(counterexample);
        this.history = new History();
    }

    private LtlFormulaExplainer(int stepNum, LtlFormula formula, LtlEvaluator evaluator, CounterexampleCursor cursor, History history) {
        this.formula = formula;
        this.stepNum = stepNum;
        this.evaluator = evaluator;
        this.cursor = cursor;
        this.history = history;
    }

    public ExplanationResult explainRootForStep(int stepNum) {
        return this.explain(stepNum, this.formula.getRoot());
    }

    public ExplanationResult explainNodeForStep(FormulaNode node, int stepNum){
        if (this.history.getResultForNodeByStep(node, stepNum) != null){
            return this.history.getResultForNodeByStep(node, stepNum);
        }

        return explain(stepNum, node);
    }

    private ExplanationResult explain(int stepNum, FormulaNode formulaNode) {
        this.cursor.goTo(stepNum);
        this.timeBranches.put(stepNum, this);
        return formulaNode.apply(this);
    }

    private LtlFormulaExplainer branchNext() {
        CounterexampleCursor cursorBranch = this.cursor.branchNext();

        if (this.timeBranches.containsKey(cursorBranch.getCurStateNum())) {
            return this.timeBranches.get(cursorBranch.getCurStateNum());
        }

        LtlFormulaExplainer timeBranch = new LtlFormulaExplainer(stepNum, this.formula, this.evaluator, cursorBranch, this.history);
        this.timeBranches.put(cursorBranch.getCurStateNum(), timeBranch);
        return timeBranch;
    }

    private void registerNodeInHistory(FormulaNode node) {
        this.history.registerNode(node);
    }

    private ExplanationResult addResToHistoryAndReturn(FormulaNode node, ExplanationResult res) {
        this.history.addResForNode(node, res);
        return res;
    }

    private void registerAttemptInHistory(FormulaNode node, int stepNum) {
        this.history.addAttemptForNode(node, stepNum);
    }

    public boolean attemptForNodeExists(FormulaNode node, int stepNum) {
        return this.history.attemptForNodeExists(node, stepNum);
    }

    ////////////////// implementation ///////////////////////////////
    @Override
    public ExplanationResult visitAnd(AndNode andNode) {
        this.registerNodeInHistory(andNode);

        LogicalResult res = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), andNode);

        if (res.getValue().equals(LogicalResultKind.TRUE)) {
            return andNode.getLeft().apply(this).addCauses(andNode.getRight().apply(this).getCauses());
        }

        List<FormulaCause> causes = new ArrayList<>();
        for (FormulaNode childNode : andNode.getChildren()) {
            LogicalResult childRes = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), childNode);
            if (childRes.getValue().equals(LogicalResultKind.FALSE)) {
                causes.addAll(childNode.apply(this).getCauses());
            }
        }
        return this.addResToHistoryAndReturn(andNode, new ExplanationResult(causes, this.cursor.getCurStateNum()));
    }

    @Override
    public ExplanationResult visitEq(EqNode eqNode) {
        this.registerNodeInHistory(eqNode);

        return this.addResToHistoryAndReturn(eqNode, eqNode.getLeft().apply(this)
                .addCauses(eqNode.getRight().apply(this).getCauses()));
    }

    @Override
    public ExplanationResult visitGreaterEq(GreaterEqNode greaterEqNode) {
        this.registerNodeInHistory(greaterEqNode);

        return this.addResToHistoryAndReturn(greaterEqNode, greaterEqNode.getLeft().apply(this)
                .addCauses(greaterEqNode.getRight().apply(this).getCauses()));
    }

    @Override
    public ExplanationResult visitGreater(GreaterNode greaterNode) {
        this.registerNodeInHistory(greaterNode);

        return this.addResToHistoryAndReturn(greaterNode, greaterNode.getLeft().apply(this)
                .addCauses(greaterNode.getRight().apply(this).getCauses()));
    }

    @Override
    public ExplanationResult visitLessEq(LessEqNode lessEqNode) {
        this.registerNodeInHistory(lessEqNode);

        return this.addResToHistoryAndReturn(lessEqNode, lessEqNode.getLeft().apply(this)
                .addCauses(lessEqNode.getRight().apply(this).getCauses()));
    }

    @Override
    public ExplanationResult visitLess(LessNode lessNode) {
        this.registerNodeInHistory(lessNode);

        return this.addResToHistoryAndReturn(lessNode, lessNode.getLeft().apply(this)
                .addCauses(lessNode.getRight().apply(this).getCauses()));
    }

    @Override
    public ExplanationResult visitNotEq(NotEqNode notEqNode) {
        this.registerNodeInHistory(notEqNode);

        return this.addResToHistoryAndReturn(notEqNode, notEqNode.getLeft().apply(this)
                .addCauses(notEqNode.getRight().apply(this).getCauses()));
    }

    @Override
    public ExplanationResult visitImpl(ImplNode implNode) {
        this.registerNodeInHistory(implNode);

        LogicalResult res = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), implNode);
        LogicalResult leftRes = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), implNode.getLeft());
        LogicalResult rightRes = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), implNode.getRight());

        List<FormulaCause> causes = new ArrayList<>();
        if (res.getValue().equals(LogicalResultKind.TRUE)) {
            if (leftRes.getValue().equals(LogicalResultKind.FALSE)) {
                causes.addAll(implNode.getLeft().apply(this).getCauses());
            }
            if (rightRes.getValue().equals(LogicalResultKind.TRUE)) {
                causes.addAll(implNode.getRight().apply(this).getCauses());
            }
            return new ExplanationResult(causes, this.cursor.getCurStateNum());
        }

        if (!rightRes.getValue().equals(LogicalResultKind.FALSE) ||
                !leftRes.getValue().equals(LogicalResultKind.TRUE)) {
            throw new RuntimeException("Impl calculation is wrong");
        }

        return this.addResToHistoryAndReturn(implNode, implNode.getRight().apply(this).addCauses(
                implNode.getLeft().apply(this).getCauses()));
    }

    @Override
    public ExplanationResult visitNot(NotNode notNode) {
        this.registerNodeInHistory(notNode);

        return this.addResToHistoryAndReturn(notNode, notNode.getChild().apply(this));
    }

    @Override
    public ExplanationResult visitOr(OrNode orNode) {
        this.registerNodeInHistory(orNode);

        LogicalResult res = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), orNode);

        if (res.getValue().equals(LogicalResultKind.TRUE)) {
            List<FormulaCause> causes = new ArrayList<>();
            for (FormulaNode childNode : orNode.getChildren()) {
                LogicalResult childRes = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), childNode);
                if (childRes.getValue().equals(LogicalResultKind.TRUE)) {
                    causes.addAll(childNode.apply(this).getCauses());
                }
            }
            return new ExplanationResult(causes, this.cursor.getCurStateNum());
        }

        return this.addResToHistoryAndReturn(orNode, orNode.getLeft().apply(this).addCauses(orNode.getRight().apply(this).getCauses()));
    }


    @Override
    public ExplanationResult visitInt(IntNode intNode) {
        this.registerNodeInHistory(intNode);

        return this.addResToHistoryAndReturn(intNode, ExplanationResult.empty(this.cursor.getCurStateNum()));
    }

    @Override
    public ExplanationResult visitVar(VarNode varNode) {
        this.registerNodeInHistory(varNode);

        return this.addResToHistoryAndReturn(varNode, new ExplanationResult(new FormulaCause(this.cursor.getCurStateNum(), varNode.getName(),
                this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), varNode).toString(), varNode.getId()), this.cursor.getCurStateNum()));
    }

    @Override
    public ExplanationResult visitDiv(DivNode divNode) {
        this.registerNodeInHistory(divNode);

        return this.addResToHistoryAndReturn(divNode, divNode.getLeft().apply(this)
                .addCauses(divNode.getRight().apply(this).getCauses()));
    }

    @Override
    public ExplanationResult visitMinus(MinusNode minusNode) {
        this.registerNodeInHistory(minusNode);

        return this.addResToHistoryAndReturn(minusNode, minusNode.getLeft().apply(this)
                .addCauses(minusNode.getRight().apply(this).getCauses()));
    }


    @Override
    public ExplanationResult visitMul(MulNode mulNode) {
        this.registerNodeInHistory(mulNode);

        return this.addResToHistoryAndReturn(mulNode, mulNode.getLeft().apply(this)
                .addCauses(mulNode.getRight().apply(this).getCauses()));
    }


    @Override
    public ExplanationResult visitPlus(PlusNode plusNode) {
        this.registerNodeInHistory(plusNode);

        return this.addResToHistoryAndReturn(plusNode, plusNode.getLeft().apply(this)
                .addCauses(plusNode.getRight().apply(this).getCauses()));
    }

    @Override
    public ExplanationResult visitR(RNode rNode) {
        this.registerNodeInHistory(rNode);

        ExplanationResult expRes = this.history.getResultForNodeByStep(rNode, this.cursor.getCurStateNum());
        if (expRes != null) {
            return expRes;
        }

        LogicalResult res = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), rNode);

        if (res.getValue().equals(LogicalResultKind.TRUE)) {
            // then right is true anyway
            List<FormulaCause> causes = rNode.getRight().apply(this).getCauses();

            LogicalResult leftRes = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), rNode.getLeft());
            // if left is true then formula is satisfied
            if (leftRes.getValue().equals(LogicalResultKind.TRUE)) {
                causes.addAll(rNode.getLeft().apply(this).getCauses());
                return addResToHistoryAndReturn(rNode, new ExplanationResult(causes, this.cursor.getCurStateNum()));
            }
            // else let's see what's going on on the next step and check if we looped
            if (this.attemptForNodeExists(rNode, this.cursor.getCurStateNum())) {
                return ExplanationResult.empty(this.cursor.getCurStateNum());
            }

            this.registerAttemptInHistory(rNode, this.cursor.getCurStateNum());

            return addResToHistoryAndReturn(rNode, new ExplanationResult(new ArrayList<>(causes) {{
                addAll(rNode.apply(branchNext()).getCauses());
            }}, this.cursor.getCurStateNum()));
        }

        LogicalResult rightRes = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), rNode.getRight());
        if (rightRes.getValue().equals(LogicalResultKind.TRUE)) {
            return addResToHistoryAndReturn(rNode, new ExplanationResult(rNode.apply(this.branchNext()).getCauses(), this.cursor.getCurStateNum()));
        }

        return addResToHistoryAndReturn(rNode, new ExplanationResult(rNode.getRight().apply(this).getCauses(), this.cursor.getCurStateNum()));

    }

    @Override
    public ExplanationResult visitU(UNode uNode) {
        this.registerNodeInHistory(uNode);

        ExplanationResult expRes = this.history.getResultForNodeByStep(uNode, this.cursor.getCurStateNum());
        if (expRes != null) {
            return expRes;
        }

        LogicalResult res = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), uNode);

        if (res.getValue().equals(LogicalResultKind.TRUE)) {
            LogicalResult rightRes = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), uNode.getRight());
            if (rightRes.getValue().equals(LogicalResultKind.TRUE)) {
                return addResToHistoryAndReturn(uNode, new ExplanationResult(uNode.getRight().apply(this).getCauses(), this.cursor.getCurStateNum()));
            }

            // if the formula true we know that left is true on the current step
            List<FormulaCause> causes = uNode.getLeft().apply(this).getCauses();
            return addResToHistoryAndReturn(uNode,
                    new ExplanationResult(new ArrayList<>(causes) {{
                        addAll(uNode.apply(branchNext()).getCauses());
                    }}, this.cursor.getCurStateNum()));
        }

        // if formula is falsified then right res is false, work with left
        LogicalResult leftRes = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), uNode.getLeft());
        // we need causes for right anyway
        List<FormulaCause> causes = uNode.getRight().apply(this).getCauses();
        if (leftRes.getValue().equals(LogicalResultKind.FALSE)) {
            causes.addAll(uNode.getLeft().apply(this).getCauses());
            return addResToHistoryAndReturn(uNode, new ExplanationResult(causes, this.cursor.getCurStateNum()));
        }

        // if left is true then check if we already tried his node, if yes, then it's end of path
        if (this.attemptForNodeExists(uNode, this.cursor.getCurStateNum())) {
            return ExplanationResult.empty(this.cursor.getCurStateNum());
        }

        // if we're here the first time
        this.registerAttemptInHistory(uNode, this.cursor.getCurStateNum());
        return addResToHistoryAndReturn(uNode, new ExplanationResult(new ArrayList<>(causes) {{
            addAll(uNode.apply(branchNext()).getCauses());
        }}, this.cursor.getCurStateNum()));
    }

    @Override
    public ExplanationResult visitX(XNode xNode) {
        this.registerNodeInHistory(xNode);

        LtlFormulaExplainer timeBranch = this.branchNext();
        return this.addResToHistoryAndReturn(xNode, xNode.getChild().apply(timeBranch));
    }

    @Override
    public ExplanationResult visitF(FNode fNode) {
        this.registerNodeInHistory(fNode);

        ExplanationResult expRes = this.history.getResultForNodeByStep(fNode, this.cursor.getCurStateNum());
        if (expRes != null) {
            return expRes;
        }

        LogicalResult res = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), fNode);

        LogicalResult childRes = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), fNode.getChild());
        if (res.getValue().equals(LogicalResultKind.TRUE)) {
            if (childRes.getValue().equals(LogicalResultKind.TRUE)) {
                return this.addResToHistoryAndReturn(fNode, new ExplanationResult(fNode.getChild().apply(this).getCauses(), this.cursor.getCurStateNum()));
            }
            return this.addResToHistoryAndReturn(fNode, new ExplanationResult(fNode.apply(this.branchNext()).getCauses(), this.cursor.getCurStateNum()));
        }

        List<FormulaCause> causes = fNode.getChild().apply(this).getCauses();
        if (this.attemptForNodeExists(fNode, this.cursor.getCurStateNum())) {
            return addResToHistoryAndReturn(fNode, new ExplanationResult(causes, this.cursor.getCurStateNum()));
        }

        this.registerAttemptInHistory(fNode, this.cursor.getCurStateNum());

        return addResToHistoryAndReturn(fNode, new ExplanationResult(new ArrayList<>(causes) {{
            addAll(fNode.apply(branchNext()).getCauses());
        }},
                this.cursor.getCurStateNum()));

    }

    @Override
    public ExplanationResult visitG(GNode gNode) {
        this.registerNodeInHistory(gNode);

        ExplanationResult expRes = this.history.getResultForNodeByStep(gNode, this.cursor.getCurStateNum());
        if (expRes != null) {
            return expRes;
        }

        LogicalResult res = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), gNode);

        LogicalResult childRes = (LogicalResult) this.evaluator.calculateNodeForStep(this.cursor.getCurStateNum(), gNode.getChild());
        if (res.getValue().equals(LogicalResultKind.FALSE)) {
            if (childRes.getValue().equals(LogicalResultKind.FALSE)) {
                return this.addResToHistoryAndReturn(gNode, new ExplanationResult(gNode.getChild().apply(this).getCauses(), this.cursor.getCurStateNum()));
            }
            return this.addResToHistoryAndReturn(gNode, new ExplanationResult(gNode.apply(this.branchNext()).getCauses(), this.cursor.getCurStateNum()));
        }

        List<FormulaCause> causes = gNode.getChild().apply(this).getCauses();
        if (this.attemptForNodeExists(gNode, this.cursor.getCurStateNum())) {
            return addResToHistoryAndReturn(gNode, new ExplanationResult(causes, this.cursor.getCurStateNum()));
        }

        this.registerAttemptInHistory(gNode, this.cursor.getCurStateNum());

        return addResToHistoryAndReturn(gNode, new ExplanationResult(new ArrayList<>() {{
            addAll(causes);
            addAll(gNode.apply(branchNext()).getCauses());
        }},
                this.cursor.getCurStateNum()));

    }

    ////////////////// Not implemented

    @Override
    public ExplanationResult visitW(WNode wNode) {
        throw new RuntimeException("Not implemented W operator");
    }

    @Override
    public ExplanationResult visitM(MNode mNode) {
        throw new RuntimeException("Not implemented M operator");
    }
}
