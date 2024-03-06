package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;





public class Donut  extends Circle implements Moveable, Serializable {

	
	private int innerRadius;
	
	
	public Donut() {
		
	}
	public Donut(Point center, int r, int innerRadius) {
		super(center, r);
		this.innerRadius = innerRadius;
		// TODO Auto-generated constructor stub
	}
	public Donut(Point center, int r, int innerRadius,String color) {
		this(center, r, innerRadius);
		setColor(color);
		// TODO Auto-generated constructor stub
	}

	public Donut(Point center, int r, int innerCircle,Color borderColor, Color insideColor) {
		this(center, r, innerCircle);
		setBorderColor(borderColor);
		setAreaColor(insideColor);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	public boolean contains(int x, int y){
		Point click = new Point(x,y);
		if(click.distance(this.getCenter())<=this.getR()){
			return true;
		} 
		else
			return false;
	}
	
	
	public void drawColor(Graphics g) {
		
		/*
		g.setColor(getBorderColor());
		
		g.drawOval(this.getCenter().getX()-getR(), this.getCenter().getY()-getR(), 2*getR(), 2*getR());
		fill(g);
		drawInsideCircle(g);
		//g.drawOval(this.centar.getX()-this.unutrasnjiPoluprecnik, this.centar.getY()-this.unutrasnjiPoluprecnik, 2*this.unutrasnjiPoluprecnik, 2*this.unutrasnjiPoluprecnik);
		//popuniUnutrasnji(g);
		if(isSelected())
			selected(g);
			
			*/
		
		Ellipse2D.Double outerCircle = new Ellipse2D.Double(this.getCenter().getX() -
		this.getR(), this.getCenter().getY() - this.getR(),
		this.getR() * 2, this.getR() * 2); 
				
		Ellipse2D.Double innerShape = new Ellipse2D.Double(this.getCenter().getX() -
		this.getInnerRadius(), this.getCenter().getY() - this.getInnerRadius(),
		this.getInnerRadius() * 2, this.getInnerRadius() * 2);
				
		Area donut = new Area(outerCircle);
        donut.subtract(new Area(innerShape));
				
        Graphics2D graphics2d = (Graphics2D) g.create();
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
		graphics2d.setColor(this.getAreaColor());
		graphics2d.fill(donut);
		graphics2d.setColor(this.getBorderColor()); 
		graphics2d.draw(donut);
			    
	    graphics2d.dispose();	
			   
	    if(isSelected()) {
	    	selected(g);
	    	}
	}
	
	public void drawInsideCircle(Graphics g) {
		g.setColor(getBorderColor());
		g.drawOval(this.getCenter().getX()-this.innerRadius, this.getCenter().getY()-this.innerRadius, 2*this.innerRadius, 2*this.innerRadius);
		fillInsideCircle(g);
		if(isSelected())
			selected(g);
	}
	
	@Override
	public void fill(Graphics g){
		/*
		g.setColor(getAreaColor());
		g.fillOval(this.getCenter().getX()-getR()+1, this.getCenter().getY()-getR()+1, 2*getR()-3, getR()*2-3);
		fillInsideCircle(g);
		*/
		g.setColor(getAreaColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
		
	}
	
	public void fillInsideCircle(Graphics g){
		g.setColor(Color.WHITE);
		//g.fillOval(this.centar.getX()-spoljasnjiPoluprecnik+1, this.centar.getY()-spoljasnjiPoluprecnik+1, 2*spoljasnjiPoluprecnik-2, spoljasnjiPoluprecnik*2-2);
		g.fillOval(this.getCenter().getX()-this.innerRadius, this.getCenter().getY()-this.innerRadius, 2*this.innerRadius, 2*this.innerRadius);
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Donut){
			Donut help = (Donut) o;
			return this.innerRadius - help.innerRadius;
		} else
			return 0;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getCenter().equals(d.getCenter()) &&
					this.getR() == d.getR() &&
					this.innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	
	
	@Override
	public Shape clone() {
		Donut donutDeep = new Donut(this.getCenter(), this.getR(), this.getInnerRadius(), this.getBorderColor(), this.getAreaColor());
		return donutDeep;
	}
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
		
	}
	
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Donut "+ "Center X: " + this.getCenter().getX() + " Y: " + this.getCenter().getY() + " Radius " + this.getR() + " InnerRadius "+ this.getInnerRadius() +" BorderColor " + getBorderColor().getRGB() + " AreaColor " + getAreaColor().getRGB();
		}
	
	}
	
	
	
	

