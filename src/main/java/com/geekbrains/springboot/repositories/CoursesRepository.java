package com.geekbrains.springboot.repositories;

import com.geekbrains.springboot.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends CrudRepository<Course, Long> {
}
