package goteamgo.AdLibStories;

import java.util.ArrayList;
import java.util.Scanner;


public class Game {

	int numRounds = 5;
	int turnTracker = 0;
	boolean gameOver = false;
	String prompt;
	ArrayList<String> story = new ArrayList<String>();
	int numPlayers;
	ArrayList<Player> players = new ArrayList<Player>();
	Scanner scan = new Scanner(System.in);
	
	
	public void run() {
		
		initGame();
		System.out.println("Prompt : " + getPrompt());
		
		for(int i = 0; i < numRounds; i++)
		{
			for(int j = 0; j < numPlayers; j++)
			{
				turn(j);
			}
			
			displayStory();
			
			if(i == numRounds - 2)
			{
				System.out.println("Last round");
			}
		}
		
		System.out.println("Game is over. Here is your story.\n-------------------------------------------------------------------");
		displayStory();
		
	}
	
	public void initGame() {
		System.out.println("Enter the number of players.");
		numPlayers = scan.nextInt();
		
		setPrompt();
		addPlayers();
	}
	
	public void displayStory() {
		
		String fullStory = "";
		
		for(int i = 0; i < story.size(); i++)
		{
			fullStory += (story.get(i) + "\n");
		}
		
		System.out.println(fullStory);
		
	}
	
	public void turn(int playerNumber) {
		
		System.out.println("Player " + (playerNumber+1) + "s turn.");
		story.add(players.get(playerNumber).turn());
		
	}
	
	public void addPlayers() {

		for(int i = 0; i < numPlayers; i++)
		{
			Player newPlayer = new Player(i);
		
			players.add(newPlayer);
		}
	}
	
	public void setPrompt() {
		prompt = DB.getRandomPrompt();
	}
	
	public String getPrompt() {
		return prompt;
	}
	
	public void displayRules() {
		
	}
	
	/*
	TODO: Add Scoring System
	SentenceScorer scorer = new SentenceScorer("java");
        String sentence = "Java is a popular programming language that is used for developing mobile apps, web applications, and games.";
        double score = scorer.score(sentence);
        System.out.println(score); // Output: 20.0
	*/

}
