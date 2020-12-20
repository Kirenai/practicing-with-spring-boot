package une.revilla.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
