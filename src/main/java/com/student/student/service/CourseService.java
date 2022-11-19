package com.student.student.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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

        List<HashMap<String, String>> map = new LinkedList<>();
        if (!courseList.isEmpty()) {
            for (CourseModel lst : courseList) {
                HashMap<String, String> data = new HashMap<>();
                System.out.println("Dataa"+lst.toString());
                data.put("id", String.valueOf(lst.getId()));
                data.put("name", String.valueOf(lst.getTitle()));
                map.add(data);
            }
        }

        Gson gson = new Gson();
        String json = gson.toJson(courseList);
        System.out.println("Testing ------------z");
        CustomStatus customStatus;
        customStatus = CustomStatus.strip("Success");
        customStatus.setStatus(true);

        return new CustomResponse<>(customStatus, map);

    }
}
