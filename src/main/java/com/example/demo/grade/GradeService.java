package com.example.demo.grade;


import com.example.demo.course.CourseController;
import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository, StudentRepository studentRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
    }

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public void addNewGrade(Grade grade) {
        if (grade == null || grade.getStudent() == null || grade.getStudent().getId() == null) {
            throw new IllegalStateException("Grade or Student ID is missing");
        }

        Long studentId = grade.getStudent().getId();

        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with ID " + studentId + " does not exist"));

        grade.setStudent(existingStudent);
        gradeRepository.save(grade);
    }

    @Transactional
    public void updateGrade(Long gradeId, String name, Integer score) {
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new IllegalStateException(
                        "grade with id " + gradeId + " does not exist"));

        if (name != null && !name.isEmpty() && !name.equals(grade.getName())) {
            grade.setName(name);
        }

        if (score != null && score > 0 && !score.equals(grade.getScore())) {
            grade.setScore(score);
        }
    }

    public void deleteGrade(Long gradeId) {
        boolean exists = gradeRepository.existsById(gradeId);
        if (!exists) {
            throw new IllegalStateException("grade with id " + gradeId + " does not exist");
        }
        gradeRepository.deleteById(gradeId);
    }
}
