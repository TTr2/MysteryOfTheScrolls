/**
 * 
 */
package main;

import game.library.models.IGameLevelObjectPool;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Extends Pane to generate the facts page of a mini quiz instance.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class SplashScreen extends Pane {

	private Text headerText;
	private Image splashBackgroundImage;
	private ImageView splashBackgroundImageView;
	private Text newGameText;
	private Text continueText;
	private Pane displayPane;
	
	/**
	 * Generates the Welcome Screen on start up, select new game or continue if saved game found.
	 */
	public SplashScreen(IGameLevelObjectPool game, Assignment1 app, boolean savedGameFound, Pane displayPane) {
		
		this.displayPane = displayPane;
		splashBackgroundImage = new Image(getClass().getResourceAsStream("/game/images/splash.png"));
		splashBackgroundImageView = new ImageView(splashBackgroundImage);
		
		headerText = new Text();
		headerText.setText(game.getGameTitleName());
		headerText.setFont(game.getGameFont("GAME_SPLASH_FONT"));
		headerText.setFill(Color.web("FFD800")); // TODO move to level.
		headerText.setTextAlignment(TextAlignment.CENTER);
		headerText.setLayoutX(400 - (headerText.getLayoutBounds().getWidth() / 2));
		headerText.setLayoutY(150);

		newGameText = new Text();
		newGameText.setText("New Game");
		newGameText.setTextAlignment(TextAlignment.CENTER);
		newGameText.setFont(game.getGameFont("GAME_SPLASH_FONT"));
		newGameText.setFill(Color.web("FFFFFF")); // TODO move to level.
		newGameText.setLayoutX(408 - (newGameText.getLayoutBounds().getWidth() / 2));
		newGameText.setLayoutY(345);
		newGameText.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				displayInstructionsAndStartGame(app);
			}
		});
		
		continueText = new Text();
		continueText.setText("Continue");
		continueText.setTextAlignment(TextAlignment.CENTER);
		continueText.setFont(game.getGameFont("GAME_SPLASH_FONT"));
		continueText.setLayoutX(400 - (continueText.getLayoutBounds().getWidth() / 2));
		continueText.setLayoutY(464);		
		if (savedGameFound){
			continueText.setFill(Color.web("FFFFFF")); // TODO move to level.
			continueText.setOnMouseClicked(event -> {
				app.loadSavedGameState();
				displayInstructionsAndStartGame(app);
			});			
		} else {
			continueText.setFill(Color.web("9E7C3D")); // TODO move to level.			
		}
		
		getChildren().addAll(splashBackgroundImageView, headerText, continueText, newGameText);
	}
	
	
	private void displayInstructionsAndStartGame(Assignment1 app){
		
		Image instructionsImage = new Image(getClass().getResourceAsStream("/game/images/instructionsText.bmp"));
		ImageView instructionsImageView = new ImageView(instructionsImage);
		instructionsImageView.setOnMouseClicked(event -> {
			app.switchToGameTab();
			displayPane.getChildren().remove(0);			
		});
		displayPane.getChildren().add(instructionsImageView);
		displayPane.getChildren().remove(0);			
		
	}
	
}
