package example.college.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import example.college.entitys.Course;
import example.college.entitys.Registration;
import example.college.entitys.Student;
import example.college.repositorys.CourseRepository;
import example.college.repositorys.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private CourseRepository courseRepository;

    private RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(CourseRepository courseRepository, RegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public Long save(Student student, String name) {
        Optional<Course> courseRegister = courseRepository.findByName(name);

        if (courseRegister.isPresent()) {
            Registration registration = new Registration(student, courseRegister.get());
            Registration newRegistration = registrationRepository.save(registration);
            return newRegistration.getCourse().getCourseId();
        } else {
            Course newCourse = courseRepository.save(new Course(name));
            Registration registration = new Registration(student, newCourse);
            Registration newRegistration = registrationRepository.save(registration);
            return newRegistration.getCourse().getCourseId();
        }
    }

}
