package com.example.students.management.repository;

import com.example.students.management.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StudentEntityRepository extends JpaRepository<StudentEntity, Long> {
}