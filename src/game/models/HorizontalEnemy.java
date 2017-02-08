package game.models;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 * 
 */

/**
 * Horizontal enemies override move() method to change their position left and right at their allotted 
 * speed. Implements IFlippableImage interface to flip image left/right, so requires assets naming with 
 * Left/Right suffix.
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class HorizontalEnemy extends Enemy implements IFlippableImage{

	private Image imageFacesLeft;
	private Image imageFacesRight;
	
	public HorizontalEnemy(String imageName, Rectangle2D outline, double patrolStartRange, 
			double patrolEndRange, int pointsIfKilled, int speed) {
		super(imageName+"Left", outline, patrolStartRange, patrolEndRange, pointsIfKilled, speed);
		imageFacesLeft = getImage();
		imageFacesRight = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/"+imageName+"Right.png"));
	}

	/**
	 * @see game.model.Enemy#move()
	 */
	@Override
	public void move(){
		if (getDirection() == getPatrolStartRange()){
			if (getX() > getPatrolStartRange() ){
				setX(getX() - getSpeed());
			} else {
				changeDirection();				
				setRightFacingImage();
			}
		} 
		else if (getDirection() == getPatrolEndRange()){
			if (getX() < getPatrolEndRange() ){
				setX(getX() + getSpeed());
			} else {
				changeDirection();				
				setLeftFacingImage();
			}
		}
	}
	
	@Override
	public void setLeftFacingImage(){
		setImage(imageFacesLeft);	
	}
	
	@Override
	public void setRightFacingImage(){
		setImage(imageFacesRight);
	}	
	
}
