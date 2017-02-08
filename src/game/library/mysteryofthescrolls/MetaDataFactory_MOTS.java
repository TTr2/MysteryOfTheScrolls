/**
 * 
 */
package game.library.mysteryofthescrolls;

import java.util.HashMap;
import java.util.Map;

import game.library.models.MetaDataFactory;
import game.models.MetaData;
import javafx.scene.text.Font;
import quiz.models.QuizCard;

/**
 * Factory class to generate MetaData objects specific to this Game Title for embedding in Collectable 
 * items.
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 */
public class MetaDataFactory_MOTS extends MetaDataFactory {

	private static MetaDataFactory_MOTS instance;
	
	private final static String GAME_TITLE_NAME = "Mystery of the Scrolls!";
	private final static String HISTORY_PERIOD = "Egypt";
	private final static String COLLECTABLE_NAME = "Hieroglyph";	
	private final static String[] META_DATA_SYMBOLS = new String[]{"O","y","L","p","/"};
	private final static String[] META_DATA_LABELS = new String[]{"MAN","SCARAB","CROWN","HORUS","PHARAOH",}; //TODO
	private static final Map<String, Font> QUIZ_FONTS;
	static {
		QUIZ_FONTS = new HashMap<String, Font>();
		QUIZ_FONTS.put("QUIZ_HEADER_FONT", Font.loadFont(MetaDataFactory_MOTS.class.getResource("/fonts/AKENATEN.TTF").toExternalForm(),96));		
		QUIZ_FONTS.put("QUIZ_TEXT_FONT", Font.loadFont(MetaDataFactory_MOTS.class.getResource("/fonts/AKENATEN.TTF").toExternalForm(),36));
		QUIZ_FONTS.put("QUIZ_SYMBOL_FONT", Font.loadFont(MetaDataFactory_MOTS.class.getResource("/fonts/OldEgyptGlyphs.ttf").toExternalForm(),192));		
		QUIZ_FONTS.put("QUIZ_LABEL_FONT", Font.loadFont(MetaDataFactory_MOTS.class.getResource("/fonts/AKENATEN.TTF").toExternalForm(),28));
	}
	
	/**
	 * Hidden constructor, use static getMetaData method.
	 */
	private MetaDataFactory_MOTS(){
	}
	
	/**
	 * Singleton pattern returns a static reference to this EnemyFactory.
	 */
	public static MetaDataFactory_MOTS getInstance()
	{
		if (instance == null){
			instance = new MetaDataFactory_MOTS();
		}
		return instance;
	}	
	
	/** Returns an instance of the specified MetaData. */
	@Override
	public MetaData getMetaData(int levelNumber){
		MetaData metaDataToReturn = null;
		QuizCard quizCard = null;
		switch (levelNumber){
		case 0:
			quizCard = new QuizCard(
					"The ancient Egyptians lived more than 3000 years ago in Africa. We know a lot about them because they were the first people in the world to use symbols as writing, called Hieroglyphs.", 
					"How long ago did the ancient Egyptians live?", 
					"30 years ago", "300 years ago", "3,000 years ago", "C");
			metaDataToReturn = new MetaData(GAME_TITLE_NAME, HISTORY_PERIOD, COLLECTABLE_NAME, 
					levelNumber, META_DATA_SYMBOLS[0], META_DATA_LABELS[0], QUIZ_FONTS, quizCard);
			break;
		case 1:
			quizCard = new QuizCard(
					"The ancient Egyptians worshipped over 1,000 different gods and goddesses. The most important gods were Ra the god of the sun, Isis the goddess of wisdom, Osiris the god of the underworld and Horus the younger, son of Osiris and Isis.", 
					"Who was worshipped as the god of the sun?", 
					"Ra", "Ar", "Ba", "A");
			metaDataToReturn = new MetaData(GAME_TITLE_NAME, HISTORY_PERIOD, COLLECTABLE_NAME, 
					levelNumber, META_DATA_SYMBOLS[1], META_DATA_LABELS[1], QUIZ_FONTS, quizCard);
			break;
		case 2:
			quizCard = new QuizCard(
					"The ancient Egyptians were ruled by kings and queens called Pharaohs and they worshipped the Pharaoh like a god, who they believed to be the incarnation of the god Horus.", 
					"What did the Egyptians call their king or queen?", 
					"Emperor", "Monarch", "Pharaoh", "C");
			metaDataToReturn = new MetaData(GAME_TITLE_NAME, HISTORY_PERIOD, COLLECTABLE_NAME, 
					levelNumber, META_DATA_SYMBOLS[2], META_DATA_LABELS[2], QUIZ_FONTS, quizCard);
			break;
		case 3:
			quizCard = new QuizCard(
					"The ancient Egyptians built enormous stone structures called pyramids, which were used as tombs to bury the dead Pharaoh. The largest pyramid in the world is called the Great Pyramid of Giza, and was built to bury Pharaoh Khufu in 2560 BC.", 
					"Why did the Egyptians build the pyramids?",
					"Stadiums", "Tombs", "Offices", "B");
			metaDataToReturn = new MetaData(GAME_TITLE_NAME, HISTORY_PERIOD, COLLECTABLE_NAME, 
					levelNumber, META_DATA_SYMBOLS[3], META_DATA_LABELS[3], QUIZ_FONTS, quizCard);
			break;
		case 4:
			quizCard = new QuizCard(
					"When a Pharaoh died they were associated with the god Osiris, god of the underworld. The priests would remove all of the Pharaoh's internal organs and wrap the body in cloth in a process called mummification.", 
					"What do we call a body that has been mummified?", 
					"Yummy", "Dummy", "Mummy", "C");
			metaDataToReturn = new MetaData(GAME_TITLE_NAME, HISTORY_PERIOD, COLLECTABLE_NAME, 
					levelNumber, META_DATA_SYMBOLS[4], META_DATA_LABELS[4], QUIZ_FONTS, quizCard);
			break;
		default:
			quizCard = new QuizCard(
					"The ancient Egyptians lived more than 3000 years ago in Africa. We know a lot about them because they were the first people in the world to use symbols as writing, called Hieroglyphs.", 
					"How long ago did the ancient Egyptians live?", 
					"30 years ago", "300 years ago", "3,000 years ago", "C");
			metaDataToReturn = new MetaData(GAME_TITLE_NAME, HISTORY_PERIOD, COLLECTABLE_NAME, 
					levelNumber, META_DATA_SYMBOLS[0], META_DATA_LABELS[0], QUIZ_FONTS, quizCard);
			break;
		}
		return metaDataToReturn;
	}
}
