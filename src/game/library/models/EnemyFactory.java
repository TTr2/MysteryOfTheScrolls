/**
 * 
 */
package game.library.models;

import game.models.IMoveableSprite;

/**
 * Abstract template for game specific Enemy Factory classes.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public abstract class EnemyFactory {
	
	public EnemyFactory(){};
	/** generates and returns an instance of the requested Enemy Type. */
	public abstract IMoveableSprite getEnemy(String enemyType, double x, double y, 
			double patrolStartRange, double patrolEndRange);
	
}
