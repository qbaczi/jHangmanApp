package hangman.infrastructure.file;

import hangman.domain.port.ForbiddenWordsRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileBaseForbiddenWordsRepository implements ForbiddenWordsRepository {

    private File file;
    private Scanner scanner;
    private List<String> forbiddenWords;

    public FileBaseForbiddenWordsRepository(File file) throws FileNotFoundException {
        this.scanner = new Scanner(file);

        initForbiddenWords();
    }

    private void initForbiddenWords() {
        this.forbiddenWords = new ArrayList();
        while (scanner.hasNextLine()) {
            String forbiddenWord = scanner.nextLine();
            forbiddenWords.add(forbiddenWord);
        }
    }

    @Override
    public List<String> findAll() {
        return new ArrayList<>(forbiddenWords);
    }
}
