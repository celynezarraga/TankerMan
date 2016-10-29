package client;

import java.util.Scanner;

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
        while(true){ //cimpletely multithreaded no blocking 
        	String s = in_nextline();
        }
    }

    public static void main(String[] args) {
        new ClientStarter();
    }

}