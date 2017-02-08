package game.models;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;


/**
 * Baseclass of all game sprite instances, composed of a Rectangle2D instance that manages 
 * layout position and collission detection with other sprites and an image for displaying.
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class Sprite {

	private Image image;
	private Rectangle2D outline;
	double x, y;

	public Sprite(String imageName, Rectangle2D outline) {
		this.image = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/"+imageName+".png"));
		this.outline = outline;
		x = outline.getMinX();
		y = outline.getMinY();
	}

	public double getX() {
		return outline.getMinX();
	}

	public void setX(double x) {
		outline = new Rectangle2D(x, outline.getMinY(), outline.getWidth(), outline.getHeight());
	}

	public double getY() {
		return outline.getMinY();
	}

	public void setY(double y) {
		outline = new Rectangle2D(outline.getMinX(), y, outline.getWidth(), outline.getHeight());
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
//		outline = new Rectangle2D(outline.getMinX(), outline.getMinY(), image.getWidth(), image.getHeight());
	}

	public double getWidth() {
		return outline.getWidth();
	}
	
	public double getHeight() {
		return outline.getHeight();
	}
	
	public Rectangle2D getOutline() {
		return outline;
	}

	public void setOutline(Rectangle2D outline) {
		this.outline = outline;
	}

	public boolean checkCollission(Sprite otherSprite){
		return outline.intersects(otherSprite.getOutline());
	}
}
