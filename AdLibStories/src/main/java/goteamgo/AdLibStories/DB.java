package goteamgo.AdLibStories;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import java.util.Arrays;
import org.mindrot.jbcrypt.BCrypt;
import java.security.SecureRandom;

public class DB {
	
    static SecureRandom rand;
	
    public static void main(String[] args) {  	    	
    	
    	//Testing Code Should return true
        //String password = "test";
        //String hashedPassword = "$2a$12$iA5BPKgj6lAOyNfLsl5adOauVVuyz6.ZDc5nJK5mQP6HKMHA.6/J2";
        
        //System.out.println("Password match: " + checkPassword(password, hashedPassword));
        
    	//System.out.println(login("test", "test")); Database test user should return true
        
    }

	public static String getRandomPrompt() {
		String prompt;

		// Replace the placeholder with your MongoDB deployment's connection string
		String uri = "mongodb+srv://readOnlyPromptUser:UTwWQTKnVl319X3l@cluster0.bemawuq.mongodb.net/?retryWrites=true&w=majority";

        // create a MongoClient instance and connect to your MongoDB instance
		try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("game");
            MongoCollection<Document> collection = database.getCollection("prompts");
            
          //Selects a random prompt from the DB
            Document randomPrompt = collection.aggregate(
                    Arrays.asList(
                            Aggregates.sample(1)
                    )
            ).first();
            
            if (randomPrompt != null) {
                prompt = randomPrompt.getString("prompt");
            } else {
                prompt = "No matching documents found.";
            }
            
     	   // close the connection to the MongoDB instance
            mongoClient.close();
            
            return prompt;
        }
		catch(Exception e)
		{
			return "Connection Error";
		}

	}
	
	//Function to insert a user into the user collection
	public static void insertUser(String username, String password, String displayName) {
		
		String uri = "mongodb+srv://readOnlyPromptUser:UTwWQTKnVl319X3l@cluster0.bemawuq.mongodb.net/?retryWrites=true&w=majority";
		String encryptedPassword = encrypt(password);

		
		MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("game");
		
        Document document = new Document();
        document.append("Username", username);
        document.append("EPassword", encryptedPassword);
        document.append("DisplayName", displayName);
	    
        //Inserting the document into the collection
        database.getCollection("users").insertOne(document);  
	}
	
	
	//Function to query for a user based on the username and return if it is already in the DB
	public static boolean validUsername(String username) {
		
		boolean isAvailable = true;
		String uri = "mongodb+srv://readOnlyPromptUser:UTwWQTKnVl319X3l@cluster0.bemawuq.mongodb.net/?retryWrites=true&w=majority";
		
		MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("game");
        MongoCollection<Document> collection = database.getCollection("users");
        
        if(collection.find(new Document("Username", username)).first() != null)
        {
        	//System.out.println("There was a matching document.");
        	
        	isAvailable = false;
        }
        
		return isAvailable;
	}
	
	
	//Checks if the screen name is already in the database
	public static boolean validScreenName(String displayName) {
		
		boolean isAvailable = true;
		String uri = "mongodb+srv://readOnlyPromptUser:UTwWQTKnVl319X3l@cluster0.bemawuq.mongodb.net/?retryWrites=true&w=majority";
		
		MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("game");
        MongoCollection<Document> collection = database.getCollection("users");
        
        if(collection.find(new Document("DisplayName", displayName)).first() != null)
        {
        	//System.out.println("There was a matching document.");
        	
        	isAvailable = false;
        }
        
		return isAvailable;
	}
	
	
	//Checks if a username and password pair is valid
	public static boolean login(String username, String password) {
		
		String uri = "mongodb+srv://readOnlyPromptUser:UTwWQTKnVl319X3l@cluster0.bemawuq.mongodb.net/?retryWrites=true&w=majority";
		
		MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("game");
        MongoCollection<Document> collection = database.getCollection("users");
        
        Document user = collection.find(new Document("Username", username)).first();
        
		if(user != null)
		{
			String storedPassword = user.getString("EPassword");
			
			if(checkPassword(password, storedPassword))
			{
				//System.out.println("Passwords matched.");
				return true;
			}
		}
		
		return false;
	}
	
	
	public static String getDisplayName(String username) {
		
		String uri = "mongodb+srv://readOnlyPromptUser:UTwWQTKnVl319X3l@cluster0.bemawuq.mongodb.net/?retryWrites=true&w=majority";
		
		MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("game");
        MongoCollection<Document> collection = database.getCollection("users");
        
		String displayName;
		
		Document user = collection.find(new Document("Username", username)).first();
		
		displayName = user.getString("DisplayName");

		return displayName;
	}
	
    public static String encrypt(String password) {
        rand = new SecureRandom();
        char pepper = (char) (rand.nextInt(12)); 
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password+pepper, salt);
    }
    
    public static boolean checkPassword(String candidate, String hash) {
        boolean isMatch = false;
        for (int i = 0; i < 12; i++) {
            char c = (char) i;
            isMatch = BCrypt.checkpw(candidate+c, hash);
            if(isMatch) {break;}
          }
        return isMatch;
    }
}




