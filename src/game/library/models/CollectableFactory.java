/**
 * 
 */
package game.library.models;

import game.library.models.CollectableFactory;
import game.models.Collectable;
import game.models.MetaData;

/**
 * Abstract template for game specific Collectable Factory classes.
 * @see game.models.Collectable
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public abstract class CollectableFactory{

	public CollectableFactory() {};
	/** generates and returns an instance of the requested Collectable Type.*/
	public abstract Collectable getCollectable(String collectableType, double x, double y,
			MetaData metaData);

}