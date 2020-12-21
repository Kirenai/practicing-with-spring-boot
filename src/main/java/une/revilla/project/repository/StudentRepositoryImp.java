package une.revilla.project.repository;

import java.util.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import une.revilla.project.entity.Student;

@Repository
@Qualifier("studentRepository")
public interface StudentRepositoryImp extends StudentRepository {

    /*List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1L, "Lucas", "Gomez", "lucas_2@mail.com", "lucas361", "12345"),
            new Student(2L, "Maria", "Rojas", "maria@mail.com", "maria361", "12345"),
            new Student(3L, "Luis", "Maldonado", "luis_361@mail.com", "luis361", "12345")
    ));

    @Override
    public List<Student> findAllStudents() {
        return students;
    }

    @Override
    public Student findStudentById(Long id) {
        return students
                .stream()
                .filter(student -> Objects.equals(student.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Student does not find with id: " + id));
    }

    @Override
    public Student saveStudent(Student student) {
        students.add(student);
        return student;
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return students
                .stream()
                .filter(studentF -> Objects.equals(studentF.getId(), id))
                .findFirst()
                .map(studentM -> {
                    studentM.setFirstName(student.getFirstName());
                    studentM.setLastName(student.getLastName());
                    studentM.setEmail(student.getEmail());
                    return studentM;
                }).orElseThrow(() -> new NoSuchElementException("Student does not find with id: " + id));
    }

    @Override
    public void deleteStudentById(Long id) {
        students
                .stream()
                .filter(value -> Objects.equals(value.getId(), id))
                .findFirst()
                .map(value -> students.remove(value))
                .orElseThrow(() -> new NoSuchElementException("Student does not find with id: " + id));
    }*/


}
