package ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JPanel{
	
	Container overallFrame = new Container();
	Container a = new Container();
	JFrame frame;
	String playerName = "";
	
	public Game(JFrame frame){
		this.frame = frame;
	}
	
	public void renderFrame(JFrame frame){
		frame.setSize(new Dimension (1500,850));						//frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		overallFrame.setPreferredSize(new Dimension(1500, 850));
		overallFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		a.setPreferredSize(new Dimension(200, 850));
		a.setLayout(new FlowLayout(FlowLayout.CENTER));
	}
	
	public void addLabelsAndContainers(JFrame frame){
		JButton startButton = new JButton("START");
		
		startButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				while(playerName == null || playerName.isEmpty() || playerName.length()==0){
					playerName = JOptionPane.showInputDialog("Enter your name");
			    }
				
				JLabel welcomePlayer = new JLabel("HI  ".concat(playerName.toUpperCase() + "!"));
				JButton createServer = new JButton("Create a new server");
				
				createServer.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						System.out.println("create server");
						ServerHost host = new ServerHost(frame,overallFrame,playerName);
					}	
				});
				
				JButton connectToServer = new JButton("Connect to server");
				
				connectToServer.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						System.out.println("connect to an existing server");
						ClientConnect client = new ClientConnect(frame,overallFrame,playerName);
					}	
				});
				
				a.add(welcomePlayer);
				a.add(createServer);
				a.add(connectToServer);
				
				overallFrame.removeAll();
				overallFrame.add(a);
				frame.pack();
			}
		});
		
		overallFrame.add(startButton);
	}
	
	public void packFrame(JFrame frame){
		frame.getContentPane().setBackground(Color.gray);

		frame.add(overallFrame);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void start(JFrame frame){
		renderFrame(frame);
		addLabelsAndContainers(frame);
		packFrame(frame);
	}
	
}
