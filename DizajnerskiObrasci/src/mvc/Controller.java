package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import command.Command;
import command.CommandAddShape;
import command.CommandBringToBack;
import command.CommandBringToFront;
import command.CommandDeleteShape;
import command.CommandDeleteShapes;
import command.CommandModifyShape;
import command.CommandToBack;
import command.CommandToFront;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.Square;
import hexagon.Hexagon;
import strategy.SaveDrawingLog;
import strategy.SaveTextLog;
import strategy.Strategy;
import wb.DlgCircle;
import wb.DlgDonut;
import wb.DlgHexagon;
import wb.DlgHexagon2;
import wb.DlgLine;
import wb.DlgPoint;
import wb.DlgRectangle;
import wb.DlgSquare;


public class Controller extends Observable{
	
	private Model model;
	private Frame frame;
	
	int click = 0;
	Point pStart;
	Point pEnd;
	Color borderColor = Color.BLACK;
	Color insideColor = Color.WHITE;
	//Command command;

	private boolean selected=false;
	private Strategy savingStrategy;
	
	
	private Stack<Command> undoStack = new Stack<Command>();
	private Stack<Command> redoStack = new Stack<Command>();
	private ArrayList<String> load;
	private StringTokenizer st;
	private int loadIterator = 0;
	private String helpIteratorString = null;
	

	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	ArrayList<Shape> helpShapes;
	//private int logListIterator = 1;
	
	public Controller (Model model, Frame frame) {
		this.model = model;
		this.frame = frame;
		
		//frame.getTglbtnModify().setEnabled(false);
		//frame.getTglbtnDelete().setEnabled(false);
		
	}
	
	
	
	int flag = 0;
	int maxIndex = 0;

	public void mouseClicked(MouseEvent e) {
		
		//System.out.println("kliknuto na view");

		if(frame.getTglbtnSelect().isSelected()){
			int x = e.getX();
			int y = e.getY();
			//frame.getCommandsList().add(frame.getCommandsList().size(),"Selected " + " X: " + x + " Y: " + y);
			for (Shape shape : model.getShapes()) {
				if (shape.contains(x, y)) {
					maxIndex = model.getShapes().indexOf(shape);
					//model.getShapes().get(maxIndex).isSelected()
				}
			}
			if (!model.getShapes().get(maxIndex).isSelected() && model.getShapes().get(maxIndex).contains(x, y)) {
				/*
				for(int i =0; i<model.getShapes().size(); i++) {
					if(model.getShapes().get(i).contains(x, y)) {
						flag++;
						System.out.println(flag);
					}
				}
				*/
				selected = true;
				model.getShapes().get(maxIndex).setSelected(true);
				model.getShapes().get(maxIndex).selected(frame.getView().getGraphics());
				selectedShapes.add(model.getShapes().get(maxIndex));
				//frame.getCommandsList().add(frame.getCommandsList().size(),"Selected " + " " + shape.toString());
				
				frame.getCommandsList().add(frame.getCommandsList().size(),"Select" + " X: " + x + " Y: " + y);
				setChanged();
				notifyObservers();
			} else if(model.getShapes().get(maxIndex).isSelected() && model.getShapes().get(maxIndex).contains(x, y)){
				model.getShapes().get(maxIndex).setSelected(false);
				selectedShapes.remove(model.getShapes().get(maxIndex));
				//frame.getCommandsList().add(frame.getCommandsList().size(),"Unselected" + " " + shape.toString());
				frame.getCommandsList().add(frame.getCommandsList().size(),"Unselect" + " X: " + x + " Y: " + y);
				setChanged();
				notifyObservers();
			}
			
		}
		
		if(!frame.getTglbtnModify().isSelected() && !frame.getTglbtnDelete().isSelected() && !frame.getTglbtnSelect().isSelected()) {
			//unselect();
			
			if(frame.getTglbtnPoint().isSelected()) {
				Point p = new Point(e.getX(), e.getY(), borderColor);
				
				//model.add(p);
				
				stackAdd(new CommandAddShape(p,this.model, this));
				frame.getCommandsList().add(frame.getCommandsList().size(),"Added" + " " + p.toString());
				
				clearRedo();
				
				
				
			} else if (frame.getTglbtnLine().isSelected()) {

				if(click==0) {
					int x=e.getX();
					int y=e.getY();
					pStart= new Point(x,y);
					click++;
				}
				else if(click==1) {
					int x=e.getX();
					int y=e.getY();
					pEnd = new Point(x,y);

					Line l = new Line(pStart,pEnd, borderColor);
					//l.setBorderColor(frame.getBtnBorderColor().getBackground());
					stackAdd(new CommandAddShape(l,model, this));
					frame.getCommandsList().add(frame.getCommandsList().size(),"Added" + " " + l.toString());
					clearRedo();
					click=0;
				}
			} else if (frame.getTglbtnSquare().isSelected()) {
				Point p = new Point(e.getX(), e.getY(), Color.BLACK);
				int length = Integer.parseInt(JOptionPane.showInputDialog("Insert square length"));
				Square s = new Square(p, length, borderColor, insideColor);
				//model.add(s);
				stackAdd(new CommandAddShape(s,this.model, this));
				frame.getCommandsList().add(frame.getCommandsList().size(),"Added" + " " + s.toString());
				clearRedo();
				return;
			} else if (frame.getTglbtnRectangle().isSelected()) {
				Point p = new Point(e.getX(), e.getY(), Color.BLACK);
				int height = Integer.parseInt(JOptionPane.showInputDialog("Insert rectangle height"));
				int width = Integer.parseInt(JOptionPane.showInputDialog("Insert rectangle width"));
				Rectangle r = new Rectangle(p, height, width, borderColor, insideColor);
				//model.add(r);
				stackAdd(new CommandAddShape(r,this.model, this));
				frame.getCommandsList().add(frame.getCommandsList().size(),"Added" + " " + r.toString());
				clearRedo();
				return;
			} else if (frame.getTglbtnCircle().isSelected()) {
				Point p = new Point(e.getX(), e.getY(), Color.BLACK);
				int radius = Integer.parseInt(JOptionPane.showInputDialog("Insert cricle radius"));
				Circle c = new Circle(p, radius, borderColor, insideColor);
				//model.add(c);
				stackAdd(new CommandAddShape(c,this.model, this));
				frame.getCommandsList().add(frame.getCommandsList().size(),"Added" + " " + c.toString());
				clearRedo();
				return;
			} else if(frame.getTglbtnDonut().isSelected()) {
				Point p = new Point(e.getX(), e.getY(), Color.BLACK);
				int outterRadius = Integer.parseInt(JOptionPane.showInputDialog("Insert donut radius"));
				int innerRadius = Integer.parseInt(JOptionPane.showInputDialog("Insert inner donut radius"));
				Donut d = new Donut(p, outterRadius, innerRadius, borderColor, insideColor);
				//model.add(d);
				stackAdd(new CommandAddShape(d,this.model, this));
				frame.getCommandsList().add(frame.getCommandsList().size(),"Added" + " " + d.toString());
				clearRedo();
				return;
			}
			else if(frame.getTglbtnHexagon().isSelected()) {
				Point p = new Point(e.getX(), e.getY(), Color.BLACK);
				int r = Integer.parseInt(JOptionPane.showInputDialog("Insert hexagon radius"));
				HexagonAdapter h = new HexagonAdapter(e.getX(), e.getY(), r, borderColor, insideColor);
				//model.add(h);
				stackAdd(new CommandAddShape(h,this.model, this));
				frame.getCommandsList().add(frame.getCommandsList().size(),"Added" + " " + h.toString());
				clearRedo();
				return;
			}
		}


		frame.repaint();
	}


