package mvc;

import javax.swing.JFrame;

import observer.ObserverFrame;

public class Application {

	public static void main(String[] args) {
		Model model = new Model();
		Frame frame = new Frame();
		
		frame.getView().setModel(model);
		
		Controller controller = new Controller(model, frame);
		frame.setController(controller);
		
		ObserverFrame of = new ObserverFrame(frame);
		controller.addObserver(of);
		
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.isResizable();
	}
	
	

}
