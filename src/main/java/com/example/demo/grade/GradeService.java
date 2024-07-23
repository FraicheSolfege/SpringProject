package com.example.demo.grade;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public void addNewGrade(Grade grade) {
        gradeRepository.save(grade);
    }

    public void deleteGrade(Long gradeId) {
        boolean exists = gradeRepository.existsById(gradeId);
        if (!exists) {
            throw new IllegalStateException("grade with id " + gradeId + " does not exist");
        }
        gradeRepository.deleteById(gradeId);
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
}
