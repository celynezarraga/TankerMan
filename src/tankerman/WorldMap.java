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
	ClientStarter client;


	
	
	public WorldMap(int worldmap ) {
//		this.initTrue = initTrue;
	}
	
	public WorldMap(ClientStarter client, String username, TextField chatFieldTf, TextField chatMsgsTf ) {
		this.client = client;
		this.chatFieldTf = chatFieldTf;
		this.chatMsgsTf = chatMsgsTf;
		System.out.println(client.getPlayerName());


		
//		WorldMap.initTrue = initTrue;
		
	}
	
	

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		//chat		
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
    }

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//map.draw(cameraX, cameraY);
		map.render(0,0);
		character.draw(charPositionX * 30, charPositionY * 30);
		g.drawString("CharacterX: " + charPositionX *30 + " CharY: " + charPositionY*30 , 400, 20);
		
//		chat
		g.drawString("CharacterX: " + posX + " CharY: " + posY , 400, 650);



		this.chatMsgsTf.deactivate();
		this.chatMsgsTf.render(gc, g);
		this.chatFieldTf.render(gc, g);
		System.out.println(chatMsgsTf.getText());

			

		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//chat
		////chat
//		, 748, 500,243,100);

//		this.posX = Mouse.getX();
//		this.posY = Mouse.getY();
//		if((posX>748 && posX<1000) && (posY>99 && posY<200)){
//			if(Mouse.isButtonDown(0)){
//					System.out.println("Yow");
//				}
//		}
		
		
		//
		Input input = gc.getInput();
		//chat
		if(input.isKeyDown(Input.KEY_ENTER)){
			
			this.chatFieldTf.setFocus(true);
		}
		
		
		
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
		
	
		
	}

	public int getID() {
		return 1;
	}
	
}
