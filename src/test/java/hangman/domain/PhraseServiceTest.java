package hangman.domain;

import hangman.domain.exceptions.ForbiddenWordsInPhrasException;
import hangman.domain.exceptions.PhraseAlreadyExisteException;
import hangman.domain.port.PhraseRepository;
import org.junit.Test;
import org.mockito.Mockito;

public class PhraseServiceTest {


    @Test
    public void addPhraseShouldAddNewPhrase() throws Exception{

        ForbiddenWordsValidator forbiddenWordsValidator = Mockito.mock(ForbiddenWordsValidator.class);
        PhraseRepository phraseRepository = Mockito.mock(PhraseRepository.class);
        Mockito.when(phraseRepository.contains(Mockito.anyString())).thenReturn(false);
        Mockito.when(forbiddenWordsValidator.validate(Mockito.anyString())).thenReturn(true);
        PhraseService phraseService = new PhraseService(phraseRepository, forbiddenWordsValidator);

        phraseService.addPhrase("phrase with forbidden word");

        Mockito.verify(phraseRepository, Mockito.times(1)).save("phrase with forbidden word");
    }



    @Test(expected = PhraseAlreadyExisteException.class)
    public void addPhraseShouldThrowAnExceptionWhenPhraseAlreadyExists() throws Exception{

        ForbiddenWordsValidator forbiddenWordsValidator = Mockito.mock(ForbiddenWordsValidator.class);
        PhraseRepository phraseRepository = Mockito.mock(PhraseRepository.class);
        Mockito.when(phraseRepository.contains(Mockito.anyString())).thenReturn(true);
        Mockito.when(forbiddenWordsValidator.validate(Mockito.anyString())).thenReturn(true);
        PhraseService phraseService = new PhraseService(phraseRepository, forbiddenWordsValidator);

        phraseService.addPhrase("phrase with forbidden word");
    }



    @Test(expected = ForbiddenWordsInPhrasException.class)
    public void addPhraseShouldThrowAnExceptionWhenPhraseContainForbiddenWords() throws Exception {

        ForbiddenWordsValidator forbiddenWordsValidator = Mockito.mock(ForbiddenWordsValidator.class);
        PhraseRepository phraseRepository = Mockito.mock(PhraseRepository.class);
        Mockito.when(phraseRepository.contains(Mockito.anyString())).thenReturn(false);
        Mockito.when(forbiddenWordsValidator.validate(Mockito.anyString())).thenReturn(false);
        PhraseService phraseService = new PhraseService(phraseRepository, forbiddenWordsValidator);

        phraseService.addPhrase("phrase with forbidden word");

    }
}
