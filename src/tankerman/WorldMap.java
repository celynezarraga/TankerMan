package tankerman;

import java.net.InetAddress;
import java.net.DatagramPacket;
import java.util.Iterator;
import java.util.LinkedList;

import networking.ChatClientStarter;

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
	Animation characters[] = new Animation[4];
	
	private TiledMap map;
	private InetAddress test;
	private Tank[] players = new Tank[4];
	
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
	
	
	

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		//chat		
		//map = new Image("res/sample.jpg");
		map = new TiledMap("res/1.tmx");
		Image[] walkUp = {new Image("res/charBack2.png"),new Image("res/charBack2.png")};
		Image[] walkDown = {new Image("res/charFront2.png"),new Image("res/charFront2.png")};
		Image[] walkLeft = {new Image("res/charLeft2.png"),new Image("res/charLeft2.png")};
		Image[] walkRight = {new Image("res/charRight2.png"),new Image("res/charRight2.png")};
		
		moveUp = new Animation(walkUp,duration,false);
		moveDown = new Animation(walkDown,duration,false);
		moveLeft = new Animation(walkLeft,duration,false);
		moveRight = new Animation(walkRight,duration,false);
		characters[0] = moveDown;
		
		bullets = new LinkedList<Bullet>();


//		if (client.connected()){
			
	
//		}
		
	}
	public void enter(GameContainer gc , StateBasedGame sbg)
            throws SlickException
    {
		chatMsgsTf = new TextField(gc, gc.getDefaultFont(), 748, 0,243,550);
		chatMsgsTf.setBorderColor(Color.white);
		chatFieldTf = new TextField(gc, gc.getDefaultFont(), 748, 500,243,100);
		chatFieldTf.setBorderColor(Color.red);
		
		playerID = networking.GameClient.getPlayerID();
		
		if(playerID == 0){
			charPositionX = 1;
			charPositionY = 1;
			characters[0] = moveDown;
		}
		else if(playerID == 1){
			charPositionX= 1;
			charPositionY = 18;
			characters[0] = moveUp;
		}
		else if(playerID == 2){
			charPositionX= 23;
			charPositionY = 18;
			characters[0] = moveDown;
		}
		else if(playerID == 3){
			charPositionX= 23;
			charPositionY = 18;
			characters[0] = moveUp;
		}
		
		players[0] = new Tank(test,"Damn",4444,character);
		players[0].setXpos(charPositionX);
		players[0].setYpos(charPositionY);
							    
    }
	


	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		map.render(0,0);
		characters[0].draw(players[0].getXpos() * 30, players[0].getYpos() * 30);
		g.drawString("CharacterX: " + players[0].getXpos() * 30 + " CharY: " + players[0].getYpos()*30 , 400, 20);
		
		for(Bullet b : bullets){
			b.render(gc, g);
		}
		
		g.drawString("CharacterX: " + posX + " CharY: " + posY , 400, 650);

		chatMsgsTf.deactivate();
		chatMsgsTf.render(gc, g);
		chatFieldTf.render(gc, g);
		chatFieldTf.setFocus(true);
	}
	public void update(GameContainer gc, StateBasedGame sbg, int t) throws SlickException {
		

		Input input = gc.getInput();
		//chat
		if(input.isKeyDown(Input.KEY_ENTER)){
			String message = chatFieldTf.getText();
			
			if(message != "" && !message.isEmpty()) {
				chatFieldTf.setText("");
				networking.ChatClientStarter.send(message);
			}	
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
		
		if(gc.getInput().isKeyPressed(Input.KEY_SPACE)){
			try{
				if(characters[0] == moveDown){
					bullets.add(new Bullet(new Vector2f((players[0].getXpos()*30) + 23,(players[0].getYpos()*30) + 30), new Vector2f(0,(players[0].getYpos()*30) + 60),map));
				}else if(characters[0] == moveUp){
					bullets.add(new Bullet(new Vector2f((players[0].getXpos()*30) + 23,(players[0].getYpos()*30)), new Vector2f(0,-((players[0].getYpos()*30) - 60)),map));
				}else if(characters[0] == moveLeft){
					bullets.add(new Bullet(new Vector2f((players[0].getXpos()*30),(players[0].getYpos()*30) + 16), new Vector2f(-((players[0].getXpos()*30) - 60),0),map));
				}else if(characters[0] == moveRight){
					bullets.add(new Bullet(new Vector2f((players[0].getXpos()*30)+30,(players[0].getYpos()*30) + 16), new Vector2f((players[0].getXpos()*30)+60,0),map));
				}
			}catch(Exception e){}
		}
		
		
		if(input.isKeyPressed(Input.KEY_UP)){
			players[0].setChar(moveUp);
			characters[0] = players[0].getChar();
			players[0].setYpos(players[0].getYpos()-1);
				if(map.getTileId(players[0].getXpos(),players[0].getYpos() , objectLayer) != 0){
					players[0].setYpos(players[0].getYpos()+1);
				}
		}
		
		if(input.isKeyPressed(Input.KEY_DOWN)){
			players[0].setChar(moveDown);
			characters[0] = players[0].getChar();
			players[0].setYpos(players[0].getYpos()+1);
			if(map.getTileId(players[0].getXpos(),players[0].getYpos() , objectLayer) != 0){
				players[0].setYpos(players[0].getYpos()-1);
			}
		}
		
		if(input.isKeyPressed(Input.KEY_LEFT)){
			players[0].setChar(moveLeft);
			characters[0] = players[0].getChar();
			players[0].setXpos(players[0].getXpos()-1);
			if(map.getTileId(players[0].getXpos(),players[0].getYpos() , objectLayer) != 0){
				players[0].setXpos(players[0].getXpos()+1);
			}
		}
		
		if(input.isKeyPressed(Input.KEY_RIGHT)){
			players[0].setChar(moveRight);
			characters[0]= players[0].getChar();
			players[0].setXpos(players[0].getXpos()+1);
			if(map.getTileId(players[0].getXpos(),players[0].getYpos() , objectLayer) != 0){
				players[0].setXpos(players[0].getXpos()-1);
			}
		}	
	}

	public int getID() {
		return 1;
	}
}
