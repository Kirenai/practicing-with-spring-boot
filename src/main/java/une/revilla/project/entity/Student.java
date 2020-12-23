package une.revilla.project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "students")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    public Student(String username, String password, String email, String firstName, String lastName, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "username", nullable = false, length = 25)
    private String username;

    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @NonNull
    @Column(name = "email", nullable = false, length = 30, unique = true)
    private String email;

    @NonNull
    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;

    @NonNull
    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @NonNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "students_roles",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles = new HashSet<>();

}
