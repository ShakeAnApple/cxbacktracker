package shakeanapple.backtracker.nusmvparsing.expression;

import shakeanapple.backtracker.nusmvparsing.NuSMVModule;
import shakeanapple.backtracker.nusmvparsing.exceptions.TypeInferenceException;
import shakeanapple.backtracker.nusmvparsing.exceptions.UndeclaredVariableException;
import shakeanapple.backtracker.nusmvparsing.exceptions.UnresolvedTypeException;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.ComponentType;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.InputVariable;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.VarType;

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
}
