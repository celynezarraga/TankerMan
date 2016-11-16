package server;

import packets.ChatMessage;
import packets.ConnectionNumber;

import javax.swing.JTextArea;

import org.newdawn.slick.gui.TextField;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;


public class ServerListener implements SocketListener {

	TextField serverConsole;
	
   public ServerListener(TextField serverConsole){
	   this.serverConsole = serverConsole;
   }

    @Override
    public void connected(Connection con) {
//    	 System.out.println("New client connected.");
    	
    	 
//    	 if(!(ConnectionManager.getInstance().getConnections().size()==2)){
    		 serverConsole.setText(serverConsole.getText().concat("New client connected." + con.getId() + "\n"));
    		 System.out.println("New client connected." );
    	    	
//        	 whenever a client connects
        	 ConnectionManager.getInstance().addConnection(con); //when a new client connect, add connection to the array
        	 serverConsole.setText(serverConsole.getText().concat("Size" + ConnectionManager.getInstance().getConnections().size() + "\n"));
        	 
        	 
        	 if(ConnectionManager.getInstance().getConnections().size()==2){
    		 
        		 serverConsole.setText(serverConsole.getText().concat("Server already full. Game can be starter" + "\n"));
    		 
//    		 con.close();
        	 }
    	 
	    	 for (Connection c: ConnectionManager.getInstance().getConnections()){
		  			c.sendTcp(new ConnectionNumber(ConnectionManager.getInstance().getConnections().size())); //send all messages to connected clients
		  	 }
    	 
    	
    }

    @Override
    public void disconnected(Connection con) {
//   	 System.out.println("Client disconnected.");
    	serverConsole.setText(serverConsole.getText().concat("Client disconnected." + con.getId() + "\n"));
   	 ConnectionManager.getInstance().removeConnection(con);
   	 for (Connection c: ConnectionManager.getInstance().getConnections()){
			c.sendTcp(new ChatMessage("", "Client has disconnected"));
		}

    }
    @Override
    public void received(Connection con, Object object) {
    	if (object instanceof ChatMessage){
    		ChatMessage msg = (ChatMessage) object;
    		for (Connection c: ConnectionManager.getInstance().getConnections()){
    			c.sendTcp(msg);
    		}
    		
    	}
    }

}