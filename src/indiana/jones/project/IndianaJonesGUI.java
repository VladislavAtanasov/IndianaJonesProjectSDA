package indiana.jones.project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class IndianaJonesGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fileName;
	private JTextField kilograms;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndianaJonesGUI frame = new IndianaJonesGUI();
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
	public IndianaJonesGUI() {
		setTitle("Indiana Jones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);

		JLabel lblTreasuresFileName = new JLabel("Treasures` file name:");
		lblTreasuresFileName.setForeground(Color.WHITE);
		lblTreasuresFileName.setFont(new Font("Rockwell Condensed", Font.BOLD, 17));
		lblTreasuresFileName.setBounds(289, 100, 215, 22);
		contentPane.add(lblTreasuresFileName);

		fileName = new JTextField();
		fileName.setHorizontalAlignment(SwingConstants.CENTER);
		fileName.setBounds(257, 135, 215, 22);
		contentPane.add(fileName);
		fileName.setColumns(10);

		JLabel lblTheKilogramsIndiana = new JLabel("The capacity of Indiana Jones` bag in kilograms:");
		lblTheKilogramsIndiana.setForeground(Color.WHITE);
		lblTheKilogramsIndiana.setBackground(Color.GRAY);
		lblTheKilogramsIndiana.setFont(new Font("Rockwell Condensed", Font.BOLD, 17));
		lblTheKilogramsIndiana.setBounds(211, 199, 346, 22);
		contentPane.add(lblTheKilogramsIndiana);

		kilograms = new JTextField();
		kilograms.setHorizontalAlignment(SwingConstants.CENTER);
		kilograms.setColumns(10);
		kilograms.setBounds(257, 238, 215, 22);
		contentPane.add(kilograms);

		JLabel lblIndianaJones = new JLabel("Indiana Jones");
		lblIndianaJones.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndianaJones.setFont(new Font("Castellar", Font.BOLD, 38));
		lblIndianaJones.setBounds(176, 54, 381, 37);
		contentPane.add(lblIndianaJones);

		JCheckBox chckbxAutogeneratedTreasures = new JCheckBox("Auto-generated treasures");
		chckbxAutogeneratedTreasures.setBackground(Color.WHITE);
		chckbxAutogeneratedTreasures.setBounds(267, 166, 175, 25);
		chckbxAutogeneratedTreasures.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					fileName.setEnabled(false);
				} else {
					fileName.setEnabled(true);
				}
			}
		});
		contentPane.add(chckbxAutogeneratedTreasures);

		JRadioButton rdbtnWithRepetitionOf = new JRadioButton("With repetition of treasures");
		buttonGroup.add(rdbtnWithRepetitionOf);
		rdbtnWithRepetitionOf.setBackground(Color.WHITE);
		rdbtnWithRepetitionOf.setBounds(276, 269, 185, 25);
		contentPane.add(rdbtnWithRepetitionOf);

		JRadioButton rdbtnWithoutRepetitionOf = new JRadioButton("Without repetition of treasures");
		rdbtnWithoutRepetitionOf.setSelected(true);
		buttonGroup.add(rdbtnWithoutRepetitionOf);
		rdbtnWithoutRepetitionOf.setBackground(Color.WHITE);
		rdbtnWithoutRepetitionOf.setBounds(276, 299, 205, 25);
		contentPane.add(rdbtnWithoutRepetitionOf);

		JRadioButton rdbtnWithoutRepetitionOf_1 = new JRadioButton("Without repetition of treasures(B&B)");
		rdbtnWithoutRepetitionOf_1.setSelected(true);
		buttonGroup.add(rdbtnWithoutRepetitionOf_1);
		rdbtnWithoutRepetitionOf_1.setBackground(Color.WHITE);
		rdbtnWithoutRepetitionOf_1.setBounds(276, 327, 246, 25);
		contentPane.add(rdbtnWithoutRepetitionOf_1);

		JLabel result_Label = new JLabel("");
		result_Label.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		result_Label.setBounds(248, 422, 430, 58);
		contentPane.add(result_Label);

		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setIcon(new ImageIcon(
				"D:\\Hack Bulgaria\\Algorithms\\JavaWindows\\IndianaJonesProject\\pictures\\playicon.png"));
		btnCalculate.setToolTipText("\"Calculate how much kilograms can Indiana Jones carry on\"");
		btnCalculate.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if ((fileName.getText().equals("") && !chckbxAutogeneratedTreasures.isSelected())
							|| kilograms.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please, fill the required fields.");
						return;
					}

					int repeats = -1;
					if (rdbtnWithRepetitionOf.isSelected()) {
						repeats = 1;
					} else if (rdbtnWithoutRepetitionOf.isSelected()) {
						repeats = -1;
					} else if (rdbtnWithoutRepetitionOf_1.isSelected()) {
						repeats = 0;
					}
					try {
						String res;
						int kilos = Integer.parseInt(kilograms.getText());
						if (chckbxAutogeneratedTreasures.isSelected()) {
							res = Starter.startDesktopAuto(kilos, repeats);
							result_Label.setText(res);
							btnCalculate.setIcon(new ImageIcon(
									"D:\\Hack Bulgaria\\Algorithms\\JavaWindows\\IndianaJonesProject\\pictures\\ok.png"));
							return;
						}
						String file = fileName.getText();
						res = Starter.startDesktop(file, kilos, repeats);
						result_Label.setText(res);
						btnCalculate.setIcon(new ImageIcon(
								"D:\\Hack Bulgaria\\Algorithms\\JavaWindows\\IndianaJonesProject\\pictures\\ok.png"));
					} catch (IllegalArgumentException exc) {
						JOptionPane.showMessageDialog(null, "Not valid kilograms.");
						return;
					} catch (IOException exc) {
						JOptionPane.showMessageDialog(null,
								"Not valid file name(Do not forget the extension!) or the file does not exists.");
						return;
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((fileName.getText().equals("") && !chckbxAutogeneratedTreasures.isSelected())
						|| kilograms.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please, fill the required fields.");
					return;
				}

				int repeats = -1;
				if (rdbtnWithRepetitionOf.isSelected()) {
					repeats = 1;
				} else if (rdbtnWithoutRepetitionOf.isSelected()) {
					repeats = -1;
				} else if (rdbtnWithoutRepetitionOf_1.isSelected()) {
					repeats = 0;
				}
				try {
					String res;
					int kilos = Integer.parseInt(kilograms.getText());
					if (chckbxAutogeneratedTreasures.isSelected()) {
						res = Starter.startDesktopAuto(kilos, repeats);
						result_Label.setText(res);
						btnCalculate.setIcon(new ImageIcon(
								"D:\\Hack Bulgaria\\Algorithms\\JavaWindows\\IndianaJonesProject\\pictures\\ok.png"));
						return;
					}
					String file = fileName.getText();
					res = Starter.startDesktop(file, kilos, repeats);
					result_Label.setText(res);
					btnCalculate.setIcon(new ImageIcon(
							"D:\\Hack Bulgaria\\Algorithms\\JavaWindows\\IndianaJonesProject\\pictures\\ok.png"));
				} catch (IllegalArgumentException exc) {
					JOptionPane.showMessageDialog(null, "Not valid kilograms.");
					return;
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(null,
							"Not valid file name(Do not forget the extension!) or the file does not exists.");
					return;
				}
			}
		});
		btnCalculate.setFont(new Font("Segoe Marker", Font.PLAIN, 17));
		btnCalculate.setBounds(306, 361, 130, 42);
		contentPane.add(btnCalculate);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(186, 199, 388, 22);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(186, 100, 388, 22);
		contentPane.add(panel_1);

		JLabel label = new JLabel("");
		label.setIcon(
				new ImageIcon("D:\\Hack Bulgaria\\Algorithms\\JavaWindows\\IndianaJonesProject\\pictures\\jones.jpg"));
		label.setBounds(0, -134, 357, 800);
		contentPane.add(label);

	}
}
