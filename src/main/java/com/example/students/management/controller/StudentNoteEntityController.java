package com.example.students.management.controller;

import com.example.students.management.custom_models.NoteToStudent;
import com.example.students.management.dto.StudentNoteEntityDto;
import com.example.students.management.service.StudentNoteEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sidali Fetoumi
 * @date 5/15/2022
 * Enjoy Coding :)
 */

@RestController
@RequestMapping("/notes")
public class StudentNoteEntityController {

    private final StudentNoteEntityService studentNoteEntityService;

    public StudentNoteEntityController(StudentNoteEntityService studentNoteEntityService) {
        this.studentNoteEntityService = studentNoteEntityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentNoteEntityDto saveNote(@RequestBody NoteToStudent noteToStudent){
        return studentNoteEntityService.saveNote(noteToStudent);
    }
}
