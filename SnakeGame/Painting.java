package soup;

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

public class Painting extends JPanel implements ActionListener, KeyListener  {
	//game panel
	//Timer
	Timer t = new Timer(100, this);
	boolean moved = false;
	
	//Segments
	//Board pieces
	Segment[][] grid = new Segment[12][13];
	//Head
	
	
	//int
	int x;
	int y;
	int x2;
	int y2;
//	Segment s =new Segment(200,250,50,50, true);
	
	//Body
	ArrayList<Segment> snake = new ArrayList<Segment>();
	
//	ArrayList <Segment> body = new ArrayList<Segment>();
	
	//Movement
//	boolean start =true;
	boolean moving =false;
	//String direction = "";
	ArrayList <String> queue = new ArrayList<String>();
	ArrayList <Segment> appleSpots = new ArrayList<>();
	boolean ded =false;
	
	//Apples
	Apple apple = new Apple(450,250,50,50);
	Random r = new Random();
	int applesEaten = 0;
	
	//Score
	JLabel label=new JLabel();
	int highScore = 0;
	
	//Panel constructor
	public Painting() {
		t.start();
		
		//gameboard
		for(int i =0; i<12; i++) {
			for(int j=0; j<13; j++) {
				grid[i][j]=new Segment(j*50,i*50,50,50,false);
				if(i==5&&j<5) {
					grid[i][j].snakeOrNot=true;
					snake.add(0, grid[i][j]);
					x=j;
					y=i;
					//System.out.println(grid[i][j].x+":"+grid[i][j].y);
				}
			}
		}
		
		//snake parts
		
		/*
		for(int i = 0; i<4; i++) {
			body.add(new Segment(200-((i+1)*50),s.y,50,50, true));
		}
		*/
		label.setFont(new Font("Times New Roman", Font.BOLD, 50));
		label.setForeground(Color.BLUE);
		add(label);
	}
	
	//Painter
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.setColor(Color.ORANGE);
		g.setColor(Color.green);
		g.fillOval(apple.x, apple.y, apple.width, apple.height);
	
		for(int i = 0; i<snake.size(); i++) {
			if(i==0) {
				g.setColor(Color.blue);
				g.fillRect(snake.get(i).x, snake.get(i).y, 50, 50);
			}
			else {
			g.setColor(Color.CYAN);
			g.fillRect(snake.get(i).x, snake.get(i).y, 50, 50);
			}
		}
		
