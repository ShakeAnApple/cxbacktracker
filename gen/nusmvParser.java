// Generated from C:/Users/ovsianp1/projects/code/SEARCH/cxbacktracker\nusmv.g4 by ANTLR 4.7.2

import java.util.*;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.*;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.expression.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

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
		BOOLEAN=40, COUNT=41, MOD=42, XOR=43, XNOR=44, MAX=45, CASE=46, ESAC=47, 
		ID=48;
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
			"'max'", "'case'", "'esac'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "WS", "LINE_COMMENT", "INT_CONST", "TRUE", 
			"FALSE", "INIT", "NEXT", "MODULE", "VAR", "ASSIGN", "DEFINE", "BOOLEAN", 
			"COUNT", "MOD", "XOR", "XNOR", "MAX", "CASE", "ESAC", "ID"
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitConstant(this);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitComposite_id(this);
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
		public Binary_operator6Context aint1;
		public Binary_operator6Context aint2;
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
		public TerminalNode MAX() { return getToken(nusmvParser.MAX, 0); }
		public List<Binary_operator6Context> binary_operator6() {
			return getRuleContexts(Binary_operator6Context.class);
		}
		public Binary_operator6Context binary_operator6(int i) {
			return getRuleContext(Binary_operator6Context.class,i);
		}
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_atom);
		int _la;
		try {
			setState(137);
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
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__8) | (1L << T__9) | (1L << INT_CONST) | (1L << TRUE) | (1L << FALSE) | (1L << NEXT) | (1L << COUNT) | (1L << MAX) | (1L << CASE) | (1L << ID))) != 0) );
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
			case MAX:
				enterOuterAlt(_localctx, 7);
				{
				setState(120);
				match(MAX);

				        List<Expression> arguments = new ArrayList<>();
				    
				setState(122);
				match(T__3);
				setState(123);
				((AtomContext)_localctx).aint1 = binary_operator6();
				 arguments.add(((AtomContext)_localctx).aint1.f); 
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(125);
					match(T__7);
					setState(126);
					((AtomContext)_localctx).aint2 = binary_operator6();
					 arguments.add(((AtomContext)_localctx).aint2.f); 
					}
					}
					setState(133);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(134);
				match(T__4);
				 ((AtomContext)_localctx).f =  new MaxOperator(arguments); 
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitUnary_operator_sign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unary_operator_signContext unary_operator_sign() throws RecognitionException {
		Unary_operator_signContext _localctx = new Unary_operator_signContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_unary_operator_sign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
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
		public Unary_operator_signContext op;
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitUnary_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unary_operatorContext unary_operator() throws RecognitionException {
		Unary_operatorContext _localctx = new Unary_operatorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_unary_operator);
		try {
			setState(148);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
			case INT_CONST:
			case TRUE:
			case FALSE:
			case NEXT:
			case COUNT:
			case MAX:
			case CASE:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				((Unary_operatorContext)_localctx).atom = atom();
				 ((Unary_operatorContext)_localctx).f =  ((Unary_operatorContext)_localctx).atom.f; 
				}
				break;
			case T__8:
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(144);
				((Unary_operatorContext)_localctx).op = unary_operator_sign();
				setState(145);
				((Unary_operatorContext)_localctx).inside = unary_operator();
				 ((Unary_operatorContext)_localctx).f =  new UnaryOperator((((Unary_operatorContext)_localctx).op!=null?_input.getText(((Unary_operatorContext)_localctx).op.start,((Unary_operatorContext)_localctx).op.stop):null), ((Unary_operatorContext)_localctx).inside.f); 
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitBinary_operator_sign7(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator_sign7Context binary_operator_sign7() throws RecognitionException {
		Binary_operator_sign7Context _localctx = new Binary_operator_sign7Context(_ctx, getState());
		enterRule(_localctx, 10, RULE_binary_operator_sign7);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitBinary_operator7(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator7Context binary_operator7() throws RecognitionException {
		Binary_operator7Context _localctx = new Binary_operator7Context(_ctx, getState());
		enterRule(_localctx, 12, RULE_binary_operator7);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			((Binary_operator7Context)_localctx).f1 = unary_operator();
			 ((Binary_operator7Context)_localctx).f =  ((Binary_operator7Context)_localctx).f1.f; 
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << MOD))) != 0)) {
				{
				{
				setState(154);
				((Binary_operator7Context)_localctx).inside = binary_operator_sign7();
				setState(155);
				((Binary_operator7Context)_localctx).f2 = unary_operator();
				 ((Binary_operator7Context)_localctx).f =  new BinaryOperator((((Binary_operator7Context)_localctx).inside!=null?_input.getText(((Binary_operator7Context)_localctx).inside.start,((Binary_operator7Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator7Context)_localctx).f2.f); 
				}
				}
				setState(162);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitBinary_operator_sign6(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator_sign6Context binary_operator_sign6() throws RecognitionException {
		Binary_operator_sign6Context _localctx = new Binary_operator_sign6Context(_ctx, getState());
		enterRule(_localctx, 14, RULE_binary_operator_sign6);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitBinary_operator6(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator6Context binary_operator6() throws RecognitionException {
		Binary_operator6Context _localctx = new Binary_operator6Context(_ctx, getState());
		enterRule(_localctx, 16, RULE_binary_operator6);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			((Binary_operator6Context)_localctx).f1 = binary_operator7();
			 ((Binary_operator6Context)_localctx).f =  ((Binary_operator6Context)_localctx).f1.f; 
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9 || _la==T__12) {
				{
				{
				setState(167);
				((Binary_operator6Context)_localctx).inside = binary_operator_sign6();
				setState(168);
				((Binary_operator6Context)_localctx).f2 = binary_operator7();
				 ((Binary_operator6Context)_localctx).f =  new BinaryOperator((((Binary_operator6Context)_localctx).inside!=null?_input.getText(((Binary_operator6Context)_localctx).inside.start,((Binary_operator6Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator6Context)_localctx).f2.f); 
				}
				}
				setState(175);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitBinary_operator_sign5(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator_sign5Context binary_operator_sign5() throws RecognitionException {
		Binary_operator_sign5Context _localctx = new Binary_operator_sign5Context(_ctx, getState());
		enterRule(_localctx, 18, RULE_binary_operator_sign5);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitBinary_operator5(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator5Context binary_operator5() throws RecognitionException {
		Binary_operator5Context _localctx = new Binary_operator5Context(_ctx, getState());
		enterRule(_localctx, 20, RULE_binary_operator5);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			((Binary_operator5Context)_localctx).f1 = binary_operator6();
			 ((Binary_operator5Context)_localctx).f =  ((Binary_operator5Context)_localctx).f1.f; 
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) {
				{
				{
				setState(180);
				((Binary_operator5Context)_localctx).inside = binary_operator_sign5();
				setState(181);
				((Binary_operator5Context)_localctx).f2 = binary_operator6();
				 ((Binary_operator5Context)_localctx).f =  new BinaryOperator((((Binary_operator5Context)_localctx).inside!=null?_input.getText(((Binary_operator5Context)_localctx).inside.start,((Binary_operator5Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator5Context)_localctx).f2.f); 
				}
				}
				setState(188);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitBinary_operator_sign4(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator_sign4Context binary_operator_sign4() throws RecognitionException {
		Binary_operator_sign4Context _localctx = new Binary_operator_sign4Context(_ctx, getState());
		enterRule(_localctx, 22, RULE_binary_operator_sign4);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitBinary_operator4(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator4Context binary_operator4() throws RecognitionException {
		Binary_operator4Context _localctx = new Binary_operator4Context(_ctx, getState());
		enterRule(_localctx, 24, RULE_binary_operator4);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			((Binary_operator4Context)_localctx).f1 = binary_operator5();
			 ((Binary_operator4Context)_localctx).f =  ((Binary_operator4Context)_localctx).f1.f; 
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__19) {
				{
				{
				setState(193);
				((Binary_operator4Context)_localctx).inside = binary_operator_sign4();
				setState(194);
				((Binary_operator4Context)_localctx).f2 = binary_operator5();
				 ((Binary_operator4Context)_localctx).f =  new BinaryOperator((((Binary_operator4Context)_localctx).inside!=null?_input.getText(((Binary_operator4Context)_localctx).inside.start,((Binary_operator4Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator4Context)_localctx).f2.f); 
				}
				}
				setState(201);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitBinary_operator_sign3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator_sign3Context binary_operator_sign3() throws RecognitionException {
		Binary_operator_sign3Context _localctx = new Binary_operator_sign3Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_binary_operator_sign3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitBinary_operator3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator3Context binary_operator3() throws RecognitionException {
		Binary_operator3Context _localctx = new Binary_operator3Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_binary_operator3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			((Binary_operator3Context)_localctx).f1 = binary_operator4();
			 ((Binary_operator3Context)_localctx).f =  ((Binary_operator3Context)_localctx).f1.f; 
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__20) | (1L << XOR) | (1L << XNOR))) != 0)) {
				{
				{
				setState(206);
				((Binary_operator3Context)_localctx).inside = binary_operator_sign3();
				setState(207);
				((Binary_operator3Context)_localctx).f2 = binary_operator4();
				 ((Binary_operator3Context)_localctx).f =  new BinaryOperator((((Binary_operator3Context)_localctx).inside!=null?_input.getText(((Binary_operator3Context)_localctx).inside.start,((Binary_operator3Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator3Context)_localctx).f2.f); 
				}
				}
				setState(214);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitTernary_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ternary_operatorContext ternary_operator() throws RecognitionException {
		Ternary_operatorContext _localctx = new Ternary_operatorContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_ternary_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			((Ternary_operatorContext)_localctx).f1 = binary_operator3();
			 ((Ternary_operatorContext)_localctx).f =  ((Ternary_operatorContext)_localctx).f1.f; 
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(217);
				match(T__21);
				setState(218);
				((Ternary_operatorContext)_localctx).f2 = binary_operator3();
				setState(219);
				match(T__5);
				setState(220);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitBinary_operator_sign2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator_sign2Context binary_operator_sign2() throws RecognitionException {
		Binary_operator_sign2Context _localctx = new Binary_operator_sign2Context(_ctx, getState());
		enterRule(_localctx, 32, RULE_binary_operator_sign2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitBinary_operator2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator2Context binary_operator2() throws RecognitionException {
		Binary_operator2Context _localctx = new Binary_operator2Context(_ctx, getState());
		enterRule(_localctx, 34, RULE_binary_operator2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			((Binary_operator2Context)_localctx).f1 = ternary_operator();
			 ((Binary_operator2Context)_localctx).f =  ((Binary_operator2Context)_localctx).f1.f; 
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(229);
				((Binary_operator2Context)_localctx).inside = binary_operator_sign2();
				setState(230);
				((Binary_operator2Context)_localctx).f2 = ternary_operator();
				 ((Binary_operator2Context)_localctx).f =  new BinaryOperator((((Binary_operator2Context)_localctx).inside!=null?_input.getText(((Binary_operator2Context)_localctx).inside.start,((Binary_operator2Context)_localctx).inside.stop):null), _localctx.f, ((Binary_operator2Context)_localctx).f2.f); 
				}
				}
				setState(237);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitBinary_operator_sign1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator_sign1Context binary_operator_sign1() throws RecognitionException {
		Binary_operator_sign1Context _localctx = new Binary_operator_sign1Context(_ctx, getState());
		enterRule(_localctx, 36, RULE_binary_operator_sign1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitBinary_operator1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operator1Context binary_operator1() throws RecognitionException {
		Binary_operator1Context _localctx = new Binary_operator1Context(_ctx, getState());
		enterRule(_localctx, 38, RULE_binary_operator1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			((Binary_operator1Context)_localctx).f1 = binary_operator2();
			 ((Binary_operator1Context)_localctx).f =  ((Binary_operator1Context)_localctx).f1.f; 
			setState(246);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(242);
				((Binary_operator1Context)_localctx).inside = binary_operator_sign1();
				setState(243);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_type);
		int _la;
		try {
			setState(263);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				match(BOOLEAN);
				}
				break;
			case INT_CONST:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(249);
				match(INT_CONST);
				setState(250);
				match(T__24);
				setState(251);
				match(INT_CONST);
				}
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(252);
				match(T__25);
				setState(253);
				constant();
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(254);
					match(T__7);
					setState(255);
					constant();
					}
					}
					setState(260);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(261);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitInternal_var_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Internal_var_declarationContext internal_var_declaration() throws RecognitionException {
		Internal_var_declarationContext _localctx = new Internal_var_declarationContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_internal_var_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			((Internal_var_declarationContext)_localctx).composite_id = composite_id();
			setState(266);
			match(T__5);
			setState(267);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitPossibly_untyped_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Possibly_untyped_declarationContext possibly_untyped_declaration() throws RecognitionException {
		Possibly_untyped_declarationContext _localctx = new Possibly_untyped_declarationContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_possibly_untyped_declaration);
		try {
			setState(276);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				((Possibly_untyped_declarationContext)_localctx).typeless = composite_id();
				 ((Possibly_untyped_declarationContext)_localctx).v =  new Variable((((Possibly_untyped_declarationContext)_localctx).typeless!=null?_input.getText(((Possibly_untyped_declarationContext)_localctx).typeless.start,((Possibly_untyped_declarationContext)_localctx).typeless.stop):null)); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(273);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 Assignment.Type type; 
			setState(283);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INIT:
				{
				setState(279);
				match(INIT);
				 type = Assignment.Type.INIT; 
				}
				break;
			case NEXT:
				{
				setState(281);
				match(NEXT);
				 type = Assignment.Type.NEXT; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(285);
			match(T__3);
			setState(286);
			((AssignmentContext)_localctx).left = composite_id();
			setState(287);
			match(T__4);
			setState(288);
			match(T__27);
			setState(289);
			((AssignmentContext)_localctx).right = binary_operator1();
			setState(290);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinitionContext definition() throws RecognitionException {
		DefinitionContext _localctx = new DefinitionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_definition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			((DefinitionContext)_localctx).left = possibly_untyped_declaration();
			setState(294);
			match(T__27);
			setState(295);
			((DefinitionContext)_localctx).right = binary_operator1();
			setState(296);
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
		public TerminalNode EOF() { return getToken(nusmvParser.EOF, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof nusmvVisitor ) return ((nusmvVisitor<? extends T>)visitor).visitModule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_module);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			match(MODULE);
			setState(300);
			((ModuleContext)_localctx).ID = match(ID);

			        List<Variable> inputVariables = new ArrayList<>();
			        List<Variable> internalVariables = new ArrayList<>();
			        List<Assignment> assignments = new ArrayList<>();
			      
			setState(317);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(302);
				match(T__3);
				setState(314);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(303);
					((ModuleContext)_localctx).v1 = possibly_untyped_declaration();
					 inputVariables.add(((ModuleContext)_localctx).v1.v); 
					setState(311);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__7) {
						{
						{
						setState(305);
						match(T__7);
						setState(306);
						((ModuleContext)_localctx).v2 = possibly_untyped_declaration();
						 inputVariables.add(((ModuleContext)_localctx).v2.v); 
						}
						}
						setState(313);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(316);
				match(T__4);
				}
			}

			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VAR) | (1L << ASSIGN) | (1L << DEFINE))) != 0)) {
				{
				setState(347);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VAR:
					{
					{
					setState(319);
					match(VAR);
					setState(326);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==ID) {
						{
						{
						setState(320);
						((ModuleContext)_localctx).internal_var_declaration = internal_var_declaration();
						setState(321);
						match(T__6);
						 internalVariables.add(((ModuleContext)_localctx).internal_var_declaration.v); 
						}
						}
						setState(328);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					}
					break;
				case ASSIGN:
					{
					{
					setState(329);
					match(ASSIGN);
					setState(335);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==INIT || _la==NEXT) {
						{
						{
						setState(330);
						((ModuleContext)_localctx).assignment = assignment();
						 assignments.add(((ModuleContext)_localctx).assignment.a); 
						}
						}
						setState(337);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					}
					break;
				case DEFINE:
					{
					{
					setState(338);
					match(DEFINE);
					setState(344);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==ID) {
						{
						{
						setState(339);
						((ModuleContext)_localctx).d = definition();
						 internalVariables.add(((ModuleContext)_localctx).d.v); assignments.add(((ModuleContext)_localctx).d.aInit); assignments.add(((ModuleContext)_localctx).d.aNext); 
						}
						}
						setState(346);
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
				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			 ((ModuleContext)_localctx).m =  new NuSMVModule((((ModuleContext)_localctx).ID!=null?((ModuleContext)_localctx).ID.getText():null), inputVariables, internalVariables, assignments); 
			setState(353);
			match(EOF);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\62\u0166\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\3\2\3\3\3\3\3\3\7\3<\n\3\f\3\16\3?\13\3\3\3\3"+
		"\3\3\3\7\3D\n\3\f\3\16\3G\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\6\4c"+
		"\n\4\r\4\16\4d\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4s\n"+
		"\4\f\4\16\4v\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4"+
		"\u0084\n\4\f\4\16\4\u0087\13\4\3\4\3\4\3\4\5\4\u008c\n\4\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\5\6\u0097\n\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\7"+
		"\b\u00a1\n\b\f\b\16\b\u00a4\13\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00ae"+
		"\n\n\f\n\16\n\u00b1\13\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00bb\n"+
		"\f\f\f\16\f\u00be\13\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00c8"+
		"\n\16\f\16\16\16\u00cb\13\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\7"+
		"\20\u00d5\n\20\f\20\16\20\u00d8\13\20\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\5\21\u00e2\n\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\7\23"+
		"\u00ec\n\23\f\23\16\23\u00ef\13\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\5\25\u00f9\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u0103"+
		"\n\26\f\26\16\26\u0106\13\26\3\26\3\26\5\26\u010a\n\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u0117\n\30\3\31\3\31\3\31"+
		"\3\31\3\31\5\31\u011e\n\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\7\33\u0138\n\33\f\33\16\33\u013b\13\33\5\33\u013d\n\33\3\33\5\33"+
		"\u0140\n\33\3\33\3\33\3\33\3\33\3\33\7\33\u0147\n\33\f\33\16\33\u014a"+
		"\13\33\3\33\3\33\3\33\3\33\7\33\u0150\n\33\f\33\16\33\u0153\13\33\3\33"+
		"\3\33\3\33\3\33\7\33\u0159\n\33\f\33\16\33\u015c\13\33\7\33\u015e\n\33"+
		"\f\33\16\33\u0161\13\33\3\33\3\33\3\33\3\33\2\2\34\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\2\b\3\2!#\3\2\13\f\4\2\r\16,,\4\2"+
		"\f\f\17\17\3\2\20\25\4\2\27\27-.\2\u016d\2\66\3\2\2\2\48\3\2\2\2\6\u008b"+
		"\3\2\2\2\b\u008d\3\2\2\2\n\u0096\3\2\2\2\f\u0098\3\2\2\2\16\u009a\3\2"+
		"\2\2\20\u00a5\3\2\2\2\22\u00a7\3\2\2\2\24\u00b2\3\2\2\2\26\u00b4\3\2\2"+
		"\2\30\u00bf\3\2\2\2\32\u00c1\3\2\2\2\34\u00cc\3\2\2\2\36\u00ce\3\2\2\2"+
		" \u00d9\3\2\2\2\"\u00e3\3\2\2\2$\u00e5\3\2\2\2&\u00f0\3\2\2\2(\u00f2\3"+
		"\2\2\2*\u0109\3\2\2\2,\u010b\3\2\2\2.\u0116\3\2\2\2\60\u0118\3\2\2\2\62"+
		"\u0127\3\2\2\2\64\u012d\3\2\2\2\66\67\t\2\2\2\67\3\3\2\2\28=\7\62\2\2"+
		"9:\7\3\2\2:<\7\62\2\2;9\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>E\3\2\2"+
		"\2?=\3\2\2\2@A\7\4\2\2AB\7!\2\2BD\7\5\2\2C@\3\2\2\2DG\3\2\2\2EC\3\2\2"+
		"\2EF\3\2\2\2F\5\3\2\2\2GE\3\2\2\2HI\7\6\2\2IJ\5(\25\2JK\7\7\2\2KL\b\4"+
		"\1\2L\u008c\3\2\2\2MN\5\2\2\2NO\b\4\1\2O\u008c\3\2\2\2PQ\5\4\3\2QR\b\4"+
		"\1\2R\u008c\3\2\2\2ST\7%\2\2TU\7\6\2\2UV\5(\25\2VW\7\7\2\2WX\b\4\1\2X"+
		"\u008c\3\2\2\2YZ\7\60\2\2Zb\b\4\1\2[\\\5(\25\2\\]\b\4\1\2]^\7\b\2\2^_"+
		"\5(\25\2_`\b\4\1\2`a\7\t\2\2ac\3\2\2\2b[\3\2\2\2cd\3\2\2\2db\3\2\2\2d"+
		"e\3\2\2\2ef\3\2\2\2fg\7\61\2\2gh\b\4\1\2h\u008c\3\2\2\2ij\7+\2\2jk\b\4"+
		"\1\2kl\7\6\2\2lm\5(\25\2mt\b\4\1\2no\7\n\2\2op\5(\25\2pq\b\4\1\2qs\3\2"+
		"\2\2rn\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2uw\3\2\2\2vt\3\2\2\2wx\7\7"+
		"\2\2xy\b\4\1\2y\u008c\3\2\2\2z{\7/\2\2{|\b\4\1\2|}\7\6\2\2}~\5\22\n\2"+
		"~\u0085\b\4\1\2\177\u0080\7\n\2\2\u0080\u0081\5\22\n\2\u0081\u0082\b\4"+
		"\1\2\u0082\u0084\3\2\2\2\u0083\177\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087\u0085\3\2\2\2\u0088"+
		"\u0089\7\7\2\2\u0089\u008a\b\4\1\2\u008a\u008c\3\2\2\2\u008bH\3\2\2\2"+
		"\u008bM\3\2\2\2\u008bP\3\2\2\2\u008bS\3\2\2\2\u008bY\3\2\2\2\u008bi\3"+
		"\2\2\2\u008bz\3\2\2\2\u008c\7\3\2\2\2\u008d\u008e\t\3\2\2\u008e\t\3\2"+
		"\2\2\u008f\u0090\5\6\4\2\u0090\u0091\b\6\1\2\u0091\u0097\3\2\2\2\u0092"+
		"\u0093\5\b\5\2\u0093\u0094\5\n\6\2\u0094\u0095\b\6\1\2\u0095\u0097\3\2"+
		"\2\2\u0096\u008f\3\2\2\2\u0096\u0092\3\2\2\2\u0097\13\3\2\2\2\u0098\u0099"+
		"\t\4\2\2\u0099\r\3\2\2\2\u009a\u009b\5\n\6\2\u009b\u00a2\b\b\1\2\u009c"+
		"\u009d\5\f\7\2\u009d\u009e\5\n\6\2\u009e\u009f\b\b\1\2\u009f\u00a1\3\2"+
		"\2\2\u00a0\u009c\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2"+
		"\u00a3\3\2\2\2\u00a3\17\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\t\5\2"+
		"\2\u00a6\21\3\2\2\2\u00a7\u00a8\5\16\b\2\u00a8\u00af\b\n\1\2\u00a9\u00aa"+
		"\5\20\t\2\u00aa\u00ab\5\16\b\2\u00ab\u00ac\b\n\1\2\u00ac\u00ae\3\2\2\2"+
		"\u00ad\u00a9\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0"+
		"\3\2\2\2\u00b0\23\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\t\6\2\2\u00b3"+
		"\25\3\2\2\2\u00b4\u00b5\5\22\n\2\u00b5\u00bc\b\f\1\2\u00b6\u00b7\5\24"+
		"\13\2\u00b7\u00b8\5\22\n\2\u00b8\u00b9\b\f\1\2\u00b9\u00bb\3\2\2\2\u00ba"+
		"\u00b6\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2"+
		"\2\2\u00bd\27\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c0\7\26\2\2\u00c0\31"+
		"\3\2\2\2\u00c1\u00c2\5\26\f\2\u00c2\u00c9\b\16\1\2\u00c3\u00c4\5\30\r"+
		"\2\u00c4\u00c5\5\26\f\2\u00c5\u00c6\b\16\1\2\u00c6\u00c8\3\2\2\2\u00c7"+
		"\u00c3\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2"+
		"\2\2\u00ca\33\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00cd\t\7\2\2\u00cd\35"+
		"\3\2\2\2\u00ce\u00cf\5\32\16\2\u00cf\u00d6\b\20\1\2\u00d0\u00d1\5\34\17"+
		"\2\u00d1\u00d2\5\32\16\2\u00d2\u00d3\b\20\1\2\u00d3\u00d5\3\2\2\2\u00d4"+
		"\u00d0\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2"+
		"\2\2\u00d7\37\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00da\5\36\20\2\u00da"+
		"\u00e1\b\21\1\2\u00db\u00dc\7\30\2\2\u00dc\u00dd\5\36\20\2\u00dd\u00de"+
		"\7\b\2\2\u00de\u00df\5\36\20\2\u00df\u00e0\b\21\1\2\u00e0\u00e2\3\2\2"+
		"\2\u00e1\u00db\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2!\3\2\2\2\u00e3\u00e4"+
		"\7\31\2\2\u00e4#\3\2\2\2\u00e5\u00e6\5 \21\2\u00e6\u00ed\b\23\1\2\u00e7"+
		"\u00e8\5\"\22\2\u00e8\u00e9\5 \21\2\u00e9\u00ea\b\23\1\2\u00ea\u00ec\3"+
		"\2\2\2\u00eb\u00e7\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed"+
		"\u00ee\3\2\2\2\u00ee%\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f1\7\32\2\2"+
		"\u00f1\'\3\2\2\2\u00f2\u00f3\5$\23\2\u00f3\u00f8\b\25\1\2\u00f4\u00f5"+
		"\5&\24\2\u00f5\u00f6\5(\25\2\u00f6\u00f7\b\25\1\2\u00f7\u00f9\3\2\2\2"+
		"\u00f8\u00f4\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9)\3\2\2\2\u00fa\u010a\7"+
		"*\2\2\u00fb\u00fc\7!\2\2\u00fc\u00fd\7\33\2\2\u00fd\u010a\7!\2\2\u00fe"+
		"\u00ff\7\34\2\2\u00ff\u0104\5\2\2\2\u0100\u0101\7\n\2\2\u0101\u0103\5"+
		"\2\2\2\u0102\u0100\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0102\3\2\2\2\u0104"+
		"\u0105\3\2\2\2\u0105\u0107\3\2\2\2\u0106\u0104\3\2\2\2\u0107\u0108\7\35"+
		"\2\2\u0108\u010a\3\2\2\2\u0109\u00fa\3\2\2\2\u0109\u00fb\3\2\2\2\u0109"+
		"\u00fe\3\2\2\2\u010a+\3\2\2\2\u010b\u010c\5\4\3\2\u010c\u010d\7\b\2\2"+
		"\u010d\u010e\5*\26\2\u010e\u010f\b\27\1\2\u010f-\3\2\2\2\u0110\u0111\5"+
		"\4\3\2\u0111\u0112\b\30\1\2\u0112\u0117\3\2\2\2\u0113\u0114\5,\27\2\u0114"+
		"\u0115\b\30\1\2\u0115\u0117\3\2\2\2\u0116\u0110\3\2\2\2\u0116\u0113\3"+
		"\2\2\2\u0117/\3\2\2\2\u0118\u011d\b\31\1\2\u0119\u011a\7$\2\2\u011a\u011e"+
		"\b\31\1\2\u011b\u011c\7%\2\2\u011c\u011e\b\31\1\2\u011d\u0119\3\2\2\2"+
		"\u011d\u011b\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0120\7\6\2\2\u0120\u0121"+
		"\5\4\3\2\u0121\u0122\7\7\2\2\u0122\u0123\7\36\2\2\u0123\u0124\5(\25\2"+
		"\u0124\u0125\7\t\2\2\u0125\u0126\b\31\1\2\u0126\61\3\2\2\2\u0127\u0128"+
		"\5.\30\2\u0128\u0129\7\36\2\2\u0129\u012a\5(\25\2\u012a\u012b\7\t\2\2"+
		"\u012b\u012c\b\32\1\2\u012c\63\3\2\2\2\u012d\u012e\7&\2\2\u012e\u012f"+
		"\7\62\2\2\u012f\u013f\b\33\1\2\u0130\u013c\7\6\2\2\u0131\u0132\5.\30\2"+
		"\u0132\u0139\b\33\1\2\u0133\u0134\7\n\2\2\u0134\u0135\5.\30\2\u0135\u0136"+
		"\b\33\1\2\u0136\u0138\3\2\2\2\u0137\u0133\3\2\2\2\u0138\u013b\3\2\2\2"+
		"\u0139\u0137\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139"+
		"\3\2\2\2\u013c\u0131\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e\3\2\2\2\u013e"+
		"\u0140\7\7\2\2\u013f\u0130\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u015f\3\2"+
		"\2\2\u0141\u0148\7\'\2\2\u0142\u0143\5,\27\2\u0143\u0144\7\t\2\2\u0144"+
		"\u0145\b\33\1\2\u0145\u0147\3\2\2\2\u0146\u0142\3\2\2\2\u0147\u014a\3"+
		"\2\2\2\u0148\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u015e\3\2\2\2\u014a"+
		"\u0148\3\2\2\2\u014b\u0151\7(\2\2\u014c\u014d\5\60\31\2\u014d\u014e\b"+
		"\33\1\2\u014e\u0150\3\2\2\2\u014f\u014c\3\2\2\2\u0150\u0153\3\2\2\2\u0151"+
		"\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u015e\3\2\2\2\u0153\u0151\3\2"+
		"\2\2\u0154\u015a\7)\2\2\u0155\u0156\5\62\32\2\u0156\u0157\b\33\1\2\u0157"+
		"\u0159\3\2\2\2\u0158\u0155\3\2\2\2\u0159\u015c\3\2\2\2\u015a\u0158\3\2"+
		"\2\2\u015a\u015b\3\2\2\2\u015b\u015e\3\2\2\2\u015c\u015a\3\2\2\2\u015d"+
		"\u0141\3\2\2\2\u015d\u014b\3\2\2\2\u015d\u0154\3\2\2\2\u015e\u0161\3\2"+
		"\2\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0162\3\2\2\2\u0161"+
		"\u015f\3\2\2\2\u0162\u0163\b\33\1\2\u0163\u0164\7\2\2\3\u0164\65\3\2\2"+
		"\2\35=Edt\u0085\u008b\u0096\u00a2\u00af\u00bc\u00c9\u00d6\u00e1\u00ed"+
		"\u00f8\u0104\u0109\u0116\u011d\u0139\u013c\u013f\u0148\u0151\u015a\u015d"+
		"\u015f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}