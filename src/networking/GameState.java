package networking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// Reference from Circle Wars (Hermocilla)

public class GameState{
	
	private Map players=new HashMap();
	private ArrayList<String> playerNames = new ArrayList<String>();
	
	public GameState(){}
	
	public void update(String name, NetPlayer player){
		players.put(name,player);
		playerNames.add(name);
	}
	
	public String toString(){
		String retval="";
		for(Iterator ite=players.keySet().iterator();ite.hasNext();){
			String name=(String)ite.next();
			NetPlayer player=(NetPlayer)players.get(name);
			retval+=player.toString()+":";
		}
		return retval;
	}

	public Map getPlayers(){
		return players;
	}
	
	public int getPlayerID(String name){
		return playerNames.indexOf(name);
	}
}
