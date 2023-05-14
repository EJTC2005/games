package Geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Art extends JPanel implements ActionListener, KeyListener {
	Square[][] squares;
	int snakeSize;
	Timer timer;
	int appleX;
	int appleY;
	int size;
	int headX;
	int headY;
	int tailX;
	int tailY;
	int ghostX;
	int ghostY;
	int direction;
	Random r;
	int random;
	int stop = -1;
	int right = 5;
	int left = -5;
	int down = 10;
	int up = -10;
	ArrayList<Integer> directions;
	ArrayList<Square> snake;
	Square head;
	Square tail;
	Square nub;
	ArrayList<Integer> heights;
	ArrayList<Integer> lengths;
	ArrayList<Square> deadSnake;
	ArrayList<Integer> originalPath;
	boolean dead = false;
	int numLefts = 0;
	int numRights = 0;
	int numUps = 0;
	int numDowns = 0;
//	boolean pathGenerated = false;
	int index = 0;
	int time = 0;
	public Art() {
		squares = new Square[Cubos.y][Cubos.x];
		for(int i = 0; i<Cubos.y; i++) {
			for(int j = 0; j<Cubos.x; j++) {
				squares[i][j] = new Square(j,i);
			}
		}
		snakeSize = 1;
		timer = new Timer(1, this);
		size = Cubos.size;
		r = new Random();
		random = r.nextInt(Cubos.x*Cubos.y-snakeSize);
		snake = new ArrayList();
		directions = new ArrayList();
		headY=4;
		headX=0;
		snake.add(squares[headY][headX]);
		originalPath = new ArrayList();
		//new path
		
		for(int i = 0; i<9; i++) {
			originalPath.add(right);
		}
		originalPath.add(down);
		for(int i = 0; i<9; i++) {
			originalPath.add(left);
		}
		originalPath.add(up);
		
		// old path
		/*
		for(int i = 0; i < 4; i++) {
			//up
			for(int j = 0; j < 4; j++) {
				path.add(up);
			}
			path.add(right);
			for(int j = 0; j < 4; j++) {
				path.add(down);
			}
			path.add(right);
		}
		for(int j = 0; j < 4; j++) {
			path.add(up);
		}
		path.add(right);
		for(int j = 0; j < 9; j++) {
			path.add(down);
		}
		for(int i = 0; i < 4; i++) {
			//up
			path.add(left);
			for(int j = 0; j < 4; j++) {
				path.add(up);
			}
			path.add(left);
			for(int j = 0; j < 4; j++) {
				path.add(down);
			}
			//path.add(right);
		}
		path.add(left);
		for(int j = 0; j < 5; j++) {
			path.add(up);
		}
		*/
		
	//	orientations = new ArrayList<Integer>();
		direction=up;
		spawnApple();
	}
	public void paintComponent(Graphics g) {
		//Initializing squares
		//length
		int base = (Cubos.x)*Cubos.size;
		//width
		int height = (Cubos.y)*Cubos.size;
		//center
		super.paintComponent(g);
		//starting apple
		//starting snake
		g.setColor(Color.cyan);
		for(int i = 0; i<snake.size(); i++) {
			g.fillRect(snake.get(i).x*size,snake.get(i).y*size, size, size);
		}
		//arrows
		g.setColor(Color.red);
		if(snake.size()>2) {
			for(int i = snake.size()-1; i>0; i--) {
				if(snake.get(i).orientation==right) {
					g.drawLine(snake.get(i).x*size+12,snake.get(i).y*size+12, snake.get(i).x*size+12, snake.get(i).y*size+38);
					g.drawLine(snake.get(i).x*size+12, snake.get(i).y*size+12, snake.get(i).x*size+38, snake.get(i).y*size+25);
					g.drawLine(snake.get(i).x*size+12, snake.get(i).y*size+38, snake.get(i).x*size+38, snake.get(i).y*size+25);
				}
				else if(snake.get(i).orientation==left) {
					g.drawLine(snake.get(i).x*size+38,snake.get(i).y*size+12, snake.get(i).x*size+38, snake.get(i).y*size+38);
					g.drawLine(snake.get(i).x*size+12, snake.get(i).y*size+25, snake.get(i).x*size+38, snake.get(i).y*size+12);
					g.drawLine(snake.get(i).x*size+12, snake.get(i).y*size+25, snake.get(i).x*size+38, snake.get(i).y*size+38);
				}
				else if(snake.get(i).orientation==down) {
					g.drawLine(snake.get(i).x*size+12, snake.get(i).y*size+12, snake.get(i).x*size+38, snake.get(i).y*size+12);
					g.drawLine(snake.get(i).x*size+12, snake.get(i).y*size+12, snake.get(i).x*size+25, snake.get(i).y*size+38);
					g.drawLine(snake.get(i).x*size+38, snake.get(i).y*size+12, snake.get(i).x*size+25, snake.get(i).y*size+38);
				}
				else if(snake.get(i).orientation==up) {
					g.drawLine(snake.get(i).x*size+12, snake.get(i).y*size+38, snake.get(i).x*size+38, snake.get(i).y*size+38);
					g.drawLine(snake.get(i).x*size+12, snake.get(i).y*size+38, snake.get(i).x*size+25, snake.get(i).y*size+12);
					g.drawLine(snake.get(i).x*size+38, snake.get(i).y*size+38, snake.get(i).x*size+25, snake.get(i).y*size+12);
				}
				if(i==snake.size()-1) {
					g.drawRect(snake.get(i).x*size+12, snake.get(i).y*size+12, 26, 26);
				}
			}
		}
		//head
		g.setColor(Color.BLUE);
		g.fillRect(snake.get(0).x*size,snake.get(0).y*size, size, size);
		//fruit
		g.setColor(Color.magenta);
		g.fillOval(appleX*size, appleY*size, size, size);
		//grid
		g.setColor(Color.black);
		for(int i =0; i<Cubos.x; i++) {
			g.drawLine(0, i*size, base, i*size);
			g.drawLine(i*size, 0, i*size, height);
		}
		timer.start();
	}
	public void spawnApple() {
		int limit = 0;
		for(int i = 0; i<Cubos.y; i++) {
			for(int j = 0; j<Cubos.x; j++) {
				if(squares[i][j].type==Square.cell) {
					limit--;
				}
				if(limit==random) {
					appleY=i;
					appleX=j;
					return;
				}
				limit++;
			}
		}
		return;
	}
	public Square left(Square cell){
		if(cell==null||cell.x==0) 
			return null;
		return squares[cell.y][cell.x-1];
	}
	public Square right(Square cell){
		if(cell==null||cell.x==9) 
			return null;
		return squares[cell.y][cell.x+1];
	}
	public Square up(Square cell){
		if(cell==null||cell.y==0) 
			return null;
		return squares[cell.y-1][cell.x];
	}
	public Square down(Square cell){
		if(cell==null||cell.y==9) 
			return null;
		return squares[cell.y+1][cell.x];
	}
	public Square nearestLeft(Square cell) {
		for(int i = cell.x; i>0; i--) {
			if(squares[cell.y][i-1].type==Square.cell) {
				return squares[cell.y][i-1];
			}
		}
		return null;
	}
	public Square nearestRight(Square cell) {
		for(int i = cell.x; i<9; i++) {
			if(squares[cell.y][i+1].type==Square.cell) {
				return squares[cell.y][i+1];
			}
		}
		return null;
	}
	public Square nearestUp(Square cell) {
		for(int i = cell.y; i>0; i--) {
			if(squares[i-1][cell.x].type==Square.cell) {
				return squares[i-1][cell.x];
			}
		}
		return null;
	}
	public Square nearestDown(Square cell) {
		for(int i = cell.y; i<9; i++) {
			if(squares[i+1][cell.x].type==Square.cell) {
				return squares[i+1][cell.x];
			}
		}
		return null;
	}
	public Square target(Square cell, int direction) {
		Square target = null;
		if(direction==left)
			target = left(cell);
		else if(direction==right)
			target = right(cell);
		else if(direction==up)
			target = up(cell);
		else if(direction==down)
			target = down(cell);
		return target;
	}
	public boolean unsafe(Square cell, int direction) {
		Square target = target(cell, direction);
		if(target==null||(target.type==Square.cell&&target!=tail)||(direction==-this.direction))
			return true;
		return false;
	}
	public void generatePath() {
		/*
		*/
		/*
		if((((headY<appleY&&(headX==appleX||headX==appleX-1)&&headX%2!=0)&&unsafe(head,down)==false)||(unsafe(head,right)==true&&headX%2!=0&&headY<8))&&appleY<=4&&direction==right) {
			direction=down;
		}
		
		
		else if((((headY>appleY&&(headX==appleX||headX==appleX+1)&&headX%2==0)&&unsafe(head,up)==false)||(unsafe(head,left)==true&&headX%2==0&&headY>1))&&appleY>=5&&direction==left) {
			direction=up;
		}
		
		else if(headX%2!=0&&unsafe(head,right)==false&&direction==down) {
			direction=right;
		}
		else if(headX%2==0&&unsafe(head,left)==false&&direction==up) {
			direction=left;
		}
		else if(headX%2==0&&headY!=9&&unsafe(head,up)==false&&direction==left) {
			direction = up;
		}
		
		else if(headX%2!=0&&headY!=0&&unsafe(head,down)==false&&direction==right) {
			direction = down;
		}
		
		else if(headY==0&&headX<9&&unsafe(head,right)==false&&direction!=left) {
			direction=right;
		}
		else if(headY==9&&headX>0&&unsafe(head,left)==false&&direction!=right) {
			direction=left;
		}
		else if(headX==9&&headY==0&&unsafe(head,down)==false) {
			direction=down;
		}
		else if(headX==0&&headY==9&&unsafe(head,up)==false) {
			direction=up;
		}
		
		//fix this
		*/
		
		if(((headY>appleY&&(headX==appleX||headX==appleX-1)&&headX%2==0)&&unsafe(head,up)==false)||(unsafe(head,right)==true&&headX%2==0&&headY<=4)) {
			direction=up;
		//	System.out.println("up path");
		}
		else if(((headY<appleY&&(headX==appleX||headX==appleX+1)&&headX%2!=0)&&unsafe(head,down)==false)||(unsafe(head,left)==true&&headX%2!=0&&headY>=5)) {
			direction=down;
		//	System.out.println("down path");
		}
		//headY<4
		//headY==0
		else if(headX%2==0&&direction==up&&headY==0&&unsafe(head,right)==false) {
			direction=right;
		//	System.out.println("right reroute");
		}
		//headY>5
		//headY==9
		else if(headX%2!=0&&direction==down&&headY==9&&unsafe(head,left)==false) {
			direction=left;
		//	System.out.println("left reroute");
		}
		else if(headX%2!=0&&headY<4&&direction==right&&unsafe(head,down)==false) {
			direction=down;
		//	System.out.println("down return");
		}
		else if(headX%2==0&&headY>5&&direction==left&&unsafe(head,up)==false) {
			direction=up;
		//	System.out.println("up return");
		}
		else if(headY==4&&headX<9&&(direction!=left)&&unsafe(head,right)==false) {
			direction=right;
		//	System.out.println("og");
		}
		else if(headX==9&&headY==4&&direction!=up&&unsafe(head,down)==false) {
			direction=down;
		//	System.out.println("og");
		}
		else if(headY==5&&headX>0&&(direction!=right)&&unsafe(head,left)==false) {
			direction=left;
		//	System.out.println("og");
		}
		else if((headX==0&&headY==5&&direction!=down)&&unsafe(head,up)==false) {
			direction=up;
			//System.out.println("og");
		}
		
		/*
		if((headX==appleX||headX==appleX-1)&&appleY<headY&&headX%2==0&&originalPath.get(index)==right&&(nearestUp(head)==null)) {
			int distance = Math.abs(appleY-headY);
			if(distance%2!=0) {
				distance++;
			}
			for(int i = 0; i<distance; i++) {
				originalPath.add(index, up);
			}
			for(int i = 0; i<distance; i++) {
				originalPath.add(index+distance+1, down);
			}
			direction=originalPath.get(index);
		}	
		else if((headX==appleX||headX==appleX+1)&&appleY>headY&&headX%2!=0&&originalPath.get(index)==left&&(nearestDown(head)==null)) {
			int distance = Math.abs(appleY-headY);
			if(distance%2!=0) {
				distance++;
			}
			for(int i = 0; i<distance; i++) {
				originalPath.add(index, down);
			}
			for(int i = 0; i<distance; i++) {
				originalPath.add(index+distance+1, up);
			}
			direction=originalPath.get(index);
		}
		*/
		/*
		else if((headY==appleY||headY==appleY-1)&&appleX<headX&&headY%2!=0&&path.get(index)==up&&(nearestLeft(head)==null)) {
			int distance = Math.abs(appleX-headX);
			if(distance%2!=0) {
				distance++;
			}
			for(int i = 0; i<distance; i++) {
				path.add(index, left);
			}
			for(int i = 0; i<distance; i++) {
				path.add(index+distance+1, right);
			}
			direction=path.get(index);
		}	
		*/
		/*
		else if((headY==appleY||headY==appleY+1)&&appleX>headX&&headY%2==0&&path.get(index)==down&&(nearestRight(head)==null)) {
			int distance = Math.abs(appleX-headX);
			if(distance%2!=0) {
				distance++;
			}
			for(int i = 0; i<distance; i++) {
				path.add(index, right);
			}
			for(int i = 0; i<distance; i++) {
				path.add(index+distance+1, left);
			}
			direction=path.get(index);
		}
		*/
	}
	public void setNums() {
		numLefts=0;
		numRights=0;
		numUps=0;
		numDowns=0;
		for(int i = 1; i<snake.size(); i++) {
			if(snake.get(i).orientation==left) {
				numLefts++;
			}
			else if(snake.get(i).orientation==right) {
				numRights++;
			}
			else if(snake.get(i).orientation==up) {
				numUps++;
			}
			else if(snake.get(i).orientation==down) {
				numDowns++;
			}
		}
	}
	public void turn() {
		head = squares[headY][headX];
		if(snake.size()>1) {
			nub = snake.get(1);
		}
		tail = snake.get(snake.size()-1);
		tailX=tail.x;
		tailY=tail.y;
		if(index==originalPath.size()) {
			index = 0;
		}
		//direction = originalPath.get(index);
		generatePath();
		index++;
	}

	public void step() {
		ghostY=snake.get(snake.size()-1).y;
		ghostX=snake.get(snake.size()-1).x;
		if(direction==right) {
			headX+=1;
		}
		else if(direction==left) {
			headX-=1;
		}
		else if(direction==down) {
			headY+=1;
		}
		else if(direction==up) {
			headY-=1;
		}
		snake.get(snake.size()-1).type=Square.land;
		snake.remove(snake.get(snake.size()-1));
	//	if(headY>-1&&headY<10&&headX>-1&&headX<10) {
			snake.add(0, squares[headY][headX]);
			squares[headY][headX].type=Square.cell;
	//	}
		if(snake.size()>1) {
			if(direction==left) {
				head.orientation=left;
			}
			else if(direction==right) {
				head.orientation=right;
			}
			else if(direction==up) {
				head.orientation=up;
			}
			else if(direction==down) {
				head.orientation=down;
			}
		}
	}
	public void move() {
		if(direction!=stop) {
			setNums();
			turn();
			step();
			if(headX==appleX&&headY==appleY) {
				snakeSize++;
				snake.add(squares[ghostY][ghostX]);
				squares[ghostY][ghostX].type=Square.cell;
				random=r.nextInt(Cubos.x*Cubos.y-snakeSize);
				spawnApple();
				//pathGenerated=false;
			}
		}
	}
	public void collisions() {
		for(int i = 1; i<snake.size(); i++) {
			if(snake.get(0).x==snake.get(i).x&&snake.get(0).y==snake.get(i).y) {
				direction=stop;
			}
		}
	}
	public void reset() {
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		time++;
		// TODO Auto-generated method stuff
		if(snake.size()==99) {
			System.out.println(time);
		}
		deadSnake = new ArrayList<Square>();
		deadSnake.clear();
		for(int i = snake.size()-1; i>0; i--) {
			deadSnake.add(snake.get(i));
		}
		move();
		collisions();
		if(direction!=stop) {
			repaint();
			timer.stop();
		}
		
		else if(direction==stop&&dead==false) {
					numLefts=0;
					numRights=0;
					numUps=0;
					numDowns=0;
			for(int i = 0; i<deadSnake.size(); i++) {
				if(deadSnake.get(i).orientation==left) {
					System.out.println("left");
					numLefts++;
				}
				if(deadSnake.get(i).orientation==right) {
					System.out.println("right");
					numRights++;
				}
				if(deadSnake.get(i).orientation==up) {
					System.out.println("up");
					numUps++;
				}
				if(deadSnake.get(i).orientation==down) {
					System.out.println("down");
					numDowns++;
				}
			}
			dead=true;
			/*
			System.out.println("left:"+numLefts);
			System.out.println("right:"+numRights);
			System.out.println("up:"+numUps);
			System.out.println("down:"+numDowns);
			*/
			System.out.println(time);
		}

		/*
		if(snake.size()==6&&direction!=stop) {
			direction=stop;
			
		}
		*/
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(KeyEvent.VK_SPACE)) {
			reset();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
