package com.student.student.repository;


import com.student.student.Entity.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, Integer> {
    @Transactional
    @Query("FROM CourseModel")
    List<Object[]>  findModels();
}
