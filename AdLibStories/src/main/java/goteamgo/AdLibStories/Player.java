
//Completely overhaul the class to hold the important info that will be saved and queried from the DB

package goteamgo.AdLibStories;

import java.io.Serializable;

public class Player implements Serializable{

	private static final long serialVersionUID = 5950169519310163575L;
	int playerNum;
	String username;
	String displayName;

	public Player() {}
	
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

	public String toString()
	{
		return "Username = " + getUsername() + " ; DisplayName = " + getDisplayName();
	}
}