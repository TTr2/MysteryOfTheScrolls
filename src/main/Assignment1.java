package main;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import game.engine.GameEngine;
import game.models.GameState;
import game.models.MetaData;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import quiz.engine.QuizEngine;

/**
 * Point of entry class for educational 2D platform game system, contains methods relevant
 * to controlling what is displayed within the application and interacting with File I/O for 
 * saved game files. 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */


public class Assignment1 extends Application {

	public static Logger logger = Logger.getLogger( Assignment1.class.getName() );

	public static void main(String[] args) {

	    SimpleDateFormat logDateFormat = new SimpleDateFormat("ddMMYYYY");
	    String todaysDate = logDateFormat.format(new Date());		
        FileHandler logFileHandler;
		try	{
            logFileHandler = new FileHandler("logs/" + todaysDate + "_log.txt");
            logFileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(logFileHandler);
	        logger.info("Initialised Log");		            
        } catch (SecurityException | IOException e){
			try {
				logFileHandler = new FileHandler(todaysDate + "_log.txt");
                logFileHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(logFileHandler);
    	        logger.info("Initialised Log");		                    		
			} catch (SecurityException | IOException e1) {
	        	e1.printStackTrace(); 
	        	System.out.println("The log file was not loaded " + e );
			}
        }
		launch(args);
	}

	private Scene scene;
	private TabPane root;
	private Tab gameTab, displayTab;
	private Canvas gameCanvas;
	private Pane displayPane;
	private GameEngine gameEngine;
	private Pane splashPane;
	private String jarDirectory;
	
	public void start(Stage stage) throws Exception 
	{
        jarDirectory = findJarLocation();
        
		stage.setTitle("Software Architectures – Tim Tyler");
	  	stage.setMinHeight(684);
	  	stage.setOnCloseRequest(event -> {
	  		logger.info("Exiting app gracefully.");
	  	    saveGameState(gameEngine.getGameState());
	  	});
        logger.info("Initialised application window.");	

	  	root = new TabPane();
	    scene = new Scene(root, 800, 600);
	  	stage.setScene(scene);

	  	gameTab = new Tab();
	  	gameCanvas = new Canvas(800,600);
	  	gameTab.setText("Game");
	  	gameTab.setClosable(false);
	  	gameTab.setContent(gameCanvas);
        logger.info("Initialised game tab.");	
	  	
	  	gameEngine = GameEngine.getInstance("Load Default Game", gameCanvas.getGraphicsContext2D(), this);
        logger.info("Initialised game engine with default game title.");	
        
	  	displayPane = new StackPane();
	  	splashPane = new SplashScreen(gameEngine.getGame(), this, checkForSavedGameState(), displayPane);
	  	displayPane.getChildren().add(splashPane);

	  	displayTab = new Tab();
	  	displayTab.setText("Select Start");
	  	displayTab.setClosable(false);
	  	displayTab.setContent(displayPane);
        logger.info("Initialised splash screen tab.");	
        
	  	gameTab.setOnSelectionChanged(new EventHandler<Event>(){
			@Override
			public void handle(Event ev) {
				if (gameTab.isSelected()){
					gameTab.setDisable(false);
					displayTab.setDisable(true);
					gameEngine.start();				
				}
			}
	  	});
	  	displayTab.setOnSelectionChanged(new EventHandler<Event>(){
			@Override
			public void handle(Event ev) {
				if (displayTab.isSelected()){
					gameEngine.stop();
					displayTab.setDisable(false);
					gameTab.setDisable(true);
				}
			}
	  	});
		root.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				if (gameEngine.isEngineRunning()){
					gameEngine.processKeyboardInput(event);
				}
			}
		});
        logger.info("Initialised tab selection and key press event handlers.");	
        
	  	root.getTabs().addAll(displayTab, gameTab);
	  	stage.show();
	}
	
	/**
	 * Called by gameEngine to instantiate a quiz mini game after player has selected a collectable 
	 * item.
	 * @param metaData Contains all the required information to display a new quiz mini game
	 * in display tab.
	 */
	public void switchToNewQuizTab(MetaData metaData){
		displayTab.setText("Quiz");
		root.getSelectionModel().select(0); // triggers setOnSelectionChangedEvent		
	  	new QuizEngine(metaData, displayPane, this);
        logger.info("Starting new quiz mini game, level " + metaData.getLevelNumber() + ".");	
	}
	
	/**
	 * Called by the splashPane or a new QuizEngine instance upon completion of a quiz mini 
	 * game to reset displayTab state and return tab selection back to the game.
	 */
	public void switchToGameTab(){
		displayPane.getChildren().remove(0);
	  	root.getSelectionModel().select(1); // triggers setOnSelectionChangedEvent
        logger.info("Returning to game tab.");	
	}
	
	/**
	 * Checks folder where JAR file is running for a saved game file called "save.swa".
	 * If found returns true to the splash screen to decide whether or not to offer the 
	 * option to "continue".
	 */
	private boolean checkForSavedGameState(){
		boolean savedGameFound = false;
		if (jarDirectory != null){
			File savedGame = new File(jarDirectory + "/save.swa");
			if (savedGame.exists()){
				savedGameFound = true;
			}
		} 
		return savedGameFound;
	}

	/**
	 * Called upon exit by default, saves or overwrites game state to a "saves.swa" file in 
	 * the directory that JAR file was run. 
	 * @param gameState The state of the current game session.
	 */
	private void saveGameState(GameState gameState){
		gameState.serialize(jarDirectory);
	}
	
	/**
	 * Locates the directory that JAR file is being run from for loading and saving of game saves.
	 * @return The path to the director where JAR file is being run.
	 */
	private static String findJarLocation(){
		CodeSource codeSource = Assignment1.class.getProtectionDomain().getCodeSource();
		File jarFile = null;
		String jarDirectory = null;
		try {
			jarFile = new File(codeSource.getLocation().toURI().getPath());
			jarDirectory = jarFile.getParentFile().getPath();
	        logger.info("JAR directory is " + jarDirectory + ".");				
		} catch (URISyntaxException e) {
	        logger.warning("Incorrect URL, failed to find JAR directory.");	
		}
		return jarDirectory;
	}
	
	/**
	 * Package private method for loading a saved game from file and calls game engine to update 
	 * it's game state. If saved game is not loaded successfully then game engine will start a new game.
	 */
	void loadSavedGameState(){
		GameState savedGameState= null;
		File savedGame = new File(jarDirectory + "/save.swa");
		if (savedGame.exists()){
			ObjectInputStream ois = null;
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(jarDirectory + "/save.swa");
				ois = new ObjectInputStream(fis);
				savedGameState = (GameState) ois.readObject();
				gameEngine.loadSavedGameState(savedGameState);
				logger.info("Score = "+savedGameState.getScore());
				logger.info("Lives = "+savedGameState.getLivesRemaining());
				logger.info("Level Number = "+savedGameState.getActiveLevelNumber());
				logger.info("Colelctables = "+savedGameState.getCollectedCollectables().toString());				
				logger.info("Loaded saved game.");
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.toString());			
			} catch (ClassNotFoundException e) {
				logger.log(Level.SEVERE, e.toString());			
			} finally {
				if (fis != null)
					try {
						fis.close();
					} catch (IOException e) {
						logger.log(Level.SEVERE, e.toString());			
					}
				if (ois != null){
					try {
						ois.close();
					} catch (IOException e) {
						logger.log(Level.SEVERE, e.toString());			
					}
				}
			}
		}			
	}

}