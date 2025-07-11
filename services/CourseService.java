package services;

import models.Course;
import utils.FileUtil;

import java.util.List;

public class CourseService {

    public void addCourse(Course course) {
        String line = course.getId() + "," + course.getTitle() + "," + course.toString();
        FileUtil.appendLine("StudentCourseManagement/data/courses.txt", line);
    }

    public List<String> getAllCourses() {
        return FileUtil.readAllLines("StudentCourseManagement/data/courses.txt");
    }
}
