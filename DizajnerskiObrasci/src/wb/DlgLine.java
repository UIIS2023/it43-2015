package wb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class DlgLine extends JDialog {

	private final JPanel contentPaneMain = new JPanel();
	private JButton btnBorderColor;
	private JButton btnConfirm;
	private JButton btnDecline;
	private Point helpStart;
	private Point helpEnd;
	private Line line;
	private Color borderColor;
	private boolean confirm;
	private JButton btnStart;
	private JButton btnEnd;
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setTitle("IT 43-2015 Kristian Kle\u010Dina");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPaneMain, BorderLayout.CENTER);
		JLabel lblStartPoint = new JLabel("Start Point:");
		JLabel lblEndPoint = new JLabel("End Point:");
		JLabel lblBorderColor = new JLabel("Border Color:");
		JLabel lblTitle = new JLabel("Line");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBorderColor = new JButton("");
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borderColor=JColorChooser.showDialog(null, "Choose border color", borderColor);
				btnBorderColor.setBackground(borderColor);
			}
		});
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgPoint dlgPStart=new DlgPoint();
				dlgPStart.getTxtXCoordinate().setText(String.valueOf(helpStart.getX()));
				dlgPStart.getTxtYCoordinate().setText(String.valueOf(helpStart.getY()));
				dlgPStart.getBtnBorderColor().setBackground(helpStart.getBorderColor());
				dlgPStart.setVisible(true);
				Point newPoint=dlgPStart.getHelp();
				helpStart.setX(newPoint.getX());
				helpStart.setY(newPoint.getY());
				helpStart.setBorderColor(newPoint.getBorderColor());
			
			}
		});
		
		btnEnd = new JButton("End");
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgPoint dlgPEnd=new DlgPoint();
				dlgPEnd.getTxtXCoordinate().setText(String.valueOf(helpEnd.getX()));
				dlgPEnd.getTxtYCoordinate().setText(String.valueOf(helpEnd.getY()));
				dlgPEnd.getBtnBorderColor().setBackground(helpEnd.getBorderColor());
				dlgPEnd.setVisible(true);
				Point newPoint=dlgPEnd.getHelp();
				helpEnd.setX(newPoint.getX());
				helpEnd.setY(newPoint.getY());
				helpEnd.setBorderColor(newPoint.getBorderColor());
			}
		});
		GroupLayout gl_cpnMain = new GroupLayout(contentPaneMain);
		gl_cpnMain.setHorizontalGroup(
			gl_cpnMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cpnMain.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_cpnMain.createSequentialGroup()
									.addComponent(lblEndPoint)
									.addGap(5))
								.addGroup(gl_cpnMain.createSequentialGroup()
									.addComponent(lblStartPoint)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(18)
							.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnEnd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnStart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addComponent(lblBorderColor)
							.addGap(41)
							.addComponent(btnBorderColor))
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addGap(275)
							.addComponent(lblTitle)))
					.addGap(81))
		);
		gl_cpnMain.setVerticalGroup(
			gl_cpnMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cpnMain.createSequentialGroup()
					.addGap(25)
					.addComponent(lblTitle)
					.addGap(8)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartPoint)
						.addComponent(btnStart))
					.addGap(28)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndPoint)
						.addComponent(btnEnd))
					.addGap(44)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblBorderColor)
						.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		contentPaneMain.setLayout(gl_cpnMain);
		{
			JPanel btnPnButtons = new JPanel();
			btnPnButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(btnPnButtons, BorderLayout.SOUTH);
			{
				btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try{
							line = new Line();
							line.setPStart(helpStart);
							line.setPEnd(helpEnd);
							line.setBorderColor(btnBorderColor.getBackground());
							confirm = true;
							setVisible(false);
							
						}
						catch(Exception ex){
							JOptionPane.showMessageDialog(null, "Error","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnConfirm.setActionCommand("OK");
				btnPnButtons.add(btnConfirm);
				getRootPane().setDefaultButton(btnConfirm);
			}
			{
				btnDecline = new JButton("Cancel");
				btnDecline.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int odgovor=JOptionPane.showConfirmDialog(null, "Do you want to quit?");
						if(odgovor == JOptionPane.YES_OPTION)
							setVisible(false);
					}
				});
				btnDecline.setActionCommand("Cancel");
				btnPnButtons.add(btnDecline);
			}
		}
	}

	public JPanel getCpnMain() {
		return contentPaneMain;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public JButton getBtnDecline() {
		return btnDecline;
	}

	public Point getHelpStart() {
		return helpStart;
	}

	public Point getHelpEnd() {
		return helpEnd;
	}

	public Line getLine() {
		return line;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}

	public void setBtnConfirm(JButton btnConfirm) {
		this.btnConfirm = btnConfirm;
	}

	public void setBtnDecline(JButton btnDecline) {
		this.btnDecline = btnDecline;
	}

	public void setHelpStart(Point helpStart) {
		this.helpStart = helpStart;
	}

	public void setHelpEnd(Point helpEnd) {
		this.helpEnd = helpEnd;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	

}
