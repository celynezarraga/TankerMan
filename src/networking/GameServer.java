package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.gui.TextField;

// Reference from Circle Wars (Hermocilla)

public class GameServer implements Runnable, Constants{
	
	String server="localhost";

	public static DatagramSocket serverSocket = null;
	Thread t = new Thread(this);
	String playerData;
	GameState game;
	int playerCount=0;
	int numPlayers;
	long startTime, currTime, elapsedTime;
	int MAX_TIME = 180;
	public static int gameStage=WAITING_FOR_PLAYERS;
	
	public GameServer(TextField serverConsole, TextField ipPortStringfield){
		this.numPlayers = 2;
		
		try {
            serverSocket = new DatagramSocket(1337);
			serverSocket.setSoTimeout(1000);
		} catch (IOException e) {
            System.err.println("Could not listen on port: "+1337);
            System.exit(-1);
		}catch(Exception e){}
		//Create the game state
		game = new GameState();
		
		System.out.println("Game created...");
		serverConsole.setText(serverConsole.getText().concat("Server has started.\n"));
		try{
			String ip = InetAddress.getLocalHost().toString();
			String[] ipAdd = ip.split("\\/");
			ipPortStringfield.setText("Now serving at " + ipAdd[ipAdd.length-1] + " Port: " + serverSocket.getLocalPort());
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
		
		//Start the game thread
		t.start();
	}
	
	public void broadcast(String msg){
		for(Iterator ite=game.getPlayers().keySet().iterator();ite.hasNext();){
			String name=(String)ite.next();
			NetPlayer player=(NetPlayer)game.getPlayers().get(name);			
			send(player,msg);	
		}
	}
	
	public void send(NetPlayer player, String msg){
		DatagramPacket packet;	
		byte buf[] = msg.getBytes();		
		packet = new DatagramPacket(buf, buf.length, player.getAddress(),player.getPort());
		try{
			serverSocket.send(packet);
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	
	//
	
	
	public void run() {
		while(true){
			
			// Get the data from players
			byte[] buf = new byte[256];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			try{
     			serverSocket.receive(packet);
			}catch(Exception ioe){}
			
			/**
			 * Convert the array of bytes to string
			 */
			playerData=new String(buf);
			
			//remove excess bytes
			playerData = playerData.trim();
			//if (!playerData.equals("")){
			//	System.out.println("Player Data:"+playerData);
			//}
		
			// process
			switch(gameStage){
				  case WAITING_FOR_PLAYERS:
						//System.out.println("Game State: Waiting for players...");
						if (playerData.startsWith("CONNECT")){
							String tokens[] = playerData.split(" ");
							NetPlayer player=new NetPlayer(tokens[1],packet.getAddress(),packet.getPort());
							System.out.println("Player connected: "+tokens[1]);
							game.update(tokens[1].trim(),player);
							broadcast("CONNECTED "+tokens[1] + "PLAYER_ID-" + game.getPlayerID(tokens[1]));
							playerCount++;
							if (playerCount==numPlayers){
								gameStage=GAME_START;
							}
						}
					  break;	
				  case GAME_START:
					  System.out.println("Game State: START");
					  broadcast("START");
					  gameStage=IN_PROGRESS;
					  startTime = System.currentTimeMillis();
					  break;
				  case IN_PROGRESS:
					  String sec = "0";
					  long tEnd = System.currentTimeMillis();
					  long tRes = 180000 -(tEnd - startTime); // time in nanoseconds
					  int secRemaining = (int) (tRes / 1000) % 60 ;
					  int minRemaining = (int) ((tRes / (1000*60)) % 60);
					  if(secRemaining < 10){
						  sec = sec.concat(String.valueOf(secRemaining));
					  }
					  else{
						  sec = String.valueOf(secRemaining);  
					  }
					  broadcast("TIME -" + String.valueOf(minRemaining + ":"+sec));
					  
					  if(secRemaining == 0 && minRemaining==0){
						  broadcast("END");
						  gameStage=GAME_END;
					  }
//					  
					  //Player data was received!
					  if (playerData.startsWith("PLAYER")){
						  System.out.println("PLAYER");
						  
						  //Tokenize:
						  //The format: PLAYER <player name> <x> <y>
						  String[] playerInfo = playerData.split(" ");					  
						  String id =playerInfo[1];
						  int x = Integer.parseInt(playerInfo[2].trim());
						  int y = Integer.parseInt(playerInfo[3].trim());
						  String character = playerInfo[4];
						  //Get the player from the game state
						  //NetPlayer player=(NetPlayer)game.getPlayers().get(pname);					  
						  //player.setX(x);
						  //player.setY(y);
						  //Update the game state
						  //game.update(pname, player);
						  //System.out.println("pname"+pname+"player.X"+player.getX()+"player.Y"+player.getY());
						  //Send to all the updated game state
						  
						  broadcast("PLAYER " + id + " " + x + " " + y + " " + character);
					  }
					  break;
			}				  
		}
	}
	
}
