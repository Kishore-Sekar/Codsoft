import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    String name;
    int[] marks;
    int totalMarks;
    double averagePercentage;
    char grade;

    public Student(String name, int numberOfSubjects) {
        this.name = name;
        this.marks = new int[numberOfSubjects];
        this.totalMarks = 0;
        this.averagePercentage = 0.0;
        this.grade = 'F'; // Default grade
    }

    public void calculateResults() {
        for (int mark : marks) {
            totalMarks += mark;
        }
        averagePercentage = (double) totalMarks / marks.length;

        // Assigning grade based on average percentage
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
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Total Marks: %d, Average Percentage: %.2f%%, Grade: %c",
                name, totalMarks, averagePercentage, grade);
    }
}

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();
        
        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.print("Enter the name of student " + (i + 1) + ": ");
            String name = scanner.nextLine();
            Student student = new Student(name, numberOfSubjects);

            // Input marks for each subject
            for (int j = 0; j < numberOfSubjects; j++) {
                while (true) {
                    System.out.print("Enter marks obtained in subject " + (j + 1) + " (0-100): ");
                    int mark = scanner.nextInt();
                    if (mark >= 0 && mark <= 100) {
                        student.marks[j] = mark;
                        break;
                    } else {
                        System.out.println("Invalid input! Please enter marks between 0 and 100.");
                    }
                }
            }

            student.calculateResults();
            students.add(student);
            scanner.nextLine(); // Consume the newline
        }

        System.out.println("\n--- Results ---");
        for (Student student : students) {
            System.out.println(student);
        }

        scanner.close();
    }
}

