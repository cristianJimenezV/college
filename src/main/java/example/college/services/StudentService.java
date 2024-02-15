package example.college.services;

import java.util.Optional;

import example.college.dto.InfoCollege;
import example.college.entitys.Course;
import example.college.entitys.Student;

public interface StudentService {

    public Student save (Student student);

    public Optional<Student> findById (Long id);

    public InfoCollege findByCourse(Course course);

    public boolean existsById(Long id);

}
