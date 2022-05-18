package com.example.students.management.custom_models;

import com.example.students.management.dto.MaterialEntityDto;
import lombok.Data;

/**
 * @author Sidali Fetoumi
 * @date 5/7/2022
 * Enjoy Coding :)
 */
@Data
public class StudentMaterialLectures {

    private Long materialId;
    private String materialName;
    private Long presentCount;



    public StudentMaterialLectures(MaterialEntityDto materialEntityDto,Long presentCount){
        this.materialId = materialEntityDto.getId();
        this.materialName = materialEntityDto.getName();
        this.presentCount = presentCount;
    }
}
