package tankerman;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{

	public static final String gameName = "TankerMan";
	public static final int startMenu = 0;
	public static final int worldMap = 1;
	
	public Game(String gameName) {
		super(gameName);
		this.addState(new MainMenu(startMenu));
		this.addState(new WorldMap(worldMap));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		gc.setShowFPS(false);
		this.getState(startMenu).init(gc, this);
		this.getState(worldMap).init(gc, this);
		this.enterState(startMenu);
	}

	public static void main(String[] args) {
		AppGameContainer agc;
		try{
			agc = new AppGameContainer(new Game(gameName));
			agc.setDisplayMode(1000, 700, false);
			agc.start();
		}
		catch(SlickException e){
			e.printStackTrace();   
		}
	}

}
