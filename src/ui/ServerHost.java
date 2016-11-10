package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.jmr.wrapper.server.ConnectionManager;

import client.ClientListener;
import server.ServerStarter;
import ui.chatUI.sendMessageButtonListener;

public class ServerHost extends JPanel{
	
	JFrame frame;
	String playerName;
	Container overallFrame;
	Container a = new Container();
	JTextArea serverConsole;
	JLabel addressLabel;
	public static Boolean gameStarted;
	
	public ServerHost(JFrame frame, Container overallFrame, String playerName){
		this.frame = frame;
		this.overallFrame = overallFrame;
		this.playerName = playerName;
	
		renderFrame(frame);
		addLabelsAndContainers(frame);
		packFrame(frame);
		
		ServerStarter server = new ServerStarter(addressLabel, serverConsole);
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
		
		JLabel playersLabel = new JLabel("CONSOLE");
		a.add(playersLabel);
		
		Container x2 = new Container();
		x2.setPreferredSize(new Dimension(1500,10));
		a.add(x2);
		
		serverConsole = new JTextArea(450,225);
		serverConsole.setEditable(false);
		serverConsole.setLineWrap(true);
		serverConsole.setWrapStyleWord(true);
		JScrollPane areaScrollPane1 = new JScrollPane(serverConsole);
		areaScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane1.setPreferredSize(new Dimension(450, 225));
		a.add(areaScrollPane1);
		
		Container x1 = new Container();
		x1.setPreferredSize(new Dimension(1500,50));
		a.add(x1);
		
		JButton startPlay = new JButton("Start Game");
		startPlay.addActionListener(new startgameButtonListener());
		a.add(startPlay);
	}
	
	public void packFrame(JFrame frame){
		overallFrame.removeAll();
		overallFrame.add(a);
		frame.pack();
	}
class startgameButtonListener implements ActionListener {   	
        

		public void actionPerformed(ActionEvent event) {
			ServerHost.gameStarted  = true;
			System.out.println(gameStarted);
			if(ConnectionManager.getInstance().getConnections().size()==4){
				GameWindow game = new GameWindow();
				game.display();
			}else{
//				 JOptionPane.showMessageDialog(new JFrame(), "Players not complete yet", "Dialog",
//					        JOptionPane.ERROR_MESSAGE);
				GameWindow game = new GameWindow();
				game.display();
			}
        }
};

}
