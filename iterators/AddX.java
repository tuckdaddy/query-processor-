package iterators;


import java.util.Iterator;

public class AddX implements Iterator{
      
    // The Iterator that this apply  object will get its input from
    private final Iterator<Integer> input;	
    private final int x;
    // Constructs an instance of Addx with and int and an iterator
    public AddX(int x, Iterator<Integer> input) {
        this.input = input;
        this.x = x;
    }

    @Override
    public boolean hasNext() {
        return input.hasNext();
    }

    @Override
    // Method that overrides Iterators next() that adds the parameter x
    // to the data in input.next()
    public Integer next() {
        if (!hasNext()) throw new IllegalStateException();
        return input.next() + x;
    }
}       
