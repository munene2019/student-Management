package com.paymentGateway.student.repository;


import com.paymentGateway.student.Entity.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, Integer> {
    @Transactional
    @Query("FROM CourseModel")
    List<CourseModel>  findModels();
    @Query("FROM CourseModel WHERE title =:title")
    CourseModel findByTitle(String title);
}
