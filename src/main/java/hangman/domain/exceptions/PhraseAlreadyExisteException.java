package hangman.domain.exceptions;

public class PhraseAlreadyExisteException extends GameException {
    public PhraseAlreadyExisteException(String message) {
        super(message);
    }
}
