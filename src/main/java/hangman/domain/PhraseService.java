package hangman.domain;

import hangman.domain.exceptions.ForbiddenWordsInPhrasException;
import hangman.domain.exceptions.PhraseAlreadyExisteException;
import hangman.domain.port.PhraseRepository;

public class PhraseService {

    private PhraseRepository phraseRepository;
    private ForbiddenWordsValidator forbiddenWordsValidator;

    public PhraseService(PhraseRepository phraseRepository, ForbiddenWordsValidator forbiddenWordsValidator) {
        this.phraseRepository = phraseRepository;
        this.forbiddenWordsValidator = forbiddenWordsValidator;
    }



    public void addPhrase(String phrase) throws ForbiddenWordsInPhrasException, PhraseAlreadyExisteException {

    }
}
