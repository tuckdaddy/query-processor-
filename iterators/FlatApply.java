package iterators;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

// Iterator that may produce 0 or more output elements for every input element
public class FlatApply<InT,OutT> implements Iterator<OutT> {
    private final Iterator <InT> input;
    private final FlatApplyFunction<InT,OutT> f;
    private final Queue<OutT> q;
    
    // Constructs a new linked list queue everytime constructor is called
    public FlatApply(FlatApplyFunction<InT,OutT> f, Iterator<InT> input) {		
        this.input = input;
        this.f = f;
        this.q = new LinkedList<>();
    }

    @Override
    // If the queue is not empty return true, else fill the queue by applying the 
    //  function until input.hasNext() is false
    //  but there is a check again if the queue is empty to eliminate any errors
    public boolean hasNext() {
        if (!q.isEmpty()){return true;}
        else{
            while(q.isEmpty() && input.hasNext()){
                List<OutT> res = f.apply(input.next());
                q.addAll(res);
            }
            if(q.isEmpty()) return false;
            return true;
        }
    }

    @Override
    public OutT next() {
        if((!hasNext())) throw new IllegalStateException();
        return q.poll();
    }
}
