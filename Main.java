/*
 * Name: Youssef Hussein
 * main program, the fram of the graph
 */


import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main{
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Street Mapping");
		
		frame.add(new MyCanvas(args));
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		
	}
	
	
}
