package tankerman;

import org.newdawn.slick.*;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.*;

public class WorldMap extends BasicGameState{

	Animation character, moveUp, moveDown, moveLeft, moveRight;
	Image map;
	
	int[] duration = {200,200};
	
	float charPositionX = 0;
	float charPositionY = 0;
	float shiftX = charPositionX + 450;
	float shiftY = charPositionY + 300;
	
	public WorldMap(int worldmap) {
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		map = new Image("res/sample.jpg");
		
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
		map.draw(charPositionX, charPositionY);
		character.draw(shiftX, shiftY);
		
		g.drawString("CharacterX: " + charPositionX + " CharY: " + charPositionY , 400, 20);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_UP)){
			character = moveUp;
			charPositionY += delta * 1f;
				if(charPositionY > 1208){
					charPositionY -= delta * 1f;
				}
		}
		
		if(input.isKeyDown(Input.KEY_DOWN)){
			character = moveDown;
			charPositionY -= delta * 1f;
				if(charPositionY < -1208){
					charPositionY += delta * 1f;
				}
		}
		
		if(input.isKeyDown(Input.KEY_LEFT)){
			character = moveLeft;
			charPositionX += delta * 1f;
				if(charPositionX > 1208){
					charPositionX -= delta * 1f;
				}
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT)){
			character = moveRight;
			charPositionX -= delta * 1f;
				if(charPositionX < -1208){
					charPositionX += delta * 1f;
				}
		}
	}

	public int getID() {
		return 1;
	}

}
