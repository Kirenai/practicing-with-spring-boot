package une.revilla.project.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Wrong student id"));
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.delete(studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Wrong student id to delete")));
    }

    @Override
    public boolean validLogin(Student student) {
        List<Student> allStudents = studentRepository.findAll();
        boolean resultOne = allStudents
                .stream()
                .anyMatch(s -> s.getUsername().equals(student.getUsername()));
        if (resultOne) {
            return allStudents
                    .stream()
                    .anyMatch(s -> s.getPassword().equals(student.getPassword()));
        }
        return false;
    }

}
