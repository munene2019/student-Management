package com.paymentGateway.student.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenDTO {
    @NotNull(message = "This field 'model' should not be empty", groups = {CourseDto.Create.class})
    @NotEmpty(message = "This field 'model' should not be empty", groups = {CourseDto.Create.class})
    private String  merchantCode;
    private String apiKey;
    private String password;

    public interface Create {
    }
}
