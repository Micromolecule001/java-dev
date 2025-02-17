import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class TextAnalyzer {

    private String text; // Holds the text from INPUT.TXT

    // Constructor to load the text from file
    public TextAnalyzer(String fileName) throws IOException {
        this.text = loadFile(fileName);
    }

    // Method to load text from a file
    private String loadFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(" ");
            }
        }
        return content.toString().trim();
    }

    // Method to count total words in the text
    public int countTotalWords() {
        String[] words = text.split("\\s+");
        return words.length;
    }

    // Method to count unique words
    public int countUniqueWords() {
        String[] words = text.toLowerCase().replaceAll("[^a-zа-яієїґ'-]", " ").split("\\s+");
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        return uniqueWords.size();
    }

    // Method to count sentences
    public int countSentences() {
        Pattern pattern = Pattern.compile("[.!?]");
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    // Method to count punctuation marks
    public int countPunctuation() {
        Pattern pattern = Pattern.compile("[,.;:!?\"'()]");
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    // Method to calculate the average word length
    public double averageWordLength() {
        String[] words = text.split("\\s+");
        int totalLength = Arrays.stream(words).mapToInt(String::length).sum();
        return (double) totalLength / words.length;
    }

    // Method to calculate the average sentence length (in words)
    public double averageSentenceLength() {
        int totalWords = countTotalWords();
        int totalSentences = countSentences();
        return (double) totalWords / totalSentences;
    }

    // Method to find the top 10 most frequent words
    public List<Map.Entry<String, Integer>> getTop10FrequentWords() {
        String[] words = text.toLowerCase().replaceAll("[^a-zа-яієїґ'-]", " ").split("\\s+");
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
        return wordFrequency.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(10)
                .collect(Collectors.toList());
    }

    // Main method to run the program
    public static void main(String[] args) {
        try {
            // Load the text from INPUT.TXT
            TextAnalyzer analyzer = new TextAnalyzer("INPUT.TXT");

            // Perform analysis and display results
            System.out.println("Total words: " + analyzer.countTotalWords());
            System.out.println("Unique words: " + analyzer.countUniqueWords());
            System.out.println("Sentences: " + analyzer.countSentences());
            System.out.println("Punctuation marks: " + analyzer.countPunctuation());
            System.out.println("Average word length: " + analyzer.averageWordLength());
            System.out.println("Average sentence length: " + analyzer.averageSentenceLength());

            // Print top 10 most frequent words
            System.out.println("Top 10 most frequent words:");
            for (Map.Entry<String, Integer> entry : analyzer.getTop10FrequentWords()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (IOException e) {
            System.out.println("Error: Could not read the file!");
        }
    }
}

