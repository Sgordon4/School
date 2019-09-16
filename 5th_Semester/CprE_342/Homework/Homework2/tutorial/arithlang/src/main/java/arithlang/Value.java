package arithlang;

public interface Value {
    public String toString();
    static class NumVal implements Value {
        private double _val;
        public NumVal(double v) { _val = v; } 
        public double v() { return _val; }
        public String toString() { 
            int tmp = (int) _val;
            if(tmp == _val) return "" + tmp;
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
