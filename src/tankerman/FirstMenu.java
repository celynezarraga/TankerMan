package tankerman;


import javax.swing.JFrame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class FirstMenu extends BasicGameState{
	float posX = 0;
	float posY = 0;
	JFrame frame;
	
	
	public FirstMenu(int startmenu) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("CharacterX: " + posX + " CharY: " + posY , 400, 20);
		g.drawString("Create Server", 500, 400);
		g.drawString("Start as Client", 500, 500);
		g.drawString("Laro Muna", 500, 600);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		this.posX = Mouse.getX();
		this.posY = Mouse.getY();
		
//		System.out.println("X: " + posX + " Y: " + posY );
		
		//server button
		if((posX>500 && posX<540) && (posY>278 && posY<300)){
			if(Mouse.isButtonDown(0)){			//0 == left click
				System.out.println("SERVER");
				//instantiate new server
//				ServerHost host = new ServerHost(frame,overallFrame);
				//startserver here
				sbg.enterState(3); // enter server state
				

			}
		}
		
		//client
		if((posX>500 && posX<543) && (posY>180 && posY<195)){
			if(Mouse.isButtonDown(0)){			//0 == left click
				System.out.println("client");
				sbg.enterState(4);
			}
		}
		
		if((posX>500 && posX<580) && (posY>85 && posY<100)){
			if(Mouse.isButtonDown(0)){			//0 == left click
				System.out.println("client");
				sbg.enterState(1); //worldmap
			}
		}
	}

	public int getID() {
		return 2;
	}

}
