package com.example.students.management.controller;


import com.example.students.management.custom_exceptions.LectureEntityException;
import com.example.students.management.custom_models.StudentToLecture;
import com.example.students.management.dto.LectureEntityDto;
import com.example.students.management.dto.StudentEntityDto;
import com.example.students.management.service.LectureEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

/**
 * @author Sidali Fetoumi
 * @date 5/6/2022
 * Enjoy Coding :)
 */
@CrossOrigin
@RestController
@RequestMapping("/lecture")
public class LectureEntityController {

    private final LectureEntityService lectureEntityService;

    @Autowired
    public LectureEntityController(LectureEntityService lectureEntityService) {
        this.lectureEntityService = lectureEntityService;
    }

    @PostMapping("/create/{materialId}")
    @ResponseStatus(HttpStatus.CREATED)
    public LectureEntityDto saveLecture(@PathVariable Long materialId) throws LectureEntityException {
        return lectureEntityService.saveLecture(materialId);
    }

    @GetMapping("/{id}")
    public LectureEntityDto findById(@PathVariable Long id){
        return lectureEntityService.findById(id);
    }

    @PostMapping
    public void addStudentToLecture(@RequestBody StudentToLecture studentToLecture) throws LectureEntityException{
        lectureEntityService.addStudentToLecture(studentToLecture);
    }

    @GetMapping("/{id}/students")
    public List<StudentEntityDto> findLecturePresentStudents(@PathVariable Long id){
        return lectureEntityService.findLecturePresentStudents(id);
    }

    @GetMapping
    public List<LectureEntityDto> findAll(){
        return lectureEntityService.findAll();
    }


}
