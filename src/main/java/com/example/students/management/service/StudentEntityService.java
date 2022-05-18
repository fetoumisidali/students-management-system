package com.example.students.management.service;

import com.example.students.management.custom_models.StudentMaterialLectures;
import com.example.students.management.dto.*;
import com.example.students.management.entity.EducationalClassEntity;
import com.example.students.management.entity.StudentEntity;
import com.example.students.management.repository.StudentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sidali Fetoumi
 * @date 5/6/2022
 * Enjoy Coding :)
 */
@Service
@Transactional
public class StudentEntityService {

    private final StudentEntityRepository studentEntityRepository;
    private final MaterialEntityService materialEntityService;
    private final LectureEntityService lectureEntityService;
    private final EducationalClassEntityService educationalClassEntityService;


    @Lazy
    @Autowired
    public StudentEntityService(StudentEntityRepository studentEntityRepository,
                                MaterialEntityService materialEntityService,
                                LectureEntityService lectureEntityService, EducationalClassEntityService educationalClassEntityService) {
        this.studentEntityRepository = studentEntityRepository;
        this.materialEntityService = materialEntityService;
        this.lectureEntityService = lectureEntityService;
        this.educationalClassEntityService = educationalClassEntityService;
    }

    public StudentEntityDto saveStudent(StudentEntityDto studentEntityDto) throws NoSuchFieldException{
        if(studentEntityDto.getClassId() == null){
            throw new NoSuchFieldException("class id cant be null");
        }
        EducationalClassEntity educationalClassEntity = educationalClassEntityService.findClassEntityById(studentEntityDto.getClassId());
        StudentEntity studentEntity = StudentEntityDto.fromDtoToEntity(studentEntityDto);
        studentEntity.setEducationalClassEntity(educationalClassEntity);
        studentEntity = studentEntityRepository.save(studentEntity);
        return StudentEntityDto.fromEntityToDto(studentEntity);
    }


    public StudentEntity findStudentEntityById(Long id){
        return studentEntityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("student not found"));
    }

    public StudentEntityDto findById(Long id){
        return StudentEntityDto.fromEntityToDto(findStudentEntityById(id));
    }

    public EducationalClassEntityDto findStudentClass(Long id){
        return EducationalClassEntityDto.fromEntityToDto(findStudentEntityById(id).getEducationalClassEntity());
    }


    public List<LectureEntityDto> findStudentsPresesntLectures(Long id){
        return lectureEntityService.findStudentsPresesntLectures(id);
    }
    public List<StudentMaterialLectures> findStudentMaterialsLectures(Long id) throws EntityNotFoundException{
        StudentEntityDto studentEntityDto = findById(id);
        if(studentEntityDto.getClassName() == null){
            throw new EntityNotFoundException("student has no class");
        }
        return materialEntityService.findByClassId(studentEntityDto.getClassId()).stream().map(materialEntityDto ->
            new StudentMaterialLectures(materialEntityDto, lectureEntityService.countByStudentsIdAndMaterialEntityId(id,materialEntityDto.getId()))
        ).collect(Collectors.toList());
    }


    public List<StudentEntityDto> findAll(){
        return studentEntityRepository.findAll().stream().map(StudentEntityDto::fromEntityToDto).collect(Collectors.toList());
    }
    public List<StudentReportEntityDto> findStudentReports(Long studentId){
        StudentEntity student = findStudentEntityById(studentId);
        return student.getReportEntities().stream().map(StudentReportEntityDto::entityToDto).collect(Collectors.toList());
    }
    public List<StudentNoteEntityDto> findStudentNotes(Long id){
        StudentEntity student = findStudentEntityById(id);
        return student.getStudentNoteEntities().stream().map(StudentNoteEntityDto::entityToDto).collect(Collectors.toList());
    }

}
