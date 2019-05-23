package com.geekbrains.springboot.services;

import com.geekbrains.springboot.entities.Course;
import com.geekbrains.springboot.repositories.CoursesRepository;
import com.geekbrains.springboot.utils.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesService {
    private CoursesRepository coursesRepository;

    @Autowired
    public void setCoursesRepository(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    public Course getCourseById(Long id) {
        Optional<Course> courseOptional = coursesRepository.findById(id);
        return courseOptional.orElse(null);
    }

    public void removeCourseAtStudent(Long courseId, Long studentId) {
        coursesRepository.removeCourseAtStudent(courseId, studentId);
    }

    public void addCourseAtStudent(Long courseId, Long studentId) {
        coursesRepository.addCourseAtStudent(courseId, studentId);
    }

    public List<Course> getAllCourses() {
        return (List<Course>)coursesRepository.findAll();
    }

    public Course saveOrUpdate(Course course) {
        return coursesRepository.save(course);
    }

    public void delete(Long id) {
        Optional<Course> course = coursesRepository.findById(id);
        if (!course.isPresent()) {
            throw new CourseNotFoundException("Course with id = " + id + " not found");
        }
        coursesRepository.delete(course.get());
    }
}
