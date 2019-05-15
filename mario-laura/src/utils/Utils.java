package utils;

import java.util.Scanner;

public class Utils {

	public static void writeConsole(String str, boolean sl, double s) throws InterruptedException {
		if (sl)
			System.out.println(str);
		else
			System.out.print(str);
		Thread.sleep((long) (s * 1000));
	}

	@SuppressWarnings("resource")
	public static String read() {
		Scanner keyboard = new Scanner(System.in);
		return keyboard.nextLine();
	}

	public static String firstToUpper(String str) {
		str = str.toLowerCase();
		String firstL = str.substring(0, 1).toUpperCase();
		return firstL + str.substring(1);
	}

	public static void counterDown(int n) throws InterruptedException {
		for (int i = n; i >= 0; i--) {
			if (i == 0) {
				System.out.println(i);
			} else {
				System.out.print(i + "...");
			}
			Thread.sleep(1000);
		}
	}

	public static void insertNewLine(int n) {
		for (int i = 0; i < n; i++) {
			System.out.println();

		}
	}

	public static void dontUnderstand() throws InterruptedException {
		Utils.writeConsole("No os he entendido muy bien pero no pasa nada... ¡Seguimos adelante!", true, 1);
	}

	public static void wordByWord(String msg) throws InterruptedException {
		String split[] = msg.split(" ");
		for (String word : split) {
			Utils.writeConsole(word, true, 1);
		}
	}
	
	public static boolean arrayContainsWord(String[] list, String word) {
		boolean res=false;
		String wordLowerCase = word.toLowerCase();
		for(int i=0; i < list.length; i++) {
			if(wordLowerCase.contains(list[i])) {
				res=true;
				i=list.length;
			}
		}
		return res;
	}

}
