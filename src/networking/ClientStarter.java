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

public class ClientStarter extends Thread {

//    public  static Client client;
    JTextArea chatBox;
	private TextField startGamebtn;
    public static   String playerName;
    public static Boolean startGame=false;
    public Boolean startGameHere;
    String ip; String port; 
    TextField connectionInfoField;
    public static Socket client;

    
    
    
    

    public ClientStarter(String ip, String port, String playerName,TextField connectionInfoField, TextField startGamebtn) {
//    	this.startGamebtn =startGamebtn; 
    	this. ip=ip;
    	this.port=port;
    	this.playerName=playerName;
    	this.startGamebtn=startGamebtn;
    	this.run();
    	
//        client = new Client(ip, Integer.parseInt(port), Integer.parseInt(port));
//        client.setListener(new ClientListener(startGamebtn));
//        client.connect();
//        ClientStarter.playerName=playerName;
//        if (client.isConnected()){
//        	connectionInfoField.setText(connectionInfoField.getText().concat(playerName + " connected to " + ip + ":" + port));
//        	System.out.println(playerName + "has connected");
//        }
    }
    public static void gracefulDisconnect(Socket sok) throws IOException {
        InputStream is = sok.getInputStream();
        sok.shutdownOutput(); // Sends the 'FIN' on the network
        while (is.read() >= 0) ; // "read()" returns '-1' when the 'FIN' is reached
        sok.close(); // Now we can close the Socket
    }
  public void run() {
      new Thread() {
         public void run() {
          
        	   try {
	              Socket client = new Socket(ip, Integer.parseInt(port));
	              ClientStarter.client=client;
	              
	              //if connected
	              ClientState.connectionInfoField.setText(ClientState.connectionInfoField.getText().concat(playerName + " connected to " + ip + ":" + port));
	              
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
	                          if(ClientStarter.startGame==true){
		                    	   System.out.println("!=!"+Integer.toString(ServerStarter.clients.size()));

	                        	  out.writeUTF("!=!"+Integer.toString(ServerStarter.clients.size()));
	                          }
	                          
	                          out.writeUTF(playerName+": " +message);
	                       }
	                    } catch (Exception e){
	                       System.out.println("Error");
	                    };
	                 }
	              }.start();
	
	              new Thread() {
	
					        private boolean startGame;
	
							public void run(){
	                    try {
	                    	//received
	                       /* Receive data from the ServerSocket */
	                       InputStream inFromServer = client.getInputStream();
	                       DataInputStream in = new DataInputStream(inFromServer);
	                       if (in.readUTF().equals("!=!2")){
            					ClientState.startGamebtn.setText("PLAYERS COMPLETE. CLICK TO START GAME");
            					this.startGame=true;
            					ClientStarter.startGame=true;

                      			System.out.println("PLAYERS COMPLETE START GAME");
			//	                       			startGameHere = true;	                       			 
			                 } 
			               	 
	                       while(true) {
	                          /* Send data to the ServerSocket */
	                    	  
	                    	   System.out.println(in.readUTF());
	                    	   
	                    	 if (in.readUTF().equals("!=!2")){
	                 					ClientState.startGamebtn.setText("PLAYERS COMPLETE. CLICK TO START GAME");
	                 					this.startGame=true;
	                 					ClientStarter.startGame=true;

		                       			System.out.println("PLAYERS COMPLETE START GAME");
	//	                       			startGameHere = true;	                       			 
	                       	 } 
	                    	 if(this.startGame=true)  ClientStarter.startGame=true;
	 						System.out.println("CLIENTSTARTER" + ClientStarter.startGame);
	 						System.out.println("thisCLIENTSTARTER" + ClientStarter.startGame);

	
	                    	 System.out.println(startGame);
	                    	 System.out.println(startGame);
	                    	 System.out.println(startGame);
	                    	 System.out.println(startGame);
	
	                          
	                          
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
    }.start();
    System.out.println("CLIENTSTARTERENDLALALALALALALLALLALAL" + ClientStarter.startGame);
 }
        
        
    

    public static void main(String[] args) {
//        new ClientStarter();
    }
    
    
	
	
	

}