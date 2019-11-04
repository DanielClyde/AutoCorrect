import java.util.ArrayList;
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

            int index = binarySearch(prefix, terms, 0, terms.length - 1);

            int startIndex = index;
            while (terms[startIndex].word.startsWith(prefix)) {
                heap.insert(terms[startIndex]);
                startIndex--;
            }
            int endIndex = index + 1;
            while (terms[endIndex].word.startsWith(prefix)) {
                heap.insert(terms[endIndex]);
                endIndex++;
            }

            // remove now sorted words from heap
            for (int i = 0; i < num; i++) {
                if (heap.isEmpty()) {
                    break;
                }
                Term max = heap.deleteMax();
                System.out.println(max.word);
            }

            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int binarySearch(String prefix, Term[] terms, int startIndex, int endIndex) {
        if (startIndex > endIndex) return -1;

        int mid = startIndex + ((endIndex - startIndex) / 2);

        if (terms[mid].word.startsWith(prefix)) return mid;

        int prefixLength = prefix.length();

        if (terms[mid].word.substring(0, prefixLength - 1).compareTo(prefix) > 0) {
            return binarySearch(prefix, terms, startIndex, mid - 1);
        } else {
            return binarySearch(prefix, terms, mid + 1, endIndex);
        }
    }

}