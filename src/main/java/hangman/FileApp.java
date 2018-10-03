package hangman;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileApp {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\Asus ROG\\Desktop\\Java Projekty\\hangman\\src\\main\\resources\\test");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }
}
