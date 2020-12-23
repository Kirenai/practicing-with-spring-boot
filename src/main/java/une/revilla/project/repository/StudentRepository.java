package une.revilla.project.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import une.revilla.project.entity.Student;

import java.util.Optional;

@Repository
@Qualifier("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByUsername(String username);

}
