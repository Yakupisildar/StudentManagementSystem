import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String ID;
    private double GPA;
    private String department;

    public Student(String name, String ID, double GPA, String department) {
        this.name = name;
        this.ID = ID;
        this.GPA = GPA;
        this.department = department;
    }

    //get methods
    public String getName() {return name;}
    public String getID() {return ID;}
    public double getGrade() {return GPA;}
    public String getDepartment() {return department;}

    //set methods (for later updates)
    public void setGPI(double GPA) {
        this.GPA = GPA;
    }

    public String toString() {
        return "ID: " + ID + " name: " + name + " GPA: " + GPA + " department: " + department;
    }
}
