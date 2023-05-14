package game;



import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
//hooligan.3


public class Minesweeper{
	JFrame frame;
	MyPanel panel;
	boolean flagging = false;
	public static void main(String[] args) {
		Minesweeper mine = new Minesweeper();
		mine.setup();
	}
	public void setup() {
		frame.add(panel.pan, BorderLayout.NORTH);
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(800, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public Minesweeper(){
		frame = new JFrame("Minesweeper");
		panel = new MyPanel();
	
	}
	
}
