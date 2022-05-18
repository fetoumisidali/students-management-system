package com.example.students.management.repository;

import com.example.students.management.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface LectureEntityRepository extends JpaRepository<LectureEntity, Long> {
    List<LectureEntity> findByStudentsId(Long id);
    Long countByStudentsIdAndMaterialEntityId(Long studentsId,Long MaterialEntityId);
    @Query("select l from LectureEntity l where l.educationalClassEntity.id = ?1")
    List<LectureEntity> findByEducationalClassEntityId(Long educationalClassEntityId);
}