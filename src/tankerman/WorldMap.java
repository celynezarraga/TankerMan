package tankerman;

import org.newdawn.slick.*;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

public class WorldMap extends BasicGameState{

	Animation character, moveUp, moveDown, moveLeft, moveRight;
	private TiledMap map;
	
	int[] duration = {200,200};
	
	int charPositionX = 1;
	int charPositionY = 2;
	//float shiftX = charPositionX + 450;
	//float shiftY = charPositionY + 300;
	
	int cameraX = 0;
	int cameraY = 0;
	
	int tileId;
	
	boolean[][] blocked;
	
	public WorldMap(int worldmap) {
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
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//map.draw(cameraX, cameraY);
		map.render(0,0);
		character.draw(charPositionX * 30, charPositionY * 30);
		g.drawString("CharacterX: " + charPositionX *30 + " CharY: " + charPositionY*30 , 400, 20);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
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
	}

	public int getID() {
		return 1;
	}

}
