package com.example.students.management.custom_models;

import lombok.Data;

/**
 * @author Sidali Fetoumi
 * @date 5/15/2022
 * Enjoy Coding :)
 */
@Data
public class NoteToStudent {

    private Long studentId;

    private Long materialId;

    private String noteType;

    private Double note;
}
