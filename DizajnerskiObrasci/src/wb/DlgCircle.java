package wb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DlgCircle extends JDialog {

	private final JPanel contentPaneMain = new JPanel();
	private JTextField txtXCoordinate;
	private JTextField txtYCoordinate;
	private JTextField txtRadius;
	private JButton btnConfirm;
	private JButton btnDecline;
	private JButton btnInsideColor;
	private JButton btnBorderColor;
	private Circle k;
	private boolean confirm = false;
	private Color borderColor;
	private Color insideColor;
	private JLabel lblTitle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
		setTitle("IT 43-2015 Kristian Kle\u010Dina");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPaneMain, BorderLayout.CENTER);
		
		JLabel lblXCoordinate = new JLabel("X Coordinate:");
		
		txtXCoordinate = new JTextField();
		txtXCoordinate.setColumns(10);
		
		JLabel lblYCoordinate = new JLabel("Y Coordinate:");
		
		txtYCoordinate = new JTextField();
		txtYCoordinate.setColumns(10);
		
		JLabel lblRadius = new JLabel("Radiuis:");
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		
		JLabel lblBorderColor = new JLabel("Border Color:");
		
		JLabel lblInsideColor = new JLabel("Inside Color:");
		
		btnBorderColor = new JButton("");
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borderColor=JColorChooser.showDialog(null, "Izaberite boju", borderColor);
				btnBorderColor.setBackground(borderColor);
			}
		});
		
		btnInsideColor = new JButton("");
		btnInsideColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insideColor=JColorChooser.showDialog(null, "Izaberite boju", insideColor);
				btnInsideColor.setBackground(insideColor);
			}
		});
		
		lblTitle = new JLabel("Circle");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_cpnMain = new GroupLayout(contentPaneMain);
		gl_cpnMain.setHorizontalGroup(
			gl_cpnMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cpnMain.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
								.addComponent(lblYCoordinate)
								.addComponent(lblRadius))
							.addGap(18)
							.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
								.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_cpnMain.createSequentialGroup()
									.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
									.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
										.addComponent(lblInsideColor)
										.addComponent(lblBorderColor))
									.addGap(18)
									.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
										.addComponent(btnInsideColor, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap(18, Short.MAX_VALUE))
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addComponent(lblXCoordinate)
							.addGap(18)
							.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
							.addComponent(lblTitle)
							.addGap(81))))
		);
		gl_cpnMain.setVerticalGroup(
			gl_cpnMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cpnMain.createSequentialGroup()
					.addContainerGap(32, Short.MAX_VALUE)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblXCoordinate)
								.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(32)
							.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblYCoordinate)
								.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRadius)
								.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(70, Short.MAX_VALUE))
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBorderColor))
							.addGap(24)
							.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblInsideColor)
								.addComponent(btnInsideColor, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addGap(97))))
				.addGroup(gl_cpnMain.createSequentialGroup()
					.addGap(22)
					.addComponent(lblTitle)
					.addContainerGap(193, Short.MAX_VALUE))
		);
		contentPaneMain.setLayout(gl_cpnMain);
		{
			JPanel btnPnButtons = new JPanel();
			btnPnButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(btnPnButtons, BorderLayout.SOUTH);
			{
				btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try{
							k = new Circle();
							Point center = new Point();
							center.setX(Integer.parseInt(txtXCoordinate.getText()));
							center.setY(Integer.parseInt(txtYCoordinate.getText()));
							k.setCenter(center);
							k.setR(Integer.parseInt(txtRadius.getText()));
							k.setBorderColor(btnBorderColor.getBackground());
							k.setAreaColor(btnInsideColor.getBackground());
							confirm = true;
							setVisible(false);
						}
						catch(Exception g){
							JOptionPane.showMessageDialog(null, "Greska");
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
						int answer=JOptionPane.showConfirmDialog(null, "Da li želite da odustanete?");
						if(answer==JOptionPane.YES_OPTION)
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

	public JTextField getTxtXCoordinate() {
		return txtXCoordinate;
	}

	public JTextField getTxtYCoordinate() {
		return txtYCoordinate;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public JButton getBtnDecline() {
		return btnDecline;
	}

	public JButton getBtnInsideColor() {
		return btnInsideColor;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public Circle getK() {
		return k;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public Color getInsideColor() {
		return insideColor;
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public void setTxtXCoordinate(JTextField txtXCoordinate) {
		this.txtXCoordinate = txtXCoordinate;
	}

	public void setTxtYCoordinate(JTextField txtYCoordinate) {
		this.txtYCoordinate = txtYCoordinate;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public void setBtnConfirm(JButton btnConfirm) {
		this.btnConfirm = btnConfirm;
	}

	public void setBtnDecline(JButton btnDecline) {
		this.btnDecline = btnDecline;
	}

	public void setBtnInsideColor(JButton btnInsideColor) {
		this.btnInsideColor = btnInsideColor;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}

	public void setK(Circle k) {
		this.k = k;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public void setInsideColor(Color insideColor) {
		this.insideColor = insideColor;
	}

	public void setLblTitle(JLabel lblTitle) {
		this.lblTitle = lblTitle;
	}
	
}
