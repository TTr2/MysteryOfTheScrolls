package game.models;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import quiz.models.QuizCard;


/**
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class Collectable {

	private String collectablesName;
	private Sprite sprite; // Composition over inheritance.
	private Image imagePostCollection;
	private MetaData metaData;
	private boolean collected;

	/**
	 * Constructor for collectable object which contains a sprite, a meta image for display on
	 * the header bar and a quizCard object for the level's quiz mini game.
	 */
	public Collectable(String collectablesName, Rectangle2D outline, String imageNamePreCollection, 
			String imageNamePostCollection, MetaData metaData) 
	{
		this.collectablesName = collectablesName;
		sprite = new Sprite(imageNamePreCollection, outline);		
		this.imagePostCollection = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/" 
				+ imageNamePostCollection + ".png"));
		this.metaData = metaData;
		collected = false;
	}

	public boolean isCollected() {
		return collected;
	}

	public void collect() {
		collected = true;
		sprite.setImage(imagePostCollection);		
	}
	
	public String getCollectableName(){
		return collectablesName;
	}
	public Sprite getSprite() {
		return sprite;
	}

	public MetaData getMetaData() {
		return metaData;
	}

	public QuizCard getQuizCard() {
		return metaData.getQuizCard();
	}

}
