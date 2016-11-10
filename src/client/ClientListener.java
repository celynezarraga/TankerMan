package client;

import java.util.ArrayList;

import javax.swing.JTextArea;

import packets.ChatMessage;
import server.ConnectionManager;
import ui.chatUI;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;


public class ClientListener implements SocketListener {
	
	JTextArea chatBox;
	static chatUI ui_chat;
	public ClientListener(chatUI ui_chat){
//		this.chatBox = ui_chat.chatBox;
		this.ui_chat = ui_chat;
	   }


    @Override
    public void received(Connection con, Object object) {
    	if (object instanceof ChatMessage){
    		ChatMessage msg = (ChatMessage) object;
    		System.out.println(msg.username + ":" +msg.message); 
    		ui_chat.chatBox.append(msg.username + ":" +msg.message + "\n");


    	}
    }

    @Override
    public void connected(Connection con) {
    	System.out.println("HELLO");
    	ui_chat.display(ui_chat.playerName);
    }

    @Override
    public void disconnected(Connection con) {
    }
    public static void exposeUI(){
    	ui_chat.display( ui_chat.playerName);

    }

}