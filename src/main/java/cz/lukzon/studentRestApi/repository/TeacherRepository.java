package cz.lukzon.studentRestApi.repository;

import cz.lukzon.studentRestApi.entity.Classroom;
import cz.lukzon.studentRestApi.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findTeacherByClassroom(Classroom classroom);
}
