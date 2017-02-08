/**
 * 
 */
package game.models;

/**
 * The final level boss enemy for a game title, inherits behaviour from Player in order to use 
 * animation timers for jump actions, but also shares patrol behaviour in common with Enemy objects.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class BossEnemyDecorator {

	private Enemy enemy;	
	private Player player;
	
	/**
	 * Constructs a BossEnemy, which inherits behaviour from Player in order to use animation timers for 
	 * jump actions, but also shares patrol behaviour in common with Enemy objects.
	 */
	public BossEnemyDecorator(Enemy enemy, Player player) 
	{
		this.enemy = enemy;
		this.player = player;
	}

	/**
	 * Combines move behaviour of both player and horizontal enemy objects to create jumping
	 * patrol behaviour.
	 *  @see game.model.Enemy#move()
	 *  @see game.model.Player#move()
	 */

	public void move(){
		player.move();
	}

	public void enemyKilled(){
		// TODO What to do when dies? Change image to "squish!"
		// Other actions affect other classes, maybe better elsewhere.
	}
	
	public double getPatrolStartRange() {
		return enemy.getPatrolStartRange();
	}

	public double getPatrolEndRange() {
		return enemy.getPatrolEndRange();
	}

	public int getPointsIfKilled() {
		return enemy.getPointsIfKilled();
	}

	public double getDirection() {
		return enemy.getDirection();
	}
	
	/* Switch direction to opposite end of patrol range.*/
	public void changeDirection() {
		enemy.changeDirection();
	}
}