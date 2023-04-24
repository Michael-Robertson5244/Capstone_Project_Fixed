package goteamgo.AdLibStories;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.InputStream;
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

	private int numPlayers = 1;
	private int maxPlayers;
	private int port;
	private List<User> clients;
	private List<Player> players;
	private ServerSocket server;

	/*public static void main(String[] args) throws IOException {
		new Server(60564, 4).run();
	}*/

	public Server(int port, int maxPlayers) {
		this.port = port;
		this.maxPlayers = maxPlayers;
		this.players = new ArrayList<Player>();
	}

	public void run() {
		
		InetAddress localhost;
		try {
			localhost = InetAddress.getLocalHost();
			
			server = new ServerSocket(port, 1, localhost) {
				protected void finalize() throws IOException {
					this.close();
				}
			};
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Port 60564 is now open.");
		System.out.println(server.getInetAddress().getHostAddress());
		System.out.println(server.getLocalPort());

		while (true) 
		{
			// accepts a new client
			if(numPlayers < maxPlayers)
			{
				try {
					Socket client = server.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Receive the player from the newly joined client and add it to list of players
				
				//Send the playerNum to the player
				
				//Send the player list to all the clients to update in the game
				
				numPlayers++;

			/* get nickname of newUser
			String nickname = (new Scanner ( client.getInputStream() )).nextLine();
			nickname = nickname.replace(",", ""); //  ',' use for serialisation
			nickname = nickname.replace(" ", "_");
			System.out.println("New Client: \"" + nickname + "\"\n\t     Host:" + client.getInetAddress().getHostAddress());

			// create new User
			User newUser = new User(client, nickname);

			// add newUser message to list
			this.clients.add(newUser);

			// Welcome msg
			newUser.getOutStream().println(
					"<img src='https://www.kizoa.fr/img/e8nZC.gif' height='42' width='42'>"
							+ "<b>Welcome</b> " + newUser.toString() +
							"<img src='https://www.kizoa.fr/img/e8nZC.gif' height='42' width='42'>"
					);
			*/

				// create a new thread for newUser incoming messages handling
				new Thread(new UserHandler(this)).start();
			}
		}
  }

  // delete a user from the list
	public void removeUser(User user){
		this.clients.remove(user);
	}

	// send incoming msg to all Users
	public void broadcastMessages(String msg, User userSender) {
		for (User client : this.clients) {
			client.getOutStream().println(userSender.toString() + "<span>: " + msg+"</span>");
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
	private User user;

	public UserHandler(Server server) {
		this.server = server;
		//this.user = user;
		this.server.broadcastAllUsers();
  }

	public void run() {
		String message;

		// when there is a new message, broadcast to all
		Scanner sc = new Scanner(this.user.getInputStream());
		
		while (sc.hasNextLine()) 
		{
			message = sc.nextLine();

      		// Gestion des messages private
      		if (message.charAt(0) == '@')
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
      		}
		}
		// end of Thread
		server.removeUser(user);
		this.server.broadcastAllUsers();
		sc.close();
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

	// change color user
	public void changeColor(String hexColor){
		// check if it's a valid hexColor
		Pattern colorPattern = Pattern.compile("#([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8})");
		Matcher m = colorPattern.matcher(hexColor);
		if (m.matches())
		{
			Color c = Color.decode(hexColor);
			// if the Color is too Bright don't change
			double luma = 0.2126 * c.getRed() + 0.7152 * c.getGreen() + 0.0722 * c.getBlue(); // per ITU-R BT.709
			if (luma > 160) 
			{
				this.getOutStream().println("<b>Color Too Bright</b>");
				return;
			}
			this.color = hexColor;
			this.getOutStream().println("<b>Color changed successfully</b> " + this.toString());
			return;
		}
		this.getOutStream().println("<b>Failed to change color</b>");
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