package wb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.HexagonAdapter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class DlgHexagon2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtXCoordinate;
	private JTextField txtYCoordinate;
	private JTextField txtRadius;
	private HexagonAdapter h;
	private boolean confirm = false;
	private Color borderColor;
	private Color insideColor;
	JButton btnBorderColor;
	JButton btnInsideColor;
	JButton okButton;
	JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgHexagon2 dialog = new DlgHexagon2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgHexagon2() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblXCoordinate = new JLabel("X Coordinate:");
		JLabel lblYCoordinate = new JLabel("Y Coordinate:");
		JLabel lblRadius = new JLabel("Radiuis:");
		JLabel lblTitle = new JLabel("Hexagon");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblBorderColor = new JLabel("Border Color:");
		JLabel lblInsideColor = new JLabel("Inside Color:");
		btnBorderColor = new JButton("");
		btnBorderColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				borderColor=JColorChooser.showDialog(null, "Izaberite boju", borderColor);
				btnBorderColor.setBackground(borderColor);
			}
		});
		btnInsideColor = new JButton("");
		btnInsideColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				insideColor=JColorChooser.showDialog(null, "Izaberite boju", insideColor);
				btnInsideColor.setBackground(insideColor);
			}
		});
		txtXCoordinate = new JTextField();
		txtXCoordinate.setColumns(10);
		txtYCoordinate = new JTextField();
		txtYCoordinate.setColumns(10);
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblRadius, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblYCoordinate, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblXCoordinate, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
									.addComponent(lblInsideColor, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap(232, Short.MAX_VALUE)
							.addComponent(lblBorderColor, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInsideColor, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
					.addGap(41))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(277, Short.MAX_VALUE)
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addGap(68))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblXCoordinate)
								.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(22)
							.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(2)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblYCoordinate)
								.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(34)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRadius)
								.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBorderColor)
								.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addGap(36)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblInsideColor)
								.addComponent(btnInsideColor, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Confirm");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						try{
							
							System.out.println("OKEJ");
							
							int x,y,r;
							x = Integer.parseInt(txtXCoordinate.getText());
							y = Integer.parseInt(txtYCoordinate.getText());
							r = Integer.parseInt(txtRadius.getText());
							borderColor = btnBorderColor.getBackground();
							insideColor = btnInsideColor.getBackground();
							
							h = new HexagonAdapter(x, y, r, borderColor, insideColor);
							
							confirm=true;
							
							setVisible(false);
							
						}
						catch(Exception g){
							JOptionPane.showMessageDialog(null, "Greska");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						int answer=JOptionPane.showConfirmDialog(null, "Da li želite da odustanete?");
						if(answer==JOptionPane.YES_OPTION)
							setVisible(false);
					}
				});
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

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public HexagonAdapter getH() {
		return h;
	}

	public void setH(HexagonAdapter h) {
		this.h = h;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
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

	public JPanel getContentPanel() {
		return contentPanel;
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
	
	

}
