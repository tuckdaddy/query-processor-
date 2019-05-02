package iterators;


import java.util.Iterator;

public class IntApply implements Iterator {
// The function that will be applied to each input element to make an output element
    private final IntApplyFunction f;

// The Iterator that this Apply object will get its input from
    private final Iterator<Integer> input;		
    // Costructor that takes a function as input and runs it through a function, the value is the output
    // and an input iterator
    // Just like ApplyX but instead of passing a variable you pass a function
    public IntApply(IntApplyFunction f, Iterator<Integer> input) {
        this.input = input;
        this.f = f;
    }

    @Override
    public boolean hasNext() {
        return input.hasNext();
    }

    @Override
    // f is an obect of IntApplyFunction allowing access to its method apply
    // the data passes to apply is input.next()
    public Integer next() {
        if (!hasNext()) throw new IllegalStateException();
        return(f.apply(input.next()));
    }
}
