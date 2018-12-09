import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Chat extends JPanel{
	private JButton send;
	private JTextField messageBox;
	private JTextArea messageDisplay;

	public Chat(){
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 0));

		this.messageDisplay = new JTextArea("Sample Text Here");
		this.messageDisplay.setPreferredSize(new Dimension(500, 475));
		this.add(this.messageDisplay);
		this.messageBox = new JTextField("Write Text Here");
		this.messageBox.setPreferredSize(new Dimension(325, 75));
		this.add(this.messageBox);

		this.send = new JButton("Send");
		this.send.setPreferredSize(new Dimension(125, 70));
		this.add(this.send);
	}
}