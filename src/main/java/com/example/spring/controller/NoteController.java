package com.example.spring.controller;

import com.example.spring.exception.DataAlreadyPresentException;
import com.example.spring.exception.ResourceNotFoundException;
import com.example.spring.model.Note;
import com.example.spring.model.User;
import com.example.spring.repository.NoteRepository;
import com.example.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/app")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/notes")
    public String createNote(@Valid @RequestBody Note note) {
        try {
            if(userRepository.findById(Long.valueOf(note.getUserId())).isPresent())
                noteRepository.save(note);
            else throw new Exception();
        }catch (Exception e)
        {
            throw new ResourceNotFoundException("User is not registered with Id:" + note.getUserId());
        }
        return "success";

    }

    @GetMapping("/notes/user/{userId}")
    public List<Note> getNoteById(@PathVariable(value = "userId") String userId) {
        try {
            List<Note> resp = noteRepository.findByUserId(userId);
            if(resp.isEmpty())
                throw new Exception();
            else
                return resp;
        }catch (Exception e){
            throw new ResourceNotFoundException(userId,"user_id",userId);
        }
    }

}
