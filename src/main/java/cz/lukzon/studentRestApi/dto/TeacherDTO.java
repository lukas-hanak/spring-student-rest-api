package cz.lukzon.studentRestApi.dto;

import cz.lukzon.studentRestApi.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TeacherDTO {

    private Long id;
    private String degree;
    private String firstname;
    private String surname;

    public static TeacherDTO map(Teacher in) {
        TeacherDTO teacherDTO = new TeacherDTO();

        teacherDTO.setId(in.getId());
        teacherDTO.setDegree(in.getDegree());
        teacherDTO.setFirstname(in.getFirstname());
        teacherDTO.setSurname(in.getSurname());

        return teacherDTO;
    }

    public static List<TeacherDTO> map(List<Teacher> in) {
        return in.stream().map(TeacherDTO::map).collect(Collectors.toList());
    }
}
