package packets;

import java.io.Serializable;

public class ChatMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String username, message;
	
	public  ChatMessage (String username, String message){
		this.username = username;
		this.message = message;
	}

}
