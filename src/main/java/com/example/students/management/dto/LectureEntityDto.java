package com.example.students.management.dto;

import com.example.students.management.entity.LectureEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author Sidali Fetoumi
 * @date 5/6/2022
 * Enjoy Coding :)
 */
@Data
public class LectureEntityDto {

    private Long id;

    private String materialName;


    private String className;

    private String startsDate;

    private Long presentStudentsNumber;

    public static LectureEntityDto fromEntityToDto(LectureEntity lectureEntity) {
        LectureEntityDto lectureEntityDto = new LectureEntityDto();
        lectureEntityDto.setId(lectureEntity.getId());
        lectureEntityDto.setMaterialName(lectureEntity.getMaterialEntity().getName());
        lectureEntityDto.setClassName(lectureEntity.getMaterialEntity().getEducationalClassEntity().getName());
        lectureEntityDto.setStartsDate(DateTimeFormatter.ofPattern
                ("dd-MM-yyyy HH:mm:ss").format(lectureEntity.getStartDateTime()));
        lectureEntityDto.setPresentStudentsNumber((long) lectureEntity.getStudents().size());
        return lectureEntityDto;
    }

}
