package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFileChooser;


public class BookReaderApplication {


    public static void main(String[] args) throws FileNotFoundException {
        final String delimiter = "(\\s|,|\\.|:|;|\\d|!|\\?|'|\\\")+"; //edited regex to remove digits

        Scanner s1 = new Scanner(new File("lab3/undantagsord.txt"));
        s1.useDelimiter(delimiter);

        Set<String> stopwords = new HashSet<>();

        while (s1.hasNext()) {
            stopwords.add(s1.next());
        }

        s1.close();

        Scanner s2 = new Scanner(new File("lab3/nilsholg.txt"));
        s2.findWithinHorizon("\uFEFF", 1);
        s2.useDelimiter(delimiter);

        GeneralWordCounter counter = new GeneralWordCounter(stopwords);

        while (s2.hasNext()) {
            String word = s2.next().toLowerCase();
            counter.process(word);
        }

        new BookReaderController(counter);

    }


}
