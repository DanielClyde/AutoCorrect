import java.util.Scanner;
import java.io.File;


public class ReadCode {
    public static void main(String[] args) {
        try {
            SkewHeap<Term> heap = new SkewHeap<>();
            String filename = "SortedWords.txt";
            Scanner fileReader = new Scanner( new File( filename ) );
            Scanner input = new Scanner(System.in);
            int count = fileReader.nextInt();
            Term[] terms = new Term[count];
            // Read in terms into array
            for (int i = 0; i < terms.length; i++) {
                String word = fileReader.next();
                long frequency = fileReader.nextInt();
                terms[i] = new Term(word, frequency);
            }

            System.out.println("Enter a prefix: ");
            String prefix = input.next();
            System.out.println("Enter the number of words you would like: ");
            int num = input.nextInt();

            // insert words that start with prefix into heap
            for (Term t : terms) {
                if (t.word.length() >= prefix.length() && t.word.startsWith(prefix)) {
                    heap.insert(t);
                }
            }

            // remove now sorted words from heap
            for (int i = 0; i < num; i++) {
                if (heap.isEmpty()) {
                    break;
                }
                System.out.println(heap.deleteMax().word);
            }

            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}