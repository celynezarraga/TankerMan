package tankerman;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MainMenu extends BasicGameState{

	public MainMenu(int startmenu) {
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Play", 500, 400);
		g.drawString("Exit", 500, 500);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		System.out.println("X: " + posX + " Y: " + posY );
		
		//play button
		if((posX>500 && posX<540) && (posY>278 && posY<300)){
			if(Mouse.isButtonDown(0)){			//0 == left click
				sbg.enterState(1);				//world map
			}
		}
		
		//exit
		if((posX>500 && posX<543) && (posY>180 && posY<195)){
			if(Mouse.isButtonDown(0)){			//0 == left click
				System.exit(0);
			}
		}
	}

	public int getID() {
		return 0;
	}

}
