// Generated from funclang/parser/FuncLang.g by ANTLR 4.5
package funclang.parser; import static funclang.AST.*;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FuncLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, Define=7, Let=8, Letrec=9, 
		Lambda=10, If=11, Car=12, Cdr=13, Cons=14, List=15, Null=16, Less=17, 
		Equal=18, Greater=19, TrueLiteral=20, FalseLiteral=21, Ref=22, Deref=23, 
		Assign=24, Free=25, Fork=26, Lock=27, UnLock=28, Process=29, Send=30, 
		Stop=31, Self=32, Dot=33, Number=34, Identifier=35, Letter=36, LetterOrDigit=37, 
		StrLiteral=38, AT=39, ELLIPSIS=40, WS=41, Comment=42, Line_Comment=43;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "Define", "Let", "Letrec", 
		"Lambda", "If", "Car", "Cdr", "Cons", "List", "Null", "Less", "Equal", 
		"Greater", "TrueLiteral", "FalseLiteral", "Ref", "Deref", "Assign", "Free", 
		"Fork", "Lock", "UnLock", "Process", "Send", "Stop", "Self", "Dot", "Number", 
		"Identifier", "Letter", "LetterOrDigit", "DIGIT", "ESCQUOTE", "StrLiteral", 
		"AT", "ELLIPSIS", "WS", "Comment", "Line_Comment"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'-'", "'+'", "'*'", "'/'", "'define'", "'let'", "'letrec'", 
		"'lambda'", "'if'", "'car'", "'cdr'", "'cons'", "'list'", "'null?'", "'<'", 
		"'='", "'>'", "'#t'", "'#f'", "'ref'", "'deref'", "'set!'", "'free'", 
		"'fork'", "'lock'", "'unlock'", "'process'", "'send'", "'stop'", "'self'", 
		"'.'", null, null, null, null, null, "'@'", "'...'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "Define", "Let", "Letrec", "Lambda", 
		"If", "Car", "Cdr", "Cons", "List", "Null", "Less", "Equal", "Greater", 
		"TrueLiteral", "FalseLiteral", "Ref", "Deref", "Assign", "Free", "Fork", 
		"Lock", "UnLock", "Process", "Send", "Stop", "Self", "Dot", "Number", 
		"Identifier", "Letter", "LetterOrDigit", "StrLiteral", "AT", "ELLIPSIS", 
		"WS", "Comment", "Line_Comment"
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


	public FuncLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FuncLang.g"; }

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
		case 35:
			return Letter_sempred((RuleContext)_localctx, predIndex);
		case 36:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2-\u0138\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3"+
		"\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3"+
		"\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!"+
		"\3!\3!\3!\3\"\3\"\3#\6#\u00e9\n#\r#\16#\u00ea\3$\3$\7$\u00ef\n$\f$\16"+
		"$\u00f2\13$\3%\3%\3%\3%\3%\3%\5%\u00fa\n%\3&\3&\3&\3&\3&\3&\5&\u0102\n"+
		"&\3\'\3\'\3(\3(\3(\3)\3)\3)\7)\u010c\n)\f)\16)\u010f\13)\3)\3)\3*\3*\3"+
		"+\3+\3+\3+\3,\6,\u011a\n,\r,\16,\u011b\3,\3,\3-\3-\3-\3-\7-\u0124\n-\f"+
		"-\16-\u0127\13-\3-\3-\3-\3-\3-\3.\3.\3.\3.\7.\u0132\n.\f.\16.\u0135\13"+
		".\3.\3.\4\u010d\u0125\2/\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63"+
		"\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M\2O\2Q(S)U*W+Y,[-\3\2\t\6\2"+
		"&&C\\aac|\4\2\2\u0101\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001\7\2"+
		"&&\62;C\\aac|\4\2\f\f\17\17\5\2\13\f\16\17\"\"\u0140\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63"+
		"\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2"+
		"?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3"+
		"\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2"+
		"\2\3]\3\2\2\2\5_\3\2\2\2\7a\3\2\2\2\tc\3\2\2\2\13e\3\2\2\2\rg\3\2\2\2"+
		"\17i\3\2\2\2\21p\3\2\2\2\23t\3\2\2\2\25{\3\2\2\2\27\u0082\3\2\2\2\31\u0085"+
		"\3\2\2\2\33\u0089\3\2\2\2\35\u008d\3\2\2\2\37\u0092\3\2\2\2!\u0097\3\2"+
		"\2\2#\u009d\3\2\2\2%\u009f\3\2\2\2\'\u00a1\3\2\2\2)\u00a3\3\2\2\2+\u00a6"+
		"\3\2\2\2-\u00a9\3\2\2\2/\u00ad\3\2\2\2\61\u00b3\3\2\2\2\63\u00b8\3\2\2"+
		"\2\65\u00bd\3\2\2\2\67\u00c2\3\2\2\29\u00c7\3\2\2\2;\u00ce\3\2\2\2=\u00d6"+
		"\3\2\2\2?\u00db\3\2\2\2A\u00e0\3\2\2\2C\u00e5\3\2\2\2E\u00e8\3\2\2\2G"+
		"\u00ec\3\2\2\2I\u00f9\3\2\2\2K\u0101\3\2\2\2M\u0103\3\2\2\2O\u0105\3\2"+
		"\2\2Q\u0108\3\2\2\2S\u0112\3\2\2\2U\u0114\3\2\2\2W\u0119\3\2\2\2Y\u011f"+
		"\3\2\2\2[\u012d\3\2\2\2]^\7*\2\2^\4\3\2\2\2_`\7+\2\2`\6\3\2\2\2ab\7/\2"+
		"\2b\b\3\2\2\2cd\7-\2\2d\n\3\2\2\2ef\7,\2\2f\f\3\2\2\2gh\7\61\2\2h\16\3"+
		"\2\2\2ij\7f\2\2jk\7g\2\2kl\7h\2\2lm\7k\2\2mn\7p\2\2no\7g\2\2o\20\3\2\2"+
		"\2pq\7n\2\2qr\7g\2\2rs\7v\2\2s\22\3\2\2\2tu\7n\2\2uv\7g\2\2vw\7v\2\2w"+
		"x\7t\2\2xy\7g\2\2yz\7e\2\2z\24\3\2\2\2{|\7n\2\2|}\7c\2\2}~\7o\2\2~\177"+
		"\7d\2\2\177\u0080\7f\2\2\u0080\u0081\7c\2\2\u0081\26\3\2\2\2\u0082\u0083"+
		"\7k\2\2\u0083\u0084\7h\2\2\u0084\30\3\2\2\2\u0085\u0086\7e\2\2\u0086\u0087"+
		"\7c\2\2\u0087\u0088\7t\2\2\u0088\32\3\2\2\2\u0089\u008a\7e\2\2\u008a\u008b"+
		"\7f\2\2\u008b\u008c\7t\2\2\u008c\34\3\2\2\2\u008d\u008e\7e\2\2\u008e\u008f"+
		"\7q\2\2\u008f\u0090\7p\2\2\u0090\u0091\7u\2\2\u0091\36\3\2\2\2\u0092\u0093"+
		"\7n\2\2\u0093\u0094\7k\2\2\u0094\u0095\7u\2\2\u0095\u0096\7v\2\2\u0096"+
		" \3\2\2\2\u0097\u0098\7p\2\2\u0098\u0099\7w\2\2\u0099\u009a\7n\2\2\u009a"+
		"\u009b\7n\2\2\u009b\u009c\7A\2\2\u009c\"\3\2\2\2\u009d\u009e\7>\2\2\u009e"+
		"$\3\2\2\2\u009f\u00a0\7?\2\2\u00a0&\3\2\2\2\u00a1\u00a2\7@\2\2\u00a2("+
		"\3\2\2\2\u00a3\u00a4\7%\2\2\u00a4\u00a5\7v\2\2\u00a5*\3\2\2\2\u00a6\u00a7"+
		"\7%\2\2\u00a7\u00a8\7h\2\2\u00a8,\3\2\2\2\u00a9\u00aa\7t\2\2\u00aa\u00ab"+
		"\7g\2\2\u00ab\u00ac\7h\2\2\u00ac.\3\2\2\2\u00ad\u00ae\7f\2\2\u00ae\u00af"+
		"\7g\2\2\u00af\u00b0\7t\2\2\u00b0\u00b1\7g\2\2\u00b1\u00b2\7h\2\2\u00b2"+
		"\60\3\2\2\2\u00b3\u00b4\7u\2\2\u00b4\u00b5\7g\2\2\u00b5\u00b6\7v\2\2\u00b6"+
		"\u00b7\7#\2\2\u00b7\62\3\2\2\2\u00b8\u00b9\7h\2\2\u00b9\u00ba\7t\2\2\u00ba"+
		"\u00bb\7g\2\2\u00bb\u00bc\7g\2\2\u00bc\64\3\2\2\2\u00bd\u00be\7h\2\2\u00be"+
		"\u00bf\7q\2\2\u00bf\u00c0\7t\2\2\u00c0\u00c1\7m\2\2\u00c1\66\3\2\2\2\u00c2"+
		"\u00c3\7n\2\2\u00c3\u00c4\7q\2\2\u00c4\u00c5\7e\2\2\u00c5\u00c6\7m\2\2"+
		"\u00c68\3\2\2\2\u00c7\u00c8\7w\2\2\u00c8\u00c9\7p\2\2\u00c9\u00ca\7n\2"+
		"\2\u00ca\u00cb\7q\2\2\u00cb\u00cc\7e\2\2\u00cc\u00cd\7m\2\2\u00cd:\3\2"+
		"\2\2\u00ce\u00cf\7r\2\2\u00cf\u00d0\7t\2\2\u00d0\u00d1\7q\2\2\u00d1\u00d2"+
		"\7e\2\2\u00d2\u00d3\7g\2\2\u00d3\u00d4\7u\2\2\u00d4\u00d5\7u\2\2\u00d5"+
		"<\3\2\2\2\u00d6\u00d7\7u\2\2\u00d7\u00d8\7g\2\2\u00d8\u00d9\7p\2\2\u00d9"+
		"\u00da\7f\2\2\u00da>\3\2\2\2\u00db\u00dc\7u\2\2\u00dc\u00dd\7v\2\2\u00dd"+
		"\u00de\7q\2\2\u00de\u00df\7r\2\2\u00df@\3\2\2\2\u00e0\u00e1\7u\2\2\u00e1"+
		"\u00e2\7g\2\2\u00e2\u00e3\7n\2\2\u00e3\u00e4\7h\2\2\u00e4B\3\2\2\2\u00e5"+
		"\u00e6\7\60\2\2\u00e6D\3\2\2\2\u00e7\u00e9\5M\'\2\u00e8\u00e7\3\2\2\2"+
		"\u00e9\u00ea\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00ebF\3"+
		"\2\2\2\u00ec\u00f0\5I%\2\u00ed\u00ef\5K&\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2"+
		"\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1H\3\2\2\2\u00f2"+
		"\u00f0\3\2\2\2\u00f3\u00fa\t\2\2\2\u00f4\u00f5\n\3\2\2\u00f5\u00fa\6%"+
		"\2\2\u00f6\u00f7\t\4\2\2\u00f7\u00f8\t\5\2\2\u00f8\u00fa\6%\3\2\u00f9"+
		"\u00f3\3\2\2\2\u00f9\u00f4\3\2\2\2\u00f9\u00f6\3\2\2\2\u00faJ\3\2\2\2"+
		"\u00fb\u0102\t\6\2\2\u00fc\u00fd\n\3\2\2\u00fd\u0102\6&\4\2\u00fe\u00ff"+
		"\t\4\2\2\u00ff\u0100\t\5\2\2\u0100\u0102\6&\5\2\u0101\u00fb\3\2\2\2\u0101"+
		"\u00fc\3\2\2\2\u0101\u00fe\3\2\2\2\u0102L\3\2\2\2\u0103\u0104\4\62;\2"+
		"\u0104N\3\2\2\2\u0105\u0106\7^\2\2\u0106\u0107\7$\2\2\u0107P\3\2\2\2\u0108"+
		"\u010d\7$\2\2\u0109\u010c\5O(\2\u010a\u010c\n\7\2\2\u010b\u0109\3\2\2"+
		"\2\u010b\u010a\3\2\2\2\u010c\u010f\3\2\2\2\u010d\u010e\3\2\2\2\u010d\u010b"+
		"\3\2\2\2\u010e\u0110\3\2\2\2\u010f\u010d\3\2\2\2\u0110\u0111\7$\2\2\u0111"+
		"R\3\2\2\2\u0112\u0113\7B\2\2\u0113T\3\2\2\2\u0114\u0115\7\60\2\2\u0115"+
		"\u0116\7\60\2\2\u0116\u0117\7\60\2\2\u0117V\3\2\2\2\u0118\u011a\t\b\2"+
		"\2\u0119\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c"+
		"\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011e\b,\2\2\u011eX\3\2\2\2\u011f\u0120"+
		"\7\61\2\2\u0120\u0121\7,\2\2\u0121\u0125\3\2\2\2\u0122\u0124\13\2\2\2"+
		"\u0123\u0122\3\2\2\2\u0124\u0127\3\2\2\2\u0125\u0126\3\2\2\2\u0125\u0123"+
		"\3\2\2\2\u0126\u0128\3\2\2\2\u0127\u0125\3\2\2\2\u0128\u0129\7,\2\2\u0129"+
		"\u012a\7\61\2\2\u012a\u012b\3\2\2\2\u012b\u012c\b-\2\2\u012cZ\3\2\2\2"+
		"\u012d\u012e\7\61\2\2\u012e\u012f\7\61\2\2\u012f\u0133\3\2\2\2\u0130\u0132"+
		"\n\7\2\2\u0131\u0130\3\2\2\2\u0132\u0135\3\2\2\2\u0133\u0131\3\2\2\2\u0133"+
		"\u0134\3\2\2\2\u0134\u0136\3\2\2\2\u0135\u0133\3\2\2\2\u0136\u0137\b."+
		"\2\2\u0137\\\3\2\2\2\f\2\u00ea\u00f0\u00f9\u0101\u010b\u010d\u011b\u0125"+
		"\u0133\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}