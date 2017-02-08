package game.models;
import java.util.Vector;

import javafx.scene.image.Image;

/**
 * Level objects store all of the game title specific state information needed to display a given 
 * level of a game, levels are specified, generated and leased by LevelFactoryObjectPool class 
 * using object pool pattern. 
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class Level {

	private int levelNumber;
	private Player player;
	private Image backgroundImage;
	private Image gameFrameImage;
	private double layout; // Y position of floor or SHAPE IF SMART ENOUGH WITH PLATFORMS
	private Collectable collectable;
	private Vector<IMoveableSprite> enemies; // Polymorphic substitutability [sp]

	/**
	 * Stores all data required for game engine to display and manipulate game state.
	 */
	public Level(int levelNumber, Image backgroundImage, Image gameFrameImage, 
			double layout, Collectable collectable, Vector<IMoveableSprite> enemies, Player player) {		
		this.levelNumber = levelNumber;
		this.backgroundImage = backgroundImage;
		this.gameFrameImage = gameFrameImage;
		this.layout = layout;
		this.collectable = collectable;
		this.enemies = enemies;
		this.player = player;
	}
	
	public int getLevelNumber() {
		return levelNumber;
	}
	public Player getPlayer() {
		return player;
	}
	public Image getBackgroundImage() {
		return backgroundImage;
	}
	public Image getGameFrameImage() {
		return gameFrameImage;
	}	
	public double getLayout() {
		return layout;
	}
	public Collectable getCollectable() {
		return collectable;
	}
	public Vector<IMoveableSprite> getEnemies() {
		return enemies;
	}
	public void removeKilledEnemy(IMoveableSprite enemy){
		Vector<IMoveableSprite> newEnemies = new Vector<IMoveableSprite>();
		for (int i=0; i<enemies.size();i++){
			if (!enemies.get(i).equals(enemy)){
				newEnemies.addElement(enemies.get(i));
			}
		}
		enemies = newEnemies;
	}
	
}
