import java.util.*;

public class task1{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 9;
        int score = 0;

        System.out.println("Welcome to the Number Game !");

        do {
            int numberToGuess = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("I have selected a number between " + minRange + " and " + maxRange + ". Guess it!");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    guessedCorrectly = true;
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    score += maxAttempts - attempts + 1; 
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low!  try again");
                } else {
                    System.out.println("Too high! try again");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + numberToGuess);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;
            }

        } while (true);

        System.out.println("Game Over. Your total score is: " + score);
    }
}
