package com.example.salesrelations.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.salesrelations.dto.CustomerDTO;
import com.example.salesrelations.response.ApiResponse;
import com.example.salesrelations.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerDTO>> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedCustomer = customerService.saveCustomer(customerDTO);

        ApiResponse<CustomerDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Customer created successfully",
                savedCustomer
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerDTO>>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();

        ApiResponse<List<CustomerDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Customers fetched successfully",
                customers
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDTO>> getCustomerById(@PathVariable Long id) {
        CustomerDTO customer = customerService.getCustomerById(id);

        ApiResponse<CustomerDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Customer fetched successfully",
                customer
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDTO>> updateCustomer(@PathVariable Long id,
                                                                   @Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);

        ApiResponse<CustomerDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Customer updated successfully",
                updatedCustomer
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);

        ApiResponse<Object> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Customer deleted successfully",
                null
        );

        return ResponseEntity.ok(response);
    }
}