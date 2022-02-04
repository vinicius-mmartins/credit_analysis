package com.github.viniciusmmartins.credit_analysis.dto.response;

import com.github.viniciusmmartins.credit_analysis.entity.Loan;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanResponseDTO {

    private Long loanId;
    private Float value;
    private Integer numberOfInstallments;

    public LoanResponseDTO(Loan loan) {
        this.setLoanId(loan.getLoanId());
        this.setValue(loan.getValue());
        this.setNumberOfInstallments(loan.getNumberOfInstallments());
    }
}
