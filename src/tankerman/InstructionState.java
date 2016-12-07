package tankerman;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class InstructionState extends BasicGameState{
	Image gmanual;
	float posX = 0;
	float posY = 0;
	
	public InstructionState(int endGame){
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gmanual = new Image("res/gamemanual.jpg");

		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		gmanual.draw(0,0);

//		g.drawString("CharacterX: " + posX + " CharY: " + posY , 400, 20);

		g.drawString("Back to game!", 400, 650);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		this.posX = Mouse.getX();
		this.posY = Mouse.getY();
		if((posX>450 && posX<550) && (posY>30 && posY<90)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(2);
			}
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 6;
	}

}
