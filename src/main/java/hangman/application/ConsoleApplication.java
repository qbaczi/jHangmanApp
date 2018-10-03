package hangman.application;

import hangman.domain.ForbiddenWordsValidator;
import hangman.domain.GameFactory;
import hangman.domain.PhraseService;
import hangman.domain.exceptions.ForbiddenWordsInPhrasException;
import hangman.domain.exceptions.PhraseAlreadyExisteException;
import hangman.domain.model.Game;
import hangman.domain.model.GameStatus;
import hangman.infrastructure.memory.InMemoryForbiddenWordsRepository;
import hangman.infrastructure.memory.InMemoryPhraseRepository;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleApplication {

    private ConsoleViews consoleViews;
    private GameFactory gameFactory;
    private PhraseService phraseService;

    public ConsoleApplication() {
        InMemoryPhraseRepository phraseRepository = new InMemoryPhraseRepository(Arrays.asList("Ala ma kota", "Wielkopolska"));
        ForbiddenWordsValidator forbiddenWordsValidator = new ForbiddenWordsValidator(new InMemoryForbiddenWordsRepository(Arrays.asList("zlodziej", "oszust")));
        this.gameFactory = new GameFactory(phraseRepository);
        this.consoleViews = new ConsoleViews(new Scanner(System.in));
        this.phraseService = new PhraseService(phraseRepository, forbiddenWordsValidator);
    }

    public void start() {

        boolean flag = true;
        while (flag) {
            Integer menuOption = consoleViews.mainMenu();
            switch (menuOption) {
                case 1:
                    startGame();
                    break;
                case 2:
                    addPhrase();
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void addPhrase() {
        String phrase = consoleViews.addPhraseMessage();
        try {
            phraseService.addPhrase(phrase);
            consoleViews.displayAddedSuccesfully(phrase);
        } catch (ForbiddenWordsInPhrasException e) {
            consoleViews.displayPhraseContainsForbiddenWords();
        } catch (PhraseAlreadyExisteException e) {
            consoleViews.displayPhraseAlreadyExists();
        }
    }

    private void startGame() {
        Game game = gameFactory.createGame();
        game.setStatus(GameStatus.ACTIVE);
        do {
            char nextLetter = consoleViews.displayGame(game);
            boolean result = game.addNextLetter(nextLetter);
            if (!result) {
                consoleViews.displayWrongLetterAdded();
            }

        } while (game.getStatus() == GameStatus.ACTIVE);

        if (game.getStatus() == GameStatus.WON) {
            consoleViews.displayGameWon();
        } else {
            consoleViews.displayGameLose();
        }

    }
}
