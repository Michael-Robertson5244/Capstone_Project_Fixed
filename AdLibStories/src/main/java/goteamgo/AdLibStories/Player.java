
//Completely overhaul the class to hold the important info that will be saved and queried from the DB

package goteamgo.AdLibStories;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Player implements Serializable{

	private static final long serialVersionUID = 5950169519310163575L;
	int playerNum;
	String username;
	String displayName;
	Socket client;
	ObjectOutputStream os;
	ObjectInputStream is;

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public ObjectOutputStream getOs() {
		return os;
	}

	public void setOs(ObjectOutputStream os) {
		this.os = os;
	}

	public ObjectInputStream getIs() {
		return is;
	}

	public void setIs(ObjectInputStream is) {
		this.is = is;
	}

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
	
	public void clear() {
		username = "";
		displayName = "";
		playerNum = 0;
	}

	public String toString()
	{
		return "Username = " + getUsername() + " ; DisplayName = " + getDisplayName();
	}
}