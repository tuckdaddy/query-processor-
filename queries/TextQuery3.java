package queries;


import iterators.Apply;
import iterators.ApplyFunction;
import iterators.Filter;
import iterators.FlatApply;
import iterators.FlatApplyFunction;
import iterators.Predicate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import readers.TextFileReader;

// return all filenames that contain the word "Mars" 
public class TextQuery3 {
    public static void main(String[] args) {
        Iterator<Pair<String,String>> filenameAndContents = new TextFileReader("../sci.space");
        Iterator<String> fileName= new Apply(new TakeLeft<>(), filenameAndContents);
        Iterator<String> contents = new Apply(new TakeRight<>(), filenameAndContents);
        Iterator<String> words = new FlatApply<>(new SplitBy("[ .!,:<>@=*_ ()/\\t~#\"|-]"), contents); 
        Iterator<String> mars = new Filter<>(new FindMars("Mars"), words);
        /* finish the query using a combination of Applys and Filters */
	// Eventually the last iterator will go through the while loop and print statement
        //Finds files with words matching the key 'Mars'. Works correctly but doesnt match expected/TextQuery3.txt
        
        while (mars.hasNext()) {
            System.out.println(fileName.next());
            //To move the iterator to the next file containing 'Mars'
            mars.next();
        }
    }
    // put your classes that implement ApplyFunction and Predicate here
    private static class FindMars implements Predicate<String>{
        private String s = null;
        public FindMars(String s){
            this.s = s;
        }
        @Override
        public boolean check(String data){
            return s.equals(data);
        }
    }

    private static class TakeLeft<L,R> implements ApplyFunction<Pair<L,R>, L>{
        @Override
        public L apply(Pair<L,R> x){
            return x.left;
        }
    }
    private static class TakeRight<L,R> implements ApplyFunction<Pair<L,R>, R> {
        @Override
        public R apply(Pair<L, R> x) {
            return x.right;	
        }
    }
    public static class SplitBy implements FlatApplyFunction<String, String> {
		private String ch;
		public SplitBy(String c) {
			this.ch = c;
		}
		@Override
		public List<String> apply(String x) {
                    return Arrays.asList(x.split(ch));
		}
	}
}
