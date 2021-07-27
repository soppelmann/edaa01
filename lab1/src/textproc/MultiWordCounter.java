package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor {
    Map<String, Integer> words = new TreeMap<>();


    public MultiWordCounter(String[] words) {
        for (String word : words) {
            this.words.put(word, 0);
        }
    }

    @Override
    public void process(String w) {
        if (words.containsKey(w)) {
            words.put(w, words.get(w) + 1);
        }
    }

    @Override
    public void report() {
        System.out.println("Report for MultiWordCounter");
        for (String key : words.keySet()) { //från labhandledning
            System.out.println(key + ": " + words.get(key));
        }
    }
}
