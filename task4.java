import java.util.*;

public class task4 {
    public static void main(String[] args) {
        
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        quizQuestions.add(new QuizQuestion("Which is the first atomic station in India?", Arrays.asList(" Tarapore Power Station", " Rajasthan Power Station", "Madras Power Station", " Narora Power Station"), 1));
        quizQuestions.add(new QuizQuestion("What is 2 + 2?", Arrays.asList("3", "4", "5", "6"), 2));
        quizQuestions.add(new QuizQuestion("Which planet is known as the Red Planet?", Arrays.asList("Mars", "Venus", "Jupiter", "Saturn"), 1));
        quizQuestions.add(new QuizQuestion("Which is the highest award in India?", Arrays.asList("Bharat Ratna", "Padma Vubhushan", "Padma Bhushan", "Padma Shri"), 1));
        
        Quiz quiz = new Quiz(quizQuestions);
        quiz.startQuiz();
    }
}

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctAnswer;

    public QuizQuestion(String question, List<String> options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

class Quiz {
    private List<QuizQuestion> questions;
    private int userScore;
    private List<Integer> userAnswers;
    private static final int TIME_LIMIT_SECONDS = 10;
    private Scanner scanner;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.userScore = 0;
        this.userAnswers = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");

        for (QuizQuestion question : questions) {
            displayQuestion(question);
            processAnswer(question);
        }

        displayResult();
        displaySummary();

        scanner.close();
    }

    private void displayQuestion(QuizQuestion question) {
        System.out.println("\n" + question.getQuestion());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.println("You have " + TIME_LIMIT_SECONDS + " seconds to answer.");
    }

    private void processAnswer(QuizQuestion question) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                timer.cancel();
                userAnswers.add(0);  
            }
        };

        timer.schedule(task, TIME_LIMIT_SECONDS * 1000);

        
        try {
            while (!scanner.hasNextInt()) {
                Thread.sleep(1000);
            }
            int userAnswer = scanner.nextInt();
            timer.cancel();  

            if (userAnswer >= 1 && userAnswer <= question.getOptions().size()) {
                userAnswers.add(userAnswer);

                if (userAnswer == question.getCorrectAnswer()) {
                    System.out.println("Correct!\n");
                    userScore++;
                } else {
                    System.out.println("Incorrect. The correct answer was: " + question.getOptions().get(question.getCorrectAnswer() - 1) + "\n");
                }
            } else {
                System.out.println("Invalid answer. Skipping question.\n");
                userAnswers.add(0);  
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void displayResult() {
        System.out.println("Quiz completed!");
        System.out.println("Your final score: " + userScore + "/" + questions.size());
    }

    private void displaySummary() {
        System.out.println("\nSummary of Answers:");

        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion question = questions.get(i);
            int userAnswer = userAnswers.get(i);

            System.out.println("Question " + (i + 1) + ": " + question.getQuestion());
            System.out.println("Your Answer: " + (userAnswer == 0 ? "Skipped" : question.getOptions().get(userAnswer - 1)));

            if (userAnswer == question.getCorrectAnswer()) {
                System.out.println("Result: Correct\n");
            } else if (userAnswer == 0) {
                System.out.println("Result: Skipped\n");
            } else {
                System.out.println("Result: Incorrect. The correct answer was: " + question.getOptions().get(question.getCorrectAnswer() - 1) + "\n");
            }
        }
    }
}
