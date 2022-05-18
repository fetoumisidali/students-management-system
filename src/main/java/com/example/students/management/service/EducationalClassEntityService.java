package com.example.students.management.service;

import com.example.students.management.dto.EducationalClassEntityDto;
import com.example.students.management.dto.MaterialEntityDto;
import com.example.students.management.dto.StudentEntityDto;
import com.example.students.management.entity.EducationalClassEntity;
import com.example.students.management.repository.EducationalClassEntityRepository;
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
public class EducationalClassEntityService {


    private EducationalClassEntityRepository educationalClassEntityRepository;

    @Lazy
    @Autowired
    public EducationalClassEntityService(EducationalClassEntityRepository educationalClassEntityRepository) {
        this.educationalClassEntityRepository = educationalClassEntityRepository;
    }

    public EducationalClassEntityDto saveClass(EducationalClassEntityDto educationalClassEntityDto){
        EducationalClassEntity educationalClassEntity = EducationalClassEntityDto.fromDtoToEntity(educationalClassEntityDto);
        educationalClassEntity = educationalClassEntityRepository.save(educationalClassEntity);
        educationalClassEntityDto = EducationalClassEntityDto.fromEntityToDto(educationalClassEntity);
        return educationalClassEntityDto;
    }

    public List<EducationalClassEntityDto> findAll(){
        return educationalClassEntityRepository.findAll().stream().map(EducationalClassEntityDto::fromEntityToDto).collect(Collectors.toList());
    }
    public EducationalClassEntity createClassEntity(EducationalClassEntity educationalClassEntity){
        return educationalClassEntityRepository.save(educationalClassEntity);
    }

    public EducationalClassEntityDto findById(Long id){
        return EducationalClassEntityDto.fromEntityToDto(findClassEntityById(id));
    }
    public List<StudentEntityDto> findClassStudents(Long id){
        EducationalClassEntity educationalClassEntity = findClassEntityById(id);
        return educationalClassEntity.getStudents().stream().
                map(StudentEntityDto::fromEntityToDto).collect(Collectors.toList());
    }
    public EducationalClassEntity findClassEntityById(Long id){
        return educationalClassEntityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("class not found"));
    }

    public List<MaterialEntityDto> findClassMaterials(Long id){
        EducationalClassEntity educationalClassEntity = findClassEntityById(id);
        return educationalClassEntity.getMaterials().stream().map(MaterialEntityDto::fromEntityToDto).collect(Collectors.toList());
    }
}
