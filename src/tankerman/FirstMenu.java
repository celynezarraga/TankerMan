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
		menu = new Image("res/Menu.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		menu.draw(0,0);
//		g.drawString("Instruction", 100, 650);

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		this.posX = Mouse.getX();
		this.posY = Mouse.getY();
		
//		System.out.println("X: " + posX + " Y: " + posY );
		//instructions
//		if((posX>100 && posX<200) && (posY>100 && posY<200)){
//			if(Mouse.isButtonDown(0)){
//				sbg.enterState(6);
//			}
//		}
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
				sbg.enterState(6);//instruction
			}
		}
	}

	public int getID() {
		return 2;
	}

}
