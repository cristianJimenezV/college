package example.college.services;

import java.util.Optional;

import example.college.dto.InfoCollege;
import example.college.entitys.Course;
import example.college.entitys.Student;

public interface CourseService {
    public Course save (Course course);

    public Optional<Course> findById(Long id);

    public InfoCollege findByStudent(Student student);
}
