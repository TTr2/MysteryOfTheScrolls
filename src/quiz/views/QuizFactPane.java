/**
 * 
 */
package quiz.views;

import game.models.MetaData;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import quiz.engine.QuizGraphicsRenderer;
import quiz.models.QuizCard;

/**
 * Extends Pane to generate the facts page of a mini quiz instance.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class QuizFactPane extends Pane {

	private QuizCard quizCard;
	private Text headerText;
	private Text factText;
	private Image quizBackgroundImage;
	private Image okButtonImage;
	private ImageView quizBackgroundImageView, okButtonImageView;
	
	/**
	 * Generates the quiz fact page for display by QuizGraphicsRenderer.
	 */
	public QuizFactPane(MetaData metaData, QuizGraphicsRenderer quizGraphicsRenderer) {
		
		quizCard = metaData.getQuizCard();
		quizBackgroundImage = new Image(QuizFactPane.class.getResourceAsStream("/game/images/quizBackground.png"));
		quizBackgroundImageView = new ImageView(quizBackgroundImage);
		headerText = new Text();
		headerText.setText(metaData.getHistoryPeriod() + " Fact #" + (metaData.getLevelNumber() + 1));
		headerText.setFont(metaData.getQuizFont("QUIZ_HEADER_FONT"));
		headerText.setFill(Color.web("9E7C3D")); // TODO move to level.
		headerText.setTextAlignment(TextAlignment.CENTER);
		headerText.setLayoutX(400 - (headerText.getLayoutBounds().getWidth() / 2));
		headerText.setLayoutY(160);
		
		factText = new Text();
		factText.setText(quizCard.getHistoryText());
		factText.setTextAlignment(TextAlignment.CENTER);		
		factText.setFont(metaData.getQuizFont("QUIZ_TEXT_FONT"));
		factText.setFill(Color.web("9E7C3D")); // TODO move to level.
		factText.setWrappingWidth(700);
		factText.setLayoutX(400 - (factText.getLayoutBounds().getWidth() / 2));
		factText.setLayoutY(350 - (factText.getLayoutBounds().getHeight() / 2));
		
		okButtonImage = new Image(QuizFactPane.class.getResourceAsStream("/game/images/okButton.png"));
		okButtonImageView = new ImageView(okButtonImage);
		okButtonImageView.setLayoutX(400 - (okButtonImage.getWidth() / 2));
		okButtonImageView.setLayoutY(480);
		okButtonImageView.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				quizGraphicsRenderer.switchActiveQuizPane("Next");
			}
		});				

		getChildren().addAll(quizBackgroundImageView, headerText, factText, okButtonImageView);
	}
}
