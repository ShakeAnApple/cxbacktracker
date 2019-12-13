package shakeanapple.backtracker.parser.ltlformula.recent.antlr;// Generated from C:/Users/ovsianp1/projects/code/SEARCH/cxbacktracker\ltl.g4 by ANTLR 4.7.2

import shakeanapple.backtracker.parser.ltlformula.recent.tree.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ltlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, WS=33, INT_CONST=34, TRUE=35, FALSE=36, COUNT=37, ID=38;
	public static final int
		RULE_constant = 0, RULE_composite_id = 1, RULE_unary_operator_sign = 2, 
		RULE_binary_operator_sign5 = 3, RULE_binary_operator_sign4 = 4, RULE_binary_operator_sign3 = 5, 
		RULE_binary_operator_sign2 = 6, RULE_binary_operator_sign1 = 7, RULE_comparison_operator_sign = 8, 
		RULE_arithmetic_atomic_value = 9, RULE_arithmetic_atom = 10, RULE_arithmetic_expression3 = 11, 
		RULE_arithmetic_expression2 = 12, RULE_arithmetic_expression1 = 13, RULE_comparison_expression = 14, 
		RULE_and_arithmetic_expression = 15, RULE_or_arithmetic_expression = 16, 
		RULE_eq_arithmetic_expression = 17, RULE_implies_arithmetic_expression = 18, 
		RULE_proposition = 19, RULE_atom = 20, RULE_unary_operator = 21, RULE_binary_operator5 = 22, 
		RULE_binary_operator4 = 23, RULE_binary_operator3 = 24, RULE_binary_operator2 = 25, 
		RULE_binary_operator1 = 26, RULE_formula = 27;
	private static String[] makeRuleNames() {
		return new String[] {
			"constant", "composite_id", "unary_operator_sign", "binary_operator_sign5", 
			"binary_operator_sign4", "binary_operator_sign3", "binary_operator_sign2", 
			"binary_operator_sign1", "comparison_operator_sign", "arithmetic_atomic_value", 
			"arithmetic_atom", "arithmetic_expression3", "arithmetic_expression2", 
			"arithmetic_expression1", "comparison_expression", "and_arithmetic_expression", 
			"or_arithmetic_expression", "eq_arithmetic_expression", "implies_arithmetic_expression", 
			"proposition", "atom", "unary_operator", "binary_operator5", "binary_operator4", 
			"binary_operator3", "binary_operator2", "binary_operator1", "formula"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'['", "']'", "'!'", "'X'", "'G'", "'F'", "'Y'", "'Z'", 
			"'O'", "'H'", "'U'", "'V'", "'&'", "'|'", "'xnor'", "'xor'", "'<->'", 
			"'->'", "'='", "'!='", "'>'", "'>='", "'<'", "'<='", "'('", "')'", "'-'", 
			"'+'", "'*'", "'/'", "'mod'", null, null, "'TRUE'", "'FALSE'", "'count'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "WS", "INT_CONST", 
			"TRUE", "FALSE", "COUNT", "ID"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ltl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



	public ltlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode INT_CONST() { return getToken(ltlParser.INT_CONST, 0); }
		public TerminalNode TRUE() { return getToken(ltlParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(ltlParser.FALSE, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT_CONST) | (1L << TRUE) | (1L << FALSE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Composite_idContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(ltlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ltlParser.ID, i);
		}
		public List<TerminalNode> INT_CONST() { return getTokens(ltlParser.INT_CONST); }
		public TerminalNode INT_CONST(int i) {
			return getToken(ltlParser.INT_CONST, i);
		}
		public Composite_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_composite_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterComposite_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitComposite_id(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitComposite_id(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Composite_idContext composite_id() throws RecognitionException {
		Composite_idContext _localctx = new Composite_idContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_composite_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(ID);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(59);
				match(T__0);
				setState(60);
				match(ID);
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(66);
				match(T__1);
				setState(67);
				match(INT_CONST);
				setState(68);
				match(T__2);
				}
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unary_operator_signContext extends ParserRuleContext {
		public Unary_operator_signContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_operator_sign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterUnary_operator_sign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitUnary_operator_sign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitUnary_operator_sign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unary_operator_signContext unary_operator_sign() throws RecognitionException {
		Unary_operator_signContext _localctx = new Unary_operator_signContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_unary_operator_sign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binary_operator_sign5Context extends ParserRuleContext {
		public Binary_operator_sign5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator_sign5; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterBinary_operator_sign5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitBinary_operator_sign5(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitBinary_operator_sign5(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator_sign5Context binary_operator_sign5() throws RecognitionException {
		Binary_operator_sign5Context _localctx = new Binary_operator_sign5Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_binary_operator_sign5);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			_la = _input.LA(1);
			if ( !(_la==T__11 || _la==T__12) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binary_operator_sign4Context extends ParserRuleContext {
		public Binary_operator_sign4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator_sign4; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterBinary_operator_sign4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitBinary_operator_sign4(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitBinary_operator_sign4(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator_sign4Context binary_operator_sign4() throws RecognitionException {
		Binary_operator_sign4Context _localctx = new Binary_operator_sign4Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_binary_operator_sign4);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binary_operator_sign3Context extends ParserRuleContext {
		public Binary_operator_sign3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator_sign3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterBinary_operator_sign3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitBinary_operator_sign3(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitBinary_operator_sign3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator_sign3Context binary_operator_sign3() throws RecognitionException {
		Binary_operator_sign3Context _localctx = new Binary_operator_sign3Context(_ctx, getState());
		enterRule(_localctx, 10, RULE_binary_operator_sign3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binary_operator_sign2Context extends ParserRuleContext {
		public Binary_operator_sign2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator_sign2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterBinary_operator_sign2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitBinary_operator_sign2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitBinary_operator_sign2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator_sign2Context binary_operator_sign2() throws RecognitionException {
		Binary_operator_sign2Context _localctx = new Binary_operator_sign2Context(_ctx, getState());
		enterRule(_localctx, 12, RULE_binary_operator_sign2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binary_operator_sign1Context extends ParserRuleContext {
		public Binary_operator_sign1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator_sign1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterBinary_operator_sign1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitBinary_operator_sign1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitBinary_operator_sign1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator_sign1Context binary_operator_sign1() throws RecognitionException {
		Binary_operator_sign1Context _localctx = new Binary_operator_sign1Context(_ctx, getState());
		enterRule(_localctx, 14, RULE_binary_operator_sign1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(T__18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Comparison_operator_signContext extends ParserRuleContext {
		public Comparison_operator_signContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison_operator_sign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterComparison_operator_sign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitComparison_operator_sign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitComparison_operator_sign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Comparison_operator_signContext comparison_operator_sign() throws RecognitionException {
		Comparison_operator_signContext _localctx = new Comparison_operator_signContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_comparison_operator_sign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arithmetic_atomic_valueContext extends ParserRuleContext {
		public Node f;
		public ConstantContext constant;
		public Composite_idContext composite_id;
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public Composite_idContext composite_id() {
			return getRuleContext(Composite_idContext.class,0);
		}
		public Arithmetic_atomic_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic_atomic_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterArithmetic_atomic_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitArithmetic_atomic_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitArithmetic_atomic_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arithmetic_atomic_valueContext arithmetic_atomic_value() throws RecognitionException {
		Arithmetic_atomic_valueContext _localctx = new Arithmetic_atomic_valueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_arithmetic_atomic_value);
		try {
			setState(94);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_CONST:
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				((Arithmetic_atomic_valueContext)_localctx).constant = constant();
				 ((Arithmetic_atomic_valueContext)_localctx).f =  new IdNode((((Arithmetic_atomic_valueContext)_localctx).constant!=null?_input.getText(((Arithmetic_atomic_valueContext)_localctx).constant.start,((Arithmetic_atomic_valueContext)_localctx).constant.stop):null)); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				((Arithmetic_atomic_valueContext)_localctx).composite_id = composite_id();
				 ((Arithmetic_atomic_valueContext)_localctx).f =  new IdNode((((Arithmetic_atomic_valueContext)_localctx).composite_id!=null?_input.getText(((Arithmetic_atomic_valueContext)_localctx).composite_id.start,((Arithmetic_atomic_valueContext)_localctx).composite_id.stop):null)); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arithmetic_atomContext extends ParserRuleContext {
		public Node f;
		public Arithmetic_atomic_valueContext arithmetic_atomic_value;
		public Implies_arithmetic_expressionContext implies_arithmetic_expression;
		public Arithmetic_atomic_valueContext arithmetic_atomic_value() {
			return getRuleContext(Arithmetic_atomic_valueContext.class,0);
		}
		public Implies_arithmetic_expressionContext implies_arithmetic_expression() {
			return getRuleContext(Implies_arithmetic_expressionContext.class,0);
		}
		public Arithmetic_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterArithmetic_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitArithmetic_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitArithmetic_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arithmetic_atomContext arithmetic_atom() throws RecognitionException {
		Arithmetic_atomContext _localctx = new Arithmetic_atomContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_arithmetic_atom);
		try {
			setState(104);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_CONST:
			case TRUE:
			case FALSE:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(96);
				((Arithmetic_atomContext)_localctx).arithmetic_atomic_value = arithmetic_atomic_value();
				 ((Arithmetic_atomContext)_localctx).f =  ((Arithmetic_atomContext)_localctx).arithmetic_atomic_value.f; 
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				match(T__25);
				setState(100);
				((Arithmetic_atomContext)_localctx).implies_arithmetic_expression = implies_arithmetic_expression();
				setState(101);
				match(T__26);
				 ((Arithmetic_atomContext)_localctx).f =  ((Arithmetic_atomContext)_localctx).implies_arithmetic_expression.f; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arithmetic_expression3Context extends ParserRuleContext {
		public Node f;
		public Arithmetic_atomContext arithmetic_atom;
		public Arithmetic_expression3Context arithmetic_expression3;
		public Arithmetic_atomContext arithmetic_atom() {
			return getRuleContext(Arithmetic_atomContext.class,0);
		}
		public Arithmetic_expression3Context arithmetic_expression3() {
			return getRuleContext(Arithmetic_expression3Context.class,0);
		}
		public Arithmetic_expression3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic_expression3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterArithmetic_expression3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitArithmetic_expression3(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitArithmetic_expression3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arithmetic_expression3Context arithmetic_expression3() throws RecognitionException {
		Arithmetic_expression3Context _localctx = new Arithmetic_expression3Context(_ctx, getState());
		enterRule(_localctx, 22, RULE_arithmetic_expression3);
		try {
			setState(121);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__25:
			case INT_CONST:
			case TRUE:
			case FALSE:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				((Arithmetic_expression3Context)_localctx).arithmetic_atom = arithmetic_atom();
				 ((Arithmetic_expression3Context)_localctx).f =  ((Arithmetic_expression3Context)_localctx).arithmetic_atom.f; 
				}
				break;
			case T__3:
			case T__27:
			case T__28:
				enterOuterAlt(_localctx, 2);
				{
				 String op; 
				setState(116);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__27:
					{
					setState(110);
					match(T__27);
					 op = "-"; 
					}
					break;
				case T__28:
					{
					setState(112);
					match(T__28);
					 op = "+"; 
					}
					break;
				case T__3:
					{
					setState(114);
					match(T__3);
					 op = "!"; 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(118);
				((Arithmetic_expression3Context)_localctx).arithmetic_expression3 = arithmetic_expression3();
				 ((Arithmetic_expression3Context)_localctx).f =  new UnOpNode(((Arithmetic_expression3Context)_localctx).arithmetic_expression3.f, op); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arithmetic_expression2Context extends ParserRuleContext {
		public Node f;
		public Arithmetic_expression3Context f1;
		public Arithmetic_expression3Context f2;
		public List<Arithmetic_expression3Context> arithmetic_expression3() {
			return getRuleContexts(Arithmetic_expression3Context.class);
		}
		public Arithmetic_expression3Context arithmetic_expression3(int i) {
			return getRuleContext(Arithmetic_expression3Context.class,i);
		}
		public Arithmetic_expression2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic_expression2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterArithmetic_expression2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitArithmetic_expression2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitArithmetic_expression2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arithmetic_expression2Context arithmetic_expression2() throws RecognitionException {
		Arithmetic_expression2Context _localctx = new Arithmetic_expression2Context(_ctx, getState());
		enterRule(_localctx, 24, RULE_arithmetic_expression2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			((Arithmetic_expression2Context)_localctx).f1 = arithmetic_expression3();
			 ((Arithmetic_expression2Context)_localctx).f =  ((Arithmetic_expression2Context)_localctx).f1.f; String op; 
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31))) != 0)) {
				{
				{
				setState(131);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__29:
					{
					setState(125);
					match(T__29);
					 op = "*"; 
					}
					break;
				case T__30:
					{
					setState(127);
					match(T__30);
					 op = "/"; 
					}
					break;
				case T__31:
					{
					setState(129);
					match(T__31);
					 op = "mod"; 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(133);
				((Arithmetic_expression2Context)_localctx).f2 = arithmetic_expression3();
				 ((Arithmetic_expression2Context)_localctx).f =  new BinOpNode(_localctx.f, ((Arithmetic_expression2Context)_localctx).f2.f, op); 
				}
				}
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arithmetic_expression1Context extends ParserRuleContext {
		public Node f;
		public Arithmetic_expression2Context f1;
		public Arithmetic_expression2Context f2;
		public List<Arithmetic_expression2Context> arithmetic_expression2() {
			return getRuleContexts(Arithmetic_expression2Context.class);
		}
		public Arithmetic_expression2Context arithmetic_expression2(int i) {
			return getRuleContext(Arithmetic_expression2Context.class,i);
		}
		public Arithmetic_expression1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic_expression1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterArithmetic_expression1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitArithmetic_expression1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitArithmetic_expression1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arithmetic_expression1Context arithmetic_expression1() throws RecognitionException {
		Arithmetic_expression1Context _localctx = new Arithmetic_expression1Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_arithmetic_expression1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			((Arithmetic_expression1Context)_localctx).f1 = arithmetic_expression2();
			 ((Arithmetic_expression1Context)_localctx).f =  ((Arithmetic_expression1Context)_localctx).f1.f; String op; 
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__27 || _la==T__28) {
				{
				{
				setState(147);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__28:
					{
					setState(143);
					match(T__28);
					 op = "+"; 
					}
					break;
				case T__27:
					{
					setState(145);
					match(T__27);
					 op = "-"; 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(149);
				((Arithmetic_expression1Context)_localctx).f2 = arithmetic_expression2();
				 ((Arithmetic_expression1Context)_localctx).f =  new BinOpNode(_localctx.f, ((Arithmetic_expression1Context)_localctx).f2.f, op); 
				}
				}
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Comparison_expressionContext extends ParserRuleContext {
		public Node f;
		public Arithmetic_expression1Context f1;
		public Comparison_operator_signContext comparison_operator_sign;
		public Arithmetic_expression1Context f2;
		public List<Arithmetic_expression1Context> arithmetic_expression1() {
			return getRuleContexts(Arithmetic_expression1Context.class);
		}
		public Arithmetic_expression1Context arithmetic_expression1(int i) {
			return getRuleContext(Arithmetic_expression1Context.class,i);
		}
		public Comparison_operator_signContext comparison_operator_sign() {
			return getRuleContext(Comparison_operator_signContext.class,0);
		}
		public Comparison_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterComparison_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitComparison_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitComparison_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Comparison_expressionContext comparison_expression() throws RecognitionException {
		Comparison_expressionContext _localctx = new Comparison_expressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_comparison_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			((Comparison_expressionContext)_localctx).f1 = arithmetic_expression1();
			 ((Comparison_expressionContext)_localctx).f =  ((Comparison_expressionContext)_localctx).f1.f; 
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) {
				{
				setState(159);
				((Comparison_expressionContext)_localctx).comparison_operator_sign = comparison_operator_sign();
				setState(160);
				((Comparison_expressionContext)_localctx).f2 = arithmetic_expression1();
				 ((Comparison_expressionContext)_localctx).f =  new BinOpNode(_localctx.f, ((Comparison_expressionContext)_localctx).f2.f, (((Comparison_expressionContext)_localctx).comparison_operator_sign!=null?_input.getText(((Comparison_expressionContext)_localctx).comparison_operator_sign.start,((Comparison_expressionContext)_localctx).comparison_operator_sign.stop):null)); 
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class And_arithmetic_expressionContext extends ParserRuleContext {
		public Node f;
		public Comparison_expressionContext f1;
		public Comparison_expressionContext f2;
		public List<Comparison_expressionContext> comparison_expression() {
			return getRuleContexts(Comparison_expressionContext.class);
		}
		public Comparison_expressionContext comparison_expression(int i) {
			return getRuleContext(Comparison_expressionContext.class,i);
		}
		public And_arithmetic_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and_arithmetic_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterAnd_arithmetic_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitAnd_arithmetic_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitAnd_arithmetic_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final And_arithmetic_expressionContext and_arithmetic_expression() throws RecognitionException {
		And_arithmetic_expressionContext _localctx = new And_arithmetic_expressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_and_arithmetic_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			((And_arithmetic_expressionContext)_localctx).f1 = comparison_expression();
			 ((And_arithmetic_expressionContext)_localctx).f =  ((And_arithmetic_expressionContext)_localctx).f1.f; 
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(167);
				match(T__13);
				setState(168);
				((And_arithmetic_expressionContext)_localctx).f2 = comparison_expression();
				 ((And_arithmetic_expressionContext)_localctx).f =  new BinOpNode(_localctx.f, ((And_arithmetic_expressionContext)_localctx).f2.f, "&"); 
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Or_arithmetic_expressionContext extends ParserRuleContext {
		public Node f;
		public And_arithmetic_expressionContext f1;
		public And_arithmetic_expressionContext f2;
		public List<And_arithmetic_expressionContext> and_arithmetic_expression() {
			return getRuleContexts(And_arithmetic_expressionContext.class);
		}
		public And_arithmetic_expressionContext and_arithmetic_expression(int i) {
			return getRuleContext(And_arithmetic_expressionContext.class,i);
		}
		public Or_arithmetic_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or_arithmetic_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterOr_arithmetic_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitOr_arithmetic_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitOr_arithmetic_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Or_arithmetic_expressionContext or_arithmetic_expression() throws RecognitionException {
		Or_arithmetic_expressionContext _localctx = new Or_arithmetic_expressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_or_arithmetic_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			((Or_arithmetic_expressionContext)_localctx).f1 = and_arithmetic_expression();
			 ((Or_arithmetic_expressionContext)_localctx).f =  ((Or_arithmetic_expressionContext)_localctx).f1.f; String op; 
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) {
				{
				setState(181);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__14:
					{
					setState(175);
					match(T__14);
					 op = "|"; 
					}
					break;
				case T__16:
					{
					setState(177);
					match(T__16);
					 op = "xor"; 
					}
					break;
				case T__15:
					{
					setState(179);
					match(T__15);
					 op = "xnor"; 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(183);
				((Or_arithmetic_expressionContext)_localctx).f2 = and_arithmetic_expression();
				 ((Or_arithmetic_expressionContext)_localctx).f =  new BinOpNode(_localctx.f, ((Or_arithmetic_expressionContext)_localctx).f2.f, op); 
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Eq_arithmetic_expressionContext extends ParserRuleContext {
		public Node f;
		public Or_arithmetic_expressionContext f1;
		public Or_arithmetic_expressionContext f2;
		public List<Or_arithmetic_expressionContext> or_arithmetic_expression() {
			return getRuleContexts(Or_arithmetic_expressionContext.class);
		}
		public Or_arithmetic_expressionContext or_arithmetic_expression(int i) {
			return getRuleContext(Or_arithmetic_expressionContext.class,i);
		}
		public Eq_arithmetic_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eq_arithmetic_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterEq_arithmetic_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitEq_arithmetic_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitEq_arithmetic_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Eq_arithmetic_expressionContext eq_arithmetic_expression() throws RecognitionException {
		Eq_arithmetic_expressionContext _localctx = new Eq_arithmetic_expressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_eq_arithmetic_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			((Eq_arithmetic_expressionContext)_localctx).f1 = or_arithmetic_expression();
			 ((Eq_arithmetic_expressionContext)_localctx).f =  ((Eq_arithmetic_expressionContext)_localctx).f1.f; 
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__17) {
				{
				setState(190);
				match(T__17);
				setState(191);
				((Eq_arithmetic_expressionContext)_localctx).f2 = or_arithmetic_expression();
				 ((Eq_arithmetic_expressionContext)_localctx).f =  new BinOpNode(_localctx.f, ((Eq_arithmetic_expressionContext)_localctx).f2.f, "<->"); 
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Implies_arithmetic_expressionContext extends ParserRuleContext {
		public Node f;
		public Eq_arithmetic_expressionContext f1;
		public Implies_arithmetic_expressionContext f2;
		public Eq_arithmetic_expressionContext eq_arithmetic_expression() {
			return getRuleContext(Eq_arithmetic_expressionContext.class,0);
		}
		public Implies_arithmetic_expressionContext implies_arithmetic_expression() {
			return getRuleContext(Implies_arithmetic_expressionContext.class,0);
		}
		public Implies_arithmetic_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implies_arithmetic_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterImplies_arithmetic_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitImplies_arithmetic_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitImplies_arithmetic_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Implies_arithmetic_expressionContext implies_arithmetic_expression() throws RecognitionException {
		Implies_arithmetic_expressionContext _localctx = new Implies_arithmetic_expressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_implies_arithmetic_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			((Implies_arithmetic_expressionContext)_localctx).f1 = eq_arithmetic_expression();
			 ((Implies_arithmetic_expressionContext)_localctx).f =  ((Implies_arithmetic_expressionContext)_localctx).f1.f; 
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(198);
				match(T__18);
				setState(199);
				((Implies_arithmetic_expressionContext)_localctx).f2 = implies_arithmetic_expression();
				 ((Implies_arithmetic_expressionContext)_localctx).f =  new BinOpNode( _localctx.f, ((Implies_arithmetic_expressionContext)_localctx).f2.f, "->"); 
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropositionContext extends ParserRuleContext {
		public Node f;
		public Arithmetic_expression1Context f1;
		public Comparison_operator_signContext comparison_operator_sign;
		public Arithmetic_expression1Context f2;
		public Arithmetic_atomic_valueContext arithmetic_atomic_value;
		public Comparison_operator_signContext comparison_operator_sign() {
			return getRuleContext(Comparison_operator_signContext.class,0);
		}
		public List<Arithmetic_expression1Context> arithmetic_expression1() {
			return getRuleContexts(Arithmetic_expression1Context.class);
		}
		public Arithmetic_expression1Context arithmetic_expression1(int i) {
			return getRuleContext(Arithmetic_expression1Context.class,i);
		}
		public Arithmetic_atomic_valueContext arithmetic_atomic_value() {
			return getRuleContext(Arithmetic_atomic_valueContext.class,0);
		}
		public PropositionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proposition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterProposition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitProposition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitProposition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropositionContext proposition() throws RecognitionException {
		PropositionContext _localctx = new PropositionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_proposition);
		try {
			setState(212);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(204);
				((PropositionContext)_localctx).f1 = arithmetic_expression1();
				setState(205);
				((PropositionContext)_localctx).comparison_operator_sign = comparison_operator_sign();
				setState(206);
				((PropositionContext)_localctx).f2 = arithmetic_expression1();
				 ((PropositionContext)_localctx).f =  new BinOpNode(((PropositionContext)_localctx).f1.f, ((PropositionContext)_localctx).f2.f, (((PropositionContext)_localctx).comparison_operator_sign!=null?_input.getText(((PropositionContext)_localctx).comparison_operator_sign.start,((PropositionContext)_localctx).comparison_operator_sign.stop):null)); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(209);
				((PropositionContext)_localctx).arithmetic_atomic_value = arithmetic_atomic_value();
				 ((PropositionContext)_localctx).f =  ((PropositionContext)_localctx).arithmetic_atomic_value.f; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public Node f;
		public FormulaContext formula;
		public PropositionContext proposition;
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_atom);
		try {
			setState(222);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				match(T__25);
				setState(215);
				((AtomContext)_localctx).formula = formula();
				setState(216);
				match(T__26);
				 ((AtomContext)_localctx).f =  ((AtomContext)_localctx).formula.f; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
				((AtomContext)_localctx).proposition = proposition();
				 ((AtomContext)_localctx).f =  ((AtomContext)_localctx).proposition.f; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unary_operatorContext extends ParserRuleContext {
		public Node f;
		public AtomContext atom;
		public Unary_operator_signContext unary_operator_sign;
		public Unary_operatorContext unary_operator;
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public Unary_operator_signContext unary_operator_sign() {
			return getRuleContext(Unary_operator_signContext.class,0);
		}
		public Unary_operatorContext unary_operator() {
			return getRuleContext(Unary_operatorContext.class,0);
		}
		public Unary_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterUnary_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitUnary_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitUnary_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unary_operatorContext unary_operator() throws RecognitionException {
		Unary_operatorContext _localctx = new Unary_operatorContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_unary_operator);
		try {
			setState(231);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(224);
				((Unary_operatorContext)_localctx).atom = atom();
				 ((Unary_operatorContext)_localctx).f =  ((Unary_operatorContext)_localctx).atom.f; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(227);
				((Unary_operatorContext)_localctx).unary_operator_sign = unary_operator_sign();
				setState(228);
				((Unary_operatorContext)_localctx).unary_operator = unary_operator();
				 ((Unary_operatorContext)_localctx).f =  new UnOpNode(((Unary_operatorContext)_localctx).unary_operator.f, (((Unary_operatorContext)_localctx).unary_operator_sign!=null?_input.getText(((Unary_operatorContext)_localctx).unary_operator_sign.start,((Unary_operatorContext)_localctx).unary_operator_sign.stop):null)); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binary_operator5Context extends ParserRuleContext {
		public Node f;
		public Unary_operatorContext f1;
		public Binary_operator_sign5Context binary_operator_sign5;
		public Unary_operatorContext f2;
		public List<Unary_operatorContext> unary_operator() {
			return getRuleContexts(Unary_operatorContext.class);
		}
		public Unary_operatorContext unary_operator(int i) {
			return getRuleContext(Unary_operatorContext.class,i);
		}
		public List<Binary_operator_sign5Context> binary_operator_sign5() {
			return getRuleContexts(Binary_operator_sign5Context.class);
		}
		public Binary_operator_sign5Context binary_operator_sign5(int i) {
			return getRuleContext(Binary_operator_sign5Context.class,i);
		}
		public Binary_operator5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator5; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterBinary_operator5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitBinary_operator5(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitBinary_operator5(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator5Context binary_operator5() throws RecognitionException {
		Binary_operator5Context _localctx = new Binary_operator5Context(_ctx, getState());
		enterRule(_localctx, 44, RULE_binary_operator5);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			((Binary_operator5Context)_localctx).f1 = unary_operator();
			 ((Binary_operator5Context)_localctx).f =  ((Binary_operator5Context)_localctx).f1.f; 
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11 || _la==T__12) {
				{
				{
				setState(235);
				((Binary_operator5Context)_localctx).binary_operator_sign5 = binary_operator_sign5();
				setState(236);
				((Binary_operator5Context)_localctx).f2 = unary_operator();
				 ((Binary_operator5Context)_localctx).f =  new BinOpNode(_localctx.f, ((Binary_operator5Context)_localctx).f2.f, (((Binary_operator5Context)_localctx).binary_operator_sign5!=null?_input.getText(((Binary_operator5Context)_localctx).binary_operator_sign5.start,((Binary_operator5Context)_localctx).binary_operator_sign5.stop):null)); 
				}
				}
				setState(243);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binary_operator4Context extends ParserRuleContext {
		public Node f;
		public Binary_operator5Context f1;
		public Binary_operator_sign4Context binary_operator_sign4;
		public Binary_operator5Context f2;
		public List<Binary_operator5Context> binary_operator5() {
			return getRuleContexts(Binary_operator5Context.class);
		}
		public Binary_operator5Context binary_operator5(int i) {
			return getRuleContext(Binary_operator5Context.class,i);
		}
		public List<Binary_operator_sign4Context> binary_operator_sign4() {
			return getRuleContexts(Binary_operator_sign4Context.class);
		}
		public Binary_operator_sign4Context binary_operator_sign4(int i) {
			return getRuleContext(Binary_operator_sign4Context.class,i);
		}
		public Binary_operator4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator4; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterBinary_operator4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitBinary_operator4(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitBinary_operator4(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator4Context binary_operator4() throws RecognitionException {
		Binary_operator4Context _localctx = new Binary_operator4Context(_ctx, getState());
		enterRule(_localctx, 46, RULE_binary_operator4);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			((Binary_operator4Context)_localctx).f1 = binary_operator5();
			 ((Binary_operator4Context)_localctx).f =  ((Binary_operator4Context)_localctx).f1.f; 
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(246);
				((Binary_operator4Context)_localctx).binary_operator_sign4 = binary_operator_sign4();
				setState(247);
				((Binary_operator4Context)_localctx).f2 = binary_operator5();
				 ((Binary_operator4Context)_localctx).f =  new BinOpNode(_localctx.f, ((Binary_operator4Context)_localctx).f2.f,
						 (((Binary_operator4Context)_localctx).binary_operator_sign4!=null?_input.getText(((Binary_operator4Context)_localctx).binary_operator_sign4.start,((Binary_operator4Context)_localctx).binary_operator_sign4.stop):null));
				}
				}
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binary_operator3Context extends ParserRuleContext {
		public Node f;
		public Binary_operator4Context f1;
		public Binary_operator_sign3Context binary_operator_sign3;
		public Binary_operator4Context f2;
		public List<Binary_operator4Context> binary_operator4() {
			return getRuleContexts(Binary_operator4Context.class);
		}
		public Binary_operator4Context binary_operator4(int i) {
			return getRuleContext(Binary_operator4Context.class,i);
		}
		public List<Binary_operator_sign3Context> binary_operator_sign3() {
			return getRuleContexts(Binary_operator_sign3Context.class);
		}
		public Binary_operator_sign3Context binary_operator_sign3(int i) {
			return getRuleContext(Binary_operator_sign3Context.class,i);
		}
		public Binary_operator3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterBinary_operator3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitBinary_operator3(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitBinary_operator3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator3Context binary_operator3() throws RecognitionException {
		Binary_operator3Context _localctx = new Binary_operator3Context(_ctx, getState());
		enterRule(_localctx, 48, RULE_binary_operator3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			((Binary_operator3Context)_localctx).f1 = binary_operator4();
			 ((Binary_operator3Context)_localctx).f =  ((Binary_operator3Context)_localctx).f1.f; 
			setState(263);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) {
				{
				{
				setState(257);
				((Binary_operator3Context)_localctx).binary_operator_sign3 = binary_operator_sign3();
				setState(258);
				((Binary_operator3Context)_localctx).f2 = binary_operator4();
				 ((Binary_operator3Context)_localctx).f =  new BinOpNode(_localctx.f, ((Binary_operator3Context)_localctx).f2.f, (((Binary_operator3Context)_localctx).binary_operator_sign3!=null?_input.getText(((Binary_operator3Context)_localctx).binary_operator_sign3.start,((Binary_operator3Context)_localctx).binary_operator_sign3.stop):null)); 
				}
				}
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binary_operator2Context extends ParserRuleContext {
		public Node f;
		public Binary_operator3Context f1;
		public Binary_operator_sign2Context binary_operator_sign2;
		public Binary_operator3Context f2;
		public List<Binary_operator3Context> binary_operator3() {
			return getRuleContexts(Binary_operator3Context.class);
		}
		public Binary_operator3Context binary_operator3(int i) {
			return getRuleContext(Binary_operator3Context.class,i);
		}
		public List<Binary_operator_sign2Context> binary_operator_sign2() {
			return getRuleContexts(Binary_operator_sign2Context.class);
		}
		public Binary_operator_sign2Context binary_operator_sign2(int i) {
			return getRuleContext(Binary_operator_sign2Context.class,i);
		}
		public Binary_operator2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterBinary_operator2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitBinary_operator2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitBinary_operator2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator2Context binary_operator2() throws RecognitionException {
		Binary_operator2Context _localctx = new Binary_operator2Context(_ctx, getState());
		enterRule(_localctx, 50, RULE_binary_operator2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			((Binary_operator2Context)_localctx).f1 = binary_operator3();
			 ((Binary_operator2Context)_localctx).f =  ((Binary_operator2Context)_localctx).f1.f; 
			setState(274);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__17) {
				{
				{
				setState(268);
				((Binary_operator2Context)_localctx).binary_operator_sign2 = binary_operator_sign2();
				setState(269);
				((Binary_operator2Context)_localctx).f2 = binary_operator3();
				 ((Binary_operator2Context)_localctx).f =  new BinOpNode(_localctx.f, ((Binary_operator2Context)_localctx).f2.f, (((Binary_operator2Context)_localctx).binary_operator_sign2!=null?_input.getText(((Binary_operator2Context)_localctx).binary_operator_sign2.start,((Binary_operator2Context)_localctx).binary_operator_sign2.stop):null)); 
				}
				}
				setState(276);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binary_operator1Context extends ParserRuleContext {
		public Node f;
		public Binary_operator2Context f1;
		public Binary_operator_sign1Context binary_operator_sign1;
		public Binary_operator1Context f2;
		public Binary_operator2Context binary_operator2() {
			return getRuleContext(Binary_operator2Context.class,0);
		}
		public Binary_operator_sign1Context binary_operator_sign1() {
			return getRuleContext(Binary_operator_sign1Context.class,0);
		}
		public Binary_operator1Context binary_operator1() {
			return getRuleContext(Binary_operator1Context.class,0);
		}
		public Binary_operator1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterBinary_operator1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitBinary_operator1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitBinary_operator1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator1Context binary_operator1() throws RecognitionException {
		Binary_operator1Context _localctx = new Binary_operator1Context(_ctx, getState());
		enterRule(_localctx, 52, RULE_binary_operator1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			((Binary_operator1Context)_localctx).f1 = binary_operator2();
			 ((Binary_operator1Context)_localctx).f =  ((Binary_operator1Context)_localctx).f1.f; 
			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(279);
				((Binary_operator1Context)_localctx).binary_operator_sign1 = binary_operator_sign1();
				setState(280);
				((Binary_operator1Context)_localctx).f2 = binary_operator1();
				 ((Binary_operator1Context)_localctx).f =  new BinOpNode(_localctx.f, ((Binary_operator1Context)_localctx).f2.f, (((Binary_operator1Context)_localctx).binary_operator_sign1!=null?_input.getText(((Binary_operator1Context)_localctx).binary_operator_sign1.start,((Binary_operator1Context)_localctx).binary_operator_sign1.stop):null)); 
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormulaContext extends ParserRuleContext {
		public Node f;
		public Binary_operator1Context binary_operator1;
		public Binary_operator1Context binary_operator1() {
			return getRuleContext(Binary_operator1Context.class,0);
		}
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ltlListener ) ((ltlListener)listener).exitFormula(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ltlVisitor ) return ((ltlVisitor<? extends T>)visitor).visitFormula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			((FormulaContext)_localctx).binary_operator1 = binary_operator1();
			 ((FormulaContext)_localctx).f =  ((FormulaContext)_localctx).binary_operator1.f; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3(\u0123\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\3\3\3\3\3\7\3@\n\3"+
		"\f\3\16\3C\13\3\3\3\3\3\3\3\7\3H\n\3\f\3\16\3K\13\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\5"+
		"\13a\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\fk\n\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\5\rw\n\r\3\r\3\r\3\r\5\r|\n\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\5\16\u0086\n\16\3\16\3\16\3\16\7\16\u008b\n\16\f"+
		"\16\16\16\u008e\13\16\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0096\n\17\3"+
		"\17\3\17\3\17\7\17\u009b\n\17\f\17\16\17\u009e\13\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\5\20\u00a6\n\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00ae"+
		"\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00b8\n\22\3\22\3\22"+
		"\3\22\5\22\u00bd\n\22\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00c5\n\23\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\5\24\u00cd\n\24\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\5\25\u00d7\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\5\26\u00e1\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u00ea\n\27\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\7\30\u00f2\n\30\f\30\16\30\u00f5\13\30\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\7\31\u00fd\n\31\f\31\16\31\u0100\13\31\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\7\32\u0108\n\32\f\32\16\32\u010b\13\32\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\7\33\u0113\n\33\f\33\16\33\u0116\13\33\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\5\34\u011e\n\34\3\35\3\35\3\35\3\35\2\2\36"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668\2\7\3\2"+
		"$&\3\2\6\r\3\2\16\17\3\2\21\23\3\2\26\33\2\u0121\2:\3\2\2\2\4<\3\2\2\2"+
		"\6L\3\2\2\2\bN\3\2\2\2\nP\3\2\2\2\fR\3\2\2\2\16T\3\2\2\2\20V\3\2\2\2\22"+
		"X\3\2\2\2\24`\3\2\2\2\26j\3\2\2\2\30{\3\2\2\2\32}\3\2\2\2\34\u008f\3\2"+
		"\2\2\36\u009f\3\2\2\2 \u00a7\3\2\2\2\"\u00af\3\2\2\2$\u00be\3\2\2\2&\u00c6"+
		"\3\2\2\2(\u00d6\3\2\2\2*\u00e0\3\2\2\2,\u00e9\3\2\2\2.\u00eb\3\2\2\2\60"+
		"\u00f6\3\2\2\2\62\u0101\3\2\2\2\64\u010c\3\2\2\2\66\u0117\3\2\2\28\u011f"+
		"\3\2\2\2:;\t\2\2\2;\3\3\2\2\2<A\7(\2\2=>\7\3\2\2>@\7(\2\2?=\3\2\2\2@C"+
		"\3\2\2\2A?\3\2\2\2AB\3\2\2\2BI\3\2\2\2CA\3\2\2\2DE\7\4\2\2EF\7$\2\2FH"+
		"\7\5\2\2GD\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\5\3\2\2\2KI\3\2\2\2"+
		"LM\t\3\2\2M\7\3\2\2\2NO\t\4\2\2O\t\3\2\2\2PQ\7\20\2\2Q\13\3\2\2\2RS\t"+
		"\5\2\2S\r\3\2\2\2TU\7\24\2\2U\17\3\2\2\2VW\7\25\2\2W\21\3\2\2\2XY\t\6"+
		"\2\2Y\23\3\2\2\2Z[\5\2\2\2[\\\b\13\1\2\\a\3\2\2\2]^\5\4\3\2^_\b\13\1\2"+
		"_a\3\2\2\2`Z\3\2\2\2`]\3\2\2\2a\25\3\2\2\2bc\5\24\13\2cd\b\f\1\2dk\3\2"+
		"\2\2ef\7\34\2\2fg\5&\24\2gh\7\35\2\2hi\b\f\1\2ik\3\2\2\2jb\3\2\2\2je\3"+
		"\2\2\2k\27\3\2\2\2lm\5\26\f\2mn\b\r\1\2n|\3\2\2\2ov\b\r\1\2pq\7\36\2\2"+
		"qw\b\r\1\2rs\7\37\2\2sw\b\r\1\2tu\7\6\2\2uw\b\r\1\2vp\3\2\2\2vr\3\2\2"+
		"\2vt\3\2\2\2wx\3\2\2\2xy\5\30\r\2yz\b\r\1\2z|\3\2\2\2{l\3\2\2\2{o\3\2"+
		"\2\2|\31\3\2\2\2}~\5\30\r\2~\u008c\b\16\1\2\177\u0080\7 \2\2\u0080\u0086"+
		"\b\16\1\2\u0081\u0082\7!\2\2\u0082\u0086\b\16\1\2\u0083\u0084\7\"\2\2"+
		"\u0084\u0086\b\16\1\2\u0085\177\3\2\2\2\u0085\u0081\3\2\2\2\u0085\u0083"+
		"\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\5\30\r\2\u0088\u0089\b\16\1\2"+
		"\u0089\u008b\3\2\2\2\u008a\u0085\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a"+
		"\3\2\2\2\u008c\u008d\3\2\2\2\u008d\33\3\2\2\2\u008e\u008c\3\2\2\2\u008f"+
		"\u0090\5\32\16\2\u0090\u009c\b\17\1\2\u0091\u0092\7\37\2\2\u0092\u0096"+
		"\b\17\1\2\u0093\u0094\7\36\2\2\u0094\u0096\b\17\1\2\u0095\u0091\3\2\2"+
		"\2\u0095\u0093\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\5\32\16\2\u0098"+
		"\u0099\b\17\1\2\u0099\u009b\3\2\2\2\u009a\u0095\3\2\2\2\u009b\u009e\3"+
		"\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\35\3\2\2\2\u009e"+
		"\u009c\3\2\2\2\u009f\u00a0\5\34\17\2\u00a0\u00a5\b\20\1\2\u00a1\u00a2"+
		"\5\22\n\2\u00a2\u00a3\5\34\17\2\u00a3\u00a4\b\20\1\2\u00a4\u00a6\3\2\2"+
		"\2\u00a5\u00a1\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\37\3\2\2\2\u00a7\u00a8"+
		"\5\36\20\2\u00a8\u00ad\b\21\1\2\u00a9\u00aa\7\20\2\2\u00aa\u00ab\5\36"+
		"\20\2\u00ab\u00ac\b\21\1\2\u00ac\u00ae\3\2\2\2\u00ad\u00a9\3\2\2\2\u00ad"+
		"\u00ae\3\2\2\2\u00ae!\3\2\2\2\u00af\u00b0\5 \21\2\u00b0\u00bc\b\22\1\2"+
		"\u00b1\u00b2\7\21\2\2\u00b2\u00b8\b\22\1\2\u00b3\u00b4\7\23\2\2\u00b4"+
		"\u00b8\b\22\1\2\u00b5\u00b6\7\22\2\2\u00b6\u00b8\b\22\1\2\u00b7\u00b1"+
		"\3\2\2\2\u00b7\u00b3\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9"+
		"\u00ba\5 \21\2\u00ba\u00bb\b\22\1\2\u00bb\u00bd\3\2\2\2\u00bc\u00b7\3"+
		"\2\2\2\u00bc\u00bd\3\2\2\2\u00bd#\3\2\2\2\u00be\u00bf\5\"\22\2\u00bf\u00c4"+
		"\b\23\1\2\u00c0\u00c1\7\24\2\2\u00c1\u00c2\5\"\22\2\u00c2\u00c3\b\23\1"+
		"\2\u00c3\u00c5\3\2\2\2\u00c4\u00c0\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5%"+
		"\3\2\2\2\u00c6\u00c7\5$\23\2\u00c7\u00cc\b\24\1\2\u00c8\u00c9\7\25\2\2"+
		"\u00c9\u00ca\5&\24\2\u00ca\u00cb\b\24\1\2\u00cb\u00cd\3\2\2\2\u00cc\u00c8"+
		"\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\'\3\2\2\2\u00ce\u00cf\5\34\17\2\u00cf"+
		"\u00d0\5\22\n\2\u00d0\u00d1\5\34\17\2\u00d1\u00d2\b\25\1\2\u00d2\u00d7"+
		"\3\2\2\2\u00d3\u00d4\5\24\13\2\u00d4\u00d5\b\25\1\2\u00d5\u00d7\3\2\2"+
		"\2\u00d6\u00ce\3\2\2\2\u00d6\u00d3\3\2\2\2\u00d7)\3\2\2\2\u00d8\u00d9"+
		"\7\34\2\2\u00d9\u00da\58\35\2\u00da\u00db\7\35\2\2\u00db\u00dc\b\26\1"+
		"\2\u00dc\u00e1\3\2\2\2\u00dd\u00de\5(\25\2\u00de\u00df\b\26\1\2\u00df"+
		"\u00e1\3\2\2\2\u00e0\u00d8\3\2\2\2\u00e0\u00dd\3\2\2\2\u00e1+\3\2\2\2"+
		"\u00e2\u00e3\5*\26\2\u00e3\u00e4\b\27\1\2\u00e4\u00ea\3\2\2\2\u00e5\u00e6"+
		"\5\6\4\2\u00e6\u00e7\5,\27\2\u00e7\u00e8\b\27\1\2\u00e8\u00ea\3\2\2\2"+
		"\u00e9\u00e2\3\2\2\2\u00e9\u00e5\3\2\2\2\u00ea-\3\2\2\2\u00eb\u00ec\5"+
		",\27\2\u00ec\u00f3\b\30\1\2\u00ed\u00ee\5\b\5\2\u00ee\u00ef\5,\27\2\u00ef"+
		"\u00f0\b\30\1\2\u00f0\u00f2\3\2\2\2\u00f1\u00ed\3\2\2\2\u00f2\u00f5\3"+
		"\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4/\3\2\2\2\u00f5\u00f3"+
		"\3\2\2\2\u00f6\u00f7\5.\30\2\u00f7\u00fe\b\31\1\2\u00f8\u00f9\5\n\6\2"+
		"\u00f9\u00fa\5.\30\2\u00fa\u00fb\b\31\1\2\u00fb\u00fd\3\2\2\2\u00fc\u00f8"+
		"\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff"+
		"\61\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0102\5\60\31\2\u0102\u0109\b\32"+
		"\1\2\u0103\u0104\5\f\7\2\u0104\u0105\5\60\31\2\u0105\u0106\b\32\1\2\u0106"+
		"\u0108\3\2\2\2\u0107\u0103\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2"+
		"\2\2\u0109\u010a\3\2\2\2\u010a\63\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010d"+
		"\5\62\32\2\u010d\u0114\b\33\1\2\u010e\u010f\5\16\b\2\u010f\u0110\5\62"+
		"\32\2\u0110\u0111\b\33\1\2\u0111\u0113\3\2\2\2\u0112\u010e\3\2\2\2\u0113"+
		"\u0116\3\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115\65\3\2\2"+
		"\2\u0116\u0114\3\2\2\2\u0117\u0118\5\64\33\2\u0118\u011d\b\34\1\2\u0119"+
		"\u011a\5\20\t\2\u011a\u011b\5\66\34\2\u011b\u011c\b\34\1\2\u011c\u011e"+
		"\3\2\2\2\u011d\u0119\3\2\2\2\u011d\u011e\3\2\2\2\u011e\67\3\2\2\2\u011f"+
		"\u0120\5\66\34\2\u0120\u0121\b\35\1\2\u01219\3\2\2\2\32AI`jv{\u0085\u008c"+
		"\u0095\u009c\u00a5\u00ad\u00b7\u00bc\u00c4\u00cc\u00d6\u00e0\u00e9\u00f3"+
		"\u00fe\u0109\u0114\u011d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}