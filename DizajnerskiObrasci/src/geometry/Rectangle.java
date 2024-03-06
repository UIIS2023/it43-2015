package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Rectangle extends Square implements Serializable {

	
	private int width;
	
	
	public Rectangle(){
		
		
	}
	
	public Rectangle(Point upLeft, int height, int width){
		
		super(upLeft, height);
		this.width = width;
		
	}
	
	public Rectangle(Point upLeft, int height, int width, String color){
		
		this(upLeft, height, width);
		setColor(color);
		
	}
	
	public Rectangle(Point upLeft, int height, int width, Color borderColor, Color insideColor){
		this(upLeft, height, width);
		setBorderColor(borderColor);
		setAreaColor(insideColor);
	}
	
	
	public Line diagonal(){
		return new Line(upLeft, new Point(upLeft.getX() + width, upLeft.getY() + borderLength));
	}
	
	public Point rectangleCenter(){
		return diagonal().centerLine();
	}
	
	

	
	public int surface(){
		
		return borderLength * width;
		
	}
	
	public double bulk(){
		
		return 2 * borderLength + 2* width;
		
	}

	public boolean contains(int x, int y) {
		if (this.getUpLeft().getX() <= x && x <= (this.getUpLeft().getX() + getBorderLength())
				&& this.getUpLeft().getY() <= y && y <= (this.getUpLeft().getY() + width))
			return true;
		else
			return false;
	}
	
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		g.setColor(Color.BLUE);
		
		new Line(getUpLeft(), new Point(getUpLeft().getX()+width, getUpLeft().getY())).selected(g);
		new Line(getUpLeft(), new Point(getUpLeft().getX(), getUpLeft().getY()+borderLength)).selected(g);
		new Line(new Point(getUpLeft().getX()+width, getUpLeft().getY()), diagonal().getPEnd()).selected(g);
		new Line(new Point(getUpLeft().getX(), getUpLeft().getY()+borderLength), diagonal().getPEnd()).selected(g);
	}
	
	
	
	
	public void drawColor(Graphics g){
		g.setColor(getBorderColor());
		g.drawRect(upLeft.getX(), upLeft.getY(), width, borderLength);
		fill(g);
		if(isSelected())
			selected(g);
	}
	
	public void fill(Graphics g){
		g.setColor(getAreaColor());
		g.fillRect(upLeft.getX()+1, upLeft.getY()+1, width-1, borderLength-1);
	}
	
	public String toString(){
		//return "Upper left angle = " + upLeft + "Width = " + width + "Height = " + borderLength;  
		return "Rectangle "+ "Upper left X: " + upLeft.getX() + " Y: " + upLeft.getY() + " Border " + getBorderLength() + " Width: "+ width +" BorderColor " + getBorderColor().getRGB() + " AreaColor " + getAreaColor().getRGB();
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Rectangle){
			Rectangle help = (Rectangle) obj;
			if(this.upLeft.equals(help.upLeft) && this.borderLength==help.borderLength && this.width==help.width)
				return true;
			else 
				return false;
		}
		else
			return false;
		
	}
	
	

	@Override
	public Shape clone() {
		Rectangle rectangleDeep = new Rectangle();
		
		Point p = new Point(this.upLeft.getX(), this.upLeft.getY());
		rectangleDeep.setBorderLength(this.borderLength);
		rectangleDeep.setUpLeft(p);
		rectangleDeep.setBorderColor(this.getBorderColor());
		rectangleDeep.setAreaColor(this.getAreaColor());
		rectangleDeep.setWidth(this.getWidth());
		
		return rectangleDeep;
	}

	public Point getUpLeft() {
		return upLeft;
	}

	public int getHeight() {
		return borderLength;
	}

	public int getWidth() {
		return width;
	}

	public void setUpLeft(Point upLeft) {
		this.upLeft = upLeft;
	}

	public void setHeight(int height) {
		this.borderLength = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}


	
	
	
	
}
