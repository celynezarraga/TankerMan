package tankerman;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

import client.ClientStarter;

import com.jmr.wrapper.client.Client;

public class WorldMap extends BasicGameState{

	Animation character, moveUp, moveDown, moveLeft, moveRight;
	private TiledMap map;
	
	int[] duration = {200,200};
	//
	float posX = 0;
	float posY = 0;
	
	int charPositionX = 1;
	int charPositionY = 2;
	//float shiftX = charPositionX + 450;
	//float shiftY = charPositionY + 300;
	
	int cameraX = 0;
	int cameraY = 0;
	
	int tileId;
	
	boolean[][] blocked;
	
	/// players
	TextField chatMsgsTf;
	TextField chatFieldTf;

	
	
	public WorldMap(int worldmap ) {
	}
	
	public WorldMap(ClientStarter client, String username ) {
		System.out.println(client.getPlayerName());
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//map = new Image("res/sample.jpg");
		map = new TiledMap("res/1.tmx");
		Image[] walkUp = {new Image("res/charBack.png"),new Image("res/charBack.png")};
		Image[] walkDown = {new Image("res/charFront.png"),new Image("res/charFront.png")};
		Image[] walkLeft = {new Image("res/charLeft.png"),new Image("res/charLeft.png")};
		Image[] walkRight = {new Image("res/charRight.png"),new Image("res/charRight.png")};
		
		moveUp = new Animation(walkUp,duration,false);
		moveDown = new Animation(walkDown,duration,false);
		moveLeft = new Animation(walkLeft,duration,false);
		moveRight = new Animation(walkRight,duration,false);
		character = moveDown;
		

		
		//chat		
		chatMsgsTf = new TextField(gc, gc.getDefaultFont(), 748, 0,243,550);
		chatMsgsTf.setBorderColor(Color.white);
		chatFieldTf = new TextField(gc, gc.getDefaultFont(), 748, 500,243,100);
		chatFieldTf.setBorderColor(Color.red);
		chatFieldTf.setCursorVisible(true);
		
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//map.draw(cameraX, cameraY);
		map.render(0,0);
		character.draw(charPositionX * 30, charPositionY * 30);
		g.drawString("CharacterX: " + charPositionX *30 + " CharY: " + charPositionY*30 , 400, 20);
		
//		chat
		g.drawString("CharacterX: " + posX + " CharY: " + posY , 400, 650);

		chatMsgsTf.deactivate();
		chatMsgsTf.render(gc, g);
		chatFieldTf.render(gc, g);
		chatFieldTf.setCursorVisible(true);
		chatFieldTf.setFocus(true);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//chat
		this.posX = Mouse.getX();
		this.posY = Mouse.getY();
		//
		Input input = gc.getInput();
		int objectLayer = map.getLayerIndex("Objects");
		map.getTileId(0,0,objectLayer);
		if(input.isKeyDown(Input.KEY_UP)){
			character = moveUp;
			charPositionY --;
				if(map.getTileId(charPositionX,charPositionY , objectLayer) != 0){
					charPositionY ++;
				}
		}
		
		if(input.isKeyDown(Input.KEY_DOWN)){
			character = moveDown;
			charPositionY ++;
			if(map.getTileId(charPositionX,charPositionY , objectLayer) != 0){
				charPositionY --;
			}
		}
		
		if(input.isKeyDown(Input.KEY_LEFT)){
			character = moveLeft;
			charPositionX --;
			if(map.getTileId(charPositionX,charPositionY , objectLayer) != 0){
				charPositionX ++;
			}
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT)){
			character = moveRight;
			charPositionX ++;
			if(map.getTileId(charPositionX,charPositionY , objectLayer) != 0){
				charPositionX --;
			}
		}
		
		////chat
//		, 748, 500,243,100);

		if((posX>748 && posX<1000) && (posY>99 && posY<200)){
			if(Mouse.isButtonDown(0)){
					System.out.println("Yow");
				}
		}
	}

	public int getID() {
		return 1;
	}

}
