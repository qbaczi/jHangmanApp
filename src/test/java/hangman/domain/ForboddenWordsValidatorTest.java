package hangman.domain;

import hangman.domain.port.ForbiddenWordsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class ForboddenWordsValidatorTest {

    private ForbiddenWordsRepository forbiddenWordsRepository;
    private ForbiddenWordsValidator forbiddenWordsValidator;

    @Before
    public void before() {
        forbiddenWordsRepository = Mockito.mock(ForbiddenWordsRepository.class);
        forbiddenWordsValidator = new ForbiddenWordsValidator(forbiddenWordsRepository);
    }

    private void mockRepository(List<String> forbiddenWords) {
        Mockito.when(forbiddenWordsRepository.findAll()).thenReturn(forbiddenWords);
    }

    @Test
    public void validateShouldReturnTrueWhenPhraseDoesNotContainsForbiddenWords() {

        mockRepository(Arrays.asList("forbidden"));

        boolean isValid = forbiddenWordsValidator.validate("test phrase");

        Assert.assertTrue(isValid);
    }

    @Test
    public void validateShouldReturnFalseWhenPhraseContainsForbiddenWords() {

        mockRepository(Arrays.asList("forbidden"));

        boolean isValid = forbiddenWordsValidator.validate("test phrase with forbidden word");

        Assert.assertFalse(isValid);
    }

    @Test
    public void validateShouldReturnFalseWhenPhraseContainsForbiddenWordsWhitWhitespaces() {

        mockRepository(Arrays.asList("forbidden"));

        boolean isValid = forbiddenWordsValidator.validate("test phrase with f o r b i d d e n word");

        Assert.assertFalse(isValid);
    }

    @Test
    public void validateShouldReturnFalseWhenPhraseContainsUppercaseForbiddenWordsWhitWhitespaces() {

        mockRepository(Arrays.asList("forbidden"));

        boolean isValid = forbiddenWordsValidator.validate("test phrase with FORBIDDEN word");

        Assert.assertFalse(isValid);
    }

    @Test
    public void validateShouldReturnFalseWhenPhraseContainsForbiddenWordsCombainedMultipleWords() {

        mockRepository(Arrays.asList("forbidden word"));

        boolean isValid = forbiddenWordsValidator.validate("test phrase with FORBIDDEN word");

        Assert.assertFalse(isValid);
    }
}
