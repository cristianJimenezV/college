package example.college.repositorys;

import org.springframework.data.repository.CrudRepository;

import example.college.entitys.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
