package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Playable extends JPanel{
	private Map map;
	private JFrame frame;
	
	
	/* components of the top panel */
	private JPanel top_panel = new JPanel();
	private JPanel[] scoreboard = new JPanel[4];
	
	/* components of scoreboard */
	private JLabel[] nameholder = new JLabel[4]; //name of player
	private JPanel[] imager = new JPanel[4]; //image of the tank. Also holds revive time when the tank dies.
	private JLabel[] scoreholder = new JLabel[4]; //Score of player
	private JPanel[] name_score = new JPanel[4];
	private JPanel[] imagerholder = new JPanel[4];
	
	
	/*where the game will be laid out*/
	private JPanel centre = new JPanel();
	
	
	/* Player Information */
	private Tank[] players = new Tank[4];
	
	public Playable(){
		players[0] = new Tank(1,1,"YASS",Color.RED, "Rekcus");
		players[1] = new Tank(19,1,"YISS",Color.BLUE, "Donggol Trump");
		players[2] = new Tank(1,19,"RIP",Color.GREEN, "Green Day");
		players[3] = new Tank(19,19,"KEK",Color.YELLOW, "Killer qu33n");
		
		frame = new JFrame("T A N K E R M A N");
		Container con = frame.getContentPane();
		con.setLayout(new BorderLayout());
		
		map = new Map();
		
		top_panel = new JPanel();
		top_panel.setLayout(new GridLayout(1,4));
		top_panel.setPreferredSize(new Dimension(20,50));
		top_panel.setBorder(new EmptyBorder(10,10,10,10));
		for(int i=0;i<4;i++){
				/* initialize scoreboard */
				
				scoreboard[i] = new JPanel();
				scoreboard[i].setLayout(new BorderLayout());
				
				/* initialize scoreboard components */
				
				name_score[i] = new JPanel();
				name_score[i].setLayout(new GridLayout(2,1));
				
				imagerholder[i] = new JPanel();
				nameholder[i] = new JLabel(players[i].getName());
				
				scoreholder[i] = new JLabel(Integer.toString(players[i].getScore()));
				
				imager[i] = new JPanel();
				imager[i].setBackground(players[i].getColor());
				imager[i].setPreferredSize(new Dimension(10,10));
				
				name_score[i].add(nameholder[i]);
				name_score[i].add(scoreholder[i]);
				
				imagerholder[i].add(imager[i]);
				
				
				scoreboard[i].add(imagerholder[i],BorderLayout.EAST);
				scoreboard[i].add(name_score[i],BorderLayout.WEST);
				
				top_panel.add(scoreboard[i]);
		}
		
		centre.add(map);
	
		
		
		con.add(top_panel,BorderLayout.NORTH);
		con.add(centre, BorderLayout.CENTER);
		
		
		//frame.add(con);
		
		frame.setSize(new Dimension(805,655));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.BLACK);
		frame.setVisible(true);
		
		/*frame.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e){
				int keyCode = e.getKeyCode();
				
				if(keyCode == KeyEvent.VK_W){
					
				}
			}
		});*/
	}
}