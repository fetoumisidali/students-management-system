package com.example.students.management.dto;

import com.example.students.management.entity.StudentNoteEntity;
import lombok.Data;

/**
 * @author Sidali Fetoumi
 * @date 5/15/2022
 * Enjoy Coding :)
 */
@Data
public class StudentNoteEntityDto {

    private Long id;

    private String noteType;

    private String materialName;

    private Double note;

    public static  StudentNoteEntityDto entityToDto(StudentNoteEntity studentNoteEntity){
        StudentNoteEntityDto studentNoteEntityDto = new StudentNoteEntityDto();
        studentNoteEntityDto.setId(studentNoteEntity.getId());
        studentNoteEntityDto.setNoteType(studentNoteEntity.getNoteType());
        studentNoteEntityDto.setMaterialName(studentNoteEntity.getMaterialEntity().getName());
        studentNoteEntityDto.setNote(studentNoteEntity.getNote());
        return studentNoteEntityDto;
    }


}
