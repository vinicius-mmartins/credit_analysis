package com.github.viniciusmmartins.credit_analysis.entity;

import com.github.viniciusmmartins.credit_analysis.security.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "client")
public class Client implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    private Long clientId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String CPF;

    @Column(nullable = false, unique = true)
    private String RG;

    @Column(nullable = false)
    private String fullAddress;

    @Column(nullable = false)
    private String income;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;                   //estas opções não são utilizadas a principio
    private Boolean locked = false;              // mas deixei preparado pra implementar funções
    private Boolean enabled = true;              // pra não precisar refatorar depois


    public Client(String name, String email, String CPF, String RG, String fullAddress, String income, String password, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.CPF = CPF;
        this.RG = RG;
        this.fullAddress = fullAddress;
        this.income = income;
        this.password = password;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singleton(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
