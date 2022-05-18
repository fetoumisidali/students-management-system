package com.example.students.management.service;

import com.example.students.management.custom_models.NoteToStudent;
import com.example.students.management.dto.StudentNoteEntityDto;
import com.example.students.management.entity.MaterialEntity;
import com.example.students.management.entity.StudentEntity;
import com.example.students.management.entity.StudentNoteEntity;
import com.example.students.management.repository.StudentNoteEntityRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sidali Fetoumi
 * @date 5/15/2022
 * Enjoy Coding :)
 */
@Service
@Transactional
public class StudentNoteEntityService {

    private final StudentNoteEntityRepository studentNoteEntityRepository;
    private final MaterialEntityService materialEntityService;
    private final StudentEntityService studentEntityService;

    public StudentNoteEntityService(StudentNoteEntityRepository studentNoteEntityRepository, MaterialEntityService materialEntityService, StudentEntityService studentEntityService) {
        this.studentNoteEntityRepository = studentNoteEntityRepository;
        this.materialEntityService = materialEntityService;
        this.studentEntityService = studentEntityService;
    }

    public StudentNoteEntityDto saveNote(NoteToStudent noteToStudent){
        StudentEntity student = studentEntityService.findStudentEntityById(noteToStudent.getStudentId());
        MaterialEntity material = materialEntityService.findMaterialEntityById(noteToStudent.getMaterialId());
        StudentNoteEntity studentNoteEntity = new StudentNoteEntity();
        studentNoteEntity.setStudentEntity(student);
        studentNoteEntity.setMaterialEntity(material);
        studentNoteEntity.setNoteType(noteToStudent.getNoteType());
        studentNoteEntity.setNote(noteToStudent.getNote());
        studentNoteEntity = studentNoteEntityRepository.save(studentNoteEntity);
        return StudentNoteEntityDto.entityToDto(studentNoteEntity);
    }
    public List<StudentNoteEntityDto> findByStudentEntityIdAndMaterialEntityId(Long studentId, Long materialId){
        StudentEntity student = studentEntityService.findStudentEntityById(studentId);
        MaterialEntity material = materialEntityService.findMaterialEntityById(materialId);
        return studentNoteEntityRepository.findByStudentEntityIdAndMaterialEntityId
                (studentId,materialId).stream().map(StudentNoteEntityDto::entityToDto)
                .collect(Collectors.toList());
    }
}
