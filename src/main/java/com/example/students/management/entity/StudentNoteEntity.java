package com.example.students.management.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Sidali Fetoumi
 * @date 5/15/2022
 * Enjoy Coding :)
 */
@Data
@Entity
@Table(name = "NOTE")
public class StudentNoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Double note;

    private String noteType;

    @ManyToOne
    private StudentEntity studentEntity;

    @ManyToOne
    private MaterialEntity materialEntity;
}
