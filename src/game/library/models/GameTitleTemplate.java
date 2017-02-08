/**
 * 
 */
package game.library.models;

import java.util.Map;

import game.models.Level;
import javafx.scene.text.Font;

/**
 * When extending GameTitleTemplate to create new game titles, designers only need to 
 * modify the constant values and level constructor details in the acquireLevel() factory 
 * method to create a new skin for game. Extensions should employ Singleton getInstance() 
 * pattern to function as an Object Pool.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public abstract class GameTitleTemplate implements IGameLevelObjectPool{

	private final String gameTitleName;
	protected final Map<Integer, Level> levels;
//	private Player player;
	private Map<String, Font> gameFontsMap; 
	private String collectableName;

	/**
	 * Super constructor for concrete realisations of GameTitle.
	 */
	public GameTitleTemplate(String gameTitleName, Map<Integer, Level> levels,  
			Map<String, Font> gameFontsMap, String collectableName)
	{
		this.gameTitleName = gameTitleName;
		this.levels = levels;
		this.collectableName = collectableName;
		this.gameFontsMap = gameFontsMap;
	}

	/**
	 * Object pool retrieval with factory method for generating new level instances if not yet instantiated.
	 * @param levelNumber The level number that is being requested.
	 * @return The level requested level object that is being borrowed from the object pool.
	 */	
	public abstract Level acquireLevel(int levelNumber);
	
	/**
	 * Returns the 'borrowed' level back to the object pool whilst retaining it's used state.
	 * @param level The level object that was acquired and is now being released. Returns to object pool.
	 */	
	public abstract void releaseLevel(Level level);	
	
	public String getGameTitleName() {
		return gameTitleName;
	}

	public Map<Integer,Level> getLevels(){
		return levels;
	}
	
	public Font getGameFont(String fontName) {
		if (gameFontsMap.containsKey(fontName)){
			return gameFontsMap.get(fontName);
		}
		else return Font.getDefault();
	}
	
	public String getCollectableName() {
		return collectableName;
	}
	
}
