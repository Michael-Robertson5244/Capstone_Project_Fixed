package goteamgo.AdLibStories;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.Color;
import java.net.InetAddress;

public class Server implements Runnable{

	private int numPlayers = 0;
	private int maxPlayers;
	private int port;
	private List<Player> players;
	private ServerSocket server;
	private ObjectOutputStream outputObject;
	private ObjectInputStream inputObject;
	

	public Server(int port, int maxPlayers) {
		this.port = port;
		this.maxPlayers = maxPlayers;
		this.players = new ArrayList<Player>();
	}

	public void run() {
		
		InetAddress localhost;
		try 
		{
			localhost = InetAddress.getLocalHost();
			
			server = new ServerSocket(port, 1, localhost) {
				protected void finalize() throws IOException {
					this.close();
				}
			};
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("IP Address: " + server.getInetAddress().getHostAddress());
		System.out.println("Port num: " + server.getLocalPort());

		while (true) 
		{
			// accepts a new client if not at max number of players
			if(numPlayers < maxPlayers)
			{
				try {
					String connectionSuccess = "success";
					
					Socket client = server.accept();
					
					this.outputObject = new ObjectOutputStream(client.getOutputStream());
					this.inputObject = new ObjectInputStream(client.getInputStream());
					
					outputObject.writeObject(connectionSuccess);
					
					//Receive the player from the newly joined client and add it to list of players
					Player player = (Player)this.inputObject.readObject();
					player.setIs(this.inputObject);
					player.setOs(this.outputObject);
					
					//Set player number in server and send the playerNum to the player
					numPlayers++;
					player.setPlayerNum(numPlayers);
					player.getOs().writeObject(numPlayers);
					
					//Add the player to the list of players and increment the number of players
					this.players.add(player);
					
					//Start a thread to handle incoming requests from the user that joined.
					new Thread(new UserHandler(this, player, client)).start();
				} 
				catch (IOException e) {
					e.printStackTrace();
				} 
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			else
			{
				//Handles when there is a connection but the max number of players has been reached
				try {
					String failedConnection = "Maxplayers";
					
					Socket client = server.accept();
					
					this.outputObject = new ObjectOutputStream(client.getOutputStream());
					this.inputObject = new ObjectInputStream(client.getInputStream());
					
					outputObject.writeObject(failedConnection);
					
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
  }

	//Used to remove a player from the list of players
	public void removePlayer(Player player){
		this.players.remove(player);
	}

	// Send the sentence to all players
	public void broadcastMove(String msg) throws Exception {
		for (Player player: this.players) 
		{
			player.getOs().writeObject(msg);
		}
	}
	
	//Sends the list of players to all the clients
	public void broadcastPlayers() throws Exception {
		for(Player player: this.players)
		{
			player.getOs().writeObject(players);
		}
	}

class UserHandler implements Runnable {

	private Server server;
	private Player player;
	private Socket client;

	public UserHandler(Server server, Player player, Socket client) throws Exception {
		this.server = server;
		this.player = player;
		this.client = client;
		//this.server.broadcastPlayers();
  }

	public void run() {
		String sc = " ";
		// when there is a new message, broadcast to all
		try {			
			while (sc != null) 
			{
				sc = (String) (player.getIs().readObject());

				System.out.println(sc);
				System.out.println("In the thread to handle the players submission.");
				
      			//server.broadcastMove(message);
			}
			// end of Thread
			server.removePlayer(player);
			this.server.broadcastPlayers();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

}