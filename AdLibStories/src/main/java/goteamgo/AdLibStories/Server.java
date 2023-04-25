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
	private List<User> clients;
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
					
					//Set player number in server and send the playerNum to the player
					numPlayers++;
					player.setPlayerNum(numPlayers);
					outputObject.writeObject(numPlayers);
					
					//Add the player to the list of players and increment the number of players
					this.players.add(player);
					
					//Send the player list to all the clients to update in the game
					
					
					//Start a thread to handle incoming requests from the user that joined.
					new Thread(new UserHandler(this, player, client)).start();
				} 
				catch (IOException e) {
					e.printStackTrace();
				} 
				catch (ClassNotFoundException e) {
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
  }

	
  // delete a user from the list
	public void removePlayer(Player player){
		this.players.remove(player);
	}

	// send incoming msg to all Users
	public void broadcastMessages(String msg, User userSender) {
		for (Player player: this.players) {
			//client.getOutStream().println(userSender.toString() + "<span>: " + msg+"</span>");
		}
	}

	// Will be used to send the list of players to each client
	public void broadcastAllUsers(){
		for (User client : this.clients) {
			client.getOutStream().println(this.clients);
		}
	}

	// send message to a User (String)
	public void sendMessageToUser(String msg, User userSender, String user){
		boolean find = false;
		for (User client : this.clients) {
			if (client.getNickname().equals(user) && client != userSender) {
				find = true;
				userSender.getOutStream().println(userSender.toString() + " -> " + client.toString() +": " + msg);
				client.getOutStream().println("(<b>Private</b>)" + userSender.toString() + "<span>: " + msg+"</span>");
			}
		}
		if (!find) 
		{
			userSender.getOutStream().println(userSender.toString() + " -> (<b>no one!</b>): " + msg);
		}
	}

class UserHandler implements Runnable {

	private Server server;
	private Player player;
	private Socket client;

	public UserHandler(Server server, Player player, Socket client) {
		this.server = server;
		this.player = player;
		this.client = client;
		//this.user = user;
		//this.server.broadcastAllUsers();
  }

	public void run() {
		String message;

		// when there is a new message, broadcast to all
		Scanner sc;
		try {
			sc = new Scanner(this.client.getInputStream());
			
			while (sc.hasNextLine()) 
			{
				message = sc.nextLine();

	      		// Gestion des messages private
	      		/*if (message.charAt(0) == '@')
	      		{
	      			if(message.contains(" "))
	      			{
	      				System.out.println("private msg : " + message);
	      				int firstSpace = message.indexOf(" ");
	      				String userPrivate = message.substring(1, firstSpace);
	      				server.sendMessageToUser(message.substring(firstSpace+1, message.length()), user, userPrivate);
	      			}

	      			// Gestion du changement
	      		}
	      		else if (message.charAt(0) == '#')
	      		{
	      			user.changeColor(message);
	      			// update color for all other users
	      			this.server.broadcastAllUsers();
	      		}
	      		else
	      		{
	      			// update user list
	      			server.broadcastMessages(message, user);
	      		}*/
			}
			// end of Thread
			//server.removeUser(user);
			//this.server.broadcastAllUsers();
			sc.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

//I don't think we need this
class User {
	
	private static int nbUser = 0;
	private int userId;
	private PrintStream streamOut;
	private InputStream streamIn;
	private String nickname;
	private Socket client;
	private String color;

	// constructor
	public User(Socket client, String name) throws IOException {
		this.streamOut = new PrintStream(client.getOutputStream());
		this.streamIn = client.getInputStream();
		this.client = client;
		this.nickname = name;
		this.userId = nbUser;
		nbUser += 1;
	}

	// getteur
	public PrintStream getOutStream(){
		return this.streamOut;
	}

	public InputStream getInputStream(){
		return this.streamIn;
	}

	public String getNickname(){
		return this.nickname;
	}

	// print user with his color
	public String toString(){

		return "<u><span style='color:"+ this.color +"'>" + this.getNickname() + "</span></u>";

	}
}
}