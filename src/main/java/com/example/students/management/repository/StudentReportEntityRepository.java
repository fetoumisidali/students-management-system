package com.example.students.management.repository;

import com.example.students.management.entity.StudentReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentReportEntityRepository extends JpaRepository<StudentReportEntity, Long> {
    @Query("select s from StudentReportEntity s where s.student.id = ?1")
    List<StudentReportEntity> findByStudentId(Long studentId);
    @Query("select s from StudentReportEntity s where s.student.id = ?1 and s.material.id = ?2")
    List<StudentReportEntity> findByStudentIdAndMaterialId(Long studentId, Long materialId);
}