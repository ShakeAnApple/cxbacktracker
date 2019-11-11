// Generated from /home/buzhinsky/repos/cxbacktracker/nusmv.g4 by ANTLR 4.6
package shakeanapple.backtracker.antlrgenerated;

import java.util.*;
import shakeanapple.backtracker.nusmvparsing.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class nusmvLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "WS", "INT_CONST", 
		"TRUE", "FALSE", "INIT", "NEXT", "MODULE", "ASSIGN", "VAR", "BOOLEAN", 
		"ID"
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


	public nusmvLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "nusmv.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2,\u00f2\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32"+
		"\3\33\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3"+
		" \3!\3!\5!\u00a7\n!\3!\6!\u00aa\n!\r!\16!\u00ab\3!\3!\3\"\5\"\u00b1\n"+
		"\"\3\"\3\"\3\"\7\"\u00b6\n\"\f\"\16\"\u00b9\13\"\5\"\u00bb\n\"\3#\3#\3"+
		"#\3#\3#\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3"+
		"\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3"+
		"*\3+\3+\7+\u00ee\n+\f+\16+\u00f1\13+\2\2,\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+"+
		"U,\3\2\5\4\2\13\13\"\"\5\2C\\aac|\6\2\62;C\\aac|\u00f8\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\3W\3\2"+
		"\2\2\5Y\3\2\2\2\7[\3\2\2\2\t]\3\2\2\2\13_\3\2\2\2\ra\3\2\2\2\17c\3\2\2"+
		"\2\21e\3\2\2\2\23j\3\2\2\2\25n\3\2\2\2\27r\3\2\2\2\31u\3\2\2\2\33w\3\2"+
		"\2\2\35z\3\2\2\2\37|\3\2\2\2!\177\3\2\2\2#\u0081\3\2\2\2%\u0084\3\2\2"+
		"\2\'\u0087\3\2\2\2)\u0089\3\2\2\2+\u008b\3\2\2\2-\u008d\3\2\2\2/\u008f"+
		"\3\2\2\2\61\u0091\3\2\2\2\63\u0093\3\2\2\2\65\u0095\3\2\2\2\67\u0099\3"+
		"\2\2\29\u009b\3\2\2\2;\u009d\3\2\2\2=\u009f\3\2\2\2?\u00a2\3\2\2\2A\u00a9"+
		"\3\2\2\2C\u00b0\3\2\2\2E\u00bc\3\2\2\2G\u00c1\3\2\2\2I\u00c7\3\2\2\2K"+
		"\u00cc\3\2\2\2M\u00d1\3\2\2\2O\u00d8\3\2\2\2Q\u00df\3\2\2\2S\u00e3\3\2"+
		"\2\2U\u00eb\3\2\2\2WX\7\60\2\2X\4\3\2\2\2YZ\7]\2\2Z\6\3\2\2\2[\\\7_\2"+
		"\2\\\b\3\2\2\2]^\7#\2\2^\n\3\2\2\2_`\7/\2\2`\f\3\2\2\2ab\7(\2\2b\16\3"+
		"\2\2\2cd\7~\2\2d\20\3\2\2\2ef\7z\2\2fg\7p\2\2gh\7q\2\2hi\7t\2\2i\22\3"+
		"\2\2\2jk\7z\2\2kl\7q\2\2lm\7t\2\2m\24\3\2\2\2no\7>\2\2op\7/\2\2pq\7@\2"+
		"\2q\26\3\2\2\2rs\7/\2\2st\7@\2\2t\30\3\2\2\2uv\7?\2\2v\32\3\2\2\2wx\7"+
		"#\2\2xy\7?\2\2y\34\3\2\2\2z{\7@\2\2{\36\3\2\2\2|}\7@\2\2}~\7?\2\2~ \3"+
		"\2\2\2\177\u0080\7>\2\2\u0080\"\3\2\2\2\u0081\u0082\7>\2\2\u0082\u0083"+
		"\7?\2\2\u0083$\3\2\2\2\u0084\u0085\7\60\2\2\u0085\u0086\7\60\2\2\u0086"+
		"&\3\2\2\2\u0087\u0088\7}\2\2\u0088(\3\2\2\2\u0089\u008a\7.\2\2\u008a*"+
		"\3\2\2\2\u008b\u008c\7\177\2\2\u008c,\3\2\2\2\u008d\u008e\7*\2\2\u008e"+
		".\3\2\2\2\u008f\u0090\7+\2\2\u0090\60\3\2\2\2\u0091\u0092\7,\2\2\u0092"+
		"\62\3\2\2\2\u0093\u0094\7\61\2\2\u0094\64\3\2\2\2\u0095\u0096\7o\2\2\u0096"+
		"\u0097\7q\2\2\u0097\u0098\7f\2\2\u0098\66\3\2\2\2\u0099\u009a\7-\2\2\u009a"+
		"8\3\2\2\2\u009b\u009c\7A\2\2\u009c:\3\2\2\2\u009d\u009e\7<\2\2\u009e<"+
		"\3\2\2\2\u009f\u00a0\7<\2\2\u00a0\u00a1\7?\2\2\u00a1>\3\2\2\2\u00a2\u00a3"+
		"\7=\2\2\u00a3@\3\2\2\2\u00a4\u00aa\t\2\2\2\u00a5\u00a7\7\17\2\2\u00a6"+
		"\u00a5\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00aa\7\f"+
		"\2\2\u00a9\u00a4\3\2\2\2\u00a9\u00a6\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab"+
		"\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\b!"+
		"\2\2\u00aeB\3\2\2\2\u00af\u00b1\7/\2\2\u00b0\u00af\3\2\2\2\u00b0\u00b1"+
		"\3\2\2\2\u00b1\u00ba\3\2\2\2\u00b2\u00bb\7\62\2\2\u00b3\u00b7\4\63;\2"+
		"\u00b4\u00b6\4\62;\2\u00b5\u00b4\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5"+
		"\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba"+
		"\u00b2\3\2\2\2\u00ba\u00b3\3\2\2\2\u00bbD\3\2\2\2\u00bc\u00bd\7V\2\2\u00bd"+
		"\u00be\7T\2\2\u00be\u00bf\7W\2\2\u00bf\u00c0\7G\2\2\u00c0F\3\2\2\2\u00c1"+
		"\u00c2\7H\2\2\u00c2\u00c3\7C\2\2\u00c3\u00c4\7N\2\2\u00c4\u00c5\7U\2\2"+
		"\u00c5\u00c6\7G\2\2\u00c6H\3\2\2\2\u00c7\u00c8\7k\2\2\u00c8\u00c9\7p\2"+
		"\2\u00c9\u00ca\7k\2\2\u00ca\u00cb\7v\2\2\u00cbJ\3\2\2\2\u00cc\u00cd\7"+
		"p\2\2\u00cd\u00ce\7g\2\2\u00ce\u00cf\7z\2\2\u00cf\u00d0\7v\2\2\u00d0L"+
		"\3\2\2\2\u00d1\u00d2\7O\2\2\u00d2\u00d3\7Q\2\2\u00d3\u00d4\7F\2\2\u00d4"+
		"\u00d5\7W\2\2\u00d5\u00d6\7N\2\2\u00d6\u00d7\7G\2\2\u00d7N\3\2\2\2\u00d8"+
		"\u00d9\7C\2\2\u00d9\u00da\7U\2\2\u00da\u00db\7U\2\2\u00db\u00dc\7K\2\2"+
		"\u00dc\u00dd\7I\2\2\u00dd\u00de\7P\2\2\u00deP\3\2\2\2\u00df\u00e0\7X\2"+
		"\2\u00e0\u00e1\7C\2\2\u00e1\u00e2\7T\2\2\u00e2R\3\2\2\2\u00e3\u00e4\7"+
		"d\2\2\u00e4\u00e5\7q\2\2\u00e5\u00e6\7q\2\2\u00e6\u00e7\7n\2\2\u00e7\u00e8"+
		"\7g\2\2\u00e8\u00e9\7c\2\2\u00e9\u00ea\7p\2\2\u00eaT\3\2\2\2\u00eb\u00ef"+
		"\t\3\2\2\u00ec\u00ee\t\4\2\2\u00ed\u00ec\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef"+
		"\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0V\3\2\2\2\u00f1\u00ef\3\2\2\2"+
		"\n\2\u00a6\u00a9\u00ab\u00b0\u00b7\u00ba\u00ef\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}