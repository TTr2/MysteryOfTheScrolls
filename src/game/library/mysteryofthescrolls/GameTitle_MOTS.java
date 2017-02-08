/**
 * 
 */
package game.library.mysteryofthescrolls;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import game.library.models.GameTitleTemplate;
import game.models.Collectable;
import game.models.IMoveableSprite;
import game.models.Level;
import game.models.MetaData;
import game.models.Player;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

/**
 * "Mystery of the Scrolls", a 2D platform game based in an ancient Egyptian pyramid.
 * Implements the IGameLevelObjectPool interface to specify the Object Pool pattern for
 * managing the creation and leasing of level objects.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class GameTitle_MOTS extends GameTitleTemplate 
{

	private static GameTitleTemplate instance;
	
	private static final String GAME_TITLE_NAME = "Mystery of the Scrolls!";
	/* Application Window Values */
	private final int GAME_WINDOW_WIDTH = 800;
	private final int GAME_WINDOW_HEIGHT = 600;
	/* Fonts for game display */
	private static final Map<String, Font> GAME_FONTS;
	static{
		GAME_FONTS = new HashMap<String, Font>();
		GAME_FONTS.put("GAME_SPLASH_FONT", Font.loadFont(GameTitle_MOTS.class.getResource("/fonts/AKENATEN.TTF").toExternalForm(),68));
		GAME_FONTS.put("GAME_INFO_FONT", Font.loadFont(GameTitle_MOTS.class.getResource("/fonts/AKENATEN.TTF").toExternalForm(),28));
		GAME_FONTS.put("GAME_TITLE_FONT", Font.loadFont(GameTitle_MOTS.class.getResource("/fonts/AKENATEN.TTF").toExternalForm(),40));
		GAME_FONTS.put("META_DATA_FONT", Font.loadFont(GameTitle_MOTS.class.getResource("/fonts/OldEgyptGlyphs.ttf").toExternalForm(),36));
		GAME_FONTS.put("META_LABEL_FONT", Font.loadFont(GameTitle_MOTS.class.getResource("/fonts/AKENATEN.TTF").toExternalForm(),14));
	}
	/* Level layout values replace magic numbers in constructor code further down.*/
	private final double FLOOR_LEVEL = 50;
	private final double FLOOR_LEVEL_RELATIVE_TO_WINDOW = GAME_WINDOW_HEIGHT - FLOOR_LEVEL;
	/* Sprite values replace magic numbers in constructor code further down. */
	private final double COLLECTABLE_SPRITE_HEIGHT = CollectableFactory_MOTS.getInstance().getCollectable("Scrolls", 0,0,null).getSprite().getHeight();	
	private final double SKELETON_SPRITE_HEIGHT = EnemyFactory_MOTS.getInstance().getEnemy("skeleton", 0,0,0,0).getHeight();
	private final double MUMMY_SPRITE_HEIGHT = EnemyFactory_MOTS.getInstance().getEnemy("mummy", 0,0,0,0).getHeight();	
	private final double BEE_SPRITE_HEIGHT = EnemyFactory_MOTS.getInstance().getEnemy("bee", 0,0,0,0).getHeight();
	private final double SPIDER_SPRITE_HEIGHT = EnemyFactory_MOTS.getInstance().getEnemy("spider", 0,0,0,0).getHeight();
	private final double PHARAOH_SPRITE_HEIGHT = EnemyFactory_MOTS.getInstance().getEnemy("pharaoh", 0,0,0,0).getHeight();	

	

	/**
	 * Hidden private constructor, use getInstance() singleton method to acquire a reference to this class.
	 */
	private GameTitle_MOTS() 
	{
		super(GAME_TITLE_NAME, new HashMap<Integer, Level>(),GAME_FONTS, "Scrolls");
		acquireLevel(0); // Setup the first level, level number zero.
	}
	
	/**
	 * Singleton pattern returns an instance of this game title. uses singleton in order to 
	 * retain state of level objects.
	 */
	public static GameTitleTemplate getInstance()
	{
		if (instance == null){
			instance = new GameTitle_MOTS();
		}
		return instance;
	}
	
	/**
	 * Returns the 'borrowed' level back to the object pool whilst retaining it's used state.
	 * @param level The level object that was acquired and is now being released. Returns to object pool.
	 */
	@Override
	public void releaseLevel(Level level)
	{
		levels.put(level.getLevelNumber(), level);
	}
	
	/**
	 * Object pool retrieval with factory method for generating new level instances if not yet instantiated.
	 * @param levelNumber The level number that is being requested.
	 * @return The level requested level object that is being borrowed from the object pool.
	 */
	@Override
	public Level acquireLevel(int levelNumber)
	{
		if (levels.containsKey(levelNumber)){
			return levels.remove(levelNumber);
		} else {
			Image backgroundImage, gameFrameImage;
			MetaData metaData;
			Collectable collectableForRequestedLevel = null;			
			Vector<IMoveableSprite> vectorOfEnemiesForLevel;
			Player player = null;
			
			switch(levelNumber){

				case 0:
					backgroundImage = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/lvlZeroBgnd.png"));
					gameFrameImage = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/GameFrame0.png"));
					metaData = MetaDataFactory_MOTS.getInstance().getMetaData(0);
					collectableForRequestedLevel = CollectableFactory_MOTS.getInstance().getCollectable("Scrolls",
							715, FLOOR_LEVEL_RELATIVE_TO_WINDOW - COLLECTABLE_SPRITE_HEIGHT, metaData);	
					IMoveableSprite[] arrayOfEnemiesForLevelZero = {
							EnemyFactory_MOTS.getInstance().getEnemy("skeleton", 
								300, FLOOR_LEVEL_RELATIVE_TO_WINDOW - SKELETON_SPRITE_HEIGHT, 
								200, 450),
							EnemyFactory_MOTS.getInstance().getEnemy("bee", 
								500, FLOOR_LEVEL_RELATIVE_TO_WINDOW - BEE_SPRITE_HEIGHT - 100, 
								300, FLOOR_LEVEL_RELATIVE_TO_WINDOW - BEE_SPRITE_HEIGHT)};
					vectorOfEnemiesForLevel = new Vector<IMoveableSprite>(Arrays.asList(arrayOfEnemiesForLevelZero));
					player = PlayerFactory_MOTS.getInstance().getPlayer("archaeologist");
					break;					
				case 1:
					backgroundImage = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/lvlExtBgnd.png"));
					gameFrameImage = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/GameFrameMid.png"));
					metaData = MetaDataFactory_MOTS.getInstance().getMetaData(1);
					collectableForRequestedLevel = CollectableFactory_MOTS.getInstance().getCollectable("Scrolls",
							715, FLOOR_LEVEL_RELATIVE_TO_WINDOW - COLLECTABLE_SPRITE_HEIGHT, metaData);	
					IMoveableSprite[] arrayOfEnemiesForLevelOne = {
							EnemyFactory_MOTS.getInstance().getEnemy("bee", 
								200, FLOOR_LEVEL_RELATIVE_TO_WINDOW - BEE_SPRITE_HEIGHT - 100, 
								300, FLOOR_LEVEL_RELATIVE_TO_WINDOW - BEE_SPRITE_HEIGHT),
							EnemyFactory_MOTS.getInstance().getEnemy("skeleton", 
								220, FLOOR_LEVEL_RELATIVE_TO_WINDOW - SKELETON_SPRITE_HEIGHT, 
								220, 580),
							EnemyFactory_MOTS.getInstance().getEnemy("bee", 
								600, FLOOR_LEVEL_RELATIVE_TO_WINDOW - BEE_SPRITE_HEIGHT - 100, 
								300, FLOOR_LEVEL_RELATIVE_TO_WINDOW - BEE_SPRITE_HEIGHT)};
					vectorOfEnemiesForLevel = new Vector<IMoveableSprite>(Arrays.asList(arrayOfEnemiesForLevelOne));
					player = PlayerFactory_MOTS.getInstance().getPlayer("archaeologist");
					break;
				case 2:
					backgroundImage = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/lvlExtBgnd.png"));
					gameFrameImage = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/GameFrameMid.png"));
					metaData = MetaDataFactory_MOTS.getInstance().getMetaData(2);
					collectableForRequestedLevel = CollectableFactory_MOTS.getInstance().getCollectable("Scrolls",
							715, FLOOR_LEVEL_RELATIVE_TO_WINDOW - COLLECTABLE_SPRITE_HEIGHT, metaData);	
					IMoveableSprite[] arrayOfEnemiesForLevel2 = {
							EnemyFactory_MOTS.getInstance().getEnemy("spider", 
								200, FLOOR_LEVEL_RELATIVE_TO_WINDOW - SPIDER_SPRITE_HEIGHT - 100, 
								300, FLOOR_LEVEL_RELATIVE_TO_WINDOW - SPIDER_SPRITE_HEIGHT),
							EnemyFactory_MOTS.getInstance().getEnemy("spider", 
								400, FLOOR_LEVEL_RELATIVE_TO_WINDOW - SPIDER_SPRITE_HEIGHT - 200, 
								300, FLOOR_LEVEL_RELATIVE_TO_WINDOW - SPIDER_SPRITE_HEIGHT),
							EnemyFactory_MOTS.getInstance().getEnemy("spider", 
								600, FLOOR_LEVEL_RELATIVE_TO_WINDOW - SPIDER_SPRITE_HEIGHT - 300, 
								300, FLOOR_LEVEL_RELATIVE_TO_WINDOW - SPIDER_SPRITE_HEIGHT)};
					vectorOfEnemiesForLevel = new Vector<IMoveableSprite>(Arrays.asList(arrayOfEnemiesForLevel2));
					player = PlayerFactory_MOTS.getInstance().getPlayer("archaeologist");
					break;
				case 3:
					backgroundImage = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/lvlExtBgnd.png"));
					gameFrameImage = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/GameFrameMid.png"));
					metaData = MetaDataFactory_MOTS.getInstance().getMetaData(3);					
					collectableForRequestedLevel = CollectableFactory_MOTS.getInstance().getCollectable("Scrolls",
							715, FLOOR_LEVEL_RELATIVE_TO_WINDOW - COLLECTABLE_SPRITE_HEIGHT, metaData);	
					IMoveableSprite[] arrayOfEnemiesForLevel3 = {
							EnemyFactory_MOTS.getInstance().getEnemy("mummy", 
								200, FLOOR_LEVEL_RELATIVE_TO_WINDOW - MUMMY_SPRITE_HEIGHT, 
								200, 400),
							EnemyFactory_MOTS.getInstance().getEnemy("mummy", 
								400, FLOOR_LEVEL_RELATIVE_TO_WINDOW - MUMMY_SPRITE_HEIGHT, 
								400, 600)};
					vectorOfEnemiesForLevel = new Vector<IMoveableSprite>(Arrays.asList(arrayOfEnemiesForLevel3));
					player = PlayerFactory_MOTS.getInstance().getPlayer("archaeologist");
					break;
				case 4:
					backgroundImage = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/lvlIntBgnd.png"));
					gameFrameImage = new Image(getClass().getResourceAsStream("/game/library/mysteryofthescrolls/images/GameFrameEnd.png"));
					metaData = MetaDataFactory_MOTS.getInstance().getMetaData(4);
					collectableForRequestedLevel = CollectableFactory_MOTS.getInstance().getCollectable("Scrolls",
							685, FLOOR_LEVEL_RELATIVE_TO_WINDOW - COLLECTABLE_SPRITE_HEIGHT, metaData);	
					IMoveableSprite[] arrayOfEnemiesForLevel4 = {
							EnemyFactory_MOTS.getInstance().getEnemy("pharaoh", 
								610, FLOOR_LEVEL_RELATIVE_TO_WINDOW - PHARAOH_SPRITE_HEIGHT, 
								80, 610)};
					vectorOfEnemiesForLevel = new Vector<IMoveableSprite>(Arrays.asList(arrayOfEnemiesForLevel4));
					player = PlayerFactory_MOTS.getInstance().getPlayer("archaeologist");
					break;
				default:
					return levels.get(0);
			}
			
			levels.put(levelNumber, new Level(levelNumber, 
					backgroundImage, gameFrameImage, FLOOR_LEVEL, 
					collectableForRequestedLevel, vectorOfEnemiesForLevel, player));

			return levels.remove(levelNumber); 
		}
		
	}

	public int getGameWindowWidth() 
	{
		return GAME_WINDOW_WIDTH;
	}

	public int getGameWindowHeight() 
	{
		return GAME_WINDOW_HEIGHT;
	}

	public Font getGameFont(String fontName) {
		if (GAME_FONTS.containsKey(fontName)){
			return GAME_FONTS.get(fontName);
		}
		else return Font.getDefault();
	}	
	
}



