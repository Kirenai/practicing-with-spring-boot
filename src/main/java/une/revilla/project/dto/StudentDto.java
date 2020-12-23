package une.revilla.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

}
