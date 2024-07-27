package com.example.demo.grade;

import com.example.demo.student.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "grade")
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

    @ManyToOne
    private Student student;

    public Grade() {
    }

    public Grade(String name, Integer score, Student student) {
        this.name = name;
        this.score = score;
        this.student = student;
    }

    public Grade(Long id, String name, Integer score, Student student) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.student = student;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", student=" + student +
                '}';
    }
}
