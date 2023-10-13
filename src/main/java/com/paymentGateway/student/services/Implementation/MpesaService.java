package com.paymentGateway.student.services.Implementation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.paymentGateway.student.DTO.CustomResponse;
import com.paymentGateway.student.DTO.CustomStatus;
import com.paymentGateway.student.DTO.MPESA;
import com.paymentGateway.student.Utils.Util;
import com.paymentGateway.student.services.Interfaces.MpesaServiceInterface;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import static com.paymentGateway.student.Controllers.MpesaController.runner;

@Service
@Slf4j
public class MpesaService implements MpesaServiceInterface {
    private final OkHttpClient okHttpClient;

    public MpesaService(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public void callProcessing(Object payload) {
        try {
            // JsonObject jsonObject=new JsonObject();
            System.out.println("PAYLOAD...." + payload);
            runner(payload);
        } catch (Exception ex) {
            System.out.println("Exception" + ex);
        }

    }

    @Override
    public CustomResponse<?> sTKPush(MPESA request) throws IOException {
        Map<String, Object> responseMap = null;
        CustomStatus customStatus = new CustomStatus();
        tokenGenerate();
        String Url = "https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest";
        MediaType mediaType = MediaType.parse("application/json");
        String jsonPayload = "{"
                + "\"BusinessShortCode\": \"" + request.getBusinessShortCode() + "\","
                + "\"Password\": \"" + Util.getStkPushPassword(request.getBusinessShortCode(), "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919", Util.getTransactionTimestamp()) + "\","
                + "\"Timestamp\": \"" + Util.getTransactionTimestamp() + "\","
                + "\"TransactionType\": \"CustomerPayBillOnline\","
                + "\"Amount\": " + request.getAmount() + ","
                + "\"PartyA\": \"" + request.getPartyA() + "\","
                + "\"PartyB\": \"" + request.getPartyB() + "\","
                + "\"PhoneNumber\": \"" + request.getPhoneNumber() + "\","
                + "\"CallBackURL\": \"" + request.getCallBackURL() + "\","
                + "\"AccountReference\": \"" + request.getAccountReference() + "\","
                + "\"TransactionDesc\": \"" + request.getTransactionDesc() + "\""
                + "}";

        RequestBody requestBody = RequestBody.create(jsonPayload, mediaType);

        Request request1 = new Request.Builder()
                .url(Url)
                .post(requestBody)
                .addHeader("Authorization", "Bearer " + tokenGenerate())
                .build();

        JsonObject jsonObject = new JsonObject();
        try (Response response = okHttpClient.newCall(request1).execute()) {
            // return response.body().string();

            String responseBody = response.body().string();
            // Process the response body

            jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            if (jsonObject.get("ResponseCode").getAsInt() == 0) {
                customStatus.setMessage(jsonObject.get("ResponseDescription").getAsString());

            }
        } catch (Exception ex) {
            System.out.println("EXCEPTION" + ex);
            customStatus.setMessage(jsonObject.get("errorMessage").getAsString());

        }
        return new CustomResponse<>(customStatus);
    }

    public String tokenGenerate() throws IOException {
        String  appKey="uPszISVUKQN59JhEBw9e1jrpLzCzzeCN";
        String appSecret="oMAl2hLSA25F7eoy";

        String appKeySecret = appKey+":"+appSecret;
        String bearer = new String(Base64.getEncoder().encode(appKeySecret.getBytes()));
        System.out.println("BEARER2.."+bearer);

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()


                .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                .method("GET", null)
                .addHeader("Authorization", "Basic "+bearer)
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        // Process the response body
        JsonObject jsonObject = new JsonObject();
        jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        return jsonObject.get("access_token").getAsString();

    }


}
