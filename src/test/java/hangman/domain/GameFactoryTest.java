package hangman.domain;

import hangman.domain.model.Game;
import hangman.domain.model.GameStatus;
import hangman.domain.port.PhraseRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class GameFactoryTest {
    @Test
    public void shouldCreateGameInstanceWithPhrase(){

        PhraseRepository phraseRepository = Mockito.mock(PhraseRepository.class);
        Mockito.when(phraseRepository.getRandomPhrase()).thenReturn("test frazy");
        GameFactory gameFactory = new GameFactory(phraseRepository);

        Game game = gameFactory.createGame();

        Assert.assertEquals("test frazy", game.getPhrase());
        Assert.assertArrayEquals(new char[]{'_','_','_','_',' ','_','_','_','_','_',}, game.getPhraseStatus());
        Assert.assertEquals(GameStatus.NEW, game.getStatus());
    }


}
