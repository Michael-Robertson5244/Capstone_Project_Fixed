package goteamgo.AdLibStories;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {
    
    private String serverName;
    private int port;
    private Socket socket;
    private ObjectOutputStream outputObject;
    private ObjectInputStream inputObject;
    private boolean isConnected;
    
    public Client(String serverName, int port) {
        this.serverName = serverName;
        this.port = port;
        this.isConnected = false;
    }
    
    public void start() {
        try {
            this.socket = new Socket(serverName, port);
            this.outputObject = new ObjectOutputStream(socket.getOutputStream());
            this.inputObject = new ObjectInputStream(socket.getInputStream());
            this.isConnected = true;
            
            System.out.println("Connected to server " + serverName + " on port " + port);
            
            Scanner scanner = new Scanner(System.in);
            while (isConnected) {
                System.out.println("Enter a message to send to the server: ");
                String message = scanner.nextLine();
                sendMessage(message);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void sendMessage(String message) throws IOException {
        outputObject.writeObject(message);
        outputObject.flush();
        
        try {
            Object object = inputObject.readObject();
            if (object instanceof List<?>) {
                List<?> list = (List<?>) object;
                System.out.println("Received the list of players:");
                for (Object obj : list) {
                    if (obj instanceof Player) {
                        Player player = (Player) obj;
                        System.out.println(player.toString());
                    }
                }
            } else if (object instanceof Integer) {
                int playerNum = (int) object;
                System.out.println("You are player " + playerNum);
            } else if (object instanceof String) {
                String response = (String) object;
                if (response.equals("Maxplayers")) {
                    System.out.println("The server is currently at max capacity. Try again later.");
                    isConnected = false;
                } else {
                    System.out.println("Server response: " + response);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Client client = new Client("172.22.34.33", 60999);
        client.start();
    }
}