package com.userssecurity.autenticsecurityusersdemo.mapper;


import com.userssecurity.autenticsecurityusersdemo.dtos.DetailsDTO;

import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DetailsMapper {

    DetailsMapper INSTANCE = Mappers.getMapper(DetailsMapper.class);
    LoanDetails toModel(DetailsDTO detailsDTO) ;
    DetailsDTO toDTO(LoanDetails loanDetails) ;


}
