/*
 * Name: Youssef Hussein
 * the edges between the vertices
 */


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Edge {
	private Node start;
	private Node end;
	private double length;
	
	public Edge(Node start, Node end) {
		this.start = start;
		this.end = end;
		this.length = this.getLength();
	}
	
	public void drawEdge(Graphics g, double east, double west, double north, double south,char ch) {
		Rectangle size = g.getClipBounds();
		double HorizontalRatio = size.getWidth()/(Math.abs(east - west));
		double VerticalRatio = size.getHeight()/Math.abs(north - south);
		
		int x1 = (int)(Math.abs(west - start.getLong()) * HorizontalRatio);
		int x2 = (int)(Math.abs(west - end.getLong()) * HorizontalRatio);
		int y1 = (int)(Math.abs(north - start.getLat()) * VerticalRatio);
		int y2 = (int)(Math.abs(north - end.getLat()) * VerticalRatio);
		Graphics2D g1 = (Graphics2D)g;
		if(ch == 's') {
			g1.setColor(Color.BLUE);
			g1.setStroke(new BasicStroke(5));
//			g.fillOval(x1, y1, 10, 10);
//			g.fillOval(x2, y2, 10, 10);

		}
		
		g1.drawLine(x1, y1, x2, y2);
//		g.fillOval(x1, y1, 200, 200);
		
		
	}

	public Node getStart() {
		return start;
	}

	public void setStart(Node start) {
		this.start = start;
	}

	public Node getEnd() {
		return end;
	}

	public void setEnd(Node end) {
		this.end = end;
	}
	
	public double getLength() {
		//get the length of the line(Edge)
//		double cosinLat = Math.cos(Math.toRadians(this.start.getLat() - this.end.getLat()));
//		double lengthOneLong = cosinLat * 69.;
//		double lengthLongDegrees = lengthOneLong * (this.start.getLong() - this.end.getLong());
//		double diffInX = Math.pow(((this.start.getLong() - this.end.getLong())*(69.)),2);
//		double diffInY = Math.pow((this.start.getLat() - this.end.getLat()),2);
		return distance(start.getLat(), start.getLong(), end.getLat(), end.getLong());
	}
	
	private double distance(double lat1, double lon1, double lat2, double lon2) {
	      double theta = lon1 - lon2;
	      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	      dist = Math.acos(dist);
	      dist = rad2deg(dist);
	      dist = dist * 60 * 1.1515;
//	      if (unit == 'K') {
//	        dist = dist * 1.609344;
//	      } else if (unit == 'N') {
//	        dist = dist * 0.8684;
//	        }
	      return (dist);
	    }

	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    /*::  This function converts decimal degrees to radians             :*/
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    private double deg2rad(double deg) {
	      return (deg * Math.PI / 180.0);
	    }

	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    /*::  This function converts radians to decimal degrees             :*/
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    private double rad2deg(double rad) {
	      return (rad * 180.0 / Math.PI);
	    }

//	    System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'M') + " Miles\n");
//	    System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'K') + " Kilometers\n");
//	    System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'N') + " Nautical Miles\n");
	
}
