package example.college.repositorys;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import example.college.entitys.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

    Optional<Course> findByName(String name);
}