		g.setColor(Color.BLACK);
		for(int i =0; i<13; i++) {
		g.drawLine(i*50, 0, i*50, 650);
		g.drawLine(0, i*50, 650, i*50);
		}
		
	}
	//game loop
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		label.setText(applesEaten+":"+highScore);
		//System.out.println();
		move();
		checkCollisions();
		eat();
		
		
		if(ded==false) {
		repaint();
		}
	}
	//eat
	public void eat() {
		
		if(snake.get(0).x==apple.x&&snake.get(0).y==apple.y) {
			
			
			Segment j =new Segment(snake.get(snake.size()-1).x,snake.get(snake.size()-1).y,50,50, true);
			snake.add(j);
			appleSpots.clear();
			for(int i=0; i<12;i++) {
				for(int k=0; k<13;k++) {
					if(grid[i][k].snakeOrNot==false) {
						appleSpots.add(grid[i][k]);
					}
				}
			}
			int soup = r.nextInt(appleSpots.size());
			
			apple = new Apple(appleSpots.get(soup).x,appleSpots.get(soup).y,50,50);
			//System.out.println(appleSpots.get(soup).x+":"+appleSpots.get(soup).y);
			applesEaten++;
			if(applesEaten>highScore) {
				highScore++;
			}
		}
		
	}
	//collisions
	public void checkCollisions() {
		
		for(int i = 0; i< snake.size(); i++) {
			if(i!=0&&snake.get(0).x==snake.get(i).x&&snake.get(0).y==snake.get(i).y||snake.get(0).x==650||snake.get(0).x==-50||snake.get(0).y==-50||snake.get(0).y==600) {
				ded=true;
				moving=false;
				//direction="";
				queue.clear();
			}
		}
	
	}
	//movement
	public void move() {
		if(moving&&ded==false) {
			moved=true;
		//	if(queue.size()>=1) {
				//direction=queue.get(0);
				if(queue.get(0).equals("down")) {
					if(y+1!=12) {
					grid[y+1][x].setSnake(true);
					snake.add(0, grid[y+1][x]);
					grid[snake.get((snake.size()-1)).y/50][snake.get((snake.size()-1)).x/50].snakeOrNot=false;
					snake.remove((snake.size()-1));
					y=y+1;
						if(queue.size()>1) {
							queue.remove(0);
						}
					}
					else {
						ded=true;
					}
				}
				else if(queue.get(0).equals("up")/*&&snake.get(0).y!=0*/) {
					if(y-1!=-1) {
					grid[y-1][x].setSnake(true);
					snake.add(0, grid[y-1][x]);
					grid[snake.get((snake.size()-1)).y/50][snake.get((snake.size()-1)).x/50].snakeOrNot=false;
					snake.remove((snake.size()-1));
					y=y-1;
						if(queue.size()>1) {
							queue.remove(0);
						}
					}
					else {
						ded=true;
					}
				}
				else if(queue.get(0).equals("right")) {
					if(x+1!=13) {
					grid[y][x+1].setSnake(true);
					snake.add(0, grid[y][x+1]);
					grid[snake.get((snake.size()-1)).y/50][snake.get((snake.size()-1)).x/50].snakeOrNot=false;
					snake.remove((snake.size()-1));
					x=x+1;
						if(queue.size()>1) {
							queue.remove(0);
						}
					}
					else {
						ded=true;
					}
				}
				else if(queue.get(0).equals("left")) {
					if(x-1!=-1) {
					grid[y][x-1].setSnake(true);
					snake.add(0, grid[y][x-1]);
					grid[snake.get((snake.size()-1)).y/50][snake.get((snake.size()-1)).x/50].snakeOrNot=false;
					snake.remove((snake.size()-1));
					x=x-1;
						if(queue.size()>1) {
							queue.remove(0);
						}
					}
					else {
						ded=true;
					}
				}	
		//	}
			/*
			else if(queue.size()==0) {
				if(direction.equals("down")) {
					
					if(y+1!=12) {
					grid[y+1][x].setSnake(true);
					snake.add(0, grid[y+1][x]);
					grid[snake.get((snake.size()-1)).y/50][snake.get((snake.size()-1)).x/50].snakeOrNot=false;
					snake.remove((snake.size()-1));
					y=y+1;
					}
					else {
						ded=true;
					}
				}
				if(direction.equals("up")) {
					if(y-1!=-1) {
						grid[y-1][x].setSnake(true);
						snake.add(0, grid[y-1][x]);
						grid[snake.get((snake.size()-1)).y/50][snake.get((snake.size()-1)).x/50].snakeOrNot=false;
						snake.remove((snake.size()-1));
						y=y-1;
					}
					else {
						ded=true;
					}
					
				}
				if(direction.equals("right")) {
					if(x+1!=13) {
					grid[y][x+1].setSnake(true);
					snake.add(0, grid[y][x+1]);
					grid[snake.get((snake.size()-1)).y/50][snake.get((snake.size()-1)).x/50].snakeOrNot=false;
					snake.remove((snake.size()-1));
					x=x+1;
					}
					else {
						ded=true;
					}
				}
				if(direction.equals("left")) {
					
					if(x-1!=-1) {
					grid[y][x-1].setSnake(true);
					snake.add(0, grid[y][x-1]);
					grid[snake.get((snake.size()-1)).y/50][snake.get((snake.size()-1)).x/50].snakeOrNot=false;
					snake.remove((snake.size()-1));
					x=x-1;
					}
					else {
						ded=true;
					}
				}
			}
			*/
		}	
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
		// TODO Auto-generated method stub
		
		
	}
	//Key Inputs
	@Override
	public void keyPressed(KeyEvent e) {
		
		// TODO Auto-generated method stub
		if(queue.size()>1) {
			if((e.getKeyCode()==KeyEvent.VK_UP||e.getKeyChar()=='w')&&queue.get(queue.size()-1)!="down"&&queue.get(queue.size()-1)!="up") {
				queue.add("up");
				moving=true;
				moved=false;
			}
			else if((e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyChar()=='s')&&queue.get(queue.size()-1)!="up"&&queue.get(queue.size()-1)!="down") {
				queue.add("down");
				moving=true;
				moved=false;
			}
			else if((e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyChar()=='d')&&queue.get(queue.size()-1)!="left"&&queue.get(queue.size()-1)!="right") {
				queue.add("right");
				moving=true;
				moved=false;
			}	
			else if((e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyChar()=='a')&&queue.get(queue.size()-1)!="right"&&queue.get(queue.size()-1)!="left") {
				queue.add("left");
				moving=true;
				moved=false;
			}
			
		}
		else if(queue.size()==1) {
			if((e.getKeyCode()==KeyEvent.VK_UP||e.getKeyChar()=='w')&&queue.get(queue.size()-1)!="down"&&queue.get(queue.size()-1)!="up") {
				queue.add("up");
				if(moved) {
					queue.remove(0);
				}
				
				moving=true;
				moved=false;
				
			}
			else if((e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyChar()=='s')&&queue.get(queue.size()-1)!="up"&&queue.get(queue.size()-1)!="down") {
				queue.add("down");
				if(moved) {
					queue.remove(0);
				}
				
				moving=true;
				moved=false;
			}
			else if((e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyChar()=='d')&&queue.get(queue.size()-1)!="left"&&queue.get(queue.size()-1)!="right") {
				queue.add("right");
				if(moved) {
					queue.remove(0);
				}
				
				moving=true;
				moved=false;
			}	
			else if((e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyChar()=='a')&&queue.get(queue.size()-1)!="right"&&queue.get(queue.size()-1)!="left") {
				queue.add("left");
				if(moved) {
					queue.remove(0);
				}
				
				moving=true;
				moved=false;
			}
			
		}
		
		else if(queue.size()==0) {
			if((e.getKeyCode()==KeyEvent.VK_UP||e.getKeyChar()=='w')/*&&direction!="down"&&direction!="up"*/) {
				queue.add("up");
				moving=true;
				moved=false;
			}
			else if((e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyChar()=='s')/*&&direction!="up"&&direction!="down"*/) {
				queue.add("down");
				moving=true;
				moved=false;
			}
			else if((e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyChar()=='d')/*&&direction!="left"&&direction!="right"*/) {
				queue.add("right");
				moving=true;
				moved=false;
			}	
			else if(moving==true&&(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyChar()=='a')/*&&direction!="right"&&direction!="left"*/) {
				queue.add("left");
				moving=true;
				moved=false;
			}
			
		}
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			ded=true;
			queue.clear();
			snake.clear();
			for(int i =0; i<12; i++) {
				for(int j=0; j<13; j++) {
					if(i==5&&j<5) {
						grid[i][j].snakeOrNot=true;
						snake.add(0, grid[i][j]);
						x=j;
						y=i;
				
			}
				}
			}
			moving=false;
		//	start=true;
			for(int i = 0; i<4; i++) {
			}
			apple = new Apple(450,250,50,50);
			ded=false;
			applesEaten=0;
		//	direction="";
		}
		
	}
}
