package com.example.students.management.service;

import com.example.students.management.custom_exceptions.LectureEntityException;
import com.example.students.management.custom_models.StudentToLecture;
import com.example.students.management.dto.LectureEntityDto;
import com.example.students.management.dto.StudentEntityDto;
import com.example.students.management.entity.EducationalClassEntity;
import com.example.students.management.entity.LectureEntity;
import com.example.students.management.entity.MaterialEntity;
import com.example.students.management.entity.StudentEntity;
import com.example.students.management.repository.LectureEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;



/**
 * @author Sidali Fetoumi
 * @date 5/6/2022
 * Enjoy Coding :)
 */
@Service
@Transactional
public class LectureEntityService {

    private final LectureEntityRepository lectureEntityRepository;
    private final EducationalClassEntityService educationalClassEntityService;
    private final MaterialEntityService materialEntityService;
    private final StudentEntityService studentEntityService;

    @Lazy
    @Autowired
    public LectureEntityService(LectureEntityRepository lectureEntityRepository,
                                EducationalClassEntityService educationalClassEntityService,
                                MaterialEntityService materialEntityService,
                                StudentEntityService studentEntityService) {
        this.lectureEntityRepository = lectureEntityRepository;
        this.educationalClassEntityService = educationalClassEntityService;
        this.materialEntityService = materialEntityService;
        this.studentEntityService = studentEntityService;
    }

    public LectureEntityDto saveLecture(Long materialId) throws LectureEntityException {
        MaterialEntity materialEntity = materialEntityService.findMaterialEntityById(materialId);
       if( lectureEntityRepository.findByEducationalClassEntityId(materialEntity.getEducationalClassEntity().getId()).stream().anyMatch(
               lectureEntity ->
                   LocalDateTime.now().minusMinutes(60).isBefore(lectureEntity.getStartDateTime())

       )) throw new LectureEntityException("class already have a lecture");
        /*if(materialEntity.getLectures().stream()
                .anyMatch(lectureEntity -> LocalDateTime.now().minusMinutes(60).isBefore(lectureEntity.getStartDateTime()))) throw
            new LectureEntityException("you already have a lecture");*/
        EducationalClassEntity educationalClassEntity =
                educationalClassEntityService.findClassEntityById(materialEntity.getEducationalClassEntity().getId());
        if(materialEntity.getEducationalClassEntity().getId() != educationalClassEntity.getId()){
            throw new LectureEntityException("you don't have permissions");
        }
        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setMaterialEntity(materialEntity);
        lectureEntity.setEducationalClassEntity(educationalClassEntity);
        lectureEntity = lectureEntityRepository.save(lectureEntity);
        return LectureEntityDto.fromEntityToDto(lectureEntity);
    }

    public LectureEntity findLectureEntityById(Long id){
        return lectureEntityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("lecture not found"));
    }

    public LectureEntityDto findById(Long id){
        LectureEntity lectureEntity = findLectureEntityById(id);
        return LectureEntityDto.fromEntityToDto(lectureEntity);
    }

    public List<StudentEntityDto> findLecturePresentStudents(Long id){
        LectureEntity lectureEntity = findLectureEntityById(id);
        return lectureEntity.getStudents().stream().map(StudentEntityDto::fromEntityToDto).collect(Collectors.toList());
    }
    public Long countByStudentsIdAndMaterialEntityId(Long studentId,Long materialId){
        return lectureEntityRepository.countByStudentsIdAndMaterialEntityId(studentId,materialId);
    }
    public List<LectureEntityDto> findStudentsPresesntLectures(Long id){
        return lectureEntityRepository.findByStudentsId(id)
                .stream().map(LectureEntityDto::fromEntityToDto).collect(Collectors.toList());
    }

    public List<LectureEntityDto> findAll(){
        return lectureEntityRepository.findAll().stream().map(LectureEntityDto::fromEntityToDto).collect(Collectors.toList());
    }

    public void addStudentToLecture(StudentToLecture studentToLecture) throws LectureEntityException {
        StudentEntity studentEntity = studentEntityService.findStudentEntityById(studentToLecture.getStudentId());
        LectureEntity lectureEntity = findLectureEntityById(studentToLecture.getLectureId());
        if(studentEntity.getEducationalClassEntity() != lectureEntity.getEducationalClassEntity()){
            throw new LectureEntityException("student does not belong to this class");
        }
        if(lectureEntity.getStudents().contains(studentEntity)){
            throw new LectureEntityException("student already in");
        }
        if(LocalDateTime.now().isAfter(lectureEntity.getStartDateTime().plusMinutes(50))){
            throw new LectureEntityException("lecture already done");
        }
        lectureEntity.getStudents().add(studentEntity);
        lectureEntityRepository.save(lectureEntity);
    }
}
