// Generated from arithlang/parser/ArithLang.g by ANTLR 4.5
package arithlang.parser; import static arithlang.AST.*;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ArithLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, Define=5, Let=6, Dot=7, Letter=8, AT=9, 
		ELLIPSIS=10, WS=11, Comment=12, Line_Comment=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "Define", "Let", "Dot", "Letter", "LETTER", 
		"AT", "ELLIPSIS", "WS", "Comment", "Line_Comment"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "'+'", "')'", "'*'", "'define'", "'let'", "'.'", null, "'@'", 
		"'...'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "Define", "Let", "Dot", "Letter", "AT", 
		"ELLIPSIS", "WS", "Comment", "Line_Comment"
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


	public ArithLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ArithLang.g"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\17^\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\r\6\r@\n\r\r\r\16\rA\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\7\16J\n\16\f\16\16\16M\13\16\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\7\17X\n\17\f\17\16\17[\13\17\3\17\3\17\3K\2\20\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\2\25\13\27\f\31\r\33\16\35\17\3\2\5\5\2g"+
		"gqqww\5\2\13\f\16\17\"\"\4\2\f\f\17\17_\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3\37"+
		"\3\2\2\2\5!\3\2\2\2\7#\3\2\2\2\t%\3\2\2\2\13\'\3\2\2\2\r.\3\2\2\2\17\62"+
		"\3\2\2\2\21\64\3\2\2\2\23\66\3\2\2\2\258\3\2\2\2\27:\3\2\2\2\31?\3\2\2"+
		"\2\33E\3\2\2\2\35S\3\2\2\2\37 \7*\2\2 \4\3\2\2\2!\"\7-\2\2\"\6\3\2\2\2"+
		"#$\7+\2\2$\b\3\2\2\2%&\7,\2\2&\n\3\2\2\2\'(\7f\2\2()\7g\2\2)*\7h\2\2*"+
		"+\7k\2\2+,\7p\2\2,-\7g\2\2-\f\3\2\2\2./\7n\2\2/\60\7g\2\2\60\61\7v\2\2"+
		"\61\16\3\2\2\2\62\63\7\60\2\2\63\20\3\2\2\2\64\65\5\23\n\2\65\22\3\2\2"+
		"\2\66\67\t\2\2\2\67\24\3\2\2\289\7B\2\29\26\3\2\2\2:;\7\60\2\2;<\7\60"+
		"\2\2<=\7\60\2\2=\30\3\2\2\2>@\t\3\2\2?>\3\2\2\2@A\3\2\2\2A?\3\2\2\2AB"+
		"\3\2\2\2BC\3\2\2\2CD\b\r\2\2D\32\3\2\2\2EF\7\61\2\2FG\7,\2\2GK\3\2\2\2"+
		"HJ\13\2\2\2IH\3\2\2\2JM\3\2\2\2KL\3\2\2\2KI\3\2\2\2LN\3\2\2\2MK\3\2\2"+
		"\2NO\7,\2\2OP\7\61\2\2PQ\3\2\2\2QR\b\16\2\2R\34\3\2\2\2ST\7\61\2\2TU\7"+
		"\61\2\2UY\3\2\2\2VX\n\4\2\2WV\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\\"+
		"\3\2\2\2[Y\3\2\2\2\\]\b\17\2\2]\36\3\2\2\2\6\2AKY\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}