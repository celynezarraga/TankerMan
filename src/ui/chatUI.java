package ui;

import packets.ChatMessage;

import com.jmr.wrapper.client.Client;
import com.jmr.wrapper.common.Connection;

import packets.ChatMessage;
import server.ConnectionManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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

import client.ClientStarter;

public class chatUI {

	 String      appName     = "Chat Window";
	 chatUI     chatUI;
	    JFrame      newFrame    = new JFrame(appName);
	    JButton     sendMessage;
	    JTextField  messageBox;
	    public JTextArea   chatBox;
	    JTextField  playerNameChooser;
	    JFrame      preFrame;
	    public String playerName;
	    Client client;
	    int msglen;
	    
	    public chatUI(Client client, String playerName ){
	    	this.client = client;
	    	this.playerName = playerName;
	    }
	    
    public void display(  String playerName) {
    	this.playerName = playerName;
    	
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();

        sendMessage = new JButton("Send Message");
        sendMessage.addActionListener(new sendMessageButtonListener());

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);

        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);

        mainPanel.add(BorderLayout.SOUTH, southPanel);

        newFrame.add(mainPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(470, 300);
        newFrame.setVisible(true);
        
        
        
       
    }

    class sendMessageButtonListener implements ActionListener {
    	
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                // do nothing        	            
            } else if (messageBox.getText().equals(".clear")) {
                chatBox.setText("Cleared all messages\n");
                messageBox.setText("");
            } else {
            	ChatMessage msg = new ChatMessage(playerName, messageBox.getText());
            	client.getServerConnection().sendTcp(msg);
                messageBox.setText("");
            }
            messageBox.requestFocusInWindow();
        }
    };
    
   
}