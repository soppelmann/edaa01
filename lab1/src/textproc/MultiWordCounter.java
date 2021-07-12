package textproc;

import java.util.HashMap;
import java.util.Map;

public class MultiWordCounter implements TextProcessor {
    private Map<String, Integer> words = new HashMap<String, Integer>();


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
        for (String key : words.keySet()) { //från labhandledning
            System.out.println(key + ": " + words.get(key));
        }
    }
}
