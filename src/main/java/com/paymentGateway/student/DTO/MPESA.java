package com.paymentGateway.student.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MPESA {
    private String businessShortCode;
    private String password;
    private String timestamp;
    private Integer amount;
    private String partyA;
    private String partyB;
    private String phoneNumber;
    private String callBackURL;
    private String accountReference;
    private String transactionDesc;
    public interface Create {
    }


}
