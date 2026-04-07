package com.example.salesrelations.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.salesrelations.dto.EmployeeDTO;
import com.example.salesrelations.response.ApiResponse;
import com.example.salesrelations.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDTO>> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO savedEmployee = employeeService.saveEmployee(employeeDTO);

        ApiResponse<EmployeeDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Employee created successfully",
                savedEmployee
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();

        ApiResponse<List<EmployeeDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employees fetched successfully",
                employees
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);

        ApiResponse<EmployeeDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employee fetched successfully",
                employee
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> updateEmployee(@PathVariable Long id,
                                                                   @Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);

        ApiResponse<EmployeeDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employee updated successfully",
                updatedEmployee
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);

        ApiResponse<Object> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employee deleted successfully",
                null
        );

        return ResponseEntity.ok(response);
    }
}