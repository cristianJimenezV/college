package example.college.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import example.college.entitys.Course;
import example.college.entitys.Registration;
import example.college.entitys.Student;

public interface RegistrationRepository extends CrudRepository<Registration, Long>{

    List<Registration> findByCourse(Course course);

    List<Registration> findByStudent(Student student);
}
