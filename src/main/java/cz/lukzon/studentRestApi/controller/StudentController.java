package cz.lukzon.studentRestApi.controller;

import java.util.ArrayList;

import cz.lukzon.studentRestApi.dto.StudentInfoDTO;
import cz.lukzon.studentRestApi.entity.Student;
import cz.lukzon.studentRestApi.exception.ConflictException;
import cz.lukzon.studentRestApi.exception.DataNotFoundException;
import cz.lukzon.studentRestApi.service.StudentService;
import cz.lukzon.studentRestApi.exception.ProccesingRejectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Long id) {
        return studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalStateException("Something is wrong!"));
    }

    @GetMapping
    public ArrayList<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public ResponseEntity<StudentInfoDTO>
    createNewStudent(@RequestBody Student student)
            throws ConflictException, ProccesingRejectedException {
        studentService.addNewStudent(student);
        StudentInfoDTO studentInfoDTO = new StudentInfoDTO(
                "Student was successfully created",
                true,
                HttpStatus.CREATED.value()
        );
        return new ResponseEntity<>(studentInfoDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<StudentInfoDTO>
    updateStudent(@RequestBody Student student, @PathVariable("studentId") Long id)
            throws ProccesingRejectedException, DataNotFoundException, ConflictException {
        studentService.updateStudent(id, student);
        StudentInfoDTO studentInfoDTO = new StudentInfoDTO(
                "Student was succesfully updated",
                true,
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(studentInfoDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<StudentInfoDTO> deleteStudent(@PathVariable("studentId") Long id)
            throws DataNotFoundException {
        studentService.deleteStudent(id);
        StudentInfoDTO studentInfoDTO = new StudentInfoDTO(
                "Student was successfully deleted",
                true,
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(studentInfoDTO, HttpStatus.OK);
    }
}
