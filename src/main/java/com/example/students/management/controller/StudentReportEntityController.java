package com.example.students.management.controller;

import com.example.students.management.custom_models.StudentReport;
import com.example.students.management.dto.StudentReportEntityDto;
import com.example.students.management.service.StudentReportEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sidali Fetoumi
 * @date 5/14/2022
 * Enjoy Coding :)
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/report")
public class StudentReportEntityController {

    private final StudentReportEntityService studentReportEntityService;

    @Autowired
    public StudentReportEntityController(StudentReportEntityService studentReportEntityService) {
        this.studentReportEntityService = studentReportEntityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentReportEntityDto createReport(@RequestBody StudentReport studentReport){
        return studentReportEntityService.createReport(studentReport);
    }
}
