package quiz.engine;
import game.models.MetaData;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import main.Assignment1;

/**
 * Instantiates a lightweight and temporary quiz engine session for each level, progress is managed by 
 * user mouse interaction that triggers the quiz graphics engine to progress through a series of display 
 * panes which themselves call back to the application. Quiz loop simply checks whether the quiz mini 
 * game has completed, after which it calls application to dereference this quiz engine and return to game.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class QuizEngine {

	private QuizGraphicsRenderer quizGraphicsRenderer;
	private AnimationTimer quizLoop;
	
	/**
	 * Constructor for a throwaway QuizEngine object to manage a single quiz mini game (one per level).
	 * Parameters provide the game title specific meta data for quiz session and provide access to the 
	 * application window for managing the display between game and quiz.
	 */
	public QuizEngine(MetaData metaData, Pane displayStackPane, Assignment1 app) {

		quizGraphicsRenderer = new QuizGraphicsRenderer(metaData, displayStackPane);
		quizLoop = new AnimationTimer(){
			@Override
			public void handle(long arg0) {
				if (quizGraphicsRenderer.isQuizCompleted()){
					stop();
					app.switchToGameTab();
				}
			}
		};
		quizLoop.start();
	};
}
