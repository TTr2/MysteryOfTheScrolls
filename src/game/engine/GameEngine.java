package game.engine;
import game.library.models.GameTitleFactory;
import game.library.models.IGameLevelObjectPool;
import game.library.mysteryofthescrolls.GameTitle_MOTS;
import game.library.mysteryofthescrolls.PlayerFactory_MOTS;
import game.models.GameState;
import game.models.Level;
import game.models.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import main.Assignment1;

/**
 * Stores and manages the progression of an active game session, through delegating responsibility 
 * for the graphics and animation to helper engine classes, while storing game state through 
 * composition of task specific game objects. GameState object persists throughout the session and 
 * is saved to disk upon exit whilst the custom game title object pool stores level objects for duration 
 * of the current game session (level state is lost on exit). 
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class GameEngine {

	private static GameEngine instance;
	private Assignment1 app;
	private GraphicsContext gc;
	private IGameLevelObjectPool game;
	private boolean engineIsRunning;
	private GameState gameState;
	private Level level;
	private Player player;
	private GameGraphicsRenderer gameGraphicsRenderer;
	private GameLoop gameLoop;



	/**
	 * Singleton instance retrieval for a GameEngine object to use for the duration of this game session.
	 * Parameters specify the game title to retrieve from GameTitleFactory and provide access to the 
	 * application window for managing the display between game and quiz.
	 */
	public static GameEngine getInstance(String gameTitle, GraphicsContext gc, Assignment1 app){
		if (instance == null){
			instance = new GameEngine(gameTitle, gc, app);
		}
		return instance;
	}
	
	
	/**
	 * Hidden private constructor, use static getInstance singleton. 
	 */
	private GameEngine(String gameTitle, GraphicsContext gc, Assignment1 app) {

		engineIsRunning = false;
		this.gc = gc;
		this.app = app;
		game = GameTitleFactory.getGameTitle(gameTitle);
		level = game.acquireLevel(0);
		player = level.getPlayer();
		gameState = new GameState();
		gameGraphicsRenderer = new GameGraphicsRenderer(gc, game, level);
		gameLoop = new GameLoop(gameGraphicsRenderer, gameState, level, player);
		
	};
		
	/**
	 * Controls (starts) the running of the internal mechanisms of Game Engine, they being the 
	 * graphical Animation Timer, user input EventHandlers for player actions and collision detection.
	 */
	public void start(){
		engineIsRunning = true;
		gameLoop.start();
	}
	
	/**
	 * Controls (stops) the running of the internal mechanisms of Game Engine, they being the 
	 * graphical Animation Timer, user input EventHandlers for player actions and collision detection. 
	 */
	public void stop(){
		engineIsRunning = false;
		gameLoop.stop();		
	}	

	/**
	 * Handles keyboard events from user, but only called from app if (game) engineIsRunning flag set to 
	 * true.
	 */
	public void processKeyboardInput(KeyEvent event){
		if (engineIsRunning){
			if (event.getEventType() == KeyEvent.KEY_PRESSED){
				switch(event.getCode()){
				case A: /*MOVE LEFT */
					player.setLeftFacingImage();
					player.move();
					break;
				case J:/*JUMP LEFT:*/
					if (!player.playerIsJumping() && !player.playerIsFalling()){
						player.setLeftFacingImage();
						player.setPlayerIsJumping(true);
						player.jumpLeft();
					}
					break;					
				case W:/*JUMP UP*/
					if (!player.playerIsJumping() && !player.playerIsFalling()){
						player.setPlayerIsJumping(true);
						player.jumpUp();
					}
					break;
				case L: /*JUMP RIGHT*/
					if (!player.playerIsJumping() && !player.playerIsFalling()){
						player.setRightFacingImage();
						player.setPlayerIsJumping(true);
						player.jumpRight();
					}
					break;
				case D: /*MOVE RIGHT*/
					player.setRightFacingImage();
					player.move();
					break;
				case SPACE: /*PICK UP COLLECTABLE*/
					if (!level.getCollectable().isCollected()){
						if (gameLoop.detectCollission(player, level.getCollectable().getSprite())){
							stop();
							app.switchToNewQuizTab(level.getCollectable().getMetaData());
							level.getCollectable().collect();
							gameState.addCollectable(
									level.getCollectable().getMetaData().getMetaDataSymbol(), 
									level.getCollectable().getMetaData().getMetaDataLabel());
							level.getCollectable().collect();
							if (level.getLevelNumber() < 5){
								// TODO write the end of game sequence.
							} else {
								
							}
						}
					}
					break;										
				default:
					break;
				}
			}
		}
	}

	public IGameLevelObjectPool getGame() {
		return game;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	public boolean isEngineRunning(){
		return engineIsRunning;
	}
	
	public void loadNewLevel(int newLevelNumber){
		Level currentLevel = level;
		if (newLevelNumber < 5){	
			stop();
			game.releaseLevel(currentLevel);
			level = game.acquireLevel(newLevelNumber);
			if (newLevelNumber < currentLevel.getLevelNumber()){
				player.setLeftFacingImage();
				player.setX(800 - player.getWidth());
			} else if (newLevelNumber > currentLevel.getLevelNumber()){
				player.setX(0);
			}
			gameState.setActiveLevelNumber(newLevelNumber);
			gameGraphicsRenderer = new GameGraphicsRenderer(gc, game, level);
			gameLoop = new GameLoop(gameGraphicsRenderer, gameState, level, player);	
			start();
		}		
	}
	public void loadSavedGameState(GameState gameState) {
		this.gameState = gameState;
		loadNewLevel(gameState.getActiveLevelNumber());
	}
	
	/**
	 * For future expansion, to load new game titles that are configured in game.library package and 
	 * accessible in GameTitleFactory. Marked private till further notice.
	 */
	@SuppressWarnings("unused")
	private void loadNewGameTitle(String gameTitle, GraphicsContext gc){
		instance = new GameEngine(gameTitle, gc, app);
	}


	/**
	 * @return static reference to this object.
	 * @throws NullPointerException as pno parameters are passed, cannot instantiate if instance is null
	 */
	public static GameEngine getInstance() throws NullPointerException{
		return instance;
	}
}
