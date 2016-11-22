package tankerman;

import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.*;
import org.newdawn.slick.SlickException;
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
	private TiledMap map;
	
	int[] duration = {200,200};
	//
	float posX = 0;
	float posY = 0;
	
	//Bullet Mechanics
	private LinkedList<Bullet> bullets;
	private static int FIRE_RATE = 250;
	private int delta = 0;
	private int current = 0;
	
	int charPositionX = 1;
	int charPositionY = 2;
	//float shiftX = charPositionX + 450;
	//float shiftY = charPositionY + 300;
	
	int cameraX = 0;
	int cameraY = 0;
	
	int tileId;

	}
	
	

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
		character = moveDown;

	}
	public void enter(GameContainer gc , StateBasedGame sbg)
            throws SlickException
    {
		chatMsgsTf = new TextField(gc, gc.getDefaultFont(), 748, 0,243,550);
		chatMsgsTf.setBorderColor(Color.white);
		chatFieldTf = new TextField(gc, gc.getDefaultFont(), 748, 500,243,100);
		chatFieldTf.setBorderColor(Color.red);
    }

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//map.draw(cameraX, cameraY);
		map.render(0,0);
		character.draw(charPositionX * 30, charPositionY * 30);
		g.drawString("CharacterX: " + charPositionX *30 + " CharY: " + charPositionY*30 , 400, 20);
		
		for(Bullet b : bullets){
			b.render(gc, g);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int t) throws SlickException {
chat
		g.drawString("CharacterX: " + posX + " CharY: " + posY , 400, 650);



		chatMsgsTf.deactivate();
		chatMsgsTf.render(gc, g);
		chatFieldTf.render(gc, g);
		chatFieldTf.setFocus(true);
//		System.out.println(chatMsgsTf.getText());

		Input input = gc.getInput();
		//chat
		String playerName = client.ClientStarter.playerName;
		if(input.isKeyDown(Input.KEY_ENTER)){
//			String text = WorldMap.chatFieldTf.getText();		
//			System.out.println(WorldMap.chatFieldTf.getText());

			

			ChatMessage msg = new ChatMessage(playerName, WorldMap.chatFieldTf.getText()+"\n");
			client.ClientStarter.client.getServerConnection().sendTcp(msg);
			WorldMap.chatFieldTf.setText("");
			
			
		}
		
		
		
		int objectLayer = map.getLayerIndex("Objects");
		map.getTileId(0,0,objectLayer);
		Iterator<Bullet> g = bullets.iterator();
		
		while(g.hasNext()){
			Bullet b = g.next();
			if(b.isActive() || (int)b.getPos().getX() < 700 || (int)b.getPos().getY() < 600){
				b.update(t);
			}else{
				g.remove();
			}
			
			if((int)b.getPos().getX() >= 720 || (int)b.getPos().getY() >= 570 || (int)b.getPos().getX() <= 35 || (int)b.getPos().getY() <= 35 ){
				g.remove();
			}
			
		}
		
		if(gc.getInput().isKeyPressed(Input.KEY_SPACE)){
			if(character == moveDown){
				bullets.add(new Bullet(new Vector2f((charPositionX*30) + 23,(charPositionY*30) + 30), new Vector2f(0,(charPositionY*30) + 60)));
			}else if(character == moveUp){
				bullets.add(new Bullet(new Vector2f((charPositionX*30) + 23,(charPositionY*30)), new Vector2f(0,-((charPositionY*30) - 60))));
			}else if(character == moveLeft){
				bullets.add(new Bullet(new Vector2f((charPositionX*30),(charPositionY*30) + 16), new Vector2f(-((charPositionX*30) - 60),0)));
			}else if(character == moveRight){
				bullets.add(new Bullet(new Vector2f((charPositionX*30)+30,(charPositionY*30) + 16), new Vector2f((charPositionX*30)+60,0)));
			}
			
		}
		
		
		if(input.isKeyPressed(Input.KEY_UP)){
			character = moveUp;
			charPositionY --;
				if(map.getTileId(charPositionX,charPositionY , objectLayer) != 0){
					charPositionY ++;
				}
		}
		
		if(input.isKeyPressed(Input.KEY_DOWN)){
			character = moveDown;
			charPositionY ++;
			if(map.getTileId(charPositionX,charPositionY , objectLayer) != 0){
				charPositionY --;
			}
		}
		
		if(input.isKeyPressed(Input.KEY_LEFT)){
			character = moveLeft;
			charPositionX --;
			if(map.getTileId(charPositionX,charPositionY , objectLayer) != 0){
				charPositionX ++;
			}
		}
		
		if(input.isKeyPressed(Input.KEY_RIGHT)){
			character = moveRight;
			charPositionX ++;
			if(map.getTileId(charPositionX,charPositionY , objectLayer) != 0){
				charPositionX --;
			}
		}
		
	
		
	}

	public int getID() {
		return 1;
	}
	
}
