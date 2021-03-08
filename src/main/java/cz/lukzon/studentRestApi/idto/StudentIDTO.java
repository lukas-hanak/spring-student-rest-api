package cz.lukzon.studentRestApi.idto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentIDTO {

    private String firstname;
    private String surname;
    private String email;
    private LocalDate boarding;
    private Integer age;
}
