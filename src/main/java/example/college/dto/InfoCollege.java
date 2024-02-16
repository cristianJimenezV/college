package example.college.dto;

import java.util.List;

import example.college.entitys.Course;
import example.college.entitys.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InfoCollege {

    private Student student;

    private Course course;

    private List<Course> listCourse;

    private List<Student> listStudent;
}
