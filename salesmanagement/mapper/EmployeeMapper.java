package com.example.salesmanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.salesmanagement.dto.EmployeeDTO;
import com.example.salesmanagement.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO toDTO(Employee employee);

    Employee toEntity(EmployeeDTO employeeDTO);

    List<EmployeeDTO> toDTOList(List<Employee> employees);
}