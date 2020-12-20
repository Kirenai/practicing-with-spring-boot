package une.revilla.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import une.revilla.project.entity.Student;
import une.revilla.project.repository.StudentRepository;

@Service
@Qualifier(value = "studentService")
public class StudentServiceImp implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImp(@Qualifier("studentRepository") StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAllStudents();
    }

    @Override
    public Student findStudentById(Long id) {
        if (id == null || studentRepository.findStudentById(id) == null) {
            throw new RuntimeException("Id del usuario incorrecto");
        }
        return studentRepository.findStudentById(id);
    }

    @Override
    public Student saveStudent(Student student) throws Exception {
        boolean result = studentRepository.findAllStudents()
                .stream()
                .noneMatch((s) -> s.getEmail().equals(student.getEmail()));
        if (!result) throw new Exception("Email duplicate");
        return studentRepository.saveStudent(student);
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        if (id == null) {
            throw new RuntimeException("Id del usuario incorrecto");
        }
        return studentRepository.updateStudent(student, id);
    }

    @Override
    public void deleteStudentById(Long id) {
        if (id == null) {
            throw new RuntimeException("Id del usuario incorrecto");
        }
        studentRepository.deleteStudentById(id);
    }

    @Override
    public boolean validLogin(Student student) {
        List<Student> allStudents = studentRepository.findAllStudents();
        boolean resultOne = allStudents
                .stream()
                .anyMatch(s -> s.getUsername().equals(student.getUsername()));
        if (resultOne) {
            return allStudents
                    .stream()
                    .anyMatch(s -> s.getPassword().equals(student.getPassword()));
        }
        return resultOne;
    }

}
