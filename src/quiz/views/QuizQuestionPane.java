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
 * Generates a quiz question page for display by the QuizGraphicsRenderer.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class QuizQuestionPane extends Pane {

	QuizCard quizCard;
	private Image blankButtonImage, crossButtonImage, quizBackgroundImage;	
	private ImageView quizBackgroundImageView;
	private ImageView answerImageButtonA, answerImageButtonB, answerImageButtonC;
	private Text headerText;
	private Text questionText;
	private Text answerAText;
	private Text answerBText;
	private Text answerCText;
	
	public QuizQuestionPane(MetaData metaData, QuizGraphicsRenderer quizGraphicsRenderer){
		quizCard = metaData.getQuizCard();
		quizBackgroundImage = new Image(QuizFactPane.class.getResourceAsStream("/game/images/quizBackground.png"));
		quizBackgroundImageView = new ImageView(quizBackgroundImage);
		
		headerText = new Text();
		headerText.setText("Level " + (metaData.getLevelNumber() + 1) + " Quiz");
		headerText.setTextAlignment(TextAlignment.CENTER);		
		headerText.setFont(metaData.getQuizFont("QUIZ_HEADER_FONT"));
		headerText.setFill(Color.web("9E7C3D")); // TODO move to level.
		headerText.setLayoutX(400 - (headerText.getLayoutBounds().getWidth() / 2));
		headerText.setLayoutY(140);
		
		questionText = new Text();
		questionText.setText(quizCard.getQuestion());
		questionText.setWrappingWidth(700);
		questionText.setTextAlignment(TextAlignment.CENTER);		
		questionText.setFont(metaData.getQuizFont("QUIZ_TEXT_FONT"));
		questionText.setFill(Color.web("9E7C3D")); // TODO move to level.
		questionText.setLayoutX(50);
		questionText.setLayoutY(210);
		
		answerAText = new Text();
		answerAText.setText(quizCard.getMultiChoiceA());
		answerAText.setWrappingWidth(570);
		answerAText.setTextAlignment(TextAlignment.LEFT);		
		answerAText.setFont(metaData.getQuizFont("QUIZ_TEXT_FONT"));
		answerAText.setFill(Color.web("9E7C3D")); // TODO move to level.
		answerAText.setLayoutX(260);
		answerAText.setLayoutY(340);
		
		answerBText = new Text();
		answerBText.setText(quizCard.getMultiChoiceB());
		answerBText.setWrappingWidth(570);
		answerBText.setTextAlignment(TextAlignment.LEFT);		
		answerBText.setFont(metaData.getQuizFont("QUIZ_TEXT_FONT"));
		answerBText.setFill(Color.web("9E7C3D")); // TODO move to level.
		answerBText.setLayoutX(260);
		answerBText.setLayoutY(430);
		
		answerCText = new Text();
		answerCText.setText(quizCard.getMultiChoiceC());
		answerCText.setWrappingWidth(570);
		answerCText.setTextAlignment(TextAlignment.LEFT);		
		answerCText.setFont(metaData.getQuizFont("QUIZ_TEXT_FONT"));
		answerCText.setFill(Color.web("9E7C3D")); // TODO move to level.
		answerCText.setLayoutX(260);
		answerCText.setLayoutY(520);		
		
		blankButtonImage = new Image(getClass().getResourceAsStream("/game/images/blankButton.png"));
		crossButtonImage = new Image(getClass().getResourceAsStream("/game/images/crossButton.png"));

		answerImageButtonA = new ImageView(blankButtonImage);
		answerImageButtonA.setLayoutX(180);
		answerImageButtonA.setLayoutY(290);
		answerImageButtonA.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				if (quizCard.getCorrectAnswer().equals("A")){
					quizGraphicsRenderer.switchActiveQuizPane("Next");
				} else {
					quizGraphicsRenderer.switchActiveQuizPane("Back");
					answerImageButtonA.setImage(crossButtonImage);
					answerImageButtonA.setDisable(true);
				}
			}
		});
		answerImageButtonB = new ImageView(blankButtonImage);
		answerImageButtonB.setLayoutX(180);
		answerImageButtonB.setLayoutY(380);
		answerImageButtonB.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				if (quizCard.getCorrectAnswer().equals("B")){
					quizGraphicsRenderer.switchActiveQuizPane("Next");
				} else {
					quizGraphicsRenderer.switchActiveQuizPane("Back");
					answerImageButtonB.setImage(crossButtonImage);
					answerImageButtonB.setDisable(true);
				}
			}
		});		
		answerImageButtonC = new ImageView(blankButtonImage);
		answerImageButtonC.setLayoutX(180);
		answerImageButtonC.setLayoutY(470);
		answerImageButtonC.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				if (quizCard.getCorrectAnswer().equals("C")){
					quizGraphicsRenderer.switchActiveQuizPane("Next");
				} else {
					quizGraphicsRenderer.switchActiveQuizPane("Back");
					answerImageButtonC.setImage(crossButtonImage);
					answerImageButtonC.setDisable(true);
				}
			}
		});		
		
		getChildren().addAll(quizBackgroundImageView, headerText, questionText, 
				answerImageButtonA, answerImageButtonB, answerImageButtonC, answerAText, answerBText, answerCText);
	}
	
	
}
