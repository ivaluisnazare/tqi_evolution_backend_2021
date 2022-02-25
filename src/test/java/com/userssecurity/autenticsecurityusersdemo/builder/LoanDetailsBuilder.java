package com.userssecurity.autenticsecurityusersdemo.builder;

import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanDetailsBuilder {

    private Integer id = 1;

    @Builder.Default
    private String email = "mongret@gmail.com";

    @Builder.Default
    private String code = "000102";

    @Builder.Default
    private Double wage = 2548.58d;

    @Builder.Default
    private Double loanAmount = 1800.00d;

    @Builder.Default
    private Double feesCharged = 0.0078d;

    @Builder.Default
    private Integer numberOfInstallments = 20;

    @Builder.Default
    private Double totalToPay = 1814.04d;

    @Builder.Default
    private Double portionAmount = 90.702d;

    @Builder.Default
    private LocalDate dayOfRequest = LocalDate.now();

    @Builder.Default
    private Integer monthsToPay = 3;

    @Builder.Default
    private LocalDate payDay = LocalDate.now().plusMonths(3);

    public LoanDetails toDetails(){
        return new LoanDetails(id, email, code, wage,loanAmount, feesCharged,numberOfInstallments,totalToPay,
                portionAmount,dayOfRequest,monthsToPay,payDay);
    }


}
