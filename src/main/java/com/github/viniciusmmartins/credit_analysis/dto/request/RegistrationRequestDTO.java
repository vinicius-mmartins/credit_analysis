package com.github.viniciusmmartins.credit_analysis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationRequestDTO {

    private final String name;
    private final String email;
    private final String CPF;
    private final String RG;
    private final String fullAddress;
    private final String income;
    private final String password;

}
