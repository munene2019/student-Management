package com.student.student.Controllers;
import com.student.student.DTO.CourseDto;
import com.student.student.DTO.TokenDTO;
import com.student.student.Utils.Util;
import com.student.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.student.student.DTO.CustomResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/course")
public class CoursesController {
    @Autowired
    CourseService courseService;
    @GetMapping(path = "/getcourses")
         public ResponseEntity<?> courses(){
        CustomResponse<?> response=courseService.getCourses();
        return Util.getResponse(response);
         }

    @PostMapping(path = "/register")
    public ResponseEntity<?> registercourse(@Validated(value =CourseDto.Create.class ) @RequestBody CourseDto request){
        CustomResponse<?> response=courseService.registerCourse(request);
        return Util.getResponse(response);
    }

}
