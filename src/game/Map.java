package game;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class Map extends JPanel{
	private JLabel[][] uwalls = new JLabel[15][15]; //tiles label
	private JLabel[][] walls = new JLabel[15][15];
	private JLabel[] tanks = new JLabel[4]; //tanks label
	private JPanel[][] p = new JPanel[15][15];
	private Tank[] players = new Tank[4];
	
	public Map(){
		setLayout(new GridLayout(15,15));
		setPreferredSize(new Dimension(800,600));
		setBackground(Color.WHITE);
		
		
		players[0] = new Tank(3,10,"YASS","RED");
		tanks[0] = new JLabel();
		tanks[0].setBackground(Color.RED);
		
		players[1] = new Tank(3,5,"YISS","BLUE");
		tanks[1] = new JLabel();
		tanks[1].setBackground(Color.BLUE);
		
		players[2] = new Tank(10,3,"RIP","GREEN");
		tanks[2] = new JLabel();
		tanks[2].setBackground(Color.GREEN);
		
		players[3] = new Tank(5,3,"KEK","YELLOW");
		tanks[3] = new JLabel();
		tanks[3].setBackground(Color.YELLOW);
		
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				p[i][j] = new JPanel();
				p[i][j].setBackground(Color.PINK);
				p[i][j].setLayout(new BorderLayout());
				this.add(p[i][j]);
			}
		}
		
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				if(i == 7 || j == 7){
					walls[i][j] = new JLabel();
					p[i][j].add(walls[i][j]);
					p[i][j].setBackground(Color.ORANGE);
					break;
				}
				
				if(i == j){
					uwalls[i][j] = new JLabel();
					p[i][j].add(uwalls[i][j]);
					p[i][j].setBackground(Color.DARK_GRAY);
					break;
				}
				
				
			}
		}
		
		p[players[0].getX()][players[0].getY()].add(tanks[0]);
		p[players[1].getX()][players[1].getY()].add(tanks[1]);
		p[players[2].getX()][players[2].getY()].add(tanks[2]);
		p[players[3].getX()][players[3].getY()].add(tanks[3]);
		
	}
	
	public JLabel getWall(int x,int y){
		return this.walls[x][y];
	}
}