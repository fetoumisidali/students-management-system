package com.example.students.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @author Sidali Fetoumi
 * @date 5/6/2022
 * Enjoy Coding :)
 */

@Data
@Entity
@Table(name = "STUDENT")
@AllArgsConstructor @NoArgsConstructor
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    private UUID matricule = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "class_id")
    private EducationalClassEntity educationalClassEntity;

    @OneToMany(mappedBy = "student")
    private List<StudentReportEntity> reportEntities = new ArrayList<>();

    @OneToMany(mappedBy = "studentEntity")
    private List<StudentNoteEntity> studentNoteEntities = new ArrayList<>();


    public StudentEntity(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
