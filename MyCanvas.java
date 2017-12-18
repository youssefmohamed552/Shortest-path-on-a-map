/*
 * Name: Youssef Hussein
 * the canvas on with the drawing happens
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JComponent;

public class MyCanvas extends JComponent{
	Graph graph = new Graph();
	String input = "";
	String from = "";
	String to = "";
	String show = "";
	String direction = "";
	int args;
	public MyCanvas(String[] args) {
		this.args = args.length;
		if(args.length < 2)System.out.println("Please input at least 2 the input and the --show ");
//		System.out.println(args[0].length()+" "+args[0].substring(args[0].length()-4));
		if(args[0].length() < 5 || !args[0].substring(args[0].length()-4).equals(".txt")) System.out.println("please input a .txt file");
		this.input = args[0];
		if(args[1].equals("--show")) {
			show = args[1];
			if(args.length == 5) {
				direction = args[2];
				from = args[3];
				to = args[4];
			}
		}
		else if(args[1].equals("--directions")) {
			direction = args[1];
			from = args[2];
			to = args[3];
		}
		
		
//		System.out.println("input: "+input+" - show: "+ show+" - directions: "+direction+ " - from: "+ from+ " - to: "+ to);
		
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			graph.readTheFileInput(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(args > 2 &&(!graph.vertex.containsKey(from) || !graph.vertex.containsKey(to))) {
			System.out.println("You have the wrong locations");
			return;
		}
		
		graph.drawGraph(g); //
//		graph.printAdjList();
		if(direction.equals(""))return;
		graph.makeTable();
		if(graph.getShortestPath(from, to) == null) {
			System.out.println("these two nodes are not connected");
			return;
		}
		graph.drawShortestPath(g);
		graph.getTheIntersections();
		System.out.println("This is the total distance covered "+ graph.getTheTravelDistance() + " Miles");

	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1000, 600);
	}
}
