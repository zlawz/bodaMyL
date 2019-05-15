package scenario;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import encoder.EncryptDecryptStringWithDES;
import model.Question;
import utils.FileUtils;

public class Questions {

	private static final String QUESTIONS_FILE = "dll3encrypted";

	public static Set<Question> getQuestions() throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		String questionsStr = FileUtils.readLineByLineJava8(QUESTIONS_FILE).replace("\n", "");

		questionsStr = EncryptDecryptStringWithDES.decrypt(questionsStr);

		List<Question> questionList = new ArrayList<>();
		String[] lines = questionsStr.split("\n");
		for (String question : lines) {
			String aux[] = question.split(";");
			String[] answers = new String[aux.length - 2];
			int j = 0;
			for (int i = 2; i < aux.length; i++) {
				answers[j] = aux[i];
				j++;
			}
			questionList.add(new Question(Integer.parseInt(aux[0]), aux[1], answers));
		}

		Set<Question> questionSet = new HashSet<>();
		while (questionList.size() > 0) {
			if (questionList.size() == 1) {
				questionSet.add(questionList.get(0));
				questionList.remove(0);
			} else {
				int random = ThreadLocalRandom.current().nextInt(0, questionList.size() - 1);
				questionSet.add(questionList.get(random));
				questionList.remove(random);
			}
		}
		return questionSet;
	}
}
