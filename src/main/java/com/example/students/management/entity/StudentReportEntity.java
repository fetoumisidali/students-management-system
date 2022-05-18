package com.example.students.management.entity;

/**
 * @author Sidali Fetoumi
 * @date 5/14/2022
 * Enjoy Coding :)
 */

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "REPORT")
@EntityListeners(AuditingEntityListener.class)
public class StudentReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @CreatedDate
    private LocalDateTime date;

    @ManyToOne
    private MaterialEntity material;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
}
