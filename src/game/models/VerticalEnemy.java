package game.models;
import javafx.geometry.Rectangle2D;

/**
 * Vertical enemies override move() method to change their position up and down at their allotted speed.
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 */
public class VerticalEnemy extends Enemy {

	public VerticalEnemy(String imageName, Rectangle2D outline, double patrolStartRange, 
			double patrolEndRange, int pointsIfKilled, int speed) {
		super(imageName, outline, patrolStartRange, patrolEndRange, pointsIfKilled, speed);
	}
	
	/**
	 * @see game.model.Enemy#move()
	 */
	@Override
	public void move(){
		if (getDirection() == getPatrolStartRange() && getY() > getPatrolStartRange() ){
			setY(getY() - getSpeed());
		} 
		else if (getDirection() == getPatrolEndRange() && getY() < getPatrolEndRange() ){
			setY(getY() + getSpeed());
		}
		else {
			changeDirection();
		}
	}
}

