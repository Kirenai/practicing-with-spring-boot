package une.revilla.project.repository;

import java.util.List;
import une.revilla.project.entity.Student;

public interface StudentRepository {

    List<Student> findAllStudents();

    Student findStudentById(Long id);

    Student saveStudent(Student student);

    Student updateStudent(Student student, Long id);

    void deleteStudentById(Long id);
    
}
