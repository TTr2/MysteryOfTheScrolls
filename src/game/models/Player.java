package game.models;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 * 
 */

/** 
 * Player class with customisable image selection, extends Sprite. Implements IFlippableImage 
 * interface to flip image left/right, so requires assets naming with Left/Right suffix.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class Player extends Sprite implements IFlippableImage, IMoveableSprite{

	private Image imageFacesLeft;
	private Image imageFacesRight;
	private double speed; // accessed by animation timers in Game Engine.
	private double gravity; // accessed by animation timers in Game Engine.
	private boolean playerIsJumping;
	private boolean playerIsFalling;
	private AnimationTimer jumpLeftLoop;
	private AnimationTimer jumpUpLoop;
	private AnimationTimer jumpRightLoop;
	private double floorLevel;
	
	public Player(String playerType, Rectangle2D outline, double speed, double gravity, double jumpHeight) {
		super(playerType+"Right", outline);
		imageFacesRight = getImage();
		imageFacesLeft = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/"+playerType+"Left.png"));
		this.speed = speed;
		this.gravity = gravity;
		floorLevel = getY() + getHeight();
		playerIsJumping = false;
		playerIsFalling = false;
		jumpLeftLoop = new AnimationTimer(){
			@Override
			public void handle(long timestamp) {
				if (playerIsJumping){
					if (getY() > floorLevel - jumpHeight){
						setY(getY() - (jumpHeight/gravity));
						setX(getX() - speed);
					} else {
						playerIsJumping = false;
						playerIsFalling = true;
					}
				} else {
					if (getY() < floorLevel - getHeight()){
						setY(getY() + (jumpHeight/gravity));
						setX(getX() - speed);
					} else {
						stop();
						playerIsFalling = false;
					}					
				}
			}	
		};
		jumpUpLoop = new AnimationTimer(){
			@Override
			public void handle(long timestamp) {
				if (playerIsJumping){
					if (getY() > floorLevel - jumpHeight){
						setY(getY() - (jumpHeight/gravity));
					} else {
						playerIsJumping = false;
						playerIsFalling = true;
					}
				} else {
					if (getY() < floorLevel - getHeight()){
						setY(getY() + (jumpHeight/gravity));
					} else {
						stop();
						playerIsFalling = false;
					}					
				}
			}	
		};
		jumpRightLoop = new AnimationTimer(){
			@Override
			public void handle(long timestamp) {
				if (playerIsJumping){
					if (getY() > floorLevel - jumpHeight){
						setY(getY() - (jumpHeight/gravity));
						setX(getX() + speed);
					} else {
						playerIsJumping = false;
						playerIsFalling = true;
					}
				} else {
					if (getY() < floorLevel - getHeight()){
						setY(getY() + (jumpHeight/gravity));
						setX(getX() + speed);
					} else {
						stop();
						playerIsFalling = false;
					}					
				}
			}	
		};		
	}

	/**
	 * Check which direction the player is facing and then increments the player 
	 * sprite's horizontal position according to user input handled by the Game Engine.
	 */
	@Override
	public void move(){
		if (getImage() == imageFacesRight){
			setX(getX() + speed);						
		} else {
			setX(getX() - speed);						
		}
	}

	/* 
	 * player has collided with an enemy so change the player sprite image to appropriate
	 * death sequence.
	 */
	public void die() {
// TODO Clone pattern Prototype ? or is thta for state only?
		// What can this sprite object actually do?
		// other than change the image of the sprite to a dead one, and fall over maybe?
		// On a  timer?
		
	}	

	public void jumpLeft(){
		jumpLeftLoop.start();
	}
	
	public void jumpUp(){
		jumpUpLoop.start();
	}
	
	public void jumpRight(){
		jumpRightLoop.start();
	}

	public void fall(double floorHeight){
		if (getY() < floorHeight - getHeight()){
			setY(getY() + gravity);
		}
	}
	
	public void pickUpScroll(){
		// add scroll to gameState collectedScrolls.
		// Kick off mini-game?
		
		// Should game actions be in here at all or just state? Would be nice to tie the two together if 
		// possible.
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;			
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	public void setPlayerIsJumping(boolean newState){
		playerIsJumping = newState;
	}

	public void setPlayerIsFalling(boolean newState){
		playerIsFalling = newState;
	}
	
	public boolean playerIsJumping() {
		return playerIsJumping;
	}
	
	public boolean playerIsFalling() {
		return playerIsFalling;
	}

	public void setLeftFacingImage(){
		setImage(imageFacesLeft);
	}
	
	public void setRightFacingImage(){
		setImage(imageFacesRight);
	}	
}
