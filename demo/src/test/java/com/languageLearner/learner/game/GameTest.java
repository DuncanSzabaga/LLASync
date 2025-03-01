package com.languageLearner.learner.game;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import com.learner.model.Difficulty;
import com.learner.model.Game;
import com.learner.model.GameManager;
import com.learner.model.innerdata.GameCategory;
import com.learner.model.innerdata.GameInfo;
import com.learner.model.innerdata.TextObject;
import com.learner.model.loadwrite.DataConstants;
import com.learner.model.loadwrite.DataLoader;
import com.learner.model.questions.MultipleChoiceQuestion;
import com.learner.model.questions.Question;
import com.learner.model.questions.QuestionType;

public class GameTest {
    @Test
    public void testFindGameByUUID() {
        DataLoader.loadGameData(DataConstants.GAME_DATA_FILE_JUNIT);
        GameManager gameManager = GameManager.getInstance();
        System.out.println(gameManager.toString());
        UUID languageUUID = UUID.fromString("8ce4fefc-a539-4546-9d7e-0ac8778f8de5");
        Game colorsGame = gameManager.findGameByUUID(languageUUID);

        assertNotNull(colorsGame);
    }

    @Test
    public void testAddGame() {
        GameManager gameManager = GameManager.getInstance();
        ArrayList<String> newList = new ArrayList<String>();
        ArrayList<TextObject> newTextList = new ArrayList<TextObject>();
        ArrayList<Question> newQuestionList = new ArrayList<Question>();
        GameInfo newGameInfo = new GameInfo("", "", newList, "", "", "", UUID.randomUUID());
        UUID newGameUUID = UUID.randomUUID();
        Game newGame = new Game(UUID.fromString("1bafb0ae-3462-4ec3-9cc2-a98ff2898e72"), "New Game", Difficulty.EASY, newGameUUID, GameCategory.WORD, newGameInfo, newTextList, newQuestionList);
        gameManager.addGame(newGame);

        assertNotNull(gameManager.findGameByUUID(newGameUUID));
    }

    @Test
    public void testAddQuestion() {
        GameManager gameManager = GameManager.getInstance();
        ArrayList<String> newList = new ArrayList<String>();
        ArrayList<TextObject> newTextList = new ArrayList<TextObject>();
        ArrayList<Question> newQuestionList = new ArrayList<Question>();
        GameInfo newGameInfo = new GameInfo("", "", newList, "", "", "", UUID.randomUUID());
        UUID newGameUUID = UUID.randomUUID();
        Game newGame = new Game(UUID.fromString("1bafb0ae-3462-4ec3-9cc2-a98ff2898e72"), "New Game", Difficulty.EASY, newGameUUID, GameCategory.WORD, newGameInfo, newTextList, newQuestionList);

        UUID questionUUID = UUID.randomUUID();
        ArrayList<String> newOptionsList = new ArrayList<String>();
        newOptionsList.add("a"); newOptionsList.add("a"); newOptionsList.add("a"); newOptionsList.add("a");
        Question newQuestion = new MultipleChoiceQuestion(questionUUID, newGameUUID, UUID.fromString("1bafb0ae-3462-4ec3-9cc2-a98ff2898e72"), "", newOptionsList);
        newGame.addQuestion(newQuestion);

        assertNotNull(newGame.getQuestion(questionUUID));
    }

    @Test
    public void testPullQuestions() {
        DataLoader.loadGameData(DataConstants.GAME_DATA_FILE_JUNIT);
        GameManager gameManager = GameManager.getInstance();
        Game colorsGame = gameManager.findGameByUUID(UUID.fromString("8ce4fefc-a539-4546-9d7e-0ac8778f8de5"));
        colorsGame.pullQuestions();

        assertNotNull(colorsGame.getPulledQuestions());
    }

    @Test
    public void testAddQuestionsByType() {
        DataLoader.loadGameData(DataConstants.GAME_DATA_FILE_JUNIT);
        GameManager gameManager = GameManager.getInstance();
        Game colorsGame = gameManager.findGameByUUID(UUID.fromString("8ce4fefc-a539-4546-9d7e-0ac8778f8de5"));
        colorsGame.addQuestionsByType(QuestionType.MULTIPLE_CHOICE, 1, 0);

        assertNotNull(colorsGame.getPulledQuestions());
    }
}