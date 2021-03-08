package cz.lukzon.studentRestApi.repository;

import cz.lukzon.studentRestApi.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {

    Optional<School> findSchoolByName(String name);
}
