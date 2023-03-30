package goteamgo.AdLibStories;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import java.util.Arrays;

public class DB {

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
	public static void insertUser(String username, String encryptedPassword, String displayName) {
		
		String uri = "mongodb+srv://readOnlyPromptUser:UTwWQTKnVl319X3l@cluster0.bemawuq.mongodb.net/?retryWrites=true&w=majority";
		
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
        	System.out.println("There was a matching document.");
        	
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
        	System.out.println("There was a matching document.");
        	
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
			String encryptedPassword = EncryptPassword.encrypt(password);
			String storedPassword = user.getString("EPassword");
			
			if(encryptedPassword.equals(storedPassword))
			{
				System.out.println("Passwords matched.");
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
}




