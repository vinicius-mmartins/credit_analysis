package com.github.viniciusmmartins.credit_analysis.repository;

import com.github.viniciusmmartins.credit_analysis.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByEmail(String email);

    Optional<Client> findClientByCPF(String CPF);

    Optional<Client> findClientByRG(String RG);

    Client findLoggedClientByEmail(String email);
}
