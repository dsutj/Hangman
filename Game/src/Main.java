import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Initialising variables
        boolean complete = false;
        User user = new User();
        //For storing which letters the user has inputted already
        ArrayList<String> words = getWords();
        String word;
        String hidden;

        //Greeting to the user
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! Please enter your name:");
        //String name = input.next();
        user.setName(input.next());
        System.out.printf("Hello %s, welcome to Hangman!%n", user.name);

        while (! complete) {
            word = randomWord(words);
            assert word != null;
            hidden = "_".repeat(word.length());
            user.newGame();
            while (! hidden.equals(word) && user.lives != 0) {
                String answer = "null";
                gameBoard(user.lives, hidden);

                while (true) {
                    while (answer.length() != 1) {
                        try {
                            answer = input.next();
                            if (answer.length() != 1) {
                            System.out.println("Please enter a single character.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input.");
                        }
                    }
                    if (!user.containsLetter(answer.charAt(0))) {
                        break;
                    }   else {
                        System.out.println("Please input a new letter!");
                        answer = "null";
                    }
                }

                char guess = answer.toLowerCase().charAt(0);
                user.addLetter(guess);
                if (word.indexOf(guess) != -1) {
                    hidden = guess(guess, hidden, word);
                    System.out.println("Correct!");
                } else {
                    System.out.println("Incorrect!");
                    user.lives -= 1;
                }
            }

            if (user.lives == 0) {
                System.out.println("Bad luck! Game over! The word was " + word);
            }   else {
                System.out.println("Congratulations! The word was " + word);
                user.addWin();
            }

            System.out.println(user.getWins());
            System.out.println("Try again?: (Y/N)");
            String answer = input.next().toLowerCase();
            char reply = answer.charAt(0);
            while (reply != 'y' && reply != 'n') {
                System.out.println("Please answer with a Y or N.");
                answer = input.next().toLowerCase();
                reply = answer.charAt(0);
            }

            if (reply == 'n') {
                System.out.println("Thanks for playing!");
                complete = true;
            }

            System.out.println("New Game!");
        }

    }

    public static class User {
        public int difficulty = 0;
        public String name = null;
        public int wins = 0;
        public int lives = 5;
        public ArrayList<Character> letters = new ArrayList<Character>();
        public User() {}

        public void setLives(int lives) {
            this.lives = lives;
        }

        public int getLives() {
            return this.lives;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void addWin() {
            this.wins += 1;
        }

        public String getWins() {
            return String.format("You have %d wins!",this.wins);
        }

        public boolean containsLetter(char letter) {
            return this.letters.contains(letter);
        }

        public void addLetter(char letter) {
            this.letters.add(letter);
        }

        public void newGame() {
            this.letters.clear();
            this.lives = 5;
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
            String word = "no";
            while (word.length() <= 5) {
            int number = rand.nextInt(100000);
            word = words.get(number);
            }
            return word.toLowerCase();
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


}