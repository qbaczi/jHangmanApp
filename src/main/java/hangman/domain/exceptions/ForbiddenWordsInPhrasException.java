package hangman.domain.exceptions;

public class ForbiddenWordsInPhrasException extends GameException {
    public ForbiddenWordsInPhrasException(String message) {
        super(message);
    }
}
