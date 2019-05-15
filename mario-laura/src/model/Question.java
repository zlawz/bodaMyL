package model;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
	private String question;
	private List<String> answers;
	private int type;

	public Question(int type, String question, String[] answers) {
		this.type = type;
		this.question = question;
		this.answers = Arrays.asList(answers);

	}

	public boolean checkAnswer(String answerUser) {
		if (type == 1) {
			for (String answer : answers) {
				if (answerUser.toLowerCase().contains(answer.toLowerCase())) {
					return true;
				}
			}

		} else if (type == 3) {
			for (String answer : answers) {
				if (!answerUser.toLowerCase().contains(answer.toLowerCase())) {
					return false;
				}
			}
			return true;
		} else {
			return answerUser.toLowerCase().equals(answers.get(0).toLowerCase());
		}
		return false;
	}

	public void makeQuestion() {
		System.out.println(question);
	}
}
