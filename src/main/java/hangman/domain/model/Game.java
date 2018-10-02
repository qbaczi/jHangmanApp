package hangman.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;


@Data
@Builder
public class Game {
    private String phrase;
    private char[] phraseStatus;
    private Instant startDate;
    private GameStatus status;
    private Integer leftAttempts;

    public boolean addNextLetter(char letter) {
        boolean flag = false;
        char[] originalPhrase = phrase.toCharArray();
        boolean alreadyAdded = isAlreadyAdded(letter);
        for (int i = 0; i < originalPhrase.length && !alreadyAdded; i++) {
            if (Character.toLowerCase(letter) == Character.toLowerCase(originalPhrase[i])) {
                flag = true;
                phraseStatus[i] = originalPhrase[i];
            }
        }
        if (!flag) {
            leftAttempts--;
            if (leftAttempts <= 0) {
                status = GameStatus.LOSE;
            }
        } else if (isGameWon()) {
            status = GameStatus.WON;
        }
        return flag;
    }

    private boolean isGameWon() {
        for (char c : phraseStatus) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    private boolean isAlreadyAdded(char letter) {
        for (char c : phraseStatus) {
            if (letter == c) {
                return true;
            }
        }
        return false;
    }
}
