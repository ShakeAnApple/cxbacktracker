package shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.expression;

import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.Assignment;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.NuSMVModule;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.TooDeepNextException;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.TypeInferenceException;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.UndeclaredVariableException;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.UnresolvedTypeException;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.ComponentType;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.InputVariable;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.VarType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by buzhinsky on 4/18/17.
 */
public abstract class Expression {
    public final String name;
    public final ExpressionType type;

    public Expression(String name, ExpressionType type) {
        this.name = name;
        this.type = type;
    }

    public abstract Expression forwardInferTypes(Map<String, Variable> allVarDeclarations)
            throws TypeInferenceException, UndeclaredVariableException;

    public abstract Expression propagateNext(boolean propagating, boolean nextAllowed, Assignment topLevelAssignment) throws TooDeepNextException;

    public abstract InputVariable generate(NuSMVModule.FunctionBlockNetworkContext context, int order)
            throws UndeclaredVariableException, UnresolvedTypeException;

    public VarType getVarType() throws UnresolvedTypeException {
        if (type == ExpressionType.UNKNOWN) {
            throw new UnresolvedTypeException("Unresolved type of expression " + this);
        }
        return type == ExpressionType.BOOL ? VarType.BOOLEAN : VarType.INTEGER;
    }

    public ComponentType getChoiceType() throws UnresolvedTypeException {
        if (type == ExpressionType.UNKNOWN) {
            throw new UnresolvedTypeException("Unresolved type of expression " + this);
        }
        return type == ExpressionType.BOOL ? ComponentType.BOOL_CHOICE : ComponentType.INT_CHOICE;
    }

    List<Expression> nextPropagateList(List<Expression> list, boolean propagating, boolean nextAllowed,
                                       Assignment topLevelAssignment) throws TooDeepNextException {
        final List<Expression> newList = new ArrayList<>();
        for (Expression e : list) {
            newList.add(e.propagateNext(propagating, nextAllowed, topLevelAssignment));
        }
        return newList;
    }
}
