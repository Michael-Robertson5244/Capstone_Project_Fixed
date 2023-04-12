package goteamgo.JUnitAdLibStories;

import org.junit.Test;
import goteamgo.AdLibStories.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GameJUnitTest {

	//old test for the template game, wont work now
    /*@Test
    public void testAddPlayers() {
		Game game = new Game();
        game.numPlayers = 5;
        game.addPlayers();
		
		assertEquals(5, game.addPlayers().size());
        
    }*/
    //old test for the template game, won't work now
   /* @Test
    public void testTurn() {
    	Game game = new Game();
    	game.numPlayers = 2;
    	game.addPlayers();
    	game.runTest();
    	
    	assertEquals(10,game.story().size());
    
    }*/
	
	//Test successful mongodb connection
	//If successful, returns a random string prompt from database
	//When testing, you will get one success and one fail, the database can't connect successfully and unsuccessfully
	/*@Test
	public void testSuccessMongoDBConnection() {
		DB db = new DB();
		//If successful connection, the string returned will not be Connection Error
		assertNotEquals("Connection Error",db.getRandomPrompt());
	}*/
	
	//Test unsuccessful mongodb connection
	//If unsuccessful, Connection Error will be returned as prompt
	//When testing, you will get one success and one fail, the database can't connect successfully and unsuccessfully
/*	@Test
	public void testUnsuccessfulMongoDBConnection() {
		DB db = new DB();
		
		//If the database does not connect successfully, then an exception is thrown and Connection Error is returned as the prompt
		assertEquals("Connection Error",db.getRandomPrompt());
	}*/

	
	//test an already existing screen name
	@Test
	public void testExistingScreenName() {
		DB db = new DB();
		
		
	}
	//test an already existing userName
	@Test
	public void testExistingUserName(){
		
	}
	
	//test full register of a non existing profile
	@Test
	public void testRegisterNonExisting() {
		DB db = new DB();
		
	}
	
	//test login success
	@Test
	public void testLoginSuccess() {
		
	}
	//test login fail
	@Test
	public void testLoginFail() {
		
	}
	//test password encryption
	@Test
	public void testPassEncryption() {
		String password = "password";
		EncryptPassword encrypt = new EncryptPassword();
		
		String encryptedPassword = encrypt.encrypt(password);
		assertNotEquals(password,encryptedPassword);
	}
	
	//tests to see if given the same password, if the encryption matches
	//test password encryption for the same password
	@Test
	public void testSamePassEncryption() {
		String password1 = "password";
		String password2 = "password";
		EncryptPassword encrypt = new EncryptPassword();
		
		String encryptedPassword1 = encrypt.encrypt(password1);
		String encryptedPassword2 = encrypt.encrypt(password2);
	
		assertEquals(password1,password2);
		assertEquals(encryptedPassword1,encryptedPassword2);
	}
	
	//Each method in sentence scorer
	
	//tests the count adjectives function in sentence scorer
	@Test
	public void testCountAdjectives() {
		SentenceScorer score = new SentenceScorer("Cookies");
		String testSentence = "These are delicious, huge, round cookies.";
		
		assertEquals(3,score.countAdjectives(testSentence));
		
		testSentence = "My friend Jenna got new, red shoes for her birthday.";
		
		assertEquals(2,score.countAdjectives(testSentence));
		
		testSentence = "I'm going to the store.";
		
		assertEquals(0,score.countAdjectives(testSentence));
		
		testSentence = "Big, stinky, cute, red, ugly, adorable.";
		
		assertEquals(6,score.countAdjectives(testSentence));
	}
	
	//tests to see if the target word is in the sentence
	@Test
	public void testFindTargetWord() {
		SentenceScorer score = new SentenceScorer("Cookies");
		String testSentence = "These are delicious, huge, round cookies.";
	
		assertTrue(score.containsTargetWord(testSentence));
		
		testSentence = "These are delicious, huge, round cakes.";

		assertFalse(score.containsTargetWord(testSentence));
		
	}
	
	//tests count syllable method
	//natural language processing is hard, I'm giving each sentence a range of 3 above and 3 below of the syllables that I count.
	//If the number of syllables counted by the function is in range (a total of range 7), then it is accepted
	@Test
	public void testCountSyllables() {
		SentenceScorer score = new SentenceScorer("Java");
		//I count 9 syllables
		String testSentence = "These are delicious, huge, round cookies.";
		
		assertTrue(6 <= score.countSyllables(testSentence) && score.countSyllables(testSentence) <= 12);
		
		testSentence = "My friend Jenna got new, red shoes for her birthday.";
		
		assertTrue(9 <= score.countSyllables(testSentence) && score.countSyllables(testSentence) <= 15);
	
		testSentence = "I'm going to the store.";
		
		assertTrue(2 <= score.countSyllables(testSentence) && score.countSyllables(testSentence) <= 8);
		
		testSentence = "Big, stinky, cute, red, ugly, adorable.";
		
		assertTrue(7 <= score.countSyllables(testSentence) && score.countSyllables(testSentence) <= 13);

	}
	
	//test count words function
	@Test
	public void testCountWords() {
		SentenceScorer score = new SentenceScorer("GoTeamGo");
		String testSentence = "These are delicious, huge, round cookies.";
		
		assertEquals(6, score.countWords(testSentence));
		
		testSentence = "My friend Jenna got new, red shoes for her birthday.";
		
		assertEquals(10, score.countWords(testSentence));
	
		testSentence = "I wish I had new red shoes. I will go buy some tomorrow.";
		
		assertEquals(13, score.countWords(testSentence));
		
		testSentence = "I'm going to the store.";
		
		assertEquals(5, score.countWords(testSentence));
		
		testSentence = "Big, stinky, cute, red, ugly, adorable.";
		
		assertEquals(6, score.countWords(testSentence));
		
	}
	
	//test count sentences function
	@Test
	public void testCountSentences() {
		SentenceScorer score = new SentenceScorer("Gamer");
		String testSentence = "These are delicious, huge, round cookies.";
		
		assertEquals(1,score.countSentences(testSentence));
		
		testSentence = "I.am.a.sentence.or.am.I.8?";
		
		assertEquals(8,score.countSentences(testSentence));
		
		
	}
	
	//tests the getReadabilityScore() method in sentence scorer
	@Test
	public void testReadabilityScore() {
		
	}
	
	//test sentence scorer
	@Test
	public void testSentenceScorer() {
		
	}
	
	
	
}