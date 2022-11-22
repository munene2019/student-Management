package com.student.student.Controllers;


import com.student.student.DTO.CourseDto;
import com.student.student.DTO.CustomResponse;
import com.student.student.DTO.TokenDTO;
import com.student.student.Utils.Util;
import com.student.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/authenticate")
public class AuthenticationController {
    @Autowired
    CourseService courseService;
    @PostMapping(path = "/generateToken")
    public ResponseEntity<?> tokenGenerate( @RequestBody TokenDTO request){
        CustomResponse<?> response=courseService.generateToken(request);
        return Util.getResponse(response);
    }
}
