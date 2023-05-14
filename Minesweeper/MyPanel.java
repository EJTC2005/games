package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class MyPanel extends JPanel implements ActionListener{
	JButton[][] button;
	JButton flagButton;
	Cell[][] cells;
	JPanel pan;
	boolean flagging;
	JButton flagger;
	JButton resetButton;
	JLabel text = new JLabel();
	int cellsRevealed = 0;
	MapOrganizer mapOrganizer = new MapOrganizer();
	Color color = new Color(255,225,0);
	Color gren = new Color(0,200,0);
	Color land = new Color(117,243,54);
	public MyPanel() {
		button = new JButton[10][16];
		cells = new Cell[10][16];
		for(int i = 0; i<10;i++) {
			for(int j = 0; j<16; j++) {
				cells[i][j] = new Cell();
				button[i][j] = new JButton();
				button[i][j].addActionListener(this);

				add(button[i][j]);
			}
		}
		
		text.setForeground(color.red);
		text.setFont(new Font("Ink Free",Font.BOLD,25));
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setText("MINESWEEPER");
		
		
		flagging=false;
		flagger = new JButton("|>");
		flagger.addActionListener(this);
		
		resetButton = new JButton("R");
		resetButton.addActionListener(this);
		
		pan = new JPanel();
		pan.add(flagger);
		pan.add(text);
		pan.add(resetButton);
		setLayout(new GridLayout(10,16));
		
	}
	public void reveal(Cell[][] cells, int x, int y) {
		mapOrganizer.countBombs(cells, x, y);
		if(cells[x][y].bombCount!=0&&cells[x][y].revealable==true) {
			
			cells[x][y].revealable=false;
			cells[x][y].revealed=true;
			
			button[x][y].setText(cells[x][y].s);
			button[x][y].setBackground(land);
			if(cells[x][y].bombCount==1) {
				button[x][y].setForeground(color.blue);
			}
			if(cells[x][y].bombCount==2) {
				button[x][y].setForeground(color.gray);
			}
			if(cells[x][y].bombCount==3) {
				button[x][y].setForeground(color.red);
			}
			if(cells[x][y].bombCount==4) {
				button[x][y].setForeground(color.orange);
			}
			if(cells[x][y].bombCount==5) {
				button[x][y].setForeground(color.MAGENTA);
			}
		}
		if(cells[x][y].bombCount==0&&cells[x][y].revealable==true) {
			for(int i = x-1; i<=x+1 ; i++ ) {
				for(int j = y-1; j<=y+1 ; j++ ) {
					try {
						if(cells[i][j].flag==false) {
						button[i][j].setBackground(color);
						mapOrganizer.countBombs(cells, i, j);
						cells[i][j].revealed=true;
						cells[x][y].revealable=false;
						button[i][j].setBackground(land);
							if(cells[i][j].bombCount!=0) {
								button[i][j].setText(cells[i][j].s);
								
								if(cells[i][j].bombCount==1) {
									button[i][j].setForeground(color.blue);
									
								}
								if(cells[i][j].bombCount==2) {
									button[i][j].setForeground(color.GRAY);
								}
								if(cells[i][j].bombCount==3) {
									button[i][j].setForeground(color.red);
								}	
								if(cells[i][j].bombCount==4) {
									button[i][j].setForeground(color.orange);
								}
								if(cells[i][j].bombCount==5) {
									button[i][j].setForeground(color.MAGENTA);
								}
							}
						}
					}
					catch(Exception e){
					}
					
				}
			}
	
			try {
				if(cells[x-1][y-1].bombCount==0&&cells[x-1][y-1].revealable==true) {
					reveal(cells, x-1,y-1 );
				}
			}
			catch(Exception e) {
			}
			try {
				if(cells[x-1][y].bombCount==0&&cells[x-1][y].revealable==true) {
					reveal(cells, x-1,y );
				}
			}
			catch(Exception e) {
			}
			try {
				if(cells[x-1][y+1].bombCount==0&&cells[x-1][y+1].revealable==true) {
					reveal(cells, x-1,y+1 );
				}
			}
			catch(Exception e) {
			}
			try {
				if(cells[x][y-1].bombCount==0&&cells[x][y-1].revealable==true) {
					reveal(cells, x,y-1 );
				}
			}
			catch(Exception e) {
			}
			try {
				if(cells[x][y+1].bombCount==0&&cells[x][y+1].revealable==true) {
					reveal(cells, x,y+1 );
				}
			}
			catch(Exception e) {
			}
			try {
				if(cells[x+1][y-1].bombCount==0&&cells[x+1][y-1].revealable==true) {
					reveal(cells, x+1,y-1 );
				}
			}
			catch(Exception e) {
			}
			try {
				if(cells[x+1][y].bombCount==0&&cells[x+1][y].revealable==true) {
					reveal(cells, x+1,y );
				}
			}
			catch(Exception e) {
			}
			try {
				if(cells[x+1][y+1].bombCount==0&&cells[x+1][y+1].revealable==true) {
					reveal(cells, x+1,y+1 );
				}
			}
			catch(Exception e) {
			}
			
		}
	}
			
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==flagger&&flagging==false) {
			flagging=true;
			flagger.setForeground(color.red);
		}
		else if(e.getSource()==flagger&&flagging==true) {
			flagging = false;
			flagger.setForeground(color.black);
		}
		for(int i = 0; i<10; i++) {
			for(int j = 0; j<16; j++) { 
				
				if(e.getSource()==resetButton) {
				button[i][j].setText("");
				button[i][j].setForeground(color.black);
				button[i][j].setBackground(new JButton().getBackground());
				cells[i][j] = new Cell();
				mapOrganizer.sp=true;
				text.setText("MINESWEEPER");
				}
				
				if(e.getSource()==button[i][j]&&flagging==true&&button[i][j].getText().equals("|>")) {
					button[i][j].setText("");
					cells[i][j].flag=false;
				}
				else if(e.getSource()==button[i][j]&&flagging==true&&cells[i][j].revealed==false) {
					button[i][j].setForeground(color.red);
					button[i][j].setText("|>");
					cells[i][j].flag=true;
				}
			
				
				if(e.getSource()==button[i][j]&&mapOrganizer.sp==false&&flagging==false&&cells[i][j].flag==false) {
					if(cells[i][j].bomb==true&&cells[i][j].flag==false) {
						for(int k = 0; k<10; k++) {
							for(int l = 0; l<16; l++) {
								if(cells[k][l].bomb==true) {
								button[k][l].setForeground(color.black);
								button[k][l].setBackground(color.red);
								button[k][l].setText("X");
								text.setText("YOU LOSE!");
								}
							}
						}
						
						
					}
					
					if(cells[i][j].bomb==false&&button[i][j].getText()=="") {						
						reveal(cells,i,j);
						cellsRevealed=0;
						for(int k =0; k<10;k++) {
							for(int l= 0; l<16; l++) {
								if(button[k][l].getBackground()==color) {
									
									cellsRevealed++;
								}
							}
						}
						if(cellsRevealed==140) {
							text.setText("YOU WIN!");
						}
					}
				}
				
				else if(e.getSource()==button[i][j]&&mapOrganizer.sp==true&&flagging==false) {
					mapOrganizer.startingPortion(cells, i, j);
					mapOrganizer.sp=false;
					mapOrganizer.setBombs(cells);
					if(cells[i][j].flag==false) {
					reveal(cells,i,j);
					}
				}
			}
		}
	}
	
}
