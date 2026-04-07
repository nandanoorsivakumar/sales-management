package com.example.salesrelations.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.salesrelations.dto.EmployeeDTO;
import com.example.salesrelations.entity.Employee;
import com.example.salesrelations.exception.ResourceNotFoundException;
import com.example.salesrelations.mapper.EmployeeMapper;
import com.example.salesrelations.repository.EmployeeRepository;
import com.example.salesrelations.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeMapper.toDTOList(employeeRepository.findAll());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        return employeeMapper.toDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setDepartment(employeeDTO.getDepartment());
        existingEmployee.setSalary(employeeDTO.getSalary());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return employeeMapper.toDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        employeeRepository.delete(existingEmployee);
    }
}