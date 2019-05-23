package com.geekbrains.springboot.controllers;

import com.geekbrains.springboot.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/courses")
public class CoursesController {
    private CoursesService coursesService;

    @RequestMapping("/list")
    @Transactional
    public String showCoursesList(Model model) {
        model.addAttribute("coursesList", coursesService.getAllCourses());
        return "courses-list";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/remove/{courseId}/atStudent/{studentId}", method = RequestMethod.GET)
    public String removeCourseAtStudent(@PathVariable(value = "courseId") Long courseId, @PathVariable(value = "studentId") Long studentId) {
        coursesService.removeCourseAtStudent(courseId, studentId);
        return "redirect:/students/" + studentId + "/edu";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/add/{courseId}/atStudent/{studentId}", method = RequestMethod.GET)
    public String addCourseAtStudent(@PathVariable(value = "courseId") Long courseId, @PathVariable(value = "studentId") Long studentId) {
        coursesService.addCourseAtStudent(courseId, studentId);
        return "redirect:/students/" + studentId + "/edu";
    }

    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }
}
