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
public class BossEnemyINPROGRESS extends Player{

	HorizontalEnemy horizontalEnemy;
	
	/**
	 * Constructs a BossEnemy, which inherits behaviour from Player in order to use animation timers for 
	 * jump actions, but also shares patrol behaviour in common with Enemy objects.
	 */
	public BossEnemyINPROGRESS(String playerType, Rectangle2D outline, double speed, double gravity, 
			double jumpHeight, double patrolStartRange, double patrolEndRange, int pointsIfKilled) 
	{
		// Player factory dicking it up? Remove factory?

		super(playerType, outline, speed, gravity, jumpHeight);
		horizontalEnemy = new HorizontalEnemy(playerType, outline, patrolStartRange, patrolEndRange, 
				pointsIfKilled, (int) speed);
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
		return horizontalEnemy.getPatrolStartRange();
	}

	public double getPatrolEndRange() {
		return horizontalEnemy.getPatrolEndRange();
	}

	public int getPointsIfKilled() {
		return horizontalEnemy.getPointsIfKilled();
	}

	public double getDirection() {
		return horizontalEnemy.getDirection();
	}
	
	/* Switch direction to opposite end of patrol range.*/
	public void changeDirection() {
		if (horizontalEnemy.getDirection() == horizontalEnemy.getPatrolStartRange()){
			horizontalEnemy.changeDirection();
		} else if (horizontalEnemy.getDirection() == horizontalEnemy.getPatrolEndRange()){
			horizontalEnemy.changeDirection();
		}
	}
}