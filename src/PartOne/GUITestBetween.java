package PartOne;
import PartTwo.GUITest2;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JOptionPane;

public class GUITestBetween {
	public static void main(String[] args) throws FileNotFoundException {
		// args gerefte shode tavasot main haman words ha hastan ke az GUITest1
		// ferestade shodan.
		// alan ya words por shode ya mosavi $ e ke yani por nashode, ke in dastan ba
		// wordsCounter check mishe ke bebinim chandta kalame vaghei be onvan voroodi
		// gerefte shode.
		int wordsCounter = 0;
		for (int i = 0; i < args.length; i++) {
			if (args[i] != "$") {
				wordsCounter++;
			} else {
				break;
			}
		}
		// hala khane haye array args main ro to ye String array i be nam wordsNew
		// mirizam
		String[] wordsNew = new String[wordsCounter];
		for (int i = 0; i < wordsNew.length; i++) {
			wordsNew[i] = args[i];
		}
		// yek array boolean ham dorost mikonam baraye inke :
		// agar dar proccess insert kardan, kalamei dar khanei az jadval insert shod,
		// dobare oon khone random baraye kalamei dg entekhab nashe va unique baraye ye
		// word bekhosoos bashe.
		boolean[][] wordsNewBoolean = new boolean[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				wordsNewBoolean[i][j] = false;
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (wordsNewBoolean[i][j] == true) {
					System.out.print("1");
				} else {
					System.out.print("0");
				}
			}
			System.out.println("");
		}
		// I HAVE WRITEN THE WORDS IN A TXT FILE AND I HAVE THEM IN ==> wordsNew[i]
		// giving a location to the words -------------------------->

		// wordsNewIndex :
		// dar ghesmat aval proje har kalame gharare location monaseb khodesh ro peida
		// kone va oonja save beshan ke moghe insert kardan
		// betoonam to hamoon index ha insert konim :

		// NOTE : (be ghesmat dovom asla va abada hich index i ferestade nemishe)

		// (manzoor az location monaseb check kardan ine ke kalame ba location random
		// khodesh dar kodoom jahat 1 2 3 4 5 6 7 8 mitoone ja beshe !)

