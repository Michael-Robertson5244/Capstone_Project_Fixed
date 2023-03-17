package goteamgo.AdLibStories;

import java.util.Properties;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.util.*;

/*
Java class that takes in a sentence and returns a score based on the length, 
number of adjectives used, whether a specific word is used, and readability. 
Note that this implementation uses the Stanford CoreNLP library for natural language processing. 

The score method will return a score based on the length, number of adjectives used, 
whether the specific word is used, and readability of the sentence. The score is 
calculated by adding up the four individual scores, each of which is weighted differently. 
In this implementation, the length score is worth 1 point per 10 characters, 
the adjective score is worth 2 points per adjective, and the word score is 
worth 5 points if the specific word is present and 0 points otherwise. 
The readability score is calculated using a simple formula that just 
counts the number of words in the sentence.
*/

public class SentenceScorer {
    
    private final String targetWord;
    
    public SentenceScorer(String targetWord) {
        this.targetWord = targetWord;
    }
    
    public double score(String sentence) {
        double lengthScore = sentence.length() / 10.0; // Reward longer sentences
        double adjScore = countAdjectives(sentence) * 2.0; // Reward sentences with more adjectives
        double wordScore = containsTargetWord(sentence) ? 5.0 : 0.0; // Reward sentences that contain a specific word
        double readScore = getReadabilityScore(sentence); // Calculate readability score
        
        return lengthScore + adjScore + wordScore + readScore;
    }
    
    private int countAdjectives(String sentence) {
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
    
    private boolean containsTargetWord(String sentence) {
        return sentence.toLowerCase().contains(targetWord.toLowerCase());
    }
    
    private double getReadabilityScore(String sentence) {
        // Calculate readability score using a formula like Flesch-Kincaid Grade Level
        // Here's a simple implementation that just returns the number of words
        return sentence.split("\\s+").length;
    }
}
