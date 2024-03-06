package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.Model;

public class CommandBringToBack implements Command{

	Shape shape;
	Model model;
	int index;
	
	
	public CommandBringToBack(Shape shape, Model model) {
		this.shape = shape;
		this.model = model;
	}
	
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		index = model.getShapes().indexOf(shape);
		if(model.getShapes().indexOf(shape) == 0) {
			
			
			JOptionPane.showMessageDialog(null,"Alreay in back","Alreay in back",JOptionPane.INFORMATION_MESSAGE);
			
		} else {
			model.remove(shape);
			//model.getAllShapes();
			//index++;
			model.addAtIndex(0, shape);
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.remove(shape);
		
		model.addAtIndex(index, shape);
	}

}
