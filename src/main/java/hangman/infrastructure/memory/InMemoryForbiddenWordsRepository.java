package hangman.infrastructure.memory;

import hangman.domain.port.ForbiddenWordsRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryForbiddenWordsRepository implements ForbiddenWordsRepository {

    private List<String> forbidddenWords;

    public InMemoryForbiddenWordsRepository(List<String> forbidddenWords) {
        this.forbidddenWords = forbidddenWords;
    }

    @Override
    public List<String> findAll() {
        return new ArrayList<>(forbidddenWords);
    }


}
