package tankerman;

import java.io.IOException;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import networking.ChatClient;
import networking.ChatClientStarter;
import networking.Constants;
import networking.GameClient;
import networking.GameServer;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

import com.jmr.wrapper.common.Connection;

import packets.ChatMessage;
import server.ConnectionManager;


public class WorldMap extends BasicGameState{

	Animation character, moveUp, moveDown, moveLeft, moveRight;
	public static Animation characters[] = new Animation[4];
	
	private TiledMap map;
	private InetAddress test;
	public static Tank[] players = new Tank[4];
	
	int[] duration = {200,200};
	//
	float posX = 0;
	float posY = 0;
	
	//Bullet Mechanics
	private LinkedList<Bullet> bullets;
	private static int FIRE_RATE = 250;
	private int delta = 0;
	private int current = 0;
	
	int charPositionX=1;
	int charPositionY=1;
	//float shiftX = charPositionX + 450;
	//float shiftY = charPositionY + 300;
	
	int cameraX = 0;
	int cameraY = 0;
	
	int tileId;

	public WorldMap(int worldmap) {}
	
	boolean[][] blocked;
	
	/// players
	public static TextField chatMsgsTf;
	public static TextField chatFieldTf;
	public static Boolean enterUp = false;

	int playerID;
	boolean chatEnabled = false;
	ArrayList<Image[]> tank1 = new ArrayList<Image[]>();
	ArrayList<Image[]> tank2 = new ArrayList<Image[]>();
	ArrayList<Image[]> tank3 = new ArrayList<Image[]>();
	ArrayList<Image[]> tank4 = new ArrayList<Image[]>();
	

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		//chat		
		//map = new Image("res/sample.jpg");
		System.out.println("playerID: "+ playerID);
		map = new TiledMap("res/1.tmx");
		Image[] walkUp = {new Image("res/charBack2.png"),new Image("res/charBack2.png")};
		Image[] walkDown = {new Image("res/charFront2.png"),new Image("res/charFront2.png")};
		Image[] walkLeft = {new Image("res/charLeft2.png"),new Image("res/charLeft2.png")};
		Image[] walkRight = {new Image("res/charRight2.png"),new Image("res/charRight2.png")};
		
		moveUp = new Animation(walkUp,duration,false);
		moveDown = new Animation(walkDown,duration,false);
		moveLeft = new Animation(walkLeft,duration,false);
		moveRight = new Animation(walkRight,duration,false);
		
