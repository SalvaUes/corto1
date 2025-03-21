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
        Email emailToSave = new Email(email,random.nextBoolean());
    
        //Agregar logica para guardar el objeto emailToSave en la base de datos
        // y al mismo tiempo regresar el objeto guardado
        //Y retornarlo en lugar de new Email();
        return new Email();
    }
    
}
