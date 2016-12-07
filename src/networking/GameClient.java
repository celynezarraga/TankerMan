package networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import tankerman.WorldMap;

public class GameClient implements Runnable, Constants{

	int x=10,y=10,xspeed=2,yspeed=2,prevX,prevY;
	Thread t=new Thread(this);
	String name="Joseph";
	String pname;
	public static String server;
	static boolean connected=false;
    public static DatagramSocket socket;
	String serverData;
	int startGame = 0;
	private String port;
	static int playerID;
	static boolean endGame = false;
	static String timeRemaining;
	
	private int[] duration = {200,200};
	
	private static Image[] walkUp;
	private static Image[] walkDown;
	private static Image[] walkLeft;
	private static Image[] walkRight;

	private Image[] walkUp1 = {new Image("res/charFront0.png"),new Image("res/charFront0.png")};
	private Image[] walkDown1 = {new Image("res/charBack0.png"),new Image("res/charBack0.png")};
	private Image[] walkLeft1 = {new Image("res/charLeft0.png"),new Image("res/charLeft0.png")};
	private Image[] walkRight1 = {new Image("res/charRight0.png"),new Image("res/charRight0.png")};
	
	private Image[] walkUp2 = {new Image("res/charFront1.png"),new Image("res/charFront1.png")};
	private Image[] walkDown2 = {new Image("res/charBack1.png"),new Image("res/charBack1.png")};
	private Image[] walkLeft2 = {new Image("res/charLeft1.png"),new Image("res/charLeft1.png")};
	private Image[] walkRight2 = {new Image("res/charRight1.png"),new Image("res/charRight1.png")};
	
	private Image[] walkUp3 = {new Image("res/charFront2.png"),new Image("res/charFront2.png")};
	private Image[] walkDown3 = {new Image("res/charBack2.png"),new Image("res/charBack2.png")};
	private Image[] walkLeft3 = {new Image("res/charLeft2.png"),new Image("res/charLeft2.png")};
	private Image[] walkRight3 = {new Image("res/charRight2.png"),new Image("res/charRight2.png")};
	
	private Image[] walkUp4 = {new Image("res/charFront3.png"),new Image("res/charFront3.png")};
	private Image[] walkDown4 = {new Image("res/charBack3.png"),new Image("res/charBack3.png")};
	private Image[] walkLeft4 = {new Image("res/charLeft3.png"),new Image("res/charLeft3.png")};
	private Image[] walkRight4 = {new Image("res/charRight3.png"),new Image("res/charRight3.png")};
	
	private Animation moveUp;
	private Animation moveDown;
	private Animation moveLeft;
	private Animation moveRight;
	
	private static ArrayList<Image[]> images = new ArrayList<Image[]>();
	
	public GameClient(String serverIp, String port, String name) throws Exception{
		this.server=serverIp;
		this.name=name;
		this.port=port;
		socket = new DatagramSocket();
		socket.setSoTimeout(100);	
		t.start();		
	}
	
	public static void send(String msg){
		try{
			byte[] buf = msg.getBytes();
        	InetAddress address = InetAddress.getByName(server);
        	DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
        	socket.send(packet);
        }catch(Exception e){}
		
	}

	public void run(){
		while(true){
			try{
				Thread.sleep(1);
			}catch(Exception ioe){}
						
			//Get the data from players
			byte[] buf = new byte[256];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			try{
     			socket.receive(packet);
			}catch(Exception ioe){/*lazy exception handling :)*/}
			
			serverData=new String(buf);
			serverData=serverData.trim();
			
			if (!connected && serverData.startsWith("CONNECTED")){
				connected=true;
				String[] tokens = serverData.split("-");
				playerID = Integer.parseInt(tokens[1]);
				System.out.println("ID : " + playerID);
				
				if(playerID == 0){
					walkUp = walkUp1;
					walkDown = walkDown1;
					walkRight = walkRight1;
					walkLeft = walkLeft1;
				}
				else if(playerID == 1){
					walkUp = walkUp2;
					walkDown = walkDown2;
					walkRight = walkRight2;
					walkLeft = walkLeft2;
				}
				else if(playerID == 2){
					walkUp = walkUp3;
					walkDown = walkDown3;
					walkRight = walkRight3;
					walkLeft = walkLeft3;
				}
				else if(playerID == 3){
					walkUp = walkUp4;
					walkDown = walkDown4;
					walkRight = walkRight4;
					walkLeft = walkLeft4;
				}
				
				moveUp = new Animation(walkUp,duration,false);
				moveDown = new Animation(walkDown,duration,false);
				moveLeft = new Animation(walkLeft,duration,false);
				moveRight = new Animation(walkRight,duration,false);
				
			}else if (!connected){
				System.out.println("Connecting..");				
				send("CONNECT "+name);
			}else if (connected){
				if(startGame==0 && serverData.startsWith("START")){
					startGame=1;
					System.out.println(startGame);
				}
				if(serverData.startsWith("END")){
					endGame = true;
				}
				if(serverData.startsWith("TIME")){
					String[] timeInfo = serverData.split("-");
					timeRemaining = timeInfo[1];
					}
				if (serverData.startsWith("PLAYER")){
					String[] playersInfo = serverData.split(":");
					for (int i=0;i<playersInfo.length;i++){
						String[] playerInfo = playersInfo[i].split(" ");
						int id =Integer.parseInt(playerInfo[1]);
						int x = Integer.parseInt(playerInfo[2]);
						int y = Integer.parseInt(playerInfo[3]);
						String character = playerInfo[4];
						WorldMap.players[id].setXpos(x);
						WorldMap.players[id].setYpos(y);
						
						if(character.equals("up")){
							WorldMap.players[id].setChar(moveUp);
							WorldMap.characters[id] = WorldMap.players[id].getChar();
						}else if(character.equals("down")){
							WorldMap.players[id].setChar(moveDown);
							WorldMap.characters[id] = WorldMap.players[id].getChar();
						}else if(character.equals("left")){
							WorldMap.players[id].setChar(moveLeft);
							WorldMap.characters[id] = WorldMap.players[id].getChar();
						}else if(character.equals("right")){
							WorldMap.players[id].setChar(moveRight);
							WorldMap.characters[id] = WorldMap.players[id].getChar();
						}
					
				}
				
			}			
		}
	}
	}
	
	public int getStartGame(){
		return startGame;
	}
	
	public static int getPlayerID(){
		return playerID;
	}
	
	public static boolean getEndGame(){
		return endGame;
	}
	
	public static String getTime(){
		return timeRemaining;
	}
	
	public static boolean getConnectionStatus(){
		return connected;
	}
	public static ArrayList<Image[]> getAnimations(){
		images.add(walkUp);
		images.add(walkDown);
		images.add(walkLeft);
		images.add(walkRight);
		return images;	
	}
	
}
