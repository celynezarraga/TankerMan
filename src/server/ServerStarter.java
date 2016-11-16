package server;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.newdawn.slick.gui.TextField;

import com.jmr.wrapper.common.exceptions.NNCantStartServer;
import com.jmr.wrapper.server.Server;

public final class ServerStarter {
	
	private Server server;
	String ip;
	
	
	public ServerStarter ( TextField serverConsole, TextField ipPortStringfield){
		try {
			
			server = new Server(1337,1337);
			server.setListener(new ServerListener(serverConsole));
			if (server.isConnected()){
				serverConsole.setText(serverConsole.getText().concat("Server has started.\n"));
				try{
					ip = InetAddress.getLocalHost().toString();
					String[] ipAdd = ip.split("\\/");
					ipPortStringfield.setText("Now serving at " + ipAdd[ipAdd.length-1] + " Port: " + server.getUdpPort());
				}catch(UnknownHostException e){
					e.printStackTrace();
				}
			}
		}catch(NNCantStartServer e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
