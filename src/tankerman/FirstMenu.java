package tankerman;


import javax.swing.JFrame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class FirstMenu extends BasicGameState{
	float posX = 0;
	float posY = 0;
	JFrame frame;
	Image menu;
	
	public FirstMenu(int startmenu) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		menu = new Image("res/GameMenu.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		menu.draw(0,0);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		this.posX = Mouse.getX();
		this.posY = Mouse.getY();
		
//		System.out.println("X: " + posX + " Y: " + posY );
		
		if((posX>363 && posX<638) && (posY>270 && posY<335)){
			if(Mouse.isButtonDown(0)){
				System.out.println("SERVER");
				//instantiate new server
//				ServerHost host = new ServerHost(frame,overallFrame);
				//startserver here
				sbg.enterState(3); // enter server state
			}
		}
		
		if((posX>363 && posX<638) && (posY>185 && posY<250)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(4);
			}
		}
		
		if((posX>363 && posX<638) && (posY>103 && posY<168)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(1); //worldmap
			}
		}
	}

	public int getID() {
		return 2;
	}

}
