package scenario;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import javazoom.jl.decoder.JavaLayerException;
import model.MsgAndComments;
import model.Question;
import model.ScoreBoard;
import utils.ReproductorMp3;
import utils.Utils;

public class ScreenPlay {
	
	private static final String[] SI = {"si","s�","ye","vale","ok", "clarisimo", "clar�simo", "claro"};
	private static final String[] NO = {"no","negativo","nada"};
	private static final String[] BIEN = {"bien","genial","perfect","felices"};
	private static final String[] MAL = {"mal","regular","no muy bien","nada bien"};

	public static ReproductorMp3 player2 = new ReproductorMp3();

	public static void advertisment() throws InterruptedException {

		System.out.println("================================== ADVERTENCIA ==================================");
		System.out.println("Este juego puede ser adictivo.");
		System.out.println("El grupo 100M recomienda un uso responsable.");
		System.out.println("No mezclar con bebidas alcoh�licas.");
		System.out.println("ES ABSOLUTAMENTE IMPRESCINDIBLE ACIVAR EL SONIDO DEL ORDENADOR.");
		System.out.println("================================ FIN ADVERTENCIA =================================");
		Thread.sleep(5000);
	}

	public static void rules() throws InterruptedException {
		System.out.println();
		Utils.writeConsole("A continuaci�n se detallan las normas del juego. "
				+ "Prestad atenci�n porque s�lo las repetiremos una vez: ", true, 3);

		Utils.writeConsole("1. Deb�is responder con mucha precisi�n lo que se os pregunte.", true, 1);
		Utils.writeConsole("2. Cada fallo ser� penalizado.", true, 1);
		Utils.writeConsole("3. Dispon�is de tres vidas. Cada respuesta incorrecta restar� una. "
				+ "Si os qued�is sin vidas, volv�is a empezar.", true, 1);
		Utils.writeConsole("4. El juego es interactivo. Si se os hace una pregunta, responded.", true, 10);
	}

	public static void previousQuestions() throws InterruptedException {
		System.out.println("");
		Utils.writeConsole("Bien. �Todo claro?", true, 1);
		String res = Utils.read().toLowerCase();
		if (Utils.arrayContainsWord(SI, res)&&(!res.toLowerCase().startsWith("no")) && (!res.toLowerCase().startsWith("nada"))) {
			Utils.writeConsole("Chicos listos.", true, 1);
		} else if (Utils.arrayContainsWord(NO, res)) {
			Utils.writeConsole("Pues bien empezamos igualmente...", true, 1);
		} else {
			Utils.dontUnderstand();
		}

		System.out.println("");

		Utils.writeConsole("�Hab�is activado el sonido del ordenador?", true, 1);
		res = Utils.read().toLowerCase();
		if (Utils.arrayContainsWord(SI, res)&&(!res.toLowerCase().startsWith("no")) && (!res.toLowerCase().startsWith("nada"))) {
			Utils.writeConsole("�Genial! Pues...", false, 3);
		} else if (Utils.arrayContainsWord(NO, res)) {
			Utils.writeConsole("�Y a qu� est�is esperando? Os doy 5 segundos para hacerlo:", true, 1);
			Utils.counterDown(5);
			Utils.writeConsole("�Estupendo! Pues...", false, 3);
		} else {
			Utils.dontUnderstand();
		}
	}

