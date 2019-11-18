package typelang;

import java.util.List;

import typelang.Value.*;

/**
 * Representation of an environment, which maps variables to values.
 * 
 * @author hridesh
 *
 */
public interface Env<T> {
	T get (String search_var);
	boolean isEmpty();

	@SuppressWarnings("serial")
	static public class LookupException extends RuntimeException {
		LookupException(String message){
			super(message);
		}
	}
	
	static public class EmptyEnv <T> implements Env <T> {
		public T get (String search_var) {
			throw new LookupException("No binding found for name: " + search_var);
		}
		public boolean isEmpty() { return true; }
	}
	
	static public class ExtendEnv <T> implements Env<T> {
		private Env<T> _saved_env; 
		private String _var; 
		private T _val; 
		public ExtendEnv(Env <T> saved_env, String var, T val){
			_saved_env = (Env<T>) saved_env;
			_var = var;
			_val = val;
		}
		public synchronized T get (String search_var) {
			if (search_var.equals(_var))
				return _val;
			return _saved_env.get(search_var);
		}
		public boolean isEmpty() { return false; }
		public Env<T> saved_env() { return _saved_env; }
		public String var() { return _var; }
		public T val() { return _val; }
	}

	static public class ExtendEnvRec<T> implements Env<T> {
		private Env<T> _saved_env;
		private List<String> _names;
		private List<Value.FunVal> _funs;
		public Env<T> saved_env() { return _saved_env; }
		public List<String> names() { return _names; }
		public List<FunVal> vals() { return _funs; }
		public ExtendEnvRec(Env<T> saved_env, List<String> names, List<Value.FunVal> funs){
			_saved_env = saved_env;
			_names = names;
			_funs = funs;
		}
		public boolean isEmpty() { return false; }
		@SuppressWarnings("unchecked")
		public T get (String search_var) {
			int size = _names.size();
			for(int index = 0; index < size; index++) {
				if (search_var.equals(_names.get(index))) {
					FunVal f = _funs.get(index);
					return (T) new Value.FunVal((ExtendEnvRec<Value>) this, f.formals(), f.body());				
				}
			}
			return _saved_env.get(search_var);
		}
	}

	static public class GlobalEnv<T> implements Env <T> {
		private java.util.Hashtable<String, T> map;
		public GlobalEnv(){
			map = new java.util.Hashtable<String, T>();
		}
		public synchronized T get (String search_var) {
			if(map.containsKey(search_var))
				return map.get(search_var);
			throw new LookupException("No binding found for name: " + search_var);
		}
		public synchronized void extend (String var, T val) {
			map.put(var, val);
		}
		public boolean isEmpty() { return map.isEmpty(); }
	}

}
