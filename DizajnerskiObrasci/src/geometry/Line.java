package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.Serializable;

public class Line extends Shape implements Serializable  {

	private Point pStart;
	private Point pEnd;
	
	public Line(){
		
		
	}
	
	public Line(Point pStart, Point pEnd){
		
		this.pStart = pStart;
		this.pEnd = pEnd;
		
	}
	
	public Line(Point pStart, Point pEnd, String color){
		this(pStart, pEnd);
		setColor(color);
		
	}
	
	public Line(Point pStart, Point pEnd, Color borderColor){
		this(pStart, pEnd);
		setBorderColor(borderColor);
	}
	
	public double length(){
		return pStart.distance(pEnd);
	}
	
	public Point centerLine(){
		
		int middleX = (pStart.getX() + pEnd.getX()) / 2;
		int middleY = (pStart.getY() + pEnd.getY()) / 2;
		return new Point(middleX, middleY);
		
	}
	
	
	public void moveFor(int x, int y){
		
		pStart.setX(pStart.getX()+x);
		pStart.setY(pStart.getY()+y);
		pEnd.setX(pEnd.getX()+x);
		pEnd.setY(pEnd.getY()+y);
		
	}
	
	
	public boolean contains(int x, int y){
		Point click = new Point(x,y);
		if(click.distance(pStart)+click.distance(pEnd)-this.length()<=0.5)
			return true;
		else
			return false;
	}
	
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		pStart.selected(g);
		pEnd.selected(g);
		centerLine().selected(g);
	}
	
	
	public String toString(){
		//return "(" + pStart.getX() + "," + pStart.getY() + ") --> (" + pEnd.getX() + "," + pEnd.getY() + ")";
		//return "Line: "+"("+this.pStart.getX()+", "+this.pStart.getY()+") ("+this.pEnd.getX()+", "+this.pEnd.getY()+"), " + 
				//"Color: ("+Integer.toString(getBorderColor().getRGB())+")";
		return "Line" + " " + "XStart: " + pStart.getX() + " " + "YStart: " + pStart.getY() + " " + "XEnd: " + pEnd.getX() + " " + "YEnd: " + pEnd.getY() + " " + "BorderColor: " + getBorderColor().getRGB(); 
	}
	
	
	
	public boolean equals(Object obj){
		if(obj instanceof Line){
			Line help = (Line) obj;
			if(this.pStart.equals(help.pStart) && this.pEnd.equals(help.pEnd))
				return true;
			else
				return false;
			
		}
		
		else 
			return false;
		
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Line){
			Line help = (Line) o;
			//Linija drugaPomocna = new Linija(tPocetna, tKrajnja);
			
			//return (int)(pomocna.duzina() - drugaPomocna.duzina());
			
			return (int) (this.length()-help.length());
		}else
			return 0;
	}

	@Override
	public void drawColor(Graphics g) {
		
		Line2D.Double line = new Line2D.Double();
		line.setLine(pStart.getX(), pStart.getY(), pEnd.getX(), pEnd.getY());
		
		
		Graphics2D graphics2d = (Graphics2D) g.create();
		graphics2d.setColor(getBorderColor());
		graphics2d.draw(line);
		graphics2d.dispose();
		
		//g.setColor(getBorderColor());
		//g.drawLine(pStart.getX(), pStart.getY(), pEnd.getX(), pEnd.getY());
		
		if(isSelected())
			selected(g);
		
		
	}
	
	@Override
	public Shape clone() {
		Line lineDeep = new Line();
		Point pStart = new Point(this.pStart.getX(), this.pStart.getY());
		Point pEnd = new Point(this.pEnd.getX(), this.pEnd.getY());
		
		lineDeep.setPStart(pStart);
		lineDeep.setPEnd(pEnd);
		lineDeep.setBorderColor(this.getBorderColor());
		return lineDeep;
	}

	public Point getPStart() {
		return pStart;
	}

	public Point getPEnd() {
		return pEnd;
	}

	public void setPStart(Point pStart) {
		this.pStart = pStart;
	}

	public void setPEnd(Point pEnd) {
		this.pEnd = pEnd;
	}

	


}
