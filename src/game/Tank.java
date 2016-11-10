package game;

import java.awt.*;

public class Tank{
	private int hp;
	private int x;
	private int y;
	private boolean isAlive;
	private int cooldown;
	private String IP;
	private Color color;
	private String name;
	private int score;
	
	public Tank(int x,int y,String IP,Color color, String name){
		this.hp = 3;
		this.x = x;
		this.y = y;
		this.isAlive = true;
		this.cooldown = 0;
		this.IP=IP;
		this.color = color;
		this.name = name;
		this.score = 0;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public int getHp(){
		return this.hp;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public String getIP(){
		return this.IP;
	}
	
	public boolean isAlive(){
		if(this.hp >0){
			return true;
		}else{
			return false;
		}
	}
	
	public int getCooldown(){
		return this.cooldown;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	
}