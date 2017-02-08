package game.models;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */

/**
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class PlayerProfile implements Serializable{

	private static final long serialVersionUID = -5226264743163185911L;
	private String playerName;
	private Map<String, Integer> highScores;
	private Map<String, GameState> savedGameStates;
	
	// TODO Need to store saved games as <gametitle String, GameState hashmap> pair.
	// FIDDLY?
	
	// WHAT IS THE PURPOSE USE OF THIS?
	// ON STARTUP APP SHOULD LOAD PROFILES FROM DISK AND FILL DETAILS, DOES THIS GET DONE
	// IN AN ALTERNATE CONSTRUCTOR?
	
	// WHEN CREATING NEW PROFILE, DOES THE GAME GET REFERENCED?
	// WHEN STYARTING A GAME SGHOULD IT GET LOADED?
	
	
	/**
	 * 
	 */
	public PlayerProfile(String playerName, String gameTitle) {
		// TODO separate game titles 
		
		// TODO 
		if (highScores == null){
			highScores = new HashMap<String, Integer>();
			highScores.put(gameTitle, 0);
		}
		
		if (savedGameStates == null){
			savedGameStates = new HashMap<String, GameState>();
			savedGameStates.put(gameTitle, new GameState());
		} else {
			// GET FROM FILE.
// TODO			savedGameStates = GET FROM FILE;
		}
	}
	
		

	public String getPlayerName(){
		return playerName;
	}
	
	public long getHighScore(String gameTitle){
		return highScores.get(gameTitle);
	}
	
}
