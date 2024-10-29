import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int MAX_ATTEMPTS = 10;
    private static final Random random = new Random();
    private static int score = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            playAgain = false;
            int numberToGuess = random.nextInt(100) + 1;
            int attemptsLeft = MAX_ATTEMPTS;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I've selected a number between 1 and 100.");
            System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it.");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess < 1 || userGuess > 100) {
                    System.out.println("Please guess a number between 1 and 100.");
                    continue;
                }

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the number: " + numberToGuess);
                    score++;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);

                if (attemptsLeft == 0) {
                    System.out.println("Sorry, you've run out of attempts. The number was: " + numberToGuess);
                }
            }

            System.out.print("Would you like to play again? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thanks for playing! Your final score: " + score);
        scanner.close();
    }
}
