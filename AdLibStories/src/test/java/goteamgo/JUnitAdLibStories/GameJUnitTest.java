package goteamgo.JUnitAdLibStories;

import org.junit.Test;
import goteamgo.AdLibStories.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
	@Test
	public void testSuccessMongoDBConnection() {
		DB db = new DB();
		//If successful connection, the string returned will not be Connection Error
		assertNotEquals("Connection Error",db.getRandomPrompt());
	}
	
	//Test unsuccessful mongodb connection
	//If unsuccessful, Connection Error will be returned as prompt
	//When testing, you will get one success and one fail, the database can't connect successfully and unsuccessfully
	@Test
	public void testUnsuccessfulMongoDBConnection() {
		DB db = new DB();
		
		//If the database does not connect successfully, then an exception is thrown and Connection Error is returned as the prompt
		assertEquals("Connection Error",db.getRandomPrompt());
	}
	
	//test full register of a non existing profile
	@Test
	public void testRegisterNonExisting() {
		DB db = new DB();
		
	}
	
	//test register with an already existing screen name
	@Test
	public void testRegisterExistingScreenName() {
		
	}
	//test register with an already existing userName
	@Test
	public void testRegisterExistingUserName(){
		
	}
	//test login
	@Test
	public void testLogin() {
		
	}
	//test password encryption
	@Test
	public void testPassEncryption() {
		String password = "password";
		EncryptPassword encrypt = new EncryptPassword();
		
		String encryptedPassword = encrypt.encrypt(password);
		assertNotEquals(password,encryptedPassword);
	}
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
	
	//test sentence scorer
	@Test
	public void testSentenceScorer() {
		
	}
	
}