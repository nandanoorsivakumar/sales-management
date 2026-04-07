package com.example.salesrelations.service;

import java.util.List;
import com.example.salesrelations.dto.CustomerDTO;

public interface CustomerService {

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);
}