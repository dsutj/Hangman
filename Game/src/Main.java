import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Initialising variables
        int lives = 5;
        ArrayList<String> letters = new ArrayList<String>();
        ArrayList<String> words = getWords();

        //Greeting to the user
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! Please enter your name:");
        String name = input.next();
        System.out.printf("Hello %s, Welcome to Hangman!", name);

    }

    public static ArrayList<String> getWords() {
        ArrayList<String> words = new ArrayList<String>();

        try {
            File file = new File("words.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String word = myReader.nextLine();
                if (word.charAt(0) != '#') {
                    words.add(word.trim());
                }
            }
        }   catch (FileNotFoundException e) {
            System.out.println("No file found!!!");
        }

        return words;
    }

    public static int randomWord(ArrayList<String> words) {
        Random rand = new Random();
        String word;

    }
}