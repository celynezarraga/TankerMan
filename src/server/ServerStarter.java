package server;

import com.jmr.wrapper.common.exceptions.NNCantStartServer;
import com.jmr.wrapper.server.Server;

public final class ServerStarter {
	
	private Server server;
	
	public ServerStarter (){
		try {
			server = new Server(1337,1337);
			server.setListener(new ServerListener());
			if (server.isConnected()){
				System.out.println("Server has strated");
			}
		}catch(NNCantStartServer e){
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new ServerStarter();

	}

}
