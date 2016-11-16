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
	TextField chatMsgsTf;
	TextField chatFieldTf;
	
	public static Boolean startGame = false;;
	
	public ClientListener(TextField startGamebtn, TextField chatFieldTf, TextField chatMsgsTf){
//		this.ui_chat = ui_chat;
		this.startGamebtn =startGamebtn; 
		this.chatFieldTf = chatFieldTf;
		this.chatMsgsTf = chatMsgsTf;
	   }


    @Override
    public void received(Connection con, Object object) {
    	if (object instanceof ChatMessage){
    		ChatMessage msg = (ChatMessage) object;
    		System.out.println(msg.username + ":" +msg.message); 
    		chatMsgsTf.setText(chatMsgsTf.getText().concat(msg.username + ":" +msg.message));
//    		ui_chat.chatBox.append(msg.username + ":" +msg.message + "\n");
    	}
    	if (object instanceof ConnectionNumber){
    		ConnectionNumber noOfConnections = (ConnectionNumber) object;
    		
    		if (noOfConnections.noOfConnections == 2){
    			startGamebtn.setText(startGamebtn.getText().concat("PLAYERS COMPLETE. CLICK TO START GAME"));
    			System.out.println("PLAYERS COMPLETE START GAME");
    			startGame = true;
    			 for (Connection c: ConnectionManager.getInstance().getConnections()){
    				c.sendTcp(new ChatMessage("", "GAME HAS STARTED"));
    			}
    		} 
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