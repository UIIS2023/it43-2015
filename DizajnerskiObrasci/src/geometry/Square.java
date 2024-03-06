package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Square extends SurfaceShape implements Moveable, Cloneable, Serializable {
	
	protected Point upLeft;
	protected int borderLength;
	 
	
	
	public Square(){
		
		
	}
	
	public Square(Point upLeft, int borderLength){
		
		this.upLeft = upLeft;
		this.borderLength = borderLength;
		
	}
	
	public Square(Point upLeft, int borderLength, String color){
		
		this(upLeft, borderLength);
		setColor(color);
		
	}
	
	public Square(Point upLeft, int borderLength, Color borderColor, Color insideColor){
		this(upLeft, borderLength);
		setBorderColor(borderColor);
		setAreaColor(insideColor);
	}
	
	public Line diagonal(){
		return new Line(upLeft, new Point(upLeft.getX() + borderLength, upLeft.getY() + borderLength));
	}
	
	public Point squareCenter(){
		return diagonal().centerLine();
	}
	
	
	public void moveFor(int x, int y){
		
		upLeft.setX(x);
		upLeft.setY(y);
		
	}
	
	public void moveTo(int x, int y){
		
		upLeft.setX(upLeft.getX() + x);
		upLeft.setY(upLeft.getY() + y);
		
	}
	
	public double bulk(){
		
		return borderLength * 4;
				
	}
	
	public int surface(){
		
		return borderLength * borderLength;
		
	}
	
	@Override
	public boolean contains(int x, int y) {
		if (upLeft.getX() <= x && x <= (upLeft.getX() + borderLength)
				&& upLeft.getY() <= y && y <= (upLeft.getY() + borderLength))
			return true;
		else
			return false;
	}
	
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		
		new Line(getUpLeft(), new Point(getUpLeft().getX()+borderLength, getUpLeft().getY())).selected(g);
		new Line(getUpLeft(), new Point(getUpLeft().getX(), getUpLeft().getY()+borderLength)).selected(g);
		new Line(new Point(getUpLeft().getX()+borderLength, getUpLeft().getY()), diagonal().getPEnd()).selected(g);
		new Line(new Point(getUpLeft().getX(), getUpLeft().getY()+borderLength), diagonal().getPEnd()).selected(g);
	}
	
	

	@Override
	public void drawColor(Graphics g) {
		g.setColor(getBorderColor());
		g.drawRect(upLeft.getX(), upLeft.getY(), borderLength, borderLength);
		fill(g);
		if(isSelected())
			selected(g);
		
	}
	
	public void fill(Graphics g){
		g.setColor(getAreaColor());
		g.fillRect(upLeft.getX()+1, upLeft.getY()+1, borderLength-1, borderLength-1);
	}
	

	@Override
	public int compareTo(Object o) {
		if(o instanceof Square) {
			Square helpSquare = (Square)o;
			return this.surface()-helpSquare.surface();
		}
		else
			return 0;
	}

	
	public String toString(){
		//return "Upper left angle = (" + upLeft.getX() + "," + upLeft.getY() + "), Border = " + borderLength; 
		return "Square "+ "Upper left X: " + upLeft.getX() + " Y: " + upLeft.getY() + " Border " + getBorderLength() + " BorderColor " + getBorderColor().getRGB() + " AreaColor " + getAreaColor().getRGB();  
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Square){
			Square help = (Square) obj;
			if(this.upLeft.equals(help.upLeft) && this.borderLength==help.borderLength)
				return true;
			else
				return false;
		}
		else
			return false;
		
	}
	
	@Override
	public Shape clone() {
		Square deepSquare = new Square();
		
		Point p = new Point(this.upLeft.getX(), this.upLeft.getY());
		deepSquare.setBorderLength(this.borderLength);
		deepSquare.setUpLeft(p);
		deepSquare.setBorderColor(this.getBorderColor());
		deepSquare.setAreaColor(this.getAreaColor());
		
		return deepSquare;
	}


	public Point getUpLeft() {
		return upLeft;
	}

	public int getBorderLength() {
		return borderLength;
	}

	public void setUpLeft(Point upLeft) {
		this.upLeft = upLeft;
	}

	public void setBorderLength(int borderLength) {
		this.borderLength = borderLength;
	}

	

	
	
	
	
	

}
