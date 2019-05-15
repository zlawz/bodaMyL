package main;

import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import scenario.ScreenPlay;

public class ContestMain {

	public static void main(String[] args) throws FileNotFoundException, JavaLayerException, InterruptedException {
		try {
			ScreenPlay.advertisment();
			ScreenPlay.rules();
			ScreenPlay.previousQuestions();
			ScreenPlay.presentation();
			ScreenPlay.quiz();
			ScreenPlay.combination();
		} catch (Exception e) {
			System.out.println("¡Ups! Algo ha ido mal... por favor inténtalo de nuevo.");
			e.printStackTrace();
			System.exit(-1);
		}

	}
}
