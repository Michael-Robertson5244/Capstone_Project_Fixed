package goteamgo.AdLibStories;

import org.bson.Document;
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
}