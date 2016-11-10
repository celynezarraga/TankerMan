package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Playable extends JPanel{
	private Map map;
	private JFrame frame;
	
	public Playable(){
		frame = new JFrame("T A N K E R M A N");
		frame.setLayout(new BorderLayout());
		map = new Map();
		frame.add(map);
		
		frame.setSize(new Dimension(800,600));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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