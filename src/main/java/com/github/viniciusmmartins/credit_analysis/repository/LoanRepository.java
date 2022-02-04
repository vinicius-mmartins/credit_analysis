package com.github.viniciusmmartins.credit_analysis.repository;

import com.github.viniciusmmartins.credit_analysis.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("SELECT cid FROM Loan cid JOIN FETCH cid.client")
    List<Loan> findLoanByClientId(@Param("clienteId") Long clientId);


    @Query("SELECT cid FROM Loan cid JOIN FETCH cid.client")
    List<Loan> findLoanByClientEmail(@Param("email") String email);

}
