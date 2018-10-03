package hangman.application;

import hangman.domain.model.Game;

import java.util.Scanner;

public class ConsoleViews {

    private Scanner scanner;

    public ConsoleViews(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer mainMenu() {
        System.out.println("1. Start");
        System.out.println("2. Dodaj fraze");
        System.out.println("0. Koniec");

        return getIntValue();
    }

    private Integer getIntValue() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public char displayGame(Game game) {
        char[] phraseStatus = game.getPhraseStatus();
        int leftAttempts = game.getLeftAttempts();
        String phraseStatusAsString = new String(phraseStatus);
        System.out.println(phraseStatusAsString + " ("+ leftAttempts + ") ");
        return scanner.nextLine().charAt(0);
    }

    public void displayWrongLetterAdded() {
        System.out.println("Podano niepoprawną litere");
    }

    public void displayGameWon() {
        System.out.println("Wygrałeś/Wygrałaś");
        waitForAction();
    }

    private void waitForAction() {
        System.out.println("Wciśnij Enter by zagrać ponwnie");
        scanner.nextLine();
    }

    public void displayGameLose() {
        System.out.println("Przegrałeś/Przegrałaś");
        waitForAction();
    }

    public String addPhraseMessage() {
        System.out.println("Podaj fraze");
        return scanner.nextLine();
    }

    public void displayPhraseContainsForbiddenWords() {
        System.out.println("Podana fraza zawiara zabronione słowa");
        waitForAction();
    }

    public void displayPhraseAlreadyExists() {
        System.out.println("Podana fraza już istnieje");
        waitForAction();
    }

    public void displayAddedSuccesfully(String phrase) {
        System.out.println("Pomyślnie dodano fraze " + phrase);
        waitForAction();
    }
}
