package com.example.demo.course;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public void addCourse(Course course){
        courseRepository.save(course);
    }

    public void deleteCourse(Long courseId){
        boolean exists = courseRepository.existsById(courseId);
        if(!exists){
            throw new IllegalStateException("Course with id " + courseId + " does not exist");
        }
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public void updateCourse(Long courseId, String name){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException("Course with id " + courseId + " does not exist"));

        if(name != null && !name.isEmpty() && !name.equals(course.getName())){
            course.setName(name);
        }
    }
}
