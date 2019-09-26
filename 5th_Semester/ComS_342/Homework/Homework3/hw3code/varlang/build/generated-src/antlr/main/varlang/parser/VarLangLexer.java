// Generated from varlang\parser\VarLang.g by ANTLR 4.5
package varlang.parser; import static varlang.AST.*;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class VarLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, Define=8, Let=9, 
		Dot=10, Number=11, Identifier=12, Letter=13, LetterOrDigit=14, AT=15, 
		ELLIPSIS=16, WS=17, Comment=18, Line_Comment=19;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "Define", "Let", 
		"Dot", "Number", "Identifier", "Letter", "LetterOrDigit", "DIGIT", "AT", 
		"ELLIPSIS", "WS", "Comment", "Line_Comment"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'lete'", "'-'", "'+'", "'*'", "'/'", "'define'", 
		"'let'", "'.'", null, null, null, null, "'@'", "'...'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "Define", "Let", "Dot", 
		"Number", "Identifier", "Letter", "LetterOrDigit", "AT", "ELLIPSIS", "WS", 
		"Comment", "Line_Comment"
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


	public VarLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "VarLang.g"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 12:
			return Letter_sempred((RuleContext)_localctx, predIndex);
		case 13:
			return LetterOrDigit_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean Letter_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return Character.isJavaIdentifierStart(_input.LA(-1));
		case 1:
			return Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)));
		}
		return true;
	}
	private boolean LetterOrDigit_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return Character.isJavaIdentifierPart(_input.LA(-1));
		case 3:
			return Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)));
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\25\u008d\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\f\6\fK\n\f\r\f\16\fL\3\r\3\r\7\rQ\n\r\f\r\16\rT\13"+
		"\r\3\16\3\16\3\16\3\16\3\16\3\16\5\16\\\n\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\5\17d\n\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\23\6\23o\n"+
		"\23\r\23\16\23p\3\23\3\23\3\24\3\24\3\24\3\24\7\24y\n\24\f\24\16\24|\13"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\7\25\u0087\n\25\f\25"+
		"\16\25\u008a\13\25\3\25\3\25\3z\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\2!\21#\22%\23\'\24)\25\3\2\t\6"+
		"\2&&C\\aac|\4\2\2\u0101\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001\7"+
		"\2&&\62;C\\aac|\5\2\13\f\16\17\"\"\4\2\f\f\17\17\u0094\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\3+\3\2\2\2\5-\3\2\2\2\7/\3\2\2\2\t\64\3\2\2\2\13\66\3\2\2\2\r"+
		"8\3\2\2\2\17:\3\2\2\2\21<\3\2\2\2\23C\3\2\2\2\25G\3\2\2\2\27J\3\2\2\2"+
		"\31N\3\2\2\2\33[\3\2\2\2\35c\3\2\2\2\37e\3\2\2\2!g\3\2\2\2#i\3\2\2\2%"+
		"n\3\2\2\2\'t\3\2\2\2)\u0082\3\2\2\2+,\7*\2\2,\4\3\2\2\2-.\7+\2\2.\6\3"+
		"\2\2\2/\60\7n\2\2\60\61\7g\2\2\61\62\7v\2\2\62\63\7g\2\2\63\b\3\2\2\2"+
		"\64\65\7/\2\2\65\n\3\2\2\2\66\67\7-\2\2\67\f\3\2\2\289\7,\2\29\16\3\2"+
		"\2\2:;\7\61\2\2;\20\3\2\2\2<=\7f\2\2=>\7g\2\2>?\7h\2\2?@\7k\2\2@A\7p\2"+
		"\2AB\7g\2\2B\22\3\2\2\2CD\7n\2\2DE\7g\2\2EF\7v\2\2F\24\3\2\2\2GH\7\60"+
		"\2\2H\26\3\2\2\2IK\5\37\20\2JI\3\2\2\2KL\3\2\2\2LJ\3\2\2\2LM\3\2\2\2M"+
		"\30\3\2\2\2NR\5\33\16\2OQ\5\35\17\2PO\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3"+
		"\2\2\2S\32\3\2\2\2TR\3\2\2\2U\\\t\2\2\2VW\n\3\2\2W\\\6\16\2\2XY\t\4\2"+
		"\2YZ\t\5\2\2Z\\\6\16\3\2[U\3\2\2\2[V\3\2\2\2[X\3\2\2\2\\\34\3\2\2\2]d"+
		"\t\6\2\2^_\n\3\2\2_d\6\17\4\2`a\t\4\2\2ab\t\5\2\2bd\6\17\5\2c]\3\2\2\2"+
		"c^\3\2\2\2c`\3\2\2\2d\36\3\2\2\2ef\4\62;\2f \3\2\2\2gh\7B\2\2h\"\3\2\2"+
		"\2ij\7\60\2\2jk\7\60\2\2kl\7\60\2\2l$\3\2\2\2mo\t\7\2\2nm\3\2\2\2op\3"+
		"\2\2\2pn\3\2\2\2pq\3\2\2\2qr\3\2\2\2rs\b\23\2\2s&\3\2\2\2tu\7\61\2\2u"+
		"v\7,\2\2vz\3\2\2\2wy\13\2\2\2xw\3\2\2\2y|\3\2\2\2z{\3\2\2\2zx\3\2\2\2"+
		"{}\3\2\2\2|z\3\2\2\2}~\7,\2\2~\177\7\61\2\2\177\u0080\3\2\2\2\u0080\u0081"+
		"\b\24\2\2\u0081(\3\2\2\2\u0082\u0083\7\61\2\2\u0083\u0084\7\61\2\2\u0084"+
		"\u0088\3\2\2\2\u0085\u0087\n\b\2\2\u0086\u0085\3\2\2\2\u0087\u008a\3\2"+
		"\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008b\3\2\2\2\u008a"+
		"\u0088\3\2\2\2\u008b\u008c\b\25\2\2\u008c*\3\2\2\2\n\2LR[cpz\u0088\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}