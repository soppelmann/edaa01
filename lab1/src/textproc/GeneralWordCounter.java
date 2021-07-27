package textproc;

import java.util.*;

public class GeneralWordCounter implements TextProcessor {
    Map<String, Integer> words = new TreeMap<>();
    Set<String> exceptions;

    public GeneralWordCounter(Set<String> stopwords) {
        this.exceptions = stopwords;
    }

    @Override
    public void process(String w) {
        if (!exceptions.contains(w) && words.get(w) == null) {
            words.put(w, 1); //initialize its value
        } else if (!exceptions.contains(w)) {
            words.put(w, words.get(w) + 1); //increment
        }

    }

    @Override
    public void report() {
        System.out.println("Report for Generalcounter");
        Set<Map.Entry<String, Integer>> wordSet = words.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
        wordList.sort(new WordCountComparator());

        for (int i = 0; i < 20; i++) {
            System.out.println(wordList.get(i));
        }
//        for (String key : words.keySet()) { //från labhandledning
//
//            if (words.get(key) >= 200) {
//                System.out.println(key + ": " + words.get(key));
//            }
    }

    private class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            int value = o2.getValue() - o1.getValue();
            if (value == 0) {
                return o1.getKey().compareTo(o2.getKey()); //bokstavsordning ifall samma value
            }
            return value;
        }
    }
}

