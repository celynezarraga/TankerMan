package packets;

import java.io.Serializable;

public class ConnectionNumber implements Serializable  {

	/**
	 * 
	 */
	public int noOfConnections;
	private static final long serialVersionUID = 1L;
	public  ConnectionNumber (int noOfConnections){
		this.noOfConnections = noOfConnections;
	}

}
