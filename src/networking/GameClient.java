package networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.newdawn.slick.Graphics;

import tankerman.WorldMap;

public class GameClient implements Runnable, Constants{

	int x=10,y=10,xspeed=2,yspeed=2,prevX,prevY;
	Thread t=new Thread(this);
	String name="Joseph";
	String pname;
	public static String server;
	boolean connected=false;
    public static DatagramSocket socket;
	String serverData;
	int startGame = 0;
	private String port;
	static int playerID;

	public GameClient(String serverIp, String port, String name) throws Exception{
		this.server=serverIp;
		this.name=name;
		this.port=port;
		socket = new DatagramSocket();
		
//		frame.setTitle(APP_NAME+":"+name);
		//set some timeout for the socket
		socket.setSoTimeout(100);
		
//		//Some gui stuff i hate.
//		frame.getContentPane().add(this);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(640, 480);
//		frame.setVisible(true);
//		
//		//create the buffer
//		offscreen=(BufferedImage)this.createImage(640, 480);
//		
//		//Some gui stuff again...
//		frame.addKeyListener(new KeyHandler());		
//		frame.addMouseMotionListener(new MouseMotionHandler());

		//tiime to play
		
		t.start();		
	}
	
	public static void send(String msg){
		try{
			byte[] buf = msg.getBytes();
        	InetAddress address = InetAddress.getByName(server);
        	DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
        	socket.send(packet);
        }catch(Exception e){}
		
	}

	public void run(){
		while(true){
			try{
				Thread.sleep(1);
			}catch(Exception ioe){}
						
			//Get the data from players
			byte[] buf = new byte[256];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			try{
     			socket.receive(packet);
			}catch(Exception ioe){/*lazy exception handling :)*/}
			
			serverData=new String(buf);
			serverData=serverData.trim();
			
//			if (!serverData.equals("")){
//				System.out.println("Server Data:" +serverData);
//			}

			//Study the following kids. 
			if (!connected && serverData.startsWith("CONNECTED")){
				connected=true;
//				System.out.println(serverData);
				String[] tokens = serverData.split("-");
				playerID = Integer.parseInt(tokens[1]);
				System.out.println("ID : " + playerID);
			}else if (!connected){
				System.out.println("Connecting..");				
				send("CONNECT "+name);
			}else if (connected){
//				offscreen.getGraphics().clearRect(0, 0, 640, 480);
				if(startGame==0 && serverData.startsWith("START")){
					startGame=1;
					System.out.println(startGame);
				}
				if (serverData.startsWith("PLAYER")){
					String[] playersInfo = serverData.split(":");
					for (int i=0;i<playersInfo.length;i++){
						String[] playerInfo = playersInfo[i].split(" ");
						String pname =playerInfo[1];
						int x = Integer.parseInt(playerInfo[2]);
						int y = Integer.parseInt(playerInfo[3]);
//						
						System.out.println(pname+" x:" +x+ " y:"+y);
						//draw on the offscreen image						
//						WorldMap.players[i].setXpos(x);
//						WorldMap.players[i].setYpos(y);
						//////here
//						offscreen.getGraphics().fillOval(x, y, 20, 20);
//						offscreen.getGraphics().drawString(pname,x-10,y+30);					
					}
					//show the changes
//					frame.repaint();
					
				}
				
			}			
		}
	}
	
	public int getStartGame(){
		return startGame;
	}
	
	public void paintComponent(Graphics g){
//		g.drawImage(offscreen, 0, 0, null);
	}
	
	public static int getPlayerID(){
		return playerID;
	}
	
	
	
	
//	class MouseMotionHandler extends MouseMotionAdapter{
//		public void mouseMoved(MouseEvent me){
//			x=me.getX();y=me.getY();
//			if (prevX != x || prevY != y){
//				send("PLAYER "+name+" "+x+" "+y);
//			}				
//		}
//	}
//	
//	class KeyHandler extends KeyAdapter{
//		public void keyPressed(KeyEvent ke){
//			prevX=x;prevY=y;
//			switch (ke.getKeyCode()){
//			case KeyEvent.VK_DOWN:y+=yspeed;break;
//			case KeyEvent.VK_UP:y-=yspeed;break;
//			case KeyEvent.VK_LEFT:x-=xspeed;break;
//			case KeyEvent.VK_RIGHT:x+=xspeed;break;
//			}
//			if (prevX != x || prevY != y){
//				send("PLAYER "+name+" "+x+" "+y);
//			}	
//		}
//	}
}
