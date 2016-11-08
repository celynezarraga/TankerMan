package ui;

import java.awt.*;
import javax.swing.*;
import server.ServerStarter;

public class ServerHost extends JPanel{
	
	JFrame frame;
	String playerName;
	Container overallFrame;
	Container a = new Container();
	JTextArea playersList;
	JLabel addressLabel;
	
	public ServerHost(JFrame frame, Container overallFrame, String playerName){
		this.frame = frame;
		this.overallFrame = overallFrame;
		this.playerName = playerName;
	
		renderFrame(frame);
		addLabelsAndContainers(frame);
		packFrame(frame);
		
		ServerStarter server = new ServerStarter(addressLabel);
	}
	
	public void renderFrame(JFrame frame){
		overallFrame.setPreferredSize(new Dimension(1500, 850));
		overallFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		a.setPreferredSize(new Dimension(1500, 850));
		a.setLayout(new FlowLayout(FlowLayout.CENTER));
	}
	
	public void addLabelsAndContainers(JFrame frame){
		addressLabel = new JLabel("");
		a.add(addressLabel);
		
		Container x = new Container();
		x.setPreferredSize(new Dimension(1500,50));
		a.add(x);
		
		JLabel playersLabel = new JLabel("PLAYERS");
		a.add(playersLabel);
		
		Container x2 = new Container();
		x2.setPreferredSize(new Dimension(1500,10));
		a.add(x2);
		
		playersList = new JTextArea(450,225);
		playersList.setEditable(false);
		playersList.setLineWrap(true);
		playersList.setWrapStyleWord(true);
		JScrollPane areaScrollPane1 = new JScrollPane(playersList);
		areaScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane1.setPreferredSize(new Dimension(450, 225));
		a.add(areaScrollPane1);
		
		Container x1 = new Container();
		x1.setPreferredSize(new Dimension(1500,50));
		a.add(x1);
		
		JButton startPlay = new JButton("Start Game");
		a.add(startPlay);
	}
	
	public void packFrame(JFrame frame){
		overallFrame.removeAll();
		overallFrame.add(a);
		frame.pack();
	}

}
