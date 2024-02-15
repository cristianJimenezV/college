package example.college.services;

import example.college.entitys.Student;

public interface RegistrationService {
    public Long save(Student student, String name);
}
