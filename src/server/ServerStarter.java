package server;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JLabel;

import com.jmr.wrapper.common.exceptions.NNCantStartServer;
import com.jmr.wrapper.server.Server;

public final class ServerStarter {
	
	private Server server;
	String ip;
	
	public ServerStarter (JLabel addressLabel){
		try {
			
			server = new Server(1337,1337);
			server.setListener(new ServerListener());
			if (server.isConnected()){
				System.out.println("Server has strated");
				try{
					ip = InetAddress.getLocalHost().toString();
					String[] ipAdd = ip.split("\\/");
					addressLabel.setText("Now serving at " + ipAdd[ipAdd.length-1] + " Port: " + server.getUdpPort());
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
