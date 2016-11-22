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

import client.ClientListener;
import client.ClientStarter;
import ui.ClientConnect;

public class ClientState extends BasicGameState{
	
	private TextField namefield;
	private TextField serverIpfield;
	private TextField serverPortfield;
	private TextField connectionInfoField;
	public static TextField startGamebtn;
	private TextField  chatFieldTf;
	private TextField chatMsgsTf;



	public static String username;
	public static String serverIp;
	public static String serverport;
	public static boolean startGame=false;

	//
	float posX = 0;
	float posY = 0;
	float shiftX = posX + 450;
	float shiftY = posY + 300;
	//
	
	
	//
	Boolean connected = false;
	//
	
	ClientStarter client;


	public ClientState(int clientState){
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		
		
	}
	public void enter(GameContainer gc , StateBasedGame sbg) throws SlickException
    {
		namefield = new TextField(gc, gc.getDefaultFont(), 20, 200,800,20);
		namefield.setBorderColor(Color.white);
		serverIpfield = new TextField(gc, gc.getDefaultFont(), 20, 300,800,20);
		serverIpfield.setBorderColor(Color.white);
		serverPortfield = new TextField(gc, gc.getDefaultFont(), 20, 400,800,20);
		serverPortfield.setBorderColor(Color.white);
		connectionInfoField = new TextField(gc, gc.getDefaultFont(), 20, 600,800,20);
	//	serverPortfield.setBorderColor(Color.white);
		startGamebtn = new TextField(gc, gc.getDefaultFont(), 20, 300,800,20);
		startGamebtn.setBorderColor(Color.white);
    }

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
		//
		
		
		g.drawString("CharacterX: " + posX + " CharY: " + posY , 400, 20);
		//
		connectionInfoField.render(gc, g);
		if (!connected){
			g.drawString("Enter username", 100, 180);
			namefield.setCursorVisible(true);
			namefield.render(gc, g);
			
			g.drawString("Enter Server IP", 100, 280);
			serverIpfield.setCursorVisible(true);
			serverIpfield.render(gc, g);
			
			g.drawString("Enter Server Port", 100, 380);
			serverPortfield.setCursorVisible(true);
			serverPortfield.render(gc, g);
			
			g.drawString("PRESS ENTER TO CREATE CONNECTION", 285, 130);
			
			
		}else{
			startGamebtn.render(gc, g);
//			connectionInfoField.setText("CLIENT NOT CONNECTED");
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int g)
			throws SlickException {
		// TODO Auto-generated method stub
		this.posX = Mouse.getX();
		this.posY = Mouse.getY();
		
		//uname
		if((posX>20 && posX<800) && (posY>480 && posY<500)){
			if(Mouse.isButtonDown(0)){
//				System.out.println("unam");
//				
				}
		}
		
		//ip
		if((posX>20 && posX<800) && (posY>380 && posY<400)){
			if(Mouse.isButtonDown(0)){
//				System.out.println("ip");

			}
		}
		
		//port
		if((posX>20 && posX<800) && (posY>280 && posY<300)){
			if(Mouse.isButtonDown(0)){
//				System.out.println("port");
//
			}
			
		}
		//start game
		if((posX>20 && posX<800) && (posY>380 && posY<400)){
			if(Mouse.isButtonDown(0)){	
				if(startGame==true){ 
					System.out.println("G NA");
					sbg.enterState(1); // enter server state
				}
			}
		}
		if(startGame==true){ 
			startGamebtn.setText("PLAYERS COMPLETE. CLICK TO START GAME");
		}
		
		
		
		
		if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			username = namefield.getText();
			serverIp = serverIpfield.getText();
			serverport = serverPortfield.getText();
			
			if((username != "" && !username.isEmpty()) && (serverIp != "" && !serverIp.isEmpty()) && (serverport != "" && !serverport.isEmpty()) ) {
//				ClientConnect client = new ClientConnect(username,serverIp,serverport );
				try {
					client = new ClientStarter(serverIp, serverport, username, connectionInfoField, startGamebtn);
				} catch (Error e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				connected = true;

//				sbg.enterState(5); // 5 chatstate
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
