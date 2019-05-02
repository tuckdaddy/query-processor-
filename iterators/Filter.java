package iterators;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.lang.NullPointerException;

// Iterator that uses a Predicate to filter out elements from the input
public class Filter<T> extends FlatApply<T, T> {

    public Filter(Predicate<T> p, Iterator<T> input) {
        // you DO NOT need to modify the constructor
        super(new FilteringFlatApplyFunction<>(p), input);
    }

    // uses a Predicate to decide whether the input element is output or not
    private static class FilteringFlatApplyFunction<T> implements FlatApplyFunction<T, T> {
        private Predicate<T> derp; 
        public FilteringFlatApplyFunction(Predicate<T> p){
            this.derp = p;
        }
        
        // if x passes the predicate, add x to the new list 
        @Override 
        public List<T> apply(T x){
            List <T> lst = new LinkedList<>();
            boolean res = derp.check(x);
            if (res == true){
                lst.add(x);
                return lst;
            }
            else {
                return lst;
            }
        }
    }

    // You DO NOT need to define hasNext() and next(). FlatApply provides them already.
}