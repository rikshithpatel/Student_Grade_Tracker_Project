package code.alpha.studentgradetracker;

import java.util.ArrayList;
import java.util.Scanner;

public class studentGradeTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentDAO dao = new StudentDAO();

        ArrayList<Student> students = dao.getStudents();

        System.out.println("=======================================");
        System.out.println("        STUDENT GRADE TRACKER");
        System.out.println("=======================================");

        int choice;

        do {

            System.out.println("\n1. Add Student");
            System.out.println("2. Add Grades");
            System.out.println("3. View Report");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            case 1:

                System.out.print("Enter student name: ");
                String name = sc.nextLine();

                int id = dao.addStudent(name);

                if (id != -1) {

                    Student student = new Student(id, name);

                    students.add(student);

                    System.out.println(
                            "Student added successfully!");
                }

                break;

            case 2:

                if (students.isEmpty()) {
                    System.out.println(
                            "No students available. Add a student first.");
                    break;
                }

                System.out.println("Select student:");

                for (int i = 0; i < students.size(); i++) {
                    System.out.println(
                            (i + 1) + ". " + students.get(i).getName());
                }

                int number = sc.nextInt();

                if (number < 1 || number > students.size()) {
                    System.out.println("Invalid choice!");
                    break;
                }

                Student selected = students.get(number - 1);

                System.out.print("Enter number of subjects: ");
                int subjects = sc.nextInt();

                for (int i = 0; i < subjects; i++) {

                    System.out.print(
                            "Enter grade " + (i + 1) + ": ");

                    int grade = sc.nextInt();

                    selected.addGrade(grade);

                    dao.addGrade(selected.getId(), grade);
                }

                System.out.println(
                        "Grades added successfully!");

                break;

            case 3:

                if (students.isEmpty()) {
                    System.out.println(
                            "No student records found.");
                } else {

                    System.out.println(
                            "\n======= STUDENT REPORT =======");

                    for (Student student : students) {
                        student.displayReport();
                    }
                }

                break;

            case 4:

                System.out.println(
                        "Data is already saved in PostgreSQL.");

                System.out.println("Exiting...");

                break;

            default:

                System.out.println(
                        "Invalid choice! Try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}