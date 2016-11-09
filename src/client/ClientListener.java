package client;

import packets.ChatMessage;
import server.ConnectionManager;
import ui.chatUI;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;


public class ClientListener implements SocketListener {

    @Override
    public void received(Connection con, Object object) {
    	if (object instanceof ChatMessage){
    		ChatMessage msg = (ChatMessage) object;
    		System.out.println(msg.username + ":" +msg.message); 
        	ConnectionManager.getInstance().addMessage(msg.username + ":" +msg.message);
//       	 ConnectionManager.getInstance().addConnection(con); //when a new client connect, add connection to the array
    		

    	}
    }

    @Override
    public void connected(Connection con) {
    }

    @Override
    public void disconnected(Connection con) {
    }

}