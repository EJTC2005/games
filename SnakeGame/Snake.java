package soup;

import javax.swing.JFrame;

public class Snake {
	//runner
	Painting pedro;
	JFrame f;
	public Snake() {
		f= new JFrame();
		pedro=new Painting();
		f.addKeyListener(pedro);
		f.add(pedro);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setSize(665,638);
		f.setResizable(false);
		f.setVisible(true);
	}
	public static void main(String[] args) {
		Snake s = new Snake();
		
	}
	
}
