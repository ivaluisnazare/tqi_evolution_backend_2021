package com.userssecurity.autenticsecurityusersdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
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
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @CPF
    @Column(nullable = false)
    private String cpf;

    @Column(length = 8, nullable = false)
    private String rg;

    @Column(length = 50, nullable = false)
    private String address;

}
