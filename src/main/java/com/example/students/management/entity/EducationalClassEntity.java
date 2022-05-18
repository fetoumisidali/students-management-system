package com.example.students.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sidali Fetoumi
 * @date 5/6/2022
 * Enjoy Coding :)
 */
@Data
@Entity
@NoArgsConstructor @AllArgsConstructor
@Table(name = "CLASS")
public class EducationalClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "educationalClassEntity")
    List<StudentEntity> students = new ArrayList<StudentEntity>();

    @OneToMany(mappedBy = "educationalClassEntity")
    private List<MaterialEntity> materials = new ArrayList<MaterialEntity>();

    public EducationalClassEntity(String name) {
        this.name = name;
    }
}
