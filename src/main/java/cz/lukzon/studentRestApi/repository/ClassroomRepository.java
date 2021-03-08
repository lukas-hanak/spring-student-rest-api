package cz.lukzon.studentRestApi.repository;

import cz.lukzon.studentRestApi.entity.Classroom;
import cz.lukzon.studentRestApi.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    Optional<Classroom> findClassroomByName(String name);

    Optional<Classroom> findClassroomBySchool(School school);

    List<Classroom> findClassroomsBySchool(School school);
}
