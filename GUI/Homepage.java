import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Homepage extends JPanel{
	private Image image;
	public JButton splayer = new JButton("Singleplayer");
	public JButton mplayer = new JButton("Multiplayer");
	public JButton exit = new JButton("Quit");

	public Homepage(Container container, JFrame frame){
		try{
			image = Toolkit.getDefaultToolkit().getImage("homepage.jpg");
		}catch(Exception e){}
		this.setLayout(null);

		splayer.setBounds(60,150,305,50);
		mplayer.setBounds(60,200,305,50);
		exit.setBounds(60,250,305,50);

		splayer.setFont(new Font("Impact", Font.BOLD, 32));
		splayer.setBackground(Color.BLACK);
		splayer.setForeground(Color.WHITE);

		mplayer.setFont(new Font("Impact", Font.BOLD, 32));
		mplayer.setBackground(Color.BLACK);
		mplayer.setForeground(Color.WHITE);

		exit.setFont(new Font("Impact", Font.BOLD, 32));
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.WHITE);

		this.splayer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					frame.dispose();
					Singleplayer singleplayer = new Singleplayer();
					singleplayer.setVisible(true);
				}catch(Exception z){}
			}
		});;

		this.mplayer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					// Instructions instructions = new Instructions(container, frame);
					// container.removeAll();
					// container.add(instructions);
					// container.revalidate();
					// container.repaint();
				}catch(Exception z){}
			}
		});

		this.exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(this.image, 0, 0, null);

		this.add(splayer);
		this.add(mplayer);
		this.add(exit);
		Toolkit.getDefaultToolkit().sync();
	}
}