	public void borderColor(ActionEvent e) {
		// TODO Auto-generated method stub
		borderColor=JColorChooser.showDialog(null, "BorderColor Color", borderColor);
		if(borderColor!=null) {
			frame.getBtnBorderColor().setBackground(borderColor);
			frame.getCommandsList().add(frame.getCommandsList().size(), "BorderColor " + borderColor.getRGB());
		}
		
	}



	public void insideColor(ActionEvent e) {
		// TODO Auto-generated method stub
		insideColor=JColorChooser.showDialog(null, "Inside Color", insideColor);
		if(insideColor!=null) {
			frame.getBtnInsideColor().setBackground(insideColor);
			frame.getCommandsList().add(frame.getCommandsList().size(), "InsideColor" + insideColor.getRGB());
		}
	}

	public void reset(){
		unselect();
		//moze se i zakomentarisati
		frame.tglbtnSelect.setEnabled(true);

	}
	
	public void unselect(){
		ListIterator it1 = model.getShapes().listIterator();
		while(it1.hasNext()){
			Shape reset = (Shape) it1.next();
			reset.setSelected(false);
		}
		frame.getTglbtnModify().setEnabled(false);
		frame.getTglbtnDelete().setEnabled(false);
	}





	public void deleteShape() {
		// TODO Auto-generated method stub
		if(frame.getTglbtnDelete().isSelected()) {
			//System.out.println("delete kliknut");
			//Shape help;
			helpShapes = new ArrayList<Shape>();
			int answer = JOptionPane.showConfirmDialog(null, "Do you want to delete?");

			if(answer==JOptionPane.YES_OPTION){
				
				frame.getCommandsList().add(frame.getCommandsList().size(),"Delete " + selectedShapes);
				helpShapes = (ArrayList<Shape>) selectedShapes.clone();
				stackDelete(new CommandDeleteShapes(helpShapes, model, this));
				//probaj da obrises ovde helpShapes listu
				//helpShapes.clear();
				
				/*
				for(Shape shape: selectedShapes){
					help = shape;
					stackDelete(new CommandDeleteShape(help, model, this));
					if(selectedShapes.size() > 1) {
						frame.getCommandsList().add(frame.getCommandsList().size(),"MDeleted " + " " + shape.toString());
					} else {
						frame.getCommandsList().add(frame.getCommandsList().size(),"Deleted " + " " + shape.toString());
					}
					
//					if(this.model.getShapes().contains(shape)){
//						stackDelete(new CommandDeleteShape(shape, model));
//						selectedShapes.remove(shape);
//
//						System.out.println(selectedShapes.size());
//						//return;
//					}
				}
				*/
				
				
				
				//selectedShapes.clear();
				//helpShapes.clear();
				clearRedo();
				System.out.println("došao dovde");
				
			}
			setChanged();
			notifyObservers();
		}
		
	}





