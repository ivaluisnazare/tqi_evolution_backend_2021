package com.userssecurity.autenticsecurityusersdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String code;

    @Column(length = 50, nullable = false)
    private Float installmentValue;
    @Column(length = 20, nullable = false)
    private Integer installmentNumber;
}
