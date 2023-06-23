package com.paymentGateway.student.services.Interfaces;

import com.google.gson.JsonObject;
import com.paymentGateway.student.DTO.CustomResponse;
import com.paymentGateway.student.DTO.MPESA;

import java.io.IOException;

public interface MpesaServiceInterface {
    CustomResponse<?> sTKPush(MPESA request) throws IOException;
    void callProcessing(Object payload);
}
