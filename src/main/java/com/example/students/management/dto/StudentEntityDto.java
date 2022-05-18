package com.example.students.management.dto;

import com.example.students.management.entity.StudentEntity;
import lombok.Data;

import java.util.UUID;

/**
 * @author Sidali Fetoumi
 * @date 5/6/2022
 * Enjoy Coding :)
 */
@Data
public class StudentEntityDto {

    private Long id;

    private String firstname;

    private String lastname;

    private UUID matricule;

    private String className;

    private Long classId;


    public static StudentEntityDto fromEntityToDto(StudentEntity studentEntity){
        StudentEntityDto studentEntityDto = new StudentEntityDto();
        studentEntityDto.setId(studentEntity.getId());
        studentEntityDto.setFirstname(studentEntity.getFirstname());
        studentEntityDto.setLastname(studentEntity.getLastname());
        studentEntityDto.setMatricule(studentEntity.getMatricule());
        studentEntityDto.setClassName(studentEntity.getEducationalClassEntity().getName());
        studentEntityDto.setClassId(studentEntity.getEducationalClassEntity().getId());

        return studentEntityDto;
    }

    public static StudentEntity fromDtoToEntity(StudentEntityDto studentEntityDto){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFirstname(studentEntityDto.getFirstname());
        studentEntity.setLastname(studentEntityDto.getLastname());
        return studentEntity;
    }

}
