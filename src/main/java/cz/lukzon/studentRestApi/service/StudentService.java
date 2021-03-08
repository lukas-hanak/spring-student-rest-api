package cz.lukzon.studentRestApi.service;

import cz.lukzon.studentRestApi.entity.Classroom;
import cz.lukzon.studentRestApi.entity.Student;
import cz.lukzon.studentRestApi.exception.DataNotFoundException;
import cz.lukzon.studentRestApi.exception.ProccesingRejectedException;
import cz.lukzon.studentRestApi.idto.StudentIDTO;
import cz.lukzon.studentRestApi.repository.ClassroomRepository;
import cz.lukzon.studentRestApi.repository.StudentRepository;
import cz.lukzon.studentRestApi.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, ClassroomRepository classroomRepository) {
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
    }

    public Student getStudentById(Long studentId) throws DataNotFoundException {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new DataNotFoundException("This student is not exists"));
    }

    public List<Student> getStudentsByClassroom(Long classroomId) throws DataNotFoundException {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new DataNotFoundException("This classroom is not exists"));
        List<Student> students = studentRepository.findStudentsByClassroom(classroom);
        if (students.isEmpty()) {
            throw new DataNotFoundException("No students were found at this classroom");
        }
        return students;
    }

    public void addNewStudent(Long classroomId, StudentIDTO studentIDTO)
            throws DataNotFoundException, ProccesingRejectedException {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new DataNotFoundException("This classroom is not exists"));
        String firstname = studentIDTO.getFirstname();
        String surname = studentIDTO.getSurname();
        String email = studentIDTO.getEmail();
        Integer age = studentIDTO.getAge();

        if (Utils.isNotStringValid(firstname)) {
            throw new ProccesingRejectedException("Firstname is not valid, must contains more then 3 character");
        }
        if (Utils.isNotStringValid(surname)) {
            throw new ProccesingRejectedException("Firstname is not valid, must contains more then 3 character");
        }
        if (Utils.isNotEmailValid(email)) {
            throw new ProccesingRejectedException("Firstname is not valid, must contains more then 3 character");
        }
        if (age > 10) {
            throw new ProccesingRejectedException("Student must be older then 10 years old");
        }

        studentRepository.save(new Student(classroom, firstname, surname, email, studentIDTO.getBoarding(), age));
    }

    public void updateStudent(Long studentId, StudentIDTO studentIDTO) throws DataNotFoundException, ProccesingRejectedException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new DataNotFoundException("This student is not exists"));
        String firstname = studentIDTO.getFirstname();
        String surname = studentIDTO.getSurname();
        String email = studentIDTO.getEmail();
        Integer age = studentIDTO.getAge();

        if (Utils.isNotStringValid(firstname)) {
            throw new ProccesingRejectedException("Firstname is not valid, must contains more then 3 character");
        }
        if (Utils.isNotStringValid(surname)) {
            throw new ProccesingRejectedException("Firstname is not valid, must contains more then 3 character");
        }
        if (Utils.isNotEmailValid(email)) {
            throw new ProccesingRejectedException("Firstname is not valid, must contains more then 3 character");
        }
        if (age > 10) {
            throw new ProccesingRejectedException("Student must be older then 10 years old");
        }

        student.setFirstname(firstname);
        student.setSurname(surname);
        student.setEmail(email);
        student.setBoarding(studentIDTO.getBoarding());
        student.setAge(age);
    }

    public void deleteStudent(Long studentId) throws DataNotFoundException {
        if (!studentRepository.existsById(studentId)) {
            throw new DataNotFoundException("This student is not exists");
        }
        classroomRepository.deleteById(studentId);
    }
}
