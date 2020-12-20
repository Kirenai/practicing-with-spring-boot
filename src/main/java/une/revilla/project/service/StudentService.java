package une.revilla.project.service;

import java.util.List;
import une.revilla.project.entity.Student;

public interface StudentService {
    
    List<Student> findAllStudents();

    Student findStudentById(Long id);

    Student saveStudent(Student student) throws Exception;

    Student updateStudent(Student student, Long id);

    void deleteStudentById(Long id);

    boolean validLogin(Student student);
    
}
