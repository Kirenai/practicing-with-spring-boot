package une.revilla.project.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import une.revilla.project.dto.StudentDto;
import une.revilla.project.entity.Student;

public interface StudentService extends UserDetailsService {
    
    List<Student> findAllStudents();

    Student findStudentById(Long id);

    Student save(StudentDto student);

    Student updateStudent(Long id, Student student);

    void deleteStudentById(Long id);

}
