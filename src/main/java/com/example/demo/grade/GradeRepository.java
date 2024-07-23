package com.example.demo.grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    @Query("SELECT g FROM Grade g WHERE g.student.id = ?")
    Grade findGradeByStudentId(Long studentId);
}
