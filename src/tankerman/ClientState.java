package tankerman;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.jmr.wrapper.common.exceptions.NNClientCantConnect;


import networking.ChatClientStarter;
import networking.GameClient;

public class ClientState extends BasicGameState{
	
	private TextField namefield;
	private TextField serverIpfield;
	private TextField serverPortfield;
	private TextField connectionInfoField;
	private TextField startGamebtn;
	private TextField  chatFieldTf;
	private TextField chatMsgsTf;
	public static String username;
	public static String serverIp;
	public static String serverport;
	float posX = 0;
	float posY = 0;
	float shiftX = posX + 450;
	float shiftY = posY + 300;
	Boolean connected = false;
	GameClient client;
	ChatClientStarter chatclient;
	int completePlayers = 0;


	public ClientState(int clientState){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
	}
	public void enter(GameContainer gc , StateBasedGame sbg) throws SlickException
    {
		namefield = new TextField(gc, gc.getDefaultFont(), 200, 200,600,20);
		namefield.setBorderColor(Color.white);
		serverIpfield = new TextField(gc, gc.getDefaultFont(), 200, 300,600,20);
		serverIpfield.setBorderColor(Color.white);
		serverPortfield = new TextField(gc, gc.getDefaultFont(), 200, 400,600,20);
		serverPortfield.setBorderColor(Color.white);
		connectionInfoField = new TextField(gc, gc.getDefaultFont(), 200, 600,600,20);
	
    }

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("CharacterX: " + posX + " CharY: " + posY , 400, 20);
		connectionInfoField.render(gc, g);
		if (!connected){
			g.drawString("Enter username", 200, 180);
			namefield.setCursorVisible(true);
			namefield.render(gc, g);
			
			g.drawString("Enter Server IP", 200, 280);
			serverIpfield.setCursorVisible(true);
			serverIpfield.render(gc, g);
			
			g.drawString("Enter Server Port", 200, 380);
			serverPortfield.setCursorVisible(true);
			serverPortfield.render(gc, g);
			
			g.drawString("PRESS ENTER TO CREATE CONNECTION", 350, 130);
			
			
		}else{
			if(completePlayers==1){
				sbg.enterState(1);
			}
			else{
				completePlayers = client.getStartGame();
			}
//			startGamebtn.render(gc, g);
			connectionInfoField.setText("WAITING FOR OTHER PLAYERS");
		}
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int g)
			throws SlickException {
		this.posX = Mouse.getX();
		this.posY = Mouse.getY();
		
		if((posX>200 && posX<600) && (posY>480 && posY<500)){
			if(Mouse.isButtonDown(0)){
				}
		}
		
		if((posX>200 && posX<600) && (posY>380 && posY<400)){
			if(Mouse.isButtonDown(0)){
			}
		}
		
		if((posX>200 && posX<600) && (posY>280 && posY<300)){
			if(Mouse.isButtonDown(0)){
			}
			
		}	
		
		if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			username = namefield.getText();
			serverIp = serverIpfield.getText();
			serverport = serverPortfield.getText();
			
			if((username != "" && !username.isEmpty()) && (serverIp != "" && !serverIp.isEmpty()) && (serverport != "" && !serverport.isEmpty()) ) {
//				ClientConnect client = new ClientConnect(username,serverIp,serverport );
				try {
					client = new GameClient(serverIp, serverport, username);	
					chatclient = new ChatClientStarter(username,serverIp, serverport);
					connected = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int getID() {
		return 4;
	}
		
}
