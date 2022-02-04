package com.github.viniciusmmartins.credit_analysis.service;

import com.github.viniciusmmartins.credit_analysis.dto.request.LoanRequestDTO;
import com.github.viniciusmmartins.credit_analysis.dto.response.LoanResponseDTO;
import com.github.viniciusmmartins.credit_analysis.entity.Client;
import com.github.viniciusmmartins.credit_analysis.entity.Loan;
import com.github.viniciusmmartins.credit_analysis.repository.ClientRepository;
import com.github.viniciusmmartins.credit_analysis.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanService {

    private final ClientRepository clientRepository;
    private final LoanRepository loanRepository;


    public String registerLoan(Loan loan) {

        if (loan.getNumberOfInstallments() > 60) {
            throw new IllegalStateException("The number of installments must be a maximum of 60.");
        } else if (loan.getNumberOfInstallments() <= 0) {
            throw new IllegalStateException("You can pick the number of installments between 1 and 60 please.");
        } else if (loan.getFirstInstallmentDate().isAfter(loan.getRequestDate().plusMonths(3))) {
            throw new IllegalStateException("The maximum term for the first installment is 3 months after the request.");
        } else if (loan.getFirstInstallmentDate().isBefore(loan.getRequestDate())) {
            throw new IllegalStateException("Invalid date! Unless you can go back in time... ;)");
        } else {
            loanRepository.save(loan);
            return "Loan successfully requested, we'll enter in contact after the analysis is done.";
        }
    }


    public String registerL(LoanRequestDTO loanRequestDTO){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userLogin = ((UserDetails)principal).getUsername();
        Client client = clientRepository.findLoggedClientByEmail(userLogin);

        return registerLoan(
                new Loan(
                        loanRequestDTO.getValue(),
                        LocalDate.now(),
                        loanRequestDTO.getFirstInstallmentDate(),
                        loanRequestDTO.getNumberOfInstallments(),
                        client
                )
        );
    }


    public List<Loan> getLoanByClientId(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userLogin = ((UserDetails)principal).getUsername();
        Client client = clientRepository.findLoggedClientByEmail(userLogin);


        return loanRepository.findLoanByClientEmail(client.getEmail());
    }
    //TODO: implementar essa ultima parte, item 4 do Problema no readme.


    public List<LoanResponseDTO> getListOfLoanDTO(){
        return getLoanByClientId().stream().map(loan -> {
            LoanResponseDTO loanResponseDTO = new LoanResponseDTO(loan);
            return loanResponseDTO;
        }).collect(Collectors.toList());
    }


}