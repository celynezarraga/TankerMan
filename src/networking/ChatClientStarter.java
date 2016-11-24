package networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextArea;

import org.newdawn.slick.gui.TextField;

import packets.ChatMessage;
import server.ConnectionManager;
import tankerman.ClientState;
import tankerman.WorldMap;

import com.jmr.wrapper.common.Connection;


//import ui.chatUI;


//
//import com.jmr.wrapper.client.Client;
//import com.jmr.wrapper.common.Connection;
//import com.jmr.wrapper.common.exceptions.NNClientCantConnect;

public class ChatClientStarter extends Thread {

//    public  static Client client;
    JTextArea chatBox;
	private TextField startGamebtn;
    public static   String playerName;
    public static Boolean startGame=false;
    public Boolean startGameHere;
    String ip; String port; 
    TextField connectionInfoField;
    public static Socket client;
	public static int chatCounter = 0;


    
    
    
    

    public ChatClientStarter(String playerName, String ip, String port) {
//    	this.startGamebtn =startGamebtn; 
    	this.playerName=playerName;
    	this. ip=ip;
    	this.port=port;
    	this.run();
    }
 
    public static void gracefulDisconnect(Socket sok) throws IOException {
        InputStream is = sok.getInputStream();
        sok.shutdownOutput(); // Sends the 'FIN' on the network
        while (is.read() >= 0) ; // "read()" returns '-1' when the 'FIN' is reached
        sok.close(); // Now we can close the Socket
    }
    public static void send( String msg){
//		try{
//			byte[] buf = msg.getBytes();
//        	InetAddress address = InetAddress.getByName(ip);
////        	DatagramPacket packet = new DatagramPacket(buf, buf.length, address, ip);
//        	socket.send(packet);
//        }catch(Exception e){}
		System.out.println("from SNED: "+msg);

		try{
			OutputStream outToServer = ChatClientStarter.client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF(playerName+": " +msg);
            out.writeUTF(playerName+": " +msg);
            out.flush();
           
       }catch(Exception e){}
		
	}
  public void run() {
      new Thread() {
         public void run() {
          
        	   try {
	              Socket client = new Socket(ip, Integer.parseInt(port));
	              ChatClientStarter.client=client;
	            
	
	                       
	              /* Open a ClientSocket and connect to ServerSocket */
	              System.out.println("Connecting to " + ip + " on port " + port);
	              //insert missing line here for creating a new socket for client and binding it to a port
	     	 
//	              new Thread() {
//	            	  //sending to all users thread
//	                 public void run(){
//	           	      try {
//	                       OutputStream outToServer = client.getOutputStream();
//	                       DataOutputStream out = new DataOutputStream(outToServer);
//	                          
//	                       while(true) {
//	                    	   //connected
//	                          /* Send data to the ServerSocket */
//	                          String message = null;
//	                          if(WorldMap.enterUp) message = WorldMap.getText(WorldMap.enterUp);
//	                          
//	                          
//	                          out.writeUTF(playerName+": " +message);
//	                       }
//	                    } catch (Exception e){
//	                       System.out.println("Error");
//	                    };
//	                 }
//	              }.start();
	
	              new Thread() {
	            	  //recieve thread
	
	
					public void run(){
	                    try {
	                    	//received
	                       /* Receive data from the ServerSocket */
	                       InputStream inFromServer = client.getInputStream();
	                       DataInputStream in = new DataInputStream(inFromServer);
	                       
	                       while(true) {
	                          /* Send data to the ServerSocket */
	                    	  
	                    	   System.out.println("in.readUTF()"+in.readUTF());	                    	   
//	                   			WorldMap.chatMsgsTf.setText(WorldMap.chatMsgsTf.getText().concat(in.readUTF()+"\n"));
	                   			
	                   			String currMessage = WorldMap.chatMsgsTf.getText();
	                			
	                			if(chatCounter < 22) {
	                				WorldMap.chatMsgsTf.setText(currMessage + "\n" + in.readUTF());
	                				
	                				
	                			} else {
	                				String[] result = currMessage.split("\n", 2);
	                				WorldMap.chatMsgsTf.setText(result[1] + "\n" + in.readUTF());
	                				
	                			}
	                			chatCounter++;
	                          
	                       }
	                    } catch (Exception e){
	                       System.out.println("Error: Receive data from the ServerSocket");
	                    };
	                 }
	              }.start();

           } catch(IOException e) {
              //e.printStackTrace();
           	System.out.println("Cannot find Server");
           } 
        } 
    }.start();
 }
        
        
    

    public static void main(String[] args) {
//        new ChatClientStarter();
    }
    
    
	
	
	

}