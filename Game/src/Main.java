import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Initialising variables
        int lives = 5;
        boolean complete = false;

        //For storing which letters the user has inputted already
        ArrayList<Character> letters = new ArrayList<Character>();
        ArrayList<String> words = getWords();
        String word = randomWord(words);
        assert word != null;
        String hidden = "_".repeat(word.length());

        //Greeting to the user
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! Please enter your name:");
        String name = input.next();
        System.out.printf("Hello %s, welcome to Hangman!%n", name);

        while (! complete) {
            while (! hidden.equals(word) && lives != 0) {
                String answer = "null";
                gameBoard(lives, hidden);

                while (true) {
                    while (answer.length() != 1) {
                        try {
                            answer = input.next();
                            System.out.println("Please enter a single character.");
                        } catch (Exception e) {
                            System.out.println("Invalid input.");
                        }
                    }
                    if (!letters.contains(answer.charAt(0))) {
                        break;
                    }   else {
                        System.out.println("Please input a new letter!");
                    }
                }

                char guess = answer.charAt(0);
                letters.add(guess);
                if (word.indexOf(guess) != -1) {
                    hidden = guess(guess, hidden, word);
                    System.out.println("Correct!");
                } else {
                    System.out.println("Incorrect!");
                    lives -= 1;
                }
            }

            if (lives == 0) {
                System.out.println("Bad luck! Game over! The word was " + word);
                complete = tryAgain();
            }   else {
                System.out.println("Congratulations! The word was " + word);
                complete = tryAgain();
            }
        }

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
    }

    //This function takes in the user's guess
    public static String guess(char guess, String hidden, String word) {
        ArrayList<Integer> inds = new ArrayList<Integer>();
        for (int i = 0;i < word.length();i++) {
            if (guess == word.charAt(i)) {
                inds.add(i);
            }
        }
        char[] hidden_a = hidden.toCharArray();
        for (int ind:inds) {
            hidden_a[ind] = guess;
        }
        hidden = String.valueOf(hidden_a);
        return hidden;
    }

    //This function asks if the user wants to try again
    public static boolean tryAgain() {
        Scanner input = new Scanner(System.in);
        String answer = input.next().toLowerCase();
        char reply = answer.charAt(0);
        System.out.println("Try again?: (Y/N");
        while (reply != 'y' && reply != 'n') {
            System.out.println("Please answer with a Y or N.");
            answer = input.next().toLowerCase();
            reply = answer.charAt(0);
        }
        if (reply == 'y') {
            return false;
        }
        System.out.println("Thanks for playing!");
        return true;
    }

}