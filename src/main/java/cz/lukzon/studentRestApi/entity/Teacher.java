package cz.lukzon.studentRestApi.entity;

import lombok.*;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Classroom classroom;

    private String degree;

    private String firstname;

    private String surname;

    public Teacher(Classroom classroom, String degree, String firstname, String surname) {
        this.classroom = classroom;
        this.degree = degree;
        this.firstname = firstname;
        this.surname = surname;
    }
}
