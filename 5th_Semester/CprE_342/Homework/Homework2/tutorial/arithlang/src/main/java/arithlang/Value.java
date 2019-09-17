package arithlang;

public interface Value {
    public String toString();
    static class CharVal implements Value {
        private char _val;
        public CharVal(char v) { _val = v; }
        public char v() { return _val; }
        public String toString() {
            return "" + _val; 
        }
    }

    static class DynamicError implements Value {
        private String _errorMsg;
        public DynamicError(String v) {
            _errorMsg = v;
        }
        public String v() {
            return _errorMsg;
        }
        public String toString() {
            String tmp = _errorMsg;
            if (tmp == _errorMsg) return "" + tmp;
            return "" + _errorMsg;
        }
    }
}
