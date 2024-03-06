package strategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import mvc.Frame;
import mvc.Model;

public class SaveDrawingLog implements Strategy{

	Model model;
	Frame frame;
	
	public SaveDrawingLog(Model model, Frame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	
	
	
	@Override
	public void saveFile() {
		// TODO Auto-generated method stub
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".ser", "ser");
		fileChooser.setFileFilter(filter);
		
		int userSelection = fileChooser.showSaveDialog(frame);
		File fileToSave; 
		
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			
			fileToSave = new File(fileChooser.getSelectedFile().getAbsolutePath());
			
			if(fileToSave != null) {
				String extension = filter.getExtensions()[0];

			    String newName = fileToSave.getName().trim() + "." + extension;
			    fileToSave = new File(fileToSave.getParent(), newName);
				
			    try {
					 
		            FileOutputStream fileOut = new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath());
		            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		            for(int i = 0; i<model.getShapes().size(); i++) {
		            	objectOut.writeObject(model.get(i));
		            }
		            
		            objectOut.close();
		            fileOut.close(); //MOŽDA I NE TREBA
		            System.out.println("The Object  was succesfully written to a file");
		 
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
			} 
		}
	}

}
