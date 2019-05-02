package iterators;


import java.util.Iterator;

// Iterator that returns a single element that is the result of
// combining all the input elements
public class Reduce<InT,OutT> implements Iterator<OutT> {
    Iterator<OutT> invar;
    
    public Reduce(ReduceFunction<InT,OutT> f, Iterator<InT> input) {
        super(new RedFun<InT,OutT> (f), input);
    }
    
    private static class RedFun<InT,OutT> implements ReduceFunction<InT, OutT>{
        ReduceFunction<InT,OutT> rf;
        Iterator<InT> its;
        
        public RedFun(ReduceFunction<InT,OutT> f, Iterator<InT> input){
           // this.its = it;
            this.rf = f;
        }
        @Override
        public OutT combine(OutT soFar, InT x) {
            return soFar;
        }

        @Override
        public OutT initialValue() {
            return (OutT) String.valueOf(Integer.MIN_VALUE);
        }
    }   // end inner class
    
    @Override
    public boolean hasNext() {
        return(invar.hasNext());
        
    }

    @Override
    public OutT next() {
        if((!hasNext())) throw new IllegalStateException();
        return invar.next();
    }
}//end Reduce class
