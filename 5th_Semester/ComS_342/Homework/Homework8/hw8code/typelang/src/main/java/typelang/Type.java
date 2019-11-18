package typelang;

import java.util.List;

public interface Type {
	public String tostring();
	public boolean typeEqual(Type other);

	static class ErrorT implements Type {
		String _message;
		public ErrorT(String message) { _message = message; }
	    public String tostring() {
	    	return "Type error: " + _message;
	    }
		public boolean typeEqual(Type other) { return other == this; }
	}
	
	static class UnitT implements Type {
		private static final UnitT _instance = new UnitT();
		public static UnitT getInstance() { return _instance; }
	    public String tostring() { return "unit"; }
	    public boolean typeEqual(Type other) { return other.getClass() == this.getClass(); }
	}

	static class BoolT implements Type {
		private static final BoolT _instance = new BoolT();
		public static BoolT getInstance() { return _instance; }
	    public String tostring() { return "bool"; }
	    public boolean typeEqual(Type other) { return other.getClass() == this.getClass(); }
	}

	static class StringT implements Type {
		private static final StringT _instance = new StringT();
		public static StringT getInstance() { return _instance; }
	    public String tostring() { return "string"; }
	    public boolean typeEqual(Type other) { return other.getClass() == this.getClass(); }
	}

	static class NumT implements Type {
		private static final NumT _instance = new NumT();
		public static NumT getInstance() { return _instance; }
	    public String tostring() { return "number"; }
	    public boolean typeEqual(Type other) { return other.getClass() == this.getClass(); }
	}

	static class PairT implements Type {
		protected Type _fst;
		protected Type _snd;
	    public PairT(Type fst, Type snd) { _fst = fst; _snd = snd; } 
		public Type fst() { return _fst; }
		public Type snd() { return _snd; }
	    public java.lang.String tostring() { 
	    	return "(" + _fst.tostring() + " " + _snd.tostring() + ")"; 
	    }
	    public boolean typeEqual(Type other) {
	    	if (other instanceof PairT) {
	    		PairT pt = (PairT)other;

	    		return _fst.typeEqual(pt._fst) && _snd.typeEqual(pt._snd);
	    	}
	    	return false;
	    }
	}

	static class ListT extends PairT implements Type {
	    public ListT(Type type) {
	    	super(type, null);

	    	_snd = this;
	    }
	    public java.lang.String tostring() { 
	    	return "List<" + _fst.tostring() + ">"; 
	    }
	    public boolean typeEqual(Type other) {
	    	if (other instanceof ListT) {
	    		ListT lt = (ListT)other;

	    		return _fst.typeEqual(lt._fst);
	    	}
	    	return false;
	    }
	}

	static class FuncT implements Type {
		protected List<Type> _argTypes;
		protected Type _returnType;
	    public FuncT(List<Type> argTypes, Type returnType) {
	    	_argTypes = argTypes;
	    	_returnType =returnType ;
	    }
		public List<Type> argTypes() { return _argTypes; }
		public Type returnType() { return _returnType; }
	    public java.lang.String tostring() {
	    	StringBuffer sb = new StringBuffer();
	    	int size = _argTypes.size();
	    	int i = 0;
	    	for (Type type : _argTypes) {
	    		sb.append(type.tostring());
	    		if (i != size - 1) { sb.append(", "); }

	    		i++;
	    	}
	    	sb.append(" -> ");
	    	sb.append(_returnType.tostring());
	    	return sb.toString(); 
	    }
		public boolean typeEqual(Type other) {
	    	if (other instanceof FuncT) {
	    		FuncT ft = (FuncT)other;

	    		List<Type> argTypes = ft._argTypes;
	    		int size = _argTypes.size();
	    		if (argTypes.size() == size) {
	    			for (int i = 0; i < size; i++) {
	    				if (!argTypes.get(i).typeEqual(_argTypes.get(i))) {
	    					return false;
	    				}
	    			}

	    			return _returnType.typeEqual(ft._returnType);
	    		}
	    	}
	    	return false;
		}
	}

	static class RefT implements Type {
		protected Type _nestType;
	    public RefT(Type nestType) { _nestType = nestType; }
		public Type nestType() { return _nestType; }
	    public java.lang.String tostring() {
	    	return "Ref " + _nestType.tostring(); 
	    }
		public boolean typeEqual(Type other) {
			if (other instanceof RefT) {
				RefT rt = (RefT)other;
	    		return _nestType.typeEqual(rt._nestType);
	    	}
	    	return false;
		}
	}
}
