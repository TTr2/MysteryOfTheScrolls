/**
 * 
 */
package game.library.models;

import game.models.MetaData;

/**
 * Abstract template for game specific MetaData Factory classes. 
 * @see game.models.MetaData
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 */
public abstract class MetaDataFactory {

	public MetaDataFactory(){};
	public abstract MetaData getMetaData(int levelNumber);
}
