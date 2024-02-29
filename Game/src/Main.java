import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Initialising variables
        int lives = 5;
        //For storing which letters the user has inputted already
        ArrayList<String> letters = new ArrayList<String>();
        ArrayList<String> words = getWords();
        String word = randomWord(words);
        assert word != null;
        String hidden = "_".repeat(word.length());

        //Greeting to the user
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! Please enter your name:");
        String name = input.next();
        System.out.printf("Hello %s, Welcome to Hangman!", name);
    }

    //This function reads the text file in the project folder with 100k words in it
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

    //This function gets a random word from the ArrayList of 100k words
    public static String randomWord(ArrayList<String> words) {
        try {
            Random rand = new Random();
            String word;
            int number = rand.nextInt(100000);
            word = words.get(number);
            return word;
        } catch (Exception e) {
            System.out.println("Cannot find text file!");
            return null;
        }
    }

    //This function prints the user lives and word progress the user has made
    public static void gameBoard(int lives, String hidden) {
        System.out.printf("Lives: %d%n", lives);
        System.out.println(hidden);
        System.out.println("Enter your guess:");
        Scanner input = new Scanner(System.in);
        String guess = "null";
        try {
            while (guess.length() != 1) {
                guess = input.next();
                System.out.println("Please enter a single character.");
            }
        }
    }

    //This function takes in the user's guess
    public static String guess(char guess, String hidden) {
        hidden.replace('_', guess);
        return hidden;
    }

}