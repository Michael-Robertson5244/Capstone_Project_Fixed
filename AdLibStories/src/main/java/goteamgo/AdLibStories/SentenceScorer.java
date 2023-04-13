package goteamgo.AdLibStories;

import java.util.Properties;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.*;

/*
Java class that takes in a sentence and returns a score based on the length, 
number of adjectives used, whether a specific word is used, and readability. 
Note that this implementation uses the Stanford CoreNLP library for natural language processing. 
The score method will return a score based on the number of adjectives used, 
whether the specific word is used, and readability of the sentence. The score is 
calculated by adding up the four individual scores, each of which is weighted differently. 
In this implementation, the adjective score is worth 2 points per adjective, and the word score is 
worth 5 points if the specific word is present and 0 points otherwise. 
The readability score is calculated using Flesch-Kincaid Grade Level that determines readability.
*/

public class SentenceScorer {
    
    private final String targetWord;
    
    public SentenceScorer(String targetWord) {
        this.targetWord = targetWord;
    }
    
    public double score(String sentence) {
        double adjScore = countAdjectives(sentence) * 2.0; // Reward sentences with more adjectives
        double wordScore = containsTargetWord(sentence) ? 5.0 : 0.0; // Reward sentences that contain a specific word
        double readScore = getReadabilityScore(sentence); // Calculate readability score
        
        return adjScore + wordScore + readScore;
    }
    
    public int countAdjectives(String sentence) {
        int count = 0;        
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation annotation = new Annotation(sentence);
        pipeline.annotate(annotation);
        for (CoreLabel token : annotation.get(CoreAnnotations.TokensAnnotation.class)) {
            String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            if (pos.startsWith("JJ")) { // Check if the part-of-speech tag starts with "JJ"
                count++;
            }
        }
        
        return count;
    }
    
    public boolean containsTargetWord(String sentence) {
        return sentence.toLowerCase().contains(targetWord.toLowerCase());
    }
    
    public static int countWords(String text) {
        return text.trim().split("\\s+").length;
    }

    public static int countSentences(String text) {
        return text.split("[!?.]+").length;
    }

    public static int countSyllables(String word) {
        word = word.toLowerCase();
        int syllables = 0;
        boolean isPrevVowel = false;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            boolean isVowel = isVowel(c);
            if (isVowel && !isPrevVowel) {
                syllables++;
            }
            isPrevVowel = isVowel;
        }
        char lastChar = word.charAt(word.length() - 1);
        if (lastChar == 'e') {
            syllables--;
        }
        if (syllables == 0) {
            syllables = 1;
        }
        return syllables;
    }

    public static double getReadabilityScore(String text) {
        // Calculate readability score using a formula like Flesch-Kincaid Grade Level
        int words = countWords(text);
        int sentences = countSentences(text);
        int syllables = 0;
        String[] wordList = text.trim().split("\\s+");
        for (String word : wordList) {
            syllables += countSyllables(word);
        }
        System.out.println(words + ":" + sentences + ":" + syllables);
        double score = (0.39 * (words / (double) sentences)) + (11.8 * (syllables / (double) words)) - (15.59);
        System.out.println(score);
        return score;
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    
    	public static void main(String[] args) {
		SentenceScorer scorer = new SentenceScorer("space"); // "java" is the keyword for points
        String sentence = "At space be when greatest for him, expected we on with did and to had value hall.";
        double score = scorer.score(sentence);
        System.out.println(score); // Output: 11.92
	}
}