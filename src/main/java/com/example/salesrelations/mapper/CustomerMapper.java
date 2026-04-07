package com.example.salesrelations.mapper;

import org.mapstruct.Mapper;
import java.util.List;

import com.example.salesrelations.dto.CustomerDTO;
import com.example.salesrelations.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDTO(Customer customer);

    Customer toEntity(CustomerDTO customerDTO);

    List<CustomerDTO> toDTOList(List<Customer> customers);
}