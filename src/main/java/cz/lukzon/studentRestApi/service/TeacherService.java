package cz.lukzon.studentRestApi.service;

import cz.lukzon.studentRestApi.entity.Classroom;
import cz.lukzon.studentRestApi.entity.Teacher;
import cz.lukzon.studentRestApi.exception.ConflictException;
import cz.lukzon.studentRestApi.exception.DataNotFoundException;
import cz.lukzon.studentRestApi.exception.ProccesingRejectedException;
import cz.lukzon.studentRestApi.idto.TeacherIDTO;
import cz.lukzon.studentRestApi.repository.ClassroomRepository;
import cz.lukzon.studentRestApi.repository.TeacherRepository;
import cz.lukzon.studentRestApi.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final ClassroomRepository classroomRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, ClassroomRepository classroomRepository) {
        this.teacherRepository = teacherRepository;
        this.classroomRepository = classroomRepository;
    }

    public Teacher getTeacherById(Long teacherId) throws DataNotFoundException {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new DataNotFoundException("This teacher is not exists"));
    }

    public void addNewTeacher(Long classroomId, TeacherIDTO teacherIDTO)
            throws DataNotFoundException, ProccesingRejectedException, ConflictException {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new DataNotFoundException("This classroom is not exists"));
        String degree = teacherIDTO.getDegree();
        String firstname = teacherIDTO.getFirstname();
        String surname = teacherIDTO.getSurname();

        Optional<Teacher> teacher = teacherRepository.findTeacherByClassroom(classroom);
        if (teacher.isPresent()) {
            throw new ConflictException("This class already has a teacher");
        }
        if (Utils.isNotStringValid(firstname)) {
            throw new ProccesingRejectedException("Firstname is not valid, must contains more then 3 characters");
        }
        if (Utils.isNotStringValid(surname)) {
            throw new ProccesingRejectedException("Surname is not valid, must contains more then 3 characters");
        }

        teacherRepository.save(new Teacher(classroom, degree, firstname, surname));
    }

    @Transactional
    public void updateTeacher(Long classroomId, TeacherIDTO teacherIDTO) throws DataNotFoundException, ProccesingRejectedException {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new DataNotFoundException("This classroom is not exists"));
        Teacher teacher = teacherRepository.findTeacherByClassroom(classroom)
                .orElseThrow(() -> new DataNotFoundException("This class has no teacher"));
        String firstname = teacherIDTO.getFirstname();
        String surname = teacherIDTO.getSurname();

        if (Utils.isNotStringValid(firstname)) {
            throw new ProccesingRejectedException("Firstname is not valid, must contains more then 3 characters");
        }
        if (Utils.isNotStringValid(surname)) {
            throw new ProccesingRejectedException("Surname is not valid, must contains more then 3 characters");
        }

        teacher.setDegree(teacherIDTO.getDegree());
        teacher.setFirstname(firstname);
        teacher.setSurname(surname);
    }

    public void deleteTeacher(Long classroomId) throws DataNotFoundException {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new DataNotFoundException("This classroom is not exists"));
        Teacher teacher = classroom.getTeacher();
        if (!teacherRepository.existsById(teacher.getId())) {
            throw new DataNotFoundException("This teacher is not exists");
        }
        teacherRepository.deleteById(teacher.getId());
    }
}
