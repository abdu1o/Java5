import java.util.*;
import java.util.stream.Collectors;

class Dictionary {
    private final Map<String, List<String>> dictionary;
    private final Map<String, Integer> wordPopularity;

    public Dictionary() {
        dictionary = new HashMap<>();
        wordPopularity = new HashMap<>();
    }

    public void addWord(String word, List<String> translations) {
        dictionary.putIfAbsent(word, new ArrayList<>(translations));
        wordPopularity.putIfAbsent(word, 0);
    }

    public List<String> getTranslations(String word) {
        wordPopularity.put(word, wordPopularity.getOrDefault(word, 0) + 1);
        return dictionary.get(word);
    }

    public void addTranslation(String word, String translation) {
        dictionary.computeIfPresent(word, (key, value) -> {
            if (!value.contains(translation)) {
                value.add(translation);
            }
            return value;
        });
    }

    public void replaceTranslations(String word, List<String> newTranslations) {
        if (dictionary.containsKey(word)) {
            dictionary.put(word, new ArrayList<>(newTranslations));
        }
    }

    public void removeTranslation(String word, String translation) {
        dictionary.computeIfPresent(word, (key, value) -> {
            value.remove(translation);
            return value;
        });
    }

    public void removeWord(String word) {
        dictionary.remove(word);
        wordPopularity.remove(word);
    }

    public List<String> getTopPopularWords() {
        return wordPopularity.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<String> getTopUnpopularWords() {
        return wordPopularity.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}