package com.example.students.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Sidali Fetoumi
 * @date 5/6/2022
 * Enjoy Coding :)
 */
@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "LECTURE")
public class LectureEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private MaterialEntity materialEntity;

    @CreatedDate
    private LocalDateTime startDateTime;

    @ManyToMany
    List<StudentEntity> students = new ArrayList<StudentEntity>();

    @ManyToOne
    private EducationalClassEntity educationalClassEntity;


}

