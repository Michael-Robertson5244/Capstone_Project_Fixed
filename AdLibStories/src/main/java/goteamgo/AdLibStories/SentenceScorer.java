package goteamgo.AdLibStories;

import java.util.Properties;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.util.*;

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
