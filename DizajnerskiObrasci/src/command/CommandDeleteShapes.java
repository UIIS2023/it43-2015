package command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import geometry.Shape;
import mvc.Controller;
import mvc.Model;

public class CommandDeleteShapes implements Command{

	ArrayList<Shape> helpShapes;
	Model model;
	Controller controller;
	
	//ArrayList<Shape> oldShapes = new ArrayList<Shape>();
	//List<Integer> indexes = new List<Integer>();
	
	TreeMap<Integer, Shape> mapShapes = new TreeMap<Integer,Shape>();
	
	int index;
	Integer helpIndex;
	Shape helpShape;
	
	public CommandDeleteShapes(ArrayList<Shape> helpShapes, Model model, Controller controller) {
		//for(int i = 0; i<shapes.size(); i++) {
		//	this.shapes.add(selectedShapes.get(i).clone());
		//}
		this.helpShapes = helpShapes;
		this.model = model; 
		this.controller=controller;
		
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("usao u exec");
		for(int i = 0; i<helpShapes.size(); i++) {
			//oldShapes = (ArrayList<Shape>) shapes.clone();
			
			//oldShapes.add(shapes.get(i).clone());
			//indexes.add(String.valueOf(model.getShapes().indexOf(shapes.get(i))));
			//System.out.println(model.getShapes().indexOf(shapes.get(i)));
			//System.out.println(String.valueOf(model.getShapes().indexOf(shapes.get(i))));
			//indexes.add(Integer.valueOf(model.getShapes().indexOf(shapes.get(i))));
			//indexes = Integer.valueOf(model.getShapes().indexOf(shapes.get(i)));
			//model.getShapes().indexOf(shapes.get(i))
			//model.remove(shapes.get(i));
			
			//PRE HASHMAPA
			/*
			oldShapes.add(helpShapes.get(i).clone());
			index = model.getShapes().indexOf(helpShapes.get(i));
			Integer help = Integer.valueOf(index);
			indexes.add((Integer)help);
			//model.get(i).setSelected(false);
			model.remove(helpShapes.get(i));
			*/
			
			//POSLE
			index = model.getShapes().indexOf(helpShapes.get(i));
			Integer help = Integer.valueOf(index);
			//indexes.add(help);
			mapShapes.put(help,helpShapes.get(i).clone() );
			//model.remove(helpShapes.get(i));
		}
		
		
		System.out.println(mapShapes);

		
		//System.out.println(indexes);
		
		for(Shape shape: mapShapes.values()) {
			model.remove(shape);
			controller.getSelectedShapes().remove(shape);
			helpShapes.remove(shape);
		}
		
		//model.remove(mapShapes.values());
		
		
		/*
		for(int i =0; i<mapShapes.size(); i++) {
			System.out.println(i+" "  + mapShapes.get(i));
			
			model.getShapes().remove(mapShapes.get(i));
			//controller.getSelectedShapes().remove(mapShapes.get(i));
			//helpShapes.remove(mapShapes.get(i));
		}
		
		*/
		
		/*
		
		model.getShapes().clear();
		
		
		
		//controller.getHelpShapes().clear();
		controller.getSelectedShapes().clear();
		helpShapes.clear();
		//shapes.clear();
		System.out.println(mapShapes);
		*/
		//PIŠE SVE KAKO TREBA
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		for(int i = 0; i<mapShapes.size();i++) {
			//model.addAtIndex(indexes.get(i), oldShapes.get(i));
			//controller.getHelpShapes().add(oldShapes.get(i));
			//controller.getSelectedShapes().add(oldShapes.get(i));
			
			//NE RADI TI ZATO ŠTO NE MOŽE DA UBACI PRVO NA INDEKS 1 AKO NEMA 0
			//MORAŠ IH SORTIRATI
			
			
			
			
			//model.add(mapShapes.get(i));
			
			//System.out.println(i + " " + mapShapes.get(i));
			
			
			
			//controller.getSelectedShapes().add(oldShapes.get(i));
			//controller.getSelectedShapes().get(i).setSelected(true);
			//model.getShapes().get(i).setSelected(true);
			//controller.getSelectedShapes().add(oldShapes.get(i));
			//System.out.println(controller.getSelectedShapes().get(i));
			//System.out.println(model.getShapes().get(i).isSelected());
			//shapes.get(i).setSelected(true);
			//controller.getHelpShapes().add(oldShapes.get(i));
			
			
		}
		
		
		
		int flag = 0;
		/*
		for(Shape shape: mapShapes.values()) {
			//int index = mapShapes.
			model.add(shape);
			controller.getHelpShapes().add(shape);
			controller.getSelectedShapes().add(shape);
			shape.setSelected(true);
		}
		*/
		
		for(Map.Entry<Integer,Shape> entry : mapShapes.entrySet()) {
			  Shape shape = entry.getValue();
			  Integer value = entry.getKey();

			  model.addAtIndex(value,shape);
				controller.getHelpShapes().add(shape);
				controller.getSelectedShapes().add(shape);
				shape.setSelected(true);
			}
		
		for(int i = 0; i<model.getShapes().size(); i++) {
			//controller.getHelpShapes().add(oldShapes.get(i));
			//controller.getSelectedShapes().add(oldShapes.get(i));
			
			
			
			//controller.getHelpShapes().add(mapShapes.get(i));
			//controller.getSelectedShapes().add(mapShapes.get(i));
		}
		
		
		
		/*
		for(int i = 0; i<model.getShapes().size(); i++) {
			//System.out.println(i + " "+oldShapes.get(i) + "old");
			//System.out.println(i+ " " +helpShapes.get(i) + "hlp");
			System.out.println(i + " " + model.get(i) + "MODEL");
			System.out.println(controller.getSelectedShapes().get(i));
		}
		 */ 
		
		
		
		System.out.println("vratio se");
		System.out.println(mapShapes);
		//oldShapes.clear();
		//mapShapes.clear();
	}
	
	

}
