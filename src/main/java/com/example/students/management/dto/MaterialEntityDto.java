package com.example.students.management.dto;

import com.example.students.management.entity.MaterialEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author Sidali Fetoumi
 * @date 5/6/2022
 * Enjoy Coding :)
 */
@Data
public class MaterialEntityDto {

    private Long id;

    private String name;


    private Long classId;

    private String className;

    private Long numberOfLectures;

    public static MaterialEntityDto fromEntityToDto(MaterialEntity materialEntity){
        MaterialEntityDto materialEntityDto = new MaterialEntityDto();
        materialEntityDto.setId(materialEntity.getId());
        materialEntityDto.setName(materialEntity.getName());
        materialEntityDto.setClassName
                (materialEntity.getEducationalClassEntity() != null ? materialEntity.getEducationalClassEntity().getName() : null);
        materialEntityDto.setClassId
                (materialEntity.getEducationalClassEntity() != null ? materialEntity.getEducationalClassEntity().getId() : null);
        materialEntityDto.setNumberOfLectures((long) materialEntity.getLectures().size());
        return materialEntityDto;
    }
    public static MaterialEntity fromDtoToEntity(MaterialEntityDto materialEntityDto){
        MaterialEntity materialEntity = new MaterialEntity();
        materialEntity.setName(materialEntityDto.getName());
        return materialEntity;
    }
}
