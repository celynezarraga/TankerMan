package game;
import javax.swing.*;
import java.awt.*;

public class Map extends JPanel{
	private JLabel[][] uwalls = new JLabel[21][21]; //tiles label
	private JLabel[][] walls = new JLabel[21][21];
	private JLabel[] tanks = new JLabel[4]; //tanks label
	private JPanel[][] p = new JPanel[21][21];
	private Tank[] players = new Tank[4];
	
	public Map(){
		setLayout(new GridLayout(21,21));
		setPreferredSize(new Dimension(800,600));
		setBackground(Color.BLACK);
		
		
		players[0] = new Tank(1,1,"YASS",Color.RED, "Rekcus");
		tanks[0] = new JLabel();
		tanks[0].setBackground(players[0].getColor());
		
		players[1] = new Tank(19,1,"YISS",Color.BLUE, "Donggol Trump");
		tanks[1] = new JLabel();
		tanks[1].setBackground(players[1].getColor());
		
		players[2] = new Tank(1,19,"RIP",Color.GREEN, "Green Day");
		tanks[2] = new JLabel();
		tanks[2].setBackground(players[2].getColor());
		
		players[3] = new Tank(19,19,"KEK",Color.YELLOW, "Killer qu33n");
		tanks[3] = new JLabel();
		tanks[3].setBackground(players[3].getColor());
		
		for(int i=0;i<21;i++){
			for(int j=0;j<21;j++){
				p[i][j] = new JPanel();
				p[i][j].setBackground(Color.PINK);
				p[i][j].setLayout(new BorderLayout());
				this.add(p[i][j]);
			}
		}
		
		for(int i=0;i<21;i++){
			for(int j=0;j<21;j++){
				if(i == 10 || j == 10){
					walls[i][j] = new JLabel();
					p[i][j].add(walls[i][j]);
					p[i][j].setBackground(Color.ORANGE);
				}else
				
				if(i == j){
					uwalls[i][j] = new JLabel();
					p[i][j].add(uwalls[i][j]);
					p[i][j].setBackground(Color.DARK_GRAY);
				}
				
				
			}
		}
		
		p[players[0].getX()][players[0].getY()].add(tanks[0]);
		p[players[0].getX()][players[0].getY()].setBackground(players[0].getColor());
		
		p[players[1].getX()][players[1].getY()].add(tanks[1]);
		p[players[1].getX()][players[1].getY()].setBackground(players[1].getColor());
		
		p[players[2].getX()][players[2].getY()].add(tanks[2]);
		p[players[2].getX()][players[2].getY()].setBackground(players[2].getColor());
		
		p[players[3].getX()][players[3].getY()].add(tanks[3]);
		p[players[3].getX()][players[3].getY()].setBackground(players[3].getColor());
	}
	
	public JLabel getWall(int x,int y){
		return this.walls[x][y];
	}
}