	public void modifyShape() {
		// TODO Auto-generated method stub
		try {
			ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
			while(it.hasPrevious()){
				Shape help = (Shape)it.previous();
				if(help.isSelected()) {
					if(help instanceof Rectangle){
						DlgRectangle dlgP = new DlgRectangle();
						Rectangle p1 = (Rectangle) help;
						dlgP.getTxtXCoordinate().setText(String.valueOf(p1.getUpLeft().getX()));
						dlgP.getTxtYCoordinate().setText(String.valueOf(p1.getUpLeft().getY()));
						dlgP.getTxtHeight().setText(String.valueOf(p1.getHeight()));
						dlgP.getTxtWidth().setText(String.valueOf(p1.getWidth()));
						dlgP.getBtnBorderColor().setBackground(p1.getBorderColor());
						dlgP.getBtnInsideColor().setBackground(p1.getAreaColor());
						dlgP.setVisible(true);
						Rectangle r = dlgP.getHelp();
						if(dlgP.isConfirm()){
							//model.remove(help);
							//model.add(r);

							//selectedShapes.clear();


							stackAdd(new CommandModifyShape(help,r,this.model, this));
							
							
							
							//MOŽE I OVAKO ALI JE BOLJE U COMMAND KLASI
							/*
							selectedShapes.clear();
							selectedShapes.add(r);
							r.setSelected(true);
							*/
							//selectedShapes.add(r);

							//r.setSelected(true);
							frame.getCommandsList().add(frame.getCommandsList().size(),"Modify " + help.toString() + " To " + r.toString());
							setChanged();
							notifyObservers();

							//System.out.println(selectedShapes);


//							frame.getTglbtnModify().setEnabled(false);
//							frame.getTglbtnDelete().setEnabled(false);
//							frame.getBtnToBack().setEnabled(false);
//							frame.getBtnToFront().setEnabled(false);
							//frame.getTglbtnSelect().setSelected(true);
							frame.getTglbtnSelect().setSelected(true);
							//reset();
							//frame.repaint();
							return;

						}
						else if(!dlgP.isConfirm()){
							//help.setSelected(false);
//							frame.getTglbtnModify().setEnabled(false);
//							frame.getTglbtnDelete().setEnabled(false);
//							frame.getBtnToBack().setEnabled(false);
//							frame.getBtnToFront().setEnabled(false);
							frame.getTglbtnSelect().setSelected(true);
//
							setChanged();
							notifyObservers();
							frame.repaint();
							return;
						}
					}
					
					else if(help instanceof Point){
						DlgPoint dlgT = new DlgPoint();
						Point p1 = (Point) help;
						dlgT.getTxtXCoordinate().setText(String.valueOf(p1.getX()));
						dlgT.getTxtYCoordinate().setText(String.valueOf(p1.getY()));
						dlgT.getBtnBorderColor().setBackground(p1.getBorderColor());
						dlgT.setVisible(true);
						Point p2 = dlgT.getHelp();
						if(dlgT.isConfirm()){
							//model.remove(help);
							//model.add(p2);
							stackAdd(new CommandModifyShape(help,p2,this.model, this));
//							frame.getTglbtnModify().setEnabled(false);
//							frame.getTglbtnDelete().setEnabled(false);
//							frame.getBtnToBack().setEnabled(false);
//							frame.getBtnToFront().setEnabled(false);
//							frame.getTglbtnSelect().setSelected(true);
//							reset();
							//p2.setSelected(true);
							frame.getCommandsList().add(frame.getCommandsList().size(),"Modify " + help.toString() + " To " + p2.toString());
							setChanged();
							notifyObservers();
							frame.getTglbtnSelect().setSelected(true);
							return;
						}
						else if(!dlgT.isConfirm()){
//							help.setSelected(false);
//							frame.getTglbtnModify().setEnabled(false);
//							frame.getTglbtnDelete().setEnabled(false);
//							frame.getBtnToBack().setEnabled(false);
//							frame.getBtnToFront().setEnabled(false);
//							frame.getTglbtnSelect().setSelected(true);
//							reset();
//							return;
							frame.getTglbtnSelect().setSelected(true);

							setChanged();
							notifyObservers();
							frame.repaint();
							return;
						}
					}
					
					else if(help instanceof Circle && !(help instanceof Donut)){
						DlgCircle dlgK = new DlgCircle();
						Circle k1 = (Circle) help;
						dlgK.getTxtXCoordinate().setText(String.valueOf(k1.getCenter().getX()));
						dlgK.getTxtYCoordinate().setText(String.valueOf(k1.getCenter().getY()));
						dlgK.getTxtRadius().setText(String.valueOf(k1.getR()));
						dlgK.getBtnBorderColor().setBackground(k1.getBorderColor());
						dlgK.getBtnInsideColor().setBackground(k1.getAreaColor());
						dlgK.setVisible(true);
						Circle c2 = dlgK.getK();
						if(dlgK.isConfirm()){
							//model.remove(help);
							//model.add(c2);
							stackAdd(new CommandModifyShape(help,c2,this.model, this));
//							frame.getTglbtnModify().setEnabled(false);
//							frame.getTglbtnDelete().setEnabled(false);
//							frame.getBtnToBack().setEnabled(false);
//							frame.getBtnToFront().setEnabled(false);
//							frame.getTglbtnSelect().setSelected(true);
//							reset();
//							return;
							//c2.setSelected(true);
							frame.getCommandsList().add(frame.getCommandsList().size(),"Modify " + help.toString() + " To " + c2.toString());
							setChanged();
							notifyObservers();
							frame.getTglbtnSelect().setSelected(true);
							return;
						}
						else if(!dlgK.isConfirm()){
//							help.setSelected(false);
//							frame.getTglbtnModify().setEnabled(false);
//							frame.getTglbtnDelete().setEnabled(false);
//							frame.getBtnToBack().setEnabled(false);
//							frame.getBtnToFront().setEnabled(false);
//							frame.getTglbtnSelect().setSelected(true);
//							reset();
//							return;
							frame.getTglbtnSelect().setSelected(true);
//
							setChanged();
							notifyObservers();
							frame.repaint();
							return;
						}
					}
					
					else if(help instanceof Line) {
						DlgLine dlgL = new DlgLine();
						Line l1 = (Line) help;
						dlgL.setHelpStart(l1.getPStart());
						dlgL.setHelpEnd(l1.getPEnd());
						dlgL.getBtnBorderColor().setBackground(l1.getBorderColor());
						dlgL.setVisible(true);
						Line l2 = dlgL.getLine();
						if(dlgL.isConfirm()){
							//model.remove(help);
							//model.add(l2);
							stackAdd(new CommandModifyShape(help,l2,this.model, this));
							
//							frame.getTglbtnModify().setEnabled(false);
//							frame.getTglbtnDelete().setEnabled(false);
//							frame.getBtnToBack().setEnabled(false);
//							frame.getBtnToFront().setEnabled(false);
//							frame.getTglbtnSelect().setSelected(true);
//							reset();
//							return;
							//l2.setSelected(true);
							frame.getCommandsList().add(frame.getCommandsList().size(),"Modify "+ help.toString() + " To " + l2.toString());
							setChanged();
							notifyObservers();
							frame.getTglbtnSelect().setSelected(true);
							return;
						}
						else if(!dlgL.isConfirm()){
//							help.setSelected(false);
//							frame.getTglbtnModify().setEnabled(false);
//							frame.getTglbtnDelete().setEnabled(false);
//							frame.getBtnToBack().setEnabled(false);
//							frame.getBtnToFront().setEnabled(false);
//							frame.getTglbtnSelect().setSelected(true);
//							reset();
//							return;
							frame.getTglbtnSelect().setSelected(true);
//
							setChanged();
							notifyObservers();
							frame.repaint();
							return;
						}
					}
					
					else if(help instanceof Square){
						DlgSquare dlgKv = new DlgSquare();
						Square s1 = (Square) help;
						dlgKv.getTxtXCoordinate().setText(String.valueOf(s1.getUpLeft().getX()));
						dlgKv.getTxtYCoordinate().setText(String.valueOf(s1.getUpLeft().getY()));
						dlgKv.getTxtBorderLength().setText(String.valueOf(s1.getBorderLength()));
						dlgKv.getBtnBorderColor().setBackground(s1.getBorderColor());
						dlgKv.getBtnInsideColor().setBackground(s1.getAreaColor());
						dlgKv.setVisible(true);
						Square s2 = dlgKv.getHelp();
						
						if(dlgKv.isConfirm()){
							//model.remove(help);
							//model.add(s2);
							stackAdd(new CommandModifyShape(help,s2,this.model, this));
//							frame.getTglbtnModify().setEnabled(false);
//							frame.getTglbtnDelete().setEnabled(false);
//							frame.getTglbtnSelect().setSelected(true);
//							reset();
//							return;
							//s2.setSelected(true);
							frame.getCommandsList().add(frame.getCommandsList().size(),"Modify " + help.toString() + " To " + s2.toString());
							setChanged();
							notifyObservers();
							frame.getTglbtnSelect().setSelected(true);
							return;
						}
						else if(!dlgKv.isConfirm()){
//							help.setSelected(false);
//							frame.getTglbtnModify().setEnabled(false);
//							frame.getTglbtnDelete().setEnabled(false);
//							frame.getTglbtnSelect().setSelected(true);
//							reset();
//							return;
							frame.getTglbtnSelect().setSelected(true);
//
							setChanged();
							notifyObservers();
							frame.repaint();
							return;
						}
						
					}
					
					else if(help instanceof Donut) {
						DlgDonut dlgD = new DlgDonut();
						Donut d1 = (Donut) help;
						
						dlgD.getTxtXCoordinate().setText(String.valueOf(d1.getCenter().getX()));
						dlgD.getTxtYCoordinate().setText(String.valueOf(d1.getCenter().getY()));
						dlgD.getTxtOutter().setText(String.valueOf(d1.getR()));
						dlgD.getTxtInner().setText(String.valueOf(d1.getInnerRadius()));
						dlgD.getBtnBorderColor().setBackground(d1.getBorderColor());
						dlgD.getBtnInsideColor().setBackground(d1.getAreaColor());
						
						dlgD.setVisible(true);
						
						Donut d2 = dlgD.getDonutHelp();
						
						//Donut donutP = new Donut(new Tacka(15,50), 77, 22, Color.GREEN, Color.BLACK);
						//dlgD.setPotvrda(true);
						if(dlgD.isConfirm()) {
							//model.remove(help);
							//model.add(d2);
							stackAdd(new CommandModifyShape(help,d2,this.model, this));
							
//							frame.getTglbtnModify().setEnabled(false);
//							frame.getTglbtnDelete().setEnabled(false);
//							frame.getTglbtnSelect().setSelected(true);
//							reset();
//							return;
							//d2.setSelected(true);
							frame.getCommandsList().add(frame.getCommandsList().size(),"Modify " + help.toString() + " To " + d2.toString());
							setChanged();
							notifyObservers();
							frame.getTglbtnSelect().setSelected(true);
							return;
						} else if(!dlgD.isConfirm()) {
//							help.setSelected(false);
//							frame.getTglbtnModify().setEnabled(false);
//							frame.getTglbtnDelete().setEnabled(false);
//							frame.getTglbtnSelect().setSelected(true);
//							reset();
//							return;
							frame.getTglbtnSelect().setSelected(true);
//
							setChanged();
							notifyObservers();
							frame.repaint();
							return;
						}
						
					}
					
					else if(help instanceof HexagonAdapter){
						//System.out.println("ADAPTER");
						DlgHexagon dlgH = new DlgHexagon();
						HexagonAdapter h1 = (HexagonAdapter) help;
						System.out.println(h1 instanceof Shape);
						
						dlgH.getTxtXCoordinate().setText(String.valueOf(h1.getHexagon().getX()));
						dlgH.getTxtYCoordinate().setText(String.valueOf(h1.getHexagon().getY()));
						dlgH.getTxtRadius().setText(String.valueOf(h1.getHexagon().getR()));
						dlgH.getBtnInsideColor().setBackground(h1.getHexagon().getAreaColor());
						dlgH.getBtnBorderColor().setBackground(h1.getHexagon().getBorderColor());
						
						dlgH.setVisible(true);
						
						HexagonAdapter h2 = dlgH.getH(); 
						//HexagonAdapter h2 = new HexagonAdapter(250,250,33,Color.BLACK,Color.BLUE);
						
						if(dlgH.isConfirm()) {
							System.out.println("uï¿½ao u true");
							//model.remove(help);
							//model.add(h2);
							stackAdd(new CommandModifyShape(help,h2,this.model, this));
							
//							frame.getTglbtnModify().setEnabled(false);
//							frame.getTglbtnDelete().setEnabled(false);
//							frame.getTglbtnSelect().setSelected(true);
//							reset();
//							return;
							//h2.setSelected(true);
							frame.getCommandsList().add(frame.getCommandsList().size(),"Modify " + help.toString() + " To " + h2.toString());
							setChanged();
							notifyObservers();
							frame.getTglbtnSelect().setSelected(true);
							return;
						} else if(!dlgH.isConfirm()) {
//							System.out.println("usao u false");
//							help.setSelected(false);
//							frame.getTglbtnModify().setEnabled(false);
//							frame.getTglbtnDelete().setEnabled(false);
//							frame.getTglbtnSelect().setSelected(true);
//							reset();
//							return;
							frame.getTglbtnSelect().setSelected(true);
//
							setChanged();
							notifyObservers();
							frame.repaint();
							return;
						}
						
					}
					
				}
			
			}
			
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Error","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void stackAdd(Command cmd) {
		cmd.execute();



		undoStack.push(cmd);
		
		
		
		if(!this.undoStack.isEmpty()) {
			frame.getBtnUndo().setEnabled(true);
		}
		
		
		
	}
	
	private void stackDelete(Command cmd) {
		
		cmd.execute();
		
		undoStack.push(cmd);
		
		
		
		
		if(!this.undoStack.isEmpty()) {
			frame.getBtnUndo().setEnabled(true);
		}
	}

	
	private void clearRedo() {
		
		if(!redoStack.isEmpty()) {
			redoStack.clear();
		}
		
		
		if(redoStack.isEmpty()) {
			frame.getBtnRedo().setEnabled(false);
		}
	}

	public void undo() {
		// TODO Auto-generated method stub
		
		Command cmdpop = undoStack.pop();
		redoStack.push(cmdpop);
		
		cmdpop.unexecute();


		frame.getCommandsList().add(frame.getCommandsList().size(),"Undo");
	
		setChanged();
		notifyObservers();
		
		if(this.undoStack.isEmpty()) {
			frame.getBtnUndo().setEnabled(false);
		}
		if(!this.redoStack.isEmpty()) {
			frame.getBtnRedo().setEnabled(true);
		}
		
	}

	public void redo() {
		// TODO Auto-generated method stub
		
		Command cmdpop = redoStack.pop();
		undoStack.push(cmdpop);
		
		cmdpop.execute();

		frame.getCommandsList().add(frame.getCommandsList().size(),"Redo");
		
		setChanged();
		notifyObservers();
		
		
		
		if(this.redoStack.isEmpty()) {
			frame.getBtnRedo().setEnabled(false);
		}
		if(!this.undoStack.isEmpty()) {
			frame.getBtnUndo().setEnabled(true);
		}
	}



	public void toBack() {
		
		ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
		while(it.hasPrevious()) {
			Shape o = (Shape) it.previous();
			if(o.isSelected()) {
				int answer = JOptionPane.showConfirmDialog(null, "Do you want to bring this shape to back?");
				if(answer == JOptionPane.YES_OPTION) {
					//model.getShapes().remove(o);
					stackAdd(new CommandToBack(o, model));
					frame.getCommandsList().add(frame.getCommandsList().size(),"ToBack" + " " + o.toString());
					clearRedo();
					setChanged();
					notifyObservers();
					/*
					frame.getTglbtnModify().setEnabled(false);
					frame.getTglbtnDelete().setEnabled(false);
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
					frame.getTglbtnSelect().setSelected(true);
					*/
					return;
					
				} else {
					o.setSelected(true);
					setChanged();
					notifyObservers();
					/*
					frame.getTglbtnModify().setEnabled(false);
					frame.getTglbtnDelete().setEnabled(false);
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
					frame.getTglbtnSelect().setSelected(true);
					*/
					//reset();
				}
			} 
		}
		
	}



	public void toFront() {
		ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
		while(it.hasPrevious()) {
			Shape o = (Shape)it.previous();
			if(o.isSelected()) {
				int answer = JOptionPane.showConfirmDialog(null, "Do you want to bring this shape to front?");
				if(answer == JOptionPane.YES_OPTION) {
					//model.getShapes().remove(o);
					stackAdd(new CommandToFront(o, model));
					/*
					frame.getTglbtnModify().setEnabled(false);
					frame.getTglbtnDelete().setEnabled(false);
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
					frame.getTglbtnSelect().setSelected(true);
					*/
					frame.getCommandsList().add(frame.getCommandsList().size(),"ToFront" + " " + o.toString());
					clearRedo();
					setChanged();
					notifyObservers();
					return;
					
				} else {
					o.setSelected(true);
					setChanged();
					notifyObservers();
					/*
					frame.getTglbtnModify().setEnabled(false);
					frame.getTglbtnDelete().setEnabled(false);
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
					frame.getTglbtnSelect().setSelected(true);
					*/
					//reset();
				}
			} 
			
		}
		
	}


	public void bringToBack() {
		// TODO Auto-generated method stub
		
		ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
		while(it.hasPrevious()) {
			Shape o = (Shape)it.previous();
			if(o.isSelected()) {
				int answer = JOptionPane.showConfirmDialog(null, "Do you want to bring this shape to front?");
				if(answer == JOptionPane.YES_OPTION) {
					//model.getShapes().remove(o);
					stackAdd(new CommandBringToBack(o, model));
					/*
					frame.getTglbtnModify().setEnabled(false);
					frame.getTglbtnDelete().setEnabled(false);
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
					frame.getTglbtnSelect().setSelected(true);
					*/
					frame.getCommandsList().add(frame.getCommandsList().size(),"BTB" + " " + o.toString());
					clearRedo();
					setChanged();
					notifyObservers();
					return;
					
				} else {
					o.setSelected(true);
					/*
					frame.getTglbtnModify().setEnabled(false);
					frame.getTglbtnDelete().setEnabled(false);
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
					frame.getTglbtnSelect().setSelected(true);
					reset();
					*/
					setChanged();
					notifyObservers();
				}
			} 
			
		}
	}


	public void bringToFront() {
		// TODO Auto-generated method stub
		ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
		while(it.hasPrevious()) {
			Shape o = (Shape)it.previous();
			if(o.isSelected()) {
				int answer = JOptionPane.showConfirmDialog(null, "Do you want to bring this shape to front?");
				if(answer == JOptionPane.YES_OPTION) {
					//model.getShapes().remove(o);
					stackAdd(new CommandBringToFront(o, model));
					/*
					frame.getTglbtnModify().setEnabled(false);
					frame.getTglbtnDelete().setEnabled(false);
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
					frame.getTglbtnSelect().setSelected(true);
					*/
					frame.getCommandsList().add(frame.getCommandsList().size(),"BTF" + " " + o.toString());
					clearRedo();
					setChanged();
					notifyObservers();
					return;
					
				} else {
					o.setSelected(true);
					/*
					frame.getTglbtnModify().setEnabled(false);
					frame.getTglbtnDelete().setEnabled(false);
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
					frame.getTglbtnSelect().setSelected(true);
					reset();
					*/
					setChanged();
					notifyObservers();
				}
			} 
			
		}
		
	}
	
	public void saveDrawingFile() {
		savingStrategy = new SaveDrawingLog(model,frame);
		savingStrategy.saveFile();
	}
	
	public void loadDrawingFile() {
		model.getShapes().clear();
		frame.getCommandsList().clear();
		JFileChooser fileChooser = new JFileChooser();
		int userSelection = fileChooser.showSaveDialog(frame);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave;
			fileToSave = new File(fileChooser.getSelectedFile().getAbsolutePath());
			FileInputStream fis; 
			try {
				fis = new FileInputStream(fileToSave);
				try (ObjectInputStream input = new ObjectInputStream(fis)) {
					while(fis.available() != 0) {
						Object obj = input.readObject();
						model.add((Shape)obj);
						frame.getCommandsList().add(frame.getCommandsList().size(),"DrawLoad" + " " + obj.toString());
					}
				    
				  } catch (Exception e) {
				    // System.out.println(e.printStackTrace());
				  }
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void saveTextFile() {
		// TODO Auto-generated method stub
		savingStrategy = new SaveTextLog(frame);
		savingStrategy.saveFile();
	}


	public void loadTextFile() {
		// TODO Auto-generated method stub
		model.getShapes().clear();
		frame.getCommandsList().clear();
		load = new ArrayList<String>();
		JFileChooser fileChooser = new JFileChooser();
		int userSelection = fileChooser.showSaveDialog(frame);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave;
			fileToSave = new File(fileChooser.getSelectedFile().getAbsolutePath());
			try {
				Scanner scan = new Scanner(fileToSave);
				while(scan.hasNext()) {
					load.add(scan.nextLine());
					
				}
				frame.getBtnNextLogLine().setEnabled(true);
				for(int i = 0; i<load.size(); i++) {
					System.out.println(i + " " + load.get(i));
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i =0; i<model.getShapes().size(); i++) {
			model.get(i).setSelected(false);
		}
	}
	
	public void nextLogLine() {
		// TODO Auto-generated method stub
		
		if(loadIterator < load.size()) {
			helpIteratorString = load.get(loadIterator);
			
			String helpArray[] = helpIteratorString.split(" ");
			
			switch (helpArray[0]) {
			case "Added": System.out.println("ulazi u add"); logAdding(helpArray); break;
			case "Select": System.out.println("ulazi u select"); logSelect(helpArray); break;
			case "Unselect": System.out.println("ulazi u unselect"); logUnselect(helpArray); break;
			case "ToFront": System.out.println("ulazi u tofront"); logToFront(); break;
			case "ToBack": System.out.println("ulazi u toback"); logToBack(); break;
			case "BTF": System.out.println("ulazi u btf"); logBTF(); break;
			case "BTB": System.out.println("ulazi u btb"); logBTB(); break;
			case "Undo": System.out.println("ulazi u undo"); logUndo(); break;
			case "Redo": System.out.println("ulazi u redo"); logRedo(); break;
			case "Modify": System.out.println("ulazi u modify"); logModify(helpArray); break;
			case "Delete": System.out.println("ulazi u delete"); logDelete(helpArray); break;
			}
				
			
		}
		loadIterator++;
		if(loadIterator == load.size()) {
			frame.getBtnNextLogLine().setEnabled(false);
		}
		
		
		/*
		if(loadIterator == load.size()) {
			frame.getBtnNextLogLine().setEnabled(false);
		} else {
			helpIteratorString = load.get(loadIterator);
			
			st = new StringTokenizer(helpIteratorString, " ");
			System.out.println(helpIteratorString);
		}
		*/
		
	}
	
	private void logDelete(String[] helpArray) {
		// TODO Auto-generated method stub
		helpShapes = (ArrayList<Shape>) selectedShapes.clone();
		stackDelete(new CommandDeleteShapes(helpShapes, model, this));
		
		setChanged();
		notifyObservers();
	}


	private void logModify(String[] helpArray) {
		// TODO Auto-generated method stub
		switch (helpArray[1]) {
		case "Point" : 
			Point helpP = new Point(Integer.parseInt(helpArray[3]),Integer.parseInt(helpArray[5]), new Color(Integer.parseInt(helpArray[7]))); 
			Point newPoint = new Point(Integer.parseInt(helpArray[11]),Integer.parseInt(helpArray[13]), new Color(Integer.parseInt(helpArray[15]))); 
			stackAdd(new CommandModifyShape(helpP,newPoint,this.model, this));
			break;
		case "Line" : 
			Point helpStart = new Point(Integer.parseInt(helpArray[3]), Integer.parseInt(helpArray[5]));
			Point helpEnd = new Point(Integer.parseInt(helpArray[7]), Integer.parseInt(helpArray[9]));
			Line helpL = new Line(helpStart,helpEnd, new Color(Integer.parseInt(helpArray[11])));
			Point pStart = new Point(Integer.parseInt(helpArray[15]), Integer.parseInt(helpArray[17]));
			Point pEnd = new Point(Integer.parseInt(helpArray[19]), Integer.parseInt(helpArray[21]));
			Line newLine = new Line(pStart,pEnd, new Color(Integer.parseInt(helpArray[23])));
			stackAdd(new CommandModifyShape(helpL,newLine,this.model, this));
			break;
		case "Square" : 
			Point helpUpLeftS = new Point(Integer.parseInt(helpArray[5]),Integer.parseInt(helpArray[7]));
			Square helpS = new Square(helpUpLeftS, Integer.parseInt(helpArray[9]), 
					new Color(Integer.parseInt(helpArray[11])), new Color(Integer.parseInt(helpArray[13])));
			Point newUpLeftS = new Point(Integer.parseInt(helpArray[19]),Integer.parseInt(helpArray[21]));
			Square newSquare = new Square(newUpLeftS, Integer.parseInt(helpArray[23]), 
					new Color(Integer.parseInt(helpArray[25])), new Color(Integer.parseInt(helpArray[27])));
			stackAdd(new CommandModifyShape(helpS,newSquare,this.model, this));
			break;
		case "Circle" :
			Point helpCenterC = new Point(Integer.parseInt(helpArray[4]), Integer.parseInt(helpArray[6]));
			Circle helpC = new Circle(helpCenterC, Integer.parseInt(helpArray[8]), 
					new Color(Integer.parseInt(helpArray[10])), new Color(Integer.parseInt(helpArray[12])));
			Point newCenter = new Point(Integer.parseInt(helpArray[17]), Integer.parseInt(helpArray[19]));
			Circle newCircle = new Circle(newCenter, Integer.parseInt(helpArray[21]), 
					new Color(Integer.parseInt(helpArray[23])), new Color(Integer.parseInt(helpArray[25])));
			stackAdd(new CommandModifyShape(helpC,newCircle,this.model, this));
			break;
		case "Rectangle":
			Point helpUpLeftR = new Point(Integer.parseInt(helpArray[5]),Integer.parseInt(helpArray[7]));
			Rectangle helpR = new Rectangle(helpUpLeftR, Integer.parseInt(helpArray[9]), Integer.parseInt(helpArray[11]), 
					new Color(Integer.parseInt(helpArray[13])), new Color(Integer.parseInt(helpArray[15])));
			Point newUpLeftR = new Point(Integer.parseInt(helpArray[21]),Integer.parseInt(helpArray[23]));
			Rectangle newRectangle = new Rectangle(newUpLeftR, Integer.parseInt(helpArray[25]), Integer.parseInt(helpArray[27]), 
					new Color(Integer.parseInt(helpArray[29])), new Color(Integer.parseInt(helpArray[31])));
			stackAdd(new CommandModifyShape(helpR,newRectangle,this.model, this));
			break;
		case "Donut" :
			Point helpCenterD = new Point(Integer.parseInt(helpArray[4]), Integer.parseInt(helpArray[6]));
			Donut helpD = new Donut(helpCenterD, Integer.parseInt(helpArray[8]), Integer.parseInt(helpArray[10]), new Color(Integer.parseInt(helpArray[12])), new Color(Integer.parseInt(helpArray[14])));
			Point newCenterD = new Point(Integer.parseInt(helpArray[19]), Integer.parseInt(helpArray[21]));
			Donut newDonut = new Donut(newCenterD, Integer.parseInt(helpArray[23]), Integer.parseInt(helpArray[25]), new Color(Integer.parseInt(helpArray[27])), new Color(Integer.parseInt(helpArray[29])));
			stackAdd(new CommandModifyShape(helpD,newDonut,this.model, this));
			break;
		case "Hexagon":
			HexagonAdapter helpH = new HexagonAdapter(Integer.parseInt(helpArray[4]), Integer.parseInt(helpArray[6]), Integer.parseInt(helpArray[8]), 
					new Color(Integer.parseInt(helpArray[10])), new Color(Integer.parseInt(helpArray[12])));
			HexagonAdapter newHexagon = new HexagonAdapter(Integer.parseInt(helpArray[17]), Integer.parseInt(helpArray[19]), Integer.parseInt(helpArray[21]), 
					new Color(Integer.parseInt(helpArray[23])), new Color(Integer.parseInt(helpArray[25])));
			stackAdd(new CommandModifyShape(helpH,newHexagon,this.model, this));
			break;		
		}
	}


	private void logRedo() {
		// TODO Auto-generated method stub
		Command cmdpop = redoStack.pop();
		undoStack.push(cmdpop);
		
		cmdpop.execute();
		setChanged();
		notifyObservers();
		
	}





	private void logUndo() {
		// TODO Auto-generated method stub
		Command cmdpop = undoStack.pop();
		redoStack.push(cmdpop);
		
		cmdpop.unexecute();
		setChanged();
		notifyObservers();
		
	}





	private void logBTB() {
		// TODO Auto-generated method stub
		ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
		while(it.hasPrevious()) {
			Shape o = (Shape)it.previous();
			if(o.isSelected()) {
				stackAdd(new CommandBringToBack(o, model));
				setChanged();
				notifyObservers();
				return;
				}
		}
	}





	private void logBTF() {
		// TODO Auto-generated method stub
		ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
		while(it.hasPrevious()) {
			Shape o = (Shape)it.previous();
			if(o.isSelected()) {
				stackAdd(new CommandBringToFront(o, model));
				setChanged();
				notifyObservers();
				return;
				}
		}
	}





	private void logToBack() {
		// TODO Auto-generated method stub
		ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
		while(it.hasPrevious()) {
			Shape o = (Shape)it.previous();
			if(o.isSelected()) {
				stackAdd(new CommandToBack(o, model));
				setChanged();
				notifyObservers();
				return;
				}
		}
	}





	private void logToFront() {
		// TODO Auto-generated method stub
		ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
		while(it.hasPrevious()) {
			Shape o = (Shape)it.previous();
			if(o.isSelected()) {
				stackAdd(new CommandToFront(o, model));
				setChanged();
				notifyObservers();
				return;
				}
		}
	}





	private void logUnselect(String[] helpArray) {
		// TODO Auto-generated method stub
		for (Shape shape : model.getShapes()) {
			if (shape.contains(Integer.parseInt(helpArray[2]), Integer.parseInt(helpArray[4]))) {
				if (shape.isSelected()) {
					selected = false;
					shape.setSelected(false);
					shape.selected(frame.getView().getGraphics());
					selectedShapes.remove(shape);
					//frame.getCommandsList().add(frame.getCommandsList().size(),"Selected " + " " + shape.toString());
					//frame.getCommandsList().add(frame.getCommandsList().size(),"Selected " + " X: " + x + " Y: " + y);
					setChanged();
					notifyObservers();
				} 
			}
		}
	}





	private void logSelect(String[] helpArray) {
		// TODO Auto-generated method stub
		
		
		for (Shape shape : model.getShapes()) {
			if (shape.contains(Integer.parseInt(helpArray[2]), Integer.parseInt(helpArray[4]))) {
				if (!shape.isSelected()) {
					selected = true;
					shape.setSelected(true);
					shape.selected(frame.getView().getGraphics());
					selectedShapes.add(shape);
					
					//frame.getCommandsList().add(frame.getCommandsList().size(),"Selected " + " " + shape.toString());
					//frame.getCommandsList().add(frame.getCommandsList().size(),"Selected " + " X: " + x + " Y: " + y);
					setChanged();
					notifyObservers();
				} 
			}
		}
		
	}





	public void logAdding(String helpArray[]) {
		System.out.println("ulazi u metodu");
		switch (helpArray[1]) {
		case "Point" : 
			Point p = new Point(Integer.parseInt(helpArray[3]),Integer.parseInt(helpArray[5]), new Color(Integer.parseInt(helpArray[7]))); 
			stackAdd(new CommandAddShape(p,this.model, this));
			break;
		case "Line" : 
			Point pStart = new Point(Integer.parseInt(helpArray[3]), Integer.parseInt(helpArray[5]));
			Point pEnd = new Point(Integer.parseInt(helpArray[7]), Integer.parseInt(helpArray[9]));
			Line l = new Line(pStart,pEnd, new Color(Integer.parseInt(helpArray[11])));
			stackAdd(new CommandAddShape(l,this.model, this));
			break;
		case "Square" : 
			Point ps = new Point(Integer.parseInt(helpArray[5]),Integer.parseInt(helpArray[7]));
			Square s = new Square(ps, Integer.parseInt(helpArray[9]), new Color(Integer.parseInt(helpArray[11])), new Color(Integer.parseInt(helpArray[13])));
			stackAdd(new CommandAddShape(s,this.model, this));
			break;
		case "Circle" :
			Point pc = new Point(Integer.parseInt(helpArray[4]), Integer.parseInt(helpArray[6]));
			Circle c = new Circle(pc, Integer.parseInt(helpArray[8]), new Color(Integer.parseInt(helpArray[10])), new Color(Integer.parseInt(helpArray[12])));
			stackAdd(new CommandAddShape(c,this.model, this));
			break;
		case "Rectangle":
			Point pr = new Point(Integer.parseInt(helpArray[5]),Integer.parseInt(helpArray[7]));
			Rectangle r = new Rectangle(pr, Integer.parseInt(helpArray[9]), Integer.parseInt(helpArray[11]), new Color(Integer.parseInt(helpArray[13])), new Color(Integer.parseInt(helpArray[15])));
			stackAdd(new CommandAddShape(r,this.model, this));
			break;
		case "Donut" :
			Point pd = new Point(Integer.parseInt(helpArray[4]), Integer.parseInt(helpArray[6]));
			Donut d = new Donut(pd, Integer.parseInt(helpArray[8]), Integer.parseInt(helpArray[10]), new Color(Integer.parseInt(helpArray[12])), new Color(Integer.parseInt(helpArray[14])));
			//model.add(d);
			stackAdd(new CommandAddShape(d,this.model, this));
			break;
		case "Hexagon":
			HexagonAdapter h = new HexagonAdapter(Integer.parseInt(helpArray[4]), Integer.parseInt(helpArray[6]), Integer.parseInt(helpArray[8]), 
					new Color(Integer.parseInt(helpArray[10])), new Color(Integer.parseInt(helpArray[12])));
			stackAdd(new CommandAddShape(h,this.model, this));
			break;		
		}
	}


	@Override
	public synchronized void addObserver(Observer o) {
		// TODO Auto-generated method stub
		super.addObserver(o);
	}


	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}


	public Frame getFrame() {
		return frame;
	}


	public ArrayList<Shape> getHelpShapes() {
		return helpShapes;
	}

}
