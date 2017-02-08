/**
 * 
 */
package game.library.mysteryofthescrolls;

import game.library.models.CollectableFactory;
import game.models.Collectable;
import game.models.MetaData;
import javafx.geometry.Rectangle2D;

/**
 * Factory helper class to generate an appropriate Collectable object for this Game Title.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public final class CollectableFactory_MOTS extends CollectableFactory{

	private static CollectableFactory_MOTS instance;
	
	/**
	 * Hidden private constructor, use public static getCollectable() method.
	 */
	private CollectableFactory_MOTS() {
	}

	/** Returns static reference to this Collectable Factory. */
	public static CollectableFactory_MOTS getInstance(){
		if (instance == null){
			instance = new CollectableFactory_MOTS();
		}
		return instance;
	}
	
	
	/**
	 * Factory method for producing game title specific collectable items. 
	 */
	@Override
	public Collectable getCollectable(String collectableType, double x, double y, MetaData metaData){
		
		Collectable collectableToReturn = null;
		Rectangle2D outline = null;
		switch (collectableType.toUpperCase()){
			case "SCROLLS":
				outline = new Rectangle2D(x, y, 50, 50);
				collectableToReturn = new Collectable("Scrolls", outline, "scrollPre", "scrollPost", metaData);
				break;
			default:
				outline = new Rectangle2D(x, y, 50, 50);
				collectableToReturn = new Collectable("Scrolls", outline, "scrollPre", "scrollPost", metaData);
				break;				
		}
		return collectableToReturn;
	}
}