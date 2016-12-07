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
						int team = Integer.parseInt(playerInfo[5]);
						WorldMap.players[id].setXpos(x);
						WorldMap.players[id].setYpos(y);
						WorldMap.players[id].team = team;

						
						if(character.equals("up")){
							if(id==0){
								if(team==1){
									walkUp1=walkUp2;
								}
								else if(team==2){
									walkUp1=walkUp3;
								}
								else if(team==3){
									walkUp1=walkUp4;
								}
								moveUp = new Animation(walkUp1,duration,false);
							}
							else if(id==1){
								if(team==0){
									walkUp2=walkUp1;
								}
								else if(team==2){
									walkUp2=walkUp3;
								}
								else if(team==3){
									walkUp2=walkUp4;
								}
								moveUp = new Animation(walkUp2,duration,false);
							}
							else if(id==2){
								if(team==0){
									walkUp3=walkUp1;
								}
								else if(team==1){
									walkUp3=walkUp2;
								}
								else if(team==3){
									walkUp3=walkUp4;
								}
								moveUp = new Animation(walkUp3,duration,false);
							}
							else if(id==3){
								if(team==0){
									walkUp4=walkUp1;
								}
								else if(team==1){
									walkUp4=walkUp2;
								}
								else if(team==2){
									walkUp4=walkUp3;
								}
								moveUp = new Animation(walkUp4,duration,false);
							}
							WorldMap.players[id].setChar(moveUp);
							WorldMap.characters[id] = WorldMap.players[id].getChar();
						}else if(character.equals("down")){
							if(id==0){
								if(team==1){
									walkDown1=walkDown2;
								}
								else if(team==2){
									walkDown1=walkDown3;
								}
								else if(team==3){
									walkDown1=walkDown4;
								}
								moveDown = new Animation(walkDown1,duration,false);
							}
							else if(id==1){
								if(team==0){
									walkDown2=walkDown1;
								}
								else if(team==2){
									walkDown2=walkDown3;
								}
								else if(team==3){
									walkDown2=walkDown4;
								}
								moveDown = new Animation(walkDown2,duration,false);
							}
							else if(id==2){
								if(team==0){
									walkDown3=walkDown1;
								}
								else if(team==1){
									walkDown3=walkDown2;
								}
								else if(team==3){
									walkDown3=walkDown4;
								}
								moveDown = new Animation(walkDown3,duration,false);
							}
							else if(id==3){
								if(team==0){
									walkDown4=walkDown1;
								}
								else if(team==1){
									walkDown4=walkDown2;
								}
								else if(team==2){
									walkDown4=walkDown3;
								}
								moveDown = new Animation(walkDown4,duration,false);
							}
							WorldMap.players[id].setChar(moveDown);
							WorldMap.characters[id] = WorldMap.players[id].getChar();
						}else if(character.equals("left")){
							if(id==0){
								if(team==1){
									walkLeft1=walkLeft2;
								}
								else if(team==2){
									walkLeft1=walkLeft3;
								}
								else if(team==3){
									walkLeft1=walkLeft4;
								}
								moveLeft = new Animation(walkLeft1,duration,false);
							}
							else if(id==1){
								if(team==0){
									walkLeft2=walkLeft1;
								}
								else if(team==2){
									walkLeft2=walkLeft3;
								}
								else if(team==3){
									walkLeft2=walkLeft4;
								}
								moveLeft = new Animation(walkLeft2,duration,false);
							}
							else if(id==2){
								if(team==0){
									walkLeft3=walkLeft1;
								}
								else if(team==1){
									walkLeft3=walkLeft2;
								}
								else if(team==3){
									walkLeft3=walkLeft4;
								}
								moveLeft = new Animation(walkLeft3,duration,false);
							}
							else if(id==3){
								if(team==0){
									walkLeft4=walkLeft1;
								}
								else if(team==1){
									walkLeft4=walkLeft2;
								}
								else if(team==2){
									walkLeft4=walkLeft3;
								}
								moveLeft = new Animation(walkLeft4,duration,false);
							}
							WorldMap.players[id].setChar(moveLeft);
							WorldMap.characters[id] = WorldMap.players[id].getChar();
						}else if(character.equals("right")){
							if(id==0){
								if(team==1){
									walkRight1=walkRight2;
								}
								else if(team==2){
									walkRight1=walkRight3;
								}
								else if(team==3){
									walkRight1=walkRight4;
								}
								moveRight = new Animation(walkRight1,duration,false);
							}
							else if(id==1){
								if(team==0){
									walkRight2=walkRight1;
								}
								else if(team==2){
									walkRight2=walkRight3;
								}
								else if(team==3){
									walkRight2=walkRight4;
								}
								moveRight = new Animation(walkRight2,duration,false);
							}
							else if(id==2){
								if(team==0){
									walkRight3=walkRight1;
								}
								else if(team==1){
									walkRight3=walkRight2;
								}
								else if(team==3){
									walkRight3=walkRight4;
								}
								moveRight = new Animation(walkRight3,duration,false);
							}
							else if(id==3){
								if(team==0){
									walkRight4=walkRight1;
								}
								else if(team==1){
									walkRight4=walkRight2;
								}
								else if(team==2){
									walkRight4=walkRight3;
								}
								moveRight = new Animation(walkRight4,duration,false);
							}
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
