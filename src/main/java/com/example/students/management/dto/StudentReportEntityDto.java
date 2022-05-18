package com.example.students.management.dto;

import com.example.students.management.entity.StudentReportEntity;
import lombok.Data;

import java.time.format.DateTimeFormatter;

/**
 * @author Sidali Fetoumi
 * @date 5/14/2022
 * Enjoy Coding :)
 */
@Data
public class StudentReportEntityDto {

    private Long reportId;

    private Long studentId;

    private String materialName;

    private String title;

    private String firstName;

    private String lastName;

    private String date;

    public static StudentReportEntityDto entityToDto(StudentReportEntity studentReportEntity){
        StudentReportEntityDto studentReportEntityDto = new StudentReportEntityDto();
        studentReportEntityDto.setReportId(studentReportEntity.getId());
        studentReportEntityDto.setStudentId(studentReportEntity.getStudent().getId());
        studentReportEntityDto.setMaterialName(studentReportEntity.getMaterial().getName());
        studentReportEntityDto.setTitle(studentReportEntity.getTitle());
        studentReportEntityDto.setFirstName(studentReportEntity.getStudent().getFirstname());
        studentReportEntityDto.setLastName(studentReportEntity.getStudent().getLastname());
        studentReportEntityDto.setDate(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(studentReportEntity.getDate()));
        return studentReportEntityDto;
    }

}
