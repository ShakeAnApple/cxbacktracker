// Generated from /home/buzhinsky/repos/cxbacktracker/nusmv.g4 by ANTLR 4.7.2
package shakeanapple.backtracker.antlrgenerated;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.Assignment;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.NuSMVModule;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.expression.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class nusmvParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, WS=29, LINE_COMMENT=30, INT_CONST=31, 
		TRUE=32, FALSE=33, INIT=34, NEXT=35, MODULE=36, VAR=37, ASSIGN=38, DEFINE=39, 
		BOOLEAN=40, COUNT=41, MOD=42, XOR=43, XNOR=44, CASE=45, ESAC=46, ID=47;
	public static final int
		RULE_constant = 0, RULE_composite_id = 1, RULE_atom = 2, RULE_unary_operator_sign = 3, 
		RULE_unary_operator = 4, RULE_binary_operator_sign7 = 5, RULE_binary_operator7 = 6, 
		RULE_binary_operator_sign6 = 7, RULE_binary_operator6 = 8, RULE_binary_operator_sign5 = 9, 
		RULE_binary_operator5 = 10, RULE_binary_operator_sign4 = 11, RULE_binary_operator4 = 12, 
		RULE_binary_operator_sign3 = 13, RULE_binary_operator3 = 14, RULE_ternary_operator = 15, 
		RULE_binary_operator_sign2 = 16, RULE_binary_operator2 = 17, RULE_binary_operator_sign1 = 18, 
		RULE_binary_operator1 = 19, RULE_type = 20, RULE_internal_var_declaration = 21, 
		RULE_possibly_untyped_declaration = 22, RULE_assignment = 23, RULE_definition = 24, 
		RULE_module = 25;
	private static String[] makeRuleNames() {
		return new String[] {
			"constant", "composite_id", "atom", "unary_operator_sign", "unary_operator", 
			"binary_operator_sign7", "binary_operator7", "binary_operator_sign6", 
			"binary_operator6", "binary_operator_sign5", "binary_operator5", "binary_operator_sign4", 
			"binary_operator4", "binary_operator_sign3", "binary_operator3", "ternary_operator", 
			"binary_operator_sign2", "binary_operator2", "binary_operator_sign1", 
			"binary_operator1", "type", "internal_var_declaration", "possibly_untyped_declaration", 
			"assignment", "definition", "module"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'['", "']'", "'('", "')'", "':'", "';'", "','", "'!'", 
			"'-'", "'*'", "'/'", "'+'", "'='", "'!='", "'>'", "'>='", "'<'", "'<='", 
			"'&'", "'|'", "'?'", "'<->'", "'->'", "'..'", "'{'", "'}'", "':='", null, 
			null, null, "'TRUE'", "'FALSE'", "'init'", "'next'", "'MODULE'", "'VAR'", 
			"'ASSIGN'", "'DEFINE'", "'boolean'", "'count'", "'mod'", "'xor'", "'xnor'", 
			"'case'", "'esac'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "WS", "LINE_COMMENT", "INT_CONST", "TRUE", 
			"FALSE", "INIT", "NEXT", "MODULE", "VAR", "ASSIGN", "DEFINE", "BOOLEAN", 
			"COUNT", "MOD", "XOR", "XNOR", "CASE", "ESAC", "ID"
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
			setState(52);
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
			setState(54);
			match(ID);
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(55);
				match(T__0);
				setState(56);
				match(ID);
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(62);
				match(T__1);
				setState(63);
				match(INT_CONST);
				setState(64);
				match(T__2);
				}
				}
				setState(69);
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

	public static class AtomContext extends ParserRuleContext {
		public Expression f;
		public Binary_operator1Context inside;
		public ConstantContext constant;
		public Composite_idContext composite_id;
		public Binary_operator1Context c;
		public Binary_operator1Context o;
		public Binary_operator1Context a1;
		public Binary_operator1Context a2;
		public List<Binary_operator1Context> binary_operator1() {
			return getRuleContexts(Binary_operator1Context.class);
		}
		public Binary_operator1Context binary_operator1(int i) {
			return getRuleContext(Binary_operator1Context.class,i);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public Composite_idContext composite_id() {
			return getRuleContext(Composite_idContext.class,0);
		}
		public TerminalNode NEXT() { return getToken(nusmvParser.NEXT, 0); }
		public TerminalNode CASE() { return getToken(nusmvParser.CASE, 0); }
		public TerminalNode ESAC() { return getToken(nusmvParser.ESAC, 0); }
		public TerminalNode COUNT() { return getToken(nusmvParser.COUNT, 0); }
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
		enterRule(_localctx, 4, RULE_atom);
		int _la;
		try {
			setState(120);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				match(T__3);
				setState(71);
				((AtomContext)_localctx).inside = binary_operator1();
				setState(72);
				match(T__4);
				 ((AtomContext)_localctx).f =  ((AtomContext)_localctx).inside.f; 
				}
				break;
			case INT_CONST:
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				((AtomContext)_localctx).constant = constant();
				 ((AtomContext)_localctx).f =  new Constant((((AtomContext)_localctx).constant!=null?_input.getText(((AtomContext)_localctx).constant.start,((AtomContext)_localctx).constant.stop):null)); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				((AtomContext)_localctx).composite_id = composite_id();
				 ((AtomContext)_localctx).f =  new Variable((((AtomContext)_localctx).composite_id!=null?_input.getText(((AtomContext)_localctx).composite_id.start,((AtomContext)_localctx).composite_id.stop):null), false); 
				}
				break;
			case NEXT:
				enterOuterAlt(_localctx, 4);
				{
				setState(81);
				match(NEXT);
				setState(82);
				match(T__3);
				setState(83);
				((AtomContext)_localctx).inside = binary_operator1();
				setState(84);
				match(T__4);
				 ((AtomContext)_localctx).f =  new NextOperator(((AtomContext)_localctx).inside.f); 
				}
				break;
			case CASE:
				enterOuterAlt(_localctx, 5);
				{
				setState(87);
				match(CASE);

				        List<Expression> conditions = new ArrayList<>();
				        List<Expression> options = new ArrayList<>();
				     
				setState(96); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(89);
					((AtomContext)_localctx).c = binary_operator1();
					 conditions.add(((AtomContext)_localctx).c.f); 
					setState(91);
					match(T__5);
					setState(92);
					((AtomContext)_localctx).o = binary_operator1();
					 options.add(((AtomContext)_localctx).o.f); 
					setState(94);
					match(T__6);
					}
					}
					setState(98); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__8) | (1L << T__9) | (1L << INT_CONST) | (1L << TRUE) | (1L << FALSE) | (1L << NEXT) | (1L << COUNT) | (1L << CASE) | (1L << ID))) != 0) );
				setState(100);
				match(ESAC);
				 ((AtomContext)_localctx).f =  new CaseOperator(conditions, options); 
				}
				break;
			case COUNT:
				enterOuterAlt(_localctx, 6);
				{
				setState(103);
				match(COUNT);

				        List<Expression> arguments = new ArrayList<>();
				     
				setState(105);
				match(T__3);
				setState(106);
				((AtomContext)_localctx).a1 = binary_operator1();
				 arguments.add(((AtomContext)_localctx).a1.f); 
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(108);
					match(T__7);
					setState(109);
					((AtomContext)_localctx).a2 = binary_operator1();
					 arguments.add(((AtomContext)_localctx).a2.f); 
					}
					}
					setState(116);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(117);
				match(T__4);
				 ((AtomContext)_localctx).f =  new CountOperator(arguments); 
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
		enterRule(_localctx, 6, RULE_unary_operator_sign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			_la = _input.LA(1);
			if ( !(_la==T__8 || _la==T__9) ) {
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
		enterRule(_localctx, 8, RULE_unary_operator);
		try {
			setState(131);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
			case INT_CONST:
			case TRUE:
			case FALSE:
			case NEXT:
			case COUNT:
			case CASE:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				((Unary_operatorContext)_localctx).atom = atom();
				 ((Unary_operatorContext)_localctx).f =  ((Unary_operatorContext)_localctx).atom.f; 
				}
				break;
			case T__8:
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				((Unary_operatorContext)_localctx).unary_operator_sign = unary_operator_sign();
				setState(128);
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

	public static class Binary_operator_sign7Context extends ParserRuleContext {
		public TerminalNode MOD() { return getToken(nusmvParser.MOD, 0); }
		public Binary_operator_sign7Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator_sign7; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterBinary_operator_sign7(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitBinary_operator_sign7(this);
		}
	}

	public final Binary_operator_sign7Context binary_operator_sign7() throws RecognitionException {
		Binary_operator_sign7Context _localctx = new Binary_operator_sign7Context(_ctx, getState());
		enterRule(_localctx, 10, RULE_binary_operator_sign7);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << MOD))) != 0)) ) {
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

	public static class Binary_operator7Context extends ParserRuleContext {
		public Expression f;
		public Unary_operatorContext f1;
		public Binary_operator_sign7Context inside;
		public Unary_operatorContext f2;
		public List<Unary_operatorContext> unary_operator() {
			return getRuleContexts(Unary_operatorContext.class);
		}
		public Unary_operatorContext unary_operator(int i) {
			return getRuleContext(Unary_operatorContext.class,i);
		}
		public List<Binary_operator_sign7Context> binary_operator_sign7() {
			return getRuleContexts(Binary_operator_sign7Context.class);
		}
		public Binary_operator_sign7Context binary_operator_sign7(int i) {
			return getRuleContext(Binary_operator_sign7Context.class,i);
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
		enterRule(_localctx, 12, RULE_binary_operator7);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			((Binary_operator7Context)_localctx).f1 = unary_operator();
			 ((Binary_operator7Context)_localctx).f =  ((Binary_operator7Context)_localctx).f1.f; 
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << MOD))) != 0)) {
				{
				{
				setState(137);
				((Binary_operator7Context)_localctx).inside = binary_operator_sign7();
				setState(138);
				((Binary_operator7Context)_localctx).f2 = unary_operator();
				 ((Binary_operator7Context)_localctx).f =  new BinaryOperator((((Binary_operator7Context)_localctx).inside!=null?_input.getText(((Binary_operator7Context)_localctx).inside.start,((Binary_operator7Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator7Context)_localctx).f2.f); 
				}
				}
				setState(145);
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

	public static class Binary_operator_sign6Context extends ParserRuleContext {
		public Binary_operator_sign6Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator_sign6; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterBinary_operator_sign6(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitBinary_operator_sign6(this);
		}
	}

	public final Binary_operator_sign6Context binary_operator_sign6() throws RecognitionException {
		Binary_operator_sign6Context _localctx = new Binary_operator_sign6Context(_ctx, getState());
		enterRule(_localctx, 14, RULE_binary_operator_sign6);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			_la = _input.LA(1);
			if ( !(_la==T__9 || _la==T__12) ) {
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

	public static class Binary_operator6Context extends ParserRuleContext {
		public Expression f;
		public Binary_operator7Context f1;
		public Binary_operator_sign6Context inside;
		public Binary_operator7Context f2;
		public List<Binary_operator7Context> binary_operator7() {
			return getRuleContexts(Binary_operator7Context.class);
		}
		public Binary_operator7Context binary_operator7(int i) {
			return getRuleContext(Binary_operator7Context.class,i);
		}
		public List<Binary_operator_sign6Context> binary_operator_sign6() {
			return getRuleContexts(Binary_operator_sign6Context.class);
		}
		public Binary_operator_sign6Context binary_operator_sign6(int i) {
			return getRuleContext(Binary_operator_sign6Context.class,i);
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
		enterRule(_localctx, 16, RULE_binary_operator6);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			((Binary_operator6Context)_localctx).f1 = binary_operator7();
			 ((Binary_operator6Context)_localctx).f =  ((Binary_operator6Context)_localctx).f1.f; 
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9 || _la==T__12) {
				{
				{
				setState(150);
				((Binary_operator6Context)_localctx).inside = binary_operator_sign6();
				setState(151);
				((Binary_operator6Context)_localctx).f2 = binary_operator7();
				 ((Binary_operator6Context)_localctx).f =  new BinaryOperator((((Binary_operator6Context)_localctx).inside!=null?_input.getText(((Binary_operator6Context)_localctx).inside.start,((Binary_operator6Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator6Context)_localctx).f2.f); 
				}
				}
				setState(158);
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
		enterRule(_localctx, 18, RULE_binary_operator_sign5);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
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
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterBinary_operator5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitBinary_operator5(this);
		}
	}

	public final Binary_operator5Context binary_operator5() throws RecognitionException {
		Binary_operator5Context _localctx = new Binary_operator5Context(_ctx, getState());
		enterRule(_localctx, 20, RULE_binary_operator5);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			((Binary_operator5Context)_localctx).f1 = binary_operator6();
			 ((Binary_operator5Context)_localctx).f =  ((Binary_operator5Context)_localctx).f1.f; 
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) {
				{
				{
				setState(163);
				((Binary_operator5Context)_localctx).inside = binary_operator_sign5();
				setState(164);
				((Binary_operator5Context)_localctx).f2 = binary_operator6();
				 ((Binary_operator5Context)_localctx).f =  new BinaryOperator((((Binary_operator5Context)_localctx).inside!=null?_input.getText(((Binary_operator5Context)_localctx).inside.start,((Binary_operator5Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator5Context)_localctx).f2.f); 
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
		enterRule(_localctx, 22, RULE_binary_operator_sign4);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(T__19);
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
		enterRule(_localctx, 24, RULE_binary_operator4);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			((Binary_operator4Context)_localctx).f1 = binary_operator5();
			 ((Binary_operator4Context)_localctx).f =  ((Binary_operator4Context)_localctx).f1.f; 
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__19) {
				{
				{
				setState(176);
				((Binary_operator4Context)_localctx).inside = binary_operator_sign4();
				setState(177);
				((Binary_operator4Context)_localctx).f2 = binary_operator5();
				 ((Binary_operator4Context)_localctx).f =  new BinaryOperator((((Binary_operator4Context)_localctx).inside!=null?_input.getText(((Binary_operator4Context)_localctx).inside.start,((Binary_operator4Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator4Context)_localctx).f2.f); 
				}
				}
				setState(184);
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

	public static class Binary_operator_sign3Context extends ParserRuleContext {
		public TerminalNode XOR() { return getToken(nusmvParser.XOR, 0); }
		public TerminalNode XNOR() { return getToken(nusmvParser.XNOR, 0); }
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
		enterRule(_localctx, 26, RULE_binary_operator_sign3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__20) | (1L << XOR) | (1L << XNOR))) != 0)) ) {
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
		enterRule(_localctx, 28, RULE_binary_operator3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			((Binary_operator3Context)_localctx).f1 = binary_operator4();
			 ((Binary_operator3Context)_localctx).f =  ((Binary_operator3Context)_localctx).f1.f; 
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__20) | (1L << XOR) | (1L << XNOR))) != 0)) {
				{
				{
				setState(189);
				((Binary_operator3Context)_localctx).inside = binary_operator_sign3();
				setState(190);
				((Binary_operator3Context)_localctx).f2 = binary_operator4();
				 ((Binary_operator3Context)_localctx).f =  new BinaryOperator((((Binary_operator3Context)_localctx).inside!=null?_input.getText(((Binary_operator3Context)_localctx).inside.start,((Binary_operator3Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator3Context)_localctx).f2.f); 
				}
				}
				setState(197);
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
		enterRule(_localctx, 30, RULE_ternary_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			((Ternary_operatorContext)_localctx).f1 = binary_operator3();
			 ((Ternary_operatorContext)_localctx).f =  ((Ternary_operatorContext)_localctx).f1.f; 
			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(200);
				match(T__21);
				setState(201);
				((Ternary_operatorContext)_localctx).f2 = binary_operator3();
				setState(202);
				match(T__5);
				setState(203);
				((Ternary_operatorContext)_localctx).f3 = binary_operator3();
				 ((Ternary_operatorContext)_localctx).f =  CaseOperator.ternaryOperator(((Ternary_operatorContext)_localctx).f1.f, ((Ternary_operatorContext)_localctx).f2.f, ((Ternary_operatorContext)_localctx).f3.f); 
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
		enterRule(_localctx, 32, RULE_binary_operator_sign2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(T__22);
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
		public Ternary_operatorContext f1;
		public Binary_operator_sign2Context inside;
		public Ternary_operatorContext f2;
		public List<Ternary_operatorContext> ternary_operator() {
			return getRuleContexts(Ternary_operatorContext.class);
		}
		public Ternary_operatorContext ternary_operator(int i) {
			return getRuleContext(Ternary_operatorContext.class,i);
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
			setState(210);
			((Binary_operator2Context)_localctx).f1 = ternary_operator();
			 ((Binary_operator2Context)_localctx).f =  ((Binary_operator2Context)_localctx).f1.f; 
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(212);
				((Binary_operator2Context)_localctx).inside = binary_operator_sign2();
				setState(213);
				((Binary_operator2Context)_localctx).f2 = ternary_operator();
				 ((Binary_operator2Context)_localctx).f =  new BinaryOperator((((Binary_operator2Context)_localctx).inside!=null?_input.getText(((Binary_operator2Context)_localctx).inside.start,((Binary_operator2Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator2Context)_localctx).f2.f); 
				}
				}
				setState(220);
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
		enterRule(_localctx, 36, RULE_binary_operator_sign1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			match(T__23);
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
		enterRule(_localctx, 38, RULE_binary_operator1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			((Binary_operator1Context)_localctx).f1 = binary_operator2();
			 ((Binary_operator1Context)_localctx).f =  ((Binary_operator1Context)_localctx).f1.f; 
			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(225);
				((Binary_operator1Context)_localctx).inside = binary_operator_sign1();
				setState(226);
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
		enterRule(_localctx, 40, RULE_type);
		int _la;
		try {
			setState(246);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(231);
				match(BOOLEAN);
				}
				break;
			case INT_CONST:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(232);
				match(INT_CONST);
				setState(233);
				match(T__24);
				setState(234);
				match(INT_CONST);
				}
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(235);
				match(T__25);
				setState(236);
				constant();
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(237);
					match(T__7);
					setState(238);
					constant();
					}
					}
					setState(243);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(244);
				match(T__26);
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

	public static class Internal_var_declarationContext extends ParserRuleContext {
		public Variable v;
		public Composite_idContext composite_id;
		public TypeContext type;
		public Composite_idContext composite_id() {
			return getRuleContext(Composite_idContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Internal_var_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_internal_var_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterInternal_var_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitInternal_var_declaration(this);
		}
	}

	public final Internal_var_declarationContext internal_var_declaration() throws RecognitionException {
		Internal_var_declarationContext _localctx = new Internal_var_declarationContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_internal_var_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			((Internal_var_declarationContext)_localctx).composite_id = composite_id();
			setState(249);
			match(T__5);
			setState(250);
			((Internal_var_declarationContext)_localctx).type = type();
			 ((Internal_var_declarationContext)_localctx).v =  new Variable((((Internal_var_declarationContext)_localctx).composite_id!=null?_input.getText(((Internal_var_declarationContext)_localctx).composite_id.start,((Internal_var_declarationContext)_localctx).composite_id.stop):null), (((Internal_var_declarationContext)_localctx).type!=null?_input.getText(((Internal_var_declarationContext)_localctx).type.start,((Internal_var_declarationContext)_localctx).type.stop):null)); 
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

	public static class Possibly_untyped_declarationContext extends ParserRuleContext {
		public Variable v;
		public Composite_idContext typeless;
		public Internal_var_declarationContext typeful;
		public Composite_idContext composite_id() {
			return getRuleContext(Composite_idContext.class,0);
		}
		public Internal_var_declarationContext internal_var_declaration() {
			return getRuleContext(Internal_var_declarationContext.class,0);
		}
		public Possibly_untyped_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_possibly_untyped_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterPossibly_untyped_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitPossibly_untyped_declaration(this);
		}
	}

	public final Possibly_untyped_declarationContext possibly_untyped_declaration() throws RecognitionException {
		Possibly_untyped_declarationContext _localctx = new Possibly_untyped_declarationContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_possibly_untyped_declaration);
		try {
			setState(259);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(253);
				((Possibly_untyped_declarationContext)_localctx).typeless = composite_id();
				 ((Possibly_untyped_declarationContext)_localctx).v =  new Variable((((Possibly_untyped_declarationContext)_localctx).typeless!=null?_input.getText(((Possibly_untyped_declarationContext)_localctx).typeless.start,((Possibly_untyped_declarationContext)_localctx).typeless.stop):null)); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(256);
				((Possibly_untyped_declarationContext)_localctx).typeful = internal_var_declaration();
				 ((Possibly_untyped_declarationContext)_localctx).v =  ((Possibly_untyped_declarationContext)_localctx).typeful.v; 
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
		enterRule(_localctx, 46, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 Assignment.Type type; 
			setState(266);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INIT:
				{
				setState(262);
				match(INIT);
				 type = Assignment.Type.INIT; 
				}
				break;
			case NEXT:
				{
				setState(264);
				match(NEXT);
				 type = Assignment.Type.NEXT; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(268);
			match(T__3);
			setState(269);
			((AssignmentContext)_localctx).left = composite_id();
			setState(270);
			match(T__4);
			setState(271);
			match(T__27);
			setState(272);
			((AssignmentContext)_localctx).right = binary_operator1();
			setState(273);
			match(T__6);
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

	public static class DefinitionContext extends ParserRuleContext {
		public Variable v;
		public Assignment aInit;
		public Assignment aNext;
		public Possibly_untyped_declarationContext left;
		public Binary_operator1Context right;
		public Possibly_untyped_declarationContext possibly_untyped_declaration() {
			return getRuleContext(Possibly_untyped_declarationContext.class,0);
		}
		public Binary_operator1Context binary_operator1() {
			return getRuleContext(Binary_operator1Context.class,0);
		}
		public DefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).enterDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof nusmvListener ) ((nusmvListener)listener).exitDefinition(this);
		}
	}

	public final DefinitionContext definition() throws RecognitionException {
		DefinitionContext _localctx = new DefinitionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_definition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			((DefinitionContext)_localctx).left = possibly_untyped_declaration();
			setState(277);
			match(T__27);
			setState(278);
			((DefinitionContext)_localctx).right = binary_operator1();
			setState(279);
			match(T__6);
			 ((DefinitionContext)_localctx).v =  ((DefinitionContext)_localctx).left.v;
			        ((DefinitionContext)_localctx).aInit =  new Assignment(Assignment.Type.INIT, _localctx.v, ((DefinitionContext)_localctx).right.f);
			        ((DefinitionContext)_localctx).aNext =  new Assignment(Assignment.Type.NEXT, _localctx.v, new NextOperator(((DefinitionContext)_localctx).right.f));
			      
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
		public NuSMVModule m;
		public Token ID;
		public Possibly_untyped_declarationContext v1;
		public Possibly_untyped_declarationContext v2;
		public Internal_var_declarationContext internal_var_declaration;
		public AssignmentContext assignment;
		public DefinitionContext d;
		public TerminalNode MODULE() { return getToken(nusmvParser.MODULE, 0); }
		public TerminalNode ID() { return getToken(nusmvParser.ID, 0); }
		public List<TerminalNode> VAR() { return getTokens(nusmvParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(nusmvParser.VAR, i);
		}
		public List<TerminalNode> ASSIGN() { return getTokens(nusmvParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(nusmvParser.ASSIGN, i);
		}
		public List<TerminalNode> DEFINE() { return getTokens(nusmvParser.DEFINE); }
		public TerminalNode DEFINE(int i) {
			return getToken(nusmvParser.DEFINE, i);
		}
		public List<Possibly_untyped_declarationContext> possibly_untyped_declaration() {
			return getRuleContexts(Possibly_untyped_declarationContext.class);
		}
		public Possibly_untyped_declarationContext possibly_untyped_declaration(int i) {
			return getRuleContext(Possibly_untyped_declarationContext.class,i);
		}
		public List<Internal_var_declarationContext> internal_var_declaration() {
			return getRuleContexts(Internal_var_declarationContext.class);
		}
		public Internal_var_declarationContext internal_var_declaration(int i) {
			return getRuleContext(Internal_var_declarationContext.class,i);
		}
		public List<AssignmentContext> assignment() {
			return getRuleContexts(AssignmentContext.class);
		}
		public AssignmentContext assignment(int i) {
			return getRuleContext(AssignmentContext.class,i);
		}
		public List<DefinitionContext> definition() {
			return getRuleContexts(DefinitionContext.class);
		}
		public DefinitionContext definition(int i) {
			return getRuleContext(DefinitionContext.class,i);
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
		enterRule(_localctx, 50, RULE_module);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			match(MODULE);
			setState(283);
			((ModuleContext)_localctx).ID = match(ID);

			        List<Variable> inputVariables = new ArrayList<>();
			        List<Variable> internalVariables = new ArrayList<>();
			        List<Assignment> assignments = new ArrayList<>();
			      
			setState(300);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(285);
				match(T__3);
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(286);
					((ModuleContext)_localctx).v1 = possibly_untyped_declaration();
					 inputVariables.add(((ModuleContext)_localctx).v1.v); 
					setState(294);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__7) {
						{
						{
						setState(288);
						match(T__7);
						setState(289);
						((ModuleContext)_localctx).v2 = possibly_untyped_declaration();
						 inputVariables.add(((ModuleContext)_localctx).v2.v); 
						}
						}
						setState(296);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(299);
				match(T__4);
				}
			}

			setState(332);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VAR) | (1L << ASSIGN) | (1L << DEFINE))) != 0)) {
				{
				setState(330);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VAR:
					{
					{
					setState(302);
					match(VAR);
					setState(309);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==ID) {
						{
						{
						setState(303);
						((ModuleContext)_localctx).internal_var_declaration = internal_var_declaration();
						setState(304);
						match(T__6);
						 internalVariables.add(((ModuleContext)_localctx).internal_var_declaration.v); 
						}
						}
						setState(311);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					}
					break;
				case ASSIGN:
					{
					{
					setState(312);
					match(ASSIGN);
					setState(318);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==INIT || _la==NEXT) {
						{
						{
						setState(313);
						((ModuleContext)_localctx).assignment = assignment();
						 assignments.add(((ModuleContext)_localctx).assignment.a); 
						}
						}
						setState(320);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					}
					break;
				case DEFINE:
					{
					{
					setState(321);
					match(DEFINE);
					setState(327);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==ID) {
						{
						{
						setState(322);
						((ModuleContext)_localctx).d = definition();
						 internalVariables.add(((ModuleContext)_localctx).d.v); assignments.add(((ModuleContext)_localctx).d.aInit); assignments.add(((ModuleContext)_localctx).d.aNext); 
						}
						}
						setState(329);
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
				setState(334);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			 ((ModuleContext)_localctx).m =  new NuSMVModule((((ModuleContext)_localctx).ID!=null?((ModuleContext)_localctx).ID.getText():null), inputVariables, internalVariables, assignments); 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\61\u0154\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\3\2\3\3\3\3\3\3\7\3<\n\3\f\3\16\3?\13\3\3\3\3"+
		"\3\3\3\7\3D\n\3\f\3\16\3G\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\6\4c"+
		"\n\4\r\4\16\4d\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4s\n"+
		"\4\f\4\16\4v\13\4\3\4\3\4\3\4\5\4{\n\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\5\6\u0086\n\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0090\n\b\f\b\16"+
		"\b\u0093\13\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u009d\n\n\f\n\16\n\u00a0"+
		"\13\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00aa\n\f\f\f\16\f\u00ad\13"+
		"\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00b7\n\16\f\16\16\16\u00ba"+
		"\13\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u00c4\n\20\f\20\16"+
		"\20\u00c7\13\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00d1\n\21"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u00db\n\23\f\23\16\23\u00de"+
		"\13\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u00e8\n\25\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u00f2\n\26\f\26\16\26\u00f5\13"+
		"\26\3\26\3\26\5\26\u00f9\n\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\5\30\u0106\n\30\3\31\3\31\3\31\3\31\3\31\5\31\u010d\n"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3"+
		"\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u0127\n\33"+
		"\f\33\16\33\u012a\13\33\5\33\u012c\n\33\3\33\5\33\u012f\n\33\3\33\3\33"+
		"\3\33\3\33\3\33\7\33\u0136\n\33\f\33\16\33\u0139\13\33\3\33\3\33\3\33"+
		"\3\33\7\33\u013f\n\33\f\33\16\33\u0142\13\33\3\33\3\33\3\33\3\33\7\33"+
		"\u0148\n\33\f\33\16\33\u014b\13\33\7\33\u014d\n\33\f\33\16\33\u0150\13"+
		"\33\3\33\3\33\3\33\2\2\34\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&"+
		"(*,.\60\62\64\2\b\3\2!#\3\2\13\f\4\2\r\16,,\4\2\f\f\17\17\3\2\20\25\4"+
		"\2\27\27-.\2\u0159\2\66\3\2\2\2\48\3\2\2\2\6z\3\2\2\2\b|\3\2\2\2\n\u0085"+
		"\3\2\2\2\f\u0087\3\2\2\2\16\u0089\3\2\2\2\20\u0094\3\2\2\2\22\u0096\3"+
		"\2\2\2\24\u00a1\3\2\2\2\26\u00a3\3\2\2\2\30\u00ae\3\2\2\2\32\u00b0\3\2"+
		"\2\2\34\u00bb\3\2\2\2\36\u00bd\3\2\2\2 \u00c8\3\2\2\2\"\u00d2\3\2\2\2"+
		"$\u00d4\3\2\2\2&\u00df\3\2\2\2(\u00e1\3\2\2\2*\u00f8\3\2\2\2,\u00fa\3"+
		"\2\2\2.\u0105\3\2\2\2\60\u0107\3\2\2\2\62\u0116\3\2\2\2\64\u011c\3\2\2"+
		"\2\66\67\t\2\2\2\67\3\3\2\2\28=\7\61\2\29:\7\3\2\2:<\7\61\2\2;9\3\2\2"+
		"\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>E\3\2\2\2?=\3\2\2\2@A\7\4\2\2AB\7!\2"+
		"\2BD\7\5\2\2C@\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\5\3\2\2\2GE\3\2"+
		"\2\2HI\7\6\2\2IJ\5(\25\2JK\7\7\2\2KL\b\4\1\2L{\3\2\2\2MN\5\2\2\2NO\b\4"+
		"\1\2O{\3\2\2\2PQ\5\4\3\2QR\b\4\1\2R{\3\2\2\2ST\7%\2\2TU\7\6\2\2UV\5(\25"+
		"\2VW\7\7\2\2WX\b\4\1\2X{\3\2\2\2YZ\7/\2\2Zb\b\4\1\2[\\\5(\25\2\\]\b\4"+
		"\1\2]^\7\b\2\2^_\5(\25\2_`\b\4\1\2`a\7\t\2\2ac\3\2\2\2b[\3\2\2\2cd\3\2"+
		"\2\2db\3\2\2\2de\3\2\2\2ef\3\2\2\2fg\7\60\2\2gh\b\4\1\2h{\3\2\2\2ij\7"+
		"+\2\2jk\b\4\1\2kl\7\6\2\2lm\5(\25\2mt\b\4\1\2no\7\n\2\2op\5(\25\2pq\b"+
		"\4\1\2qs\3\2\2\2rn\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2uw\3\2\2\2vt\3"+
		"\2\2\2wx\7\7\2\2xy\b\4\1\2y{\3\2\2\2zH\3\2\2\2zM\3\2\2\2zP\3\2\2\2zS\3"+
		"\2\2\2zY\3\2\2\2zi\3\2\2\2{\7\3\2\2\2|}\t\3\2\2}\t\3\2\2\2~\177\5\6\4"+
		"\2\177\u0080\b\6\1\2\u0080\u0086\3\2\2\2\u0081\u0082\5\b\5\2\u0082\u0083"+
		"\5\n\6\2\u0083\u0084\b\6\1\2\u0084\u0086\3\2\2\2\u0085~\3\2\2\2\u0085"+
		"\u0081\3\2\2\2\u0086\13\3\2\2\2\u0087\u0088\t\4\2\2\u0088\r\3\2\2\2\u0089"+
		"\u008a\5\n\6\2\u008a\u0091\b\b\1\2\u008b\u008c\5\f\7\2\u008c\u008d\5\n"+
		"\6\2\u008d\u008e\b\b\1\2\u008e\u0090\3\2\2\2\u008f\u008b\3\2\2\2\u0090"+
		"\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\17\3\2\2"+
		"\2\u0093\u0091\3\2\2\2\u0094\u0095\t\5\2\2\u0095\21\3\2\2\2\u0096\u0097"+
		"\5\16\b\2\u0097\u009e\b\n\1\2\u0098\u0099\5\20\t\2\u0099\u009a\5\16\b"+
		"\2\u009a\u009b\b\n\1\2\u009b\u009d\3\2\2\2\u009c\u0098\3\2\2\2\u009d\u00a0"+
		"\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\23\3\2\2\2\u00a0"+
		"\u009e\3\2\2\2\u00a1\u00a2\t\6\2\2\u00a2\25\3\2\2\2\u00a3\u00a4\5\22\n"+
		"\2\u00a4\u00ab\b\f\1\2\u00a5\u00a6\5\24\13\2\u00a6\u00a7\5\22\n\2\u00a7"+
		"\u00a8\b\f\1\2\u00a8\u00aa\3\2\2\2\u00a9\u00a5\3\2\2\2\u00aa\u00ad\3\2"+
		"\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\27\3\2\2\2\u00ad\u00ab"+
		"\3\2\2\2\u00ae\u00af\7\26\2\2\u00af\31\3\2\2\2\u00b0\u00b1\5\26\f\2\u00b1"+
		"\u00b8\b\16\1\2\u00b2\u00b3\5\30\r\2\u00b3\u00b4\5\26\f\2\u00b4\u00b5"+
		"\b\16\1\2\u00b5\u00b7\3\2\2\2\u00b6\u00b2\3\2\2\2\u00b7\u00ba\3\2\2\2"+
		"\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\33\3\2\2\2\u00ba\u00b8"+
		"\3\2\2\2\u00bb\u00bc\t\7\2\2\u00bc\35\3\2\2\2\u00bd\u00be\5\32\16\2\u00be"+
		"\u00c5\b\20\1\2\u00bf\u00c0\5\34\17\2\u00c0\u00c1\5\32\16\2\u00c1\u00c2"+
		"\b\20\1\2\u00c2\u00c4\3\2\2\2\u00c3\u00bf\3\2\2\2\u00c4\u00c7\3\2\2\2"+
		"\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\37\3\2\2\2\u00c7\u00c5"+
		"\3\2\2\2\u00c8\u00c9\5\36\20\2\u00c9\u00d0\b\21\1\2\u00ca\u00cb\7\30\2"+
		"\2\u00cb\u00cc\5\36\20\2\u00cc\u00cd\7\b\2\2\u00cd\u00ce\5\36\20\2\u00ce"+
		"\u00cf\b\21\1\2\u00cf\u00d1\3\2\2\2\u00d0\u00ca\3\2\2\2\u00d0\u00d1\3"+
		"\2\2\2\u00d1!\3\2\2\2\u00d2\u00d3\7\31\2\2\u00d3#\3\2\2\2\u00d4\u00d5"+
		"\5 \21\2\u00d5\u00dc\b\23\1\2\u00d6\u00d7\5\"\22\2\u00d7\u00d8\5 \21\2"+
		"\u00d8\u00d9\b\23\1\2\u00d9\u00db\3\2\2\2\u00da\u00d6\3\2\2\2\u00db\u00de"+
		"\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd%\3\2\2\2\u00de"+
		"\u00dc\3\2\2\2\u00df\u00e0\7\32\2\2\u00e0\'\3\2\2\2\u00e1\u00e2\5$\23"+
		"\2\u00e2\u00e7\b\25\1\2\u00e3\u00e4\5&\24\2\u00e4\u00e5\5(\25\2\u00e5"+
		"\u00e6\b\25\1\2\u00e6\u00e8\3\2\2\2\u00e7\u00e3\3\2\2\2\u00e7\u00e8\3"+
		"\2\2\2\u00e8)\3\2\2\2\u00e9\u00f9\7*\2\2\u00ea\u00eb\7!\2\2\u00eb\u00ec"+
		"\7\33\2\2\u00ec\u00f9\7!\2\2\u00ed\u00ee\7\34\2\2\u00ee\u00f3\5\2\2\2"+
		"\u00ef\u00f0\7\n\2\2\u00f0\u00f2\5\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f5"+
		"\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f6\3\2\2\2\u00f5"+
		"\u00f3\3\2\2\2\u00f6\u00f7\7\35\2\2\u00f7\u00f9\3\2\2\2\u00f8\u00e9\3"+
		"\2\2\2\u00f8\u00ea\3\2\2\2\u00f8\u00ed\3\2\2\2\u00f9+\3\2\2\2\u00fa\u00fb"+
		"\5\4\3\2\u00fb\u00fc\7\b\2\2\u00fc\u00fd\5*\26\2\u00fd\u00fe\b\27\1\2"+
		"\u00fe-\3\2\2\2\u00ff\u0100\5\4\3\2\u0100\u0101\b\30\1\2\u0101\u0106\3"+
		"\2\2\2\u0102\u0103\5,\27\2\u0103\u0104\b\30\1\2\u0104\u0106\3\2\2\2\u0105"+
		"\u00ff\3\2\2\2\u0105\u0102\3\2\2\2\u0106/\3\2\2\2\u0107\u010c\b\31\1\2"+
		"\u0108\u0109\7$\2\2\u0109\u010d\b\31\1\2\u010a\u010b\7%\2\2\u010b\u010d"+
		"\b\31\1\2\u010c\u0108\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u010e\3\2\2\2"+
		"\u010e\u010f\7\6\2\2\u010f\u0110\5\4\3\2\u0110\u0111\7\7\2\2\u0111\u0112"+
		"\7\36\2\2\u0112\u0113\5(\25\2\u0113\u0114\7\t\2\2\u0114\u0115\b\31\1\2"+
		"\u0115\61\3\2\2\2\u0116\u0117\5.\30\2\u0117\u0118\7\36\2\2\u0118\u0119"+
		"\5(\25\2\u0119\u011a\7\t\2\2\u011a\u011b\b\32\1\2\u011b\63\3\2\2\2\u011c"+
		"\u011d\7&\2\2\u011d\u011e\7\61\2\2\u011e\u012e\b\33\1\2\u011f\u012b\7"+
		"\6\2\2\u0120\u0121\5.\30\2\u0121\u0128\b\33\1\2\u0122\u0123\7\n\2\2\u0123"+
		"\u0124\5.\30\2\u0124\u0125\b\33\1\2\u0125\u0127\3\2\2\2\u0126\u0122\3"+
		"\2\2\2\u0127\u012a\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129"+
		"\u012c\3\2\2\2\u012a\u0128\3\2\2\2\u012b\u0120\3\2\2\2\u012b\u012c\3\2"+
		"\2\2\u012c\u012d\3\2\2\2\u012d\u012f\7\7\2\2\u012e\u011f\3\2\2\2\u012e"+
		"\u012f\3\2\2\2\u012f\u014e\3\2\2\2\u0130\u0137\7\'\2\2\u0131\u0132\5,"+
		"\27\2\u0132\u0133\7\t\2\2\u0133\u0134\b\33\1\2\u0134\u0136\3\2\2\2\u0135"+
		"\u0131\3\2\2\2\u0136\u0139\3\2\2\2\u0137\u0135\3\2\2\2\u0137\u0138\3\2"+
		"\2\2\u0138\u014d\3\2\2\2\u0139\u0137\3\2\2\2\u013a\u0140\7(\2\2\u013b"+
		"\u013c\5\60\31\2\u013c\u013d\b\33\1\2\u013d\u013f\3\2\2\2\u013e\u013b"+
		"\3\2\2\2\u013f\u0142\3\2\2\2\u0140\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141"+
		"\u014d\3\2\2\2\u0142\u0140\3\2\2\2\u0143\u0149\7)\2\2\u0144\u0145\5\62"+
		"\32\2\u0145\u0146\b\33\1\2\u0146\u0148\3\2\2\2\u0147\u0144\3\2\2\2\u0148"+
		"\u014b\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014d\3\2"+
		"\2\2\u014b\u0149\3\2\2\2\u014c\u0130\3\2\2\2\u014c\u013a\3\2\2\2\u014c"+
		"\u0143\3\2\2\2\u014d\u0150\3\2\2\2\u014e\u014c\3\2\2\2\u014e\u014f\3\2"+
		"\2\2\u014f\u0151\3\2\2\2\u0150\u014e\3\2\2\2\u0151\u0152\b\33\1\2\u0152"+
		"\65\3\2\2\2\34=Edtz\u0085\u0091\u009e\u00ab\u00b8\u00c5\u00d0\u00dc\u00e7"+
		"\u00f3\u00f8\u0105\u010c\u0128\u012b\u012e\u0137\u0140\u0149\u014c\u014e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}