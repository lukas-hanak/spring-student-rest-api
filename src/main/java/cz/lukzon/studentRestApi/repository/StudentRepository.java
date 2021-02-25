package cz.lukzon.studentRestApi.repository;

import cz.lukzon.studentRestApi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentByEmail(String email);

    Optional<Student> findStudentByFirstname(String firstname);

    Optional<Student> findStudentByLastname(String lastname);
}
