package com.example.demo.course;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(path = "getOne/{courseId}")
    public void findById(@PathVariable Long courseId){
        courseService.getSingleCourse(courseId);
    }


    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping
    public void addCourse(@RequestBody Course course){
        courseService.addCourse(course);
    }

    @DeleteMapping(path = "deleteCourse{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId){
        courseService.deleteCourse(courseId);
    }

    @PutMapping
    public void updateCourse(@RequestParam Long courseId, @RequestParam String name){
        courseService.updateCourse(courseId, name);
    }

}
