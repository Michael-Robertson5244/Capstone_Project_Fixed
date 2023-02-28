package AdLibStories.AdLibStories;

import java.util.Scanner;

public class Player {

	int playerNum;
	Scanner scan = new Scanner(System.in);

	public Player(int playerNum) {
		this.playerNum = playerNum;
	}
	
	public String turn() {
		
		String sentence = null;
		
		sentence = scan.nextLine();
		
		return sentence;
	}
	
}