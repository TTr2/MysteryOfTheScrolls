/**
 * 
 */
package game.models;

import java.util.Map;

import javafx.scene.text.Font;
import quiz.models.QuizCard;

/**
 * MetaData objects are a repository of details stored by Collectable items, for use by the quiz graphics 
 * engine to display an icon for each collectable in the game and to store the HistoryQuiz info.
 * 
 * @author Tim Tyler / stb082 / 2nd Year UoS (workshop 1)
 *
 */
public class MetaData {

	private String gameTitle;
	private String historyPeriod;
	private String collectableName;
	private int levelNumber;
	private String metaDataSymbol;
	private String metaDataLabel;
	private Map<String, Font> quizFontsMap;
	private QuizCard quizCard;
	
	/**
	 * Stores text, image and QuizCard details for retrieval by the Quiz game graphics engine. 
	 */
	public MetaData(String gameTitle, String historyPeriod, String collectableName, int levelNumber, 
			String metaDataSymbol, String metaDataLabel, Map<String, Font> quizFontsMap, QuizCard quizCard)
	{
		this.gameTitle = gameTitle;
		this.historyPeriod = historyPeriod;
		this.collectableName = collectableName;
		this.levelNumber = levelNumber;
		this.metaDataSymbol = metaDataSymbol;
		this.metaDataLabel = metaDataLabel;
		this.quizFontsMap = quizFontsMap;
		this.quizCard = quizCard;
	}

	public String getGameTitle() {
		return gameTitle;
	}
	public String getHistoryPeriod() {
		return historyPeriod;
	}
	public String getCollectableName() {
		return collectableName;
	}
	public int getLevelNumber() {
		return levelNumber;
	}
	public String getMetaDataSymbol() {
		return metaDataSymbol;
	}
	public String getMetaDataLabel() {
		return metaDataLabel;
	}
	public Font getQuizFont(String fontName) {
		if (quizFontsMap.containsKey(fontName)){
			return quizFontsMap.get(fontName);
		}
		else return Font.getDefault();
	}
	public QuizCard getQuizCard() {
		return quizCard;
	}
}
