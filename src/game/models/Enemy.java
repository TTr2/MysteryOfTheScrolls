package game.models;
import javafx.geometry.Rectangle2D;

/**
 * 
 */

/**
 * Abstract superclass for Enemy objects, adds a speed, direction and patrol range that Enemy sprites move 
 * between and value to add to high score record if player kills an Enemy.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public abstract class Enemy extends Sprite implements IMoveableSprite{

	private double patrolStartRange; // Can represent X for HorizontalEnemy or Y for verticalEnemy.
	private double patrolEndRange;
	private double direction; // Set to either patrol start or end to control direction of animation.
	private int pointsIfKilled;
	private double speed;
	
	
	/**
	 * Super constructor for concrete realisations of Enemy.
	 */
	public Enemy(String imageName, Rectangle2D outline, double patrolStartRange, 
			double patrolEndRange, int pointsIfKilled, int speed) {
		super(imageName, outline);
		this.patrolStartRange = patrolStartRange;
		this.patrolEndRange = patrolEndRange;
		this.direction = patrolStartRange;
		this.pointsIfKilled = pointsIfKilled;
		this.speed = speed;
	}

	/**
	 * Move() adds value of speed to x or y position depending on whether child is a 
	 * HorizontalEnemy or VerticalEnemymovement, to move enemy sprite across the screen 
	 * until it reaches the value specified in direction (either patrolStartRange or patrolEndRange). 
	 */
	public abstract void move();

	public double getPatrolStartRange() {
		return patrolStartRange;
	}

	public double getPatrolEndRange() {
		return patrolEndRange;
	}

	public int getPointsIfKilled() {
		return pointsIfKilled;
	}

	public double getSpeed() {
		return speed;
	}

	public double getDirection() {
		return direction;
	}
	
	/* Switch direction to opposite end of patrol range.*/
	public void changeDirection() {
		if (direction == patrolStartRange){
			direction = patrolEndRange;
		} else if (direction == patrolEndRange){
			direction = patrolStartRange;
		}
	}
}
