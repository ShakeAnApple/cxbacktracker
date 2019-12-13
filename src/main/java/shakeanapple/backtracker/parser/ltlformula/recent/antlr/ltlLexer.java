package shakeanapple.backtracker.parser.ltlformula.recent.antlr;// Generated from C:/Users/ovsianp1/projects/code/SEARCH/cxbacktracker\ltl.g4 by ANTLR 4.7.2

import shakeanapple.backtracker.parser.ltlformula.recent.tree.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ltlLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "WS", 
			"INT_CONST", "TRUE", "FALSE", "COUNT", "ID"
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


	public ltlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ltl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2(\u00cc\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\3\2\3\3\3\3\3\4\3\4\3"+
		"\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3"+
		"\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3"+
		"\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3"+
		"\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3!\3\"\3\"\5\"\u009f\n\"\3"+
		"\"\6\"\u00a2\n\"\r\"\16\"\u00a3\3\"\3\"\3#\5#\u00a9\n#\3#\3#\3#\7#\u00ae"+
		"\n#\f#\16#\u00b1\13#\5#\u00b3\n#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3&\3"+
		"&\3&\3&\3&\3&\3\'\3\'\7\'\u00c8\n\'\f\'\16\'\u00cb\13\'\2\2(\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C"+
		"#E$G%I&K\'M(\3\2\5\4\2\13\13\"\"\5\2C\\aac|\6\2\62;C\\aac|\2\u00d2\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2"+
		"\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2"+
		"\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2"+
		"I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\3O\3\2\2\2\5Q\3\2\2\2\7S\3\2\2\2\tU\3"+
		"\2\2\2\13W\3\2\2\2\rY\3\2\2\2\17[\3\2\2\2\21]\3\2\2\2\23_\3\2\2\2\25a"+
		"\3\2\2\2\27c\3\2\2\2\31e\3\2\2\2\33g\3\2\2\2\35i\3\2\2\2\37k\3\2\2\2!"+
		"m\3\2\2\2#r\3\2\2\2%v\3\2\2\2\'z\3\2\2\2)}\3\2\2\2+\177\3\2\2\2-\u0082"+
		"\3\2\2\2/\u0084\3\2\2\2\61\u0087\3\2\2\2\63\u0089\3\2\2\2\65\u008c\3\2"+
		"\2\2\67\u008e\3\2\2\29\u0090\3\2\2\2;\u0092\3\2\2\2=\u0094\3\2\2\2?\u0096"+
		"\3\2\2\2A\u0098\3\2\2\2C\u00a1\3\2\2\2E\u00a8\3\2\2\2G\u00b4\3\2\2\2I"+
		"\u00b9\3\2\2\2K\u00bf\3\2\2\2M\u00c5\3\2\2\2OP\7\60\2\2P\4\3\2\2\2QR\7"+
		"]\2\2R\6\3\2\2\2ST\7_\2\2T\b\3\2\2\2UV\7#\2\2V\n\3\2\2\2WX\7Z\2\2X\f\3"+
		"\2\2\2YZ\7I\2\2Z\16\3\2\2\2[\\\7H\2\2\\\20\3\2\2\2]^\7[\2\2^\22\3\2\2"+
		"\2_`\7\\\2\2`\24\3\2\2\2ab\7Q\2\2b\26\3\2\2\2cd\7J\2\2d\30\3\2\2\2ef\7"+
		"W\2\2f\32\3\2\2\2gh\7X\2\2h\34\3\2\2\2ij\7(\2\2j\36\3\2\2\2kl\7~\2\2l"+
		" \3\2\2\2mn\7z\2\2no\7p\2\2op\7q\2\2pq\7t\2\2q\"\3\2\2\2rs\7z\2\2st\7"+
		"q\2\2tu\7t\2\2u$\3\2\2\2vw\7>\2\2wx\7/\2\2xy\7@\2\2y&\3\2\2\2z{\7/\2\2"+
		"{|\7@\2\2|(\3\2\2\2}~\7?\2\2~*\3\2\2\2\177\u0080\7#\2\2\u0080\u0081\7"+
		"?\2\2\u0081,\3\2\2\2\u0082\u0083\7@\2\2\u0083.\3\2\2\2\u0084\u0085\7@"+
		"\2\2\u0085\u0086\7?\2\2\u0086\60\3\2\2\2\u0087\u0088\7>\2\2\u0088\62\3"+
		"\2\2\2\u0089\u008a\7>\2\2\u008a\u008b\7?\2\2\u008b\64\3\2\2\2\u008c\u008d"+
		"\7*\2\2\u008d\66\3\2\2\2\u008e\u008f\7+\2\2\u008f8\3\2\2\2\u0090\u0091"+
		"\7/\2\2\u0091:\3\2\2\2\u0092\u0093\7-\2\2\u0093<\3\2\2\2\u0094\u0095\7"+
		",\2\2\u0095>\3\2\2\2\u0096\u0097\7\61\2\2\u0097@\3\2\2\2\u0098\u0099\7"+
		"o\2\2\u0099\u009a\7q\2\2\u009a\u009b\7f\2\2\u009bB\3\2\2\2\u009c\u00a2"+
		"\t\2\2\2\u009d\u009f\7\17\2\2\u009e\u009d\3\2\2\2\u009e\u009f\3\2\2\2"+
		"\u009f\u00a0\3\2\2\2\u00a0\u00a2\7\f\2\2\u00a1\u009c\3\2\2\2\u00a1\u009e"+
		"\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\u00a6\b\"\2\2\u00a6D\3\2\2\2\u00a7\u00a9\7/\2\2\u00a8"+
		"\u00a7\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00b2\3\2\2\2\u00aa\u00b3\7\62"+
		"\2\2\u00ab\u00af\4\63;\2\u00ac\u00ae\4\62;\2\u00ad\u00ac\3\2\2\2\u00ae"+
		"\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b3\3\2"+
		"\2\2\u00b1\u00af\3\2\2\2\u00b2\u00aa\3\2\2\2\u00b2\u00ab\3\2\2\2\u00b3"+
		"F\3\2\2\2\u00b4\u00b5\7V\2\2\u00b5\u00b6\7T\2\2\u00b6\u00b7\7W\2\2\u00b7"+
		"\u00b8\7G\2\2\u00b8H\3\2\2\2\u00b9\u00ba\7H\2\2\u00ba\u00bb\7C\2\2\u00bb"+
		"\u00bc\7N\2\2\u00bc\u00bd\7U\2\2\u00bd\u00be\7G\2\2\u00beJ\3\2\2\2\u00bf"+
		"\u00c0\7e\2\2\u00c0\u00c1\7q\2\2\u00c1\u00c2\7w\2\2\u00c2\u00c3\7p\2\2"+
		"\u00c3\u00c4\7v\2\2\u00c4L\3\2\2\2\u00c5\u00c9\t\3\2\2\u00c6\u00c8\t\4"+
		"\2\2\u00c7\u00c6\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9"+
		"\u00ca\3\2\2\2\u00caN\3\2\2\2\u00cb\u00c9\3\2\2\2\n\2\u009e\u00a1\u00a3"+
		"\u00a8\u00af\u00b2\u00c9\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}