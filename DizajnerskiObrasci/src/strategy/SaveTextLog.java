package strategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import mvc.Frame;

public class SaveTextLog implements Strategy{

	Frame frame;
	 
	public SaveTextLog(Frame frame) {
		this.frame = frame;
	}
	
	
	@Override
	public void saveFile() {
		// TODO Auto-generated method stub
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
		fileChooser.setFileFilter(filter);
		
		
		int userSelection = fileChooser.showSaveDialog(frame);
		File fileToSave;  
		FileWriter fw;
		PrintWriter pw;
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    
			
			fileToSave = new File(fileChooser.getSelectedFile().getAbsolutePath());
			
			if(fileToSave != null) {
				String extension = filter.getExtensions()[0];

			    String newName = fileToSave.getName().trim() + "." + extension;
			    fileToSave = new File(fileToSave.getParent(), newName);
				
			    try {
					fw = new FileWriter(fileToSave);
					pw = new PrintWriter(fw);
					for(int i = 0; i<frame.getCommandsList().size(); i++) {
						//pw.write(frame.getCommandsList().get(i));
						pw.println(frame.getCommandsList().get(i));
					}
					pw.close();
					fw.close(); //MOŽDA I NE TREBA
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
		    //System.out.println("Save as file: " + fileToSave.getAbsolutePath()+ ".txt");
		}
		
		
		
	}

}
