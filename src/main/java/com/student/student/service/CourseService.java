package com.student.student.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.student.student.DTO.CourseDto;
import com.student.student.DTO.CustomResponse;
import com.student.student.DTO.CustomStatus;
import com.student.student.DTO.TokenDTO;
import com.student.student.Entity.CourseModel;
import com.student.student.Utils.OrderedJSONObject;
import com.student.student.helpers.RestcallsHelper;
import com.student.student.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
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


    public CustomResponse<?> generateToken(String apiKey,TokenDTO request) {
        Map<String, Object> responseMap=null;

       // responseMap.put("MESSAGE", "Failed");
        // responseMap.put("STATUS", false);

        String Url ="https://api-finserve-uat.azure-api.net/authentication/api/v3/authenticate/merchant";

        JSONObject payload = new JSONObject();

        payload.put("merchantCode", request.getMerchantCode());
        payload.put("consumerSecret", request.getPassword());
        CustomStatus customStatus = null;
        try {

            System.out.println("before RESPONSE......." );
            ResponseEntity<String> response = httpsHelper(HttpMethod.POST, Url,
                    apiKey, payload.toString(), MediaType.APPLICATION_JSON, "8778888");
            System.out.println("RESPONSE..here....." +response.getBody());
            System.out.println("RESPONSE..here....." +response.getBody());
            //System.out.println("RESPONSE..code....." +response.getBody().ge);

            if (response.getStatusCodeValue() == 200) {
                customStatus = CustomStatus.strip("Empty Request");
           customStatus.setStatus(true);
           customStatus.setCode(200);
           // return new CustomResponse<>(customStatus, HttpStatus.BAD_REQUEST);
                JSONObject responseBodyObject = new JSONObject(response.getBody());
                System.out.println("Response body....Token "+responseBodyObject);
                ObjectMapper mapper = new ObjectMapper();
                String json = responseBodyObject.toString();
                    // convert JSON string to Map
                    Map<String, String> map = mapper.readValue(json, Map.class);

                    // it works
                    //Map<String, String> map = mapper.readValue(json, new TypeReference<Map<String, String>>() {});

                    System.out.println(map);



                return new CustomResponse<>(customStatus,map );
//                if (responseBodyObject.getBoolean("successful")) {
//
//                    // Format response
//                    JSONObject responseJsonObject = responseBodyObject.getJSONObject("responseObject");
//                    //  responseMap.put("DATA-OUT", formatResponseObject("66"));
//                    // responseMap.put("STATUS", true);
//                    // responseMap.put("MESSAGE", "Success");
//
//
//                }
//
//                else {
//
//
//
//                }

            }
            else{
               // customStatus = CustomStatus.strip("Empty Request");
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
