package code.alpha.studentgradetracker;

import java.util.ArrayList;

public class Student {

    private int id;
    private String name;
    private ArrayList<Integer> grades;

//    public Student(String name) {
//        this.name = name;
//        grades = new ArrayList<>();
//    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        grades = new ArrayList<>();
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public double getAverage() {
        if (grades.isEmpty()) {
            return 0;
        }

        int sum = 0;

        for (int grade : grades) {
            sum = sum + grade;
        }

        return (double) sum / grades.size();
    }

    public int getHighest() {
        if (grades.isEmpty()) {
            return 0;
        }

        int highest = grades.get(0);

        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }

        return highest;
    }

    public int getLowest() {
        if (grades.isEmpty()) {
            return 0;
        }

        int lowest = grades.get(0);

        for (int grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }

        return lowest;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getGrades() {
        return grades;
    }

    public void displayReport() {
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Grades: " + grades);

        if (grades.isEmpty()) {
            System.out.println("No grades available");
        } else {
            System.out.printf("Average: %.2f%n", getAverage());
            System.out.println("Highest: " + getHighest());
            System.out.println("Lowest: " + getLowest());
        }

        System.out.println("----------------------------------");
    }
}