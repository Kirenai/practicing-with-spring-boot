package une.revilla.project.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false, length = 20)
    private String name;

}
