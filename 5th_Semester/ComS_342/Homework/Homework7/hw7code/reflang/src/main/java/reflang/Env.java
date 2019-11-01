package reflang;

import java.util.List;

/**
 * Representation of an environment, which maps variables to values.
 * 
 * @author hridesh
 *
 */
public interface Env {
	Value get (String search_var);
	boolean isEmpty();

	@SuppressWarnings("serial")
	static public class LookupException extends RuntimeException {
		LookupException(String message){
			super(message);
		}
	}
	
	static public class EmptyEnv implements Env {
		public Value get (String search_var) {
			throw new LookupException("No binding found for name: " + search_var);
		}
		public boolean isEmpty() { return true; }
	}
	
	static public class ExtendEnv implements Env {
		private Env _saved_env; 
		private String _var; 
		private Value _val; 
		public ExtendEnv(Env saved_env, String var, Value val){
			_saved_env = saved_env;
			_var = var;
			_val = val;
		}
		public synchronized Value get (String search_var) {
			if (search_var.equals(_var))
				return _val;
			return _saved_env.get(search_var);
		}
		public boolean isEmpty() { return false; }
		public Env saved_env() { return _saved_env; }
		public String var() { return _var; }
		public Value val() { return _val; }
	}

	static public class ExtendEnvRec implements Env {
		private Env _saved_env;
		private List<String> _names;
		private List<Value.FunVal> _funs;

		public Env saved_env() {
			return _saved_env;
		}

		public List<String> names() {
			return _names;
		}

		public List<Value.FunVal> vals() {
			return _funs;
		}

		public ExtendEnvRec(Env saved_env, List<String> names, List<Value.FunVal> funs) {
			_saved_env = saved_env;
			_names = names;
			_funs = funs;
		}

		public boolean isEmpty() {
			return false;
		}

		public Value get(String search_var) {
			int size = _names.size();
			for (int index = 0; index < size; index++) {
				if (search_var.equals(_names.get(index))) {
					Value.FunVal f = _funs.get(index);
					return new Value.FunVal(this, f.formals(), f.body());
				}
			}
			return _saved_env.get(search_var);
		}
	}

	static public class GlobalEnv implements Env {
		private java.util.Hashtable<String, Value> map;
		public GlobalEnv(){
			map = new java.util.Hashtable<String, Value>();
		}
		public synchronized Value get (String search_var) {
			if(map.containsKey(search_var))
				return map.get(search_var);
			throw new LookupException("No binding found for name: " + search_var);
		}
		public synchronized void extend (String var, Value val) {
			map.put(var, val);
		}
		public boolean isEmpty() { return map.isEmpty(); }
	}

}
