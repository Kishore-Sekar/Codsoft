import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    int enrolled;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0; // Start with no students enrolled
    }

    public boolean isAvailable() {
        return enrolled < capacity;
    }

    public void enrollStudent() {
        if (isAvailable()) {
            enrolled++;
        }
    }

    public void dropStudent() {
        if (enrolled > 0) {
            enrolled--;
        }
    }

    @Override
    public String toString() {
        return String.format("Code: %s, Title: %s, Description: %s, Capacity: %d, Enrolled: %d",
                code, title, description, capacity, enrolled);
    }
}

class Student {
    String id;
    String name;
    List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (course.isAvailable() && !registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.enrollStudent();
            System.out.println(name + " has been registered for " + course.title);
        } else {
            System.out.println("Registration failed: Course is not available or already registered.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            System.out.println(name + " has dropped " + course.title);
        } else {
            System.out.println("You are not registered for this course.");
        }
    }
}

public class CourseRegistrationSystem {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        initializeCourses();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Course Registration System ---");
            System.out.println("1. List Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    Student student = findOrCreateStudent(studentId, studentName);
                    registerForCourse(scanner, student);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    String dropStudentId = scanner.nextLine();
                    Student dropStudent = findStudent(dropStudentId);
                    if (dropStudent != null) {
                        dropCourse(scanner, dropStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void initializeCourses() {
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basics of programming.", 30));
        courses.add(new Course("MATH101", "Calculus I", "Introduction to calculus.", 25));
        courses.add(new Course("ENG101", "English Literature", "Study of classic literature.", 20));
    }

    private static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static Student findOrCreateStudent(String id, String name) {
        Student student = findStudent(id);
        if (student == null) {
            student = new Student(id, name);
            students.add(student);
        }
        return student;
    }

    private static Student findStudent(String id) {
        for (Student student : students) {
            if (student.id.equals(id)) {
                return student;
            }
        }
        return null;
    }

    private static void registerForCourse(Scanner scanner, Student student) {
        System.out.print("Enter course code to register: ");
        String courseCode = scanner.nextLine();
        Course course = findCourse(courseCode);
        if (course != null) {
            student.registerCourse(course);
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void dropCourse(Scanner scanner, Student student) {
        System.out.print("Enter course code to drop: ");
        String courseCode = scanner.nextLine();
        Course course = findCourse(courseCode);
        if (course != null) {
            student.dropCourse(course);
        } else {
            System.out.println("Course not found.");
        }
    }

    private static Course findCourse(String code) {
        for (Course course : courses) {
            if (course.code.equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }
}
