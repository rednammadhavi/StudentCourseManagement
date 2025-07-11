package services;

import models.Student;
import utils.FileUtil;

import java.util.List;

public class UserService {

    private final String STUDENT_FILE = "StudentCourseManagement/data/students.txt";

    public boolean isEmailRegistered(String email) {
        List<String> lines = FileUtil.readAllLines(STUDENT_FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 3 && parts[2].equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public void registerStudent(Student student) {
        if (!isEmailRegistered(student.getEmail())) {
            String line = student.getId() + "," + student.getName() + "," + student.getEmail();
            FileUtil.appendLine(STUDENT_FILE, line);
        }
    }

    public List<String> getAllStudents() {
        return FileUtil.readAllLines(STUDENT_FILE);
    }
}
