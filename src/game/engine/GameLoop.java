/**
 * 
 */
package game.engine;

import game.models.BossEnemy;
import game.models.Enemy;
import game.models.GameState;
import game.models.IMoveableSprite;
import game.models.Level;
import game.models.Player;
import game.models.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

/**
 * An animation game timer for the game engine, which checks for changes in game state prior 
 * to calling displayGameState() in GameGraphicsRenderer. Can be stopped and started by QuizEngine.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 */
public class GameLoop extends AnimationTimer {

	private GameGraphicsRenderer gameGraphicsRenderer;
	private GameState gameState;	
	private Level level;
	private Player player;
	private boolean playerIsInvulnerable;
	
	/**
	 * Constructs animation game timer for gameEngine, initialises its local references to initial game 
	 * state but is not active until started by QuizEngine.
	 */
	public GameLoop(GameGraphicsRenderer gameGraphicsRenderer, GameState gameState, Level level, Player player) {
		this.gameGraphicsRenderer = gameGraphicsRenderer;
		this.gameState = gameState;
		this.level = level;
		this.player = player;
		playerIsInvulnerable = false;
	}

	/**
	 * Manages the movement of enemy sprites and checks for collissions with player sprite.
	 * @see javafx.animation.AnimationTimer#handle(long)
	 */
	@Override
	public void handle(long timestamp) {
		moveEnemies();
		checkForCollissions();
		gameGraphicsRenderer.displayGameState(gameState, level, player);		
	}

	private void moveEnemies(){
		for (IMoveableSprite enemy : level.getEnemies()){
			enemy.move();
		}
	}
	
	private void checkForCollissions(){
		for (IMoveableSprite enemy : level.getEnemies()){
			detectCollission(player, enemy);
		}		
		if (level.getLevelNumber() == 0 && player.getX() < 50){
			player.setX(50);
		} else if (level.getLevelNumber() > 0 && player.getX() < 0){
			GameEngine.getInstance().loadNewLevel(level.getLevelNumber() - 1);		
		} else if (player.getX() > 800 - player.getWidth() && level.getLevelNumber() < 4){
			GameEngine.getInstance().loadNewLevel(level.getLevelNumber() + 1);
		} else if (player.getX() > 750 - player.getWidth() && level.getLevelNumber() == 4){
			player.setX(750 - player.getWidth());
		}		
	}

	
	/** 
	 * Used to perform simple check whether player in contact with sprite, whereas 
	 * detectCollission(IMoveableSprite player, IMoveableSprite enemy) performs more advanced 
	 * checks about point of intersection and initiates action.
	 * @see game.model.GameLoop#detectCollision(IMoveableSprite player, IMoveableSprite enemy)llljll
	 * @param player The player sprite.
	 * @param sprite Can be a collectable item or an enemy (requires down casting to Sprite).
	 * @return True if collission detected.
	 */
	public boolean detectCollission(Sprite player, Sprite sprite){
			return player.checkCollission(sprite);
	}
	
	// TODO check for entrance and exits, action or just transition?
	// Also check for jumping against walls.

	
	/**
	 * Used to check whether player in contact with enemy, and if so decides
	 * which sprite dies from the contact as player can kill enemy only if it lands 
	 * on top of the enemy. 
	 */
	public void detectCollission(IMoveableSprite player, IMoveableSprite enemy){
		
		if (detectCollission((Sprite) player, (Sprite) enemy)){
			
			if (!(enemy instanceof BossEnemy)){
				if (player.getY() + player.getHeight() - 20 <= enemy.getY()){
					killEnemy((Enemy) enemy);
					playerIsInvulnerable = true;
					AnimationTimer invulnerable = new AnimationTimer(){
						int count = 0;
						@Override
						public void handle(long arg0) {
							count++;
							if (count > 100){
								playerIsInvulnerable = false;
								stop();
							}
						}
					};
					invulnerable.start();
				} else {
					if (gameState.getLivesRemaining() > 1){
						if (!playerIsInvulnerable){
							gameState.loseALife();
							playerIsInvulnerable = true;
							AnimationTimer invulnerable = new AnimationTimer(){
								int count = 0;
								@Override
								public void handle(long arg0) {
									count++;
									if (count > 100){
										playerIsInvulnerable = false;
										stop();
									}
								}
							};
							invulnerable.start();
						}
					} else {
						gameState.setLivesRemaining(5);
						((Player) player).setX(50);
					}
				}
			}
		}			
	}
	
	private void killEnemy(Enemy enemy){
		gameState.addToScore(((Enemy)enemy).getPointsIfKilled());

		AnimationTimer deathSequence = new AnimationTimer(){
			int counter = 0;
			@Override
			public void handle(long timestamp) {
				if (counter <= 2){enemy.setImage(new Image(getClass().getResourceAsStream("/game/images/explosion/01.png")));
				} else if (counter <= 4){enemy.setImage(new Image(getClass().getResourceAsStream("/game/images/explosion/02.png")));						
				} else if (counter <= 6){enemy.setImage(new Image(getClass().getResourceAsStream("/game/images/explosion/03.png")));						
				} else if (counter <= 8){enemy.setImage(new Image(getClass().getResourceAsStream("/game/images/explosion/04.png")));						
				} else if (counter <= 10){enemy.setImage(new Image(getClass().getResourceAsStream("/game/images/explosion/05.png")));						
				} else if (counter <= 12){enemy.setImage(new Image(getClass().getResourceAsStream("/game/images/explosion/06.png")));						
				} else if (counter <= 14){enemy.setImage(new Image(getClass().getResourceAsStream("/game/images/explosion/07.png")));						
				} else if (counter <= 16){enemy.setImage(new Image(getClass().getResourceAsStream("/game/images/explosion/08.png")));
				} else if (counter <= 18){enemy.setImage(new Image(getClass().getResourceAsStream("/game/images/explosion/09.png")));
				} else if (counter <= 20){enemy.setImage(new Image(getClass().getResourceAsStream("/game/images/explosion/10.png")));
				} else if (counter <= 22){enemy.setImage(new Image(getClass().getResourceAsStream("/game/images/explosion/11.png")));
				} else { 
					stop();
					level.removeKilledEnemy(enemy);
				}
				counter++;
			}

		};		

		deathSequence.start();
		
	}
	
	
}
