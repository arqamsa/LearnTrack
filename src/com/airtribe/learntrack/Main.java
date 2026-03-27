package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final StudentService    studentService    = new StudentService();
    private static final CourseService     courseService     = new CourseService();
    private static final EnrollmentService enrollmentService =
            new EnrollmentService(studentService, courseService);

    public static void main(String[] args) {
        System.out.println("==============================");
        System.out.println("   Welcome to LearnTrack");
        System.out.println("==============================");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt();

            switch (choice) {
                case 1: handleStudentMenu();    break;
                case 2: handleCourseMenu();     break;
                case 3: handleEnrollmentMenu(); break;
                case 0:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("[ERROR] Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n==============================");
        System.out.println("         MAIN MENU");
        System.out.println("------------------------------");
        System.out.println(" 1. Student Management");
        System.out.println(" 2. Course Management");
        System.out.println(" 3. Enrollment Management");
        System.out.println(" 0. Exit");
        System.out.println("==============================");
        System.out.print("Enter choice: ");
    }

    private static void handleStudentMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Student Management ---");
            System.out.println(" 1. Add Student");
            System.out.println(" 2. View All Students");
            System.out.println(" 3. Search Student by ID");
            System.out.println(" 4. Update Student");
            System.out.println(" 5. Deactivate Student");
            System.out.println(" 6. Activate Student");
            System.out.println(" 0. Back");
            System.out.print("Enter choice: ");

            int choice = readInt();
            switch (choice) {
                case 1: addStudent();        break;
                case 2: viewAllStudents();   break;
                case 3: searchStudent();     break;
                case 4: updateStudent();     break;
                case 5: deactivateStudent(); break;
                case 6: activateStudent();   break;
                case 0: inMenu = false;      break;
                default: System.out.println("[ERROR] Invalid option.");
            }
        }
    }

    private static void addStudent() {
        System.out.println("\n-- Add Student --");
        System.out.print("First Name : ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Last Name  : ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Email (Enter to skip): ");
        String email = scanner.nextLine().trim();

        System.out.print("Batch      : ");
        String batch = scanner.nextLine().trim();

        try {
            Student student;
            if (email.isEmpty()) {
                student = studentService.addStudent(firstName, lastName, batch);
            } else {
                student = studentService.addStudent(firstName, lastName, email, batch);
            }
            System.out.println("[SUCCESS] Student added: " + student);
        } catch (InvalidInputException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void viewAllStudents() {
        List<Student> list = studentService.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("[INFO] No students found.");
            return;
        }
        System.out.println("\n--- All Students ---");
        for (Student s : list) {
            System.out.println(s);
        }
        System.out.println("Total: " + list.size());
    }

    private static void searchStudent() {
        System.out.print("Enter Student ID: ");
        int id = readInt();
        try {
            System.out.println(studentService.getStudentById(id));
        } catch (EntityNotFoundException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = readInt();
        try {
            Student existing = studentService.getStudentById(id);
            System.out.println("Current: " + existing);
            System.out.println("(Press Enter to keep existing value)");

            System.out.print("New First Name: ");
            String fn = scanner.nextLine().trim();

            System.out.print("New Last Name: ");
            String ln = scanner.nextLine().trim();

            System.out.print("New Email: ");
            String em = scanner.nextLine().trim();

            System.out.print("New Batch: ");
            String bt = scanner.nextLine().trim();

            Student updated = studentService.updateStudent(
                    id,
                    fn.isEmpty() ? null : fn,
                    ln.isEmpty() ? null : ln,
                    em.isEmpty() ? null : em,
                    bt.isEmpty() ? null : bt
            );
            System.out.println("[SUCCESS] Updated: " + updated);
        } catch (EntityNotFoundException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void deactivateStudent() {
        System.out.print("Enter Student ID to deactivate: ");
        int id = readInt();
        try {
            studentService.deactivateStudent(id);
            System.out.println("[SUCCESS] Student ID " + id + " deactivated.");
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void activateStudent() {
        System.out.print("Enter Student ID to activate: ");
        int id = readInt();
        try {
            studentService.activateStudent(id);
            System.out.println("[SUCCESS] Student ID " + id + " activated.");
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void handleCourseMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Course Management ---");
            System.out.println(" 1. Add Course");
            System.out.println(" 2. View All Courses");
            System.out.println(" 3. Search Course by ID");
            System.out.println(" 4. Activate / Deactivate Course");
            System.out.println(" 0. Back");
            System.out.print("Enter choice: ");

            int choice = readInt();
            switch (choice) {
                case 1: addCourse();          break;
                case 2: viewAllCourses();     break;
                case 3: searchCourse();       break;
                case 4: toggleCourseStatus(); break;
                case 0: inMenu = false;       break;
                default: System.out.println("[ERROR] Invalid option.");
            }
        }
    }

    private static void addCourse() {
        System.out.println("\n-- Add Course --");
        System.out.print("Course Name        : ");
        String name = scanner.nextLine().trim();

        System.out.print("Description (Enter to skip): ");
        String desc = scanner.nextLine().trim();

        System.out.print("Duration (weeks)   : ");
        int weeks = readInt();

        try {
            Course course;
            if (desc.isEmpty()) {
                course = courseService.addCourse(name, weeks);
            } else {
                course = courseService.addCourse(name, desc, weeks);
            }
            System.out.println("[SUCCESS] Course added: " + course);
        } catch (InvalidInputException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void viewAllCourses() {
        List<Course> list = courseService.getAllCourses();
        if (list.isEmpty()) {
            System.out.println("[INFO] No courses found.");
            return;
        }
        System.out.println("\n--- All Courses ---");
        for (Course c : list) {
            System.out.println(c);
        }
        System.out.println("Total: " + list.size());
    }

    private static void searchCourse() {
        System.out.print("Enter Course ID: ");
        int id = readInt();
        try {
            System.out.println(courseService.getCourseById(id));
        } catch (EntityNotFoundException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void toggleCourseStatus() {
        System.out.print("Enter Course ID: ");
        int id = readInt();
        try {
            Course course = courseService.toggleCourseStatus(id);
            System.out.println("[SUCCESS] Course status is now: " + (course.isActive() ? "ACTIVE" : "INACTIVE"));
        } catch (EntityNotFoundException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void handleEnrollmentMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Enrollment Management ---");
            System.out.println(" 1. Enroll Student in Course");
            System.out.println(" 2. View Enrollments by Student");
            System.out.println(" 3. View All Enrollments");
            System.out.println(" 4. Mark Enrollment as Completed");
            System.out.println(" 5. Cancel Enrollment");
            System.out.println(" 0. Back");
            System.out.print("Enter choice: ");

            int choice = readInt();
            switch (choice) {
                case 1: enrollStudent();          break;
                case 2: viewByStudent();          break;
                case 3: viewAllEnrollments();     break;
                case 4: markCompleted();          break;
                case 5: cancelEnrollment();       break;
                case 0: inMenu = false;           break;
                default: System.out.println("[ERROR] Invalid option.");
            }
        }
    }

    private static void enrollStudent() {
        System.out.print("Enter Student ID: ");
        int studentId = readInt();
        System.out.print("Enter Course ID : ");
        int courseId = readInt();
        try {
            Enrollment e = enrollmentService.enrollStudent(studentId, courseId);
            System.out.println("[SUCCESS] Enrolled! " + e);
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void viewByStudent() {
        System.out.print("Enter Student ID: ");
        int studentId = readInt();
        try {
            List<Enrollment> list = enrollmentService.getEnrollmentsByStudent(studentId);
            if (list.isEmpty()) {
                System.out.println("[INFO] No enrollments found for Student ID " + studentId);
                return;
            }
            for (Enrollment e : list) System.out.println(e);
        } catch (EntityNotFoundException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void viewAllEnrollments() {
        List<Enrollment> list = enrollmentService.getAllEnrollments();
        if (list.isEmpty()) {
            System.out.println("[INFO] No enrollments found.");
            return;
        }
        System.out.println("\n--- All Enrollments ---");
        for (Enrollment e : list) System.out.println(e);
        System.out.println("Total: " + list.size());
    }

    private static void markCompleted() {
        System.out.print("Enter Enrollment ID: ");
        int id = readInt();
        try {
            enrollmentService.markCompleted(id);
            System.out.println("[SUCCESS] Enrollment ID " + id + " marked as COMPLETED.");
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void cancelEnrollment() {
        System.out.print("Enter Enrollment ID: ");
        int id = readInt();
        try {
            enrollmentService.cancelEnrollment(id);
            System.out.println("[SUCCESS] Enrollment ID " + id + " CANCELLED.");
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("[ERROR] Please enter a valid number: ");
            }
        }
    }
}
