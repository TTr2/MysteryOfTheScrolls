/**
 * 
 */
package game.library.models;


import game.models.Player;

/**
 * Abstract template for game specific Player Factory classes.
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 * 
 * @see game.models.Player
 */
public abstract class PlayerFactory {

	public PlayerFactory(){};
	public abstract Player getPlayer(String playerType);
}
