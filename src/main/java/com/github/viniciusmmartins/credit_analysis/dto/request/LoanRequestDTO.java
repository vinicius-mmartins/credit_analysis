package com.github.viniciusmmartins.credit_analysis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class LoanRequestDTO {

    private final Float value;
    private final LocalDate firstInstallmentDate;
    private final Integer numberOfInstallments;
}
