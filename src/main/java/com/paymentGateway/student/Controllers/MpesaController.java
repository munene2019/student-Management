package com.paymentGateway.student.Controllers;

import com.paymentGateway.student.DTO.CustomResponse;
import com.paymentGateway.student.DTO.MPESA;
import com.paymentGateway.student.services.Interfaces.MpesaServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/mpesa")
public class MpesaController {

    MpesaServiceInterface mpesaServiceInterface;

    public MpesaController(MpesaServiceInterface mpesaServiceInterface) {
        this.mpesaServiceInterface = mpesaServiceInterface;

    }


    @PostMapping(path = "/pay")
    public CustomResponse<?> mpesaPay(@RequestBody MPESA request) throws IOException {
        System.out.println("STK PUSH REQUEST..." + request);
        CustomResponse<?> response = mpesaServiceInterface.sTKPush(request);

        return response;
    }
        @PostMapping(path = "/callback")
        public ResponseEntity<String> callBack (@RequestBody Object payload)  {
            System.out.println("STK PUSH CALLBACK..." + payload);
            //jsonObject = JsonParser.(payload).getAsJsonObject();
           // CustomResponse<?> response = mpesaServiceInterface.sTKPush(request);
            return ResponseEntity.ok("Callback received successfully");
            //return response;

            // return Util.getResponse(response);
        }
    }

