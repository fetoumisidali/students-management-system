package com.example.students.management.controller;

import com.example.students.management.custom_models.StudentMaterialLectures;
import com.example.students.management.dto.LectureEntityDto;
import com.example.students.management.dto.StudentEntityDto;
import com.example.students.management.dto.StudentNoteEntityDto;
import com.example.students.management.dto.StudentReportEntityDto;
import com.example.students.management.service.StudentEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author Sidali Fetoumi
 * @date 5/7/2022
 * Enjoy Coding :)
 */
@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentEntityController {


    private final StudentEntityService studentEntityService;

    @Autowired
    public StudentEntityController(StudentEntityService studentEntityService) {
        this.studentEntityService = studentEntityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentEntityDto saveStudent(@RequestBody StudentEntityDto studentEntityDto) throws NoSuchFieldException{
        return studentEntityService.saveStudent(studentEntityDto);
    }

    @GetMapping
    public List<StudentEntityDto> findAl(){
        return studentEntityService.findAll();
    }

    @GetMapping("/{id}")
    public StudentEntityDto findById(@PathVariable Long id){
        return studentEntityService.findById(id);
    }

    @GetMapping("/{id}/lectures")
    public List<LectureEntityDto> findStudentsPresentLectures(@PathVariable Long id){
        return studentEntityService.findStudentsPresesntLectures(id);
    }
    @GetMapping("/{id}/materials")
    public List<StudentMaterialLectures> findStudentMaterialsLectures(@PathVariable Long id) throws EntityNotFoundException {
        return studentEntityService.findStudentMaterialsLectures(id);
    }
    @GetMapping("/{StudentId}/reports")
    public List<StudentReportEntityDto> findStudentReports(@PathVariable Long StudentId){
        return studentEntityService.findStudentReports(StudentId);
    }
    @GetMapping("/{id}/notes")
    public List<StudentNoteEntityDto> findStudentNotes(@PathVariable Long id){
        return studentEntityService.findStudentNotes(id);
    }
}
