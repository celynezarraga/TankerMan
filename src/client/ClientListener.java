package client;

import java.util.ArrayList;

import javax.swing.JTextArea;

import org.newdawn.slick.gui.TextField;

import packets.ChatMessage;
import packets.ConnectionNumber;
import server.ConnectionManager;
//import ui.chatUI;



import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;


public class ClientListener implements SocketListener {
	
	JTextArea chatBox;
//	static chatUI ui_chat;
	TextField startGamebtn;
	
	public ClientListener(TextField startGamebtn){
//		this.ui_chat = ui_chat;
		this.startGamebtn =startGamebtn; 
	   }


    @Override
    public void received(Connection con, Object object) {
    	if (object instanceof ChatMessage){
    		ChatMessage msg = (ChatMessage) object;
    		System.out.println(msg.username + ":" +msg.message); 
//    		ui_chat.chatBox.append(msg.username + ":" +msg.message + "\n");
    	}
    	if (object instanceof ConnectionNumber){
    		ConnectionNumber noOfConnections = (ConnectionNumber) object;
    		startGamebtn.setText(startGamebtn.getText().concat("PLAYERS COMPLETE. CLICK TO START GAME"));
    		System.out.println("PLAYERS COMPLETE START GAME");
        	

    		
    	}
    }

    @Override
    public void connected(Connection con) {
    	System.out.println("HELLO");
//    	ui_chat.display(ui_chat.playerName);
    }

    @Override
    public void disconnected(Connection con) {
    }
    public static void exposeUI(){
//    	ui_chat.display( ui_chat.playerName);

    }

}