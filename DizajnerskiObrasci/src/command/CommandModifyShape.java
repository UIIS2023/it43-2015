package command;

import geometry.Shape;
import mvc.Controller;
import mvc.Model;

public class CommandModifyShape implements Command{

	
	Shape shape;
	Model model;
	Shape helpShape;
	Shape newShape;
	Controller controller;
	int index;
	
	
	
	public CommandModifyShape(Shape shape, Shape newShape,Model model, Controller controller) {
		this.shape = shape;
		this.model = model;
		this.newShape = newShape;
		this.controller = controller; 
	}
	@Override
	public void execute() {
		helpShape = shape.clone();
		index = model.getShapes().indexOf(shape);
		model.remove(this.shape);
		model.addAtIndex(index, newShape);
		//newShape.setSelected(true);

		
		controller.getSelectedShapes().clear();
		controller.getSelectedShapes().add(newShape);
		newShape.setSelected(true);
		
		controller.getFrame().repaint();
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		controller.getSelectedShapes().clear();
		model.remove(newShape);
		model.addAtIndex(index, helpShape);
		controller.getSelectedShapes().add(helpShape);
		helpShape.setSelected(true);
		
		controller.getFrame().repaint();
	}

}
