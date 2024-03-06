package wb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgPoint extends JDialog {

	private final JPanel controlPanelMain = new JPanel();
	private JLabel lblTitle;
	private JTextField txtXCoordinate;
	private JTextField txtYCoordinate;
	private JButton btnBorderColor;
	private JButton btnConfirm;
	private JButton btnDecline;
	private Point help;
	private boolean confirm=false;
	private Color borderColor;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPoint() {
		setTitle("IT 43-2015 Kristian Kle\u010Dina");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		controlPanelMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(controlPanelMain, BorderLayout.CENTER);
		{
			lblTitle = new JLabel("Point");
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		JLabel lblXCoordinate = new JLabel("X Coordinate:");
		JLabel lblYCoordinate = new JLabel("Y Coordinate:");
		JLabel lblBorderColor = new JLabel("Border Color:");
		txtXCoordinate = new JTextField();
		txtXCoordinate.setColumns(10);
		txtYCoordinate = new JTextField();
		txtYCoordinate.setColumns(10);
		btnBorderColor = new JButton("");
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borderColor = JColorChooser.showDialog(null, "Choose border color", borderColor);
				btnBorderColor.setBackground(borderColor);
			}
		});
		GroupLayout gl_cpnMain = new GroupLayout(controlPanelMain);
		gl_cpnMain.setHorizontalGroup(
			gl_cpnMain.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_cpnMain.createSequentialGroup()
					.addContainerGap(306, Short.MAX_VALUE)
					.addComponent(lblTitle)
					.addGap(75))
				.addGroup(gl_cpnMain.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblYCoordinate)
						.addComponent(lblXCoordinate)
						.addComponent(lblBorderColor))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnBorderColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtXCoordinate)
						.addComponent(txtYCoordinate))
					.addContainerGap(248, Short.MAX_VALUE))
		);
		gl_cpnMain.setVerticalGroup(
			gl_cpnMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cpnMain.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addComponent(lblTitle)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblXCoordinate)))
					.addGap(36)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblYCoordinate)
						.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblBorderColor)
						.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		controlPanelMain.setLayout(gl_cpnMain);
		{
			JPanel btnPnButtons = new JPanel();
			btnPnButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(btnPnButtons, BorderLayout.SOUTH);
			{
				btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try{
							help = new Point();
							help.setX(Integer.parseInt(txtXCoordinate.getText()));
							help.setY(Integer.parseInt(txtYCoordinate.getText()));
							help.setBorderColor(btnBorderColor.getBackground());
							setVisible(false);
							confirm = true;
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
						int answer = JOptionPane.showConfirmDialog(null, "Do you want to quit?");
						if(answer == JOptionPane.YES_OPTION)
							setVisible(false);
					}
				});
				btnDecline.setActionCommand("Cancel");
				btnPnButtons.add(btnDecline);
			}
		}
	}

	
	public JPanel getCpnMain() {
		return controlPanelMain;
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public JTextField getTxtXCoordinate() {
		return txtXCoordinate;
	}

	public JTextField getTxtYCoordinate() {
		return txtYCoordinate;
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

	public Point getHelp() {
		return help;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setLblTitle(JLabel lblTitle) {
		this.lblTitle = lblTitle;
	}

	public void setTxtXCoordinate(JTextField txtXCoordinate) {
		this.txtXCoordinate = txtXCoordinate;
	}

	public void setTxtYCoordinate(JTextField txtYCoordinate) {
		this.txtYCoordinate = txtYCoordinate;
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

	public void setHelp(Point help) {
		this.help = help;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	

}
