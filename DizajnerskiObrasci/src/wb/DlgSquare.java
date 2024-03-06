package wb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Square;
import geometry.Point;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgSquare extends JDialog {

	private final JPanel contentPaneMain = new JPanel();
	private JTextField txtXCoordinate;
	private JTextField txtBorderLength;
	private JTextField txtYCoordinate;
	private JButton btnConfirm;
	private JButton btnDecline;
	private JButton btnBorderColor;
	private JButton btnInsideColor;
	private Square help;
	private boolean confirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgSquare dialog = new DlgSquare();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgSquare() {
		setTitle("IT 43-2015 Kristian Kle\u010Dina");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPaneMain, BorderLayout.CENTER);
		JLabel lblXCoordinate = new JLabel("X Coordinate:");
		JLabel lblYCoordinate = new JLabel("Y Coordinate:");
		JLabel lblBorderLength = new JLabel("Border length:");
		txtXCoordinate = new JTextField();
		txtXCoordinate.setColumns(10);
		txtYCoordinate = new JTextField();
		txtYCoordinate.setColumns(10);
		txtBorderLength = new JTextField();
		txtBorderLength.setColumns(10);
		JLabel lblBorderColor = new JLabel("Border color:");
		JLabel lblInsideColor = new JLabel("Inside color:");
		btnBorderColor = new JButton("");
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = Color.BLACK;
				color = JColorChooser.showDialog(null, "Choose border color", color);
				btnBorderColor.setBackground(color);
			}
		});
		
		JLabel lblTitle = new JLabel("Kvadrat");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		btnInsideColor = new JButton("");
		btnInsideColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color insideColor = Color.WHITE;
				insideColor = JColorChooser.showDialog(null, "Choose inside color", insideColor);
				btnInsideColor.setBackground(insideColor);
			}
		});
		GroupLayout gl_cpnMain = new GroupLayout(contentPaneMain);
		gl_cpnMain.setHorizontalGroup(
			gl_cpnMain.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_cpnMain.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addComponent(lblXCoordinate)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addComponent(lblBorderLength)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtBorderLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addComponent(lblYCoordinate)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
					.addComponent(lblTitle)
					.addGap(63))
				.addGroup(gl_cpnMain.createSequentialGroup()
					.addContainerGap(289, Short.MAX_VALUE)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addComponent(lblInsideColor)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnInsideColor, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addComponent(lblBorderColor)
							.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
							.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_cpnMain.setVerticalGroup(
			gl_cpnMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cpnMain.createSequentialGroup()
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblXCoordinate)
								.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addGap(22)
							.addComponent(lblTitle)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblBorderColor)
						.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYCoordinate)
						.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblInsideColor)
						.addComponent(btnInsideColor, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBorderLength)
						.addComponent(txtBorderLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
							help = new Square();
							Point t = new Point();
							t.setX(Integer.parseInt(txtXCoordinate.getText()));
							t.setY(Integer.parseInt(txtYCoordinate.getText()));
							help.setUpLeft(t);
							help.setBorderLength(Integer.parseInt(txtBorderLength.getText()));
							help.setAreaColor(btnInsideColor.getBackground());
							help.setBorderColor(btnBorderColor.getBackground());
							confirm = btnConfirm.isEnabled();
							setVisible(false);
						}
						catch(Exception ex){
							JOptionPane.showMessageDialog(null,"You didnt put values","Error",JOptionPane.ERROR_MESSAGE);
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
						int decline = JOptionPane.showConfirmDialog(null, "Do you want to quit?");
						if(decline == JOptionPane.YES_OPTION){
							setVisible(false);
						}
						
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

	public JTextField getTxtBorderLength() {
		return txtBorderLength;
	}

	public JTextField getTxtYCoordinate() {
		return txtYCoordinate;
	}

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public JButton getBtnDecline() {
		return btnDecline;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public JButton getBtnInsideColor() {
		return btnInsideColor;
	}

	public Square getHelp() {
		return help;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setTxtXCoordinate(JTextField txtXCoordinate) {
		this.txtXCoordinate = txtXCoordinate;
	}

	public void setTxtBorderLength(JTextField txtBorderLength) {
		this.txtBorderLength = txtBorderLength;
	}

	public void setTxtYCoordinate(JTextField txtYCoordinate) {
		this.txtYCoordinate = txtYCoordinate;
	}

	public void setBtnConfirm(JButton btnConfirm) {
		this.btnConfirm = btnConfirm;
	}

	public void setBtnDecline(JButton btnDecline) {
		this.btnDecline = btnDecline;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}

	public void setBtnInsideColor(JButton btnInsideColor) {
		this.btnInsideColor = btnInsideColor;
	}

	public void setHelp(Square help) {
		this.help = help;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

}
