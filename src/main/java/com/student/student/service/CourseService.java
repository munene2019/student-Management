package com.student.student.service;

import com.google.gson.Gson;
import com.student.student.DTO.CustomResponse;
import com.student.student.DTO.CustomStatus;
import com.student.student.Entity.CourseModel;
import com.student.student.models.Course;
import com.student.student.models.PagedData;
import com.student.student.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public CustomResponse<?> getCourses() {

        CustomResponse<?> customResponse;

        List<Object[]> courseList = courseRepository.findModels();

        Gson gson = new Gson();
        String json = gson.toJson(courseList);
        System.out.println("Testing ------------z");

        CustomStatus customStatus;
        customStatus = CustomStatus.strip("Success");
        customStatus.setStatus(true);

//    }
//    catch (Exception ex) {
//        System.out.println("Exception!!" + ex);
//    }
        return new CustomResponse<>(customStatus, json);

    }
}
