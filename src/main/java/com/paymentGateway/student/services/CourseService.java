package com.paymentGateway.student.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentGateway.student.DTO.CourseDto;
import com.paymentGateway.student.DTO.CustomResponse;
import com.paymentGateway.student.DTO.CustomStatus;
import com.paymentGateway.student.DTO.TokenDTO;
import com.paymentGateway.student.Entity.CourseModel;
import com.paymentGateway.student.helpers.RestcallsHelper;
import com.paymentGateway.student.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class CourseService extends RestcallsHelper {
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
        System.out.println("Dataa" + request.getTitle());
        CourseModel courseModel = new CourseModel();

        // generateToken("343434");
        // CustomResponse<?> customResponse;
        CustomStatus customStatus = null;
        if (request != null) {
            CourseModel exist = courseRepository.findByTitle(request.getTitle());
            if (exist != null) {
                customStatus = CustomStatus.strip(request.getTitle() + " Already Exist");
                customStatus.setCode(HttpStatus.BAD_REQUEST.value());
                customStatus.setStatus(false);
                return new CustomResponse<>(customStatus, HttpStatus.BAD_REQUEST);
            } else {
                courseModel.setTitle(request.getTitle());
                courseRepository.save(courseModel);
                customStatus = CustomStatus.strip("Registration successful");
                customStatus.setStatus(true);
            }

        } else {
            customStatus = CustomStatus.strip("Empty Request");
            customStatus.setStatus(false);
            customStatus.setCode(HttpStatus.BAD_REQUEST.value());
            return new CustomResponse<>(customStatus, HttpStatus.BAD_REQUEST);

        }
        return new CustomResponse<>(customStatus);
    }


    public CustomResponse<?> generateToken(String apiKey, TokenDTO request) {
        Map<String, Object> responseMap = null;
        String Url = "https://api-finserve-uat.azure-api.net/authentication/api/v3/authenticate/merchant";

        JSONObject payload = new JSONObject();



        payload.put("merchantCode", request.getMerchantCode());
        payload.put("consumerSecret", request.getPassword());
        CustomStatus customStatus = null;
        try {

            ResponseEntity<String> response = httpsHelper(HttpMethod.POST, Url,
                    apiKey, payload.toString(), MediaType.APPLICATION_JSON, "8778888");

            if (response.getStatusCodeValue() == 200) {
                customStatus = CustomStatus.strip("success");
                customStatus.setStatus(true);
                customStatus.setCode(200);
                JSONObject responseBodyObject = new JSONObject(response.getBody());
                ObjectMapper mapper = new ObjectMapper();
                String json = responseBodyObject.toString();
                // convert JSON string to Map
                Map<String, String> map = mapper.readValue(json, Map.class);
                System.out.println(map);
                return new CustomResponse<>(customStatus, map);

            } else if (response.getStatusCodeValue() == 401) {
                customStatus = CustomStatus.strip("Empty Request");
                customStatus.setStatus(false);
                customStatus.setCode(401);
                JSONObject responseBodyObject = new JSONObject(response.getBody());
                ObjectMapper mapper = new ObjectMapper();
                String json = responseBodyObject.toString();
                // convert JSON string to Map
                Map<String, String> map = mapper.readValue(json, Map.class);
                customStatus.setMessage(map.get("message"));
            } else {
                System.out.println(response.getStatusCode());
                    customStatus.setStatus(false);
                customStatus.setCode((response.getStatusCodeValue()));
                return new CustomResponse<>(customStatus, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {

            // serviceExceptionHandler(ex, logged, responseMap, account);

        } finally {
            // Persist


        }
        return new CustomResponse<>(customStatus);
    }


}
