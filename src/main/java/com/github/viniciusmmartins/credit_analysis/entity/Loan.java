package com.github.viniciusmmartins.credit_analysis.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @SequenceGenerator(
            name = "loan_sequence",
            sequenceName = "loan_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loan_sequence"
    )
    private Long loanId;

    @Column(nullable = false)
    private Float value;

    private LocalDate requestDate;

    @Column(nullable = false)
    private LocalDate firstInstallmentDate;

    @Column(nullable = false)
    private Integer numberOfInstallments;

    @ManyToOne(targetEntity = Client.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    public Loan(Float value, LocalDate requestDate, LocalDate firstInstallmentDate, Integer numberOfInstallments, Client client) {
        this.value = value;
        this.requestDate = requestDate;
        this.firstInstallmentDate = firstInstallmentDate;
        this.numberOfInstallments = numberOfInstallments;
        this.client = client;
    }
}
