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

public class ClientStarter {

    private final Client client;
    JTextArea chatBox;
    private String playerName;

    public ClientStarter(String ip, String port, String playerName,TextField connectionInfoField, TextField startGamebtn) {
        client = new Client(ip, Integer.parseInt(port), Integer.parseInt(port));
        
//        chatUI ui_chat = new chatUI(client, playerName);
//        client.setListener(new ClientListener(ui_chat)); //dont delete this is needed
        client.setListener(new ClientListener(startGamebtn));

//        client.setListener(new ClientListener());

        client.connect();
        this.setPlayerName(playerName);
        
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

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

}