package tankerman;
import java.util.*;

import org.newdawn.slick.Animation;

import java.net.*;

public class Tank {
	private int userId;
	private int xpos;
	private int ypos;
	private Bullet[] bullets;
	private InetAddress address;
	private String name;
	private int port;
	private Animation character;
	
	public Tank(String name,Animation character,int id){
		this.address = address;
		this.name = name;
		this.bullets = new Bullet[3];
		this.character = character;
		this.userId = id;
	}
	
	public int getUserId(){
		return userId;
	}
	
	public Animation getChar(){
		return character;
	}
	
	public InetAddress getAddress(){
		return address;
	}
	
	public String getName(){
		return name;
	}
	
	public int getXpos(){
		return xpos;
	}
	
	public int getYpos(){
		return ypos;
	}
	
	private int getPort(){
		return port;
	}
	
	public void setXpos(int x){
		this.xpos = x;
	}
	
	public void setYpos(int y){
		this.ypos = y;
	}
	
	public void setId(int id){
		this.userId = id;
	}
	
	public void setChar(Animation c){
		this.character = c;
	}
	
	public String toString(){
		String ret="PLAYER: ";
		ret += name+" ";
		ret += xpos+" ";
		ret += ypos;
		return ret;
	}
}
