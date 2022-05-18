package com.example.students.management.service;

import com.example.students.management.custom_models.StudentPresense;
import com.example.students.management.dto.LectureEntityDto;
import com.example.students.management.dto.MaterialEntityDto;
import com.example.students.management.dto.StudentNoteEntityDto;
import com.example.students.management.dto.StudentReportEntityDto;
import com.example.students.management.entity.EducationalClassEntity;
import com.example.students.management.entity.MaterialEntity;
import com.example.students.management.entity.StudentEntity;
import com.example.students.management.repository.MaterialEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sidali Fetoumi
 * @date 5/7/2022
 * Enjoy Coding :)
 */
@Service
@Transactional
public class MaterialEntityService {

    private final MaterialEntityRepository materialEntityRepository;
    private final EducationalClassEntityService educationalClassEntityService;
    private final LectureEntityService lectureEntityService;
    private final StudentReportEntityService studentReportEntityService;
    private final StudentNoteEntityService studentNoteEntityService;

    @Lazy
    @Autowired
    public MaterialEntityService(MaterialEntityRepository materialEntityRepository, EducationalClassEntityService educationalClassEntityService, LectureEntityService lectureEntityService, StudentReportEntityService studentReportEntityService, StudentNoteEntityService studentNoteEntityService) {
        this.materialEntityRepository = materialEntityRepository;
        this.educationalClassEntityService = educationalClassEntityService;
        this.lectureEntityService = lectureEntityService;
        this.studentReportEntityService = studentReportEntityService;
        this.studentNoteEntityService = studentNoteEntityService;
    }
    public List<MaterialEntityDto> findByClassId(Long id){
        return materialEntityRepository.findByEducationalClassEntityId(id).stream().map(MaterialEntityDto::fromEntityToDto).collect(Collectors.toList());
    }

    public MaterialEntityDto saveMaterial(MaterialEntityDto materialEntityDto) throws NoSuchFieldException{
        if (materialEntityDto.getClassId() == null){
            throw new NoSuchFieldException("id must not be null");
        }
        EducationalClassEntity educationalClassEntity = educationalClassEntityService.findClassEntityById(materialEntityDto.getClassId());
        MaterialEntity materialEntity = MaterialEntityDto.fromDtoToEntity(materialEntityDto);
        materialEntity.setEducationalClassEntity(educationalClassEntity);
        materialEntity = materialEntityRepository.save(materialEntity);
        return MaterialEntityDto.fromEntityToDto(materialEntity);
    }
    MaterialEntity findMaterialEntityById(Long id){
        return materialEntityRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("material with id " + id + " not found"));
    }
    public MaterialEntityDto findById(Long id){
        return MaterialEntityDto.fromEntityToDto(findMaterialEntityById(id));
    }

    public List<MaterialEntityDto> findAll() {
        return materialEntityRepository.findAll().stream().map(MaterialEntityDto::fromEntityToDto).collect(Collectors.toList());
    }
    public void deleteById(Long id){
        MaterialEntity materialEntity = findMaterialEntityById(id);
        materialEntityRepository.deleteById(id);
    }
    public List<LectureEntityDto> findLectures(Long id){
        return findMaterialEntityById(id).getLectures()
                .stream().map(LectureEntityDto::fromEntityToDto).collect(Collectors.toList());
    }
    public List<StudentPresense> findStudents(Long id){
        MaterialEntity materialEntity = findMaterialEntityById(id);
        return materialEntity.getEducationalClassEntity().getStudents().stream().map(
                studentEntity -> new StudentPresense(studentEntity.getId(),studentEntity.getFirstname(),studentEntity.getLastname(),
                lectureEntityService.countByStudentsIdAndMaterialEntityId(studentEntity.getId(),
                        materialEntity.getId()))).collect(Collectors.toList());
    }
    public List<StudentReportEntityDto> findMaterialStudentsReports(Long studentId,Long materialId){
        return studentReportEntityService.findByStudentIdAndMaterialId(studentId,materialId);
    }
    public List<StudentNoteEntityDto> findMaterialStudentsNotes(Long studentId,Long materialId){
        return studentNoteEntityService.findByStudentEntityIdAndMaterialEntityId(studentId,materialId);
    }


}
