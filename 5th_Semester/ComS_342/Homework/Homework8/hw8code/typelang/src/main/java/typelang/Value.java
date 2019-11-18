package typelang;

import java.util.List;

import typelang.AST.Exp;

public interface Value {
	public String tostring();
	static class RefVal implements Value { //New in the reflang
		private int _loc = -1; 
		public RefVal(int loc) { _loc = loc; }
		public String tostring() {
			return "loc:" + this._loc;
		}
		public int loc() { return _loc; }
	}
	static class FunVal implements Value { //New in the funclang
		private Env<Value> _env;
		private List<String> _formals;
		private Exp _body;
		public FunVal(Env<Value> env, List<String> formals, Exp body) {
			_env = env;
			_formals = formals;
			_body = body;
		}
		public Env<Value> env() { return _env; }
		public List<String> formals() { return _formals; }
		public Exp body() { return _body; }
	    public String tostring() { 
			String result = "(lambda ( ";
			for(String formal : _formals) 
				result += formal + " ";
			result += ") ";
			result += _body.accept(new Printer.Formatter(), _env);
			return result + ")";
	    }
	}
	static class NumVal implements Value {
	    private double _val;
	    public NumVal(double v) { _val = v; } 
	    public double v() { return _val; }
	    public String tostring() { 
	    	int tmp = (int) _val;
	    	if(tmp == _val) return "" + tmp;
	    	return "" + _val; 
	    }
	}
	static class BoolVal implements Value {
		private boolean _val;
	    public BoolVal(boolean v) { _val = v; } 
	    public boolean v() { return _val; }
	    public String tostring() { if(_val) return "#t"; return "#f"; }
	}
	static class StringVal implements Value {
		private java.lang.String _val;
	    public StringVal(String v) { _val = v; } 
	    public String v() { return _val; }
	    public java.lang.String tostring() { return "" + _val; }
	}
	static class PairVal implements Value {
		protected Value _fst;
		protected Value _snd;
	    public PairVal(Value fst, Value snd) { _fst = fst; _snd = snd; } 
		public Value fst() { return _fst; }
		public Value snd() { return _snd; }
	    public java.lang.String tostring() { 
	    	if(isList()) return listToString();
	    	return "(" + _fst.tostring() + " " + _snd.tostring() + ")"; 
	    }
	    private boolean isList() {
	    	if(_snd instanceof Value.Null) return true;
	    	if(_snd instanceof Value.PairVal &&
	    		((Value.PairVal) _snd).isList()) return true;
	    	return false;
	    }
	    private java.lang.String listToString() {
	    	String result = "(";
	    	result += _fst.tostring();
	    	Value next = _snd; 
	    	while(!(next instanceof Value.Null)) {
	    		result += " " + ((PairVal) next)._fst.tostring();
	    		next = ((PairVal) next)._snd;
	    	}
	    	return result + ")";
	    }
	}
	static class Null implements Value {
		public Null() {}
	    public String tostring() { return "()"; }
	}
	static class UnitVal implements Value {
		public static final UnitVal v = new UnitVal();
	    public String tostring() { return ""; }
	}
	static class DynamicError implements Value { 
		private String message = "Unknown dynamic error.";
		public DynamicError(String message) { this.message = message; }
	    public String tostring() { return "" + message; }
	}
}