		// (baraye insert kardan dar har khoone wordsNewIndex charAt(0) neshoon dahande
		// i
		// va charAt(1) neshoon dahande j va charAt(2) neshoon dahande jahat i e ke
		// gharare por beshe toosh)
		String[] wordsNewIndex = new String[wordsNew.length];
		// random baraye peida kardan jahat por shodan kalame e
		Random randwordsNewIndexIJ = new Random();
		// foundHeadedTo variable i e ke neshoon mide aya jahat monaseb baraye por
		// shodan peida shode ya na
		boolean foundHeadedTo = false;
		// foundFilled boolean i hast ke check kone aya khane haye pishe roo hame khali
		// (khali) hastand ya na
		boolean foundFilled = false;
		// in for har word ro migire va shoroo mikone anjam process peida kardan jahat
		// monaseb ba location monaseb ro
		for (int i = 0; i < wordsNewIndex.length; i++) {
			foundHeadedTo = false;
			System.out.println("% " + i + " " + wordsNew[i] + " omad");
			wordsNewIndex[i] = "";
			// dadan do ta Location Coordination be word
			int iCoordination = randwordsNewIndexIJ.nextInt(10);
			int jCoordination = randwordsNewIndexIJ.nextInt(10);
			wordsNewIndex[i] = Integer.toString(iCoordination) + Integer.toString(jCoordination);
			int rnd3HeadedTo = 0;
			int counter = 1;
			// counterBlocked baraye zamanie ke yek word, ba tavajoh be i va j random i ke
			// behesh dade shode, hich jahate monasebi az 1 ta 8 peida nemikone baraye por
			// shodan, pas agar ta 20 bar natoonest ke peida kone, dobare az aval for shoroo
			// mikone baraye hamoon kalame, i va j taze dadan va search i dobare baraye
			// peida kardan jahat .
			int counterBlocked = 0;
			while (!foundHeadedTo) {
				counterBlocked++;
				if (counterBlocked == 20) {
					System.out.println("BLOCKED");
					i = i - 1;
					break;
				}
				int iCheck = iCoordination;
				int jCheck = jCoordination;
				counter = 1;
				rnd3HeadedTo = 0;
				// 1 == bala, 2 == rast, 3 == pain, 4 == chap, 5 == bala rast, 6 == pain rast, 7
				// == pain chap, 8 == bala chap
				rnd3HeadedTo = randwordsNewIndexIJ.nextInt(8) + 1;
				switch (rnd3HeadedTo) {
				// agar jahat eandom entekhab shode baraye kalame 1 bood, yani bala
				case 1:
					System.out.println("_1");
					// agar ke dar samt 1 jai be tedad character haye in word ja bood...
					if ((iCoordination + 1) - wordsNew[i].length() >= 0) {
						foundFilled = false;
						// marahele check kardan inke aya khane haye jelo hamegi khali (false) hastand
						// ya na
						for (int j = 1; j <= wordsNew[i].length(); j++) {
							if (wordsNewBoolean[iCheck][jCheck]) {
								foundFilled = true;
								break;
							}
							iCheck--;
						}
						iCheck = iCoordination;
						jCheck = jCoordination;
						if (!foundFilled) {
							// hal age jeloye kalame ja bood, va tamami khane ha khali bood pas halle dg
							// mishe inja ja beshe.
							System.out.println(iCoordination + " " + jCoordination + " Acceted in 1");
							// hal tamami khanehaye pish roo ro por (true) mikonim ke dafe dg word dg i inja
							// ja nagire.
							for (int j = 1; j <= wordsNew[i].length(); j++) {
								wordsNewBoolean[iCheck][jCheck] = true;
								iCheck--;
							}
							// hala dar charAt(2) index array kalame morede nazr 1 mizarim ke yani kalame
							// dar i va j, jahat makhsoose be khodesh ro ham dare.
							wordsNewIndex[i] += "1";
							// haka foundHeadedTo ro ham true mikonim ke yani in kalame ja barash peida shod
							// va nobat kalame badie :)
							foundHeadedTo = true;
						} else {
							System.out.println(iCoordination + " " + jCoordination + " Rejected in 1*");
						}
					} else {
						System.out.println(iCoordination + " " + jCoordination + " Rejected in 1");
					}
					break;
				case 2:
					System.out.println("_2");
					if ((jCoordination + 1) + wordsNew[i].length() <= 10) {
						foundFilled = false;
						for (int j = 1; j <= wordsNew[i].length(); j++) {
							if (wordsNewBoolean[iCheck][jCheck]) {
								foundFilled = true;
								break;
							}
							jCheck++;
						}
						iCheck = iCoordination;
						jCheck = jCoordination;
						if (!foundFilled) {
							System.out.println(iCoordination + " " + jCoordination + " Acceted in 2");
							for (int j = 1; j <= wordsNew[i].length(); j++) {
								wordsNewBoolean[iCheck][jCheck] = true;
								jCheck++;
							}
							wordsNewIndex[i] += "2";
							foundHeadedTo = true;
						} else {
							System.out.println(iCoordination + " " + jCoordination + " Rejected in 2*");
						}
					} else {
						System.out.println(iCoordination + " " + jCoordination + " Rejected in 2");
					}
					break;
				case 3:
					System.out.println("_3");
					if ((iCoordination) + wordsNew[i].length() <= 10) {
						foundFilled = false;
						for (int j = 1; j <= wordsNew[i].length(); j++) {
							if (wordsNewBoolean[iCheck][jCheck]) {
								foundFilled = true;
								break;
							}
							iCheck++;
						}
						iCheck = iCoordination;
						jCheck = jCoordination;
						if (!foundFilled) {
							System.out.println(iCoordination + " " + jCoordination + " Acceted in 3");
							for (int j = 1; j <= wordsNew[i].length(); j++) {
								wordsNewBoolean[iCheck][jCheck] = true;
								iCheck++;
							}
							wordsNewIndex[i] += "3";
							foundHeadedTo = true;
						} else {
							System.out.println(iCoordination + " " + jCoordination + " Rejected in 3*");
						}
					} else {
						System.out.println(iCoordination + " " + jCoordination + " Rejected in 3");
					}
					break;
				case 4:
					System.out.println("_4");
					if ((jCoordination + 1) - wordsNew[i].length() >= 0) {
						foundFilled = false;
						for (int j = 1; j <= wordsNew[i].length(); j++) {
							if (wordsNewBoolean[iCheck][jCheck]) {
								foundFilled = true;
								break;
							}
							jCheck--;
						}
						iCheck = iCoordination;
						jCheck = jCoordination;
						if (!foundFilled) {
							System.out.println(iCoordination + " " + jCoordination + " Acceted in 4");
							for (int j = 1; j <= wordsNew[i].length(); j++) {
								wordsNewBoolean[iCheck][jCheck] = true;
								jCheck--;
							}
							wordsNewIndex[i] += "4";
							foundHeadedTo = true;
						} else {
							System.out.println(iCoordination + " " + jCoordination + " Rejected in 4*");
						}
					} else {
						System.out.println(iCoordination + " " + jCoordination + " Rejected in 4");
					}
					break;
				case 5:
					System.out.println("_5");
					iCheck = iCoordination;
					jCheck = jCoordination;
					foundFilled = false;
					foundHeadedTo = false;
					// marahele check kardan inke aya baraye khane haye 5 o 6 o 7 o 8 yekam
					// motafavete, inja check mishe ke aya dar masire roberoo ja hast ya na, va age
					// ja hast dar masire iCHeck va jCheck hast ya na...
					for (counter = 1; counter <= wordsNew[i].length() && iCheck >= 0 && jCheck <= 9; counter++) {
						if (wordsNewBoolean[iCheck][jCheck]) {
							foundFilled = true;
							break;
						}
						iCheck--;
						jCheck++;
					}
					iCheck = iCoordination;
					jCheck = jCoordination;
					if (foundFilled) {
						System.out.println(iCoordination + " " + jCoordination + " Rejected in 5*");
					} else {
						for (counter = 1; counter <= wordsNew[i].length() && iCheck >= 0 && jCheck <= 9; counter++) {
							if (counter == wordsNew[i].length()) {
								System.out.println(iCoordination + " " + jCoordination + " Acceted in 5");
								iCheck = iCoordination;
								jCheck = jCoordination;
								for (counter = 1; counter <= wordsNew[i].length() && iCheck >= 0
										&& jCheck <= 9; counter++) {
									wordsNewBoolean[iCheck][jCheck] = true;
									iCheck--;
									jCheck++;
								}
								wordsNewIndex[i] += "5";
								foundHeadedTo = true;
								break;
							}
							iCheck--;
							jCheck++;
						}
						if (counter != wordsNew[i].length() && !foundHeadedTo) {
							System.out.println(iCoordination + " " + jCoordination + " Rejected in 5");
						} else {
							if (foundHeadedTo) {
								break;
							}
						}
					}
					break;
				case 6:
					System.out.println("_6");
					foundFilled = false;
					foundHeadedTo = false;
					for (counter = 1; counter <= wordsNew[i].length() && iCheck <= 9 && jCheck <= 9; counter++) {
						if (wordsNewBoolean[iCheck][jCheck]) {
							foundFilled = true;
							break;
						}
						iCheck++;
						jCheck++;
					}
					iCheck = iCoordination;
					jCheck = jCoordination;
					if (foundFilled) {
						System.out.println(iCoordination + " " + jCoordination + " Rejected in 6*");
					} else {
						for (counter = 1; counter <= wordsNew[i].length() && iCheck <= 9 && jCheck <= 9; counter++) {
							if (counter == wordsNew[i].length()) {
								System.out.println(iCoordination + " " + jCoordination + " Acceted in 6");
								iCheck = iCoordination;
								jCheck = jCoordination;
								for (counter = 1; counter <= wordsNew[i].length() && iCheck <= 9
										&& jCheck <= 9; counter++) {
									wordsNewBoolean[iCheck][jCheck] = true;
									iCheck++;
									jCheck++;
								}
								wordsNewIndex[i] += "6";
								foundHeadedTo = true;
								break;
							}
							iCheck++;
							jCheck++;
						}
						if (counter != wordsNew[i].length() && !foundHeadedTo) {
							System.out.println(iCoordination + " " + jCoordination + " Rejected in 6");
						} else {
							if (foundHeadedTo) {
								break;
							}
						}
					}
				case 7:
					System.out.println("_7");
					iCheck = iCoordination;
					jCheck = jCoordination;
					foundFilled = false;
					foundHeadedTo = false;
					for (counter = 1; counter <= wordsNew[i].length() && iCheck <= 9 && jCheck >= 0; counter++) {
						if (wordsNewBoolean[iCheck][jCheck]) {
							foundFilled = true;
							break;
						}
						iCheck++;
						jCheck--;
					}
					iCheck = iCoordination;
					jCheck = jCoordination;
					if (foundFilled) {
						System.out.println(iCoordination + " " + jCoordination + " Rejected in 7*");
					} else {
						for (counter = 1; counter <= wordsNew[i].length() && iCheck <= 9 && jCheck >= 0; counter++) {
							if (counter == wordsNew[i].length()) {
								System.out.println(iCoordination + " " + jCoordination + " Acceted in 7");
								iCheck = iCoordination;
								jCheck = jCoordination;
								for (counter = 1; counter <= wordsNew[i].length() && iCheck <= 9
										&& jCheck >= 0; counter++) {
									wordsNewBoolean[iCheck][jCheck] = true;
									iCheck++;
									jCheck--;
								}
								wordsNewIndex[i] += "7";
								foundHeadedTo = true;
								break;
							}
							iCheck++;
							jCheck--;
						}

						if (counter != wordsNew[i].length() && !foundHeadedTo) {
							System.out.println(iCoordination + " " + jCoordination + " Rejected in 7");
						} else {
							if (foundHeadedTo) {
								break;
							}
						}
					}
					break;
				case 8:
					System.out.println("_8");
					iCheck = iCoordination;
					jCheck = jCoordination;
					foundFilled = false;
					foundHeadedTo = false;
					for (counter = 1; counter <= wordsNew[i].length() && iCheck >= 0 && jCheck >= 0; counter++) {
						if (wordsNewBoolean[iCheck][jCheck]) {
							foundFilled = true;
							break;
						}
						iCheck--;
						jCheck--;
					}
					iCheck = iCoordination;
					jCheck = jCoordination;
					if (foundFilled) {
						System.out.println(iCoordination + " " + jCoordination + " Rejected in 8*");
					} else {
						for (counter = 1; counter <= wordsNew[i].length() && iCheck >= 0 && jCheck >= 0; counter++) {
							if (counter == wordsNew[i].length()) {
								System.out.println(iCoordination + " " + jCoordination + " Acceted in 8");
								iCheck = iCoordination;
								jCheck = jCoordination;
								for (counter = 1; counter <= wordsNew[i].length() && iCheck >= 0
										&& jCheck >= 0; counter++) {
									wordsNewBoolean[iCheck][jCheck] = true;
									iCheck--;
									jCheck--;
								}
								wordsNewIndex[i] += "8";
								foundHeadedTo = true;
								break;
							}
							iCheck--;
							jCheck--;
						}
						if (counter != wordsNew[i].length() && !foundHeadedTo) {
							System.out.println(iCoordination + " " + jCoordination + " Rejected in 8");
						} else {
							if (foundHeadedTo) {
								break;
							}
						}
					}
					break;
				default:
					System.out.println(":(");
					break;
				}
			}
		}
		// ---------------------------------------------------------0
		// make a 2D Array with the coordinates -------------------->

