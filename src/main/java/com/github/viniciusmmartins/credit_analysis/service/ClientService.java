package com.github.viniciusmmartins.credit_analysis.service;

import com.github.viniciusmmartins.credit_analysis.dto.request.RegistrationRequestDTO;
import com.github.viniciusmmartins.credit_analysis.entity.Client;
import com.github.viniciusmmartins.credit_analysis.repository.ClientRepository;
import com.github.viniciusmmartins.credit_analysis.security.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with e-mail %s not found";
    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clientRepository.findClientByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG)));
    }



    public String registerClient(Client client) {

        boolean clientAlreadyRegistered = clientRepository.findClientByEmail(client.getEmail()).isPresent();
        if(clientAlreadyRegistered){
            throw new IllegalStateException("E-mail already taken!");
        }
        boolean cpfTaken = clientRepository.findClientByCPF(client.getCPF()).isPresent();
        if(cpfTaken){
            throw new IllegalStateException("CPF already taken!");
        }
        boolean rgTaken = clientRepository.findClientByRG(client.getRG()).isPresent();
        if(rgTaken){
            throw new IllegalStateException("RG already taken!");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);

        clientRepository.save(client);

        return "You're registered!";
    }



    public String register(RegistrationRequestDTO registrationRequestDTO) {
        return registerClient(
                new Client(
                        registrationRequestDTO.getName(),
                        registrationRequestDTO.getEmail(),
                        registrationRequestDTO.getCPF(),
                        registrationRequestDTO.getRG(),
                        registrationRequestDTO.getFullAddress(),
                        registrationRequestDTO.getIncome(),
                        registrationRequestDTO.getPassword(),
                        UserRole.USER
                )
        );
    }
}
