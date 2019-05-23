package com.geekbrains.springboot.repositories;

import com.geekbrains.springboot.entities.Course;
import com.geekbrains.springboot.entities.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CoursesRepository extends CrudRepository<Course, Long> {
    List<Course> findByStudents(Student student);

    @Query(value = "SELECT * FROM courses WHERE id NOT IN (SELECT course_id FROM students_courses WHERE student_id = :studentId )\n", nativeQuery = true)
    List<Course> findAllOtherCourses(@Param("studentId") Long studentId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM students_courses WHERE course_id = :courseId AND student_id = :studentId", nativeQuery = true)
    void removeCourseAtStudent(@Param("courseId") Long courseId, @Param("studentId") Long studentId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO students_courses (course_id, student_id) VALUES (:courseId, :studentId)", nativeQuery = true)
    void addCourseAtStudent(@Param("courseId") Long courseId, @Param("studentId") Long studentId);
}
