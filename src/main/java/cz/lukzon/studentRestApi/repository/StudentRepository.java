package cz.lukzon.studentRestApi.repository;

import cz.lukzon.studentRestApi.entity.Classroom;
import cz.lukzon.studentRestApi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByClassroom(Classroom classroom);
}
