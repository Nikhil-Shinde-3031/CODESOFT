import java.util.*;

public class task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of subjects: ");
        int totalsub = sc.nextInt();

        int[] marks = new int[totalsub];

        for (int i = 0; i < totalsub; i++) 
        {
            do {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                marks[i] = sc.nextInt();
            
                // Validate that the entered marks are within the valid range (0 to 100)
                if (marks[i] < 0 || marks[i] > 100) {
                    System.out.println("Invalid input. Marks must be between 0 and 100.");
                }
            } while (marks[i] < 0 || marks[i] > 100);
            
        }

        int totalMarks = 0;

        for (int i=0;i<totalsub;i++) {
            totalMarks = totalMarks+ marks[i];
        }

        double averagePercentage = (double) totalMarks / totalsub;

        char grade;

        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("\nResults:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

    }
}
