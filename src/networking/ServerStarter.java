package client;

   // File Name ServerStarter.java

import java.net.*;
import java.io.*;
import java.util.LinkedList;

import org.newdawn.slick.gui.TextField;

import tankerman.ServerState;


public class ServerStarter extends Thread {
   static LinkedList<Socket> clients = new LinkedList<Socket>();
   private static ServerSocket serverSocket;
   String ip;
   private static TextField console;
	private static TextField  ipPortStringfield;
	private TextField startGamebtn;
	int port = 1337;

   public ServerStarter( TextField console, TextField ipPortStringfield, TextField startGamebtn) throws IOException {
      //insert missing line here for binding a port to a socket
	   ServerStarter.console=console;
	   ServerStarter.ipPortStringfield=ipPortStringfield;
	   serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(1000000);
      console.setText(console.getText().concat("Server has started.\n"));
		try{
			ip = InetAddress.getLocalHost().toString();
			String[] ipAdd = ip.split("\\/");
			ipPortStringfield.setText("Now serving at " +ipAdd[ipAdd.length-1] + " Port: " + port);
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
		
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
                   console.setText(console.getText().concat("New client connected." + clients.size() + "\n"));
          		 	System.out.println("New client connected." );
          	    	
//              	 	whenever a client connects
          		 	console.setText(console.getText().concat("Size" + clients.size() + "\n"));
          		 	for(Socket c : clients) {
                      DataOutputStream out = new DataOutputStream(c.getOutputStream());
                      /* Send data to the ClientSocket */
                      /*received */

                      out.writeUTF("!=!"+Integer.toString(clients.size()));
                      out.flush();
                   }
              	 
          		 	if(clients.size() != 2){
                        System.out.println("Waiting to complete players");

                    }else{
                    	System.out.println("PLAYERS ALREADY COMPLETE");
                    	ServerState.startGamebtn.setText("PLAYERS COMPLETE. CLICK TO START GAME");
                    	ClientStarter.startGame=true;
                    }
//          		 	else{
                    	
//                    	new Thread(){
//                            public void run(){
//                               try{                              	 
//                              	 
//                                  	 while(true){
//                                           DataInputStream in = new DataInputStream(client.getInputStream());
//                                           /* Read data from the ClientSocket */
//                                           String message = in.readUTF();
//                                           System.out.println(message);
//                                           
//                                           
//                                           for(Socket c : clients) {
//                                              DataOutputStream out = new DataOutputStream(c.getOutputStream());
//                                              /* Send data to the ClientSocket */
//                                              /*received */
//
//                                              out.writeUTF(message);
//                                              out.flush();
//                                           }
//                                        }                            	 
//                                                              
//                               } catch (Exception e){
//                                  System.out.print("Error");
//                               }
//                            }
//                         }.start(); 
//                    }
                                    
                } catch(SocketTimeoutException s) {
                   System.out.println("Socket timed out!");
                   break;
                } catch(IOException e) {
                   System.out.println("Usage: java ServerStarter <port no.>");
                   break;
                }
             }
        }
            
         
      }.start();
   }

   public static void main(String [] args) {
//      try {
////         Thread t = new ServerStarter(console, ipPortStringfield, startGamebtn);
//
////         t.start();
//      } catch(IOException e) {
//         System.out.println("Usage: java ServerStarter <port no.>");
//      } catch(ArrayIndexOutOfBoundsException e) {
//         System.out.println("Usage: java ServerStarter <port no.> ");
//      }
   }
}
