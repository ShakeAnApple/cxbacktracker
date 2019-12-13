package shakeanapple.backtracker.parser.ltlformula.recent.antlr;// Generated from C:/Users/ovsianp1/projects/code/SEARCH/cxbacktracker\ltl.g4 by ANTLR 4.7.2

import shakeanapple.backtracker.parser.ltlformula.recent.tree.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ltlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ltlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ltlParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(ltlParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#composite_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComposite_id(ltlParser.Composite_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#unary_operator_sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_operator_sign(ltlParser.Unary_operator_signContext ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#binary_operator_sign5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator_sign5(ltlParser.Binary_operator_sign5Context ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#binary_operator_sign4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator_sign4(ltlParser.Binary_operator_sign4Context ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#binary_operator_sign3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator_sign3(ltlParser.Binary_operator_sign3Context ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#binary_operator_sign2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator_sign2(ltlParser.Binary_operator_sign2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#binary_operator_sign1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator_sign1(ltlParser.Binary_operator_sign1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#comparison_operator_sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison_operator_sign(ltlParser.Comparison_operator_signContext ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#arithmetic_atomic_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic_atomic_value(ltlParser.Arithmetic_atomic_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#arithmetic_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic_atom(ltlParser.Arithmetic_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#arithmetic_expression3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic_expression3(ltlParser.Arithmetic_expression3Context ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#arithmetic_expression2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic_expression2(ltlParser.Arithmetic_expression2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#arithmetic_expression1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic_expression1(ltlParser.Arithmetic_expression1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#comparison_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison_expression(ltlParser.Comparison_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#and_arithmetic_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_arithmetic_expression(ltlParser.And_arithmetic_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#or_arithmetic_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr_arithmetic_expression(ltlParser.Or_arithmetic_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#eq_arithmetic_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq_arithmetic_expression(ltlParser.Eq_arithmetic_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#implies_arithmetic_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplies_arithmetic_expression(ltlParser.Implies_arithmetic_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#proposition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProposition(ltlParser.PropositionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(ltlParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#unary_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_operator(ltlParser.Unary_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#binary_operator5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator5(ltlParser.Binary_operator5Context ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#binary_operator4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator4(ltlParser.Binary_operator4Context ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#binary_operator3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator3(ltlParser.Binary_operator3Context ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#binary_operator2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator2(ltlParser.Binary_operator2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#binary_operator1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator1(ltlParser.Binary_operator1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ltlParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormula(ltlParser.FormulaContext ctx);
}