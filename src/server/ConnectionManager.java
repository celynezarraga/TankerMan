package server;

import java.util.*;
import com.jmr.wrapper.common.Connection;


public class ConnectionManager {
	private static ConnectionManager instance = new ConnectionManager(); //allows connection managers to be created once;
//	once instance of the class itself
	
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	private ArrayList<String> messages = new ArrayList<String>();

	private HashMap<Connection, String> players = new HashMap<Connection, String>();

	private ConnectionManager(){
		
	}
	
	public void addConnection(Connection con){
		connections.add(con);
	}
	
	public void removeConnection(Connection con){
		connections.remove(con);
	}

	public ArrayList<Connection> getConnections(){
		return connections; 
	}
	
	
	public void addMessage(String con){
		messages.add(con);
	}
	
	public void removeMessage(String con){
		messages.remove(con);
	}

	public ArrayList<String> getMessages(){
		return messages; 
	}
	
	
	public static ConnectionManager getInstance(){
		//	any class outside will get instance of the class
		return instance;
	}
	
}
 