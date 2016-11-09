package server;

import packets.ChatMessage;

import javax.swing.JTextArea;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;


public class ServerListener implements SocketListener {

	JTextArea serverConsole;
	
   public ServerListener(JTextArea serverConsole){
	   this.serverConsole = serverConsole;
   }

    @Override
    public void connected(Connection con) {
//    	 System.out.println("New client connected.");
    	 if(!(ConnectionManager.getInstance().getConnections().size()==4)){
    		 serverConsole.setText(serverConsole.getText().concat("New client connected." + con.getId() + "\n"));
    	    	
//        	 whenever a client connects
        	 ConnectionManager.getInstance().addConnection(con); //when a new client connect, add connection to the array
        	 serverConsole.setText(serverConsole.getText().concat("Size" + ConnectionManager.getInstance().getConnections().size() + "\n"));
    	 }
    	 else{
    		 con.close();
    	 }
//    	 for (Connection c: ConnectionManager.getInstance().getConnections()){
// 			c.sendTcp(new ChatMessage("", "Client has connected")); //send all messages to connected clients
// 		}
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