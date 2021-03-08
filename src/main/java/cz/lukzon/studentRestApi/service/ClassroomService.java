package cz.lukzon.studentRestApi.service;

import cz.lukzon.studentRestApi.idto.ClassroomIDTO;
import cz.lukzon.studentRestApi.entity.Classroom;
import cz.lukzon.studentRestApi.entity.School;
import cz.lukzon.studentRestApi.exception.ConflictException;
import cz.lukzon.studentRestApi.exception.DataNotFoundException;
import cz.lukzon.studentRestApi.exception.ProccesingRejectedException;
import cz.lukzon.studentRestApi.repository.ClassroomRepository;
import cz.lukzon.studentRestApi.repository.SchoolRepository;
import org.springframework.transaction.annotation.Transactional;
import cz.lukzon.studentRestApi.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    private final SchoolRepository schoolRepository;
    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomService(
            SchoolRepository schoolRepository,
            ClassroomRepository classroomRepository
    ) {
        this.schoolRepository = schoolRepository;
        this.classroomRepository = classroomRepository;
    }


    public Classroom getClassroomById(Long classroomId) throws DataNotFoundException {
        return classroomRepository.findById(classroomId)
                .orElseThrow(() -> new DataNotFoundException("This classroom is not exists"));
    }

    public List<Classroom> getClassroomsBySchool(Long schoolId) throws DataNotFoundException {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new DataNotFoundException("This school is not exists"));
        List<Classroom> classrooms = classroomRepository.findClassroomsBySchool(school);
        if (classrooms.isEmpty()) {
            throw new DataNotFoundException("No classrooms were found at this school");
        }
        return classrooms;
    }

    public void addNewClassroom(ClassroomIDTO classroomIDTO, Long schoolId)
            throws ProccesingRejectedException, ConflictException, DataNotFoundException {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new DataNotFoundException("This school is not exists"));
        String name = classroomIDTO.getName();

        if (name == null) {
            throw new ProccesingRejectedException("Name is not valid, must contains more then 1 character");
        }

        Optional<Classroom> classroomByNameOptional = classroomRepository.findClassroomByName(name);
        if (classroomByNameOptional.isPresent()) {
            throw new ConflictException("This classroom is exists");
        }

        classroomRepository.save(new Classroom(school, classroomIDTO.getName()));
    }

    @Transactional
    public void updateClassroom(Long classroomId, ClassroomIDTO classroomIDTO)
            throws DataNotFoundException, ProccesingRejectedException, ConflictException {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new DataNotFoundException("This classroom is not exist"));
        String name = classroomIDTO.getName();

        if (name == null) {
            throw new ProccesingRejectedException("Name is not valid, must contains more then 1 character");
        }

        Optional<Classroom> classroomByNameOptional = classroomRepository.findClassroomByName(name);
        if (classroomByNameOptional.isPresent()) {
            throw new ConflictException("This classroom is exists");
        }

        classroom.setName(name);
    }

    public void deleteClassroom(Long classroomId) throws DataNotFoundException {
        if (!classroomRepository.existsById(classroomId)) {
            throw new DataNotFoundException("This classroom is not exists");
        }
        classroomRepository.deleteById(classroomId);
    }
}
