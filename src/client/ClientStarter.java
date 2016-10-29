package client;

import java.util.Scanner;

import packets.ChatMessage;

import com.jmr.wrapper.client.Client;

public class ClientStarter {

    private final Client client;

    public ClientStarter() {
        client = new Client("localhost", 1337, 1337);
        client.setListener(new ClientListener());
        client.connect();
//        if (client.isConnected()) {
//            System.out.println("Connected to the server.");
//        }
        
        Scanner in = new Scanner(System.in);
        if (client.isConnected()){
        	System.out.print("Enter username:");
        	String username = in.nextLine();
        	System.out.print(username + ":");
        	while(true){ //cimpletely multithreaded no blocking 
            	String s = in.nextLine();
//            	System.out.println(s);
            	ChatMessage msg = new ChatMessage(username, s);
            	client.getServerConnection().sendTcp(msg);
            	
            }
        }
        
    }

    public static void main(String[] args) {
        new ClientStarter();
    }

}