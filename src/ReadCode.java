import java.util.Scanner;
import java.io.File;


public class ReadCode {
    public static void main(String[] args) {
        try {
            LeftistHeap<Term> h = new LeftistHeap<>();
            String filename = "SortedWords.txt";
            Scanner reader = new Scanner( new File( filename ) );
            int count = reader.nextInt();
            while ((reader.hasNext())) {
                String word = reader.next();
                long freq = reader.nextInt();
                h.insert(new Term(word, freq));
             }

            System.out.println( "\n\n\n " + h.deleteMin() );
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}