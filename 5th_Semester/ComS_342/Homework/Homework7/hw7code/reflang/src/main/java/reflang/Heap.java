package reflang;

public interface Heap {


    Value ref(Value val);
    Value deref(Value.RefVal loc);
    Value setref(Value.RefVal loc, Value val);
    Value free(Value.RefVal loc);

    public static class Heap16Bit implements Heap{
        static final int HEAP_SIZE = 65536;

        Value[] _rep;       //Heap
        int location;       //Latest location in heap

        public Heap16Bit(){
            _rep = new Value[HEAP_SIZE];
            location = 0;
        }

        public Value ref(Value val){
            if(location+1 >= HEAP_SIZE)
                return new Value.DynamicError("Out of memory!");

            Value.RefVal newLoc = new Value.RefVal(location);
            _rep[location] = val;
            location++;

            return newLoc;
        }

        public Value deref(Value.RefVal loc){
            if(loc.loc() < 0 || loc.loc() > location)
                return new Value.DynamicError("Reference " + loc.loc() + " outside current heap.");
            return _rep[loc.loc()];
        }

        public Value setref(Value.RefVal loc, Value val){
            if(loc.loc() < 0 || loc.loc() > location)
                return new Value.DynamicError("Reference " + loc.loc() + " outside current heap.");
            _rep[loc.loc()] = val;
            return val;         //Why not return the prev val?
        }

        public Value free(Value.RefVal loc){
            if(loc.loc() < 0 || loc.loc() > location)
                return new Value.DynamicError("Reference " + loc.loc() + " outside current heap.");
            _rep[loc.loc()] = null;
            return loc;         //Why not return the prev val?
        }
    }
}
