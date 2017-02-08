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

/**
 * Extends Pane to generate the facts page of a mini quiz instance.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class QuizCompletePane extends Pane {

	private Text headerText;
	private Text labelText;
	private Image quizBackgroundImage;
	private Image okButtonImage;
	private ImageView quizBackgroundImageView, okButtonImageView;
	private Text congratsText;
	private Text symbolText;
	private Text strapText;
	
	/**
	 * Generates the quiz fact page for display by QuizGraphicsRenderer.
	 */
	public QuizCompletePane(MetaData metaData, QuizGraphicsRenderer quizGraphicsRenderer) {
		
		quizBackgroundImage = new Image(QuizCompletePane.class.getResourceAsStream("/game/images/quizBackground.png"));
		quizBackgroundImageView = new ImageView(quizBackgroundImage);
		
		headerText = new Text();
		headerText.setText("Correct Answer!");
		headerText.setFont(metaData.getQuizFont("QUIZ_HEADER_FONT"));
		headerText.setFill(Color.web("9E7C3D")); // TODO move to level.
		headerText.setTextAlignment(TextAlignment.CENTER);
		headerText.setLayoutX(400 - (headerText.getLayoutBounds().getWidth() / 2));
		headerText.setLayoutY(160);

		congratsText = new Text();
		congratsText.setText("Well done, you discovered a " + metaData.getCollectableName() +"!");
		congratsText.setTextAlignment(TextAlignment.CENTER);
		congratsText.setFont(metaData.getQuizFont("QUIZ_TEXT_FONT"));
		congratsText.setFill(Color.web("9E7C3D")); // TODO move to level.
		congratsText.setWrappingWidth(700);
		congratsText.setLayoutX(400 - (congratsText.getLayoutBounds().getWidth() / 2));
		congratsText.setLayoutY(220);		
		
		symbolText = new Text();
		symbolText.setText(metaData.getMetaDataSymbol());
		symbolText.setTextAlignment(TextAlignment.CENTER);
		symbolText.setFont(metaData.getQuizFont("QUIZ_SYMBOL_FONT"));
		symbolText.setFill(Color.web("9E7C3D")); // TODO move to level.
//		symbolText.setWrappingWidth(700);
		symbolText.setLayoutX(400 - (symbolText.getLayoutBounds().getWidth() / 2));
		symbolText.setLayoutY(450);		
		
		
		labelText = new Text();
		labelText.setText(metaData.getMetaDataLabel());
		labelText.setTextAlignment(TextAlignment.CENTER);		
		labelText.setFont(metaData.getQuizFont("QUIZ_TEXT_FONT"));
		labelText.setFill(Color.web("9E7C3D")); // TODO move to level.
//		labelText.setWrappingWidth(700);
		labelText.setLayoutX(400 - (labelText.getLayoutBounds().getWidth() / 2));
		labelText.setLayoutY(500);
		
		strapText = new Text();
		strapText.setText("Collect all the " + metaData.getCollectableName() 
			+ " to uncover the " + metaData.getGameTitle());
		strapText.setTextAlignment(TextAlignment.CENTER);		
		strapText.setFont(metaData.getQuizFont("QUIZ_STRAP_FONT"));
		labelText.setFill(Color.web("9E7C3D")); // TODO move to level.
//		labelText.setWrappingWidth(700);
		strapText.setLayoutX(400 - (strapText.getLayoutBounds().getWidth() / 2));
		strapText.setLayoutY(550);
		
		okButtonImage = new Image(QuizFactPane.class.getResourceAsStream("/game/images/okButton.png"));
		okButtonImageView = new ImageView(okButtonImage);
		okButtonImageView.setLayoutX(664);
		okButtonImageView.setLayoutY(470);
		okButtonImageView.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				quizGraphicsRenderer.switchActiveQuizPane("Next");
			}
		});						

		getChildren().addAll(quizBackgroundImageView, headerText, congratsText, 
				symbolText, labelText, okButtonImageView);
	}
}
