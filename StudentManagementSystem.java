import java.util.ArrayList;
import java.util.Scanner;

// The main class that contains the application logic and the main method.
// This class must be public and its name must match the filename (StudentManagementSystem.java).
public class StudentManagementSystem {
    private ArrayList<Student> students;
    private Scanner scanner;

    public StudentManagementSystem() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // --- CRUD Operations ---

    public void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        if (findStudentById(id) != null) {
            System.out.println("Error: Student with this ID already exists.");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Student Marks: ");
        double marks = -1;
        while (marks < 0 || marks > 100) {
            try {
                marks = Double.parseDouble(scanner.nextLine());
                if (marks < 0 || marks > 100) {
                    System.out.println("Marks must be between 0 and 100. Please re-enter.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value for marks.");
            }
        }

        Student newStudent = new Student(id, name, marks);
        students.add(newStudent);
        System.out.println("Student added successfully!");
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("\n--- Student List ---");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("--------------------");
    }

    public void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        String id = scanner.nextLine();
        Student studentToUpdate = findStudentById(id);

        if (studentToUpdate == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        System.out.println("Student found: " + studentToUpdate);
        System.out.print("Enter new Name (leave blank to keep current: " + studentToUpdate.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            studentToUpdate.setName(newName);
        }

        System.out.print("Enter new Marks (leave blank to keep current: " + studentToUpdate.getMarks() + "): ");
        String newMarksStr = scanner.nextLine();
        if (!newMarksStr.isEmpty()) {
            try {
                double newMarks = Double.parseDouble(newMarksStr);
                if (newMarks >= 0 && newMarks <= 100) {
                    studentToUpdate.setMarks(newMarks);
                } else {
                    System.out.println("Invalid marks. Marks not updated.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for marks. Marks not updated.");
            }
        }
        System.out.println("Student updated successfully!");
    }

    public void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();
        Student studentToDelete = findStudentById(id);

        if (studentToDelete == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        students.remove(studentToDelete);
        System.out.println("Student deleted successfully!");
    }

    private Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    // --- Menu and Main Loop ---

    public void displayMenu() {
        System.out.println("\n--- Student Management System Menu ---");
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    public void run() {
        int choice;
        do {
            displayMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewStudents();
                        break;
                    case 3:
                        updateStudent();
                        break;
                    case 4:
                        deleteStudent();
                        break;
                    case 5:
                        System.out.println("Exiting Student Management System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                choice = 0;
            }
        } while (choice != 5);
        scanner.close();
    }

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        system.run();
    }
}

// The Student class, now declared without the 'public' keyword,
// as only one public class is allowed per .java file.
class Student {
    private String id;
    private String name;
    private double marks;

    public Student(String id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
    }
}