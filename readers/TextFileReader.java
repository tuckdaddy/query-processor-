package readers;


import queries.Pair;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class TextFileReader implements Iterator<Pair<String,String>> {
		private final File[] files;
		private int curFile;

		public TextFileReader(String folderPath) {
			File folder = new File(folderPath);
			this.files = folder.listFiles();
			this.curFile = 0;
		}
		
		@Override
		public boolean hasNext() {
			return curFile < files.length;
		}

		/* returns a Pair containing (filename, contents), i.e.,
		   Pair.left=filename and Pair.right=contents
		*/
		@Override
		public Pair<String, String> next() {
			try {
				String name = files[curFile].getName();
				BufferedReader in = new BufferedReader(new FileReader(files[curFile]));
				StringBuilder sb = new StringBuilder();
				String s = in.readLine();
				while (s != null) {
					sb.append(s);
					s = in.readLine();
				}
				curFile++;
				return new Pair(name, sb.toString());
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
}
