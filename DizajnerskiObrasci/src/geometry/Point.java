package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Point extends Shape implements Moveable, Serializable  {
	
	private int x;
	private int y;
	private String color;
	
	public Point(){
		
		
	}
	
	public Point(int middleX, int middleY){
		
		this.x = middleX;
		this.y = middleY;
		
	}
	
	/*
	public Point(int x, int y, String color){
		
		this(x,y);
		this.color = color;
		setColor(color);
		
	}
	*/
	
	public Point(int x, int y, Color borderColor){
		this(x, y);
		setBorderColor(borderColor);
	}
	
	
	
	
	public void moveFor(int newX, int newY){
		
		x = newX;
		y = newY;
		
	}
	
	public void moveTo(int newX, int newY){
		
		x = x + newX;
		y = y + newY;
		
	}
	
	public double distance(Point t2){
		
		double dx = x - t2.getX();
		double dy = y - t2.getY();
		
		double result = Math.sqrt(dx*dx + dy*dy);
		
		return result;
		
	}
	
	public boolean contains(int x, int y){
		Point click = new Point(x, y);
		if(click.distance(this)<=2)
			return true;
		else
			return false;
	}
	
	
	public void selected(Graphics g){
		g.setColor(findColor("plava"));
		g.drawRect(this.getX()-3, this.getY()-3, 6, 6);
	}
	
	
	public void drawColor(Graphics g) {
	
		g.setColor(getBorderColor());
		g.drawLine((int)x-2, (int)y, (int)x+2, (int)y);
		g.drawLine((int)x, (int)y-2, (int)x, (int)y+2);
		if(isSelected())
			selected(g);
		
		
	}
	
	public String toString(){
		//return "(" + x + "," + getY() + ")" + getBorderColor().getRGB();
		return "Point" + " " + "X: " + getX() + " " + "Y: " +getY() + " " + "BorderColor: " + getBorderColor().getRGB(); 
	}
	
	public boolean equals(Object obj){
		
		if(obj instanceof Point){
			
			Point help = (Point) obj;
			if(x==help.getX() && y==help.getY())
				return true;
			else 
				return false;
			
		}
		
		else 
			return false;
		
	}
	
	
	@Override
	public int compareTo(Object o) {
		Point zero = new Point(0,0);
		Point newPoint = (Point)o;
		return (int)(this.distance(zero)-newPoint.distance(zero));
	}
	
	
	@Override
	public Shape clone() {
		Point pointDeep = new Point();
		
		pointDeep.setX(this.getX());
		pointDeep.setY(this.getY());
		pointDeep.setBorderColor(this.getBorderColor());
		return pointDeep;
	}
	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	

	


}
