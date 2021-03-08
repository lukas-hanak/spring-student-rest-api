package cz.lukzon.studentRestApi.dto;

import cz.lukzon.studentRestApi.entity.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Getter
@Setter
public class StudentDTO {

    private Long id;
    private String firstname;
    private String surname;
    private String email;
    private LocalDate boarding;
    private Integer age;

    public static StudentDTO map(Student in) {
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId(in.getId());
        studentDTO.setFirstname(in.getFirstname());
        studentDTO.setSurname(in.getSurname());
        studentDTO.setEmail(in.getEmail());
        studentDTO.setBoarding(in.getBoarding());
        studentDTO.setAge(in.getAge());

        return studentDTO;
    }

    public static List<StudentDTO> map(List<Student> in) {
        return in.stream().map(StudentDTO::map).collect(Collectors.toList());
    }
}
