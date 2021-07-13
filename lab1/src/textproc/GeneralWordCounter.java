package textproc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GeneralWordCounter implements TextProcessor {
    private Map<String, Integer> words = new HashMap<String, Integer>();
    private Set<String> exceptions = new HashSet<String>();

    public GeneralWordCounter(Set<String> stopwords) {
        this.exceptions = stopwords;
    }

    @Override
    public void process(String w) {
        if(!exceptions.contains(w) && words.get(w) == null) {
            words.put(w, 1); //initialize its value
        } else if (!exceptions.contains(w)){
            words.put(w, words.get(w) + 1); //increment
        }

    }

    @Override
    public void report() {

        for (String key : words.keySet()) { //från labhandledning
            if (words.get(key) >= 200) System.out.println(key + ": " + words.get(key));
        }
    }
}
