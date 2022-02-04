package com.github.viniciusmmartins.credit_analysis.dto.response;

import com.github.viniciusmmartins.credit_analysis.entity.Loan;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LoanDetailsResponseDTO {

    private Long loanId;
    private Float value;
    private Integer numberOfInstallments;
    private LocalDate firstInstallmentDate;

    private String email;
    private String income;

    public LoanDetailsResponseDTO(Loan loan) {
        this.setLoanId(loan.getLoanId());
        this.setValue(loan.getValue());
        this.setNumberOfInstallments(loan.getNumberOfInstallments());
        this.setFirstInstallmentDate(loan.getFirstInstallmentDate());
        this.setEmail(loan.getClient().getEmail());
        this.setIncome(loan.getClient().getIncome());
    }
}
