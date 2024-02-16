package example.college.dto;

import javax.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewRegister {
    @NotNull(message = "El identificador del alumno no puede estar en blanco")
    private Long studentId;

    @NotEmpty(message = "El nombre no puede estar en blanco")
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String name;
}
