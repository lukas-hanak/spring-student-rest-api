package cz.lukzon.studentRestApi.service;

import cz.lukzon.studentRestApi.entity.Student;
import cz.lukzon.studentRestApi.exception.ConflictException;
import cz.lukzon.studentRestApi.exception.DataNotFoundException;
import cz.lukzon.studentRestApi.exception.ProccesingRejectedException;
import cz.lukzon.studentRestApi.repository.StudentRepository;
import cz.lukzon.studentRestApi.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public ArrayList<Student> getStudents() {
        return new ArrayList<>(studentRepository.findAll());
    }

    public void addNewStudent(Student student) throws ConflictException, ProccesingRejectedException {
        String firstname = student.getFirstname();
        String lastname = student.getLastname();
        String email = student.getEmail();

        if (!Utils.isNameValid(firstname)) {
            throw new ProccesingRejectedException("Firstname is not valid, must contains more then 3 characters");
        }

        if (!Utils.isNameValid(lastname)) {
            throw new ProccesingRejectedException("Surname is not valid, must contains more then 3 characters");
        }

        if (!Utils.isEmailValid(email)) {
            throw new ProccesingRejectedException("Email is not in valid format");
        }

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new ConflictException("This student with this email is exists");
        }
        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Long studentId, Student bodyStudent)
            throws ProccesingRejectedException, DataNotFoundException, ConflictException {
        String firstname = bodyStudent.getFirstname();
        String lastname = bodyStudent.getLastname();
        String email = bodyStudent.getEmail();
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new DataNotFoundException("Student with id " + studentId + " does not exists"));

        if (!Utils.isNameValid(firstname)) {
            throw new ProccesingRejectedException("Firstname is not valid, must contains more then 3 characters");
        }

        if (!Utils.isNameValid(lastname)) {
            throw new ProccesingRejectedException("Surname is not valid, must contains more then 3 characters");
        }

        if (!Utils.isEmailValid(email)) {
            throw new ProccesingRejectedException("Email is not in valid format");
        }
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
        if (studentOptional.isPresent()) {
            throw new ConflictException("This student with this email is exist");
        }

        student.setFirstname(firstname);
        student.setLastname(lastname);
        student.setEmail(email);
        if (!Objects.equals(student.getAge(), bodyStudent.getAge())) {
            student.setAge(bodyStudent.getAge());
        }
        if (!Objects.equals(student.getBoarding(), bodyStudent.getBoarding())) {
            student.setBoarding(bodyStudent.getBoarding());
        }
    }

    public void deleteStudent(Long studentId) throws DataNotFoundException {
        if (!studentRepository.existsById(studentId)) {
            throw new DataNotFoundException("Student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }
}
