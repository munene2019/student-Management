package com.student.student.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.student.student.DTO.CourseDto;
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public CustomResponse<?> getCourses() {

        CustomResponse<?> customResponse;

        List<CourseModel> courseList = courseRepository.findModels();
        org.json.JSONObject balResp = new org.json.JSONObject();
        List<HashMap<String, String>> map = new LinkedList<>();
        if (!courseList.isEmpty()) {
            for (CourseModel lst : courseList) {
                HashMap<String, String> data = new HashMap<>();
                System.out.println("Dataa" + lst.toString());
                data.put("id", String.valueOf(lst.getId()));
                data.put("name", String.valueOf(lst.getTitle()));
                map.add(data);
            }
        }
        CustomStatus customStatus;
        customStatus = CustomStatus.strip("Success");
        customStatus.setStatus(true);

        return new CustomResponse<>(customStatus, map);

    }

    public CustomResponse<?> registerCourse(CourseDto request) {
        CourseModel courseModel = new CourseModel();

        CustomResponse<?> customResponse;
        CustomStatus customStatus = null;
        if (request.getTitle() != null) {
            CourseModel exist = courseRepository.findByTitle(request.getTitle());
            if (exist != null) {
                customStatus = CustomStatus.strip(request.getTitle() + "Already Exist");
                customStatus.setStatus(false);
                customStatus.setCode(400);
            } else {
                courseModel.setTitle(request.getTitle());
                courseRepository.save(courseModel);
                customStatus = CustomStatus.strip("Registration successful");
                customStatus.setStatus(true);
                customStatus.setCode(200);
            }


        } else {
            customStatus = CustomStatus.strip("Empty Request");
            customStatus.setStatus(false);
            customStatus.setCode(400);

        }
        return new CustomResponse<>(customStatus);
    }
}
