package com.example.students.management.repository;

import com.example.students.management.entity.StudentNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentNoteEntityRepository extends JpaRepository<StudentNoteEntity, Long> {
    @Query("select s from StudentNoteEntity s where s.studentEntity.id = ?1 and s.materialEntity.id = ?2")
    List<StudentNoteEntity> findByStudentEntityIdAndMaterialEntityId(Long studentId, Long materialId);
}