package cz.lukzon.studentRestApi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne
    private School school;

    private String name;

    @OneToOne(mappedBy = "classroom", cascade = CascadeType.ALL)
    private Teacher teacher;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Student> students;

    public Classroom(School school, String name) {
        this.school = school;
        this.name = name;
    }
}
