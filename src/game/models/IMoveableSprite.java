/**
 * 
 */
package game.models;

import javafx.scene.image.Image;

/**
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public interface IMoveableSprite {

	public void move();
	public double getX();
	public double getY();
	public Image getImage();
	public double getHeight();
	
}
