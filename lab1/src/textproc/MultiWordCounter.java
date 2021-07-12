package textproc;

import java.util.HashMap;
import java.util.Map;

public class MultiWordCounter implements TextProcessor {
    private Map<String, Integer> words = new HashMap<String, Integer>();


    public MultiWordCounter(String[] words) {
        for (int i = 0; i < words.length; i++) {
            this.words.put(words[i], 0);
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
