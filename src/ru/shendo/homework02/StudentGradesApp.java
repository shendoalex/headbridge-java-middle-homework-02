package ru.shendo.homework02;

import java.util.Scanner;

public class StudentGradesApp {

    private static final String LOAD_OR_CREATE_DATA = "Загрузить существующий файл данных или создать новый? (1/2)";
    private static final String ENTER_FILE_NAME = "Введите имя файла:";
    private static final String SELECT_OPERATION = "Выберите операцию:";
    private static final String STUDENT_ADD = "a. Добавьте нового ученика";
    private static final String STUDENT_DELETE = "b. Удалите ученика";
    private static final String STUDENT_UPDATE_GRADE = "c. Обновите оценку ученика";
    private static final String VIEW_GRADES_OF_ALL_STUDENTS = "d. Просмотр оценок всех учащихся";
    private static final String VIEW_GRADES_OF_SPECIFIC_STUDENT = "e. Просмотр оценок конкретного учащегося";
    private static final String SAVE_TO_FILE = "f. Сохранить данные в файл";
    private static final String STUDENT_ALREADY_EXISTS = "g. Загрузить данные из файла";
    private static final String EXIT_MENU = "h. Выход";
    private static final String ENTER_STUDENT_NAME_TO_ADD = "Введите имя нового ученика:";
    private static final String ENTER_STUDENT_NAME_TO_DELETE = "Введите имя ученика, которого нужно удалить:";
    private static final String ENTER_STUDENT_NAME_TO_UPDATE_GRADE = "Введите имя ученика, оценку которого нужно обновить:";
    private static final String STUDENT_NEW_GRADE = "Введите новую оценку:";
    private static final String ENTER_STUDENT_NAME_TO_VIEW_GRADES = "Введите имя ученика, оценки которого нужно просмотреть:";
    private static final String ENTER_FILENAME_TO_SAVE_DATA = "Введите имя файла для сохранения данных:";
    private static final String ENTER_FILENAME_TO_READ_DATA = "Введите имя файла для загрузки данных:";
    private static final String WRONG_INPUT = "Неверный ввод. Попробуйте снова.";

    public static void runStudentGradesApp() {

        StudentGrades studentGrades = new StudentGrades();
        Scanner scanner = new Scanner(System.in);

        System.out.println(LOAD_OR_CREATE_DATA);
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.println(ENTER_FILE_NAME);
            String filename = scanner.nextLine();
            studentGrades.loadStudentGradesFromFile(filename);
        } else {

            while (true) {
                System.out.println(SELECT_OPERATION);
                System.out.println(STUDENT_ADD);
                System.out.println(STUDENT_DELETE);
                System.out.println(STUDENT_UPDATE_GRADE);
                System.out.println(VIEW_GRADES_OF_ALL_STUDENTS);
                System.out.println(VIEW_GRADES_OF_SPECIFIC_STUDENT);
                System.out.println(SAVE_TO_FILE);
                System.out.println(STUDENT_ALREADY_EXISTS);
                System.out.println(EXIT_MENU);

                String option = scanner.nextLine();

                switch (option) {
                    case "a":
                        System.out.println(ENTER_STUDENT_NAME_TO_ADD);
                        String newStudent = scanner.nextLine();
                        studentGrades.addStudent(newStudent);
                        break;
                    case "b":
                        System.out.println(ENTER_STUDENT_NAME_TO_DELETE);
                        String removeStudent = scanner.nextLine();
                        studentGrades.deleteStudent(removeStudent);
                        break;
                    case "c":
                        System.out.println(ENTER_STUDENT_NAME_TO_UPDATE_GRADE);
                        String updateStudent = scanner.nextLine();
                        System.out.println(STUDENT_NEW_GRADE);
                        int newGrade = scanner.nextInt();
                        scanner.nextLine();
                        studentGrades.addStudentGrade(updateStudent, newGrade);
                        break;
                    case "d":
                        studentGrades.printAllStudentsGrades();
                        break;
                    case "e":
                        System.out.println(ENTER_STUDENT_NAME_TO_VIEW_GRADES);
                        String viewStudent = scanner.nextLine();
                        studentGrades.printStudentGrades(viewStudent);
                        break;
                    case "f":
                        System.out.println(ENTER_FILENAME_TO_SAVE_DATA);
                        String saveFile = scanner.nextLine();
                        studentGrades.saveStudentGradesToFile(saveFile);
                        break;
                    case "g":
                        System.out.println(ENTER_FILENAME_TO_READ_DATA);
                        String loadFile = scanner.nextLine();
                        studentGrades.loadStudentGradesFromFile(loadFile);
                        break;
                    case "h":
                        System.exit(0);
                        break;
                    default:
                        System.out.println(WRONG_INPUT);
                }
            }
        }
    }
}