package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class ReproductorMp3 {

	private boolean stop = false;

	public void play(String audio) throws FileNotFoundException, JavaLayerException, InterruptedException {
		final Player pl = new Player(new FileInputStream(audio));

		Thread thread = new Thread() {
			public void run() {
				try {
					while (true) {
						if (!stop) {
							if (!pl.play(1)) {
								break;
							}
						}
					}
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();

	}

	public void stop() {
		stop = true;
	}
}