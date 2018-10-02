package hangman.domain;

import hangman.domain.model.Game;
import hangman.domain.model.GameStatus;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;

public class GameTest {

    @Test
    public void shouldReturnTrueAndAddCharacterToPhraseStatusWhenLetterCanBeAdded() {
        Game game = createSampleGame();

        boolean result = game.addNextLetter('a');

        Assert.assertTrue(result);
        Assert.assertArrayEquals(new char[]{'A', '_', 'a', ' ', '_', 'a', ' ', '_', '_', '_', 'a'}, game.getPhraseStatus());
    }

    @Test
    public void shouldReturnFalseAndDoNotUpdatePhraseStatusAndDecrementLeftAttemptsWhenLetterIsNotPresentInPhrase() {
        Game game = createSampleGame();
        Integer leftAttempts = game.getLeftAttempts();

        boolean result = game.addNextLetter('b');

        Assert.assertFalse(result);
        Assert.assertArrayEquals(new char[]{'_', '_', '_', ' ', '_', '_', ' ', '_', '_', '_', '_'}, game.getPhraseStatus());
        Assert.assertEquals((Integer) (leftAttempts - 1), game.getLeftAttempts());
    }

    @Test
    public void shouldReturnFalseAndDoNotUpdatePhraseStatusAndDecrementLeftAttemptsWhenLetterIsAddedSecondTime() {

        Game game = createSampleGame();
        Integer leftAttempts = game.getLeftAttempts();
        game.addNextLetter('a');
        boolean result = game.addNextLetter('a');

        Assert.assertFalse(result);
        Assert.assertArrayEquals(new char[]{'A', '_', 'a', ' ', '_', 'a', ' ', '_', '_', '_', 'a'}, game.getPhraseStatus());
        Assert.assertEquals((Integer) (leftAttempts - 1), game.getLeftAttempts());
    }

    @Test
    public void addNextLettershouldChangeStatusToWonWhenLastLetterIsAdded() {
        //given
        Game game = crateSampleGameWhitCustomPhraseState((new char[]{'A', '_', 'a', ' ', 'm', 'a', ' ', 'k', 'o', 't', 'a'}));

        //when
        boolean result = game.addNextLetter('l');

        //then
        Assert.assertTrue(result);
        Assert.assertArrayEquals((new char[]{'A', 'l', 'a', ' ', 'm', 'a', ' ', 'k', 'o', 't', 'a'}),
                game.getPhraseStatus());
        Assert.assertEquals(GameStatus.WON, game.getStatus());


    }

    @Test
    public void addNextLettershouldChangeStatusToLoseWhenLeftAttemptsEqualsIsZero() {
        //given
        Game game = createSampleGameBuilder()
                .leftAttempts(1)
                .build();

        //when
        boolean result = game.addNextLetter('z');

        //then
        Assert.assertFalse(result);
        Assert.assertEquals((Integer) 0, game.getLeftAttempts());
        Assert.assertEquals(GameStatus.LOSE, game.getStatus());

    }

    private Game crateSampleGameWhitCustomPhraseState(char[] phraseStatus) {
        return createSampleGameBuilder()
                .phraseStatus(phraseStatus)
                .build();
    }

    private Game createSampleGame() {
        return createSampleGameBuilder()
                .build();
    }

    private Game.GameBuilder createSampleGameBuilder() {
        return Game.builder()
                .phrase("Ala ma kota")
                .phraseStatus(new char[]{'_', '_', '_', ' ', '_', '_', ' ', '_', '_', '_', '_',})
                .leftAttempts(5)
                .startDate(Instant.now())
                .status(GameStatus.ACTIVE);

    }
}
