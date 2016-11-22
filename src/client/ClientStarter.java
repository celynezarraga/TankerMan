package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextArea;

import org.newdawn.slick.gui.TextField;

import packets.ChatMessage;
import server.ConnectionManager;
import tankerman.ClientState;

import com.jmr.wrapper.common.Connection;


//import ui.chatUI;


//
//import com.jmr.wrapper.client.Client;
//import com.jmr.wrapper.common.Connection;
//import com.jmr.wrapper.common.exceptions.NNClientCantConnect;

public class ClientStarter {

//    public  static Client client;
    JTextArea chatBox;
    public static   String playerName;
    
    
    
    

    public ClientStarter(String ip, String port, String playerName,TextField connectionInfoField, 
    		TextField startGamebtn) {
//        client = new Client(ip, Integer.parseInt(port), Integer.parseInt(port));
//        client.setListener(new ClientListener(startGamebtn));
//        client.connect();
//        ClientStarter.playerName=playerName;
//        if (client.isConnected()){
//        	connectionInfoField.setText(connectionInfoField.getText().concat(playerName + " connected to " + ip + ":" + port));
//        	System.out.println(playerName + "has connected");
//        }
        try {
        	  
              Socket client = new Socket(ip, Integer.parseInt(port));
              //if connected
              connectionInfoField.setText(connectionInfoField.getText().concat(playerName + " connected to " + ip + ":" + port));
              
              final Scanner scanner = new Scanner(System.in);
//              System.out.print("Enter username: ");
//              String username = sc.nextLine();

                       
              /* Open a ClientSocket and connect to ServerSocket */
              System.out.println("Connecting to " + ip + " on port " + port);
              //insert missing line here for creating a new socket for client and binding it to a port
     	 
              new Thread() {
                 public void run(){
           	      try {
                       OutputStream outToServer = client.getOutputStream();
                       DataOutputStream out = new DataOutputStream(outToServer);
                          
                       while(true) {
                    	   //connected
                          /* Send data to the ServerSocket */
                          String message = scanner.nextLine();
                          
                          out.writeUTF(playerName+": " +message);
                       }
                    } catch (Exception e){
                       System.out.println("Error");
                    };
                 }
              }.start();

              new Thread() {
                 public void run(){
                    try {
                    	//received
                       /* Receive data from the ServerSocket */
                       InputStream inFromServer = client.getInputStream();
                       DataInputStream in = new DataInputStream(inFromServer);
                 
                       while(true) {
                          /* Send data to the ServerSocket */
                    	  
                    	   System.out.println(in.readUTF());
                    	   
                    	 if (in.readUTF().equals("!=!2")){
	                       			
	                       			System.out.println("PLAYERS COMPLETE START GAME");
	                       			ClientState.startGame = true;
	                       			 
                       	 } else{
                       		 
                       	 }
                    	   
                    	      
                          
                          
                       }
                    } catch (Exception e){
                       System.out.println("Error");
                    };
                 }
              }.start();

           } catch(IOException e) {
              //e.printStackTrace();
           	System.out.println("Cannot find Server");
           } catch(ArrayIndexOutOfBoundsException e) {
              System.out.println("Usage: java GreetingClient <server ip> <port no.> '<your message to the server>'");
           }
        
        
    }

    public static void main(String[] args) {
//        new ClientStarter();
    }
    
    
	
	
	

}