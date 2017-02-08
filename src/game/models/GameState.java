package game.models;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import main.Assignment1;

/**
 * 
 */

/**
 * Lightweight container of player game progress, implements serializable and cloneable marker 
 * patterns as instances are saved to file.
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class GameState implements Serializable, Cloneable {

	private static final long serialVersionUID = -153383592411303294L;
	private Map<String, String> collectables;
	private int livesRemaining;
	private int score;
	private int activeLevelNumber;
	
	/**
	 * For constructing new instances of game state (new player or existing player restarting.
	 * Note: saved games with be retrieved from serialized file players.txt
	 */
	public GameState() {
		livesRemaining = 5;
		score = 0;
		activeLevelNumber = 0;
		collectables = new HashMap<String, String>();
	}

	
	public void addCollectable(String symbol, String metaDataLabel){
		collectables.put(symbol, metaDataLabel);
	}
	
	public Map<String, String> getCollectedCollectables(){
		return collectables;
	}
	
	public int getLivesRemaining(){
		return livesRemaining;
	}
	
	public void loseALife(){
		livesRemaining--;
	}	
	public void setLivesRemaining(int livesRemaining) {
		this.livesRemaining = livesRemaining;
	}

	public int getScore(){
		return score;
	}

	public void addToScore(int points){
		score += points;
	}
	
	public int getActiveLevelNumber() {
		return activeLevelNumber;
	}

	public void setActiveLevelNumber(int activeLevelNumber) {
		this.activeLevelNumber = activeLevelNumber;
	}
	
	/** 
	 * Serialises game state object to the folder where JAR is run from, which is passed as a parameter. 
	 */
	public void serialize(String path){
		try{
			FileOutputStream fos = new FileOutputStream(path + "/save.swa");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
			Assignment1.logger.log(Level.INFO, "Game state saved to " + path + "/save.swa");					
		} catch (IOException e) {
			Assignment1.logger.log(Level.SEVERE, e.toString());		
		}
	}
	
	
	/**
	 * Prototype pattern - resets player lives and level then clones game state when player runs 
	 * out of remaining lives. TODO
	 */
	@Override
	public Object clone(){
		livesRemaining = 3;
		activeLevelNumber = 0;
		return this;
	};
}
