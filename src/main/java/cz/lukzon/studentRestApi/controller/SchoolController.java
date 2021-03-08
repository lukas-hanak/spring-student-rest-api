package cz.lukzon.studentRestApi.controller;

import cz.lukzon.studentRestApi.dto.BasicInfoDTO;
import cz.lukzon.studentRestApi.dto.SchoolDTO;
import cz.lukzon.studentRestApi.entity.School;
import cz.lukzon.studentRestApi.exception.ConflictException;
import cz.lukzon.studentRestApi.exception.DataNotFoundException;
import cz.lukzon.studentRestApi.exception.ProccesingRejectedException;
import cz.lukzon.studentRestApi.idto.SchoolIDTO;
import cz.lukzon.studentRestApi.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/school")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping(path = "{schoolId}")
    public ResponseEntity<SchoolDTO> getOne(@PathVariable("schoolId") Long schoolId) throws DataNotFoundException {
        School school = schoolService.getSchoolById(schoolId);
        SchoolDTO schoolDTO = SchoolDTO.map(school);
        return new ResponseEntity<>(schoolDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SchoolDTO>> getAll() throws DataNotFoundException {
        List<School> schools = schoolService.getAllSchools();
        List<SchoolDTO> schoolsDTO = SchoolDTO.map(schools);
        return new ResponseEntity<>(schoolsDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BasicInfoDTO> create(@RequestBody SchoolIDTO schoolIDTO)
            throws ProccesingRejectedException, ConflictException {
        schoolService.addNewSchool(schoolIDTO);
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO(
                "School was successfully created!",
                true,
                HttpStatus.CREATED.value()
        );
        return new ResponseEntity<>(basicInfoDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "{schoolId}")
    public ResponseEntity<BasicInfoDTO> update(@RequestBody SchoolIDTO schoolIDTO, @PathVariable("schoolId") Long id)
            throws DataNotFoundException, ProccesingRejectedException, ConflictException {
        schoolService.updateSchool(id, schoolIDTO);
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO(
                "School was successfully updated!",
                true,
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(basicInfoDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "{schoolId}")
    public ResponseEntity<BasicInfoDTO> delete(@PathVariable("schoolId") Long schoolId) throws DataNotFoundException {
        schoolService.deleteSchool(schoolId);
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO(
                "School was successfully deleted",
                true,
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(basicInfoDTO, HttpStatus.OK);
    }
}
