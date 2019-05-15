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
	
	private static final String[] SI = {"si","sí","ye","vale","ok", "clarisimo", "clarísimo", "claro"};
	private static final String[] NO = {"no","negativo","nada"};
	private static final String[] BIEN = {"bien","genial","perfect","felices"};
	private static final String[] MAL = {"mal","regular","no muy bien","nada bien"};

	public static ReproductorMp3 player2 = new ReproductorMp3();

	public static void advertisment() throws InterruptedException {

		System.out.println("================================== ADVERTENCIA ==================================");
		System.out.println("Este juego puede ser adictivo.");
		System.out.println("El grupo 100M recomienda un uso responsable.");
		System.out.println("No mezclar con bebidas alcohólicas.");
		System.out.println("ES ABSOLUTAMENTE IMPRESCINDIBLE ACIVAR EL SONIDO DEL ORDENADOR.");
		System.out.println("================================ FIN ADVERTENCIA =================================");
		Thread.sleep(5000);
	}

	public static void rules() throws InterruptedException {
		System.out.println();
		Utils.writeConsole("A continuación se detallan las normas del juego. "
				+ "Prestad atención porque sólo las repetiremos una vez: ", true, 3);

		Utils.writeConsole("1. Debéis responder con mucha precisión lo que se os pregunte.", true, 1);
		Utils.writeConsole("2. Cada fallo será penalizado.", true, 1);
		Utils.writeConsole("3. Disponéis de tres vidas. Cada respuesta incorrecta restará una. "
				+ "Si os quedáis sin vidas, volvéis a empezar.", true, 1);
		Utils.writeConsole("4. El juego es interactivo. Si se os hace una pregunta, responded.", true, 10);
	}

	public static void previousQuestions() throws InterruptedException {
		System.out.println("");
		Utils.writeConsole("Bien. ¿Todo claro?", true, 1);
		String res = Utils.read().toLowerCase();
		if (Utils.arrayContainsWord(SI, res)&&(!res.toLowerCase().startsWith("no")) && (!res.toLowerCase().startsWith("nada"))) {
			Utils.writeConsole("Chicos listos.", true, 1);
		} else if (Utils.arrayContainsWord(NO, res)) {
			Utils.writeConsole("Pues bien empezamos igualmente...", true, 1);
		} else {
			Utils.dontUnderstand();
		}

		System.out.println("");

		Utils.writeConsole("¿Habéis activado el sonido del ordenador?", true, 1);
		res = Utils.read().toLowerCase();
		if (Utils.arrayContainsWord(SI, res)&&(!res.toLowerCase().startsWith("no")) && (!res.toLowerCase().startsWith("nada"))) {
			Utils.writeConsole("¡Genial! Pues...", false, 3);
		} else if (Utils.arrayContainsWord(NO, res)) {
			Utils.writeConsole("¿Y a qué estáis esperando? Os doy 5 segundos para hacerlo:", true, 1);
			Utils.counterDown(5);
			Utils.writeConsole("¡Estupendo! Pues...", false, 3);
		} else {
			Utils.dontUnderstand();
		}
	}

	public static void presentation() throws InterruptedException, FileNotFoundException, JavaLayerException {

		Utils.writeConsole(" ¡¡¡Que comience el espectáculo!!!", true, 1);

		ReproductorMp3 player1 = new ReproductorMp3();
		player1.play("dll1");

		Thread.sleep(3000);
		Utils.insertNewLine(3);

		Utils.writeConsole("¡Hoy tenemos aquí a Mario y Laura!", true, 3);
		Utils.writeConsole(
				"Esta pareja se ha casado recientemente y están aquí " + "para conseguir el espigo del grupo 100M.",
				true, 3);
		Utils.writeConsole("¿Qué tal estáis, Mario y Laura?", true, 0);

		String res = Utils.read();
		String msg = "";
		if (Utils.arrayContainsWord(BIEN, res)&&(!res.toLowerCase().startsWith("no")) && (!res.toLowerCase().startsWith("nada"))) {
			msg = "¿" + Utils.firstToUpper(res) + "? Me alegro. Yo también, gracias.";
		} else if (Utils.arrayContainsWord(MAL, res)) {
			msg = "Vaya, siento oír eso, pero... vamos a seguir el juego igual.";
		} else if (res.contains("nervios")) {
			msg = "¡No estéis nerviosos! Estamos aquí para divertirnos.";
		} else {
			Utils.dontUnderstand();
		}
		Utils.writeConsole(msg, true, 1.5);

		Utils.writeConsole(
				"Como ellos ya saben, su espigo ha sido depositado en una caja fuerte con un candado de combinación. \n"
						+ "El objetivo de este concurso es que consigan esa combinación. "
						+ "Para ello deberán contestar correctamente una serie de preguntas.",
				true, 10);

		Utils.writeConsole(
				"Si responden mal a una de ellas, perderán una vida. Y si las pierden todas... tendrán que volver a empezar.",
				true, 5);

		Utils.writeConsole("¿Estáis preparados, Mario y Laura?", true, 0);

		res = Utils.read();
		if (Utils.arrayContainsWord(SI, res) || res.toLowerCase().contains("preparad")) {
			Utils.writeConsole("¡Eso es lo que quería oír. Allá vamos!", true, 5);
		} else if (Utils.arrayContainsWord(NO, res)) {
			Utils.writeConsole("Mala suerte porque...", true, 5);
		} else {
			Utils.dontUnderstand();
		}

		Utils.insertNewLine(1);
		Utils.wordByWord("El juego comienza... ¡¡YA!!");
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
			Utils.writeConsole("¡Habéis conseguido finalizar el juego!", true, 1);
			Utils.writeConsole("Aunque habéis tenido dos fallos.", true, 1);
			Utils.writeConsole("Os vamos a dar la combinación pero... ¡DESORDENADA!", true, 1);
			System.out.println(MsgAndComments.COMBINATION_DISORDER);

		} else if (ScoreBoard.lives == 2) {
			Utils.writeConsole("¡Habéis conseguido finalizar el juego!", true, 1);
			Utils.writeConsole("Aunque habéis tenido un fallo.", true, 1);
			Utils.writeConsole("Os vamos a dar la combinación pero... ¡Os falta el último número!", true, 1);
			System.out.println(MsgAndComments.COMBINATION_MISSING_1);
		} else {
			Utils.writeConsole("¡Habéis conseguido acertar todas las preguntas!", true, 1);
			Utils.writeConsole("Sois dignos de pertenecer al grupo 100 Montaditos.", true, 1);
			Utils.writeConsole("¡Aquí tenéis vuestra combinación!", true, 1);
			System.out.println(MsgAndComments.RIGHT_COMBINATION);
		}
		Utils.writeConsole("¡Enhorabuena por vuestro premio! ¡Vivan los novios!", true, 10);
		System.exit(-1);
	}
}
