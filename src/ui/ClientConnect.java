package ui;

import javax.swing.*;
import java.awt.*;
import client.ClientStarter;

public class ClientConnect extends JPanel{
	JFrame frame;
	Container overallFrame;
	String playerName;
	Container a = new Container();
	JTextArea playersList;
	JLabel addressLabel;
	String ip = "";
	String port = "";

	
	chatUI ui_chat = new chatUI();
	
	
	public ClientConnect(JFrame frame, Container overallFrame, String playerName){
		this.frame = frame;
		this.overallFrame = overallFrame;
		this.playerName = playerName;
		
		while(ip == null || ip.isEmpty() || ip.length()==0){
			ip = JOptionPane.showInputDialog("Enter IP of server");
	    }
		while(port == null || port.isEmpty() || port.length()==0){
			port = JOptionPane.showInputDialog("Enter port number");
	    }
		
		
		
		renderFrame(frame);
		addLabelsAndContainers(frame);		
		packFrame(frame);
		
		ClientStarter client = new ClientStarter(ip, port, addressLabel, playersList, playerName);
		
		if(!client.connected()){
			ClientConnect cConnect = new ClientConnect(frame,overallFrame, playerName);
		}
	}
	
	public void renderFrame(JFrame frame){
		overallFrame.setPreferredSize(new Dimension(1500, 850));
		overallFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		a.setPreferredSize(new Dimension(500, 850));
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
		
		
	}
	
	public void packFrame(JFrame frame){
		overallFrame.removeAll();
		overallFrame.add(a);
		
		overallFrame.add(ui_chat.display());

		
		frame.pack();
	}
	
	
}
