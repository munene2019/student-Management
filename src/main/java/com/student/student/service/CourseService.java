package com.student.student.service;

import com.student.student.Entity.CourseModel;
import com.student.student.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

public List<CourseModel> getCourses() {
    List <CourseModel> courseModels = courseRepository.findModels();
    return courseModels;
    }
}