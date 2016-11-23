package tankerman;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import networking.ChatServerStarter;
import networking.GameServer;
import server.ServerStarter;

public class ServerState extends BasicGameState{
	public static TextField console;
	private TextField  ipPortStringfield;
	public static String serverIp;
	
	public ServerState(int serverState){
		
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
		
	}
	
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		ipPortStringfield = new TextField(arg0, arg0.getDefaultFont(), 100, 180,800,20);
		console = new TextField(arg0, arg0.getDefaultFont(), 0, 200,800,500);
//		ServerStarter server = new ServerStarter(console, ipPortStringfield);
		GameServer server = new GameServer(console, ipPortStringfield);
		try {
			ChatServerStarter chatserver = new ChatServerStarter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		ipPortStringfield.deactivate();
		ipPortStringfield.render(arg0, arg2);
		console.deactivate();
		console.render(arg0, arg2);
		
		//get the clients connected
		
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	


}
