package example.college.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import example.college.dto.InfoCollege;
import example.college.entitys.Course;
import example.college.entitys.Registration;
import example.college.entitys.Student;
import example.college.repositorys.CourseRepository;
import example.college.repositorys.RegistrationRepository;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    private final RegistrationRepository registrationRepository;

    public CourseServiceImpl(CourseRepository courseRepository, RegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> findById(Long id) {
       return courseRepository.findById(id);
    }

    @Override
    public InfoCollege findByStudent(Student student) {
        List<Registration> list = registrationRepository.findByStudent(student);
         List<Course> listCourse = list.stream()
                .map(Registration::getCourse)
                .collect(Collectors.toList());
        InfoCollege infoCollege = new InfoCollege();
        infoCollege.setListCourse(listCourse);
        infoCollege.setStudent(student);
        return infoCollege;
    }

}
