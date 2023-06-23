package com.paymentGateway.student.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCode {
    private  String code;
    private String name;

    public ItemCode() {
    }

    public ItemCode(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
