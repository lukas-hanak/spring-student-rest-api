package cz.lukzon.studentRestApi.dto;

import cz.lukzon.studentRestApi.entity.Classroom;
import cz.lukzon.studentRestApi.entity.School;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SchoolDTO {

    private Long id;
    private String name;
    private String town;
    public List<ClassroomDTO> classrooms = new ArrayList<>();

    public static SchoolDTO map(School in) {
        SchoolDTO schoolDTO = new SchoolDTO();
        List<Classroom> classrooms = in.getClassrooms();

        schoolDTO.setId(in.getId());
        schoolDTO.setName(in.getName());
        schoolDTO.setTown(in.getTown());
        if (!classrooms.isEmpty())
            schoolDTO.setClassrooms(ClassroomDTO.map(classrooms));

        return schoolDTO;
    }

    public static List<SchoolDTO> map(List<School> in) {
        return in.stream().map(SchoolDTO::map).collect(Collectors.toList());
    }
}
