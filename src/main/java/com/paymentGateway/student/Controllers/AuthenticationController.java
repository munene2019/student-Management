package com.paymentGateway.student.Controllers;


import com.paymentGateway.student.DTO.CustomResponse;
import com.paymentGateway.student.DTO.TokenDTO;
import com.paymentGateway.student.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/authenticate")
public class AuthenticationController {
    @Autowired
    CourseService courseService;
    @PostMapping(path = "/generateToken")
    public CustomResponse<?> tokenGenerate(@RequestHeader("apiKey") String apiKey, @RequestBody TokenDTO request){
        CustomResponse<?> response=courseService.generateToken(apiKey,request);
        return response;
       // return Util.getResponse(response);
    }
}
