package hangman.application;

import hangman.domain.GameFactory;
import hangman.domain.model.Game;
import hangman.domain.model.GameStatus;
import hangman.infrastructure.memory.InMemoryPhraseRepository;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleApplication {

    private ConsoleViews consoleViews;
    private GameFactory gameFactory;

    public ConsoleApplication() {
        this.gameFactory = new GameFactory(new InMemoryPhraseRepository(Arrays.asList("Ala ma kota", "Wielkopolska")));
        this.consoleViews = new ConsoleViews(new Scanner(System.in));
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
//        String phrase = consoleViews.addPhraseMessage();
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
