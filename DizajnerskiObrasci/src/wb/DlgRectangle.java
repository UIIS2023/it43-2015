package wb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Rectangle;
import geometry.Point;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgRectangle extends JDialog {

	private final JPanel contentPaneMain = new JPanel();
	private JTextField txtXCoordinate;
	private JTextField txtYCoordinate;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private JButton btnBorderColor;
	private JButton btnInsideColor;
	private JButton btnConfirm;
	private JButton btnDecline;
	private Rectangle help;
	private boolean confirm=false;
	private Color borderColor;
	private Color insideColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setTitle("IT 43-2015 Kristian Kle\u010Dina");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPaneMain, BorderLayout.CENTER);
		
		JLabel lblUpLeftPoint = new JLabel("Ta\u010Dka Gore Levo:");
		
		JLabel lblHeight = new JLabel("Visina:");
		
		JLabel lblWidth = new JLabel("\u0160irina:");
		
		JLabel lblBorderColor = new JLabel("Boja Ivice:");
		
		JLabel lblInsideColor = new JLabel("Boja Unutra\u0161njosti:");
		
		JLabel lblX = new JLabel("X");
		
		JLabel lblY = new JLabel("Y");
		
		JLabel lblTitle = new JLabel("Pravougaonik");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txtXCoordinate = new JTextField();
		txtXCoordinate.setColumns(10);
		
		txtYCoordinate = new JTextField();
		txtYCoordinate.setColumns(10);
		
		txtHeight = new JTextField();
		txtHeight.setColumns(10);
		
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		
		btnBorderColor = new JButton("");
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borderColor = JColorChooser.showDialog(null, "Izaberite boju ivice", borderColor);
				btnBorderColor.setBackground(borderColor);
			}
		});
		
		btnInsideColor = new JButton("");
		btnInsideColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insideColor = JColorChooser.showDialog(null, "Odaberite boju unutrašnjosti", insideColor);
				btnInsideColor.setBackground(insideColor);
			}
		});
		GroupLayout gl_cpnMain = new GroupLayout(contentPaneMain);
		gl_cpnMain.setHorizontalGroup(
			gl_cpnMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cpnMain.createSequentialGroup()
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblBorderColor)
								.addComponent(lblWidth)
								.addComponent(lblUpLeftPoint)
								.addComponent(lblHeight)))
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblInsideColor)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addGap(4)
							.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_cpnMain.createSequentialGroup()
									.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnInsideColor, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addGap(22)
							.addComponent(lblX)
							.addGap(38)
							.addComponent(lblY)))
					.addPreferredGap(ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
					.addComponent(lblTitle)
					.addGap(56))
		);
		gl_cpnMain.setVerticalGroup(
			gl_cpnMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cpnMain.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_cpnMain.createSequentialGroup()
							.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblX)
								.addComponent(lblY))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUpLeftPoint)
								.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblTitle))
					.addGap(18)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHeight))
					.addGap(8)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWidth)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblBorderColor)
						.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_cpnMain.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblInsideColor)
						.addComponent(btnInsideColor, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(11))
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
							help = new Rectangle();
							Point upLeftPoint = new Point();
							upLeftPoint.setX(Integer.parseInt(txtXCoordinate.getText()));
							upLeftPoint.setY(Integer.parseInt(txtYCoordinate.getText()));
							help.setUpLeft(upLeftPoint);
							help.setHeight(Integer.parseInt(txtHeight.getText()));
							help.setWidth(Integer.parseInt(txtWidth.getText()));
							help.setBorderColor(btnBorderColor.getBackground());
							help.setAreaColor(btnInsideColor.getBackground());
							confirm = true;
							setVisible(false);
						}
						catch(Exception ex){
							JOptionPane.showMessageDialog(null, "Greska","Greska",JOptionPane.ERROR_MESSAGE);
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
						int answer = JOptionPane.showConfirmDialog(null, "Da li želite da odustanete?");
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
		return contentPaneMain;
	}

	public JTextField getTxtXCoordinate() {
		return txtXCoordinate;
	}

	public JTextField getTxtYCoordinate() {
		return txtYCoordinate;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public JButton getBtnInsideColor() {
		return btnInsideColor;
	}

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public JButton getBtnDecline() {
		return btnDecline;
	}

	public Rectangle getHelp() {
		return help;
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

	public void setTxtXCoordinate(JTextField txtXCoordinate) {
		this.txtXCoordinate = txtXCoordinate;
	}

	public void setTxtYCoordinate(JTextField txtYCoordinate) {
		this.txtYCoordinate = txtYCoordinate;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}

	public void setBtnInsideColor(JButton btnInsideColor) {
		this.btnInsideColor = btnInsideColor;
	}

	public void setBtnConfirm(JButton btnConfirm) {
		this.btnConfirm = btnConfirm;
	}

	public void setBtnDecline(JButton btnDecline) {
		this.btnDecline = btnDecline;
	}

	public void setHelp(Rectangle help) {
		this.help = help;
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
	
	
}
