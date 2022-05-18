package com.example.students.management.controller;

import com.example.students.management.dto.EducationalClassEntityDto;
import com.example.students.management.dto.MaterialEntityDto;
import com.example.students.management.dto.StudentEntityDto;
import com.example.students.management.service.EducationalClassEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sidali Fetoumi
 * @date 5/6/2022
 * Enjoy Coding :)
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/class")
public class EducationalClassEntityController {

    private final EducationalClassEntityService educationalClassEntityService;

    @Autowired
    public EducationalClassEntityController(EducationalClassEntityService educationalClassEntityService) {
        this.educationalClassEntityService = educationalClassEntityService;
    }
    @GetMapping
    public List<EducationalClassEntityDto> findAll(){
        return educationalClassEntityService.findAll();
    }

    @GetMapping("/{id}")
    public EducationalClassEntityDto findById(@PathVariable Long id){
        return educationalClassEntityService.findById(id);
    }


    @GetMapping("/{id}/materials")
    public List<MaterialEntityDto> findClassMaterials(@PathVariable Long id){
        return educationalClassEntityService.findClassMaterials(id);
    }

    @GetMapping("/{id}/students")
    public List<StudentEntityDto> findClassStudents(@PathVariable Long id){
        return educationalClassEntityService.findClassStudents(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EducationalClassEntityDto saveClass(@RequestBody EducationalClassEntityDto educationalClassEntityDto){
        return educationalClassEntityService.saveClass(educationalClassEntityDto);
    }
}
