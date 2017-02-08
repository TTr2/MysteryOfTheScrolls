package quiz.models;

/**
 * Quiz card objects store quiz questions and answers for display by the quiz graphics engine.
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class QuizCard {

	String historyText;
	String question;
	String multiChoiceA, multiChoiceB, multiChoiceC;
	String correctAnswer;

	/**
	 * Constructs a quizCard object composed of strings used by history quiz mini game.
	 */
	public QuizCard(String historyText, String question, String multiChoiceA, String multiChoiceB, 
			String multiChoiceC, String correctAnswer){
		this.historyText = historyText;
		this.question = question;
		this.multiChoiceA = multiChoiceA;
		this.multiChoiceB = multiChoiceB;
		this.multiChoiceC = multiChoiceC;	
		this.correctAnswer = correctAnswer;
	}

	public String getHistoryText() {
		return historyText;
	}
	public String getQuestion() {
		return question;
	}
	public String getMultiChoiceA() {
		return multiChoiceA;
	}
	public String getMultiChoiceB() {
		return multiChoiceB;
	}
	public String getMultiChoiceC() {
		return multiChoiceC;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
}
