package com.student.Controllers;


import com.student.student.Entity.CourseModel;
import com.student.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/course")
public class CoursesController {
    @Autowired
    CourseService courseService;
    @GetMapping(path = "/getcourses")
         public List<CourseModel> courses(){

        return courseService.getCourses();
         }
}
