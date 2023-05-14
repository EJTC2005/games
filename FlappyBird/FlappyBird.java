import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyBird implements KeyListener {
	JFrame frame;
	int width = 1000;
	int height = 600;
	Painter p;
	public static void main(String[] args) {
		FlappyBird b = new FlappyBird();
		
		
	}
	public FlappyBird() {
		frame = new JFrame("fleppBerD.exe");
		p = new Painter();
		frame.addKeyListener(p);
		frame.setSize(width,height);
		frame.add(p);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
