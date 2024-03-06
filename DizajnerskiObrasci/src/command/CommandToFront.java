package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.Model;

public class CommandToFront implements Command{

	
	Shape shape;
	Model model;
	int index;
	
	
	public CommandToFront(Shape shape, Model model) {
		this.shape = shape;
		this.model = model;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		index = model.getShapes().indexOf(shape);
		if(index < model.getShapes().size()-1) {
			model.remove(shape);
			//model.getAllShapes();
			//index++;
			model.addAtIndex(index+1, shape);
			
		} else {
			JOptionPane.showMessageDialog(null,"Alreay in front","Alreay in front",JOptionPane.INFORMATION_MESSAGE);
			
		}
		
		
		
		
		
	}


	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.remove(shape);
		
		model.addAtIndex(index, shape);
		
	}
	
	
}
