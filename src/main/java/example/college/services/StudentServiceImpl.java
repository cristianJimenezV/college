package example.college.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import example.college.dto.InfoCollege;
import example.college.entitys.Course;
import example.college.entitys.Registration;
import example.college.entitys.Student;
import example.college.repositorys.RegistrationRepository;
import example.college.repositorys.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final RegistrationRepository registrationRepository;

    private StudentServiceImpl(StudentRepository studentRepository, RegistrationRepository registrationRepository) {
        this.studentRepository = studentRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public boolean existsById(final Long id) {
        if (id != null) {
            return studentRepository.existsById(id);
        }
        return false;
    }

    @SuppressWarnings("null")
    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @SuppressWarnings("null")
    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public InfoCollege findByCourse(Course course) {
        List<Registration> list = registrationRepository.findByCourse(course);
        List<Student> listStudents = list.stream()
                .map(Registration::getStudent)
                .collect(Collectors.toList());
        InfoCollege infoCollege = new InfoCollege();
        infoCollege.setCourse(course);
        infoCollege.setListStudent(listStudents);
        return infoCollege;
    }

}
