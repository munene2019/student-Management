package com.paymentGateway.student.Controllers;
import com.paymentGateway.student.DTO.CourseDto;
import com.paymentGateway.student.Utils.Util;
import com.paymentGateway.student.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.paymentGateway.student.DTO.CustomResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
