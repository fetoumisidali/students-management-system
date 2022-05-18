package com.example.students.management.repository;

import com.example.students.management.entity.EducationalClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationalClassEntityRepository extends JpaRepository<EducationalClassEntity, Long> {
}