	public static void presentation() throws InterruptedException, FileNotFoundException, JavaLayerException {

		Utils.writeConsole(" ���Que comience el espect�culo!!!", true, 1);

		ReproductorMp3 player1 = new ReproductorMp3();
		player1.play("dll1");

		Thread.sleep(3000);
		Utils.insertNewLine(3);

		Utils.writeConsole("�Hoy tenemos aqu� a Mario y Laura!", true, 3);
		Utils.writeConsole(
				"Esta pareja se ha casado recientemente y est�n aqu� " + "para conseguir el espigo del grupo 100M.",
				true, 3);
		Utils.writeConsole("�Qu� tal est�is, Mario y Laura?", true, 0);

		String res = Utils.read();
		String msg = "";
		if (Utils.arrayContainsWord(BIEN, res)&&(!res.toLowerCase().startsWith("no")) && (!res.toLowerCase().startsWith("nada"))) {
			msg = "�" + Utils.firstToUpper(res) + "? Me alegro. Yo tambi�n, gracias.";
		} else if (Utils.arrayContainsWord(MAL, res)) {
			msg = "Vaya, siento o�r eso, pero... vamos a seguir el juego igual.";
		} else if (res.contains("nervios")) {
			msg = "�No est�is nerviosos! Estamos aqu� para divertirnos.";
		} else {
			Utils.dontUnderstand();
		}
		Utils.writeConsole(msg, true, 1.5);

		Utils.writeConsole(
				"Como ellos ya saben, su espigo ha sido depositado en una caja fuerte con un candado de combinaci�n. \n"
						+ "El objetivo de este concurso es que consigan esa combinaci�n. "
						+ "Para ello deber�n contestar correctamente una serie de preguntas.",
				true, 10);

		Utils.writeConsole(
				"Si responden mal a una de ellas, perder�n una vida. Y si las pierden todas... tendr�n que volver a empezar.",
				true, 5);

		Utils.writeConsole("�Est�is preparados, Mario y Laura?", true, 0);

		res = Utils.read();
		if (Utils.arrayContainsWord(SI, res) || res.toLowerCase().contains("preparad")) {
			Utils.writeConsole("�Eso es lo que quer�a o�r. All� vamos!", true, 5);
		} else if (Utils.arrayContainsWord(NO, res)) {
			Utils.writeConsole("Mala suerte porque...", true, 5);
		} else {
			Utils.dontUnderstand();
		}

		Utils.insertNewLine(1);
		Utils.wordByWord("El juego comienza... ��YA!!");
		Utils.insertNewLine(2);
		player1.stop();

		player2 = new ReproductorMp3();
		player2.play("dll2");

	}

	public static void quiz() throws InterruptedException, FileNotFoundException, JavaLayerException,
			InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException {
		Set<Question> questions = Questions.getQuestions();
		for (Question q : questions) {
			q.makeQuestion();
			String resp = Utils.read();
			if (q.checkAnswer(resp)) {
				System.out.println(MsgAndComments.getRandomMsg(1));
			} else {
				System.out.println(MsgAndComments.getRandomMsg(2));
				ScoreBoard.lives--;
			}
			Thread.sleep(1000);
			ScoreBoard.getScoreBoard();
			Thread.sleep(3000);
		}
	}

	public static void combination() throws InterruptedException, FileNotFoundException, JavaLayerException {
		player2.stop();
		ReproductorMp3 player3 = new ReproductorMp3();
		player3.play("ws");
		Utils.writeConsole(MsgAndComments.ENHORABUENA, true, 3);
		if (ScoreBoard.lives == 1) {
			Utils.writeConsole("�Hab�is conseguido finalizar el juego!", true, 1);
			Utils.writeConsole("Aunque hab�is tenido dos fallos.", true, 1);
			Utils.writeConsole("Os vamos a dar la combinaci�n pero... �DESORDENADA!", true, 1);
			System.out.println(MsgAndComments.COMBINATION_DISORDER);

		} else if (ScoreBoard.lives == 2) {
			Utils.writeConsole("�Hab�is conseguido finalizar el juego!", true, 1);
			Utils.writeConsole("Aunque hab�is tenido un fallo.", true, 1);
			Utils.writeConsole("Os vamos a dar la combinaci�n pero... �Os falta el �ltimo n�mero!", true, 1);
			System.out.println(MsgAndComments.COMBINATION_MISSING_1);
		} else {
			Utils.writeConsole("�Hab�is conseguido acertar todas las preguntas!", true, 1);
			Utils.writeConsole("Sois dignos de pertenecer al grupo 100 Montaditos.", true, 1);
			Utils.writeConsole("�Aqu� ten�is vuestra combinaci�n!", true, 1);
			System.out.println(MsgAndComments.RIGHT_COMBINATION);
		}
		Utils.writeConsole("�Enhorabuena por vuestro premio! �Vivan los novios!", true, 10);
		System.exit(-1);
	}
}
