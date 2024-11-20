import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String REGEX = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String LINE_BREAK = "\n";

    public String getWordFrequency(String sentence) {
        if (sentence.split(REGEX).length == 1) {
            return sentence + " 1";
        }
        try {
            List<WordFrequency> wordFrequencies = getWordFrequencies(sentence);
            wordFrequencies = getWordFrequencyList(wordFrequencies);
            return wordFrequencies.stream()
                    .sorted((currentWord, nextWord) -> nextWord.getWordCount() - currentWord.getWordCount())
                    .map(WordFrequencyGame::format)
                    .collect(Collectors.joining(LINE_BREAK));
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private static String format(WordFrequency wordFrequency) {
        return String.format("%s %d", wordFrequency.getValue(), wordFrequency.getWordCount());
    }

    private List<WordFrequency> getWordFrequencyList(List<WordFrequency> wordFrequencies) {
        //get the wordToWordFrequency for the next step of sizing the same word
        Map<String, List<WordFrequency>> wordToWordFrequency = getListMap(wordFrequencies);
        return wordToWordFrequency.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }

    private List<WordFrequency> getWordFrequencies(String sentence) {
        //split the input string with 1 to n pieces of spaces
        String[] words = sentence.split(REGEX);
        return Arrays.stream(words)
                .map(word -> new WordFrequency(word, 1))
                .collect(Collectors.toList());
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordToWordFrequency = new HashMap<>();
        wordFrequencyList.forEach(wordFrequency ->
                wordToWordFrequency.computeIfAbsent(wordFrequency.getValue(), k -> new ArrayList<>()).add(wordFrequency)
        );
        return wordToWordFrequency;
    }
}
