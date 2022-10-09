package com.student.student.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name="courses")
@AllArgsConstructor
@NoArgsConstructor
public class CourseModel {

@Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    Integer id;
@Column(name="Title")
    private String Title;


}
