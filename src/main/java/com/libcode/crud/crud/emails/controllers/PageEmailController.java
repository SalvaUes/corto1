package com.libcode.crud.crud.emails.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.libcode.crud.crud.emails.entities.Email;
import com.libcode.crud.crud.emails.repository.EmailRepository;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class PageEmailController {

    @Autowired
    private EmailRepository emailRepository;

    @GetMapping("/test/{email}")
    public Email comprobarEmail(@PathVariable String email) {
        Random random = new Random();
        Email emailToSave = new Email(email, random.nextBoolean());

        Email savedEmail = emailRepository.save(emailToSave);
        
        return savedEmail;
    }
}
