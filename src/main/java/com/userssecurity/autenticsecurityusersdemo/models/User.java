package com.userssecurity.autenticsecurityusersdemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(length = 20, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 40, nullable = false)
    private String code;

    @Column(length = 30, nullable = false)
    private Double wage;

    @Column(length = 50, nullable = false)
    private Double loanAmount;

    @Column(length = 50, nullable = false)
    private Double feesCharged;

    @Column(length = 20, nullable = false)
    private Integer numberOfInstallments;

    @Column(length = 100)
    private Double totalToPay;

    @Column(length = 100)
    private Double portionAmount;

    @Column(nullable = false)
    private Integer monthsToPay;

    @Column(nullable = false)
    private LocalDate payDay;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tab_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    private List<String> roles = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "userLoan_id")
    private List<LoanDetails> loanDetails = new ArrayList<>();

}
