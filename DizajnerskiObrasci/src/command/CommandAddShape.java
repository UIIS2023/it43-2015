package command;

import geometry.Shape;
import mvc.Controller;
import mvc.Model;

public class CommandAddShape implements Command{
	
	Shape shape;
	Model model;
	Controller controller;
	int flag = 0;
	
	
	
	public CommandAddShape(Shape shape, Model model, Controller controller) {
		this.shape = shape;
		this.model = model;
		this.controller=controller;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.add(shape);
		if(flag == 1) {
			shape.setSelected(true);
			controller.getSelectedShapes().add(shape);
		}
		flag = 0;
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.remove(shape);
		if(shape.isSelected()) {
			controller.getSelectedShapes().remove(shape);
			flag = 1;
		}
		
	}

}
