package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Circle extends SurfaceShape implements Moveable, Cloneable , Serializable {

	private Point center;
	private int r;
	
	
	public Circle(){
		
		
	}
	
	public Circle(Point center, int r){
		
		this.center = center;
		this.r = r;
		
	}
	
	public Circle(Point center, int r, String color){
		
		this(center, r);
		setColor(color);
		
	}
	
	
	
	public Circle(Point center, int r, Color borderColor, Color insideCOlor){
		this(center, r);
		setBorderColor(borderColor);
		setAreaColor(insideCOlor);
	}
	
	public void moveFor(int x, int y){
		
		center.setX(x);
		center.setY(y);
	}
	
	public void moveTo(int x, int y){
		
		center.setX(center.getX() + x);
		center.setY(center.getY() + y);
		
	}
	
	public double surface(){
		
		return r * r * Math.PI;
	}
	
	public double circumference(){
		
		return 2 * r * Math.PI;
	}
	
	public boolean contains(int x, int y){
		Point click = new Point(x,y);
		if(click.distance(center)<=r){
			return true;
		} 
		else
			return false;
	}
	
	public boolean equals(Object obj) {
		if( obj instanceof Circle) {
			Circle help =(Circle)obj;
			if(this.center.equals(help.center) && this.r==help.r)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public void selected(Graphics g){
		new Line(new Point(center.getX(), center.getY()-r), new Point(center.getX(), center.getY() + r)).selected(g);
		new Line(new Point(center.getX()-r, center.getY()), new Point(center.getX() + r, center.getY())).selected(g);
	}
	
	

	@Override
	public void drawColor(Graphics g) {
		g.setColor(getBorderColor());
		g.drawOval(center.getX()-r, center.getY()-r, 2*r, 2*r);
		fill(g);
		if(isSelected())
			selected(g);
		
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Circle){
			Circle help = (Circle) o;
			return this.r - help.r;
		} else
			return 0;
	}
	
	public void fill(Graphics g){
		g.setColor(getAreaColor());
		g.fillOval(center.getX()-r+1, center.getY()-r+1, 2*r-2, r*2-2);
	}
	
	
	@Override
	public Shape clone() {
		Circle circle = new Circle(this.getCenter(), this.getR(), this.getBorderColor(), this.getAreaColor());
		return circle;
	}
	

	
	public String toString(){
		//return "Center = " + center + ", Radius = " + r;
		//return "Circle "+ "Center X " + center.getX() + " Y " + center.getY() + " Radius " + getR() + " BorderColor " + getBorderColor().getRGB() + " AreaColor " + getAreaColor().getRGB();
		return "Circle "+ "Center X: " + center.getX() + " Y: " + center.getY() + " Radius " + this.getR() + " BorderColor " + getBorderColor().getRGB() + " AreaColor " + getAreaColor().getRGB();
		//return "Circle " + center.getX() + " " + center.getY() + " " + getR() + " " + getBorderColor().getRGB() + " " + getAreaColor().getRGB();
	}
	

	public Point getCenter() {
		return center;
	}

	public int getR() {
		return r;
	}


	public void setCenter(Point center) {
		this.center = center;
	}

	public void setR(int r) {
		this.r = r;
	}

	

	

	
}
