package cz.lukzon.studentRestApi.controller;

import cz.lukzon.studentRestApi.dto.BasicInfoDTO;
import cz.lukzon.studentRestApi.dto.TeacherDTO;
import cz.lukzon.studentRestApi.entity.Teacher;
import cz.lukzon.studentRestApi.exception.ConflictException;
import cz.lukzon.studentRestApi.exception.DataNotFoundException;
import cz.lukzon.studentRestApi.exception.ProccesingRejectedException;
import cz.lukzon.studentRestApi.idto.TeacherIDTO;
import cz.lukzon.studentRestApi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/classroom")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(path = "{classroomId}/teacher")
    public ResponseEntity<TeacherDTO> getOne(@PathVariable("classroomId") Long teacherId) throws DataNotFoundException {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        TeacherDTO teacherDTO = TeacherDTO.map(teacher);
        return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
    }

    @PostMapping(path = "{classroomId}/teacher")
    public ResponseEntity<BasicInfoDTO> create(@PathVariable("classroomId") Long classroomId, @RequestBody TeacherIDTO teacherIDTO)
            throws DataNotFoundException, ProccesingRejectedException, ConflictException {
        teacherService.addNewTeacher(classroomId, teacherIDTO);
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO(
                "Teacher is successfully created",
                true,
                HttpStatus.CREATED.value()
        );
        return new ResponseEntity<>(basicInfoDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "{classroomId}/teacher")
    public ResponseEntity<BasicInfoDTO>
    update(@PathVariable("classroomId") Long classroomId, @RequestBody TeacherIDTO teacherIDTO)
            throws DataNotFoundException, ProccesingRejectedException {
        teacherService.updateTeacher(classroomId, teacherIDTO);
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO(
                "Teacher was successfully updated",
                true,
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(basicInfoDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "{classroomId}/teacher")
    public ResponseEntity<BasicInfoDTO> delete(@PathVariable("classroomId") Long classroomId)
            throws DataNotFoundException {
        teacherService.deleteTeacher(classroomId);
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO(
                "Teacher was successfully deleted",
                true,
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(basicInfoDTO, HttpStatus.OK);
    }
}
