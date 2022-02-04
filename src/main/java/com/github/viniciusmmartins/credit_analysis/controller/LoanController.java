package com.github.viniciusmmartins.credit_analysis.controller;

import com.github.viniciusmmartins.credit_analysis.dto.request.LoanRequestDTO;
import com.github.viniciusmmartins.credit_analysis.dto.response.LoanResponseDTO;
import com.github.viniciusmmartins.credit_analysis.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v3")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping(path = "loan-request")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String requestLoan(@RequestBody LoanRequestDTO loanRequestDTO){
        return loanService.registerL(loanRequestDTO);
    }

    @GetMapping(path = "loans")
    public List<LoanResponseDTO> getLoanDTO(){
        return loanService.getListOfLoanDTO();
    }

}