package com.example.students.management.custom_models;

import lombok.Data;

/**
 * @author Sidali Fetoumi
 * @date 5/9/2022
 * Enjoy Coding :)
 */
@Data
public class StudentPresense {
    private Long studentId;
    private String studentFirstname;
    private String studentLastName;
    private Long presenseNumber;

    public StudentPresense(Long studentId, String studentFirstname, String studentLastName, Long presenseNumber) {
        this.studentId = studentId;
        this.studentFirstname = studentFirstname;
        this.studentLastName = studentLastName;
        this.presenseNumber = presenseNumber;
    }
}
