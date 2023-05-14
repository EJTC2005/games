package Geometry;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cubos {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	Art art = new Art();
	static int size = 50;
	static int x = (10);
	static int y = (10);
	int width = (x+1)*size-35;
	int length = (y+1)*size-12;
	public Cubos() {
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(width,length);
		frame.setResizable(false);
		frame.add(art);
		//frame.add();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
