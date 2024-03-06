package wb;

import javax.swing.JPanel;

import geometry.Shape;

import mvc.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class CrtanjeView extends JPanel {

	/**
	 * Create the panel.
	 */
	
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	private Model model = new Model();

	public void setModel(Model model) {
		this.model = model;
	}
	
	
	public CrtanjeView() {
		setBackground(Color.WHITE);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		ListIterator<Shape> itShapes = shapes.listIterator();
		while (itShapes.hasNext()) {
			Shape o = (Shape) itShapes.next();
			o.drawColor(g);
		}
		repaint();
		
		
	}
	
	
	//DODAVANJE U ARRAY LISTU MODELA
	
	/*
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			it.next().drawColor(g);
		}
		repaint();
		//System.out.println(System.currentTimeMillis());
	}
	*/

}
