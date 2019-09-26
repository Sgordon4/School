// Generated from varlang\parser\VarLang.g by ANTLR 4.5
package varlang.parser; import static varlang.AST.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class VarLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, Define=8, Let=9, 
		Dot=10, Number=11, Identifier=12, Letter=13, LetterOrDigit=14, AT=15, 
		ELLIPSIS=16, WS=17, Comment=18, Line_Comment=19;
	public static final int
		RULE_exp = 0, RULE_varexp = 1, RULE_letexp = 2, RULE_constexp = 3, RULE_leteexp = 4, 
		RULE_program = 5, RULE_numexp = 6, RULE_addexp = 7, RULE_subexp = 8, RULE_multexp = 9, 
		RULE_divexp = 10;
	public static final String[] ruleNames = {
		"exp", "varexp", "letexp", "constexp", "leteexp", "program", "numexp", 
		"addexp", "subexp", "multexp", "divexp"
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

	@Override
	public String getGrammarFileName() { return "VarLang.g"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public VarLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpContext extends ParserRuleContext {
		public Exp ast;
		public VarexpContext v;
		public NumexpContext n;
		public AddexpContext a;
		public SubexpContext s;
		public MultexpContext m;
		public DivexpContext d;
		public LetexpContext l;
		public ConstexpContext c;
		public LeteexpContext y;
		public VarexpContext varexp() {
			return getRuleContext(VarexpContext.class,0);
		}
		public NumexpContext numexp() {
			return getRuleContext(NumexpContext.class,0);
		}
		public AddexpContext addexp() {
			return getRuleContext(AddexpContext.class,0);
		}
		public SubexpContext subexp() {
			return getRuleContext(SubexpContext.class,0);
		}
		public MultexpContext multexp() {
			return getRuleContext(MultexpContext.class,0);
		}
		public DivexpContext divexp() {
			return getRuleContext(DivexpContext.class,0);
		}
		public LetexpContext letexp() {
			return getRuleContext(LetexpContext.class,0);
		}
		public ConstexpContext constexp() {
			return getRuleContext(ConstexpContext.class,0);
		}
		public LeteexpContext leteexp() {
			return getRuleContext(LeteexpContext.class,0);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_exp);
		try {
			setState(49);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(22);
				((ExpContext)_localctx).v = varexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).v.ast; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(25);
				((ExpContext)_localctx).n = numexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).n.ast; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(28);
				((ExpContext)_localctx).a = addexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).a.ast; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(31);
				((ExpContext)_localctx).s = subexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).s.ast; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(34);
				((ExpContext)_localctx).m = multexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).m.ast; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(37);
				((ExpContext)_localctx).d = divexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).d.ast; 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(40);
				((ExpContext)_localctx).l = letexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).l.ast; 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(43);
				((ExpContext)_localctx).c = constexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).c.ast; 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(46);
				((ExpContext)_localctx).y = leteexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).y.ast; 
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

	public static class VarexpContext extends ParserRuleContext {
		public VarExp ast;
		public Token id;
		public TerminalNode Identifier() { return getToken(VarLangParser.Identifier, 0); }
		public VarexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varexp; }
	}

	public final VarexpContext varexp() throws RecognitionException {
		VarexpContext _localctx = new VarexpContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_varexp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			((VarexpContext)_localctx).id = match(Identifier);
			 ((VarexpContext)_localctx).ast =  new VarExp((((VarexpContext)_localctx).id!=null?((VarexpContext)_localctx).id.getText():null)); 
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

	public static class LetexpContext extends ParserRuleContext {
		public LetExp ast;
		public ArrayList<String> names;
		public ArrayList<Exp> value_exps;
		public Token id;
		public ExpContext e;
		public ExpContext body;
		public TerminalNode Let() { return getToken(VarLangParser.Let, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(VarLangParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(VarLangParser.Identifier, i);
		}
		public LetexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letexp; }
	}

	public final LetexpContext letexp() throws RecognitionException {
		LetexpContext _localctx = new LetexpContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_letexp);
		 ((LetexpContext)_localctx).names =  new ArrayList<String>(); ((LetexpContext)_localctx).value_exps =  new ArrayList<Exp>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(T__0);
			setState(55);
			match(Let);
			setState(56);
			match(T__0);
			setState(63); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(57);
				match(T__0);
				setState(58);
				((LetexpContext)_localctx).id = match(Identifier);
				setState(59);
				((LetexpContext)_localctx).e = exp();
				setState(60);
				match(T__1);
				 _localctx.names.add((((LetexpContext)_localctx).id!=null?((LetexpContext)_localctx).id.getText():null)); _localctx.value_exps.add(((LetexpContext)_localctx).e.ast); 
				}
				}
				setState(65); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			setState(67);
			match(T__1);
			setState(68);
			((LetexpContext)_localctx).body = exp();
			setState(69);
			match(T__1);
			 ((LetexpContext)_localctx).ast =  new LetExp(_localctx.names, _localctx.value_exps, ((LetexpContext)_localctx).body.ast); 
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

	public static class ConstexpContext extends ParserRuleContext {
		public ConstExp ast;
		public Token id;
		public Token num;
		public TerminalNode Define() { return getToken(VarLangParser.Define, 0); }
		public TerminalNode Identifier() { return getToken(VarLangParser.Identifier, 0); }
		public TerminalNode Number() { return getToken(VarLangParser.Number, 0); }
		public ConstexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constexp; }
	}

	public final ConstexpContext constexp() throws RecognitionException {
		ConstexpContext _localctx = new ConstexpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_constexp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__0);
			setState(73);
			match(Define);
			{
			setState(74);
			((ConstexpContext)_localctx).id = match(Identifier);
			setState(75);
			((ConstexpContext)_localctx).num = match(Number);
			}
			setState(77);
			match(T__1);
			 ((ConstexpContext)_localctx).ast =  new ConstExp((((ConstexpContext)_localctx).id!=null?((ConstexpContext)_localctx).id.getText():null), Integer.parseInt((((ConstexpContext)_localctx).num!=null?((ConstexpContext)_localctx).num.getText():null))); 
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

	public static class LeteexpContext extends ParserRuleContext {
		public LeteExp ast;
		public ArrayList<String> names;
		public ArrayList<Exp> value_exps;
		public Token id;
		public ExpContext e;
		public ExpContext body;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(VarLangParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(VarLangParser.Identifier, i);
		}
		public LeteexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leteexp; }
	}

	public final LeteexpContext leteexp() throws RecognitionException {
		LeteexpContext _localctx = new LeteexpContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_leteexp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__0);
			setState(81);
			match(T__2);
			setState(82);
			match(T__0);
			setState(89); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(83);
				match(T__0);
				setState(84);
				((LeteexpContext)_localctx).id = match(Identifier);
				setState(85);
				((LeteexpContext)_localctx).e = exp();
				setState(86);
				match(T__1);
				 _localctx.names.add((((LeteexpContext)_localctx).id!=null?((LeteexpContext)_localctx).id.getText():null)); _localctx.value_exps.add(((LeteexpContext)_localctx).e.ast); 
				}
				}
				setState(91); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			setState(93);
			match(T__1);
			setState(94);
			((LeteexpContext)_localctx).body = exp();
			setState(95);
			match(T__1);
			 ((LeteexpContext)_localctx).ast =  new LeteExp(_localctx.names, _localctx.value_exps, ((LeteexpContext)_localctx).body.ast); 
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
		enterRule(_localctx, 10, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
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

	public static class NumexpContext extends ParserRuleContext {
		public NumExp ast;
		public Token n0;
		public Token n1;
		public List<TerminalNode> Number() { return getTokens(VarLangParser.Number); }
		public TerminalNode Number(int i) {
			return getToken(VarLangParser.Number, i);
		}
		public TerminalNode Dot() { return getToken(VarLangParser.Dot, 0); }
		public NumexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numexp; }
	}

	public final NumexpContext numexp() throws RecognitionException {
		NumexpContext _localctx = new NumexpContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_numexp);
		try {
			setState(115);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				((NumexpContext)_localctx).n0 = match(Number);
				 ((NumexpContext)_localctx).ast =  new NumExp(Integer.parseInt((((NumexpContext)_localctx).n0!=null?((NumexpContext)_localctx).n0.getText():null))); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				match(T__3);
				setState(104);
				((NumexpContext)_localctx).n0 = match(Number);
				 ((NumexpContext)_localctx).ast =  new NumExp(-Integer.parseInt((((NumexpContext)_localctx).n0!=null?((NumexpContext)_localctx).n0.getText():null))); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(106);
				((NumexpContext)_localctx).n0 = match(Number);
				setState(107);
				match(Dot);
				setState(108);
				((NumexpContext)_localctx).n1 = match(Number);
				 ((NumexpContext)_localctx).ast =  new NumExp(Double.parseDouble((((NumexpContext)_localctx).n0!=null?((NumexpContext)_localctx).n0.getText():null)+"."+(((NumexpContext)_localctx).n1!=null?((NumexpContext)_localctx).n1.getText():null))); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(110);
				match(T__3);
				setState(111);
				((NumexpContext)_localctx).n0 = match(Number);
				setState(112);
				match(Dot);
				setState(113);
				((NumexpContext)_localctx).n1 = match(Number);
				 ((NumexpContext)_localctx).ast =  new NumExp(Double.parseDouble("-" + (((NumexpContext)_localctx).n0!=null?((NumexpContext)_localctx).n0.getText():null)+"."+(((NumexpContext)_localctx).n1!=null?((NumexpContext)_localctx).n1.getText():null))); 
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
		enterRule(_localctx, 14, RULE_addexp);
		 ((AddexpContext)_localctx).list =  new ArrayList<Exp>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(T__0);
			setState(118);
			match(T__4);
			setState(119);
			((AddexpContext)_localctx).e = exp();
			 _localctx.list.add(((AddexpContext)_localctx).e.ast); 
			setState(124); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(121);
				((AddexpContext)_localctx).e = exp();
				 _localctx.list.add(((AddexpContext)_localctx).e.ast); 
				}
				}
				setState(126); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << Number) | (1L << Identifier))) != 0) );
			setState(128);
			match(T__1);
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

	public static class SubexpContext extends ParserRuleContext {
		public SubExp ast;
		public ArrayList<Exp> list;
		public ExpContext e;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public SubexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subexp; }
	}

	public final SubexpContext subexp() throws RecognitionException {
		SubexpContext _localctx = new SubexpContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_subexp);
		 ((SubexpContext)_localctx).list =  new ArrayList<Exp>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(T__0);
			setState(132);
			match(T__3);
			setState(133);
			((SubexpContext)_localctx).e = exp();
			 _localctx.list.add(((SubexpContext)_localctx).e.ast); 
			setState(138); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(135);
				((SubexpContext)_localctx).e = exp();
				 _localctx.list.add(((SubexpContext)_localctx).e.ast); 
				}
				}
				setState(140); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << Number) | (1L << Identifier))) != 0) );
			setState(142);
			match(T__1);
			 ((SubexpContext)_localctx).ast =  new SubExp(_localctx.list); 
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
		enterRule(_localctx, 18, RULE_multexp);
		 ((MultexpContext)_localctx).list =  new ArrayList<Exp>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(T__0);
			setState(146);
			match(T__5);
			setState(147);
			((MultexpContext)_localctx).e = exp();
			 _localctx.list.add(((MultexpContext)_localctx).e.ast); 
			setState(152); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(149);
				((MultexpContext)_localctx).e = exp();
				 _localctx.list.add(((MultexpContext)_localctx).e.ast); 
				}
				}
				setState(154); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << Number) | (1L << Identifier))) != 0) );
			setState(156);
			match(T__1);
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

	public static class DivexpContext extends ParserRuleContext {
		public DivExp ast;
		public ArrayList<Exp> list;
		public ExpContext e;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public DivexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_divexp; }
	}

	public final DivexpContext divexp() throws RecognitionException {
		DivexpContext _localctx = new DivexpContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_divexp);
		 ((DivexpContext)_localctx).list =  new ArrayList<Exp>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(T__0);
			setState(160);
			match(T__6);
			setState(161);
			((DivexpContext)_localctx).e = exp();
			 _localctx.list.add(((DivexpContext)_localctx).e.ast); 
			setState(166); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(163);
				((DivexpContext)_localctx).e = exp();
				 _localctx.list.add(((DivexpContext)_localctx).e.ast); 
				}
				}
				setState(168); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << Number) | (1L << Identifier))) != 0) );
			setState(170);
			match(T__1);
			 ((DivexpContext)_localctx).ast =  new DivExp(_localctx.list); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\25\u00b0\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\64\n\2\3\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\6\4B\n\4\r\4\16\4C\3\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\6\6\\\n\6\r\6\16\6]\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bv\n\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\6\t\177\n\t\r\t\16\t\u0080\3\t\3\t\3\t\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\6\n\u008d\n\n\r\n\16\n\u008e\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\6\13\u009b\n\13\r\13\16\13\u009c\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00a9\n\f\r\f\16\f\u00aa\3\f\3\f\3\f\3"+
		"\f\2\2\r\2\4\6\b\n\f\16\20\22\24\26\2\2\u00b5\2\63\3\2\2\2\4\65\3\2\2"+
		"\2\68\3\2\2\2\bJ\3\2\2\2\nR\3\2\2\2\fd\3\2\2\2\16u\3\2\2\2\20w\3\2\2\2"+
		"\22\u0085\3\2\2\2\24\u0093\3\2\2\2\26\u00a1\3\2\2\2\30\31\5\4\3\2\31\32"+
		"\b\2\1\2\32\64\3\2\2\2\33\34\5\16\b\2\34\35\b\2\1\2\35\64\3\2\2\2\36\37"+
		"\5\20\t\2\37 \b\2\1\2 \64\3\2\2\2!\"\5\22\n\2\"#\b\2\1\2#\64\3\2\2\2$"+
		"%\5\24\13\2%&\b\2\1\2&\64\3\2\2\2\'(\5\26\f\2()\b\2\1\2)\64\3\2\2\2*+"+
		"\5\6\4\2+,\b\2\1\2,\64\3\2\2\2-.\5\b\5\2./\b\2\1\2/\64\3\2\2\2\60\61\5"+
		"\n\6\2\61\62\b\2\1\2\62\64\3\2\2\2\63\30\3\2\2\2\63\33\3\2\2\2\63\36\3"+
		"\2\2\2\63!\3\2\2\2\63$\3\2\2\2\63\'\3\2\2\2\63*\3\2\2\2\63-\3\2\2\2\63"+
		"\60\3\2\2\2\64\3\3\2\2\2\65\66\7\16\2\2\66\67\b\3\1\2\67\5\3\2\2\289\7"+
		"\3\2\29:\7\13\2\2:A\7\3\2\2;<\7\3\2\2<=\7\16\2\2=>\5\2\2\2>?\7\4\2\2?"+
		"@\b\4\1\2@B\3\2\2\2A;\3\2\2\2BC\3\2\2\2CA\3\2\2\2CD\3\2\2\2DE\3\2\2\2"+
		"EF\7\4\2\2FG\5\2\2\2GH\7\4\2\2HI\b\4\1\2I\7\3\2\2\2JK\7\3\2\2KL\7\n\2"+
		"\2LM\7\16\2\2MN\7\r\2\2NO\3\2\2\2OP\7\4\2\2PQ\b\5\1\2Q\t\3\2\2\2RS\7\3"+
		"\2\2ST\7\5\2\2T[\7\3\2\2UV\7\3\2\2VW\7\16\2\2WX\5\2\2\2XY\7\4\2\2YZ\b"+
		"\6\1\2Z\\\3\2\2\2[U\3\2\2\2\\]\3\2\2\2][\3\2\2\2]^\3\2\2\2^_\3\2\2\2_"+
		"`\7\4\2\2`a\5\2\2\2ab\7\4\2\2bc\b\6\1\2c\13\3\2\2\2de\5\2\2\2ef\b\7\1"+
		"\2f\r\3\2\2\2gh\7\r\2\2hv\b\b\1\2ij\7\6\2\2jk\7\r\2\2kv\b\b\1\2lm\7\r"+
		"\2\2mn\7\f\2\2no\7\r\2\2ov\b\b\1\2pq\7\6\2\2qr\7\r\2\2rs\7\f\2\2st\7\r"+
		"\2\2tv\b\b\1\2ug\3\2\2\2ui\3\2\2\2ul\3\2\2\2up\3\2\2\2v\17\3\2\2\2wx\7"+
		"\3\2\2xy\7\7\2\2yz\5\2\2\2z~\b\t\1\2{|\5\2\2\2|}\b\t\1\2}\177\3\2\2\2"+
		"~{\3\2\2\2\177\u0080\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081"+
		"\u0082\3\2\2\2\u0082\u0083\7\4\2\2\u0083\u0084\b\t\1\2\u0084\21\3\2\2"+
		"\2\u0085\u0086\7\3\2\2\u0086\u0087\7\6\2\2\u0087\u0088\5\2\2\2\u0088\u008c"+
		"\b\n\1\2\u0089\u008a\5\2\2\2\u008a\u008b\b\n\1\2\u008b\u008d\3\2\2\2\u008c"+
		"\u0089\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2"+
		"\2\2\u008f\u0090\3\2\2\2\u0090\u0091\7\4\2\2\u0091\u0092\b\n\1\2\u0092"+
		"\23\3\2\2\2\u0093\u0094\7\3\2\2\u0094\u0095\7\b\2\2\u0095\u0096\5\2\2"+
		"\2\u0096\u009a\b\13\1\2\u0097\u0098\5\2\2\2\u0098\u0099\b\13\1\2\u0099"+
		"\u009b\3\2\2\2\u009a\u0097\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009a\3\2"+
		"\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\7\4\2\2\u009f"+
		"\u00a0\b\13\1\2\u00a0\25\3\2\2\2\u00a1\u00a2\7\3\2\2\u00a2\u00a3\7\t\2"+
		"\2\u00a3\u00a4\5\2\2\2\u00a4\u00a8\b\f\1\2\u00a5\u00a6\5\2\2\2\u00a6\u00a7"+
		"\b\f\1\2\u00a7\u00a9\3\2\2\2\u00a8\u00a5\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa"+
		"\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\7\4"+
		"\2\2\u00ad\u00ae\b\f\1\2\u00ae\27\3\2\2\2\n\63C]u\u0080\u008e\u009c\u00aa";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}