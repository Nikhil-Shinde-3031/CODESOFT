import java.util.*;


public class task5 {
    private Map<String, Course> courseDatabase = new HashMap<>();
    private Map<Integer, Student> studentDatabase = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void addCourse(String courseCode, String title, String description, int capacity, String schedule) {
        Course course = new Course(courseCode, title, description, capacity, schedule);
        courseDatabase.put(courseCode, course);
    }

    public void addStudent(int studentID, String name) {
        Student student = new Student(studentID, name);
        studentDatabase.put(studentID, student);
    }

    public void displayCourseListing() {
        System.out.println("Course Listing:");
        for (Course course : courseDatabase.values()) {
            int availableSlots = course.capacity - getRegisteredStudents(course.courseCode).size();
            System.out.println("Course Code: " + course.courseCode +
                    ", Title: " + course.title +
                    ", Available Slots: " + availableSlots);
        }
    }

    public void registerStudentForCourse(int studentID, String courseCode) {
        if (courseDatabase.containsKey(courseCode) && studentDatabase.containsKey(studentID)) {
            Course course = courseDatabase.get(courseCode);
            Student student = studentDatabase.get(studentID);

            System.out.println("Debugging: Course Code = " + courseCode + ", Student ID = " + studentID);

            if (getRegisteredStudents(courseCode).size() < course.capacity) {
                student.registeredCourses.add(courseCode);
                System.out.println("Student " + student.name + " registered for course " + course.title);
            } else {
                System.out.println("Course " + course.title + " is full. Registration failed.");
            }
        } else {
            System.out.println("Invalid student ID or course code. Registration failed.");
        }
    }

    public void removeStudentFromCourse(int studentID, String courseCode) {
        if (courseDatabase.containsKey(courseCode) && studentDatabase.containsKey(studentID)) {
            Student student = studentDatabase.get(studentID);

            System.out.println("Debugging: Course Code = " + courseCode + ", Student ID = " + studentID);

            if (student.registeredCourses.contains(courseCode)) {
                student.registeredCourses.remove(courseCode);
                System.out.println("Student " + student.name + " removed from course " + courseDatabase.get(courseCode).title);
            } else {
                System.out.println("Student " + student.name + " is not registered for course " + courseDatabase.get(courseCode).title);
            }
        } else {
            System.out.println("Invalid student ID or course code. Removal failed.");
        }
    }

    public List<Student> getRegisteredStudents(String courseCode) {
        List<Student> registeredStudents = new ArrayList<>();
        for (Student student : studentDatabase.values()) {
            if (student.registeredCourses.contains(courseCode)) {
                registeredStudents.add(student);
            }
        }
        return registeredStudents;
    }

    public void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Display Course Listing");
        System.out.println("2. Register Student for Course");
        System.out.println("3. Remove Student from Course");
        System.out.println("4. Add Students to database");
        System.out.println("5. Exit");
    }

    public void addStudentByUser() {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        addStudent(studentID, studentName);
        System.out.println("Student added: ID = " + studentID + ", Name = " + studentName);
    }

    public static void main(String[] args) {
        task5 studentDatabase = new task5();

        studentDatabase.addCourse("CSE101", "Introduction to Computer Science", "Basic concepts of programming", 30, "Mon-Wed-Fri 10:00 AM");
        studentDatabase.addCourse("MTH201", "Calculus I", "Limits, derivatives, and integrals", 25, "Tue-Thu 1:00 PM");

        int choice;
        do {
            studentDatabase.displayMenu();
            System.out.print("Enter your choice: ");
            choice = studentDatabase.scanner.nextInt();
            studentDatabase.scanner.nextLine(); 
            switch (choice) {
                case 1:
                    
                    studentDatabase.displayCourseListing();
                    break;
                case 2:
    
                    System.out.print("Enter student ID: ");
                    int studentID = studentDatabase.scanner.nextInt();
                    studentDatabase.scanner.nextLine();
                    System.out.print("Enter course code: ");
                    String courseCodeToRegister = studentDatabase.scanner.nextLine();
                    studentDatabase.registerStudentForCourse(studentID, courseCodeToRegister);
                    break;
                case 3:
                    
                    System.out.print("Enter student ID: ");
                    int studentIDToRemove = studentDatabase.scanner.nextInt();
                    studentDatabase.scanner.nextLine(); 
                    System.out.print("Enter course code: ");
                    String courseCodeToRemove = studentDatabase.scanner.nextLine();
                    studentDatabase.removeStudentFromCourse(studentIDToRemove, courseCodeToRemove);
                    break;
                case 4:

                    studentDatabase.addStudentByUser();
                    break;
                case 5:
                    
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        studentDatabase.scanner.close();
    }
}
class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;

    Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

class Student {
    int studentID;
    String name;
    List<String> registeredCourses;

    Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}