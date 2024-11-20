import java.util.*;

public class WordFrequencyGame {
    public String getWordFrequency(String sentence) {
        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");
                List<WordFrequency> wordFrequencies = new ArrayList<>();
                for (String s : words) {
                    WordFrequency wordFrequency = new WordFrequency(s, 1);
                    wordFrequencies.add(wordFrequency);
                }
                //get the wordToWordFrequency for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequency = getListMap(wordFrequencies);
                List<WordFrequency> list = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequency.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    list.add(wordFrequency);
                }
                wordFrequencies = list;
                wordFrequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
                StringJoiner joiner = new StringJoiner("\n");
                for (WordFrequency w : wordFrequencies) {
                    String wordFrequencyString = w.getValue() + " " + w.getWordCount();
                    joiner.add(wordFrequencyString);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordToWordFrequency = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       wordToWordFrequency.computeIfAbsent(wordFrequency.getValue(), k -> new ArrayList<>()).add(wordFrequency);
            if (!wordToWordFrequency.containsKey(wordFrequency.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequency);
                wordToWordFrequency.put(wordFrequency.getValue(), arr);
            } else {
                wordToWordFrequency.get(wordFrequency.getValue()).add(wordFrequency);
            }
        }
        return wordToWordFrequency;
    }
}
