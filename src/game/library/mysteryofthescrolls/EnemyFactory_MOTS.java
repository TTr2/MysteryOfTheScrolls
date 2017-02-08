/**
 * 
 */
package game.library.mysteryofthescrolls;

import game.library.models.EnemyFactory;
import game.models.BossEnemy;
import game.models.HorizontalEnemy;
import game.models.IMoveableSprite;
import game.models.VerticalEnemy;
import javafx.geometry.Rectangle2D;

/**
 * Static factory class for generating enemy objects, specified by the type of the required enemy. There 
 * are two sub classes of the abstract Enemy superclass - vertical and horizontal, each with specific 
 * move() method (up/down or left/right).
 * 
 * @see game.models.Enemy
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public final class EnemyFactory_MOTS extends EnemyFactory{
	
	private static EnemyFactory_MOTS instance;
	
	/**
	 * Hidden constructor, encouraging user to access only through static factory method.
	 */
	private EnemyFactory_MOTS(){
	}
	
	/**
	 * Singleton pattern returns a static reference to this EnemyFactory.
	 */
	public static EnemyFactory_MOTS getInstance()
	{
		if (instance == null){
			instance = new EnemyFactory_MOTS();
		}
		return instance;
	}	
	
	/** Returns an instance of the specified Enemy type. */
	@Override
	public IMoveableSprite getEnemy(String enemyType, double x, double y, 
			double patrolStartRange, double patrolEndRange){
		IMoveableSprite enemyToReturn = null;
		Rectangle2D outline = null;		
		switch (enemyType.toUpperCase()){
			case "MUMMY":
				outline = new Rectangle2D(x, y, 73,66);
				enemyToReturn = new HorizontalEnemy("mummy", outline, 
						patrolStartRange, patrolEndRange, 100, 3);
				break;
			case "SKELETON":
				outline = new Rectangle2D(x, y, 27,70);
				enemyToReturn = new HorizontalEnemy("skeleton", outline, 
						patrolStartRange, patrolEndRange, 100, 2);
				break;				
			case "SPIDER":
				outline = new Rectangle2D(x, y, 43,70);
				enemyToReturn = new VerticalEnemy("spider", outline, 
						patrolStartRange, patrolEndRange, 250, 5);
				break;
			case "BEE":
				outline = new Rectangle2D(x, y, 52,60);				
				enemyToReturn = new VerticalEnemy("bee", outline, 
						patrolStartRange, patrolEndRange, 250, 5);
				break;
			case "PHARAOH":
				outline = new Rectangle2D(x, y, 107,120);
				enemyToReturn = new BossEnemy("pharaoh", outline, 
						10, 20, 530, patrolStartRange, patrolEndRange, 1000);
				// TODO player = 20, 170. Thinking 530 is dist between start and end patrol range
				// So dist over 2 up and 2 down gradients will be perfect
				break;				
			default:
				outline = new Rectangle2D(x, y, 27,70);
				enemyToReturn = new HorizontalEnemy("skeleton", outline, 
						patrolStartRange, patrolEndRange, 200, 2);
				break;
		}
		return enemyToReturn;
	}
	
}
