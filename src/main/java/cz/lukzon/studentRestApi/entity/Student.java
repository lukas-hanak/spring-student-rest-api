package cz.lukzon.studentRestApi.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Student {

    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDate boarding;
    private Integer age;

    public Student() {}

    public Student(String firstname, String lastname, String email, LocalDate boarding, Integer age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.boarding = boarding;
        this.age = age;
    }

    public Student(Long id, String firstname, String lastname, String email, LocalDate boarding, Integer age) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.boarding = boarding;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBoarding() {
        return boarding;
    }

    public void setBoarding(LocalDate boarding) {
        this.boarding = boarding;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", boarding=" + boarding +
                ", age=" + age +
                '}';
    }
}
