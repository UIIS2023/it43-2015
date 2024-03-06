package command;


import geometry.Shape;
import mvc.Controller;
import mvc.Model;

public class CommandDeleteShape implements Command{

	
	Shape shape;
	Model model;
	Controller controller;
	
	Shape oldShape;
	
	int index;
	
	//napravi for da se vraćaju odma svi ako ih obrišeš više odjednom
	
	public CommandDeleteShape(Shape shape, Model model, Controller controller) {
		this.shape = shape;
		this.model = model; 
		this.controller=controller;
		
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		
		//System.out.println("REDO ILI INICIJALNI DELETE");
		//System.out.println(index);
		
		oldShape = shape.clone();
		index = model.getShapes().indexOf(shape);
		
		//System.out.println(shape.toString());
		//System.out.println(oldShape.toString());
		model.remove(this.shape);
		
		
		//System.out.println(model.getShapes());
		//System.out.println(model.getShapes().indexOf(oldShape));
		
	}

	@Override
	public void unexecute() {
		
		//System.out.println("UNDO DELETA U�AO");
		//System.out.println(index);
		
		//System.out.println(shape.hashCode());
		//System.out.println(oldShape.hashCode());
		
		/*
		if(controller.getShapesToDelete().size() > 1) {
			for(Shape shape: controller.getShapesToDelete()) {
				model.addAtIndex(this.index,oldShape);
			}
		}
		*/
		
		model.addAtIndex(this.index,oldShape);
		//System.out.println(model.getShapes());
		//System.out.println(model.getShapes().indexOf(oldShape));
		
	}

}
