package com.example.students.management.repository;

import com.example.students.management.entity.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MaterialEntityRepository extends JpaRepository<MaterialEntity, Long> {
    @Query("select m from MaterialEntity m where m.educationalClassEntity.id = ?1")
    List<MaterialEntity> findByEducationalClassEntityId(Long id);
}