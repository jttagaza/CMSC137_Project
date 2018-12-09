import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class MyFrame extends JFrame {
	public static final int DIMENSION_X = 420;
	public static final int DIMENSION_Y = 460;
	private JFrame frame = this;
	
	public MyFrame (){
		this.setPreferredSize(new Dimension(DIMENSION_X,DIMENSION_Y));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		Container container = this.getContentPane();	

		try{
			Homepage menu = new Homepage(container, frame); //Start Menu
			container.removeAll();
			container.add(menu);
			container.revalidate();
			container.repaint();
		}catch(Exception e){}

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}