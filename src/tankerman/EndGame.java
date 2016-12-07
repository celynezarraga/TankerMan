package tankerman;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import networking.GameState;

public class EndGame extends BasicGameState{
	public static int winner;
	public EndGame(int endGame){
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.drawString("GAME OVER!", 400, 450);
		
		g.drawString("CONGRATULATIONS TANKER!", 400, 400);
//		g.drawString(GameState.getPlayername(winner), 400, 550);
		WorldMap.characters[winner].draw(400,350);
		WorldMap.characters[winner].draw(430,350);
		WorldMap.characters[winner].draw(460,350);
		WorldMap.characters[winner].draw(490,350);
		WorldMap.characters[winner].draw(520,350);
		
		
		
		
		//check if same team
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 5;
	}

}
