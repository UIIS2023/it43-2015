package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.Model;

public class CommandBringToFront implements Command{

	Shape shape;
	Model model;
	int index;
	
	
	public CommandBringToFront(Shape shape, Model model) {
		this.shape = shape;
		this.model = model;
	}
	
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		index = model.getShapes().indexOf(shape);
		if(model.getShapes().indexOf(shape) == model.getShapes().size()-1) {
			
			
			JOptionPane.showMessageDialog(null,"Alreay in front","Alreay in front",JOptionPane.INFORMATION_MESSAGE);
			
		} else {
			model.remove(shape);
			//model.getAllShapes();
			//index++;
			model.addAtIndex(model.getShapes().size(), shape);
		}
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.remove(shape);
		
		model.addAtIndex(index, shape);
	}

}
