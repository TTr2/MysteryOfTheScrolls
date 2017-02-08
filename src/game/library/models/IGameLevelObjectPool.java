/**
 * 
 */
package game.library.models;

import game.models.Level;
import javafx.scene.text.Font;

/**
 * Specifies Object Pool pattern behaviours required for GameTitle classes implementing 
 * IGameLevelObjectPool
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 */
public interface IGameLevelObjectPool {

	Level acquireLevel(int gameLevel);
	void releaseLevel(Level level);
	String getGameTitleName();
	String getCollectableName();
	Font getGameFont(String fontName);
	
}
