package une.revilla.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import une.revilla.project.dto.StudentDto;
import une.revilla.project.entity.Role;
import une.revilla.project.entity.Student;
import une.revilla.project.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Qualifier(value = "studentService")
public class StudentServiceImp implements StudentService {

    private StudentRepository studentRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public StudentServiceImp(@Qualifier("studentRepository") StudentRepository studentRepository,
                             PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("\n" +
                        "Error, the student was not found"));
    }

    @Override
    public Student save(StudentDto student) {
        Student newStudent = new Student(
                student.getUsername(),
                passwordEncoder.encode(student.getPassword()),
                student.getEmail(),
                student.getLastName(),
                student.getLastName(),
                Set.of(new Role("ROLE_USER"))
        );
        return studentRepository.save(newStudent);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student studentToUpdate = findStudentById(id);
        studentToUpdate.setUsername(student.getUsername());
        studentToUpdate.setPassword(passwordEncoder.encode(student.getPassword()));
        studentToUpdate.setEmail(student.getEmail());
        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setLastName(student.getLastName());
        return studentRepository.save(studentToUpdate);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.delete(studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("\n" +
                        "Error, the student was not found")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUsername(username);
        if (student == null) throw  new UsernameNotFoundException("Invalid username or password");
        return new User(student.getUsername(), student.getPassword(), grantedAuthorities(student.getRoles()));
    }

    /*@Override
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
    }*/

    private Collection<? extends GrantedAuthority> grantedAuthorities(Collection<Role> roles) {
        return  roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }

}
