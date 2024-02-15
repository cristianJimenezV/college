package example.college.entitys;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Courses")
@NoArgsConstructor
@Getter
@Setter
public class Course {
    @Id
	@Column(name = "CourseID", length = 30)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(name = "NameCourse", length = 30)
    private String name;


    public Course(String name) {
        this.name = name;
    }
}
