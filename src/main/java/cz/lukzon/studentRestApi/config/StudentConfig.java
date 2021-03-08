package cz.lukzon.studentRestApi.config;

import java.util.ArrayList;

import cz.lukzon.studentRestApi.entity.Student;
import cz.lukzon.studentRestApi.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class StudentConfig {
/*
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student lukas = new Student(
                    "Lukas",
                    "Hanak",
                    "luk.hanak592@gmail.com",
                    LocalDate.of(2001, Month.AUGUST, 11),
                    19
            );
            Student martin = new Student(
                    "Martin",
                    "Horak",
                    "martin.horak@gmail.com",
                    LocalDate.of(2005, Month.MARCH, 18),
                    25
            );
            Student pavel = new Student(
                    "Pavel",
                    "Konecny",
                    "pavel.konecny@gmail.com",
                    LocalDate.of(2000, Month.AUGUST, 12),
                    12
            );
            ArrayList<Student> arrayOfStudents = new ArrayList<>();
            arrayOfStudents.add(lukas);
            arrayOfStudents.add(martin);
            arrayOfStudents.add(pavel);
            repository.saveAll(arrayOfStudents);
        };
    }
 */
}
