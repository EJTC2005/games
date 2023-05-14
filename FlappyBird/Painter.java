import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Painter extends JPanel implements ActionListener, KeyListener{
	Timer t = new Timer(1,this);
	int y = 100;
	int x = 100;
	int speed =4;
	Random r = new Random();
	int highScore =0;
	int ySpeed = 1;
	int numbers[] = new int[500];
	Pipe[] pipes = new Pipe[500];
	Pipe[] downPipes = new Pipe[500];
	int ini =1;
	boolean potato =true;
	
	int score =0;
	boolean ded = false;
	//JLabel label = new JLabel();
	JLabel scores = new JLabel();
	//Pipe pipe = new Pipe(600, 0, 100, 225);
	JPanel stuff = new JPanel();
	int [] los = new int[500];
	
	public Painter() {
		
	
		scores.setForeground(Color.WHITE);
		scores.setFont(new Font("Ink Free", Font.BOLD, 75));
		scores.setOpaque(true);
		scores.setBackground(Color.CYAN);
		stuff.add(scores);
		add(stuff);
		
		t.start();
		//setBackground(Color.cyan);
		for(int i = 0; i<500;i++) {
			numbers[i]=r.nextInt(3);
			pipes[i]= new Pipe(600+i*400,0, 100, 100+numbers[i]*50);
			downPipes[i] = new Pipe(pipes[i].x, pipes[i].y+pipes[i].length+150, pipes[i].width, 600-pipes[i].length);
	
			
			los[i] = 4;
		}
		    
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.cyan);
		g.setColor(Color.yellow);
		g.fillOval(x,y,40,40);
		g.setColor(Color.green);
	//	g.fillRect(pipe.x, pipe.y, pipe.width, pipe.length);
	//	g.fillRect(pipe.x, pipe.y+350 , pipe.width, pipe.length);
		
		for(int i = 0; i<500; i++) {
			g.fillRect(pipes[i].x, pipes[i].y, pipes[i].width, pipes[i].length);
			//g.fillRect(pipes[i].x, pipes[i].y+pipes[i].length+125, pipes[i].width, 1000-pipes[i].length);
			g.fillRect(downPipes[i].x, downPipes[i].y, downPipes[i].width, downPipes[i].length);
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	//	System.out.println(y);
		
		scores.setText(score+":"+highScore);
			
			for(int i = 0; i<500; i++) {
				
				pipes[i].x-=speed;
				downPipes[i].x-=speed;
				
				
				if(x>pipes[i].x+pipes[i].width&&score<i+1) {
					score=i+1;
					//System.out.println(score);
					if(score>highScore) {
					highScore=score;
					}
				}
			}
			if(y<522) {
				y+=ySpeed;
				if(ySpeed!=6) {
					ySpeed+=1;
				}
				
			}
			else {
				y=522;
				ySpeed=0;
			    speed=0;
			    ded=true;
			}
			for(int i = 0; i<500; i++) {
				if(x+40>=pipes[i].x&&x<=pipes[i].x+pipes[i].width) {
					if(y>=pipes[i].y&&y<=pipes[i].y+pipes[i].length) {
						ySpeed=0;
					    speed=0;
					    ini=0;
					    ded=true;
					}
					else if(y+40>=downPipes[i].y&&y+40<=downPipes[i].y+downPipes[i].length) {
						ySpeed=0;
					    speed=0;
					    ini=0;
					    ded=true;
					}
				
				}
				
			}
		
		
		if(x<300&&y!=522) {
			x+=ini;
		}
		
		repaint();  
		 
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_SPACE&&ded==false&&y>=40) {
			ySpeed=-ySpeed;
			ySpeed-=6;
		}
		else if(ded==true && e.getKeyCode()==KeyEvent.VK_SPACE) {
			y = 100;
			speed =4;
			ySpeed = 1;
		    numbers = new int[500];
			pipes = new Pipe[500];
			downPipes = new Pipe[500];
			ini =1;
			ded = false;
			score=0;
			
			for(int i = 0; i<500;i++) {
				numbers[i]=r.nextInt(5);
				pipes[i]= new Pipe(600+i*400,0, 100, 125+numbers[i]*50);
				downPipes[i] = new Pipe(pipes[i].x, pipes[i].y+pipes[i].length+150, pipes[i].width, 1000-pipes[i].length);
			}
		}
		/*
		if(e.getKeyChar()=='r') {
			y = 100;
			speed =4;
			ySpeed = 1;
		    numbers = new int[500];
			pipes = new Pipe[500];
			downPipes = new Pipe[500];
			ini =1;
			ded = false;
			score=0;
			for(int i = 0; i<500;i++) {
				numbers[i]=r.nextInt(5);
				pipes[i]= new Pipe(600+i*400,0, 100, 125+numbers[i]*50);
				downPipes[i] = new Pipe(pipes[i].x, pipes[i].y+pipes[i].length+150, pipes[i].width, 1000-pipes[i].length);
			}
		}
		*/
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
