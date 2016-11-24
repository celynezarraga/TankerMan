	package tankerman;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import java.net.InetAddress;

import org.newdawn.slick.Color;

public class Bullet {
	Vector2f pos;
	private Vector2f spd;
	private int lived = 0;
	private int port;
	private InetAddress address;
	private TiledMap map;
	
	private boolean active = true;
	
	private static int MAX_LIFETIME = 1800;
	
	public Bullet(Vector2f pos, Vector2f spd,String map){
		this.pos = pos;
		this.spd = spd;
		this.map = 
	}
	
	public Bullet(){
		active = false;
	}
	
	public void update(int t){
		if(active){
			pos.add(spd.copy().scale(t/1000.0f));
			lived += t;
			if(lived > MAX_LIFETIME){
				active = false;
			}
			
		}
	}
	
	public void render(GameContainer gc, Graphics g)throws SlickException{
		if(active){
			g.setColor(Color.yellow);
			g.fillOval(pos.getX()-10,pos.getY()-10,5,5);
		}
	}
	
	public boolean isActive(){
		return active;
	}
	
	public Vector2f getPos(){
		return pos;
	}
	
	public int getPort(){
		return port;
	}
	
	public InetAddress getAddress(){
		return address;
	}
	
	public String toString(){
		String ret = "BULLET: ";
		ret += pos.toString()+" ";
		ret += spd.toString();
		return ret;
	}
}
