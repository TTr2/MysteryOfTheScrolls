/**
 * 
 */
package game.engine;

import java.util.Map;

import game.library.models.IGameLevelObjectPool;
import game.models.GameState;
import game.models.IMoveableSprite;
import game.models.Level;
import game.models.Player;
import game.models.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Displays the current game state to the game tab, managed by Game Engine.
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class GameGraphicsRenderer 
{

	private GraphicsContext gc;
	private Level level; 
	private Player player;
	private GameState gameState;
	private IGameLevelObjectPool game;
	
	private Image skull;
	private Image life;
	private String GAME_TITLE_TEXT;
	private final double GAME_TITLE_PANEL_WIDTH = 100;
	private final String COLLECTABLES_HEADER_TEXT;	
	private final String SCORE_HEADER_TEXT = "Score";
	private final String LIVES_HEADER_TEXT = "Lives";
	private final double HEADER_TEXT_HEIGHT = 40;
	private final double LEVEL_INFO_HEIGHT = 55;
	private final double SCORE_INFO_CENTRE = 266;
	private final double LIVES_INFO_CENTRE = 370;
	private final double COLLECTABLES_INFO_CENTRE = 610;	
	private final double GAME_TITLE_TEXT_CENTRE = 110;
	private final double LIVES_ICON_CENTRE_X;
	private final double[] META_DATA_INFO_X = new double[]{470, 544, 620, 684, 744};
	private final double META_DATA_CHAR_Y = 82;	
	private final double META_DATA_LABEL_Y = 98;		

	/**
	 * Displays the current game state to the Game tab.
	 */
	public GameGraphicsRenderer(GraphicsContext gc, IGameLevelObjectPool game, Level level) 
	{
		this.gc = gc;
		this.game = game;
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(game.getGameFont("GAME_TITLE_FONT"));
		skull = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/skull.png"));
		life = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/lifeIcon.png"));;
		GAME_TITLE_TEXT = wrapTitleText(game.getGameTitleName());
		LIVES_ICON_CENTRE_X = LIVES_INFO_CENTRE - life.getWidth()/2;
		COLLECTABLES_HEADER_TEXT = "Collected "+ game.getCollectableName();		
	}

	public void displayGameState(GameState gameState, Level level, Player player)
	{
		this.level = level;
		this.player = player;
		this.gameState = gameState;
		displayLevelBackground();
		displayCollectable();
		displayPlayer();
		displayEnemies();
	}
	
	private void displayLevelBackground()
	{
		gc.drawImage(level.getBackgroundImage(), 0, 0);
		gc.drawImage(level.getGameFrameImage(), 0, 0);
		gc.setFont(game.getGameFont("GAME_TITLE_FONT"));
		gc.setFill(Color.web("FFD800")); // TODO move to level.
		gc.fillText(GAME_TITLE_TEXT, GAME_TITLE_TEXT_CENTRE, 50);	
		
		gc.setFont(game.getGameFont("GAME_INFO_FONT"));
		gc.setFill(Color.BLACK); // TODO add to level.
		gc.fillText(SCORE_HEADER_TEXT, SCORE_INFO_CENTRE, HEADER_TEXT_HEIGHT);
		gc.fillText(LIVES_HEADER_TEXT, LIVES_INFO_CENTRE, HEADER_TEXT_HEIGHT);		
		gc.fillText(COLLECTABLES_HEADER_TEXT, COLLECTABLES_INFO_CENTRE, HEADER_TEXT_HEIGHT);
		gc.setFill(Color.web("FF4B42")); // TODO add to level.		
		gc.fillText(String.valueOf(gameState.getScore()), SCORE_INFO_CENTRE, LEVEL_INFO_HEIGHT + 22);

		switch(gameState.getLivesRemaining()){
			case 5:
				gc.drawImage(life, LIVES_ICON_CENTRE_X - 25, LEVEL_INFO_HEIGHT - 6);
				gc.drawImage(life, LIVES_ICON_CENTRE_X, LEVEL_INFO_HEIGHT - 6);
				gc.drawImage(life, LIVES_ICON_CENTRE_X + 25, LEVEL_INFO_HEIGHT - 6);
				gc.drawImage(life, LIVES_ICON_CENTRE_X - 13, LEVEL_INFO_HEIGHT + 18);			
				gc.drawImage(life, LIVES_ICON_CENTRE_X + 13, LEVEL_INFO_HEIGHT + 18);						
				break;
			case 4:
				gc.drawImage(life, LIVES_ICON_CENTRE_X - 25, LEVEL_INFO_HEIGHT - 6);
				gc.drawImage(life, LIVES_ICON_CENTRE_X, LEVEL_INFO_HEIGHT - 6);
				gc.drawImage(life, LIVES_ICON_CENTRE_X + 25, LEVEL_INFO_HEIGHT - 6);
				gc.drawImage(life, LIVES_ICON_CENTRE_X - 13, LEVEL_INFO_HEIGHT + 18);			
				break;
			case 3:
				gc.drawImage(life, LIVES_ICON_CENTRE_X - 25, LEVEL_INFO_HEIGHT);
				gc.drawImage(life, LIVES_ICON_CENTRE_X, LEVEL_INFO_HEIGHT);
				gc.drawImage(life, LIVES_ICON_CENTRE_X + 25, LEVEL_INFO_HEIGHT);
				break;
			case 2:
				gc.drawImage(life, LIVES_ICON_CENTRE_X - 13, LEVEL_INFO_HEIGHT);			
				gc.drawImage(life, LIVES_ICON_CENTRE_X + 13, LEVEL_INFO_HEIGHT);						
				break;
			case 1:
				gc.drawImage(life, LIVES_ICON_CENTRE_X, LEVEL_INFO_HEIGHT);			
				break;
			case 0:
				gc.drawImage(skull, LIVES_ICON_CENTRE_X, LEVEL_INFO_HEIGHT);
				break;
		}
		int counter = 0;
		for (Map.Entry<String, String> metaData : gameState.getCollectedCollectables().entrySet()){
			if (metaData.getKey() != " "){
				gc.setFont(game.getGameFont("META_DATA_FONT"));
				gc.fillText(metaData.getKey(),META_DATA_INFO_X[counter], META_DATA_CHAR_Y);
				gc.setFont(game.getGameFont("META_LABEL_FONT"));
				gc.fillText(metaData.getValue(), 
						META_DATA_INFO_X[counter], META_DATA_LABEL_Y);
			}
			counter++;
		}
	}
	
	private void displayPlayer(){
		gc.drawImage(player.getImage(), player.getX(), player.getY());
	}
	
	private void displayCollectable(){
		Sprite collectableSprite = level.getCollectable().getSprite();
		if (collectableSprite instanceof Sprite){
			gc.drawImage(collectableSprite.getImage(), collectableSprite.getX(),collectableSprite.getY());
		}
	}
	
	private void displayEnemies(){
		for (IMoveableSprite enemy : level.getEnemies() ){
			gc.drawImage(enemy.getImage(), enemy.getX(), enemy.getY());
		}
	}
	
	/**
	 * Checks whether game title is wider than the title panel and attempts to find white space
	 * where it can insert a new line character to wrap text within the panel space.
	 */
	private String wrapTitleText(String gameTitleText) {
		double gameTitleTextWidth = new Text(gameTitleText).getLayoutBounds().getWidth();
		if ((int) gameTitleTextWidth > GAME_TITLE_PANEL_WIDTH){
			int midPointInString = (int) (gameTitleText.length() / 2.5);
			for (int i = midPointInString; i < gameTitleText.length(); i++){
				if (gameTitleText.charAt(i) == ' '){
					return gameTitleText.substring(0, i) + "\n" 
							+ gameTitleText.substring(i, gameTitleText.length());
				}
			}
		}
		return gameTitleText;
	}

	
}
