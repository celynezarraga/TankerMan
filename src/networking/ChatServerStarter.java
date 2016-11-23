package networking;

   // File Name ChatServerStarter.java

import java.net.*;
import java.io.*;
import java.util.LinkedList;

import org.newdawn.slick.gui.TextField;

import tankerman.ServerState;
import tankerman.WorldMap;


public class ChatServerStarter extends Thread {
   static LinkedList<Socket> clients = new LinkedList<Socket>();
   private static ServerSocket serverSocket;
   String ip;
   private static TextField console;
	private static TextField  ipPortStringfield;
	private TextField startGamebtn;
	int port = 1337;

   public ChatServerStarter( ) throws IOException {
      //insert missing line here for binding a port to a socket
	   
	   serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(1000000);
//      console.setText(console.getText().concat("Chat Server has started.\n"));
		
		
		this.run();
		
   }

   public void run() {
      new Thread() {
         public void run() {
            boolean connected = true;           
			
            //start only if 4 players          
            
        	while(connected) {            
                try {
                   // Start accepting data from the ServerSocket //
                   //insert missing line for accepting connection from client here]
                   final Socket client = serverSocket.accept();
                   System.out.println("Just connected to " + client.getRemoteSocketAddress());
                   clients.add(client);
                   ServerState.console.setText(ServerState.console.getText().concat("New client connected." + clients.size() + "\n"));
              		System.out.println("New client connected." );
              	    	
    //              	 	whenever a client connects
              		 ServerState.console.setText(ServerState.console.getText().concat("Size" + clients.size() + "\n"));

                    	
                 	new Thread(){
                         public void run(){
                            try{                              	 
                           	 
                               	 while(true){
                                        DataInputStream in = new DataInputStream(client.getInputStream());
                                        /* Read data from the ClientSocket */
                                        String message = in.readUTF();
//                                        WorldMap.chatMsgsTf.setText(WorldMap.chatMsgsTf.getText().concat(message));
                                        System.out.println(message);
                                        
                                        
                                        for(Socket c : clients) {
                                           DataOutputStream out = new DataOutputStream(c.getOutputStream());
                                           /* Send data to the ClientSocket */
                                           /*received */

                                           out.writeUTF(message);
                                           
                                           out.flush();
                                        }
                                     }                            	 
                                                           
                            } catch (Exception e){
                               System.out.print("Error: ChatServerStarter");
                            }
                         }
                      }.start(); 
                 
                                    
                } catch(SocketTimeoutException s) {
                   System.out.println("Socket timed out!");
                   break;
                } catch(IOException e) {
                   System.out.println("Usage: java ChatServerStarter <port no.>");
                   break;
                }
             }
        }
            
         
      }.start();
   }

   public static void main(String [] args) {
//      try {
////         Thread t = new ChatServerStarter(console, ipPortStringfield, startGamebtn);
//
////         t.start();
//      } catch(IOException e) {
//         System.out.println("Usage: java ChatServerStarter <port no.>");
//      } catch(ArrayIndexOutOfBoundsException e) {
//         System.out.println("Usage: java ChatServerStarter <port no.> ");
//      }
   }
}
