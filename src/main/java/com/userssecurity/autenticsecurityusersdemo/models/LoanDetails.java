package com.userssecurity.autenticsecurityusersdemo.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tab_details")

public class LoanDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(length = 20, nullable = false)
    @NotNull
    private String email;

    @Column(length = 40, unique = true)
    private String code;

    @Column(length = 30)
    private Double wage;

    @Column(length = 50)
    private Double loanAmount;

    @Column(length = 50)
    private Double feesCharged;

    @Column(length = 20)
    private Integer numberOfInstallments;

    @Column(length = 100)
    private Double totalToPay;

    @Column(length = 100)
    private Double portionAmount;

    @Column

    private LocalDate dayOfRequest;

    @Column
    private Integer monthsToPay;

    @Column
    private LocalDate payDay;

}
