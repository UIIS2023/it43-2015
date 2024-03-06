package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Comparable, Cloneable, Serializable  {

	private String color = "crna" ;
	private boolean selected;
	private Color borderColor=Color.BLACK;
	
	public Shape(){
		
		
	}
	
	public Shape(String color){
		this.color = color;
	}
	
	public abstract void drawColor(Graphics g);
	public abstract void selected(Graphics g);
	public abstract boolean contains(int x, int y);
	
	@Override
	public abstract Shape clone();
	
	public static Color findColor(String color){
		
		if(color.equalsIgnoreCase("crna"))
			return Color.BLACK;
		else if(color.equalsIgnoreCase("bela"))
			return Color.WHITE;
		else if(color.equalsIgnoreCase("plava"))
			return Color.BLUE;
		else if(color.equalsIgnoreCase("crvena"))
			return Color.RED;
		else if(color.equalsIgnoreCase("zuta"))
			return Color.YELLOW;
		else if(color.equalsIgnoreCase("zelena"))
			return Color.GREEN;
		else if(color.equalsIgnoreCase("pink"))
			return Color.PINK;
		else
			return Color.BLACK;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String boja) {
		this.color = boja;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color bojaIvice) {
		this.borderColor = bojaIvice;
	}
	
	
	
	
	
	
}