		// hala index har kalame shamel se ghesmat i o j o masir e, hala vaghteshe ke
		// jadval ro ba ina por konim :)

		// aval az hame kol jadval o ba $ por mikonim.
		String[][] matrisWithTheHiddenWords = new String[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				matrisWithTheHiddenWords[i][j] = "$";
			}
		}
		for (int i = 0; i < wordsNewIndex.length; i++) {
			int iMatris = wordsNewIndex[i].charAt(0) - '0';
			int jMatris = wordsNewIndex[i].charAt(1) - '0';
			int counter = 0;
			switch (wordsNewIndex[i].charAt(2) - '0') {
			case 1:
				counter = 0;
				// dar jahat 1 i done done kam mishe ta be samt bala biad por beshe
				// por shodan yani inke inja dar har khoone, yek character az oon kalame insert
				// mishe.
				for (int i2 = iMatris; counter != wordsNew[i].length(); i2--) {
					matrisWithTheHiddenWords[i2][jMatris] = Character.toString(wordsNew[i].charAt(counter));
					counter++;
				}
				break;
			case 2:
				counter = 0;
				for (int j2 = jMatris; counter != wordsNew[i].length(); j2++) {
					matrisWithTheHiddenWords[iMatris][j2] = Character.toString(wordsNew[i].charAt(counter));
					counter++;
				}
				break;
			case 3:
				counter = 0;
				for (int i2 = iMatris; counter != wordsNew[i].length(); i2++) {
					matrisWithTheHiddenWords[i2][jMatris] = Character.toString(wordsNew[i].charAt(counter));
					counter++;
				}
				break;
			case 4:
				counter = 0;
				for (int j2 = jMatris; counter != wordsNew[i].length(); j2--) {
					matrisWithTheHiddenWords[iMatris][j2] = Character.toString(wordsNew[i].charAt(counter));
					counter++;
				}
				break;
			case 5:
				for (counter = 0; counter < wordsNew[i].length(); counter++) {
					matrisWithTheHiddenWords[iMatris][jMatris] = Character.toString(wordsNew[i].charAt(counter));
					iMatris--;
					jMatris++;
				}
				break;
			case 6:
				for (counter = 0; counter < wordsNew[i].length(); counter++) {
					matrisWithTheHiddenWords[iMatris][jMatris] = Character.toString(wordsNew[i].charAt(counter));
					iMatris++;
					jMatris++;
				}
				break;
			case 7:
				for (counter = 0; counter < wordsNew[i].length(); counter++) {
					matrisWithTheHiddenWords[iMatris][jMatris] = Character.toString(wordsNew[i].charAt(counter));
					iMatris++;
					jMatris--;
				}
				break;
			case 8:
				for (counter = 0; counter < wordsNew[i].length(); counter++) {
					matrisWithTheHiddenWords[iMatris][jMatris] = Character.toString(wordsNew[i].charAt(counter));
					iMatris--;
					jMatris--;
				}
				break;
			default:
				break;
			}
		}
		// -----------------------------------------------------------0
		// haminjoori yek array jadid dorost mikonam ke age $ NAbood (yani ye character
		// kalame bekhosoosi toosh khabide) hamoono bezar agaram $ Bood ye Character
		// random toosh insert kon.
		String[][] lastWord = new String[10][10];
		Random randLast = new Random();
		for (int i = 0; i < lastWord.length; i++) {
			for (int j = 0; j < lastWord.length; j++) {
				if (!"$".equals(matrisWithTheHiddenWords[i][j])) {
					lastWord[i][j] = matrisWithTheHiddenWords[i][j];
				} else {
					int rnd = randLast.nextInt(25) + 1;
					rnd += 97;
					lastWord[i][j] = Character.toString((char) rnd);
				}
				System.out.print(lastWord[i][j]);
			}
			System.out.println("");
		}
		// print the 2D array in matris.txt-------------------------->

		// hala nobat insert kardan matris dar ye file i be nam words.txt e, ke har satr
		// insert mishe badesh yek "_" mikhore,
		// bad az inke jadval tamoom shod inserting esh, yek "@" insert mishe ke gharare
		// badesh kalamat i ke hidden shode insert beshe ke oona ham ba "_" az ham joda
		// shodan, va dar nahaye "/" insert mishe ke yani yek jadval ba tamam
		// mohtaviatesh insert shode va kare ma tamoome, hala age mikhad dobare mitoone
		// too line
		String fullStr = "";
		try {
			FileReader fr = new FileReader("matris.txt");
			BufferedReader br = new BufferedReader(fr);
			fullStr = br.readLine();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "NOT FOUND ! (2_1)");
		}
		if (fullStr == null) {
			fullStr = "default_2_@default_2/";
		}
		PrintWriter fileOutMatris = new PrintWriter("matris.txt");
		fileOutMatris.print(fullStr);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				fileOutMatris.print(lastWord[i][j]);
			}
			fileOutMatris.print("_");
		}
		fileOutMatris.print("@");
		fileOutMatris.print(wordsNew[0]);
		for (int i = 1; i < wordsNew.length; i++) {
			fileOutMatris.print("_" + wordsNew[i]);
		}
		fileOutMatris.print("/");
		fileOutMatris.close();
		// ---------------------------------------------------------0
		System.out.println("PRINTED");
		GUITest firstPage = new GUITest();
		GUITest2 secondPage = new GUITest2();
		int reply = JOptionPane.showConfirmDialog(null, "Do you want to build an another Word Search Table?", "Close?",
				JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			firstPage.setVisible(true);
		} else {
			secondPage.setVisible(true);
		}
	}
}
