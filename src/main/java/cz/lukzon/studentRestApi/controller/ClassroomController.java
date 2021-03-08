package cz.lukzon.studentRestApi.controller;

import cz.lukzon.studentRestApi.dto.BasicInfoDTO;
import cz.lukzon.studentRestApi.dto.ClassroomDTO;
import cz.lukzon.studentRestApi.idto.ClassroomIDTO;
import cz.lukzon.studentRestApi.entity.Classroom;
import cz.lukzon.studentRestApi.exception.ConflictException;
import cz.lukzon.studentRestApi.exception.DataNotFoundException;
import cz.lukzon.studentRestApi.exception.ProccesingRejectedException;
import cz.lukzon.studentRestApi.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping(path = "classroom/{classroomId}")
    public ResponseEntity<ClassroomDTO> getOne(@PathVariable("classroomId") Long classroomId)
            throws DataNotFoundException {
        Classroom classroom = classroomService.getClassroomById(classroomId);
        ClassroomDTO classroomDTO = ClassroomDTO.map(classroom);
        return new ResponseEntity<>(classroomDTO, HttpStatus.OK);
    }

    @GetMapping(path = "school/{schoolId}/classroom")
    public ResponseEntity<List<ClassroomDTO>> getAll(@PathVariable("schoolId") Long schoolId)
            throws DataNotFoundException {
        List<Classroom> classrooms = classroomService.getClassroomsBySchool(schoolId);
        List<ClassroomDTO> classroomsDTO = ClassroomDTO.map(classrooms);
        return new ResponseEntity<>(classroomsDTO, HttpStatus.OK);
    }

    @PostMapping(path = "school/{schoolId}/classroom")
    public ResponseEntity<BasicInfoDTO>
    create(@PathVariable("schoolId") Long schoolId, @RequestBody ClassroomIDTO classroomIDTO)
            throws ProccesingRejectedException, ConflictException, DataNotFoundException {
        classroomService.addNewClassroom(classroomIDTO, schoolId);
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO(
                "Classroom is successfully created",
                true,
                HttpStatus.CREATED.value()
        );
        return new ResponseEntity<>(basicInfoDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "classroom/{classroomId}")
    public ResponseEntity<BasicInfoDTO>
    update(@PathVariable("classroomId") Long classroomId, @RequestBody ClassroomIDTO classroomIDTO)
            throws DataNotFoundException, ProccesingRejectedException, ConflictException {
        classroomService.updateClassroom(classroomId, classroomIDTO);
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO(
                "Classroom was successfully updated",
                true,
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(basicInfoDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "classroom/{classroomId}")
    public ResponseEntity<BasicInfoDTO>
    delete(@PathVariable("classroomId") Long classroomId) throws DataNotFoundException {
        classroomService.deleteClassroom(classroomId);
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO(
                "Classroom was successfully deleted",
                true,
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(basicInfoDTO, HttpStatus.OK);
    }
}
