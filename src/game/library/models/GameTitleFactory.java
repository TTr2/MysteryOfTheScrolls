/**
 * 
 */
package game.library.models;

import game.library.mysteryofthescrolls.GameTitle_MOTS;

/**
 * Static class with public factory method for acquiring and returning a reference 
 * to a static instance of a specific game title object.
 * @see game.library.models.GameTitleTemplate
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 */
public final class GameTitleFactory {

	/**
	 * Hidden constructor, as only the factory method should be used.
	 */
	private GameTitleFactory() {
	}

	/**
	 * Public static factory method for acquiring and returning a reference 
	 * to a static instance of a game title object.
	 * @param nameOfGame The title of the game to return.
	 * @return The requested game title (defaults to "Mystery Of The Scrolls" if game title parameter 
	 * not found).
	 */
	public static IGameLevelObjectPool getGameTitle(String nameOfGame){
		
		GameTitleTemplate gameTitleToReturn = null;
		
		switch (nameOfGame.toUpperCase()){
		
			case "MYSTERY OF THE SCROLLS!":
				gameTitleToReturn = GameTitle_MOTS.getInstance();
				break;
			/* Add case for additional games here. */
			default:
				gameTitleToReturn = GameTitle_MOTS.getInstance(); 
		}
		return gameTitleToReturn;
	}
	
	
}
