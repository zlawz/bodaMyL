package model;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javazoom.jl.decoder.JavaLayerException;
import scenario.ScreenPlay;
import utils.ReproductorMp3;

public class ScoreBoard {

	public static int lives = 3;

	private static final String THREE = "\r\n" + " +-++-++-+\r\n" + " |O||O||O|\r\n" + " +-++-++-+\r\n";
	private static final String TWO = "\r\n" + " +-++-++-+\r\n" + " |X||O||O|\r\n" + " +-++-++-+\r\n";
	private static final String ONE = "\r\n" + " +-++-++-+\r\n" + " |X||X||O|\r\n" + " +-++-++-+\r\n";

	static final String ZERO = "\r\n" + " +-++-++-+\r\n" + " |X||X||X|\r\n" + " +-++-++-+\r\n";

	private static String GAME_OVER = "\r\n"
			+ "               ('-.     _   .-')       ('-.                           (`-.      ('-.  _  .-')   \r\n"
			+ "              ( OO ).-.( '.( OO )_   _(  OO)                        _(OO  )_  _(  OO)( \\( -O )  \r\n"
			+ "  ,----.      / . --. / ,--.   ,--.)(,------.       .-'),-----. ,--(_/   ,. \\(,------.,------.  \r\n"
			+ " '  .-./-')   | \\-.  \\  |   `.'   |  |  .---'      ( OO'  .-.  '\\   \\   /(__/ |  .---'|   /`. ' \r\n"
			+ " |  |_( O- ).-'-'  |  | |         |  |  |          /   |  | |  | \\   \\ /   /  |  |    |  /  | | \r\n"
			+ " |  | .--, \\ \\| |_.'  | |  |'.'|  | (|  '--.       \\_) |  |\\|  |  \\   '   /, (|  '--. |  |_.' | \r\n"
			+ "(|  | '. (_/  |  .-.  | |  |   |  |  |  .--'         \\ |  | |  |   \\     /__) |  .--' |  .  '.' \r\n"
			+ " |  '--'  |   |  | |  | |  |   |  |  |  `---.         `'  '-'  '    \\   /     |  `---.|  |\\  \\  \r\n"
			+ "  `------'    `--' `--' `--'   `--'  `------'           `-----'      `-'      `------'`--' '--' \r\n";

	private static final Map<Integer, String> asciiArt;
	static {
		Map<Integer, String> aMap = new HashMap<>();
		aMap.put(0, ZERO);
		aMap.put(1, ONE);
		aMap.put(2, TWO);
		aMap.put(3, THREE);
		asciiArt = Collections.unmodifiableMap(aMap);
	}

	public static void getScoreBoard() throws FileNotFoundException, JavaLayerException, InterruptedException {

		if (lives > 1)
			System.out.println(asciiArt.get(lives) + "Os quedan " + lives + " vidas.");
		else
			System.out.println(asciiArt.get(lives) + "Os queda " + lives + " vida.");

		if (lives == 0) {
			System.out.println(GAME_OVER);
			ScreenPlay.player2.stop();
			ReproductorMp3 player3 = new ReproductorMp3();
			player3.play("go");
			Thread.sleep(5000);
			System.exit(-1);

		}
		System.out.println();
	}
}
