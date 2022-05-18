package com.example.students.management.controller;

import com.example.students.management.custom_models.StudentPresense;
import com.example.students.management.dto.LectureEntityDto;
import com.example.students.management.dto.MaterialEntityDto;
import com.example.students.management.dto.StudentNoteEntityDto;
import com.example.students.management.dto.StudentReportEntityDto;
import com.example.students.management.service.MaterialEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sidali Fetoumi
 * @date 5/7/2022
 * Enjoy Coding :)
 */
@CrossOrigin
@RestController
@RequestMapping("/material")
public class MaterialEntityController {

    private final MaterialEntityService materialEntityService;

    public MaterialEntityController(MaterialEntityService materialEntityService) {
        this.materialEntityService = materialEntityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialEntityDto saveMaterial(@RequestBody MaterialEntityDto materialEntityDto) throws NoSuchFieldException{
        return materialEntityService.saveMaterial(materialEntityDto);
    }

    @GetMapping
    public List<MaterialEntityDto> findAll(){
        return materialEntityService.findAll();
    }

    @GetMapping("/{id}/lectures")
    public List<LectureEntityDto> findLectures(@PathVariable Long id){
        return materialEntityService.findLectures(id);
    }

    @GetMapping("/{id}")
    public MaterialEntityDto findById(@PathVariable Long id){
        return materialEntityService.findById(id);
    }
    @GetMapping("/{id}/students")
    public List<StudentPresense> findStudents(@PathVariable Long id){
        return materialEntityService.findStudents(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        materialEntityService.deleteById(id);
    }


    @GetMapping("/{materialId}/students/{studentId}/reports")
    public List<StudentReportEntityDto> findMaterialStudentsReports
            (@PathVariable Long materialId,@PathVariable Long studentId){
        return materialEntityService.findMaterialStudentsReports(studentId,materialId);
    }
    @GetMapping("/{materialId}/students/{studentId}/notes")
    public List<StudentNoteEntityDto> findMaterialStudentsNotes(@PathVariable Long materialId,@PathVariable  Long studentId){
        return materialEntityService.findMaterialStudentsNotes(studentId,materialId);
    }
}
