package shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.antlrgenerated;// Generated from C:/Users/ovsianp1/projects/code/SEARCH/cxbacktracker\nusmv.g4 by ANTLR 4.7.2

import java.util.*;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.*;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.expression.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link nusmvParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface nusmvVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link nusmvParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(nusmvParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#composite_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComposite_id(nusmvParser.Composite_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(nusmvParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#unary_operator_sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_operator_sign(nusmvParser.Unary_operator_signContext ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#unary_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_operator(nusmvParser.Unary_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#binary_operator_sign7}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator_sign7(nusmvParser.Binary_operator_sign7Context ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#binary_operator7}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator7(nusmvParser.Binary_operator7Context ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#binary_operator_sign6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator_sign6(nusmvParser.Binary_operator_sign6Context ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#binary_operator6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator6(nusmvParser.Binary_operator6Context ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#binary_operator_sign5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator_sign5(nusmvParser.Binary_operator_sign5Context ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#binary_operator5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator5(nusmvParser.Binary_operator5Context ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#binary_operator_sign4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator_sign4(nusmvParser.Binary_operator_sign4Context ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#binary_operator4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator4(nusmvParser.Binary_operator4Context ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#binary_operator_sign3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator_sign3(nusmvParser.Binary_operator_sign3Context ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#binary_operator3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator3(nusmvParser.Binary_operator3Context ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#ternary_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernary_operator(nusmvParser.Ternary_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#binary_operator_sign2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator_sign2(nusmvParser.Binary_operator_sign2Context ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#binary_operator2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator2(nusmvParser.Binary_operator2Context ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#binary_operator_sign1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator_sign1(nusmvParser.Binary_operator_sign1Context ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#binary_operator1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator1(nusmvParser.Binary_operator1Context ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(nusmvParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#internal_var_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInternal_var_declaration(nusmvParser.Internal_var_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#possibly_untyped_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPossibly_untyped_declaration(nusmvParser.Possibly_untyped_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(nusmvParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(nusmvParser.DefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link nusmvParser#module}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModule(nusmvParser.ModuleContext ctx);
}