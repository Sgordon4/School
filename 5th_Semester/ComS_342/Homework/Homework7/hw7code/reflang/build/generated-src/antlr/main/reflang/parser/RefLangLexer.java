// Generated from reflang\parser\RefLang.g by ANTLR 4.5
package reflang.parser; import static reflang.AST.*;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RefLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, Define=14, Let=15, Letrec=16, Lambda=17, 
		If=18, Car=19, Cdr=20, Cons=21, List=22, Null=23, Less=24, Equal=25, Greater=26, 
		TrueLiteral=27, FalseLiteral=28, Ref=29, Deref=30, Assign=31, Free=32, 
		Fork=33, Lock=34, UnLock=35, Process=36, Send=37, Stop=38, Self=39, Dot=40, 
		Number=41, Identifier=42, Letter=43, LetterOrDigit=44, StrLiteral=45, 
		AT=46, ELLIPSIS=47, WS=48, Comment=49, Line_Comment=50;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "Define", "Let", "Letrec", "Lambda", 
		"If", "Car", "Cdr", "Cons", "List", "Null", "Less", "Equal", "Greater", 
		"TrueLiteral", "FalseLiteral", "Ref", "Deref", "Assign", "Free", "Fork", 
		"Lock", "UnLock", "Process", "Send", "Stop", "Self", "Dot", "Number", 
		"Identifier", "Letter", "LetterOrDigit", "DIGIT", "ESCQUOTE", "StrLiteral", 
		"AT", "ELLIPSIS", "WS", "Comment", "Line_Comment"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'number?'", "'boolean?'", "'string?'", "'procedure?'", 
		"'list?'", "'pair?'", "'unit?'", "'-'", "'+'", "'*'", "'/'", "'define'", 
		"'let'", "'letrec'", "'lambda'", "'if'", "'car'", "'cdr'", "'cons'", "'list'", 
		"'null?'", "'<'", "'='", "'>'", "'#t'", "'#f'", "'ref'", "'deref'", "'set!'", 
		"'free'", "'fork'", "'lock'", "'unlock'", "'process'", "'send'", "'stop'", 
		"'self'", "'.'", null, null, null, null, null, "'@'", "'...'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "Define", "Let", "Letrec", "Lambda", "If", "Car", "Cdr", "Cons", 
		"List", "Null", "Less", "Equal", "Greater", "TrueLiteral", "FalseLiteral", 
		"Ref", "Deref", "Assign", "Free", "Fork", "Lock", "UnLock", "Process", 
		"Send", "Stop", "Self", "Dot", "Number", "Identifier", "Letter", "LetterOrDigit", 
		"StrLiteral", "AT", "ELLIPSIS", "WS", "Comment", "Line_Comment"
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


	public RefLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RefLang.g"; }

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
		case 42:
			return Letter_sempred((RuleContext)_localctx, predIndex);
		case 43:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\64\u017c\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\32"+
		"\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3"+
		"\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3&"+
		"\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3)\3)\3*\6*\u012d\n*\r"+
		"*\16*\u012e\3+\3+\7+\u0133\n+\f+\16+\u0136\13+\3,\3,\3,\3,\3,\3,\5,\u013e"+
		"\n,\3-\3-\3-\3-\3-\3-\5-\u0146\n-\3.\3.\3/\3/\3/\3\60\3\60\3\60\7\60\u0150"+
		"\n\60\f\60\16\60\u0153\13\60\3\60\3\60\3\61\3\61\3\62\3\62\3\62\3\62\3"+
		"\63\6\63\u015e\n\63\r\63\16\63\u015f\3\63\3\63\3\64\3\64\3\64\3\64\7\64"+
		"\u0168\n\64\f\64\16\64\u016b\13\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65"+
		"\3\65\3\65\7\65\u0176\n\65\f\65\16\65\u0179\13\65\3\65\3\65\4\u0151\u0169"+
		"\2\66\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[\2]\2_/a\60c\61e\62g\63i\64\3\2\t"+
		"\6\2&&C\\aac|\4\2\2\u0101\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001"+
		"\7\2&&\62;C\\aac|\4\2\f\f\17\17\5\2\13\f\16\17\"\"\u0184\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2"+
		"\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2"+
		"K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3"+
		"\2\2\2\2Y\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2"+
		"\2\2i\3\2\2\2\3k\3\2\2\2\5m\3\2\2\2\7o\3\2\2\2\tw\3\2\2\2\13\u0080\3\2"+
		"\2\2\r\u0088\3\2\2\2\17\u0093\3\2\2\2\21\u0099\3\2\2\2\23\u009f\3\2\2"+
		"\2\25\u00a5\3\2\2\2\27\u00a7\3\2\2\2\31\u00a9\3\2\2\2\33\u00ab\3\2\2\2"+
		"\35\u00ad\3\2\2\2\37\u00b4\3\2\2\2!\u00b8\3\2\2\2#\u00bf\3\2\2\2%\u00c6"+
		"\3\2\2\2\'\u00c9\3\2\2\2)\u00cd\3\2\2\2+\u00d1\3\2\2\2-\u00d6\3\2\2\2"+
		"/\u00db\3\2\2\2\61\u00e1\3\2\2\2\63\u00e3\3\2\2\2\65\u00e5\3\2\2\2\67"+
		"\u00e7\3\2\2\29\u00ea\3\2\2\2;\u00ed\3\2\2\2=\u00f1\3\2\2\2?\u00f7\3\2"+
		"\2\2A\u00fc\3\2\2\2C\u0101\3\2\2\2E\u0106\3\2\2\2G\u010b\3\2\2\2I\u0112"+
		"\3\2\2\2K\u011a\3\2\2\2M\u011f\3\2\2\2O\u0124\3\2\2\2Q\u0129\3\2\2\2S"+
		"\u012c\3\2\2\2U\u0130\3\2\2\2W\u013d\3\2\2\2Y\u0145\3\2\2\2[\u0147\3\2"+
		"\2\2]\u0149\3\2\2\2_\u014c\3\2\2\2a\u0156\3\2\2\2c\u0158\3\2\2\2e\u015d"+
		"\3\2\2\2g\u0163\3\2\2\2i\u0171\3\2\2\2kl\7*\2\2l\4\3\2\2\2mn\7+\2\2n\6"+
		"\3\2\2\2op\7p\2\2pq\7w\2\2qr\7o\2\2rs\7d\2\2st\7g\2\2tu\7t\2\2uv\7A\2"+
		"\2v\b\3\2\2\2wx\7d\2\2xy\7q\2\2yz\7q\2\2z{\7n\2\2{|\7g\2\2|}\7c\2\2}~"+
		"\7p\2\2~\177\7A\2\2\177\n\3\2\2\2\u0080\u0081\7u\2\2\u0081\u0082\7v\2"+
		"\2\u0082\u0083\7t\2\2\u0083\u0084\7k\2\2\u0084\u0085\7p\2\2\u0085\u0086"+
		"\7i\2\2\u0086\u0087\7A\2\2\u0087\f\3\2\2\2\u0088\u0089\7r\2\2\u0089\u008a"+
		"\7t\2\2\u008a\u008b\7q\2\2\u008b\u008c\7e\2\2\u008c\u008d\7g\2\2\u008d"+
		"\u008e\7f\2\2\u008e\u008f\7w\2\2\u008f\u0090\7t\2\2\u0090\u0091\7g\2\2"+
		"\u0091\u0092\7A\2\2\u0092\16\3\2\2\2\u0093\u0094\7n\2\2\u0094\u0095\7"+
		"k\2\2\u0095\u0096\7u\2\2\u0096\u0097\7v\2\2\u0097\u0098\7A\2\2\u0098\20"+
		"\3\2\2\2\u0099\u009a\7r\2\2\u009a\u009b\7c\2\2\u009b\u009c\7k\2\2\u009c"+
		"\u009d\7t\2\2\u009d\u009e\7A\2\2\u009e\22\3\2\2\2\u009f\u00a0\7w\2\2\u00a0"+
		"\u00a1\7p\2\2\u00a1\u00a2\7k\2\2\u00a2\u00a3\7v\2\2\u00a3\u00a4\7A\2\2"+
		"\u00a4\24\3\2\2\2\u00a5\u00a6\7/\2\2\u00a6\26\3\2\2\2\u00a7\u00a8\7-\2"+
		"\2\u00a8\30\3\2\2\2\u00a9\u00aa\7,\2\2\u00aa\32\3\2\2\2\u00ab\u00ac\7"+
		"\61\2\2\u00ac\34\3\2\2\2\u00ad\u00ae\7f\2\2\u00ae\u00af\7g\2\2\u00af\u00b0"+
		"\7h\2\2\u00b0\u00b1\7k\2\2\u00b1\u00b2\7p\2\2\u00b2\u00b3\7g\2\2\u00b3"+
		"\36\3\2\2\2\u00b4\u00b5\7n\2\2\u00b5\u00b6\7g\2\2\u00b6\u00b7\7v\2\2\u00b7"+
		" \3\2\2\2\u00b8\u00b9\7n\2\2\u00b9\u00ba\7g\2\2\u00ba\u00bb\7v\2\2\u00bb"+
		"\u00bc\7t\2\2\u00bc\u00bd\7g\2\2\u00bd\u00be\7e\2\2\u00be\"\3\2\2\2\u00bf"+
		"\u00c0\7n\2\2\u00c0\u00c1\7c\2\2\u00c1\u00c2\7o\2\2\u00c2\u00c3\7d\2\2"+
		"\u00c3\u00c4\7f\2\2\u00c4\u00c5\7c\2\2\u00c5$\3\2\2\2\u00c6\u00c7\7k\2"+
		"\2\u00c7\u00c8\7h\2\2\u00c8&\3\2\2\2\u00c9\u00ca\7e\2\2\u00ca\u00cb\7"+
		"c\2\2\u00cb\u00cc\7t\2\2\u00cc(\3\2\2\2\u00cd\u00ce\7e\2\2\u00ce\u00cf"+
		"\7f\2\2\u00cf\u00d0\7t\2\2\u00d0*\3\2\2\2\u00d1\u00d2\7e\2\2\u00d2\u00d3"+
		"\7q\2\2\u00d3\u00d4\7p\2\2\u00d4\u00d5\7u\2\2\u00d5,\3\2\2\2\u00d6\u00d7"+
		"\7n\2\2\u00d7\u00d8\7k\2\2\u00d8\u00d9\7u\2\2\u00d9\u00da\7v\2\2\u00da"+
		".\3\2\2\2\u00db\u00dc\7p\2\2\u00dc\u00dd\7w\2\2\u00dd\u00de\7n\2\2\u00de"+
		"\u00df\7n\2\2\u00df\u00e0\7A\2\2\u00e0\60\3\2\2\2\u00e1\u00e2\7>\2\2\u00e2"+
		"\62\3\2\2\2\u00e3\u00e4\7?\2\2\u00e4\64\3\2\2\2\u00e5\u00e6\7@\2\2\u00e6"+
		"\66\3\2\2\2\u00e7\u00e8\7%\2\2\u00e8\u00e9\7v\2\2\u00e98\3\2\2\2\u00ea"+
		"\u00eb\7%\2\2\u00eb\u00ec\7h\2\2\u00ec:\3\2\2\2\u00ed\u00ee\7t\2\2\u00ee"+
		"\u00ef\7g\2\2\u00ef\u00f0\7h\2\2\u00f0<\3\2\2\2\u00f1\u00f2\7f\2\2\u00f2"+
		"\u00f3\7g\2\2\u00f3\u00f4\7t\2\2\u00f4\u00f5\7g\2\2\u00f5\u00f6\7h\2\2"+
		"\u00f6>\3\2\2\2\u00f7\u00f8\7u\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7v\2"+
		"\2\u00fa\u00fb\7#\2\2\u00fb@\3\2\2\2\u00fc\u00fd\7h\2\2\u00fd\u00fe\7"+
		"t\2\2\u00fe\u00ff\7g\2\2\u00ff\u0100\7g\2\2\u0100B\3\2\2\2\u0101\u0102"+
		"\7h\2\2\u0102\u0103\7q\2\2\u0103\u0104\7t\2\2\u0104\u0105\7m\2\2\u0105"+
		"D\3\2\2\2\u0106\u0107\7n\2\2\u0107\u0108\7q\2\2\u0108\u0109\7e\2\2\u0109"+
		"\u010a\7m\2\2\u010aF\3\2\2\2\u010b\u010c\7w\2\2\u010c\u010d\7p\2\2\u010d"+
		"\u010e\7n\2\2\u010e\u010f\7q\2\2\u010f\u0110\7e\2\2\u0110\u0111\7m\2\2"+
		"\u0111H\3\2\2\2\u0112\u0113\7r\2\2\u0113\u0114\7t\2\2\u0114\u0115\7q\2"+
		"\2\u0115\u0116\7e\2\2\u0116\u0117\7g\2\2\u0117\u0118\7u\2\2\u0118\u0119"+
		"\7u\2\2\u0119J\3\2\2\2\u011a\u011b\7u\2\2\u011b\u011c\7g\2\2\u011c\u011d"+
		"\7p\2\2\u011d\u011e\7f\2\2\u011eL\3\2\2\2\u011f\u0120\7u\2\2\u0120\u0121"+
		"\7v\2\2\u0121\u0122\7q\2\2\u0122\u0123\7r\2\2\u0123N\3\2\2\2\u0124\u0125"+
		"\7u\2\2\u0125\u0126\7g\2\2\u0126\u0127\7n\2\2\u0127\u0128\7h\2\2\u0128"+
		"P\3\2\2\2\u0129\u012a\7\60\2\2\u012aR\3\2\2\2\u012b\u012d\5[.\2\u012c"+
		"\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2"+
		"\2\2\u012fT\3\2\2\2\u0130\u0134\5W,\2\u0131\u0133\5Y-\2\u0132\u0131\3"+
		"\2\2\2\u0133\u0136\3\2\2\2\u0134\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135"+
		"V\3\2\2\2\u0136\u0134\3\2\2\2\u0137\u013e\t\2\2\2\u0138\u0139\n\3\2\2"+
		"\u0139\u013e\6,\2\2\u013a\u013b\t\4\2\2\u013b\u013c\t\5\2\2\u013c\u013e"+
		"\6,\3\2\u013d\u0137\3\2\2\2\u013d\u0138\3\2\2\2\u013d\u013a\3\2\2\2\u013e"+
		"X\3\2\2\2\u013f\u0146\t\6\2\2\u0140\u0141\n\3\2\2\u0141\u0146\6-\4\2\u0142"+
		"\u0143\t\4\2\2\u0143\u0144\t\5\2\2\u0144\u0146\6-\5\2\u0145\u013f\3\2"+
		"\2\2\u0145\u0140\3\2\2\2\u0145\u0142\3\2\2\2\u0146Z\3\2\2\2\u0147\u0148"+
		"\4\62;\2\u0148\\\3\2\2\2\u0149\u014a\7^\2\2\u014a\u014b\7$\2\2\u014b^"+
		"\3\2\2\2\u014c\u0151\7$\2\2\u014d\u0150\5]/\2\u014e\u0150\n\7\2\2\u014f"+
		"\u014d\3\2\2\2\u014f\u014e\3\2\2\2\u0150\u0153\3\2\2\2\u0151\u0152\3\2"+
		"\2\2\u0151\u014f\3\2\2\2\u0152\u0154\3\2\2\2\u0153\u0151\3\2\2\2\u0154"+
		"\u0155\7$\2\2\u0155`\3\2\2\2\u0156\u0157\7B\2\2\u0157b\3\2\2\2\u0158\u0159"+
		"\7\60\2\2\u0159\u015a\7\60\2\2\u015a\u015b\7\60\2\2\u015bd\3\2\2\2\u015c"+
		"\u015e\t\b\2\2\u015d\u015c\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u015d\3\2"+
		"\2\2\u015f\u0160\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u0162\b\63\2\2\u0162"+
		"f\3\2\2\2\u0163\u0164\7\61\2\2\u0164\u0165\7,\2\2\u0165\u0169\3\2\2\2"+
		"\u0166\u0168\13\2\2\2\u0167\u0166\3\2\2\2\u0168\u016b\3\2\2\2\u0169\u016a"+
		"\3\2\2\2\u0169\u0167\3\2\2\2\u016a\u016c\3\2\2\2\u016b\u0169\3\2\2\2\u016c"+
		"\u016d\7,\2\2\u016d\u016e\7\61\2\2\u016e\u016f\3\2\2\2\u016f\u0170\b\64"+
		"\2\2\u0170h\3\2\2\2\u0171\u0172\7\61\2\2\u0172\u0173\7\61\2\2\u0173\u0177"+
		"\3\2\2\2\u0174\u0176\n\7\2\2\u0175\u0174\3\2\2\2\u0176\u0179\3\2\2\2\u0177"+
		"\u0175\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u017a\3\2\2\2\u0179\u0177\3\2"+
		"\2\2\u017a\u017b\b\65\2\2\u017bj\3\2\2\2\f\2\u012e\u0134\u013d\u0145\u014f"+
		"\u0151\u015f\u0169\u0177\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}