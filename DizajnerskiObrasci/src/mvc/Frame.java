package mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import wb.CrtanjeView;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;

public class Frame extends JFrame{
	
	private View view = new View();
	private Controller controller;
	
	JToggleButton tglbtnPoint;
	JToggleButton tglbtnLine;
	JToggleButton tglbtnSquare;
	JToggleButton tglbtnRectangle;
	JToggleButton tglbtnCircle;
	JToggleButton tglbtnDonut;
	JToggleButton tglbtnHexagon;
	
	JToggleButton tglbtnSelect;
	JToggleButton tglbtnDelete;
	JToggleButton tglbtnModify;
	
	JButton btnBorderColor;
	JButton btnInsideColor;
	
	
	
	
	
	private final ButtonGroup btnGrpFunctionsAndShapes = new ButtonGroup();
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnToBack;
	private JButton btnToFront;
	private JButton btnBringToBack;
	private JButton btnBringToFront;
	private JList<String> logList;
	private JButton btnSaveDrawing;
	private JButton btnLoadDrawing;
	private JButton btnSaveTextLog;
	private JButton btnLoadTextLog;
	private JButton btnNextLogLine;
	DefaultListModel<String> commandsList = new DefaultListModel<>();
	private JScrollPane scrlPnList;
	
	public Frame() {
		getContentPane().setName("mainContentPanel");
		setTitle("IT 43-2015 Kristian Kle\u010Dina");
		setMinimumSize(new Dimension(600, 400));
		setMaximumSize(new Dimension(800, 600));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel btnPanel = new JPanel();
		
		scrlPnList = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
						.addComponent(scrlPnList, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 770, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrlPnList, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
					.addGap(2))
		);
		
		logList = new JList<String>(commandsList);
		scrlPnList.setViewportView(logList);
		
		logList.setValueIsAdjusting(true);
		
		JPanel pnlFunctions = new JPanel();
		pnlFunctions.setBackground(Color.LIGHT_GRAY);
		
		JPanel pnlShapes = new JPanel();
		pnlShapes.setBackground(Color.LIGHT_GRAY);
		
		JPanel pnlColors = new JPanel();
		pnlColors.setBackground(Color.LIGHT_GRAY);
		
		btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.undo();
			}
		});
		btnUndo.setEnabled(false);
		
		btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.redo();
			}
		});
		btnRedo.setEnabled(false);
		
		btnToBack = new JButton("To Back");
		btnToBack.setEnabled(false);
		btnToBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.toBack();
			}
		});
		
		btnToFront = new JButton("To Front");
		btnToFront.setEnabled(false);
		btnToFront.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.toFront();
			}
		});
		
		btnBringToBack = new JButton("BTB");
		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.bringToBack();
				
			}
		});
		
		btnBringToFront = new JButton("BTF");
		btnBringToFront.setEnabled(false);
		btnBringToFront.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.bringToFront();
			}
		});
		
		btnSaveTextLog = new JButton("Save Text");
		btnSaveTextLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.saveTextFile();
			}
		});
		
		btnLoadTextLog = new JButton("Load Text");
		btnLoadTextLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.loadTextFile();
			}
		});
		
		btnNextLogLine = new JButton("Next Line");
		btnNextLogLine.setEnabled(false);
		btnNextLogLine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.nextLogLine();
			}
		});
		
		GroupLayout gl_btnPanel = new GroupLayout(btnPanel);
		gl_btnPanel.setHorizontalGroup(
			gl_btnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlFunctions, GroupLayout.PREFERRED_SIZE, 167, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlShapes, GroupLayout.PREFERRED_SIZE, 279, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_btnPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_btnPanel.createSequentialGroup()
							.addGroup(gl_btnPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnUndo)
								.addComponent(btnToBack, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBringToBack))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_btnPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBringToFront)
								.addComponent(btnRedo)
								.addComponent(btnToFront, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_btnPanel.createSequentialGroup()
							.addComponent(btnSaveTextLog)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnLoadTextLog))
						.addComponent(btnNextLogLine))
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addComponent(pnlColors, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_btnPanel.setVerticalGroup(
			gl_btnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_btnPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_btnPanel.createSequentialGroup()
							.addGroup(gl_btnPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnUndo)
								.addComponent(btnRedo))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_btnPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnToBack)
								.addComponent(btnToFront))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_btnPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnBringToBack)
								.addComponent(btnBringToFront))
							.addGap(82))
						.addGroup(gl_btnPanel.createParallelGroup(Alignment.TRAILING)
							.addComponent(pnlColors, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
							.addGroup(gl_btnPanel.createSequentialGroup()
								.addGroup(gl_btnPanel.createParallelGroup(Alignment.TRAILING)
									.addComponent(pnlFunctions, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
									.addComponent(pnlShapes, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
									.addGroup(gl_btnPanel.createSequentialGroup()
										.addComponent(btnNextLogLine)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_btnPanel.createParallelGroup(Alignment.BASELINE)
											.addComponent(btnSaveTextLog)
											.addComponent(btnLoadTextLog))))
								.addContainerGap()))))
		);
		
		tglbtnPoint = new JToggleButton("Point");
		btnGrpFunctionsAndShapes.add(tglbtnPoint);
		
		
		tglbtnLine = new JToggleButton("Line");
		btnGrpFunctionsAndShapes.add(tglbtnLine);
		
		tglbtnSquare = new JToggleButton("Square");
		btnGrpFunctionsAndShapes.add(tglbtnSquare);
		
		tglbtnRectangle = new JToggleButton("Rectangle");
		btnGrpFunctionsAndShapes.add(tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton("Circle");
		btnGrpFunctionsAndShapes.add(tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("Donut");
		btnGrpFunctionsAndShapes.add(tglbtnDonut);
		
		tglbtnHexagon = new JToggleButton("Hexagon");
		btnGrpFunctionsAndShapes.add(tglbtnHexagon);
		
		btnSaveDrawing = new JButton("Save Draw");
		btnSaveDrawing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.saveDrawingFile();
			}
		});
		
		btnLoadDrawing = new JButton("Load Draw");
		btnLoadDrawing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.loadDrawingFile();
			}
		});
		GroupLayout gl_pnlShapes = new GroupLayout(pnlShapes);
		gl_pnlShapes.setHorizontalGroup(
			gl_pnlShapes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlShapes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlShapes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlShapes.createSequentialGroup()
							.addComponent(tglbtnPoint, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnCircle, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlShapes.createSequentialGroup()
							.addComponent(tglbtnLine, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnDonut, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addComponent(tglbtnRectangle, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlShapes.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, gl_pnlShapes.createSequentialGroup()
								.addComponent(btnSaveDrawing)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnLoadDrawing))
							.addGroup(Alignment.LEADING, gl_pnlShapes.createSequentialGroup()
								.addComponent(tglbtnSquare, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tglbtnHexagon, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		gl_pnlShapes.setVerticalGroup(
			gl_pnlShapes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlShapes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlShapes.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnPoint)
						.addComponent(tglbtnCircle))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlShapes.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnLine)
						.addComponent(tglbtnDonut))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlShapes.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnSquare)
						.addComponent(tglbtnHexagon))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnRectangle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlShapes.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSaveDrawing)
						.addComponent(btnLoadDrawing))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pnlShapes.setLayout(gl_pnlShapes);
		
		tglbtnSelect = new JToggleButton("Select");
		
		btnGrpFunctionsAndShapes.add(tglbtnSelect);
		
		tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.setEnabled(false);
		tglbtnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.deleteShape();
				
			}
		});
		btnGrpFunctionsAndShapes.add(tglbtnDelete);
		
		tglbtnModify = new JToggleButton("Modify");
		tglbtnModify.setEnabled(false);
		tglbtnModify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.modifyShape();
				
			}
		});
		btnGrpFunctionsAndShapes.add(tglbtnModify);
		
		GroupLayout gl_pnlFunctions = new GroupLayout(pnlFunctions);
		gl_pnlFunctions.setHorizontalGroup(
			gl_pnlFunctions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFunctions.createSequentialGroup()
					.addGroup(gl_pnlFunctions.createParallelGroup(Alignment.LEADING)
						.addComponent(tglbtnDelete, GroupLayout.PREFERRED_SIZE, 111, Short.MAX_VALUE)
						.addComponent(tglbtnSelect, GroupLayout.PREFERRED_SIZE, 111, Short.MAX_VALUE)
						.addComponent(tglbtnModify, GroupLayout.PREFERRED_SIZE, 111, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_pnlFunctions.setVerticalGroup(
			gl_pnlFunctions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFunctions.createSequentialGroup()
					.addComponent(tglbtnSelect, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tglbtnModify, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnDelete, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
		);
		pnlFunctions.setLayout(gl_pnlFunctions);
		pnlColors.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnBorderColor = new JButton("Border");
		pnlColors.add(btnBorderColor);
		btnBorderColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.borderColor(e);
			}
		});
		
		btnInsideColor = new JButton("Inside");
		btnInsideColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.insideColor(e);
			}
		});
		pnlColors.add(btnInsideColor);
		btnPanel.setLayout(gl_btnPanel);
		scrollPane.setViewportView(view);
		
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		getContentPane().setLayout(groupLayout);
		
		
		
		
	}
	
	public View getView() {
		return view;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnSquare() {
		return tglbtnSquare;
	}

	public void setTglbtnSquare(JToggleButton tglbtnSquare) {
		this.tglbtnSquare = tglbtnSquare;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}

	public JToggleButton getTglbtnDelete() {
		return tglbtnDelete;
	}

	public void setTglbtnDelete(JToggleButton tglbtnDelete) {
		this.tglbtnDelete = tglbtnDelete;
	}

	public JToggleButton getTglbtnModify() {
		return tglbtnModify;
	}

	public void setTglbtnModify(JToggleButton tglbtnModify) {
		this.tglbtnModify = tglbtnModify;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}

	public JButton getBtnInsideColor() {
		return btnInsideColor;
	}

	public void setBtnInsideColor(JButton btnInsideColor) {
		this.btnInsideColor = btnInsideColor;
	}

	public Controller getController() {
		return controller;
	}

	public ButtonGroup getBtnGrpFunctionsAndShapes() {
		return btnGrpFunctionsAndShapes;
	}

	public void setView(View view) {
		this.view = view;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public void setBtnToBack(JButton btnToBack) {
		this.btnToBack = btnToBack;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public void setBtnToFront(JButton btnToFront) {
		this.btnToFront = btnToFront;
	}

	public JList getLogList() {
		return logList;
	}

	public void setLogList(JList logList) {
		this.logList = logList;
	}

	public JButton getBtnSaveDrawing() {
		return btnSaveDrawing;
	}

	public void setBtnSaveDrawing(JButton btnSaveDrawing) {
		this.btnSaveDrawing = btnSaveDrawing;
	}

	public JButton getBtnLoadDrawing() {
		return btnLoadDrawing;
	}

	public void setBtnLoadDrawing(JButton btnLoadDrawing) {
		this.btnLoadDrawing = btnLoadDrawing;
	}

	public JButton getBtnSaveTextLog() {
		return btnSaveTextLog;
	}

	public void setBtnSaveTextLog(JButton btnSaveTextLog) {
		this.btnSaveTextLog = btnSaveTextLog;
	}

	public JButton getBtnLoadTextLog() {
		return btnLoadTextLog;
	}

	public void setBtnLoadTextLog(JButton btnLoadTextLog) {
		this.btnLoadTextLog = btnLoadTextLog;
	}

	public JButton getBtnNextLogLine() {
		return btnNextLogLine;
	}

	public void setBtnNextLogLine(JButton btnNextLogLine) {
		this.btnNextLogLine = btnNextLogLine;
	}

	public DefaultListModel<String> getCommandsList() {
		return commandsList;
	}

	public void setCommandsList(DefaultListModel<String> model) {
		this.commandsList = model;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public void setBtnBringToBack(JButton btnBringToBack) {
		this.btnBringToBack = btnBringToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public void setBtnBringToFront(JButton btnBringToFront) {
		this.btnBringToFront = btnBringToFront;
	}
	
	
}
