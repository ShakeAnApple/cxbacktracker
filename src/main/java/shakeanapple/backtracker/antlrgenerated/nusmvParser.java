// Generated from /home/buzhinsky/repos/cxbacktracker/nusmv.g4 by ANTLR 4.6
package shakeanapple.backtracker.antlrgenerated;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import shakeanapple.backtracker.nusmvparsing.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class nusmvParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		WS=32, INT_CONST=33, TRUE=34, FALSE=35, INIT=36, NEXT=37, MODULE=38, ASSIGN=39, 
		VAR=40, BOOLEAN=41, ID=42;
	public static final int
		RULE_constant = 0, RULE_composite_id = 1, RULE_unary_operator_sign = 2, 
		RULE_binary_operator_sign4 = 3, RULE_binary_operator_sign3 = 4, RULE_binary_operator_sign2 = 5, 
		RULE_binary_operator_sign1 = 6, RULE_binary_operator_sign5 = 7, RULE_type = 8, 
		RULE_atom = 9, RULE_unary_operator = 10, RULE_binary_operator7 = 11, RULE_binary_operator6 = 12, 
		RULE_binary_operator5 = 13, RULE_binary_operator4 = 14, RULE_binary_operator3 = 15, 
		RULE_ternary_operator = 16, RULE_binary_operator2 = 17, RULE_binary_operator1 = 18, 
		RULE_assignment = 19, RULE_var_declaration = 20, RULE_module = 21;
	public static final String[] ruleNames = {
		"constant", "composite_id", "unary_operator_sign", "binary_operator_sign4", 
		"binary_operator_sign3", "binary_operator_sign2", "binary_operator_sign1", 
		"binary_operator_sign5", "type", "atom", "unary_operator", "binary_operator7", 
		"binary_operator6", "binary_operator5", "binary_operator4", "binary_operator3", 
		"ternary_operator", "binary_operator2", "binary_operator1", "assignment", 
		"var_declaration", "module"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'.'", "'['", "']'", "'!'", "'-'", "'&'", "'|'", "'xnor'", "'xor'", 
		"'<->'", "'->'", "'='", "'!='", "'>'", "'>='", "'<'", "'<='", "'..'", 
		"'{'", "','", "'}'", "'('", "')'", "'*'", "'/'", "'mod'", "'+'", "'?'", 
		"':'", "':='", "';'", null, null, "'TRUE'", "'FALSE'", "'init'", "'next'", 
		"'MODULE'", "'ASSIGN'", "'VAR'", "'boolean'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "WS", "INT_CONST", "TRUE", 
		"FALSE", "INIT", "NEXT", "MODULE", "ASSIGN", "VAR", "BOOLEAN", "ID"
	};
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
	public String getGrammarFileName() { return "nusmv.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



	public nusmvParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode INT_CONST() { return getToken(nusmvParser.INT_CONST, 0); }
		public TerminalNode TRUE() { return getToken(nusmvParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(nusmvParser.FALSE, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitConstant(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
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
		public List<TerminalNode> ID() { return getTokens(nusmvParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(nusmvParser.ID, i);
		}
		public List<TerminalNode> INT_CONST() { return getTokens(nusmvParser.INT_CONST); }
		public TerminalNode INT_CONST(int i) {
			return getToken(nusmvParser.INT_CONST, i);
		}
		public Composite_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_composite_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterComposite_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitComposite_id(this);
		}
	}

	public final Composite_idContext composite_id() throws RecognitionException {
		Composite_idContext _localctx = new Composite_idContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_composite_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(ID);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(47);
				match(T__0);
				setState(48);
				match(ID);
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(54);
				match(T__1);
				setState(55);
				match(INT_CONST);
				setState(56);
				match(T__2);
				}
				}
				setState(61);
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
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterUnary_operator_sign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitUnary_operator_sign(this);
		}
	}

	public final Unary_operator_signContext unary_operator_sign() throws RecognitionException {
		Unary_operator_signContext _localctx = new Unary_operator_signContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_unary_operator_sign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==T__4) ) {
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
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterBinary_operator_sign4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitBinary_operator_sign4(this);
		}
	}

	public final Binary_operator_sign4Context binary_operator_sign4() throws RecognitionException {
		Binary_operator_sign4Context _localctx = new Binary_operator_sign4Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_binary_operator_sign4);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__5);
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
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterBinary_operator_sign3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitBinary_operator_sign3(this);
		}
	}

	public final Binary_operator_sign3Context binary_operator_sign3() throws RecognitionException {
		Binary_operator_sign3Context _localctx = new Binary_operator_sign3Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_binary_operator_sign3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8))) != 0)) ) {
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
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterBinary_operator_sign2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitBinary_operator_sign2(this);
		}
	}

	public final Binary_operator_sign2Context binary_operator_sign2() throws RecognitionException {
		Binary_operator_sign2Context _localctx = new Binary_operator_sign2Context(_ctx, getState());
		enterRule(_localctx, 10, RULE_binary_operator_sign2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(T__9);
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
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterBinary_operator_sign1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitBinary_operator_sign1(this);
		}
	}

	public final Binary_operator_sign1Context binary_operator_sign1() throws RecognitionException {
		Binary_operator_sign1Context _localctx = new Binary_operator_sign1Context(_ctx, getState());
		enterRule(_localctx, 12, RULE_binary_operator_sign1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(T__10);
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
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterBinary_operator_sign5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitBinary_operator_sign5(this);
		}
	}

	public final Binary_operator_sign5Context binary_operator_sign5() throws RecognitionException {
		Binary_operator_sign5Context _localctx = new Binary_operator_sign5Context(_ctx, getState());
		enterRule(_localctx, 14, RULE_binary_operator_sign5);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) ) {
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(nusmvParser.BOOLEAN, 0); }
		public List<TerminalNode> INT_CONST() { return getTokens(nusmvParser.INT_CONST); }
		public TerminalNode INT_CONST(int i) {
			return getToken(nusmvParser.INT_CONST, i);
		}
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_type);
		int _la;
		try {
			setState(89);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				match(BOOLEAN);
				}
				break;
			case INT_CONST:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(75);
				match(INT_CONST);
				setState(76);
				match(T__17);
				setState(77);
				match(INT_CONST);
				}
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(78);
				match(T__18);
				setState(79);
				constant();
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__19) {
					{
					{
					setState(80);
					match(T__19);
					setState(81);
					constant();
					}
					}
					setState(86);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(87);
				match(T__20);
				}
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

	public static class AtomContext extends ParserRuleContext {
		public Expression f;
		public Binary_operator1Context inside;
		public ConstantContext constant;
		public Composite_idContext composite_id;
		public Binary_operator1Context binary_operator1() {
			return getRuleContext(Binary_operator1Context.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public Composite_idContext composite_id() {
			return getRuleContext(Composite_idContext.class,0);
		}
		public TerminalNode NEXT() { return getToken(nusmvParser.NEXT, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_atom);
		try {
			setState(108);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				match(T__21);
				setState(92);
				((AtomContext)_localctx).inside = binary_operator1();
				setState(93);
				match(T__22);
				 ((AtomContext)_localctx).f =  ((AtomContext)_localctx).inside.f; 
				}
				break;
			case INT_CONST:
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				((AtomContext)_localctx).constant = constant();
				 ((AtomContext)_localctx).f =  new Constant((((AtomContext)_localctx).constant!=null?_input.getText(((AtomContext)_localctx).constant.start,((AtomContext)_localctx).constant.stop):null)); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				((AtomContext)_localctx).composite_id = composite_id();
				 ((AtomContext)_localctx).f =  new Variable((((AtomContext)_localctx).composite_id!=null?_input.getText(((AtomContext)_localctx).composite_id.start,((AtomContext)_localctx).composite_id.stop):null), Variable.ReferenceType.CURRENT); 
				}
				break;
			case NEXT:
				enterOuterAlt(_localctx, 4);
				{
				setState(102);
				match(NEXT);
				setState(103);
				match(T__21);
				setState(104);
				((AtomContext)_localctx).composite_id = composite_id();
				setState(105);
				match(T__22);
				 ((AtomContext)_localctx).f =  new Variable((((AtomContext)_localctx).composite_id!=null?_input.getText(((AtomContext)_localctx).composite_id.start,((AtomContext)_localctx).composite_id.stop):null), Variable.ReferenceType.NEXT); 
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

	public static class Unary_operatorContext extends ParserRuleContext {
		public Expression f;
		public AtomContext atom;
		public Unary_operator_signContext unary_operator_sign;
		public Unary_operatorContext inside;
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
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterUnary_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitUnary_operator(this);
		}
	}

	public final Unary_operatorContext unary_operator() throws RecognitionException {
		Unary_operatorContext _localctx = new Unary_operatorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_unary_operator);
		try {
			setState(117);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
			case INT_CONST:
			case TRUE:
			case FALSE:
			case NEXT:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				((Unary_operatorContext)_localctx).atom = atom();
				 ((Unary_operatorContext)_localctx).f =  ((Unary_operatorContext)_localctx).atom.f; 
				}
				break;
			case T__3:
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				((Unary_operatorContext)_localctx).unary_operator_sign = unary_operator_sign();
				setState(114);
				((Unary_operatorContext)_localctx).inside = unary_operator();
				 ((Unary_operatorContext)_localctx).f =  new UnaryOperator((((Unary_operatorContext)_localctx).unary_operator_sign!=null?_input.getText(((Unary_operatorContext)_localctx).unary_operator_sign.start,((Unary_operatorContext)_localctx).unary_operator_sign.stop):null), ((Unary_operatorContext)_localctx).inside.f); 
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

	public static class Binary_operator7Context extends ParserRuleContext {
		public Expression f;
		public Unary_operatorContext f1;
		public Unary_operatorContext f2;
		public List<Unary_operatorContext> unary_operator() {
			return getRuleContexts(Unary_operatorContext.class);
		}
		public Unary_operatorContext unary_operator(int i) {
			return getRuleContext(Unary_operatorContext.class,i);
		}
		public Binary_operator7Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator7; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterBinary_operator7(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitBinary_operator7(this);
		}
	}

	public final Binary_operator7Context binary_operator7() throws RecognitionException {
		Binary_operator7Context _localctx = new Binary_operator7Context(_ctx, getState());
		enterRule(_localctx, 22, RULE_binary_operator7);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			((Binary_operator7Context)_localctx).f1 = unary_operator();
			 ((Binary_operator7Context)_localctx).f =  ((Binary_operator7Context)_localctx).f1.f; String op; 
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__24) | (1L << T__25))) != 0)) {
				{
				{
				setState(127);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__23:
					{
					setState(121);
					match(T__23);
					 op = "*"; 
					}
					break;
				case T__24:
					{
					setState(123);
					match(T__24);
					 op = "/"; 
					}
					break;
				case T__25:
					{
					setState(125);
					match(T__25);
					 op = "mod"; 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(129);
				((Binary_operator7Context)_localctx).f2 = unary_operator();
				 ((Binary_operator7Context)_localctx).f =  new BinaryOperator(op, _localctx.f, ((Binary_operator7Context)_localctx).f2.f); 
				}
				}
				setState(136);
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

	public static class Binary_operator6Context extends ParserRuleContext {
		public Expression f;
		public Binary_operator7Context f1;
		public Binary_operator7Context f2;
		public List<Binary_operator7Context> binary_operator7() {
			return getRuleContexts(Binary_operator7Context.class);
		}
		public Binary_operator7Context binary_operator7(int i) {
			return getRuleContext(Binary_operator7Context.class,i);
		}
		public Binary_operator6Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator6; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterBinary_operator6(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitBinary_operator6(this);
		}
	}

	public final Binary_operator6Context binary_operator6() throws RecognitionException {
		Binary_operator6Context _localctx = new Binary_operator6Context(_ctx, getState());
		enterRule(_localctx, 24, RULE_binary_operator6);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			((Binary_operator6Context)_localctx).f1 = binary_operator7();
			 ((Binary_operator6Context)_localctx).f =  ((Binary_operator6Context)_localctx).f1.f; String op; 
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4 || _la==T__26) {
				{
				{
				setState(143);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__26:
					{
					setState(139);
					match(T__26);
					 op = "+"; 
					}
					break;
				case T__4:
					{
					setState(141);
					match(T__4);
					 op = "-"; 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(145);
				((Binary_operator6Context)_localctx).f2 = binary_operator7();
				 ((Binary_operator6Context)_localctx).f =  new BinaryOperator(op, _localctx.f, ((Binary_operator6Context)_localctx).f2.f); 
				}
				}
				setState(152);
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

	public static class Binary_operator5Context extends ParserRuleContext {
		public Expression f;
		public Binary_operator6Context f1;
		public Binary_operator_sign5Context inside;
		public Binary_operator6Context f2;
		public List<Binary_operator6Context> binary_operator6() {
			return getRuleContexts(Binary_operator6Context.class);
		}
		public Binary_operator6Context binary_operator6(int i) {
			return getRuleContext(Binary_operator6Context.class,i);
		}
		public Binary_operator_sign5Context binary_operator_sign5() {
			return getRuleContext(Binary_operator_sign5Context.class,0);
		}
		public Binary_operator5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator5; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterBinary_operator5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitBinary_operator5(this);
		}
	}

	public final Binary_operator5Context binary_operator5() throws RecognitionException {
		Binary_operator5Context _localctx = new Binary_operator5Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_binary_operator5);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			((Binary_operator5Context)_localctx).f1 = binary_operator6();
			 ((Binary_operator5Context)_localctx).f =  ((Binary_operator5Context)_localctx).f1.f; 
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) {
				{
				setState(155);
				((Binary_operator5Context)_localctx).inside = binary_operator_sign5();
				setState(156);
				((Binary_operator5Context)_localctx).f2 = binary_operator6();
				 ((Binary_operator5Context)_localctx).f =  new BinaryOperator((((Binary_operator5Context)_localctx).inside!=null?_input.getText(((Binary_operator5Context)_localctx).inside.start,((Binary_operator5Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator5Context)_localctx).f2.f); 
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

	public static class Binary_operator4Context extends ParserRuleContext {
		public Expression f;
		public Binary_operator5Context f1;
		public Binary_operator_sign4Context inside;
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
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterBinary_operator4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitBinary_operator4(this);
		}
	}

	public final Binary_operator4Context binary_operator4() throws RecognitionException {
		Binary_operator4Context _localctx = new Binary_operator4Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_binary_operator4);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			((Binary_operator4Context)_localctx).f1 = binary_operator5();
			 ((Binary_operator4Context)_localctx).f =  ((Binary_operator4Context)_localctx).f1.f; 
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(163);
				((Binary_operator4Context)_localctx).inside = binary_operator_sign4();
				setState(164);
				((Binary_operator4Context)_localctx).f2 = binary_operator5();
				 ((Binary_operator4Context)_localctx).f =  new BinaryOperator((((Binary_operator4Context)_localctx).inside!=null?_input.getText(((Binary_operator4Context)_localctx).inside.start,((Binary_operator4Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator4Context)_localctx).f2.f); 
				}
				}
				setState(171);
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
		public Expression f;
		public Binary_operator4Context f1;
		public Binary_operator_sign3Context inside;
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
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterBinary_operator3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitBinary_operator3(this);
		}
	}

	public final Binary_operator3Context binary_operator3() throws RecognitionException {
		Binary_operator3Context _localctx = new Binary_operator3Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_binary_operator3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			((Binary_operator3Context)_localctx).f1 = binary_operator4();
			 ((Binary_operator3Context)_localctx).f =  ((Binary_operator3Context)_localctx).f1.f; 
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8))) != 0)) {
				{
				{
				setState(174);
				((Binary_operator3Context)_localctx).inside = binary_operator_sign3();
				setState(175);
				((Binary_operator3Context)_localctx).f2 = binary_operator4();
				 ((Binary_operator3Context)_localctx).f =  new BinaryOperator((((Binary_operator3Context)_localctx).inside!=null?_input.getText(((Binary_operator3Context)_localctx).inside.start,((Binary_operator3Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator3Context)_localctx).f2.f); 
				}
				}
				setState(182);
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

	public static class Ternary_operatorContext extends ParserRuleContext {
		public Expression f;
		public Binary_operator3Context f1;
		public Binary_operator3Context f2;
		public Binary_operator3Context f3;
		public List<Binary_operator3Context> binary_operator3() {
			return getRuleContexts(Binary_operator3Context.class);
		}
		public Binary_operator3Context binary_operator3(int i) {
			return getRuleContext(Binary_operator3Context.class,i);
		}
		public Ternary_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ternary_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterTernary_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitTernary_operator(this);
		}
	}

	public final Ternary_operatorContext ternary_operator() throws RecognitionException {
		Ternary_operatorContext _localctx = new Ternary_operatorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_ternary_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			((Ternary_operatorContext)_localctx).f1 = binary_operator3();
			 ((Ternary_operatorContext)_localctx).f =  ((Ternary_operatorContext)_localctx).f1.f; 
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(185);
				match(T__27);
				setState(186);
				((Ternary_operatorContext)_localctx).f2 = binary_operator3();
				setState(187);
				match(T__28);
				setState(188);
				((Ternary_operatorContext)_localctx).f3 = binary_operator3();
				 ((Ternary_operatorContext)_localctx).f =  new TernaryOperator(((Ternary_operatorContext)_localctx).f1.f, ((Ternary_operatorContext)_localctx).f2.f, ((Ternary_operatorContext)_localctx).f3.f); 
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

	public static class Binary_operator2Context extends ParserRuleContext {
		public Expression f;
		public Binary_operator3Context f1;
		public Binary_operator_sign2Context inside;
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
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterBinary_operator2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitBinary_operator2(this);
		}
	}

	public final Binary_operator2Context binary_operator2() throws RecognitionException {
		Binary_operator2Context _localctx = new Binary_operator2Context(_ctx, getState());
		enterRule(_localctx, 34, RULE_binary_operator2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			((Binary_operator2Context)_localctx).f1 = binary_operator3();
			 ((Binary_operator2Context)_localctx).f =  ((Binary_operator2Context)_localctx).f1.f; 
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(195);
				((Binary_operator2Context)_localctx).inside = binary_operator_sign2();
				setState(196);
				((Binary_operator2Context)_localctx).f2 = binary_operator3();
				 ((Binary_operator2Context)_localctx).f =  new BinaryOperator((((Binary_operator2Context)_localctx).inside!=null?_input.getText(((Binary_operator2Context)_localctx).inside.start,((Binary_operator2Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator2Context)_localctx).f2.f); 
				}
				}
				setState(203);
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
		public Expression f;
		public Binary_operator2Context f1;
		public Binary_operator_sign1Context inside;
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
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterBinary_operator1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitBinary_operator1(this);
		}
	}

	public final Binary_operator1Context binary_operator1() throws RecognitionException {
		Binary_operator1Context _localctx = new Binary_operator1Context(_ctx, getState());
		enterRule(_localctx, 36, RULE_binary_operator1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			((Binary_operator1Context)_localctx).f1 = binary_operator2();
			 ((Binary_operator1Context)_localctx).f =  ((Binary_operator1Context)_localctx).f1.f; 
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(206);
				((Binary_operator1Context)_localctx).inside = binary_operator_sign1();
				setState(207);
				((Binary_operator1Context)_localctx).f2 = binary_operator1();
				 ((Binary_operator1Context)_localctx).f =  new BinaryOperator((((Binary_operator1Context)_localctx).inside!=null?_input.getText(((Binary_operator1Context)_localctx).inside.start,((Binary_operator1Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator1Context)_localctx).f2.f); 
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

	public static class AssignmentContext extends ParserRuleContext {
		public Assignment a;
		public Composite_idContext left;
		public Binary_operator1Context right;
		public Composite_idContext composite_id() {
			return getRuleContext(Composite_idContext.class,0);
		}
		public Binary_operator1Context binary_operator1() {
			return getRuleContext(Binary_operator1Context.class,0);
		}
		public TerminalNode INIT() { return getToken(nusmvParser.INIT, 0); }
		public TerminalNode NEXT() { return getToken(nusmvParser.NEXT, 0); }
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 Assignment.Type type; 
			setState(217);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INIT:
				{
				setState(213);
				match(INIT);
				 type = Assignment.Type.INIT; 
				}
				break;
			case NEXT:
				{
				setState(215);
				match(NEXT);
				 type = Assignment.Type.NEXT; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(219);
			match(T__21);
			setState(220);
			((AssignmentContext)_localctx).left = composite_id();
			setState(221);
			match(T__22);
			setState(222);
			match(T__29);
			setState(223);
			((AssignmentContext)_localctx).right = binary_operator1();
			setState(224);
			match(T__30);
			 ((AssignmentContext)_localctx).a =  new Assignment(type, new Variable((((AssignmentContext)_localctx).left!=null?_input.getText(((AssignmentContext)_localctx).left.start,((AssignmentContext)_localctx).left.stop):null)), ((AssignmentContext)_localctx).right.f); 
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

	public static class Var_declarationContext extends ParserRuleContext {
		public Variable v;
		public Composite_idContext composite_id;
		public Composite_idContext composite_id() {
			return getRuleContext(Composite_idContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Var_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterVar_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitVar_declaration(this);
		}
	}

	public final Var_declarationContext var_declaration() throws RecognitionException {
		Var_declarationContext _localctx = new Var_declarationContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_var_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			((Var_declarationContext)_localctx).composite_id = composite_id();
			setState(228);
			match(T__28);
			setState(229);
			type();
			setState(230);
			match(T__30);
			 ((Var_declarationContext)_localctx).v =  new Variable((((Var_declarationContext)_localctx).composite_id!=null?_input.getText(((Var_declarationContext)_localctx).composite_id.start,((Var_declarationContext)_localctx).composite_id.stop):null)); 
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

	public static class ModuleContext extends ParserRuleContext {
		public Module m;
		public Token ID;
		public Composite_idContext id1;
		public Composite_idContext id2;
		public AssignmentContext assignment;
		public Var_declarationContext var_declaration;
		public TerminalNode MODULE() { return getToken(nusmvParser.MODULE, 0); }
		public TerminalNode ID() { return getToken(nusmvParser.ID, 0); }
		public List<TerminalNode> ASSIGN() { return getTokens(nusmvParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(nusmvParser.ASSIGN, i);
		}
		public List<TerminalNode> VAR() { return getTokens(nusmvParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(nusmvParser.VAR, i);
		}
		public List<Composite_idContext> composite_id() {
			return getRuleContexts(Composite_idContext.class);
		}
		public Composite_idContext composite_id(int i) {
			return getRuleContext(Composite_idContext.class,i);
		}
		public List<AssignmentContext> assignment() {
			return getRuleContexts(AssignmentContext.class);
		}
		public AssignmentContext assignment(int i) {
			return getRuleContext(AssignmentContext.class,i);
		}
		public List<Var_declarationContext> var_declaration() {
			return getRuleContexts(Var_declarationContext.class);
		}
		public Var_declarationContext var_declaration(int i) {
			return getRuleContext(Var_declarationContext.class,i);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterModule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitModule(this);
		}
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_module);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(MODULE);
			setState(234);
			((ModuleContext)_localctx).ID = match(ID);

			        List<Variable> inputVariables = new ArrayList<>();
			        List<Variable> internalVariables = new ArrayList<>();
			        List<Assignment> assignments = new ArrayList<>();
			      
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(236);
				match(T__21);
				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(237);
					((ModuleContext)_localctx).id1 = composite_id();
					 inputVariables.add(new Variable((((ModuleContext)_localctx).id1!=null?_input.getText(((ModuleContext)_localctx).id1.start,((ModuleContext)_localctx).id1.stop):null))); 
					setState(245);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__19) {
						{
						{
						setState(239);
						match(T__19);
						setState(240);
						((ModuleContext)_localctx).id2 = composite_id();
						 inputVariables.add(new Variable((((ModuleContext)_localctx).id2!=null?_input.getText(((ModuleContext)_localctx).id2.start,((ModuleContext)_localctx).id2.stop):null))); 
						}
						}
						setState(247);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(250);
				match(T__22);
				}
			}

			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ASSIGN || _la==VAR) {
				{
				setState(271);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ASSIGN:
					{
					{
					setState(253);
					match(ASSIGN);
					setState(259);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==INIT || _la==NEXT) {
						{
						{
						setState(254);
						((ModuleContext)_localctx).assignment = assignment();
						 assignments.add(((ModuleContext)_localctx).assignment.a); 
						}
						}
						setState(261);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					}
					break;
				case VAR:
					{
					{
					setState(262);
					match(VAR);
					setState(268);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==ID) {
						{
						{
						setState(263);
						((ModuleContext)_localctx).var_declaration = var_declaration();
						 internalVariables.add(((ModuleContext)_localctx).var_declaration.v); 
						}
						}
						setState(270);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			 ((ModuleContext)_localctx).m =  new Module((((ModuleContext)_localctx).ID!=null?((ModuleContext)_localctx).ID.getText():null), inputVariables, internalVariables, assignments); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3,\u0119\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\3\3\3\3\3"+
		"\7\3\64\n\3\f\3\16\3\67\13\3\3\3\3\3\3\3\7\3<\n\3\f\3\16\3?\13\3\3\4\3"+
		"\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\7\nU\n\n\f\n\16\nX\13\n\3\n\3\n\5\n\\\n\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13o\n\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\fx\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\5\r\u0082\n\r\3\r\3\r\3\r\7\r\u0087\n\r\f\r\16\r\u008a\13\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\5\16\u0092\n\16\3\16\3\16\3\16\7\16\u0097\n\16\f"+
		"\16\16\16\u009a\13\16\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00a2\n\17\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\7\20\u00aa\n\20\f\20\16\20\u00ad\13\20\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\7\21\u00b5\n\21\f\21\16\21\u00b8\13\21\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00c2\n\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\7\23\u00ca\n\23\f\23\16\23\u00cd\13\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\5\24\u00d5\n\24\3\25\3\25\3\25\3\25\3\25\5\25\u00dc\n"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\7\27\u00f6\n\27"+
		"\f\27\16\27\u00f9\13\27\5\27\u00fb\n\27\3\27\5\27\u00fe\n\27\3\27\3\27"+
		"\3\27\3\27\7\27\u0104\n\27\f\27\16\27\u0107\13\27\3\27\3\27\3\27\3\27"+
		"\7\27\u010d\n\27\f\27\16\27\u0110\13\27\7\27\u0112\n\27\f\27\16\27\u0115"+
		"\13\27\3\27\3\27\3\27\2\2\30\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(*,\2\6\3\2#%\3\2\6\7\3\2\t\13\3\2\16\23\u011e\2.\3\2\2\2\4\60\3\2\2"+
		"\2\6@\3\2\2\2\bB\3\2\2\2\nD\3\2\2\2\fF\3\2\2\2\16H\3\2\2\2\20J\3\2\2\2"+
		"\22[\3\2\2\2\24n\3\2\2\2\26w\3\2\2\2\30y\3\2\2\2\32\u008b\3\2\2\2\34\u009b"+
		"\3\2\2\2\36\u00a3\3\2\2\2 \u00ae\3\2\2\2\"\u00b9\3\2\2\2$\u00c3\3\2\2"+
		"\2&\u00ce\3\2\2\2(\u00d6\3\2\2\2*\u00e5\3\2\2\2,\u00eb\3\2\2\2./\t\2\2"+
		"\2/\3\3\2\2\2\60\65\7,\2\2\61\62\7\3\2\2\62\64\7,\2\2\63\61\3\2\2\2\64"+
		"\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66=\3\2\2\2\67\65\3\2\2\289\7"+
		"\4\2\29:\7#\2\2:<\7\5\2\2;8\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>\5\3"+
		"\2\2\2?=\3\2\2\2@A\t\3\2\2A\7\3\2\2\2BC\7\b\2\2C\t\3\2\2\2DE\t\4\2\2E"+
		"\13\3\2\2\2FG\7\f\2\2G\r\3\2\2\2HI\7\r\2\2I\17\3\2\2\2JK\t\5\2\2K\21\3"+
		"\2\2\2L\\\7+\2\2MN\7#\2\2NO\7\24\2\2O\\\7#\2\2PQ\7\25\2\2QV\5\2\2\2RS"+
		"\7\26\2\2SU\5\2\2\2TR\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WY\3\2\2\2"+
		"XV\3\2\2\2YZ\7\27\2\2Z\\\3\2\2\2[L\3\2\2\2[M\3\2\2\2[P\3\2\2\2\\\23\3"+
		"\2\2\2]^\7\30\2\2^_\5&\24\2_`\7\31\2\2`a\b\13\1\2ao\3\2\2\2bc\5\2\2\2"+
		"cd\b\13\1\2do\3\2\2\2ef\5\4\3\2fg\b\13\1\2go\3\2\2\2hi\7\'\2\2ij\7\30"+
		"\2\2jk\5\4\3\2kl\7\31\2\2lm\b\13\1\2mo\3\2\2\2n]\3\2\2\2nb\3\2\2\2ne\3"+
		"\2\2\2nh\3\2\2\2o\25\3\2\2\2pq\5\24\13\2qr\b\f\1\2rx\3\2\2\2st\5\6\4\2"+
		"tu\5\26\f\2uv\b\f\1\2vx\3\2\2\2wp\3\2\2\2ws\3\2\2\2x\27\3\2\2\2yz\5\26"+
		"\f\2z\u0088\b\r\1\2{|\7\32\2\2|\u0082\b\r\1\2}~\7\33\2\2~\u0082\b\r\1"+
		"\2\177\u0080\7\34\2\2\u0080\u0082\b\r\1\2\u0081{\3\2\2\2\u0081}\3\2\2"+
		"\2\u0081\177\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\5\26\f\2\u0084\u0085"+
		"\b\r\1\2\u0085\u0087\3\2\2\2\u0086\u0081\3\2\2\2\u0087\u008a\3\2\2\2\u0088"+
		"\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\31\3\2\2\2\u008a\u0088\3\2\2"+
		"\2\u008b\u008c\5\30\r\2\u008c\u0098\b\16\1\2\u008d\u008e\7\35\2\2\u008e"+
		"\u0092\b\16\1\2\u008f\u0090\7\7\2\2\u0090\u0092\b\16\1\2\u0091\u008d\3"+
		"\2\2\2\u0091\u008f\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\5\30\r\2\u0094"+
		"\u0095\b\16\1\2\u0095\u0097\3\2\2\2\u0096\u0091\3\2\2\2\u0097\u009a\3"+
		"\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\33\3\2\2\2\u009a"+
		"\u0098\3\2\2\2\u009b\u009c\5\32\16\2\u009c\u00a1\b\17\1\2\u009d\u009e"+
		"\5\20\t\2\u009e\u009f\5\32\16\2\u009f\u00a0\b\17\1\2\u00a0\u00a2\3\2\2"+
		"\2\u00a1\u009d\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\35\3\2\2\2\u00a3\u00a4"+
		"\5\34\17\2\u00a4\u00ab\b\20\1\2\u00a5\u00a6\5\b\5\2\u00a6\u00a7\5\34\17"+
		"\2\u00a7\u00a8\b\20\1\2\u00a8\u00aa\3\2\2\2\u00a9\u00a5\3\2\2\2\u00aa"+
		"\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\37\3\2\2"+
		"\2\u00ad\u00ab\3\2\2\2\u00ae\u00af\5\36\20\2\u00af\u00b6\b\21\1\2\u00b0"+
		"\u00b1\5\n\6\2\u00b1\u00b2\5\36\20\2\u00b2\u00b3\b\21\1\2\u00b3\u00b5"+
		"\3\2\2\2\u00b4\u00b0\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6"+
		"\u00b7\3\2\2\2\u00b7!\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00ba\5 \21\2"+
		"\u00ba\u00c1\b\22\1\2\u00bb\u00bc\7\36\2\2\u00bc\u00bd\5 \21\2\u00bd\u00be"+
		"\7\37\2\2\u00be\u00bf\5 \21\2\u00bf\u00c0\b\22\1\2\u00c0\u00c2\3\2\2\2"+
		"\u00c1\u00bb\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2#\3\2\2\2\u00c3\u00c4\5"+
		" \21\2\u00c4\u00cb\b\23\1\2\u00c5\u00c6\5\f\7\2\u00c6\u00c7\5 \21\2\u00c7"+
		"\u00c8\b\23\1\2\u00c8\u00ca\3\2\2\2\u00c9\u00c5\3\2\2\2\u00ca\u00cd\3"+
		"\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc%\3\2\2\2\u00cd\u00cb"+
		"\3\2\2\2\u00ce\u00cf\5$\23\2\u00cf\u00d4\b\24\1\2\u00d0\u00d1\5\16\b\2"+
		"\u00d1\u00d2\5&\24\2\u00d2\u00d3\b\24\1\2\u00d3\u00d5\3\2\2\2\u00d4\u00d0"+
		"\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\'\3\2\2\2\u00d6\u00db\b\25\1\2\u00d7"+
		"\u00d8\7&\2\2\u00d8\u00dc\b\25\1\2\u00d9\u00da\7\'\2\2\u00da\u00dc\b\25"+
		"\1\2\u00db\u00d7\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd"+
		"\u00de\7\30\2\2\u00de\u00df\5\4\3\2\u00df\u00e0\7\31\2\2\u00e0\u00e1\7"+
		" \2\2\u00e1\u00e2\5&\24\2\u00e2\u00e3\7!\2\2\u00e3\u00e4\b\25\1\2\u00e4"+
		")\3\2\2\2\u00e5\u00e6\5\4\3\2\u00e6\u00e7\7\37\2\2\u00e7\u00e8\5\22\n"+
		"\2\u00e8\u00e9\7!\2\2\u00e9\u00ea\b\26\1\2\u00ea+\3\2\2\2\u00eb\u00ec"+
		"\7(\2\2\u00ec\u00ed\7,\2\2\u00ed\u00fd\b\27\1\2\u00ee\u00fa\7\30\2\2\u00ef"+
		"\u00f0\5\4\3\2\u00f0\u00f7\b\27\1\2\u00f1\u00f2\7\26\2\2\u00f2\u00f3\5"+
		"\4\3\2\u00f3\u00f4\b\27\1\2\u00f4\u00f6\3\2\2\2\u00f5\u00f1\3\2\2\2\u00f6"+
		"\u00f9\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fb\3\2"+
		"\2\2\u00f9\u00f7\3\2\2\2\u00fa\u00ef\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb"+
		"\u00fc\3\2\2\2\u00fc\u00fe\7\31\2\2\u00fd\u00ee\3\2\2\2\u00fd\u00fe\3"+
		"\2\2\2\u00fe\u0113\3\2\2\2\u00ff\u0105\7)\2\2\u0100\u0101\5(\25\2\u0101"+
		"\u0102\b\27\1\2\u0102\u0104\3\2\2\2\u0103\u0100\3\2\2\2\u0104\u0107\3"+
		"\2\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0112\3\2\2\2\u0107"+
		"\u0105\3\2\2\2\u0108\u010e\7*\2\2\u0109\u010a\5*\26\2\u010a\u010b\b\27"+
		"\1\2\u010b\u010d\3\2\2\2\u010c\u0109\3\2\2\2\u010d\u0110\3\2\2\2\u010e"+
		"\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3\2"+
		"\2\2\u0111\u00ff\3\2\2\2\u0111\u0108\3\2\2\2\u0112\u0115\3\2\2\2\u0113"+
		"\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0116\3\2\2\2\u0115\u0113\3\2"+
		"\2\2\u0116\u0117\b\27\1\2\u0117-\3\2\2\2\32\65=V[nw\u0081\u0088\u0091"+
		"\u0098\u00a1\u00ab\u00b6\u00c1\u00cb\u00d4\u00db\u00f7\u00fa\u00fd\u0105"+
		"\u010e\u0111\u0113";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}