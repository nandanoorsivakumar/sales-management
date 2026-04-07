package com.example.salesrelations.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.salesrelations.dto.CustomerDTO;
import com.example.salesrelations.entity.Customer;
import com.example.salesrelations.exception.ResourceNotFoundException;
import com.example.salesrelations.mapper.CustomerMapper;
import com.example.salesrelations.repository.CustomerRepository;
import com.example.salesrelations.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(savedCustomer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerMapper.toDTOList(customerRepository.findAll());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        return customerMapper.toDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        existingCustomer.setCustomerName(customerDTO.getCustomerName());
        existingCustomer.setEmail(customerDTO.getEmail());
        existingCustomer.setPhone(customerDTO.getPhone());
        existingCustomer.setCity(customerDTO.getCity());

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        customerRepository.delete(existingCustomer);
    }
}