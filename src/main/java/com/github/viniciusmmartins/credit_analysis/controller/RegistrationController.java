package com.github.viniciusmmartins.credit_analysis.controller;

import com.github.viniciusmmartins.credit_analysis.dto.request.RegistrationRequestDTO;
import com.github.viniciusmmartins.credit_analysis.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v3/registration")
public class RegistrationController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody RegistrationRequestDTO registrationRequestDTO){
        return clientService.register(registrationRequestDTO);
    }

}
