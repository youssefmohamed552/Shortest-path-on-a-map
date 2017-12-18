/*
 * Name: Youssef Hussein
 * the graph functions
 */

import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph implements Comparator<String[]>{
	 Map<String,Edge> edge = new HashMap<>();
	 Map<String,Node> vertex = new HashMap<>();
	 Map<String,LinkedList<String>> adjList = new HashMap<>();
	private static final int EOF = -1;           //the end of file constant
	public static final int NL = 10;			 //the New Line constant
	public static final int SP = 32;			//the Space Constant
	public static final int CR = 13;
	double east = 0;
	double west = 0;
	double north = 0;
	double south  = 0;
	private Map<Integer,String> encode = new HashMap<>();
	private Map<String,Integer> decode = new HashMap<>();
	private Map<String, String> findEdge = new HashMap<>();
	private PriorityQueue<String[]> pque;
	private Map<Integer , Boolean> visited = new HashMap<>();
	private String[][] table;
	private int NumOfVertex = 0;
	private ArrayList<Node> list;
//	private ArrayList<Node> listEdge;
	
	public Graph() {
	}
	
	
	
	public void readTheFileInput(String inputFile) throws IOException {
		InputStream input = new FileInputStream(new File(inputFile));
		
		boolean first = true;
		
		
		while(true) {
			String str = "";
			byte b;
			while(true) {
				b = (byte) input.read();
				if(b == EOF || b == NL)break;
				if(b != CR)str += (char)b;
			}
			String[] strArr = str.split("\\s+");
			switch(strArr[0]) {
			case "i":
				if(vertex.get(strArr[1]) == null) vertex.put(strArr[1], new Node(strArr[1],Double.parseDouble(strArr[2]),Double.parseDouble(strArr[3])));
//				System.out.printf("%s  %s  %s\n", strArr[1],strArr[2],strArr[3]);
				
				if(encode.get(NumOfVertex) == null) {
					encode.put(NumOfVertex,strArr[1]);
					decode.put(strArr[1], NumOfVertex);
					visited.put(NumOfVertex,false);
					NumOfVertex++;
				}
				if(first) {
					east = Double.parseDouble(strArr[3]);
					west = Double.parseDouble(strArr[3]);
					north =  Double.parseDouble(strArr[2]);
					south  = Double.parseDouble(strArr[2]);
					first = false;
					continue;
				}
				if(Double.parseDouble(strArr[3]) > east)east = Double.parseDouble(strArr[3]);
				if(Double.parseDouble(strArr[3]) < west)west = Double.parseDouble(strArr[3]);
				if(Double.parseDouble(strArr[2]) > north)north = Double.parseDouble(strArr[2]);
				if(Double.parseDouble(strArr[2]) < south)south = Double.parseDouble(strArr[2]);
				break;
			case "r":
				
				if(edge.get(strArr[1]) == null)edge.put(strArr[1], new Edge(vertex.get(strArr[2]),vertex.get(strArr[3])));
				addToAdjList(strArr[2],strArr[3]);
				addToAdjList(strArr[3],strArr[2]);
//				String str2 = (strArr[2] + " " + strArr[3]);
//				String str3 = (strArr[3] + " " + strArr[2]);
				if(findEdge.get(strArr[2]) == null) findEdge.put(strArr[2], strArr[1]);
				if(findEdge.get(strArr[3]) == null) findEdge.put(strArr[3], strArr[1]);
				break;
				
			}
			if(b == EOF) break;
		}
		
		
		input.close();
	}
	
	public void drawGraph(Graphics g) {
		for(Map.Entry<String, Edge> pair: edge.entrySet()) {
			pair.getValue().drawEdge(g,east,west,north,south,'m');
		}
	}
	
	
	public void drawShortestPath(Graphics g) {
		int i = 0;
		Node A = list.get(i);
		i++;
		while(i < list.size()) {
			
			Node B = list.get(i);
			i++;
			Edge e = new Edge(A,B);
			A=B;
			
//			g.setColor(Color.BLUE);
			e.drawEdge(g,east,west,north,south,'s');
		}
	}
	
	public void addToAdjList(String s2, String s3) {
		if(s2.equals(s3)) return;
		if(adjList.get(s2) == null)adjList.put(s2, new LinkedList<>());
		if(adjList.get(s2).contains(s3)) return;
		adjList.get(s2).add(s3);
	}
	
	public List<Node> getShortestPath(String i, String j){
		pque = new PriorityQueue<>(new Graph());
		list = new ArrayList<>();
		//get the integer value of the begining and the end of the verteces
		Integer A = decode.get(i);
		Integer J = decode.get(j);
		//add the adjacent 
		//graph.table = new int[N][2];0 = prev ; 1 = dist
		table[A][2] = "0";
		int vertNum = 0;
		while(vertNum <= NumOfVertex && A != J) {
			visited.put(A, true);
//			System.out.println(vertNum + " " + encode.get(A));
//			System.out.println(visited.get(A));
			vertNum ++;
			//traverse the list of connecting edges to the particular node
			for(String S: adjList.get(encode.get(A))) {
				//find the other vertex of the edge.
				Integer I = decode.get(S);
//				if(edge.get(S).getStart().equals(vertex.get(encode.get(A))))I = decode.get(edge.get(S).getEnd().getID());
//				I = decode.get(edge.get(S).getStart().getID());
				//check if the vertex is already visited
				
				if(visited.get(I))continue;
				
//				System.out.println("here");
				//update their previous and total distance
//				Double CurLength = Double.parseDouble(table[I][2]);
//				Double Possible = Double.parseDouble(table[A][2]) + edge.get(findEdge.get(S)).getLength();
//				String str2 = encode.get(A) +" " + S; 
				Edge e = new Edge(vertex.get(S),vertex.get(encode.get(A)));
				if(table[I][2].equals("inf") || Double.parseDouble(table[I][2]) > Double.parseDouble(table[A][2]) + e.getLength()); {
					table[I][2] = String.valueOf(Double.parseDouble(table[A][2]) + e.getLength());
					table[I][1] = String.valueOf(A);
					//add the element to the priority queue
					if(!pque.contains(table[I]))pque.offer(table[I]);
				}
				

				
			}
			if(pque.isEmpty())break;
			//make A the vertex of the line with the shortest distance
			String[] arr = pque.poll();
			A = Integer.parseInt(arr[0]);
		}
//		for(Integer I: visited.keySet()){
//			System.out.println(I + " " + encode.get(I) + " " + visited.get(I));
////			else System.out.printf("False\n");
//		}
//		
////		for(String S: decode.keySet()){
////			System.out.printf("%s  -->  %d \n", S, decode.get(S));
////		}
		
		//if A didn't reach the end so they are separated
		if(A != J) return null;
		//only end when the list is done
		while(A != decode.get(i)) {
			list.add(vertex.get(encode.get(A)));
			A = Integer.parseInt(table[A][1]);
		}
		list.add(vertex.get(encode.get(A)));
		
//		for(Integer I: encode.keySet()){
//			System.out.printf("%d  -->  %s \n", I, encode.get(I));
//		}
		
		return list;
		
		
	}
	public void makeListEdge() {
		
	}


	



	@Override
	public int compare(String[] a1, String[] a2) {
		Double o1 = Double.parseDouble(a1[2]);
		Double o2 = Double.parseDouble(a2[2]);
		if(o1 > o2)return 1;
		else if(o1 < o2)return -1;
		else return 0;
	}
	
	public void printAdjList() {
		for(String pair: adjList.keySet()) {
			System.out.print(pair);
			for(String entry: adjList.get(pair)) {
				System.out.printf(" --> %s", entry);
			}
			System.out.println();
		}
//		for(String pair: edge.keySet()) {
//			System.out.println(pair+ " -- " + edge.get(pair).getLength());
//		}
	}
	
	public void getTheIntersections() {
		int i = list.size()-1;
		while(i >= 0) {
			System.out.print(list.get(i).getID());
			if(i != 0) System.out.print(" --> ");
			i--;
		}
		
		System.out.println();
	}
	
	public double getTheTravelDistance() {
		double total = 0;
		int i = 0;
		Node A = list.get(i);
		i++;
		while(i < list.size()) {
			
			Node B = list.get(i);
			i++;
			Edge e = new Edge(A,B);
			
			total += e.getLength();
			A=B;

		}
		return total;
	}
	
	public void makeTable(){
		table = new String[NumOfVertex+1][3];
		for(int i = 0; i <= NumOfVertex; i++) {
			table[i][0] = String.valueOf(i);
			for(int j = 1; j < 3;j++) {
				table[i][j] = "inf";
			}
		}
	}
	
	
	
}
