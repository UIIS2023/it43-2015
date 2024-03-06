package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape implements Serializable, Cloneable{
	
	private Hexagon hexagon;
	
	public HexagonAdapter() {
		
	}
	
	public HexagonAdapter(int x, int y, int r,Color borderColor, Color insideColor) {
		this.hexagon = new Hexagon(x, y, r);
		hexagon.setAreaColor(insideColor);
		hexagon.setBorderColor(borderColor);
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getAreaColor());
		
	}

	@Override
	public void drawColor(Graphics g) {
		g.setColor(getBorderColor());
		fill(g);
		this.hexagon.paint(g);
		selected(g);
	}

	@Override
	public void selected(Graphics g) {
		if(isSelected()) 
			hexagon.setSelected(true);
		else
			hexagon.setSelected(false);
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof HexagonAdapter) {
			HexagonAdapter help = (HexagonAdapter)obj;
			if(this.hexagon.getX()==help.hexagon.getX() && this.hexagon.getY()==help.hexagon.getY() && this.hexagon.getR()==help.hexagon.getR()) {
				return true;
			}else
				return false;
		}
		else
			return false;
	}

	@Override
	public Shape clone() {
		// TODO Auto-generated method stub
		
		HexagonAdapter hexDeep = new HexagonAdapter();
		
		hexDeep.hexagon = new Hexagon(this.getHexagon().getX(), this.getHexagon().getY(), this.getHexagon().getR());
		
		hexDeep.hexagon.setBorderColor(this.getHexagon().getBorderColor());
		hexDeep.hexagon.setAreaColor(this.getHexagon().getAreaColor());
		
		
		return hexDeep;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Hexagon "+ "Center X: " + hexagon.getX() + " Y: " + hexagon.getY() + " Radius " + hexagon.getR() + " BorderColor " + hexagon.getBorderColor().getRGB() + " AreaColor " + hexagon.getAreaColor().getRGB();
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	

	
	
	
}
