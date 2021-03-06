package hangman.infrastructure.memory;

import hangman.domain.port.PhraseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InMemoryPhraseRepository implements PhraseRepository {

    private List<String> phrases;
    private Random random;

    public InMemoryPhraseRepository(List<String> phrases) {
        this.phrases = new ArrayList(phrases);
        this.random = new Random();
    }

    @Override
    public String getRandomPhrase() {
        int randomIndex = random.nextInt(phrases.size());
        return phrases.get(randomIndex);
    }

    @Override
    public boolean contains(String phrase) {
        return phrases.contains(phrase);
    }

    @Override
    public void save(String phrase) {
        phrases.add(phrase);
    }
}
