package tankerman;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{

	public static final String gameName = "TankerMan";
	public static final int startMenu = 0;
	public static final int worldMap = 1;
	public static final int veryfirstMenu = 2;
	public static final int serverState = 3;
	public static final int clientState = 4;

	

	
	public Game(String gameName) {
		super(gameName);
	}
		
	
	public void initStatesList(GameContainer gc) throws SlickException{
		gc.setShowFPS(false);
		this.addState(new FirstMenu(veryfirstMenu));
		

		this.addState(new ServerState(serverState));
		this.addState(new ClientState(clientState));
		this.addState(new WorldMap(worldMap));
		this.addState(new MainMenu(startMenu));
	
//		this.getState(veryfirstMenu).init(gc, this);
//		this.getState(clientState).init(gc, this);

//		this.getState(startMenu).init(gc, this);

//		this.getState(worldMap).init(gc, this);


//		this.enterState(veryfirstMenu);
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
