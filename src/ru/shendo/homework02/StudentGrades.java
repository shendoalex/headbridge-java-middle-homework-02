package ru.shendo.homework02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentGrades {

    private static final String STUDENT_NOT_FOUND = "Учащийся с именем: %s не найден";
    private static final String STUDENT_ALREADY_EXISTS = "Учащийся с именем: %s уже существует";
    private final Map<String, List<Integer>> studentGrades;
    private String studentName;
    private int studentGrade;

    public StudentGrades() {
        studentGrades = new HashMap<>();
    }

    private boolean checkStudentNameIsValid() {
        return studentName != null && !studentName.isEmpty();
    }

    private boolean checkStudentGradeIsValid() {
        return studentGrade >= 0 && studentGrade <= 100;
    }

    public void addStudent(String studentName) {
        this.studentName = studentName;
        if (checkStudentNameIsValid()) {
            if (!studentGrades.containsKey(studentName)) {
                studentGrades.putIfAbsent(studentName, new ArrayList<>());
            } else {
                System.out.printf(STUDENT_ALREADY_EXISTS, studentName);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void deleteStudent(String studentName) {
        this.studentName = studentName;
        if (checkStudentNameIsValid()) {
            if (!studentGrades.containsKey(studentName)) {
                studentGrades.remove(studentName);
            } else {
                System.out.printf(STUDENT_ALREADY_EXISTS, studentName);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void addStudentGrade(String studentName, int grade) {
        this.studentName = studentName;
        this.studentGrade = grade;
        if (checkStudentNameIsValid() && checkStudentGradeIsValid()) {
            if (studentGrades.containsKey(studentName)) {
                studentGrades.get(studentName).add(grade);
            } else {
                System.out.printf(STUDENT_NOT_FOUND, studentName);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void printStudentGrades(String studentName) {
        this.studentName = studentName;
        if (checkStudentNameIsValid()) {
            if (studentGrades.containsKey(studentName)) {
                System.out.println("Имя: " + studentName);
                System.out.println("Оценки: " + studentGrades.get(studentName));
            } else {
                System.out.printf(STUDENT_NOT_FOUND, studentName);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void printAllStudentsGrades() {
        for (Map.Entry<String, List<Integer>> entry : studentGrades.entrySet()) {
            System.out.println("Имя: " + entry.getKey());
            System.out.println("Оценки: " + entry.getValue());
        }
    }

    public void saveStudentGradesToFile(String fileName) {
        try (PrintWriter pw = new PrintWriter(fileName)) {
            for (Map.Entry<String, List<Integer>> entry : studentGrades.entrySet()) {
                pw.println(entry.getKey());
                for (Integer grade : entry.getValue()) {
                    pw.println(grade);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadStudentGradesFromFile(String fileName) {
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNext()) {
                String name = sc.nextLine();
                addStudent(name);
                while (sc.hasNextInt()) {
                    int grade = sc.nextInt();
                    addStudentGrade(name, grade);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
