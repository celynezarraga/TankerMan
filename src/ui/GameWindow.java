package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import packets.ChatMessage;
import ui.chatUI.sendMessageButtonListener;
import client.ClientStarter;

public class GameWindow {

	 String      appName     = "Game Window";
	 GameWindow     GameWindow;
	    JFrame      newFrame    = new JFrame(appName);
	    JButton     sendMessage;
	    JTextField  messageBox;
	    public JTextArea   chatBox;
	    JTextField  playerNameChooser;
	    JFrame      preFrame;
	    public String playerName;
	    int msglen;
	    
	    public GameWindow( ){
	    	
	    }
	    
    public void display(  ) {
    	
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
//        JPanel holderPanel = new JPanel();
//        holderPanel.setLayout(new BorderLayout());
//        gamePanel.setSize(900, 800);

        JPanel gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(700, 700));
        gamePanel.setBackground(Color.BLUE);

        
        JPanel chatPanel = new JPanel();
        chatPanel.setPreferredSize(new Dimension(300, 700));
        chatPanel.setBackground(Color.RED);
        chatPanel.setLayout(new BorderLayout());


        
//        mainPanel.add(holderPanel, BorderLayout.CENTER);

        ///// chat elements
    	
       
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.PINK);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();
//        messageBox.setPreferredSize(new Dimension(280, 50));

        sendMessage = new JButton("Send Message");
        sendMessage.addActionListener(new sendMessageButtonListener());

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);
        chatBox.setBackground(Color.BLACK);

        chatPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 0, 0, 10);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);

        chatPanel.add(BorderLayout.SOUTH, southPanel);

//        mainPanel.add(chatPanel);
//        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        newFrame.setSize(350, 700);
//        newFrame.setVisible(true);
        
        /////

        mainPanel.add(gamePanel, BorderLayout.WEST);
        mainPanel.add(chatPanel, BorderLayout.EAST);

        newFrame.add(mainPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(1020, 1000);
        newFrame.setVisible(true);
        
        
        
       
    }

    class sendMessageButtonListener implements ActionListener {
    	
        public void actionPerformed(ActionEvent event) {
            
        }
    };
    
	
   
}