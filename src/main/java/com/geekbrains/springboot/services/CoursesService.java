package com.geekbrains.springboot.services;

import com.geekbrains.springboot.entities.Course;
import com.geekbrains.springboot.repositories.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesService {
    private CoursesRepository coursesRepository;

    @Autowired
    public void setCoursesRepository(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    public List<Course> getAllCoursesList() {
        return (List<Course>)coursesRepository.findAll();
    }
}
