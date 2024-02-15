package example.college.controllers;

import java.net.URI;
import java.util.Optional;
import java.util.function.Function;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import example.college.dto.InfoCollege;
import example.college.dto.NewRegister;
import example.college.entitys.Course;
import example.college.entitys.Student;
import example.college.services.CourseService;
import example.college.services.RegistrationService;
import example.college.services.StudentService;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/college")
public class CollegeController {

    private final StudentService studentService;

    private final CourseService courseService;

    private final RegistrationService registrationService;

    private CollegeController(StudentService studentService, CourseService courseService,
            RegistrationService registrationService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.registrationService = registrationService;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<InfoCollege> findById(
            @PathVariable @NotNull(message = "El parametro, es necesario.") Long requestedId,
            @RequestParam(value = "option", required = false, defaultValue = "student") String option) {

        Function<Long, ResponseEntity<InfoCollege>> handleCourse = id -> {
            Optional<Course> course = courseService.findById(id);
            return course.map(value -> ResponseEntity.ok(studentService.findByCourse(value)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        };

        Function<Long, ResponseEntity<InfoCollege>> handleStudent = id -> {
            Optional<Student> student = studentService.findById(id);
            return student.map(value -> ResponseEntity.ok(courseService.findByStudent(value)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        };

        return switch (option) {
            case "course" -> handleCourse.apply(requestedId);
            case "student" -> handleStudent.apply(requestedId);
            default -> ResponseEntity.notFound().build();
        };
    }

    @PostMapping("/student")
    private ResponseEntity<Void> createStuden(@RequestBody @Valid Student newStudenRequest, UriComponentsBuilder ucb) {
        Student savedStuden = studentService.save(newStudenRequest);
        URI locationOfNewCashCard = ucb
                .path("/college/{id}")
                .buildAndExpand(savedStuden.getStudentId())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    @PostMapping("/registration")
    private ResponseEntity<Void> createRegistration(@RequestBody @Valid NewRegister newRegisterRequest,
            UriComponentsBuilder ucb) {
        Optional<Student> student = studentService.findById(newRegisterRequest.getStudentId());
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Long id = registrationService.save(student.get(), newRegisterRequest.getName());
        URI locationOfNewCashCard = ucb
                .path("/college/{id}?option=course")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }

}
