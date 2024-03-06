package wb;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.border.MatteBorder;

import geometry.Donut;
import geometry.Circle;
import geometry.Square;
import geometry.Line;
import geometry.Shape;
import geometry.Rectangle;
import geometry.Point;

import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ListIterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Crtanje extends JFrame {

	private JPanel contentPaneMain;
	private CrtanjeView view;
	private JToggleButton tglBtnSelect;
	private JToggleButton tglBtnModify;
	private JToggleButton tglBtnDelete;
	private JToggleButton tglBtnPoint;
	private JToggleButton tglBtnLine;
	private JToggleButton tglBtnSquare;
	private JToggleButton tglBtnRectangle;
	private JToggleButton tglBtnCircle;
	private JToggleButton tglbtnDonut;
	private JButton btnBorderColor;
	private JButton btnInsideColor;
	private JLabel lblFunctions;
	private JLabel lblColors;
	private JLabel lblShapes;
	private JPanel pnlColors;
	private JPanel pnlShapes;
	private JPanel pnlFunctions;
	private JScrollPane scrollPane;
	private final ButtonGroup btnGrpFunctionsAndShapes = new ButtonGroup();
	private Color borderColorr;
	private Color insideColorr;
	private Color borderColor=Color.BLACK;
	private Color insideColor=Color.WHITE;
	private int x;
	private int y;
	private Point pointStart;
	private Point pointEnd;
	private int click=0;
	private Line l1;
	private Square k1;
	private Donut d1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Crtanje frame = new Crtanje();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Crtanje() {
		setMinimumSize(new Dimension(700, 400));
		setMaximumSize(new Dimension(800, 600));
		setTitle("IT 43-2015 Kristian Kle\u010Dina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPaneMain = new JPanel();
		view = new CrtanjeView();
		contentPaneMain.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				view.paint(view.getGraphics());
			}
		});
		contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneMain);
		
		JPanel panel = new JPanel();
		
		scrollPane = new JScrollPane();
		GroupLayout gl_cpnMain = new GroupLayout(contentPaneMain);
		gl_cpnMain.setHorizontalGroup(
			gl_cpnMain.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
		);
		gl_cpnMain.setVerticalGroup(
			gl_cpnMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cpnMain.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
		);
		
		pnlFunctions = new JPanel();
		pnlFunctions.setBackground(Color.LIGHT_GRAY);
		pnlFunctions.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		
		pnlShapes = new JPanel();
		pnlShapes.setBackground(Color.LIGHT_GRAY);
		pnlShapes.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		
		pnlColors = new JPanel();
		pnlColors.setBackground(Color.LIGHT_GRAY);
		pnlColors.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(pnlFunctions, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlShapes, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlColors, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlColors, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(pnlShapes, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(pnlFunctions, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		lblShapes = new JLabel("SHAPES");
		lblShapes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		tglBtnPoint = new JToggleButton("Point");
		btnGrpFunctionsAndShapes.add(tglBtnPoint);
		
		tglBtnLine = new JToggleButton("Line");
		btnGrpFunctionsAndShapes.add(tglBtnLine);
		
		tglBtnCircle = new JToggleButton("Circle");
		btnGrpFunctionsAndShapes.add(tglBtnCircle);
		
		tglBtnSquare = new JToggleButton("Square");
		btnGrpFunctionsAndShapes.add(tglBtnSquare);
		
		tglBtnRectangle = new JToggleButton("Rectangle");
		btnGrpFunctionsAndShapes.add(tglBtnRectangle);
		
		tglbtnDonut = new JToggleButton("Donut");
		btnGrpFunctionsAndShapes.add(tglbtnDonut);
		
		GroupLayout gl_pnlShapes = new GroupLayout(pnlShapes);
		gl_pnlShapes.setHorizontalGroup(
			gl_pnlShapes.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlShapes.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_pnlShapes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlShapes.createSequentialGroup()
							.addGroup(gl_pnlShapes.createParallelGroup(Alignment.LEADING)
								.addComponent(tglBtnLine)
								.addComponent(tglBtnSquare))
							.addGap(0)
							.addGroup(gl_pnlShapes.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlShapes.createSequentialGroup()
									.addGap(4)
									.addComponent(tglBtnPoint)
									.addGap(18)
									.addComponent(tglbtnDonut))
								.addGroup(gl_pnlShapes.createSequentialGroup()
									.addGap(6)
									.addComponent(tglBtnCircle)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tglBtnRectangle, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))))
						.addComponent(lblShapes))
					.addGap(23))
		);
		gl_pnlShapes.setVerticalGroup(
			gl_pnlShapes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlShapes.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblShapes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlShapes.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglBtnSquare)
						.addComponent(tglBtnRectangle)
						.addComponent(tglBtnCircle))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlShapes.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglBtnLine)
						.addComponent(tglBtnPoint)
						.addComponent(tglbtnDonut))
					.addGap(21))
		);
		pnlShapes.setLayout(gl_pnlShapes);
		
		lblColors = new JLabel("COLORS");
		lblColors.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnBorderColor = new JButton("Border Color");
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borderColorr = Color.BLACK;
				borderColorr = JColorChooser.showDialog(null, "Izaberite boju ivice", borderColorr);
				if(borderColorr != null){
					borderColor = borderColorr;
					btnBorderColor.setBackground(borderColor);
				}
			}
		});
		
		btnInsideColor = new JButton("Inner Color");
		btnInsideColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insideColorr = Color.WHITE;
				insideColorr = JColorChooser.showDialog(null, "Izaberite boju unutrašnjosti", insideColorr);
				if(insideColorr != null){
					insideColor = insideColorr;
					btnInsideColor.setBackground(insideColor);
				}
			}
		});
		GroupLayout gl_pnlColors = new GroupLayout(pnlColors);
		gl_pnlColors.setHorizontalGroup(
			gl_pnlColors.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlColors.createSequentialGroup()
					.addGap(41)
					.addComponent(btnBorderColor)
					.addContainerGap(51, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_pnlColors.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_pnlColors.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblColors, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInsideColor))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		gl_pnlColors.setVerticalGroup(
			gl_pnlColors.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlColors.createSequentialGroup()
					.addGap(6)
					.addComponent(lblColors, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnBorderColor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnInsideColor)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		pnlColors.setLayout(gl_pnlColors);
		
		lblFunctions = new JLabel("FUNCTIONS");
		lblFunctions.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		tglBtnSelect = new JToggleButton("Select");
		btnGrpFunctionsAndShapes.add(tglBtnSelect);
		
		tglBtnModify = new JToggleButton("Modify");
		tglBtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ListIterator newIterator = view.shapes.listIterator(view.shapes.size());
					while(newIterator.hasPrevious()){
						Shape help = (Shape)newIterator.previous();
						if(help.isSelected()){
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
								Rectangle novi = dlgP.getHelp();
								if(dlgP.isConfirm()==true){
									view.shapes.remove(help);
									view.shapes.add(novi);
									tglBtnModify.setEnabled(false);
									tglBtnDelete.setEnabled(false);
									tglBtnSelect.setSelected(true);
									reset();
									return;
								}
								else if(dlgP.isConfirm() == false){
									help.setSelected(false);
									tglBtnModify.setEnabled(false);
									tglBtnDelete.setEnabled(false);
									tglBtnSelect.setSelected(true);
									reset();
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
								if(dlgT.isConfirm() == true){
									view.shapes.remove(help);
									view.shapes.add(p2);
									tglBtnModify.setEnabled(false);
									tglBtnDelete.setEnabled(false);
									tglBtnSelect.setSelected(true);
									reset();
									return;
								}
								else if(dlgT.isConfirm() == false){
									help.setSelected(false);
									tglBtnModify.setEnabled(false);
									tglBtnDelete.setEnabled(false);
									tglBtnSelect.setSelected(true);
									reset();
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
									view.shapes.remove(help);
									view.shapes.add(c2);
									tglBtnModify.setEnabled(false);
									tglBtnDelete.setEnabled(false);
									tglBtnSelect.setSelected(true);
									reset();
									return;
								}
								else if(dlgK.isConfirm() == false){
									help.setSelected(false);
									tglBtnModify.setEnabled(false);
									tglBtnDelete.setEnabled(false);
									tglBtnSelect.setSelected(true);
									reset();
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
									view.shapes.remove(help);
									view.shapes.add(l2);
									tglBtnModify.setEnabled(false);
									tglBtnDelete.setEnabled(false);
									tglBtnSelect.setSelected(true);
									reset();
									return;
								}
								else if(dlgL.isConfirm()==false){
									help.setSelected(false);
									tglBtnModify.setEnabled(false);
									tglBtnDelete.setEnabled(false);
									tglBtnSelect.setSelected(true);
									reset();
									return;
								}
							} else if(help instanceof Square){
								DlgSquare dlgKv = new DlgSquare();
								k1 = (Square) help;
								dlgKv.getTxtXCoordinate().setText(String.valueOf(k1.getUpLeft().getX()));
								dlgKv.getTxtYCoordinate().setText(String.valueOf(k1.getUpLeft().getY()));
								dlgKv.getTxtBorderLength().setText(String.valueOf(k1.getBorderLength()));
								dlgKv.getBtnBorderColor().setBackground(k1.getBorderColor());
								dlgKv.getBtnInsideColor().setBackground(k1.getAreaColor());
								dlgKv.setVisible(true);
								Square k2 = dlgKv.getHelp();
								
								if(dlgKv.isConfirm() == true){
									view.shapes.remove(k1);
									view.shapes.add(k2);
									tglBtnModify.setEnabled(false);
									tglBtnDelete.setEnabled(false);
									tglBtnSelect.setSelected(true);
									reset();
									return;
								}
								else if(dlgKv.isConfirm() == false){
									help.setSelected(false);
									tglBtnModify.setEnabled(false);
									tglBtnDelete.setEnabled(false);
									tglBtnSelect.setSelected(true);
									reset();
									return;
								}
								
							} else if(help instanceof Donut) {
								DlgDonut dlgD = new DlgDonut();
								d1 = (Donut) help;
								
								dlgD.getTxtXCoordinate().setText(String.valueOf(d1.getCenter().getX()));
								dlgD.getTxtYCoordinate().setText(String.valueOf(d1.getCenter().getY()));
								dlgD.getTxtOutter().setText(String.valueOf(d1.getR()));
								dlgD.getTxtInner().setText(String.valueOf(d1.getInnerRadius()));
								dlgD.getBtnBorderColor().setBackground(d1.getBorderColor());
								dlgD.getBtnInsideColor().setBackground(d1.getAreaColor());
								
								dlgD.setVisible(true);
								
								Donut donutD = dlgD.getDonutHelp();
								
								//Donut donutP = new Donut(new Tacka(15,50), 77, 22, Color.GREEN, Color.BLACK);
								//dlgD.setPotvrda(true);
								if(dlgD.isConfirm() == true) {
									view.shapes.remove(d1);
									view.shapes.add(donutD);
									tglBtnModify.setEnabled(false);
									tglBtnDelete.setEnabled(false);
									tglBtnSelect.setSelected(true);
									reset();
									return;
								} else if(dlgD.isConfirm() == false) {
									help.setSelected(false);
									tglBtnModify.setEnabled(false);
									tglBtnDelete.setEnabled(false);
									tglBtnSelect.setSelected(true);
									reset();
									return;
								}
								
								
							}
						}
						
					}
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Greška");

				}
			}
		});
		btnGrpFunctionsAndShapes.add(tglBtnModify);
		tglBtnModify.setEnabled(false);
		
		tglBtnDelete = new JToggleButton("Delete");
		tglBtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ListIterator it= view.shapes.listIterator(view.shapes.size());
					while(it.hasPrevious()){
						Shape o = (Shape) it.previous();
						if(o.isSelected()){
							int answer = JOptionPane.showConfirmDialog(null, "Da li želite da obrišete");
							if(answer == JOptionPane.YES_OPTION){
								view.shapes.remove(o);
								tglBtnModify.setEnabled(false);
								tglBtnDelete.setEnabled(false);
								tglBtnSelect.setSelected(true);
								return;
							}
							else{
								o.setSelected(false);
								tglBtnModify.setEnabled(false);
								tglBtnDelete.setEnabled(false);
								tglBtnSelect.setSelected(true);
								reset();
							}
						}
						
					}
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Greška prilikom brisanja");
				}
			}
		});
		btnGrpFunctionsAndShapes.add(tglBtnDelete);
		tglBtnDelete.setEnabled(false);
		GroupLayout gl_pnlFunctions = new GroupLayout(pnlFunctions);
		gl_pnlFunctions.setHorizontalGroup(
			gl_pnlFunctions.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnlFunctions.createSequentialGroup()
					.addContainerGap()
					.addComponent(tglBtnModify, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(tglBtnDelete, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_pnlFunctions.createSequentialGroup()
					.addGap(70)
					.addGroup(gl_pnlFunctions.createParallelGroup(Alignment.TRAILING)
						.addComponent(tglBtnSelect)
						.addComponent(lblFunctions))
					.addGap(68))
		);
		gl_pnlFunctions.setVerticalGroup(
			gl_pnlFunctions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFunctions.createSequentialGroup()
					.addGap(6)
					.addComponent(lblFunctions)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglBtnSelect)
					.addGap(18)
					.addGroup(gl_pnlFunctions.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglBtnDelete)
						.addComponent(tglBtnModify))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		pnlFunctions.setLayout(gl_pnlFunctions);
		panel.setLayout(gl_panel);
		
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				try{
					if(tglBtnModify.isSelected()==false && tglBtnSelect.isSelected()== false && tglBtnDelete.isSelected()==false)
					{
						unselect();
						if(tglBtnPoint.isSelected()){
							x=event.getX();
							y=event.getY();
							Point t1 = new Point(x,y,borderColor);
							t1.drawColor(view.getGraphics());
							view.shapes.add(t1);
							click = 0;
							
						}
						else if(tglBtnLine.isSelected()){
							if(click == 0){
								x=event.getX();
								y=event.getY();
								pointStart = new Point(x,y);
							}
							if(click == 1){
								int newX = event.getX();
								int newY = event.getY();
								pointEnd = new Point(newX, newY);
								l1 = new Line(pointStart, pointEnd, borderColor);
								l1.drawColor(view.getGraphics());
								view.shapes.add(l1);
								l1 = null;
								click = 0;
								return;
							}
							click++;
						}
						else if(tglBtnSquare.isSelected()){
							x=event.getX();
							y=event.getY();
							int length = Integer.parseInt(JOptionPane.showInputDialog("Unsetite dužinu stranice"));
							k1 = new Square(new Point(x,y), length, borderColor, insideColor);
							k1.drawColor(view.getGraphics());
							view.shapes.add(k1);
						}
						else if(tglBtnRectangle.isSelected()){
							x=event.getX();
							y=event.getY();
							int rectLength = Integer.parseInt(JOptionPane.showInputDialog("Unesite dužinu stranice"));
							int rectWidth = Integer.parseInt(JOptionPane.showInputDialog("Unesite širinu"));
							Rectangle p1 = new Rectangle(new Point(x,y),rectLength, rectWidth, borderColor, insideColor);
							p1.drawColor(view.getGraphics());
							view.shapes.add(p1);
						}
						else if(tglBtnCircle.isSelected()){
							x = event.getX();
							y = event.getY();
							int radius = Integer.parseInt(JOptionPane.showInputDialog("Unesite polupreènik kruga"));
							Circle kr = new Circle(new Point(x,y), radius,borderColor, insideColor);
							kr.drawColor(view.getGraphics());
							view.shapes.add(kr);
						}
						else if(tglbtnDonut.isSelected()) {
							x = event.getX();
							y = event.getY();
							int outterRadius = Integer.parseInt(JOptionPane.showInputDialog("Unesite spoljasni poluprecnik"));
							int innerRadius = Integer.parseInt(JOptionPane.showInputDialog("Unesite unutrasnji poluprecnik"));
							Donut d = new Donut(new Point(x,y), outterRadius, innerRadius, borderColor, insideColor);
							d.drawColor(view.getGraphics());
							view.shapes.add(d);
						}
					}
					if(tglBtnSelect.isSelected()){
						Point click = new Point(event.getX(), event.getY());
						ListIterator it = view.shapes.listIterator(view.shapes.size());
						while(it.hasPrevious()){
							Shape help = (Shape)it.previous();
							if(help.contains(click.getX(), click.getY())){
								unselect();
								help.selected(view.getGraphics());
								help.setSelected(true);
								tglBtnModify.setEnabled(true);
								tglBtnDelete.setEnabled(true);
								return;
							} else {
								reset();
							}
						}
					}
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Greška","Greška",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		scrollPane.setViewportView(view);
		contentPaneMain.setLayout(gl_cpnMain);
		
	}
	
	public void unselect(){
		ListIterator it1 = view.shapes.listIterator();
		while(it1.hasNext()){
			Shape reset = (Shape) it1.next();
			reset.setSelected(false);
		}
		tglBtnModify.setEnabled(false);
		tglBtnDelete.setEnabled(false);
	}
	
	
	public void reset(){
		unselect();
		//moze se i zakomentarisati
		tglBtnSelect.setEnabled(true);
	}

	public Square getK1() {
		return k1;
	}

	public void setK1(Square k1) {
		this.k1 = k1;
	}
}
