/**
 * 
 */
package game.models;

import javafx.geometry.Rectangle2D;

/**
 * The final level boss enemy for a game title, inherits behaviour from Player in order to use 
 * animation timers for jump actions, but also shares patrol behaviour in common with Enemy objects.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class BossEnemy extends Player{
	//TODO MUST EXTEND ENEMY FOR COLLISISON DETECTION
	private double patrolStartRange; // On the X Axis as per a HorizontalEnemy.
	private double patrolEndRange;
	private double direction; // Set to either patrol start or patrol end to control direction of animation.
	private int pointsIfKilled;
	
	/**
	 * Constructs a BossEnemy, which inherits behaviour from Player in order to use animation timers for 
	 * jump actions, but also shares patrol behaviour in common with Enemy objects.
	 */
	public BossEnemy(String playerType, Rectangle2D outline, double speed, double gravity, 
			double jumpHeight, double patrolStartRange, double patrolEndRange, int pointsIfKilled) 
	{
		super(playerType, outline, speed, gravity, jumpHeight);
		this.patrolStartRange = patrolStartRange;
		this.patrolEndRange = patrolEndRange;
		this.direction = patrolStartRange;
		this.pointsIfKilled = pointsIfKilled;
	}

	/**
	 * Combines move behaviour of both player and horizontal enemy objects to create jumping
	 * patrol behaviour.
	 *  @see game.model.Enemy#move()
	 *  @see game.model.Player#move()
	 */
	@Override
	public void move(){
		if (getDirection() == getPatrolStartRange()){
			if (getX() > getPatrolStartRange() ){
				if (!playerIsJumping() && !playerIsFalling()){
					setLeftFacingImage();
					setPlayerIsJumping(true);
					jumpLeft();
				} else {
					changeDirection();				
					setRightFacingImage();
				}
			}
		} else if (getDirection() == getPatrolEndRange()){
			if (getX() < getPatrolEndRange() ){
				if (!playerIsJumping() && !playerIsFalling()){
					setRightFacingImage();
					setPlayerIsJumping(true);
					jumpRight();
				} else {
					changeDirection();				
					setLeftFacingImage();
				}
			}	
		}	
	}

	public void enemyKilled(){
		// TODO What to do when dies? Change image to "squish!"
		// Other actions affect other classes, maybe better elsewhere.
	}
	
	public double getPatrolStartRange() {
		return patrolStartRange;
	}

	public double getPatrolEndRange() {
		return patrolEndRange;
	}

	public int getPointsIfKilled() {
		return pointsIfKilled;
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