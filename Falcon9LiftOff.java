import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Falcon9LiftOff extends JPanel {
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 1000;
	private static double DELTATIME = 0.5;
	
	private BufferedImage image;
	private Graphics g;
	private Falcon9 rocket;
	private Timer timer;
	
	private static double draw_deltaTime;
	private static double draw_velocity;
	private static double draw_altitude;
	
	public Falcon9LiftOff() {
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		
		g.setColor(new Color(135,206,250));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		rocket = new Falcon9(475, 925, 50, 75);
		
		timer = new Timer(500, new TimerListener());
		timer.start();
	}
	
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			g.setColor(new Color(135,206,250));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			rocket.setY((int)((HEIGHT-rocket.getHeight())*(1-(rocket.getAltitude()/106749.02170042))));
			
			g.setFont(new Font("Roboto", Font.PLAIN, 25));
			g.setColor(Color.BLUE);
			
			if (rocket.getDeltaTime() <= 162) {
				draw_deltaTime = rocket.getDeltaTime();
				draw_velocity = rocket.getVelocity();
				draw_altitude = rocket.getAltitude();
			}
			else {
				rocket.setY((int)(0.8*rocket.getHeight()));
			}
			g.drawString("Time: " + draw_deltaTime, 25, 25);
			g.drawString("Speed: " + draw_velocity, 25, 50);
			g.drawString("Altitude: " + draw_altitude, 25, 75);
			
			rocket.move(DELTATIME);
			rocket.draw(g);
			
			
			for (int i = 0; i < HEIGHT; i++) {
				g.setColor(Color.WHITE);
				
				int x = (int)(Math.random() * HEIGHT) + 0;
				int y = (int)(Math.random() * HEIGHT) + 0;
				int z = (int)(Math.random() * 10) + 0;
				
				g.drawLine(x, y, x+z, y+z);
			}
			repaint();
			
			/*
			for (int i = 0; i < 5; i++) {
				
				int x = (int)(Math.random() * HEIGHT) + 0;
				int y = (int)(Math.random() * HEIGHT) + 0;
				int z = (int)(Math.random() * 15) + 0;
				
				Color[] colors = {new Color(42, 72, 88), new Color(37, 85, 102), new Color(28, 99, 115), new Color(11, 113, 126), new Color(0, 127, 134), new Color(0, 141, 140), new Color(0, 156, 143), new Color(35, 170, 143), new Color(63, 183, 141), new Color(91, 196, 137), new Color (119, 209, 131), new Color(149, 221, 125), new Color(181, 232, 119), new Color(215, 241, 113), new Color(250, 250, 110)};
				g.setColor(colors[z]);
				
				g.fillRect(x, y, 25, 25);
			}
			
			repaint();
			*/
		}
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Rocket Animation");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Falcon9LiftOff());
		frame.setVisible(true);
	}
}
