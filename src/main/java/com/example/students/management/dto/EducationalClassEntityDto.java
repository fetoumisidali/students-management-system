package com.example.students.management.dto;

import com.example.students.management.entity.EducationalClassEntity;
import lombok.Data;

/**
 * @author Sidali Fetoumi
 * @date 5/6/2022
 * Enjoy Coding :)
 */
@Data
public class EducationalClassEntityDto {

    private Long id;
    private String name;
    private Long studentsNumber;
    private Long numberOfMaterials;

    public static EducationalClassEntityDto fromEntityToDto(EducationalClassEntity educationalClassEntity){
        EducationalClassEntityDto educationalClassEntityDto = new EducationalClassEntityDto();
        educationalClassEntityDto.setId(educationalClassEntity.getId());
        educationalClassEntityDto.setName(educationalClassEntity.getName());
        educationalClassEntityDto.setStudentsNumber((long) educationalClassEntity.getStudents().size());
        educationalClassEntityDto.setNumberOfMaterials((long) educationalClassEntity.getMaterials().size());
        return educationalClassEntityDto;
    }

    public static EducationalClassEntity fromDtoToEntity(EducationalClassEntityDto educationalClassEntityDto){
        EducationalClassEntity educationalClassEntity = new EducationalClassEntity();
        educationalClassEntity.setName(educationalClassEntityDto.getName());
        return educationalClassEntity;
    }

}
