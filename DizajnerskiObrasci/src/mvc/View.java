package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Shape;

public class View extends JPanel{

	public View() {
		setBackground(Color.WHITE);
	}
	
	private Model model = new Model();

	public void setModel(Model model) {
		this.model = model;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			it.next().drawColor(g);
		}
		repaint();
		//System.out.println(System.currentTimeMillis());
	}
	
}
