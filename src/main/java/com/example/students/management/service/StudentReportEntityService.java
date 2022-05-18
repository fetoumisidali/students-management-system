package com.example.students.management.service;

import com.example.students.management.custom_models.StudentReport;
import com.example.students.management.dto.StudentReportEntityDto;
import com.example.students.management.entity.MaterialEntity;
import com.example.students.management.entity.StudentEntity;
import com.example.students.management.entity.StudentReportEntity;
import com.example.students.management.repository.StudentReportEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sidali Fetoumi
 * @date 5/14/2022
 * Enjoy Coding :)
 */
@Service
@Transactional
public class StudentReportEntityService {


    private final StudentReportEntityRepository studentReportEntityRepository;
    private final StudentEntityService studentEntityService;
    private final MaterialEntityService materialEntityService;

    @Lazy
    @Autowired
    public StudentReportEntityService(StudentEntityService studentEntityService, StudentReportEntityRepository studentReportEntityRepository, MaterialEntityService materialEntityService) {
        this.studentEntityService = studentEntityService;
        this.studentReportEntityRepository = studentReportEntityRepository;
        this.materialEntityService = materialEntityService;
    }

    public StudentReportEntityDto createReport(StudentReport studentReport){
        StudentEntity studentEntity = studentEntityService.findStudentEntityById(studentReport.getStudentId());
        MaterialEntity materialEntity = materialEntityService.findMaterialEntityById(studentReport.getMaterialId());
        StudentReportEntity studentReportEntity = new StudentReportEntity();
        studentReportEntity.setStudent(studentEntity);
        studentReportEntity.setMaterial(materialEntity);
        studentReportEntity.setTitle(studentReport.getTitle());
        studentReportEntity = studentReportEntityRepository.save(studentReportEntity);
        return StudentReportEntityDto.entityToDto(studentReportEntity);
    }
    public List<StudentReportEntityDto> findReportsByStudentId(Long studentId){
        return studentReportEntityRepository.findByStudentId
                (studentId).stream().map(StudentReportEntityDto::entityToDto).collect(Collectors.toList());
    }
    public List<StudentReportEntityDto> findByStudentIdAndMaterialId(Long studentId,Long materialId){
        StudentEntity student = studentEntityService.findStudentEntityById(studentId);
        MaterialEntity material = materialEntityService.findMaterialEntityById(materialId);
        return studentReportEntityRepository.
                findByStudentIdAndMaterialId
                        (student.getId(),material.getId()).stream().map(StudentReportEntityDto::entityToDto)
                .collect(Collectors.toList());
    }
}
