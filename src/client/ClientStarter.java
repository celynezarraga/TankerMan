package client;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.newdawn.slick.gui.TextField;

import packets.ChatMessage;
import server.ConnectionManager;
//import ui.chatUI;



import com.jmr.wrapper.client.Client;
import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.exceptions.NNClientCantConnect;

public class ClientStarter {

    public  static Client client;
    JTextArea chatBox;
    public static   String playerName;
    
    
    
    

    public ClientStarter(String ip, String port, String playerName,TextField connectionInfoField, 
    		TextField startGamebtn) throws NNClientCantConnect{
        client = new Client(ip, Integer.parseInt(port), Integer.parseInt(port));
        
//        chatUI ui_chat = new chatUI(client, playerName);
//        client.setListener(new ClientListener(ui_chat)); //dont delete this is needed
        client.setListener(new ClientListener(startGamebtn));

//        client.setListener(new ClientListener());

        client.connect();
        ClientStarter.playerName=playerName;
        
//        if (client.isConnected()) {
//            System.out.println("Connected to the server.");
//        }
        
//        Scanner in = new Scanner(System.in);
        if (client.isConnected()){
//        	
//        		addressLabel.setText(playerName + " connected to " + ip + ":" + port);
        	connectionInfoField.setText(connectionInfoField.getText().concat(playerName + " connected to " + ip + ":" + port));
        	System.out.println(playerName + "has connected");
			   	        		       	
        	
        }
        
    }

    public static void main(String[] args) {
//        new ClientStarter();
    }
    
    public Boolean connected(){
    	return client.isConnected();
    }

	
	
	

}