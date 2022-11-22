package com.student.student.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.student.student.DTO.CourseDto;
import com.student.student.DTO.CustomResponse;
import com.student.student.DTO.CustomStatus;
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

        generateToken("343434");
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


    private void generateToken(String account) {

       // responseMap.put("MESSAGE", "Failed");
       // responseMap.put("STATUS", false);

        String Url ="https://api-finserve-uat.azure-api.net/authentication/api/v3/authenticate/merchant";

        JSONObject payload = new JSONObject();

        payload.put("merchantCode", "7712521263");
        payload.put("consumerSecret", "sh1Q57q6g3QCg1H2hM0cKoSCNHw94v");
        try {

            System.out.println("before RESPONSE......." );
            ResponseEntity<String> response = httpsHelper(HttpMethod.POST, Url,
                    "65efggggggggrgg", payload.toString(), MediaType.APPLICATION_JSON, "8778888");
            System.out.println("RESPONSE......." +response.toString());

            if (response.getStatusCodeValue() == 200) {
                JSONObject responseBodyObject = new JSONObject(response.getBody());
                System.out.println("Response body....Token "+responseBodyObject.get("accessToken"));
                if (responseBodyObject.getBoolean("successful")) {

                    // Format response
                    JSONObject responseJsonObject = responseBodyObject.getJSONObject("responseObject");
                  //  responseMap.put("DATA-OUT", formatResponseObject("66"));
                   // responseMap.put("STATUS", true);
                   // responseMap.put("MESSAGE", "Success");


                } else {



                }

            }

        } catch (Exception ex) {

           // serviceExceptionHandler(ex, logged, responseMap, account);

        } finally {
            // Persist


        }

    }
    private JSONObject formatResponseObject(JSONObject responseBodyObject) throws JsonProcessingException {
        JSONObject customResponseObject = new OrderedJSONObject();

        JSONObject responseJsonObject = new JSONObject();

        JSONObject responseObject = responseBodyObject.getJSONObject("responseObject");

        // Creates response Object
        responseJsonObject.put("currency", responseObject.get("currency").toString());
        JSONObject available = new JSONObject();
        available.put("amount", responseObject.get("ammount"));
        available.put("type", "Available");
        JSONObject current = new JSONObject();
        current.put("amount", responseObject.get("ammount"));
        current.put("type", "Current");
        JSONArray balances = new JSONArray();
        balances.put(available);
        balances.put(current);
        responseJsonObject.put("balances", balances);

        customResponseObject.put("status", true);
        customResponseObject.put("code", 0);
        customResponseObject.put("message", "success");

        customResponseObject.put("data", responseJsonObject);

        JSONObject metadata = new JSONObject();
      //  metadata.put("merchantId", merchantId);
      //  metadata.put("serviceId", serviceId);
        metadata.put("logged", true);
        customResponseObject.put("metadata", metadata);
        return customResponseObject;

    }
}
