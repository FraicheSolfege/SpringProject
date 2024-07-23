package com.example.demo.grade;

import com.example.demo.student.Student;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table
public class Grade {
    @Id
    @SequenceGenerator(
            name = "grade_sequence",
            sequenceName = "grade_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "grade_sequence"
    )
    private Long id;
    private String name;
    private Integer score;

    private List<Student> ArrayList;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name =  "student_grade",
    joinColumns = @JoinColumn(name = "grade_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students;



    public Grade() {
    }

    public Grade(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public Grade(Long id, String name, Integer score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
