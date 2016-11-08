package client;

import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import packets.ChatMessage;

import com.jmr.wrapper.client.Client;

public class ClientStarter {

    private final Client client;

    public ClientStarter(String ip, String port, JLabel addressLabel, JTextArea PlayersList, String playerName) {
        client = new Client(ip, Integer.parseInt(port), Integer.parseInt(port));
        client.setListener(new ClientListener());
        client.connect();
//        if (client.isConnected()) {
//            System.out.println("Connected to the server.");
//        }
        
        Scanner in = new Scanner(System.in);
        if (client.isConnected()){
//        	System.out.print("Enter username:"); ///if client connected get username
//        	String username = in.nextLine();
//        	System.out.print(username + ":");
        	addressLabel.setText(playerName + " connected to " + ip + ":" + port);
        	System.out.println("here");
        	while(true){ //completely multithreaded no blocking 
            	String s = in.nextLine();
//            	System.out.println(s);
            	ChatMessage msg = new ChatMessage(playerName, s);
            	client.getServerConnection().sendTcp(msg);
            	
            }
        }
        
    }

    public static void main(String[] args) {
//        new ClientStarter();
    }

}