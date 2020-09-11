package com.example.spring.controller;

import com.example.spring.exception.ResourceNotFoundException;
import com.example.spring.model.Note;
import com.example.spring.model.User;
import com.example.spring.repository.NoteRepository;
import com.example.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/app")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        if(userRepository.findById(Long.valueOf(note.getUserId())).isPresent())
            return noteRepository.save(note);
        return null;
    }

    @GetMapping("/notes/user/{userId}")
    public List<Note> getNoteById(@PathVariable(value = "userId") String userId) {

        List<Note> data = noteRepository.findByUserId(userId);
        if(data.isEmpty())
            return (List<Note>) new ResourceNotFoundException("Notes", "userId", data);
        else
            return data;
    }

}
