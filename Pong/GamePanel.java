package pongs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer t;
	Shape player1;
	ArrayList<String> queue1;
	String currentDirection1 = "";
	Shape player2;
	int playerSpeed=8;
	ArrayList<String> queue2;
	Shape ball;
	String currentDirection2 = "";
	int ballXSpeed=0;
	int ballYSpeed=0;
	Shape[] center;
	boolean started;
	int p1Score=0;
	int p2Score=0;
	JLabel scores;
	Random r;
	public GamePanel() {
		t = new Timer(1, this);
		player1 = new Shape(50,255,20,90);
		player2 = new Shape(675,255,20,90);
		ball = new Shape(360,285,30,30);
		center = new Shape[10];
		queue1 = new ArrayList<String>();
		queue2 = new ArrayList<String>();
		started=false;
		r=new Random();
		t.start();
		scores = new JLabel(p1Score+"                 "+p2Score);
		scores.setHorizontalAlignment(JLabel.CENTER);
		scores.setForeground(Color.WHITE);
		scores.setFont(new Font("Ink Free",Font.BOLD,75));
		add(scores);
	}
	public void paintComponent(Graphics g) {
	//	Graphics g2D = (Graphics) g;
		super.paintComponent(g);
		setBackground(Color.BLACK);
		g.setColor(Color.WHITE);
		g.fillRect(player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight());
		g.fillRect(player2.getX(), player2.getY(), player2.getWidth(), player2.getHeight());
		for(int i = 0; i < center.length; i++) {
			g.fillRect(370, i*50+70, 10, 10);
		}
		g.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
	}
	public void startRound() {
		if(r.nextBoolean()) {
			ballXSpeed=5;
		}
		else{
			ballXSpeed=-5;
		}
		started=true;
	}
	public void endRound() {
		started=false;
		ball = new Shape(360,285,30,30);
		ballYSpeed=0;
	}
	public void move() {
		if(ball.getX()+ball.getWidth()<0) {
			p2Score++;
			scores.setText(p1Score+"                 "+p2Score);
			endRound();
			
		}
		else if(ball.getX()>750) {
			p1Score++;
			scores.setText(p1Score+"                 "+p2Score);
			endRound();
		
		}
		//ballMovement
		if(ball.getY()+ballYSpeed<=0) {
			ball.setY(0);
			ballYSpeed=-ballYSpeed;
			
		}
		else if(ball.getY()+ball.getHeight()+ballYSpeed>=615) {
			ball.setY(615-ball.getHeight());
			ballYSpeed=-ballYSpeed;
		}
		if(ball.getX()+ball.getWidth()>=player2.getX()&&ball.getX()<=player2.getX()+player2.getWidth()&&ball.getY()+ball.getHeight()>=player2.getY()&&ball.getY()<=player2.getY()+player2.getHeight()) {
			ballXSpeed=-5;
			ballYSpeed=(((2*ball.getY())+ball.getHeight())/2-((2*player2.getY())+player2.getHeight())/2)/5;
		}
		else if(ball.getX()<=player1.getX()+player1.getWidth()&&ball.getX()+ball.getWidth()>=player1.getX()&&ball.getY()+ball.getHeight()>=player1.getY()&&ball.getY()<=player1.getY()+player1.getHeight()) {
			ballXSpeed=5;
			ballYSpeed=(((2*ball.getY())+ball.getHeight())/2-((2*player1.getY())+player1.getHeight())/2)/5;
		}
		if(started==true) {
			ball.setX(ball.getX()+ballXSpeed);
			ball.setY(ball.getY()+ballYSpeed);
		}
		//player1 movement
		if(queue1.size()>0) {
			if(queue1.get(0)=="up"&&player1.getY()>0) {
				currentDirection1="up";
				player1.setY(player1.getY()-playerSpeed);
			
			}
			else if(queue1.get(0)=="down"&&player1.getY()+player1.getHeight()<615) {
				currentDirection1="down";
				player1.setY(player1.getY()+playerSpeed);
				
			}
		}
		else if(currentDirection1=="up"&&player1.getY()>0) {
			player1.setY(player1.getY()-playerSpeed);
		}
		else if(currentDirection1=="down"&&player1.getY()+player1.getHeight()<615) {
			player1.setY(player1.getY()+playerSpeed);
		}
		//player2
		if(queue2.size()>0) {
			if(queue2.get(0)=="up"&&player2.getY()>0) {
				currentDirection2="up";
				player2.setY(player2.getY()-playerSpeed);
				//queue1.remove(queue1.get(0));
			}
			else if(queue2.get(0)=="down"&&player2.getY()+player2.getHeight()<615) {
				currentDirection2="down";
				player2.setY(player2.getY()+playerSpeed);
				//queue1.remove(queue1.get(0));
			}
		}
		else if(currentDirection2=="up"&&player2.getY()>0) {
			player2.setY(player2.getY()-playerSpeed);
		}
		else if(currentDirection2=="down"&&player2.getY()+player2.getHeight()<615) {
			player2.setY(player2.getY()+playerSpeed);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		move();
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar()=='w'&&(queue1.size()==0||queue1.get(queue1.size()-1)=="down")&&player1.getY()>0) {
			queue1.add("up");
		}
		else if(e.getKeyChar()=='s'&&(queue1.size()==0||queue1.get(queue1.size()-1)=="up")&&player1.getY()+player1.getHeight()<615) {
			queue1.add("down");
		}
		if(e.getKeyCode()==KeyEvent.VK_UP&&(queue2.size()==0||queue2.get(queue2.size()-1)=="down")&&player2.getY()>0) {
			queue2.add("up");
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN&&(queue2.size()==0||queue2.get(queue2.size()-1)=="up")&&player2.getY()+player2.getHeight()<615) {
			queue2.add("down");
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE&&started==false) {
			startRound();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar()=='w'&&queue1.size()>0) {
			queue1.remove(queue1.get(0));
			currentDirection1="";
		}
		else if(e.getKeyChar()=='s'&&queue1.size()>0) {
			queue1.remove(queue1.get(0));
			currentDirection1="";
		}
		if(e.getKeyCode()==KeyEvent.VK_UP&&queue2.size()>0) {
		//	System.out.println("it kinda works");
			queue2.remove(queue2.get(0));
			currentDirection2="";
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN&&queue2.size()>0) {
		//	System.out.println("it works");
			queue2.remove(queue2.get(0));
			currentDirection2="";
		}
	}
}
