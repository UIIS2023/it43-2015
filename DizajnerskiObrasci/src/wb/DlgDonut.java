package wb;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import geometry.Donut;
import geometry.Point;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DlgDonut extends JDialog {

	private final JPanel contentPaneMain = new JPanel();
	private JTextField txtXCoordinate;
	private JTextField txtYCoordinate;
	private JTextField txtOutter;
	private JTextField txtInner;
	private JLabel lblBorderColor;
	private JLabel lblInsideColor;
	private JButton btnBorderColor;
	private JButton btnInsideColor;
	private Donut donutHelp;
	private boolean confirm;
	private JButton btnConfirm;
	private JButton btnDecline;
	private Color borderColor;
	private Color insideColor;
	private JLabel lblTitle;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonut() {
		setTitle("IT 43-2015 Kristian Kle\u010Dina");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPaneMain, BorderLayout.CENTER);

		JLabel lblXCoordinate = new JLabel("X Coordinate:");

		JLabel lblYCoordinate = new JLabel("Y Coordinate:");

		JLabel lblOutter = new JLabel("Outter:");

		JLabel lblInner = new JLabel("Inner");

		txtXCoordinate = new JTextField();
		txtXCoordinate.setColumns(10);

		txtYCoordinate = new JTextField();
		txtYCoordinate.setColumns(10);

		txtOutter = new JTextField();
		txtOutter.setColumns(10);

		txtInner = new JTextField();
		txtInner.setColumns(10);

		lblBorderColor = new JLabel("Boja Ivice:");

		lblInsideColor = new JLabel("Boja Unutrasnjosti:");

		btnBorderColor = new JButton("");
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borderColor = JColorChooser.showDialog(null, "Choose color", borderColor);
				btnBorderColor.setBackground(borderColor);
			}
		});
		btnInsideColor = new JButton("");
		btnInsideColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insideColor = JColorChooser.showDialog(null, "Choose color", insideColor);
				btnInsideColor.setBackground(insideColor);
			}
		});
		lblTitle = new JLabel("Donut");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_cpnMain = new GroupLayout(contentPaneMain);
		gl_cpnMain.setHorizontalGroup(
				gl_cpnMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_cpnMain.createSequentialGroup()
								.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_cpnMain.createSequentialGroup()
												.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_cpnMain.createSequentialGroup()
																.addContainerGap()
																.addComponent(lblOutter))
														.addGroup(gl_cpnMain.createSequentialGroup()
																.addContainerGap()
																.addComponent(lblYCoordinate))
														.addGroup(gl_cpnMain.createSequentialGroup()
																.addContainerGap()
																.addComponent(lblXCoordinate)))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
														.addGroup(Alignment.TRAILING, gl_cpnMain.createSequentialGroup()
																.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
																		.addComponent(txtInner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addComponent(txtOutter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
																		.addComponent(lblBorderColor)
																		.addComponent(lblInsideColor))
																.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_cpnMain.createSequentialGroup()
																				.addGap(7)
																				.addComponent(btnInsideColor, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
																		.addGroup(gl_cpnMain.createSequentialGroup()
																				.addPreferredGap(ComponentPlacement.RELATED)
																				.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))))
														.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
																.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
												.addGap(8))
										.addGroup(gl_cpnMain.createSequentialGroup()
												.addContainerGap()
												.addComponent(lblInner)))
								.addContainerGap(56, Short.MAX_VALUE))
		);
		gl_cpnMain.setVerticalGroup(
				gl_cpnMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_cpnMain.createSequentialGroup()
								.addGap(26)
								.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblXCoordinate)
										.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(13)
								.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
										.addComponent(lblBorderColor)
										.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_cpnMain.createSequentialGroup()
												.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblYCoordinate)
														.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(30)
												.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblOutter)
														.addComponent(txtOutter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
												.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblInner)
														.addComponent(txtInner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(36))
										.addGroup(Alignment.TRAILING, gl_cpnMain.createSequentialGroup()
												.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
														.addComponent(btnInsideColor, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblInsideColor))
												.addGap(51))))
		);
		contentPaneMain.setLayout(gl_cpnMain);
		{
			JPanel btnPnButtons = new JPanel();
			btnPnButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(btnPnButtons, BorderLayout.SOUTH);
			{
				btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
							donutHelp = new Donut();
							Point center = new Point(Integer.parseInt(getTxtXCoordinate().getText()),
									Integer.parseInt(getTxtYCoordinate().getText()));

							donutHelp.setCenter(center);
							donutHelp.setR(Integer.parseInt(getTxtOutter().getText()));
							donutHelp.setInnerRadius(Integer.parseInt(getTxtInner().getText()));
							donutHelp.setBorderColor(btnBorderColor.getBackground());
							donutHelp.setAreaColor(btnInsideColor.getBackground());
							setVisible(false);
							setConfirm(true);
						} catch (Exception g) {
							JOptionPane.showMessageDialog(null, "Error");
						}

					}
				});
				btnConfirm.setActionCommand("OK");
				btnPnButtons.add(btnConfirm);
				getRootPane().setDefaultButton(btnConfirm);
			}
			{
				btnDecline = new JButton("Decline");
				btnDecline.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int decline = JOptionPane.showConfirmDialog(null, "Do you want to cancel?");
						if (decline == JOptionPane.YES_OPTION)
							setVisible(false);

					}
				});
				btnDecline.setActionCommand("Cancel");
				btnPnButtons.add(btnDecline);
			}
		}
	}

	public JTextField getTxtXCoordinate() {
		return txtXCoordinate;
	}

	public void setTxtXCoordinate(JTextField txtXCoordinate) {
		this.txtXCoordinate = txtXCoordinate;
	}

	public JTextField getTxtYCoordinate() {
		return txtYCoordinate;
	}

	public void setTxtYCoordinate(JTextField txtYCoordinate) {
		this.txtYCoordinate = txtYCoordinate;
	}

	public JTextField getTxtOutter() {
		return txtOutter;
	}

	public void setTxtOutter(JTextField txtOutter) {
		this.txtOutter = txtOutter;
	}

	public JTextField getTxtInner() {
		return txtInner;
	}

	public void setTxtInner(JTextField txtInner) {
		this.txtInner = txtInner;
	}

	public JLabel getLblBorderColor() {
		return lblBorderColor;
	}

	public void setLblBorderColor(JLabel lblBorderColor) {
		this.lblBorderColor = lblBorderColor;
	}

	public JLabel getLblInsideColor() {
		return lblInsideColor;
	}

	public void setLblInsideColor(JLabel lblInsideColor) {
		this.lblInsideColor = lblInsideColor;
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

	public Donut getDonutHelp() {
		return donutHelp;
	}

	public void setDonutHelp(Donut donutHelp) {
		this.donutHelp = donutHelp;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public void setBtnConfirm(JButton btnConfirm) {
		this.btnConfirm = btnConfirm;
	}

	public JButton getBtnDecline() {
		return btnDecline;
	}

	public void setBtnDecline(JButton btnDecline) {
		this.btnDecline = btnDecline;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getInsideColor() {
		return insideColor;
	}

	public void setInsideColor(Color insideColor) {
		this.insideColor = insideColor;
	}

	public JPanel getCpnGlavni() {
		return contentPaneMain;
	}
	
	
	
}
