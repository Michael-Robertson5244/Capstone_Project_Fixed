
//Completely overhaul the class to hold the important info that will be saved and queried from the DB

package goteamgo.AdLibStories;

import java.util.Scanner;

public class Player {

	int playerNum;
	String username;
	String displayName;
	Scanner scan = new Scanner(System.in);

	public Player(String username, String displayName) {
		this.username = username;
		this.displayName = displayName;
	}
	
	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	
}