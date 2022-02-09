package com.userssecurity.autenticsecurityusersdemo.dtos;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailsDTO {

    private Integer id;

    @NotNull
    private String email;

    @NotNull
    private String code;

    @NotNull
    private Double wage;

    @NotNull
    private Double loanAmount;

    @NotNull
    private Double feesCharged;

    @NotNull
    private Integer numberOfInstallments;

    @NotNull
    private Double totalToPay;

    @NotNull
    private Double portionAmount;

    @NotNull
    private LocalDate dayOfRequest;

    @NotNull
    private Integer monthsToPay;

    @NotNull
    private LocalDate payDay;

}

