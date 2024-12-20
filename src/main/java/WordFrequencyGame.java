import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String REGEX = "\\s+";
    public static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";
    public static final String LINE_BREAK = "\n";

    public String getWordFrequency(String sentence) {
        try {
            List<WordFrequency> wordFrequencies = getWordFrequencies(sentence);
            wordFrequencies = getWordFrequencyList(wordFrequencies);
            return wordFrequencies.stream()
                    .sorted((currentWord, nextWord) -> nextWord.getWordCount() - currentWord.getWordCount())
                    .map(WordFrequencyGame::format)
                    .collect(Collectors.joining(LINE_BREAK));
        } catch (Exception e) {
            return CALCULATE_ERROR_MESSAGE;
        }
    }

    private static String format(WordFrequency wordFrequency) {
        return String.format("%s %d", wordFrequency.getValue(), wordFrequency.getWordCount());
    }

    private List<WordFrequency> getWordFrequencyList(List<WordFrequency> wordFrequencies) {
        Map<String, List<WordFrequency>> wordToWordFrequencyList = getListMap(wordFrequencies);
        return wordToWordFrequencyList.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }

    private List<WordFrequency> getWordFrequencies(String sentence) {
        String[] words = sentence.split(REGEX);
        return Arrays.stream(words)
                .map(word -> new WordFrequency(word, 1))
                .collect(Collectors.toList());
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .collect(Collectors.groupingBy(WordFrequency::getValue));
    }
}
