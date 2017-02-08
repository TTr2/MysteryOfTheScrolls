/**
 * 
 */
package quiz.engine;

import game.models.MetaData;
import javafx.scene.layout.Pane;
import quiz.views.QuizCompletePane;
import quiz.views.QuizFactPane;
import quiz.views.QuizQuestionPane;

/**
 * Drives the display of the quiz mini game, alternating the display panes according to user 
 * input and checking quiz answers.
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class QuizGraphicsRenderer {
	
	private MetaData metaData;
	private Pane displayStackPane;
	private Pane[] quizPanes;
	private int activePane;
	private boolean quizIsCompleted;

	/**
	 * Coordinates the display of the quiz mini game without need for an animation timer.
	 */
	public QuizGraphicsRenderer(MetaData metaData, Pane displayStackPane)
	{
		this.metaData = metaData;
		this.displayStackPane = displayStackPane;		
		this.quizPanes = initialiseQuizPanes();
		activePane = 0;
		quizIsCompleted = false;
		this.displayStackPane.getChildren().add(quizPanes[activePane]);
	}
	
	private Pane[] initialiseQuizPanes(){
		QuizFactPane quizFactPane = new QuizFactPane(metaData, this);
		QuizQuestionPane quizQuestionPane = new QuizQuestionPane(metaData, this);
		QuizCompletePane quizCompletePane = new QuizCompletePane(metaData, this);		
		Pane[] newQuizPanes = new Pane[3];
		newQuizPanes[0] = quizFactPane;
		newQuizPanes[1] = quizQuestionPane;
		newQuizPanes[2] = quizCompletePane;	
		return newQuizPanes;
	}
	
	public void switchActiveQuizPane(String flowDirection){
		if (flowDirection.equals("Next")){
			if (activePane == 0){
				displayStackPane.getChildren().remove(0);		
				displayStackPane.getChildren().add(quizPanes[1]);
				activePane++;
			} else if (activePane == 1){
				displayStackPane.getChildren().remove(0);		
				displayStackPane.getChildren().add(quizPanes[2]);
				activePane++;				
			} else if (activePane == 2){
				quizIsCompleted = true;
				// Do not remove quiz complete pane from displayPane as switchToGamePane() does that.
			}
		}
		else if (flowDirection.equals("Back") && activePane == 1){
			displayStackPane.getChildren().remove(0);			
			displayStackPane.getChildren().add(quizPanes[0]);
			activePane--;
		}
	}

	public boolean isQuizCompleted() {
		return quizIsCompleted;
	}

	public void setQuizCompleted(boolean quizCompleted) {
		this.quizIsCompleted = quizCompleted;
	}
	
}
