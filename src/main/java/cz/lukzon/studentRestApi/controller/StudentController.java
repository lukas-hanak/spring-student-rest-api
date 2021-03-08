package cz.lukzon.studentRestApi.controller;

import cz.lukzon.studentRestApi.dto.BasicInfoDTO;
import cz.lukzon.studentRestApi.dto.StudentDTO;
import cz.lukzon.studentRestApi.entity.Student;
import cz.lukzon.studentRestApi.exception.DataNotFoundException;
import cz.lukzon.studentRestApi.exception.ProccesingRejectedException;
import cz.lukzon.studentRestApi.idto.StudentIDTO;
import cz.lukzon.studentRestApi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "student/{studentId}")
    public ResponseEntity<StudentDTO> getOne(@PathVariable("studentId") Long studentId) throws DataNotFoundException {
        Student student = studentService.getStudentById(studentId);
        StudentDTO studentDTO = StudentDTO.map(student);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @GetMapping(path = "classroom/{classroomId}/student")
    public ResponseEntity<List<StudentDTO>> getAll(@PathVariable("classroomId") Long classroomId) throws DataNotFoundException {
        List<Student> students = studentService.getStudentsByClassroom(classroomId);
        List<StudentDTO> studentsDTO = StudentDTO.map(students);
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    @PostMapping(path = "classroom/{classroomId}/student")
    public ResponseEntity<BasicInfoDTO>
    create(@PathVariable("classroomId") Long classroomId, @RequestBody StudentIDTO studentIDTO)
            throws DataNotFoundException, ProccesingRejectedException {
        studentService.addNewStudent(classroomId, studentIDTO);
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO(
                "Student is successfully created",
                true,
                HttpStatus.CREATED.value()
        );
        return new ResponseEntity<>(basicInfoDTO, HttpStatus.OK);
    }

    @PutMapping(path = "student/{studentId}")
    public ResponseEntity<BasicInfoDTO> update(@PathVariable("studentId") Long studentId, StudentIDTO studentIDTO)
            throws DataNotFoundException, ProccesingRejectedException {
        studentService.updateStudent(studentId, studentIDTO);
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO(
                "Student was successfully updated",
                true,
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(basicInfoDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "student/{studentId}")
    public ResponseEntity<BasicInfoDTO> delete(@PathVariable("studentId") Long studentId) throws DataNotFoundException {
        studentService.deleteStudent(studentId);
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO(
                "Student was successfully deleted",
                true,
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(basicInfoDTO, HttpStatus.OK);
    }
}
