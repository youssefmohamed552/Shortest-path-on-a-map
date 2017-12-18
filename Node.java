/*
 * Name: Youssef Hussein
 * the verteces on the graph
 */

public class Node {
	private String ID;
	private double Lat;
	private double Long;
	private int x;
	private int y;
	
	public Node(String ID ,double Lat,double Long) {
		this.ID = ID;
		this.Lat = Lat;
		this.Long = Long;
	}
	
	
	


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public double getLat() {
		return Lat;
	}


	public void setLat(double lat) {
		Lat = lat;
	}


	public double getLong() {
		return Long;
	}


	public void setLong(double l) {
		Long = l;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getY() {
		return y;
	}	
	
	
}
