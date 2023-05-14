package pongs;

import javax.swing.JFrame;

public class Pong {
	JFrame frame;
	GamePanel gamePanel;
	public Pong() {
		frame = new JFrame("Pong.exe");
		gamePanel = new GamePanel();
	}
	public void setup() {
		frame.setSize(750, 650);
		frame.add(gamePanel);
		frame.addKeyListener(gamePanel);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	public static void main(String[]args) {
		Pong pong = new Pong();
		pong.setup();
	}
}
