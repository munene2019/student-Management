package com.student.student.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {

   @NotNull(message = "This field 'model' should not be empty", groups = {Create.class})
   @NotEmpty(message = "This field 'model' should not be empty", groups = {Create.class})
   private String  title;
   private int id;

   public interface Create {
   }
}
