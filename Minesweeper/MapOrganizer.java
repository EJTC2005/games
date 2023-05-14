package game;

import java.util.Random;

public class MapOrganizer {
	int x;
	int y;
	boolean b;
	boolean sp = true;

	
	Random r = new Random();
	public void startingPortion(Cell[][] cells, int x, int y) {
		for(int i = x-1; i<=x+1 ; i++ ) {
			for(int j = y-1; j<=y+1 ; j++ ) {
				try {
				cells[i][j].bomb=false;
				cells[i][j].bombable=false;
				cells[i][j].revealed=true;
				}
				catch(Exception e) {
					
				}
			}
		}
	}
	public void setBombs(Cell[][] cells) {
		for(int i=0; i<20; i++) {
			x=r.nextInt(10);
			y=r.nextInt(16);
			if(cells[x][y].bomb==false&&cells[x][y].bombable==true) {
			cells[x][y].bomb=true;
			}
			else{
				reroll(cells);
			}
		}
	}
	public void reroll(Cell[][] cells) {
		x=r.nextInt(10);
		y=r.nextInt(16);
		if(cells[x][y].bomb==false&&cells[x][y].bombable==true){
		cells[x][y].bomb=true;
		
		}
		else{
			reroll(cells);
		}
	}
	public void countBombs(Cell[][] cells, int x, int y) {
		if(cells[x][y].finish==false) {
		cells[x][y].bombCount=0;
		for(int i = x-1; i<=x+1 ; i++ ) {
			for(int j = y-1; j<=y+1 ; j++ ) {
				try {
					if(cells[i][j].bomb==true) {
						cells[x][y].bombCount+=1;
					}
				}
				catch(Exception e) {
				}
			}
		}
		
		cells[x][y].s = Integer.toString(cells[x][y].bombCount);
		cells[x][y].finish=true;
		}
	}
}


