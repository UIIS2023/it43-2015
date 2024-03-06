package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class SurfaceShape extends Shape implements Cloneable, Serializable {

	private String bojaUnutrasnjosti = "bela";
	private Color areaColor=Color.WHITE;
	
	public abstract void fill(Graphics g);

	/*
	public String getBojaUnutrasnjosti() {
		return bojaUnutrasnjosti;
	}

	public void setBojaUnutrasnjosti(String bojaUnutrasnjosti) {
		this.bojaUnutrasnjosti = bojaUnutrasnjosti;
	}

	 */
	public Color getAreaColor() {
		return areaColor;
	}

	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
	}
	
	@Override
	public abstract Shape clone();
	
	
	
}
