import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static StudentManager studentManager = new StudentManager();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        File file = new File("Students.txt");
        if (file.exists()) {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
                studentManager.setStudents((Map<String, Student>) inputStream.readObject());
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("problem occurred while reading the file : " + e.getMessage());
            }
        }
        else
            System.out.println("there is no prev file");

        boolean exit = false;
        while (!exit) {
            System.out.println("Please select a operation");
            System.out.println("1.Add Student\n2.Search with ID\n3.Search With Prefix\n4.List All Students\n5.Update Student GPA\n6.Remove Student\n7.Exit");
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        IDSearch();
                        break;
                    case 3:
                        prefixSearch();
                        break;
                    case 4:
                        listAllStudents();
                        break;
                    case 5:
                        updateStudent();
                        break;
                    case 6:
                        removeStudent();
                        break;
                    case 7:
                        exit();
                        exit = true;
                        break;
                    default:
                        System.out.println("please enter a valid choice");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("there is a problem, wrong input type entered");
                sc.nextLine();
            }
        }
    }

    public static void addStudent() {
        System.out.println("please enter a student ID");
        System.out.println("In this format : CPE123456");
        String ID = sc.next();
        if (!Validator.isValidID(ID)) {
            System.out.println("Invalid ID format format need to be like this :\n3 Letter and 6-8 number ex: CPE123456");
            return;
        }
        System.out.println("please enter a student name");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println("please enter a GPI value for student between 0.00 and 4.00");
        double gpi = sc.nextDouble();
        if (!Validator.isValidGPA(gpi)) {
            System.out.println("please enter a gpi value between 4.00 - 0.00");
            return;
        }
        System.out.println("please enter a department");
        sc.nextLine();
        String department = sc.nextLine();

        Student student = new Student(name, ID, gpi, department);
        studentManager.addStudent(student);
    }

    public static void IDSearch() {
        System.out.println("please enter a ID for search");
        String id = sc.next();
        System.out.println(studentManager.searchStudent(id));
    }

    public static void prefixSearch() {
        System.out.println("please enter a any valid prefix of ID");
        String prefix = sc.next();
        ArrayList<Student> matched = studentManager.searchWithPrefix(prefix);
        System.out.println(matched);
    }

    public static void listAllStudents() {
        studentManager.displayStudents();
    }

    public static void updateStudent() {
        System.out.println("Please enter the ID of the student to update gpa: ");
        String id = sc.next();
        System.out.println("Please enter the student's gpa: ");
        double gpa = sc.nextDouble();
        if(!Validator.isValidGPA(gpa)) {
            System.out.println("gpa is not valid update failed");
            return;
        }
        studentManager.updateStudent(id, gpa);
    }

    public static void removeStudent() {
        System.out.println("Please enter the ID for the student to remove: ");
        String id = sc.next();
        studentManager.removeStudent(id);
    }

    public static void exit() {
        System.out.println("exiting from the program, Students will be saved to the binary txt file");
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Students.txt"));
            outputStream.writeObject(studentManager.getStudents());
            outputStream.close();
        } catch (IOException e) {
            System.out.println("problem occurred");
            return;
        }
    }
}