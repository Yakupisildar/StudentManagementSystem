import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentManager implements Serializable {
    private Map<String, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        if(students.containsKey(student.getID())) {
            System.out.println("Error: student with ID: " + student.getID() + " is already in the list");
        }
        else {
            students.put(student.getID(), student);
            System.out.println("student with ID: " + student.getID() + " added");
        }
    }

    public Student searchStudent(String id) {
        return students.get(id);
    }

    public ArrayList<Student> searchWithPrefix(String prefix) {
        int length = prefix.length();
        ArrayList<Student> matched = new ArrayList<>();
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            if(entry.getKey().startsWith(prefix))
                matched.add(entry.getValue());
        }
        return matched;
    }

    public void displayStudents() {
        for (Student s : students.values()) {
            System.out.println(s); // will use to String of Student
        }
    }

    public void updateStudent(String id, double gpa) {
        if(!students.containsKey(id)) {
            System.out.println("Student with ID: " + id + " is not in the list");
            return;
        }
        students.get(id).setGPI(gpa);
    }

    public void removeStudent(String id) {
        if(students.containsKey(id)) {
            students.remove(id);
            System.out.println("Student with ID: " + id + " removed");
        } else {
            System.out.println("Error : No student found with the given ID");
        }
    }

    public Map<String, Student> getStudents() {
        return students;
    }

    public void setStudents (Map<String, Student> students) {
        this.students = students;
    }
}
