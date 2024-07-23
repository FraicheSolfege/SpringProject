package com.example.demo.grade;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class GradeController {
    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping(path = "api/v1/grade")
    public List<Grade> getGrades() {
        return gradeService.getAllGrades();
    }

    @PostMapping(path = "api/v1/create-grade")
    public void addNewGrade(@RequestBody Grade grade) {
        gradeService.addNewGrade(grade);
    }

    @DeleteMapping(path = "api/v1/grade/{gradeId}")
    public void deleteGrade(@PathVariable("gradeId") Long gradeId) {
        gradeService.deleteGrade(gradeId);
    }

    @PutMapping(path = "api/v1/grade/{gradeId}")
    public void updateGrade(
            @PathVariable("gradeId") Long gradeId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer score) {
        gradeService.updateGrade(gradeId, name, score);
    }

}
