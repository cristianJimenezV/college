package example.college.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Registrations")
@NoArgsConstructor
@Getter
@Setter
public class Registration {
	@Id
    @Column(name = "RegistrationID", length = 30)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToOne
    @JoinColumn(name = "StudentID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;

    public Registration(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

}
