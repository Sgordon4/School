// Generated from arithlang/parser/ArithLang.g by ANTLR 4.5
package arithlang.parser; import static arithlang.AST.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ArithLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, Define=5, Let=6, Dot=7, Letter=8, AT=9, 
		ELLIPSIS=10, WS=11, Comment=12, Line_Comment=13;
	public static final int
		RULE_program = 0, RULE_exp = 1, RULE_charexp = 2, RULE_addexp = 3, RULE_multexp = 4;
	public static final String[] ruleNames = {
		"program", "exp", "charexp", "addexp", "multexp"
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

	@Override
	public String getGrammarFileName() { return "ArithLang.g"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ArithLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public Program ast;
		public ExpContext e;
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			((ProgramContext)_localctx).e = exp();
			 ((ProgramContext)_localctx).ast =  new Program(((ProgramContext)_localctx).e.ast); 
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

	public static class ExpContext extends ParserRuleContext {
		public Exp ast;
		public CharexpContext c;
		public AddexpContext a;
		public MultexpContext m;
		public CharexpContext charexp() {
			return getRuleContext(CharexpContext.class,0);
		}
		public AddexpContext addexp() {
			return getRuleContext(AddexpContext.class,0);
		}
		public MultexpContext multexp() {
			return getRuleContext(MultexpContext.class,0);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_exp);
		try {
			setState(22);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(13);
				((ExpContext)_localctx).c = charexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).c.ast; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(16);
				((ExpContext)_localctx).a = addexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).a.ast; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(19);
				((ExpContext)_localctx).m = multexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).m.ast; 
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

	public static class CharexpContext extends ParserRuleContext {
		public CharExp ast;
		public Token c0;
		public TerminalNode Letter() { return getToken(ArithLangParser.Letter, 0); }
		public CharexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charexp; }
	}

	public final CharexpContext charexp() throws RecognitionException {
		CharexpContext _localctx = new CharexpContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_charexp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			((CharexpContext)_localctx).c0 = match(Letter);
			 ((CharexpContext)_localctx).ast =  new CharExp((((CharexpContext)_localctx).c0!=null?((CharexpContext)_localctx).c0.getText():null)); 
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

	public static class AddexpContext extends ParserRuleContext {
		public AddExp ast;
		public ArrayList<Exp> list;
		public ExpContext e;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public AddexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addexp; }
	}

	public final AddexpContext addexp() throws RecognitionException {
		AddexpContext _localctx = new AddexpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_addexp);
		 ((AddexpContext)_localctx).list =  new ArrayList<Exp>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(T__0);
			setState(28);
			match(T__1);
			setState(29);
			((AddexpContext)_localctx).e = exp();
			 _localctx.list.add(((AddexpContext)_localctx).e.ast); 
			setState(34); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(31);
				((AddexpContext)_localctx).e = exp();
				 _localctx.list.add(((AddexpContext)_localctx).e.ast); 
				}
				}
				setState(36); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 || _la==Letter );
			setState(38);
			match(T__2);
			 ((AddexpContext)_localctx).ast =  new AddExp(_localctx.list); 
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

	public static class MultexpContext extends ParserRuleContext {
		public MultExp ast;
		public ArrayList<Exp> list;
		public ExpContext e;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public MultexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multexp; }
	}

	public final MultexpContext multexp() throws RecognitionException {
		MultexpContext _localctx = new MultexpContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_multexp);
		 ((MultexpContext)_localctx).list =  new ArrayList<Exp>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(T__0);
			setState(42);
			match(T__3);
			setState(43);
			((MultexpContext)_localctx).e = exp();
			 _localctx.list.add(((MultexpContext)_localctx).e.ast); 
			setState(48); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(45);
				((MultexpContext)_localctx).e = exp();
				 _localctx.list.add(((MultexpContext)_localctx).e.ast); 
				}
				}
				setState(50); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 || _la==Letter );
			setState(52);
			match(T__2);
			 ((MultexpContext)_localctx).ast =  new MultExp(_localctx.list); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\17:\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\5\3\31\n\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\6\5%\n\5\r\5\16"+
		"\5&\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\6\6\63\n\6\r\6\16\6\64\3\6"+
		"\3\6\3\6\3\6\2\2\7\2\4\6\b\n\2\28\2\f\3\2\2\2\4\30\3\2\2\2\6\32\3\2\2"+
		"\2\b\35\3\2\2\2\n+\3\2\2\2\f\r\5\4\3\2\r\16\b\2\1\2\16\3\3\2\2\2\17\20"+
		"\5\6\4\2\20\21\b\3\1\2\21\31\3\2\2\2\22\23\5\b\5\2\23\24\b\3\1\2\24\31"+
		"\3\2\2\2\25\26\5\n\6\2\26\27\b\3\1\2\27\31\3\2\2\2\30\17\3\2\2\2\30\22"+
		"\3\2\2\2\30\25\3\2\2\2\31\5\3\2\2\2\32\33\7\n\2\2\33\34\b\4\1\2\34\7\3"+
		"\2\2\2\35\36\7\3\2\2\36\37\7\4\2\2\37 \5\4\3\2 $\b\5\1\2!\"\5\4\3\2\""+
		"#\b\5\1\2#%\3\2\2\2$!\3\2\2\2%&\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\'(\3\2\2"+
		"\2()\7\5\2\2)*\b\5\1\2*\t\3\2\2\2+,\7\3\2\2,-\7\6\2\2-.\5\4\3\2.\62\b"+
		"\6\1\2/\60\5\4\3\2\60\61\b\6\1\2\61\63\3\2\2\2\62/\3\2\2\2\63\64\3\2\2"+
		"\2\64\62\3\2\2\2\64\65\3\2\2\2\65\66\3\2\2\2\66\67\7\5\2\2\678\b\6\1\2"+
		"8\13\3\2\2\2\5\30&\64";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}