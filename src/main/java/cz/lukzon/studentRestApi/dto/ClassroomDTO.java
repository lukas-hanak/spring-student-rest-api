package cz.lukzon.studentRestApi.dto;


import cz.lukzon.studentRestApi.entity.Classroom;
import cz.lukzon.studentRestApi.entity.Student;
import cz.lukzon.studentRestApi.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClassroomDTO {

    private Long id;
    private String name;
    private TeacherDTO teacher;
    private List<StudentDTO> students = new ArrayList<>();

    public static ClassroomDTO map(Classroom in) {
        ClassroomDTO classroomDTO = new ClassroomDTO();
        Teacher teacher = in.getTeacher();
        List<Student> students = in.getStudents();

        classroomDTO.setId(in.getId());
        classroomDTO.setName(in.getName());
        if (teacher != null)
            classroomDTO.setTeacher(TeacherDTO.map(teacher));
        if (!students.isEmpty())
            classroomDTO.setStudents(StudentDTO.map(students));

        return classroomDTO;
    }

    public static List<ClassroomDTO> map(List<Classroom> in) {
        return in.stream().map(ClassroomDTO::map).collect(Collectors.toList());
    }
}
