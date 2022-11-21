package com.student.student.Utils;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "response")
public class BadRequestException {
    public  String message;
    public List<?> details;

    public BadRequestException(List<?> details, String message){
        super();
        this.message = message;
        this.details = details;
    }
}
