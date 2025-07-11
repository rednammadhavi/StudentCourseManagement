import models.Admin;
import models.Course;
import models.Student;
import services.CourseService;
import services.EnrollmentService;
import services.UserService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    static UserService userService = new UserService();
    static CourseService courseService = new CourseService();
    static EnrollmentService enrollmentService = new EnrollmentService();
    static Admin admin = new Admin();
    static Scanner scanner = new Scanner(System.in);
    static Student loggedInStudent = null;

    public static void main(String[] args) {
        System.out.println("🎓 Welcome to Student Course Management System");

        while (true) {
            System.out.println("\n1. Register as Student");
            System.out.println("2. Student Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> registerStudent();
                case 2 -> studentLogin();
                case 3 -> adminLogin();
                case 4 -> {
                    System.out.println("👋 Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    static void registerStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        String id = UUID.randomUUID().toString().substring(0, 8);
        Student student = new Student(id, name, email);
        userService.registerStudent(student);
        System.out.println("✅ Registered successfully! Your ID: " + id);
    }

    static void studentLogin() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        List<String> students = userService.getAllStudents();
        for (String line : students) {
            String[] data = line.split(",");
            if (data[2].equals(email)) {
                loggedInStudent = new Student(data[0], data[1], data[2]);
                System.out.println("✅ Logged in as " + loggedInStudent.getName());
                studentMenu();
                return;
            }
        }
        System.out.println("❌ No student found with that email.");
    }

    static void adminLogin() {
        System.out.print("Admin username: ");
        String user = scanner.nextLine();
        System.out.print("Admin password: ");
        String pass = scanner.nextLine();
        if (admin.login(user, pass)) {
            System.out.println("✅ Admin login successful.");
            adminMenu();
        } else {
            System.out.println("❌ Invalid admin credentials.");
        }
    }

    static void studentMenu() {
        while (true) {
            System.out.println("\n👩‍🎓 Student Menu");
            System.out.println("1. View Available Courses");
            System.out.println("2. Enroll in a Course");
            System.out.println("3. Logout");
            System.out.print("Select: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> viewCourses();
                case 2 -> {
                    viewCourses();
                    System.out.print("Enter Course ID to enroll: ");
                    String cid = scanner.nextLine();
                    enrollmentService.enroll(loggedInStudent.getId(), cid);
                }
                case 3 -> {
                    loggedInStudent = null;
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void adminMenu() {
        while (true) {
            System.out.println("\n🧑‍💼 Admin Menu");
            System.out.println("1. View All Students");
            System.out.println("2. View All Courses");
            System.out.println("3. Add New Course");
            System.out.println("4. Logout");
            System.out.print("Select: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    List<String> students = userService.getAllStudents();
                    System.out.println("\n--- Registered Students ---");
                    for (String s : students) System.out.println(s);
                }
                case 2 -> viewCourses();
                case 3 -> addCourse();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void viewCourses() {
        List<String> courses = courseService.getAllCourses();
        System.out.println("\n📚 Available Courses:");
        for (String c : courses) System.out.println(c);
    }

    static void addCourse() {
        System.out.print("Course title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        System.out.print("Duration (weeks): ");
        int duration = scanner.nextInt();
        System.out.print("Fee: ₹");
        double fee = scanner.nextDouble();
        scanner.nextLine();
        String cid = UUID.randomUUID().toString().substring(0, 6);
        Course course = new Course(cid, title, desc, duration, fee);
        courseService.addCourse(course);
        System.out.println("✅ Course added!");
    }
}
