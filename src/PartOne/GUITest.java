package PartOne;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

public class GUITest {

	private JFrame frame;
	private JLabel lblWelcome;
	private JTextField textFieldTedad;
	private JLabel lblTdWord;
	private JTextField textFieldWord;
	private JLabel lblW1;
	private JLabel lblW2;
	private JLabel lblW3;
	private JLabel lblW4;
	private JLabel lblW5;
	private JButton btnDone;
	private JLabel lblLenght;
	// to see if this is the first time pushing the NEXT button, if is it's "NEXT"
	// if not it is "DONE"
	boolean firstTime = true;
	// hamoon input aval e ke mige chandta adad mikhad bede
	int counterWordsToEnter = 0;
	boolean doneInsertingWordsOrNot = false;
	public static String[] words = new String[5];
	private JLabel lblNewLabel;
	private JLabel label;
	private JButton btnExite;
	private JTextField textField;

	/**
	 * Launch the application.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter fileOutSetDefault = new PrintWriter("matris.txt");
		fileOutSetDefault.print("default_@default/");
		fileOutSetDefault.close();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUITest window = new GUITest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public GUITest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// be soora pishfarz be tamami array ha $ dade mishe ke por beshan
		for (int i = 0; i < 5; i++) {
			words[i] = "$";
		}
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 204, 204));
		frame.setBounds(100, 100, 522, 366);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		lblWelcome = new JLabel("Welcome To Word Search");
		lblWelcome.setForeground(new Color(204, 102, 0));
		lblWelcome.setFont(new Font("Square721 BT", Font.BOLD, 22));
		lblWelcome.setBounds(98, 42, 328, 58);
		frame.getContentPane().add(lblWelcome);

		JLabel lblHowMany = new JLabel("How Many?");
		lblHowMany.setBackground(new Color(51, 51, 51));
		lblHowMany.setForeground(new Color(51, 51, 51));
		lblHowMany.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHowMany.setBounds(85, 110, 126, 42);
		frame.getContentPane().add(lblHowMany);

		textFieldTedad = new JTextField();
		textFieldTedad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldTedad.setForeground(new Color(51, 51, 51));
		textFieldTedad.setBackground(new Color(204, 204, 204));
		textFieldTedad.setBounds(221, 113, 96, 37);
		frame.getContentPane().add(textFieldTedad);
		textFieldTedad.setColumns(10);

		lblTdWord = new JLabel("First Word :");
		lblTdWord.setBackground(new Color(51, 51, 51));
		lblTdWord.setForeground(new Color(51, 51, 51));
		lblTdWord.setEnabled(false);
		lblTdWord.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTdWord.setBounds(85, 168, 113, 42);
		frame.getContentPane().add(lblTdWord);

		textFieldWord = new JTextField();
		textFieldWord.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldWord.setForeground(new Color(51, 51, 51));
		textFieldWord.setBackground(new Color(204, 204, 204));
		textFieldWord.setEnabled(false);
		textFieldWord.setBounds(221, 171, 96, 37);
		frame.getContentPane().add(textFieldWord);
		textFieldWord.setColumns(10);

		lblW1 = new JLabel("");
		lblW1.setForeground(new Color(204, 102, 0));
		lblW1.setBackground(new Color(240, 128, 128));
		lblW1.setEnabled(false);
		lblW1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblW1.setBounds(55, 243, 86, 37);
		frame.getContentPane().add(lblW1);

		lblW2 = new JLabel("");
		lblW2.setForeground(new Color(204, 102, 0));
		lblW2.setEnabled(false);
		lblW2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblW2.setBounds(206, 243, 86, 37);
		frame.getContentPane().add(lblW2);

		lblW3 = new JLabel("");
		lblW3.setForeground(new Color(204, 102, 0));
		lblW3.setEnabled(false);
		lblW3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblW3.setBounds(362, 238, 86, 42);
		frame.getContentPane().add(lblW3);

		lblW4 = new JLabel("");
		lblW4.setForeground(new Color(204, 102, 0));
		lblW4.setEnabled(false);
		lblW4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblW4.setBounds(125, 290, 86, 42);
		frame.getContentPane().add(lblW4);

		lblW5 = new JLabel("");
		lblW5.setForeground(new Color(204, 102, 0));
		lblW5.setEnabled(false);
		lblW5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblW5.setBounds(311, 290, 86, 42);
		frame.getContentPane().add(lblW5);

		btnDone = new JButton("Next");
		btnDone.setForeground(new Color(204, 204, 204));
		btnDone.setBackground(new Color(102, 102, 102));
		btnDone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// agar input val khali nist...
				if (!"".equals(textFieldTedad.getText())) {
					// agar process insert kardan tamam nashode...
					if (!doneInsertingWordsOrNot) {
						// int how many tedad khane hayi e ke mikhad por beshe
						int howMany = Integer.parseInt(textFieldTedad.getText());
						// agar first time hast, pas faghat howMany ro begir agar na boro soragh
						// gereftan kalamat
						if (firstTime) {
							switch (howMany) {
							case 1:
								lblW1.setEnabled(true);
								lblTdWord.setEnabled(true);
								textFieldWord.setEnabled(true);
								lblLenght.setEnabled(true);

								break;
							case 2:
								lblW1.setEnabled(true);
								lblW2.setEnabled(true);
								lblTdWord.setEnabled(true);
								textFieldWord.setEnabled(true);
								lblLenght.setEnabled(true);
								break;
							case 3:
								lblW1.setEnabled(true);
								lblW2.setEnabled(true);
								lblW3.setEnabled(true);
								lblTdWord.setEnabled(true);
								textFieldWord.setEnabled(true);
								lblLenght.setEnabled(true);
								break;
							case 4:
								lblW1.setEnabled(true);
								lblW2.setEnabled(true);
								lblW3.setEnabled(true);
								lblW4.setEnabled(true);
								lblTdWord.setEnabled(true);
								textFieldWord.setEnabled(true);
								lblLenght.setEnabled(true);
								break;
							case 5:
								lblW1.setEnabled(true);
								lblW2.setEnabled(true);
								lblW3.setEnabled(true);
								lblW4.setEnabled(true);
								lblW5.setEnabled(true);
								lblTdWord.setEnabled(true);
								textFieldWord.setEnabled(true);
								lblLenght.setEnabled(true);
								break;
							default:
								break;
							}
							lblHowMany.setEnabled(false);
							textFieldTedad.setEnabled(false);
							firstTime = false;
						} else {
							// agar first time nist...
							if (!"".equals(textFieldWord.getText())) {
								// counterWordsToEnter neshoon dahande ine ke ma alan dar marhale chandom
								// inserting hastim
								counterWordsToEnter++;
								switch (counterWordsToEnter) {
								case 1:
									// agar dafe aval ke dare insert mishe...
									// dar khane aval kalame gerefte shode ro insert kon
									lblW1.setText(textFieldWord.getText());
									// dar khane aval array kalame gerefte shode ra insert kon
									words[0] = textFieldWord.getText();
									// check kon ke mahale i ke alan toosh hastim marhale akhar bood ya na..
									if (counterWordsToEnter == howMany) {
										btnDone.setText("Done !");
										// doneInsertingWordsOrNot ro true kon ke yani dg tamoom shode va vaghte raftan
										// be safhe badie
										doneInsertingWordsOrNot = true;
										lblTdWord.setEnabled(false);
										textFieldWord.setEnabled(false);
									} else {
										lblTdWord.setText("Second: ");
									}
									break;
								case 2:
									lblW2.setText(textFieldWord.getText());
									words[1] = textFieldWord.getText();
									if (counterWordsToEnter == howMany) {
										btnDone.setText("Done !");
										doneInsertingWordsOrNot = true;
										lblTdWord.setEnabled(false);
										textFieldWord.setEnabled(false);
									} else {
										lblTdWord.setText("Third: ");
									}
									break;
								case 3:
									lblW3.setText(textFieldWord.getText());
									words[2] = textFieldWord.getText();
									if (counterWordsToEnter == howMany) {
										btnDone.setText("Done !");
										doneInsertingWordsOrNot = true;
										lblTdWord.setEnabled(false);
										textFieldWord.setEnabled(false);
									} else {
										lblTdWord.setText("Fourth: ");
									}
									break;
								case 4:
									lblW4.setText(textFieldWord.getText());
									words[3] = textFieldWord.getText();
									if (counterWordsToEnter == howMany) {
										btnDone.setText("Done !");
										doneInsertingWordsOrNot = true;
										lblTdWord.setEnabled(false);
										textFieldWord.setEnabled(false);
									} else {
										lblTdWord.setText("Fifth: ");
									}
									break;
								case 5:
									lblW5.setText(textFieldWord.getText());
									words[4] = textFieldWord.getText();
									if (counterWordsToEnter == howMany) {
										btnDone.setText("Done !");
										doneInsertingWordsOrNot = true;
										lblTdWord.setEnabled(false);
										textFieldWord.setEnabled(false);
									}
									break;
								default:
									break;
								}
								textFieldWord.setText("");
							} else {
								JOptionPane.showMessageDialog(null, "Empty Text Field ! 	 \t\t\t -_-");
							}
						}
					} else {
						GUITestBetween betweenPage = new GUITestBetween();
						try {
							// baraye main safhe badi (between page) array words ro be onvan args barash
							// mifrestam.
							betweenPage.main(words);
							frame.dispose();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Empty Text Field ! 	 \t\t\t -_-");
				}
			}
		});
		btnDone.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDone.setBounds(362, 116, 86, 94);
		frame.getContentPane().add(btnDone);
		frame.setUndecorated(true);
		lblLenght = new JLabel("( Lenght < 5 )");
		lblLenght.setForeground(new Color(204, 204, 204));
		lblLenght.setEnabled(false);
		lblLenght.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblLenght.setBounds(98, 199, 86, 19);
		frame.getContentPane().add(lblLenght);

		lblNewLabel = new JLabel("______________________________________________________________");
		lblNewLabel.setBounds(55, 87, 464, 13);
		frame.getContentPane().add(lblNewLabel);

		label = new JLabel("______________________________________________________________");
		label.setBounds(55, 220, 464, 13);
		frame.getContentPane().add(label);

		btnExite = new JButton("");
		Image img = new ImageIcon(this.getClass().getResource("/close.png")).getImage();
		btnExite.setIcon(new ImageIcon(img));
		btnExite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExite.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnExite.setBackground(new Color(204, 204, 204));
		btnExite.setForeground(new Color(204, 102, 0));
		btnExite.setBounds(485, 0, 39, 32);
		frame.getContentPane().add(btnExite);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setBackground(new Color(204, 102, 0));
		textField.setBounds(-12, -10, 550, 42);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		frame.setResizable(false);
	}

	public void setVisible(boolean b) {
		frame.setVisible(true);
	}
}
