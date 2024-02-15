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

@Entity(name="Students")
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
	@Column(name = "StudentID", length = 30)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;

    @Column(name = "Name", length = 30)
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String name;

    public Student(String name) {
        this.name = name;
    }
}
