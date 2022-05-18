package com.example.students.management.custom_models;

import lombok.Data;

import java.util.UUID;

/**
 * @author Sidali Fetoumi
 * @date 5/9/2022
 * Enjoy Coding :)
 */
@Data
public class StudentToLecture {
    private Long lectureId;
    private Long studentId;
}
