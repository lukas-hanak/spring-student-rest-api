package cz.lukzon.studentRestApi.idto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherIDTO {

    private String degree;
    private String firstname;
    private String surname;
}
