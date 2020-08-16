package PartTwo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import org.w3c.dom.css.Counter;

import PartOne.GUITest;

import javax.swing.JLabel;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JDesktopPane;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;

public class GUITest2 extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldGuessedWord;
	private JLabel lblFindAWord;
	private JButton btnGuess;
	private JTextArea textArea;
	private JTextArea textAreaHidden;
	// remainings variable i e ke mige chandta kalame dg bara hads zadan baghi
	// moonde
	int remainings = 0;

	/**
	 * Launch the application.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUITest2 frame = new GUITest2();
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
	// dar split kardan file matris.txt be "_" har ghesmat yek matris ba words hash
	// e ke ba @ az hamdige joda shodan. ghesmat ghabl @ str1 va badesh str2 e.
	String str1 = "";
	String str2 = "";
	private JLabel label;
	private JLabel label_1;
	private JTextField textField;
	private JButton btnExite;

	public GUITest2() {
		String[][] matrisColored = new String[10][10];
		try {
			// read the matris.txt file
			FileReader fr = new FileReader("matris.txt");
			BufferedReader br = new BufferedReader(fr);

			String fullStr;
			// fullStr kol etelaat dakhel file e
			fullStr = br.readLine();
			String[] matrisPartBetweenBrackets = fullStr.split("/");
			for (int j = 0; j < matrisPartBetweenBrackets.length; j++) {
				System.out.println(matrisPartBetweenBrackets[j]);
			}
			Random brackets = new Random();
			// hala yek matris be delkhah entekhab mishe, montaha matris aval
			// (default_@default/) dar nazar gerefte nemishe !
			int rndBrackets = brackets.nextInt(matrisPartBetweenBrackets.length - 1) + 1;
			String[] str = matrisPartBetweenBrackets[rndBrackets].split("@");
			str1 = str[0];
			str2 = str[1];
			remainings = str[1].split("_").length;
			System.out.println(remainings + " left !");
			String[] lines = str[0].split("_");
			// hala kol etelaat jadval ro dar matris i be nam matrisColored mirizim
			for (int i = 0; i < lines.length; i++) {
				for (int j = 0; j < lines.length; j++) {
					matrisColored[i][j] = Character.toString(lines[i].charAt(j));
				}
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "NOT FOUND ! (21)");
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 644);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Word Search Table");
		lblFindAWord = new JLabel("Find a Word :");
		lblFindAWord.setForeground(new Color(51, 51, 51));
		lblFindAWord.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblFindAWord.setBounds(172, 61, 168, 45);
		contentPane.add(lblFindAWord);
		setUndecorated(true);
		textFieldGuessedWord = new JTextField();
		textFieldGuessedWord.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldGuessedWord.setBackground(new Color(204, 204, 204));
		textFieldGuessedWord.setForeground(new Color(204, 102, 0));
		textFieldGuessedWord.setBounds(172, 537, 168, 34);
		contentPane.add(textFieldGuessedWord);
		textFieldGuessedWord.setColumns(10);

		// --------------------------------------------
		// ta inja, etelaat dakhel file dakhel variable hayi zakhire shode, hala marhale
		// asli ghesmat dovom proje zamani start mikhore ke dokme guess zade mishe...
		// --------------------------------------------

		// age deghat koni to khat bad az str2 estefade shode, str2 ghesmat dovom
		// (words) matris random i e ke aval kar entekhab shode, ke inja word haro
		// estekhraj mikonim
		String[] words = str2.split("_");
		boolean[] wordsAvailable = new boolean[words.length];
		for (int i = 0; i < wordsAvailable.length; i++) {
			wordsAvailable[i] = true;
		}

		btnGuess = new JButton("GUESS");
		btnGuess.setForeground(new Color(255, 255, 255));
		btnGuess.setBackground(new Color(102, 102, 102));
		btnGuess.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// agar dakhel textfield khali nabood...
				if (!"".equals(textFieldGuessedWord.getText()) && remainings != 0) {
					try {
						// tricky variable i e ke check mikone age ye kalame dadi ke to jadvak bood,
						// oono dobare nade !
						boolean tricky = false;
						boolean foundOrNot = false;
						// hala nobat ine ke bebinim kalame i ke too input dade aya ba hichkodoom az
						// kalame haye dakhel file yeki hast ya na...
						for (int i = 0; i < words.length; i++) {
							if (textFieldGuessedWord.getText().equals(words[i])) {
								if (wordsAvailable[i] == true) {
									System.out.println(words[i]);
									remainings--;
									wordsAvailable[i] = false;
									// halle ta inja yadi kalame i ke too jadval peida shode yeki az kalamati ke too
									// jadval penhan shode hast.

									// check konim bebinim aya hame klamt i ro ke bayad to jadval peida mikarde ro
									// peida karde ya na...
									if (remainings == 0) {
										textArea.setEnabled(false);
										lblFindAWord.setEnabled(false);
										JOptionPane.showMessageDialog(null, "Found ! \t  \t\t\t Index : ( "
												+ findWord(matrisColored, words[i]) + " ) ");
										JOptionPane.showMessageDialog(null, "YOU WON !");
										textFieldGuessedWord.setEnabled(false);
										btnGuess.setBounds(172, 581, 168, 45);
										btnGuess.setText("Play Again !");
										btnGuess.setBackground(new Color(204, 102, 0));
									} else {
										JOptionPane.showMessageDialog(null,
												"Found ! \t  \t\t\t Index : ( " + findWord(matrisColored, words[i])
														+ " ) " + remainings + " remained.");
									}
									foundOrNot = true;
									break;
								} else {
									JOptionPane.showMessageDialog(null,
											"You've already found this word man c'mon \t\t\t -_-");
									tricky = true;
								}
							}
						}
						if (!foundOrNot && !tricky) {
							JOptionPane.showMessageDialog(null, "Doesn't Exist \t\t\t :(");
						}
					} catch (Exception e4) {
						JOptionPane.showMessageDialog(null, "NOT FOUND ! (22)");
					}
				} else {
					if (remainings == 0) {
						try {
							PrintWriter fileOutSetDefault = new PrintWriter("matris.txt");
							fileOutSetDefault.print("default_@default/");
							fileOutSetDefault.close();
							GUITest startAgain = new GUITest();
							dispose();
							startAgain.setVisible(true);
						} catch (FileNotFoundException e1) {
							System.out.println("Not Found !");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Empty Text Field ! 	 \t\t\t -_-");
					}
				}
			}

			// halaa in ghesmat function findWord karesh ine ke miad kalame dade shode too
			// input ro ke ta injaye kar motmaenim yeki az kalamat dakhel jadval hast ro
			// peida mikone ke index esh chie, yani ye index of 2D mizanim :)

			public String findWord(String[][] matris, String word) {
				String wordCoordinations = "";
				int iTemp = 0;
				int jTemp = 0;
				boolean found = false;
				for (int i = 0; i <= 9; i++) {
					for (int j = 0; j <= 9; j++) {
						// khob hala ke ma matris ro darim az khoone aval shoroo mikonim bebinim ke aya
						// character khabide to in khoone ba character aval kalame morede nazar yeki
						// hast ya na...
						if (matris[i][j].charAt(0) == word.charAt(0)) {
							iTemp = i;
							jTemp = j;
							int iCheck = 0;
							int jCheck = 0;
							System.out.println(iTemp + " and " + jTemp + " are SUSPICIOUS !");
							// ta injaye kar yani yek harf peida shode to jadval ke ba harf aval kalame
							// morede nazar barabare, yani mashkooke...

							// hala mirim to tamam jahat haye 1 o 2 o 3 o 4 o 5 o 6 o 7 o 8 check mikonim ke
							// aya character haye badi kalame mored nazar to in jahat matris khabidan ya na.

							// c variable i e ke namayande jahat haye 1 ta 8 e ke az jahat 1 shoroo
							// mikone...

							// agar dar har jahat tamam character haro peida kone found ro true mikone !
							int c = 1;
							while (!found) {
								if (c == 1) {
									System.out.println("1");
									iCheck = iTemp;
									jCheck = jTemp;
									// marahel check kardan ba in shoroo mishe ke bebinim aya pish romoon dar jahat
									// 1, aya be tedad character haye kalame mored nazar ja hast ya na akhe...
									if ((iCheck + 1) - word.length() >= 0) {
										// hala gharare be tedad character haye kalame morede nazar berim jelo...
										for (int charCounter = 0; charCounter < word.length(); charCounter++) {
											// age character haye khoone ha ba ham barabar naboodan ghazie cancele va
											// kalame too jahay c == 1 nakhabide.
											if (matris[iCheck][jCheck].charAt(0) != word.charAt(charCounter)) {
												found = false;
												break;
											} else {
												// khob age ta inja omadi yani check shode va character ha dar marhale
												// mored nazar ba ham equal boodan, hala age marhale mored nazar,
												// marhale akhar bashe va inja oomadi yani found shode kalame mored
												// nazar va hala nobat taid nahai e.
												if (charCounter == word.length() - 1) {
													iCheck = iTemp;
													jCheck = jTemp;
													// let's make em all UPERCASE !
													for (int counterToUppercase = 0; counterToUppercase < word
															.length(); counterToUppercase++) {
														matris[iCheck][jCheck] = Character.toString(
																Character.toUpperCase(word.charAt(counterToUppercase)));
														iCheck--;
													}
													// hala tamami UPERCASE character haro dar yek matris rikhte va
													// sepas dar file write mikonim.
													textArea.setText("");
													for (int k = 0; k < 10; k++) {
														for (int l = 0; l < 10; l++) {
															textArea.setText(textArea.getText() + matris[k][l]);
														}
														textArea.setText(textArea.getText() + "\n");
													}
													return Integer.toString(iTemp + 1) + " , "
															+ Integer.toString(jTemp + 1);
												}
											}
											iCheck--;
										}
									}
								} else if (c == 2) {
									System.out.println("2");
									iCheck = iTemp;
									jCheck = jTemp;
									if ((jTemp + 1) + word.length() <= 10) {
										for (int charCounter = 0; charCounter < word.length(); charCounter++) {
											if (matris[iCheck][jCheck].charAt(0) != word.charAt(charCounter)) {
												found = false;
												break;
											} else {
												if (charCounter == word.length() - 1) {
													iCheck = iTemp;
													jCheck = jTemp;
													for (int counterToUppercase = 0; counterToUppercase < word
															.length(); counterToUppercase++) {
														matris[iCheck][jCheck] = Character.toString(
																Character.toUpperCase(word.charAt(counterToUppercase)));
														jCheck++;
													}
													textArea.setText("");
													for (int k = 0; k < 10; k++) {
														for (int l = 0; l < 10; l++) {
															textArea.setText(textArea.getText() + matris[k][l]);
														}
														textArea.setText(textArea.getText() + "\n");
													}
													return Integer.toString(iTemp + 1) + " , "
															+ Integer.toString(jTemp + 1);
												}
											}
											jCheck++;
										}
									}
								} else if (c == 3) {
									System.out.println("3");
									iCheck = iTemp;
									jCheck = jTemp;
									if ((iTemp) + word.length() <= 10) {
										for (int charCounter = 0; charCounter < word.length(); charCounter++) {
											if (matris[iCheck][jCheck].charAt(0) != word.charAt(charCounter)) {
												found = false;
												break;
											} else {
												if (charCounter == word.length() - 1) {
													iCheck = iTemp;
													jCheck = jTemp;
													for (int counterToUppercase = 0; counterToUppercase < word
															.length(); counterToUppercase++) {
														matris[iCheck][jCheck] = Character.toString(
																Character.toUpperCase(word.charAt(counterToUppercase)));
														iCheck++;
													}
													textArea.setText("");
													for (int k = 0; k < 10; k++) {
														for (int l = 0; l < 10; l++) {
															textArea.setText(textArea.getText() + matris[k][l]);
														}
														textArea.setText(textArea.getText() + "\n");
													}
													return Integer.toString(iTemp + 1) + " , "
															+ Integer.toString(jTemp + 1);
												}
											}
											iCheck++;
										}
									}
								} else if (c == 4) {
									System.out.println("4");
									iCheck = iTemp;
									jCheck = jTemp;
									found = false;
									if ((jTemp + 1) - word.length() >= 0) {
										for (int charCounter = 0; charCounter < word.length(); charCounter++) {
											if (matris[iCheck][jCheck].charAt(0) == word.charAt(charCounter)) {
												if (charCounter == word.length() - 1) {
													iCheck = iTemp;
													jCheck = jTemp;
													for (int counterToUppercase = 0; counterToUppercase < word
															.length(); counterToUppercase++) {
														matris[iCheck][jCheck] = Character.toString(
																Character.toUpperCase(word.charAt(counterToUppercase)));
														jCheck--;
													}
													textArea.setText("");
													for (int k = 0; k < 10; k++) {
														for (int l = 0; l < 10; l++) {
															textArea.setText(textArea.getText() + matris[k][l]);
														}
														textArea.setText(textArea.getText() + "\n");
													}
													return Integer.toString(iTemp + 1) + " , "
															+ Integer.toString(jTemp + 1);
												}
											}
											jCheck--;
										}
									}
								} else if (c == 5) {
									System.out.println("5");
									iCheck = iTemp;
									jCheck = jTemp;
									// marrahel 5 o 6 o 7 o 8 ham mese ghabliast ba in tafavot ke i va j ham bayesti
									// dar rastaye khodeshoon check beshan...
									for (int charCounter = 0; charCounter < word.length() && iCheck >= 0
											&& jCheck <= 9; charCounter++) {
										if (matris[iCheck][jCheck].charAt(0) != word.charAt(charCounter)) {
											found = false;
											break;
										} else {
											if (charCounter == word.length() - 1) {
												iCheck = iTemp;
												jCheck = jTemp;
												for (int counterToUppercase = 0; counterToUppercase < word
														.length(); counterToUppercase++) {
													matris[iCheck][jCheck] = Character.toString(
															Character.toUpperCase(word.charAt(counterToUppercase)));
													iCheck--;
													jCheck++;
												}
												textArea.setText("");
												for (int k = 0; k < 10; k++) {
													for (int l = 0; l < 10; l++) {
														textArea.setText(textArea.getText() + matris[k][l]);
													}
													textArea.setText(textArea.getText() + "\n");
												}
												return Integer.toString(iTemp + 1) + " , "
														+ Integer.toString(jTemp + 1);
											}
										}
										iCheck--;
										jCheck++;
									}
								} else if (c == 6) {
									System.out.println("6");
									iCheck = iTemp;
									jCheck = jTemp;
									for (int charCounter = 0; charCounter < word.length() && iCheck <= 9
											&& jCheck <= 9; charCounter++) {
										if (matris[iCheck][jCheck].charAt(0) != word.charAt(charCounter)) {
											found = false;
											break;
										} else {
											if (charCounter == word.length() - 1) {
												iCheck = iTemp;
												jCheck = jTemp;
												for (int counterToUppercase = 0; counterToUppercase < word
														.length(); counterToUppercase++) {
													matris[iCheck][jCheck] = Character.toString(
															Character.toUpperCase(word.charAt(counterToUppercase)));
													iCheck++;
													jCheck++;
												}
												textArea.setText("");
												for (int k = 0; k < 10; k++) {
													for (int l = 0; l < 10; l++) {
														textArea.setText(textArea.getText() + matris[k][l]);
													}
													textArea.setText(textArea.getText() + "\n");
												}
												return Integer.toString(iTemp + 1) + " , "
														+ Integer.toString(jTemp + 1);
											}
										}
										iCheck++;
										jCheck++;
									}
								} else if (c == 7) {
									System.out.println("7");
									iCheck = iTemp;
									jCheck = jTemp;
									for (int charCounter = 0; charCounter < word.length() && iCheck <= 9
											&& jCheck >= 0; charCounter++) {
										if (matris[iCheck][jCheck].charAt(0) != word.charAt(charCounter)) {
											found = false;
											break;
										} else {
											if (charCounter == word.length() - 1) {
												iCheck = iTemp;
												jCheck = jTemp;
												for (int counterToUppercase = 0; counterToUppercase < word
														.length(); counterToUppercase++) {
													matris[iCheck][jCheck] = Character.toString(
															Character.toUpperCase(word.charAt(counterToUppercase)));
													iCheck++;
													jCheck--;
												}
												textArea.setText("");
												for (int k = 0; k < 10; k++) {
													for (int l = 0; l < 10; l++) {
														textArea.setText(textArea.getText() + matris[k][l]);
													}
													textArea.setText(textArea.getText() + "\n");
												}
												return Integer.toString(iTemp + 1) + " , "
														+ Integer.toString(jTemp + 1);
											}
										}
										iCheck++;
										jCheck--;
									}
								} else if (c == 8) {
									System.out.println("8");
									iCheck = iTemp;
									jCheck = jTemp;
									for (int charCounter = 0; charCounter < word.length() && iCheck >= 0
											&& jCheck >= 0; charCounter++) {
										if (matris[iCheck][jCheck].charAt(0) != word.charAt(charCounter)) {
											found = false;
											break;
										} else {
											if (charCounter == word.length() - 1) {
												iCheck = iTemp;
												jCheck = jTemp;
												for (int counterToUppercase = 0; counterToUppercase < word
														.length(); counterToUppercase++) {
													matris[iCheck][jCheck] = Character.toString(
															Character.toUpperCase(word.charAt(counterToUppercase)));
													iCheck--;
													jCheck--;
												}
												textArea.setText("");
												for (int k = 0; k < 10; k++) {
													for (int l = 0; l < 10; l++) {
														textArea.setText(textArea.getText() + matris[k][l]);
													}
													textArea.setText(textArea.getText() + "\n");
												}
												return Integer.toString(iTemp + 1) + " , "
														+ Integer.toString(jTemp + 1);
											}
										}
										iCheck--;
										jCheck--;
									}
									// check mishe ke aya c == 9 hast ya na, ke age hast reset mishe, ke
									// khodemoonim, kar be inja nemikeshe chon age dar marahel ghesmat dovo proje ke
									// to aval hamin safhe hast check shode ke kalame vared shode hatman yeki az
									// kalamt too jadval hast pas hatman dar yeki az jahat haye 1 ta 8 peida khahad
									// shod...
								} else if (c == 9) {
									break;
								}
								c++;
							}
						}
					}
				}
				return "-J-";
			}
		});
		btnGuess.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGuess.setBounds(198, 581, 117, 45);
		contentPane.add(btnGuess);
		setResizable(false);
		textArea = new JTextArea();
		textArea.setForeground(new Color(51, 51, 51));
		textArea.setFont(new Font("Courier New", Font.PLAIN, 32));
		textArea.setBackground(new Color(204, 204, 204));
		textArea.setEditable(false);
		textArea.setBounds(153, 138, 197, 376);
		contentPane.add(textArea);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				textArea.setText(textArea.getText() + matrisColored[i][j]);
			}
			textArea.setText(textArea.getText() + "\n");
		}

		textAreaHidden = new JTextArea();
		textAreaHidden.setBackground(new Color(204, 204, 204));
		textAreaHidden.setFont(new Font("Courier New", Font.PLAIN, 15));
		textAreaHidden.setEditable(false);
		textAreaHidden.setBounds(376, 162, 117, 352);
		contentPane.add(textAreaHidden);
		textAreaHidden.setText("");

		label = new JLabel("_____________________________________________________________");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(70, 110, 464, 13);
		contentPane.add(label);

		label_1 = new JLabel("_____________________________________________________________");
		label_1.setBounds(70, 514, 464, 13);
		contentPane.add(label_1);

		btnExite = new JButton("");
		Image img = new ImageIcon(this.getClass().getResource("/close.png")).getImage();
		btnExite.setIcon(new ImageIcon(img));
		btnExite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExite.setBounds(507, 0, 43, 31);
		contentPane.add(btnExite);

		setLocationRelativeTo(null);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(204, 102, 0));
		textField.setBounds(-12, -14, 562, 45);
		contentPane.add(textField);
		// va last but not least, nobat ine ke oon matris nahayi ro too textArea berizim
		// va namayeshesh bedim...
		try {
			String[] wordsFinal = str2.split("_");
			for (int i = 0; i < words.length; i++) {
				textAreaHidden.setText(textAreaHidden.getText() + wordsFinal[i] + "\n");
			}
		} catch (Exception e5) {
			JOptionPane.showMessageDialog(null, "NOT FOUND ! (23)");
		}
		// THE END :)
	}
}
