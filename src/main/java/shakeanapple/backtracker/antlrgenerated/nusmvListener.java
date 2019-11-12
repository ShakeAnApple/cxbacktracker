// Generated from /home/buzhinsky/repos/cxbacktracker/nusmv.g4 by ANTLR 4.6
package shakeanapple.backtracker.antlrgenerated;

import java.util.*;
import shakeanapple.backtracker.nusmvparsing.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link nusmvParser}.
 */
public interface nusmvListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link nusmvParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(nusmvParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(nusmvParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#composite_id}.
	 * @param ctx the parse tree
	 */
	void enterComposite_id(nusmvParser.Composite_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#composite_id}.
	 * @param ctx the parse tree
	 */
	void exitComposite_id(nusmvParser.Composite_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(nusmvParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(nusmvParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#unary_operator_sign}.
	 * @param ctx the parse tree
	 */
	void enterUnary_operator_sign(nusmvParser.Unary_operator_signContext ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#unary_operator_sign}.
	 * @param ctx the parse tree
	 */
	void exitUnary_operator_sign(nusmvParser.Unary_operator_signContext ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void enterUnary_operator(nusmvParser.Unary_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void exitUnary_operator(nusmvParser.Unary_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#binary_operator7}.
	 * @param ctx the parse tree
	 */
	void enterBinary_operator7(nusmvParser.Binary_operator7Context ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#binary_operator7}.
	 * @param ctx the parse tree
	 */
	void exitBinary_operator7(nusmvParser.Binary_operator7Context ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#binary_operator6}.
	 * @param ctx the parse tree
	 */
	void enterBinary_operator6(nusmvParser.Binary_operator6Context ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#binary_operator6}.
	 * @param ctx the parse tree
	 */
	void exitBinary_operator6(nusmvParser.Binary_operator6Context ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#binary_operator_sign5}.
	 * @param ctx the parse tree
	 */
	void enterBinary_operator_sign5(nusmvParser.Binary_operator_sign5Context ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#binary_operator_sign5}.
	 * @param ctx the parse tree
	 */
	void exitBinary_operator_sign5(nusmvParser.Binary_operator_sign5Context ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#binary_operator5}.
	 * @param ctx the parse tree
	 */
	void enterBinary_operator5(nusmvParser.Binary_operator5Context ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#binary_operator5}.
	 * @param ctx the parse tree
	 */
	void exitBinary_operator5(nusmvParser.Binary_operator5Context ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#binary_operator_sign4}.
	 * @param ctx the parse tree
	 */
	void enterBinary_operator_sign4(nusmvParser.Binary_operator_sign4Context ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#binary_operator_sign4}.
	 * @param ctx the parse tree
	 */
	void exitBinary_operator_sign4(nusmvParser.Binary_operator_sign4Context ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#binary_operator4}.
	 * @param ctx the parse tree
	 */
	void enterBinary_operator4(nusmvParser.Binary_operator4Context ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#binary_operator4}.
	 * @param ctx the parse tree
	 */
	void exitBinary_operator4(nusmvParser.Binary_operator4Context ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#binary_operator_sign3}.
	 * @param ctx the parse tree
	 */
	void enterBinary_operator_sign3(nusmvParser.Binary_operator_sign3Context ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#binary_operator_sign3}.
	 * @param ctx the parse tree
	 */
	void exitBinary_operator_sign3(nusmvParser.Binary_operator_sign3Context ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#binary_operator3}.
	 * @param ctx the parse tree
	 */
	void enterBinary_operator3(nusmvParser.Binary_operator3Context ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#binary_operator3}.
	 * @param ctx the parse tree
	 */
	void exitBinary_operator3(nusmvParser.Binary_operator3Context ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#ternary_operator}.
	 * @param ctx the parse tree
	 */
	void enterTernary_operator(nusmvParser.Ternary_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#ternary_operator}.
	 * @param ctx the parse tree
	 */
	void exitTernary_operator(nusmvParser.Ternary_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#binary_operator_sign2}.
	 * @param ctx the parse tree
	 */
	void enterBinary_operator_sign2(nusmvParser.Binary_operator_sign2Context ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#binary_operator_sign2}.
	 * @param ctx the parse tree
	 */
	void exitBinary_operator_sign2(nusmvParser.Binary_operator_sign2Context ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#binary_operator2}.
	 * @param ctx the parse tree
	 */
	void enterBinary_operator2(nusmvParser.Binary_operator2Context ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#binary_operator2}.
	 * @param ctx the parse tree
	 */
	void exitBinary_operator2(nusmvParser.Binary_operator2Context ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#binary_operator_sign1}.
	 * @param ctx the parse tree
	 */
	void enterBinary_operator_sign1(nusmvParser.Binary_operator_sign1Context ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#binary_operator_sign1}.
	 * @param ctx the parse tree
	 */
	void exitBinary_operator_sign1(nusmvParser.Binary_operator_sign1Context ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#binary_operator1}.
	 * @param ctx the parse tree
	 */
	void enterBinary_operator1(nusmvParser.Binary_operator1Context ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#binary_operator1}.
	 * @param ctx the parse tree
	 */
	void exitBinary_operator1(nusmvParser.Binary_operator1Context ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(nusmvParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(nusmvParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(nusmvParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(nusmvParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#internal_var_declaration}.
	 * @param ctx the parse tree
	 */
	void enterInternal_var_declaration(nusmvParser.Internal_var_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#internal_var_declaration}.
	 * @param ctx the parse tree
	 */
	void exitInternal_var_declaration(nusmvParser.Internal_var_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#input_var_declaration}.
	 * @param ctx the parse tree
	 */
	void enterInput_var_declaration(nusmvParser.Input_var_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#input_var_declaration}.
	 * @param ctx the parse tree
	 */
	void exitInput_var_declaration(nusmvParser.Input_var_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link nusmvParser#module}.
	 * @param ctx the parse tree
	 */
	void enterModule(nusmvParser.ModuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link nusmvParser#module}.
	 * @param ctx the parse tree
	 */
	void exitModule(nusmvParser.ModuleContext ctx);
}