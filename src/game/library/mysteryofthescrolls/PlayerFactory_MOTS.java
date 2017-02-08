/**
 * 
 */
package game.library.mysteryofthescrolls;


import game.library.models.PlayerFactory;
import game.models.Player;
import javafx.geometry.Rectangle2D;

/**
 * Returns an instance of the specified Player object.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class PlayerFactory_MOTS extends PlayerFactory{

	private static PlayerFactory_MOTS instance;

	/** Hidden private constructor, use static getPlayer factory method. */
	private PlayerFactory_MOTS() {
	}

	/**
	 * Singleton pattern returns a static reference to this PlayerFactory.
	 */
	public static PlayerFactory_MOTS getInstance()
	{
		if (instance == null){
			instance = new PlayerFactory_MOTS();
		}
		return instance;
	}	
	
	/** Returns an instance of the specified Player type. */
	@Override
	public Player getPlayer(String playerType){
		Player playerToReturn = null;
		Rectangle2D outline = null;
		switch (playerType.toUpperCase()){
			case "ARCHAEOLOGIST":
				outline = new Rectangle2D(70, 480, 50, 70);
				playerToReturn = new Player("archaeologist", outline, 10, 20, 170);
				break;
			default:
				outline = new Rectangle2D(70, 480, 50, 70);
				playerToReturn = new Player("archaeologist", outline, 10, 20, 170);
				break;
		}
		return playerToReturn;		
	}
}
