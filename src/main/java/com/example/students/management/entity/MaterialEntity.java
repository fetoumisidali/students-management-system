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

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "MATERIAL")
public class MaterialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private EducationalClassEntity educationalClassEntity;


    @OneToMany(mappedBy = "materialEntity")
    private List<LectureEntity> lectures = new ArrayList<>();

    public MaterialEntity(String name) {
        this.name = name;
    }
}
