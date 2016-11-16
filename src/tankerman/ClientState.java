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

import client.ClientStarter;
import ui.ClientConnect;

public class ClientState extends BasicGameState{
	
	private TextField namefield;
	private TextField serverIpfield;
	private TextField serverPortfield;
	private TextField connectionInfoField;
	private TextField startGamebtn;


	public static String username;
	public static String serverIp;
	public static String serverport;

	//
	float posX = 0;
	float posY = 0;
	float shiftX = posX + 450;
	float shiftY = posY + 300;
	//
	
	
	//
	Boolean connected = false;
	//


	public ClientState(int clientState){
		
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		namefield = new TextField(arg0, arg0.getDefaultFont(), 20, 200,800,20);
		namefield.setBorderColor(Color.white);
		serverIpfield = new TextField(arg0, arg0.getDefaultFont(), 20, 300,800,20);
		serverIpfield.setBorderColor(Color.white);
		serverPortfield = new TextField(arg0, arg0.getDefaultFont(), 20, 400,800,20);
		serverPortfield.setBorderColor(Color.white);
		connectionInfoField = new TextField(arg0, arg0.getDefaultFont(), 20, 600,800,20);
//		serverPortfield.setBorderColor(Color.white);
		startGamebtn = new TextField(arg0, arg0.getDefaultFont(), 20, 300,800,20);
		startGamebtn.setBorderColor(Color.white);
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
		//
		
		
		arg2.drawString("CharacterX: " + posX + " CharY: " + posY , 400, 20);
		//
		connectionInfoField.render(arg0, arg2);
		if (!connected){
			arg2.drawString("Enter username", 100, 180);
			namefield.render(arg0, arg2);
			namefield.setCursorVisible(true);
			arg2.drawString("Enter Server IP", 100, 280);
			serverIpfield.render(arg0, arg2);
			serverIpfield.setCursorVisible(true);
			arg2.drawString("Enter Server Port", 100, 380);
			serverPortfield.render(arg0, arg2);
			serverPortfield.setCursorVisible(true);
			arg2.drawString("PRESS ENTER TO CREATE CONNECTION", 285, 130);
			
			
		}else{
			startGamebtn.render(arg0, arg2);
		}
		
		
		
		

		
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		this.posX = Mouse.getX();
		this.posY = Mouse.getY();
		
		//uname
		if((posX>20 && posX<800) && (posY>480 && posY<500)){
			if(Mouse.isButtonDown(0)){
				}
		}
		
		//ip
		if((posX>20 && posX<800) && (posY>380 && posY<400)){
			if(Mouse.isButtonDown(0)){
			}
		}
		
		//port
		if((posX>20 && posX<800) && (posY>280 && posY<300)){
			if(Mouse.isButtonDown(0)){				
			}
			
		}
		
		
		if (arg0.getInput().isKeyPressed(Input.KEY_ENTER)) {
			username = namefield.getText();
			serverIp = serverIpfield.getText();
			serverport = serverPortfield.getText();
			
			if((username != "" && !username.isEmpty()) && (serverIp != "" && !serverIp.isEmpty()) && (serverport != "" && !serverport.isEmpty()) ) {
//				ClientConnect client = new ClientConnect(username,serverIp,serverport );
				ClientStarter client = new ClientStarter(serverIp, serverport, username, connectionInfoField, startGamebtn);
				connected = true;
//				arg1.enterState(5); // 5 chatstate
//				System.out.println(username);
//				System.out.println(serverIp);
//				System.out.println(serverport);

			}
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}
	
	

	
	
}