		bullets = new LinkedList<Bullet>();


//		if (client.connected()){
			
	
//		}
		
	}
	public void enter(GameContainer gc , StateBasedGame sbg) throws SlickException{
		 playerID = networking.GameClient.getPlayerID();
		
		
//		characters[0] = moveDown;
		
		
		GameServer.gameStage=Constants.GAME_START;
		chatMsgsTf = new TextField(gc, gc.getDefaultFont(), 748, 0,243,550);
		chatMsgsTf.setBorderColor(Color.white);
		chatFieldTf = new TextField(gc, gc.getDefaultFont(), 748, 500,243,100);
		chatFieldTf.setCursorVisible(false);
		chatFieldTf.setBorderColor(Color.red);
		
		Image[] walkUp1 = {new Image("res/charFront0.png"),new Image("res/charFront0.png")};
		Image[] walkDown1 = {new Image("res/charBack0.png"),new Image("res/charBack0.png")};
		Image[] walkLeft1 = {new Image("res/charLeft0.png"),new Image("res/charLeft0.png")};
		Image[] walkRight1 = {new Image("res/charRight0.png"),new Image("res/charRight0.png")};
		
		Image[] walkUp2 = {new Image("res/charFront1.png"),new Image("res/charFront1.png")};
		Image[] walkDown2 = {new Image("res/charBack1.png"),new Image("res/charBack1.png")};
		Image[] walkLeft2 = {new Image("res/charLeft1.png"),new Image("res/charLeft1.png")};
		Image[] walkRight2 = {new Image("res/charRight1.png"),new Image("res/charRight1.png")};
		
		Image[] walkUp3 = {new Image("res/charFront2.png"),new Image("res/charFront2.png")};
		Image[] walkDown3 = {new Image("res/charBack2.png"),new Image("res/charBack2.png")};
		Image[] walkLeft3 = {new Image("res/charLeft2.png"),new Image("res/charLeft2.png")};
		Image[] walkRight3 = {new Image("res/charRight2.png"),new Image("res/charRight2.png")};
		
		Image[] walkUp4 = {new Image("res/charFront3.png"),new Image("res/charFront3.png")};
		Image[] walkDown4 = {new Image("res/charBack3.png"),new Image("res/charBack3.png")};
		Image[] walkLeft4 = {new Image("res/charLeft3.png"),new Image("res/charLeft3.png")};
		Image[] walkRight4 = {new Image("res/charRight3.png"),new Image("res/charRight3.png")};
		
		tank1.add(walkUp1);
		tank1.add(walkDown1);
		tank1.add(walkLeft1);
		tank1.add(walkRight1);

		tank2.add(walkUp2);
		tank2.add(walkDown2);
		tank2.add(walkLeft2);
		tank2.add(walkRight2);

		tank3.add(walkUp3);
		tank3.add(walkDown3);
		tank3.add(walkLeft3);
		tank3.add(walkRight3);

		tank4.add(walkUp4);
		tank4.add(walkDown4);
		tank4.add(walkLeft4);
		tank4.add(walkRight4);

		players[0] = new Tank("p1",moveDown,tank1,0);
		players[0].setXpos(1);
		players[0].setYpos(1);
		
		players[1] = new Tank("p2",moveUp,tank2,1);
		players[1].setXpos(1);
		players[1].setYpos(18);
		
		players[2] = new Tank("p3",moveUp,tank3,2);
		players[2].setXpos(23);
		players[2].setYpos(18);
		
		players[3] = new Tank("p4",moveDown,tank4,3);
		players[3].setXpos(23);
		players[3].setYpos(1);
		
		for(int i=0;i<4;i++){
			characters[i] = players[i].getChar();
		}
		
    }
	


	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		map.render(0,0);
		for(int i=0;i<2;i++){
			//update animations/pics
			ArrayList<Image[]> currentTank = new ArrayList<Image[]>();
			currentTank = players[i].getAnimations();
			Image[] walkUp = currentTank.get(0);
			Image[] walkDown = currentTank.get(1);
			Image[] walkLeft = currentTank.get(2);
			Image[] walkRight = currentTank.get(3);
			
			moveUp = new Animation(walkUp,duration,false);
			moveDown = new Animation(walkDown,duration,false);
			moveLeft = new Animation(walkLeft,duration,false);
			moveRight = new Animation(walkRight,duration,false);
			
			characters[i].draw(players[i].getXpos() * 30,players[i].getYpos() * 30);
		}
		//characters[0].draw(players[0].getXpos() * 30, players[0].getYpos() * 30);
		g.drawString("CharacterX: " + players[playerID].getXpos() * 30 + " CharY: " + players[playerID].getYpos()*30 , 400, 20);
		
		
		
		for(Bullet b : bullets){
			b.render(gc, g);
		}
		
		String time = GameClient.getTime();
		if(time!=null){
			g.drawString(time, 400, 650);
		}

		chatMsgsTf.deactivate();
		chatMsgsTf.render(gc, g);
		chatFieldTf.render(gc, g);
		chatFieldTf.setFocus(true);
		
		if(GameClient.getEndGame()){
			//scoreboard here
//			System.out.println("TIMES UP!");
			sbg.enterState(5);
		}

	}
	public void update(GameContainer gc, StateBasedGame sbg, int t) throws SlickException {
//		GameServer.gameStage=Constants.IN_PROGRESS;
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_TAB)){
			chatEnabled = true;
			chatFieldTf.setFocus(true);
		}
		
		//chat
		if(input.isKeyDown(Input.KEY_ENTER) && chatEnabled==true){
			String message = chatFieldTf.getText();
			
			if(message != "" && !message.isEmpty()) {
				chatFieldTf.setText("");
				networking.ChatClientStarter.send(message);
			}	
			chatEnabled = false;
		}

		int objectLayer = map.getLayerIndex("Objects");
		map.getTileId(0,0,objectLayer);
		Iterator<Bullet> bulletIter = bullets.iterator();
		
		while(bulletIter.hasNext()){
			Bullet b = bulletIter.next();
			if(b.isActive() || (int)b.getPos().getX() < 700 || (int)b.getPos().getY() < 600){
				b.update(t);
			}else{
				bulletIter.remove();
			}
			
			
			if((int)b.getPos().getX() >= 720 || (int)b.getPos().getY() >= 570 || (int)b.getPos().getX() <= 35 || (int)b.getPos().getY() <= 35 ){
				bulletIter.remove();
			}
			
		}
		
		if(gc.getInput().isKeyPressed(Input.KEY_SPACE) && chatEnabled==false){
			chatFieldTf.setText("");
			try{
				if(characters[playerID] == moveDown){
					bullets.add(new Bullet(new Vector2f((players[playerID].getXpos()*30) + 23,(players[playerID].getYpos()*30) + 30), new Vector2f(0,(players[0].getYpos()*30) + 60),map));
				}else if(characters[playerID] == moveUp){
					bullets.add(new Bullet(new Vector2f((players[playerID].getXpos()*30) + 23,(players[playerID].getYpos()*30)), new Vector2f(0,-((players[0].getYpos()*30) - 60)),map));
				}else if(characters[playerID] == moveLeft){
					bullets.add(new Bullet(new Vector2f((players[playerID].getXpos()*30),(players[playerID].getYpos()*30) + 16), new Vector2f(-((players[0].getXpos()*30) - 60),0),map));
				}else if(characters[playerID] == moveRight){
					bullets.add(new Bullet(new Vector2f((players[playerID].getXpos()*30)+30,(players[playerID].getYpos()*30) + 16), new Vector2f((players[0].getXpos()*30)+60,0),map));
				}
			}catch(Exception e){}
		}
		
		
		if(input.isKeyPressed(Input.KEY_UP) && chatEnabled==false){
//			send
			
//			keyup(objectLayer);
			players[playerID].setChar(moveUp);
			characters[playerID] = players[playerID].getChar();
			players[playerID].setYpos(players[playerID].getYpos()-1);
				if(map.getTileId(players[playerID].getXpos(),players[playerID].getYpos() , objectLayer) != 0){
					players[playerID].setYpos(players[playerID].getYpos()+1);
				}
				////The format: PLAYER <player name> <x> <y>
				GameClient.send("PLAYER "+ playerID +" "+players[playerID].getXpos()+" "+players[playerID].getYpos() + " up");
		}
		
		if(input.isKeyPressed(Input.KEY_DOWN) && chatEnabled==false){
//			keydown(objectLayer);
			players[playerID].setChar(moveDown);
			characters[playerID] = players[playerID].getChar();
			players[playerID].setYpos(players[playerID].getYpos()+1);
			if(map.getTileId(players[playerID].getXpos(),players[playerID].getYpos() , objectLayer) != 0){
				players[playerID].setYpos(players[playerID].getYpos()-1);
			}
			
			
			GameClient.send("PLAYER "+ playerID +" "+players[playerID].getXpos()+" "+players[playerID].getYpos() + " down");
		}
		
		if(input.isKeyPressed(Input.KEY_LEFT) && chatEnabled==false){
//			keyleft(objectLayer);
			players[playerID].setChar(moveLeft);
			characters[playerID] = players[playerID].getChar();
			players[playerID].setXpos(players[playerID].getXpos()-1);
			if(map.getTileId(players[playerID].getXpos(),players[playerID].getYpos() , objectLayer) != 0){
				players[playerID].setXpos(players[playerID].getXpos()+1);
			}
			GameClient.send("PLAYER "+ playerID +" "+players[playerID].getXpos()+" "+players[playerID].getYpos() + " left");
		}
		
		if(input.isKeyPressed(Input.KEY_RIGHT) && chatEnabled==false){
			
			players[playerID].setChar(moveRight);
			characters[playerID]= players[playerID].getChar();
			players[playerID].setXpos(players[playerID].getXpos()+1);
			if(map.getTileId(players[playerID].getXpos(),players[playerID].getYpos() , objectLayer) != 0){
				players[playerID].setXpos(players[playerID].getXpos()-1);
			}
			GameClient.send("PLAYER "+ playerID +" "+players[playerID].getXpos()+" "+players[playerID].getYpos()+ " right");
		}	
		
	}
	
	// public void keyup(int objectLayer){
	// 	playerID = networking.GameClient.getPlayerID();
	// 	players[playerID].setChar(moveUp);
	// 	characters[playerID] = players[playerID].getChar();
	// 	players[playerID].setYpos(players[playerID].getYpos()-1);
	// 		if(map.getTileId(players[playerID].getXpos(),players[playerID].getYpos() , objectLayer) != 0){
	// 			players[playerID].setYpos(players[playerID].getYpos()+1);
	// 		}
	// }
	// public void keydown(int objectLayer){
	// 	playerID = networking.GameClient.getPlayerID();
	// 	players[playerID].setChar(moveDown);
	// 	characters[playerID] = players[playerID].getChar();
	// 	players[playerID].setYpos(players[playerID].getYpos()+1);
	// 	if(map.getTileId(players[playerID].getXpos(),players[playerID].getYpos() , objectLayer) != 0){
	// 		players[0].setYpos(players[0].getYpos()-1);
	// 	}
	// }
	// public void keyleft(int objectLayer){
	// 	playerID = networking.GameClient.getPlayerID();
	// 	players[playerID].setChar(moveLeft);
	// 	characters[playerID] = players[playerID].getChar();
	// 	players[playerID].setXpos(players[playerID].getXpos()-1);
	// 	if(map.getTileId(players[playerID].getXpos(),players[playerID].getYpos() , objectLayer) != 0){
	// 		players[playerID].setXpos(players[playerID].getXpos()+1);
	// 	}
	// }
	

	public int getID() {
		return 1;
	}
	
	/**
	 * Helper method for sending data to server
	 * @param msg
	 */
	public void send(String msg){
		try{
			byte[] buf = msg.getBytes();
        	InetAddress address = InetAddress.getByName(GameClient.server);
        	DatagramPacket packet = new DatagramPacket(buf, buf.length, address, Constants.PORT);
        	GameServer.serverSocket.send(packet);
        }catch(Exception e){}
		
	}
	
}
