package shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.antlrgenerated;// Generated from C:/Users/ovsianp1/projects/code/SEARCH/cxbacktracker\nusmv.g4 by ANTLR 4.7.2

import java.util.*;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.*;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.expression.*;

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
			"T__25", "T__26", "T__27", "WS", "LINE_COMMENT", "INT_CONST", "TRUE", 
			"FALSE", "INIT", "NEXT", "MODULE", "VAR", "ASSIGN", "DEFINE", "BOOLEAN", 
			"COUNT", "MOD", "XOR", "XNOR", "MAX", "CASE", "ESAC", "ID"
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
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\62\u0128\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r"+
		"\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23"+
		"\3\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36"+
		"\3\36\5\36\u00a6\n\36\3\36\6\36\u00a9\n\36\r\36\16\36\u00aa\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\7\37\u00b3\n\37\f\37\16\37\u00b6\13\37\6\37\u00b8"+
		"\n\37\r\37\16\37\u00b9\3\37\3\37\3 \5 \u00bf\n \3 \3 \3 \7 \u00c4\n \f"+
		" \16 \u00c7\13 \5 \u00c9\n \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#"+
		"\3#\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3"+
		"\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3*\3*"+
		"\3*\3*\3*\3*\3+\3+\3+\3+\3,\3,\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3/\3/"+
		"\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\61\3\61\7\61\u0124\n\61\f\61\16\61"+
		"\u0127\13\61\2\2\62\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62\3\2"+
		"\6\4\2\13\13\"\"\4\2\f\f\17\17\5\2C\\aac|\6\2\62;C\\aac|\2\u0130\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2"+
		"\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I"+
		"\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2"+
		"\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2"+
		"\3c\3\2\2\2\5e\3\2\2\2\7g\3\2\2\2\ti\3\2\2\2\13k\3\2\2\2\rm\3\2\2\2\17"+
		"o\3\2\2\2\21q\3\2\2\2\23s\3\2\2\2\25u\3\2\2\2\27w\3\2\2\2\31y\3\2\2\2"+
		"\33{\3\2\2\2\35}\3\2\2\2\37\177\3\2\2\2!\u0082\3\2\2\2#\u0084\3\2\2\2"+
		"%\u0087\3\2\2\2\'\u0089\3\2\2\2)\u008c\3\2\2\2+\u008e\3\2\2\2-\u0090\3"+
		"\2\2\2/\u0092\3\2\2\2\61\u0096\3\2\2\2\63\u0099\3\2\2\2\65\u009c\3\2\2"+
		"\2\67\u009e\3\2\2\29\u00a0\3\2\2\2;\u00a8\3\2\2\2=\u00b7\3\2\2\2?\u00be"+
		"\3\2\2\2A\u00ca\3\2\2\2C\u00cf\3\2\2\2E\u00d5\3\2\2\2G\u00da\3\2\2\2I"+
		"\u00df\3\2\2\2K\u00e6\3\2\2\2M\u00ea\3\2\2\2O\u00f1\3\2\2\2Q\u00f8\3\2"+
		"\2\2S\u0100\3\2\2\2U\u0106\3\2\2\2W\u010a\3\2\2\2Y\u010e\3\2\2\2[\u0113"+
		"\3\2\2\2]\u0117\3\2\2\2_\u011c\3\2\2\2a\u0121\3\2\2\2cd\7\60\2\2d\4\3"+
		"\2\2\2ef\7]\2\2f\6\3\2\2\2gh\7_\2\2h\b\3\2\2\2ij\7*\2\2j\n\3\2\2\2kl\7"+
		"+\2\2l\f\3\2\2\2mn\7<\2\2n\16\3\2\2\2op\7=\2\2p\20\3\2\2\2qr\7.\2\2r\22"+
		"\3\2\2\2st\7#\2\2t\24\3\2\2\2uv\7/\2\2v\26\3\2\2\2wx\7,\2\2x\30\3\2\2"+
		"\2yz\7\61\2\2z\32\3\2\2\2{|\7-\2\2|\34\3\2\2\2}~\7?\2\2~\36\3\2\2\2\177"+
		"\u0080\7#\2\2\u0080\u0081\7?\2\2\u0081 \3\2\2\2\u0082\u0083\7@\2\2\u0083"+
		"\"\3\2\2\2\u0084\u0085\7@\2\2\u0085\u0086\7?\2\2\u0086$\3\2\2\2\u0087"+
		"\u0088\7>\2\2\u0088&\3\2\2\2\u0089\u008a\7>\2\2\u008a\u008b\7?\2\2\u008b"+
		"(\3\2\2\2\u008c\u008d\7(\2\2\u008d*\3\2\2\2\u008e\u008f\7~\2\2\u008f,"+
		"\3\2\2\2\u0090\u0091\7A\2\2\u0091.\3\2\2\2\u0092\u0093\7>\2\2\u0093\u0094"+
		"\7/\2\2\u0094\u0095\7@\2\2\u0095\60\3\2\2\2\u0096\u0097\7/\2\2\u0097\u0098"+
		"\7@\2\2\u0098\62\3\2\2\2\u0099\u009a\7\60\2\2\u009a\u009b\7\60\2\2\u009b"+
		"\64\3\2\2\2\u009c\u009d\7}\2\2\u009d\66\3\2\2\2\u009e\u009f\7\177\2\2"+
		"\u009f8\3\2\2\2\u00a0\u00a1\7<\2\2\u00a1\u00a2\7?\2\2\u00a2:\3\2\2\2\u00a3"+
		"\u00a9\t\2\2\2\u00a4\u00a6\7\17\2\2\u00a5\u00a4\3\2\2\2\u00a5\u00a6\3"+
		"\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\7\f\2\2\u00a8\u00a3\3\2\2\2\u00a8"+
		"\u00a5\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2"+
		"\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\b\36\2\2\u00ad<\3\2\2\2\u00ae\u00af"+
		"\7/\2\2\u00af\u00b0\7/\2\2\u00b0\u00b4\3\2\2\2\u00b1\u00b3\n\3\2\2\u00b2"+
		"\u00b1\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2"+
		"\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00ae\3\2\2\2\u00b8"+
		"\u00b9\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\3\2"+
		"\2\2\u00bb\u00bc\b\37\2\2\u00bc>\3\2\2\2\u00bd\u00bf\7/\2\2\u00be\u00bd"+
		"\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c8\3\2\2\2\u00c0\u00c9\7\62\2\2"+
		"\u00c1\u00c5\4\63;\2\u00c2\u00c4\4\62;\2\u00c3\u00c2\3\2\2\2\u00c4\u00c7"+
		"\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7"+
		"\u00c5\3\2\2\2\u00c8\u00c0\3\2\2\2\u00c8\u00c1\3\2\2\2\u00c9@\3\2\2\2"+
		"\u00ca\u00cb\7V\2\2\u00cb\u00cc\7T\2\2\u00cc\u00cd\7W\2\2\u00cd\u00ce"+
		"\7G\2\2\u00ceB\3\2\2\2\u00cf\u00d0\7H\2\2\u00d0\u00d1\7C\2\2\u00d1\u00d2"+
		"\7N\2\2\u00d2\u00d3\7U\2\2\u00d3\u00d4\7G\2\2\u00d4D\3\2\2\2\u00d5\u00d6"+
		"\7k\2\2\u00d6\u00d7\7p\2\2\u00d7\u00d8\7k\2\2\u00d8\u00d9\7v\2\2\u00d9"+
		"F\3\2\2\2\u00da\u00db\7p\2\2\u00db\u00dc\7g\2\2\u00dc\u00dd\7z\2\2\u00dd"+
		"\u00de\7v\2\2\u00deH\3\2\2\2\u00df\u00e0\7O\2\2\u00e0\u00e1\7Q\2\2\u00e1"+
		"\u00e2\7F\2\2\u00e2\u00e3\7W\2\2\u00e3\u00e4\7N\2\2\u00e4\u00e5\7G\2\2"+
		"\u00e5J\3\2\2\2\u00e6\u00e7\7X\2\2\u00e7\u00e8\7C\2\2\u00e8\u00e9\7T\2"+
		"\2\u00e9L\3\2\2\2\u00ea\u00eb\7C\2\2\u00eb\u00ec\7U\2\2\u00ec\u00ed\7"+
		"U\2\2\u00ed\u00ee\7K\2\2\u00ee\u00ef\7I\2\2\u00ef\u00f0\7P\2\2\u00f0N"+
		"\3\2\2\2\u00f1\u00f2\7F\2\2\u00f2\u00f3\7G\2\2\u00f3\u00f4\7H\2\2\u00f4"+
		"\u00f5\7K\2\2\u00f5\u00f6\7P\2\2\u00f6\u00f7\7G\2\2\u00f7P\3\2\2\2\u00f8"+
		"\u00f9\7d\2\2\u00f9\u00fa\7q\2\2\u00fa\u00fb\7q\2\2\u00fb\u00fc\7n\2\2"+
		"\u00fc\u00fd\7g\2\2\u00fd\u00fe\7c\2\2\u00fe\u00ff\7p\2\2\u00ffR\3\2\2"+
		"\2\u0100\u0101\7e\2\2\u0101\u0102\7q\2\2\u0102\u0103\7w\2\2\u0103\u0104"+
		"\7p\2\2\u0104\u0105\7v\2\2\u0105T\3\2\2\2\u0106\u0107\7o\2\2\u0107\u0108"+
		"\7q\2\2\u0108\u0109\7f\2\2\u0109V\3\2\2\2\u010a\u010b\7z\2\2\u010b\u010c"+
		"\7q\2\2\u010c\u010d\7t\2\2\u010dX\3\2\2\2\u010e\u010f\7z\2\2\u010f\u0110"+
		"\7p\2\2\u0110\u0111\7q\2\2\u0111\u0112\7t\2\2\u0112Z\3\2\2\2\u0113\u0114"+
		"\7o\2\2\u0114\u0115\7c\2\2\u0115\u0116\7z\2\2\u0116\\\3\2\2\2\u0117\u0118"+
		"\7e\2\2\u0118\u0119\7c\2\2\u0119\u011a\7u\2\2\u011a\u011b\7g\2\2\u011b"+
		"^\3\2\2\2\u011c\u011d\7g\2\2\u011d\u011e\7u\2\2\u011e\u011f\7c\2\2\u011f"+
		"\u0120\7e\2\2\u0120`\3\2\2\2\u0121\u0125\t\4\2\2\u0122\u0124\t\5\2\2\u0123"+
		"\u0122\3\2\2\2\u0124\u0127\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2"+
		"\2\2\u0126b\3\2\2\2\u0127\u0125\3\2\2\2\f\2\u00a5\u00a8\u00aa\u00b4\u00b9"+
		"\u00be\u00c5\u00c8\u0125\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}