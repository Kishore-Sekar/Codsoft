import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    String question;
    String[] options;
    int correctAnswerIndex;

    public Question(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}

public class QuizApplication {
    private static final int TIME_LIMIT = 10; // seconds
    private static List<Question> questions = new ArrayList<>();
    private static int score = 0;

    public static void main(String[] args) {
        // Sample questions
        questions.add(new Question("What is the capital of France?",
                new String[]{"A. Berlin", "B. Madrid", "C. Paris", "D. Rome"}, 2));
        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[]{"A. Earth", "B. Mars", "C. Jupiter", "D. Venus"}, 1));
        questions.add(new Question("What is the largest mammal in the world?",
                new String[]{"A. Elephant", "B. Blue Whale", "C. Giraffe", "D. Shark"}, 1));
        questions.add(new Question("What is the square root of 64?",
                new String[]{"A. 6", "B. 7", "C. 8", "D. 9"}, 2));
        questions.add(new Question("Which element has the chemical symbol 'O'?",
                new String[]{"A. Oxygen", "B. Gold", "C. Silver", "D. Iron"}, 0));

        // Start the quiz
        takeQuiz();
        
        // Display results
        displayResults();
    }

    private static void takeQuiz() {
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.question);
            for (String option : question.options) {
                System.out.println(option);
            }

            long startTime = System.currentTimeMillis();
            Integer userAnswer = null;

            while (true) {
                if (scanner.hasNextInt()) {
                    userAnswer = scanner.nextInt();
                    if (userAnswer >= 0 && userAnswer < 4) { // Validating answer index (0 to 3)
                        break;
                    } else {
                        System.out.println("Invalid option! Please enter a valid option (0-3).");
                    }
                }

                // Check if time limit exceeded
                if ((System.currentTimeMillis() - startTime) / 1000 >= TIME_LIMIT) {
                    System.out.println("Time's up! The correct answer was: " +
                            question.options[question.correctAnswerIndex]);
                    userAnswer = null; // Indicate that time is up
                    break;
                }
            }

            if (userAnswer != null && userAnswer == question.correctAnswerIndex) {
                System.out.println("Correct!");
                score++;
            } else if (userAnswer != null) {
                System.out.println("Incorrect! The correct answer was: " +
                        question.options[question.correctAnswerIndex]);
            }
        }
        scanner.close();
    }

    private static void displayResults() {
        System.out.println("\n--- Quiz Completed ---");
        System.out.println("Your score: " + score + "/" + questions.size());
        System.out.println("Thank you for participating!");
    }
}

