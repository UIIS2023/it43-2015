package observer;

import java.util.Observable;
import java.util.Observer;

import mvc.Controller;
import mvc.Frame;

public class ObserverFrame implements Observer{

	Frame frame;
	
	public ObserverFrame(Frame frame) {
		this.frame = frame;
	}
	
	
	@Override
	public void update(Observable o, Object arg1) {
		// TODO Auto-generated method stub
		Controller c = (Controller)o;

		if(c.getSelectedShapes().size() == 1) {
			frame.getTglbtnModify().setEnabled(true);
			frame.getTglbtnDelete().setEnabled(true);
			frame.getBtnToBack().setEnabled(true);
			frame.getBtnToFront().setEnabled(true);
			frame.getBtnBringToBack().setEnabled(true);
			frame.getBtnBringToFront().setEnabled(true);
		} else if(c.getSelectedShapes().size() > 1){
			frame.getTglbtnModify().setEnabled(false);
			frame.getTglbtnDelete().setEnabled(true);
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
			frame.getBtnBringToFront().setEnabled(false);
		} else if(c.getSelectedShapes().size() == 0){
			frame.getTglbtnModify().setEnabled(false);
			frame.getTglbtnDelete().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
			frame.getBtnBringToFront().setEnabled(false);
		}
		
	}

}
