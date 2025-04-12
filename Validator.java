import java.util.regex.Pattern;

public class Validator {

    // Validate for student ID
    public static boolean isValidID(String id) {
        String idRegex = "[A-Z]{3}\\d{6,8}";
        return Pattern.matches(idRegex, id);
    }

    // Validate for student GPA
    public static boolean isValidGPA(double gpa) {
        return gpa >= 0.0 && gpa <= 4.0;
    }
}
