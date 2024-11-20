import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String REGEX = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getWordFrequency(String sentence) {
        if (sentence.split(REGEX).length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split(REGEX);
                List<WordFrequency> wordFrequencies = Arrays.stream(words)
                        .map(word -> new WordFrequency(word, 1))
                        .collect(Collectors.toList());
                //get the wordToWordFrequency for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequency = getListMap(wordFrequencies);
                List<WordFrequency> list = new ArrayList<>();
                wordToWordFrequency.entrySet().stream()
                        .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                        .forEach(list::add);
                wordFrequencies = list;
                wordFrequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
                StringJoiner joiner = new StringJoiner("\n");
                wordFrequencies.stream()
                        .map(w -> w.getValue() + " " + w.getWordCount())
                        .forEach(joiner::add);
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordToWordFrequency = new HashMap<>();
        wordFrequencyList.forEach(wordFrequency ->
                wordToWordFrequency.computeIfAbsent(wordFrequency.getValue(), k -> new ArrayList<>()).add(wordFrequency)
        );
        return wordToWordFrequency;
    }
}
