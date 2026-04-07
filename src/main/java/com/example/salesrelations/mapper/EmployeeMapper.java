package com.example.salesrelations.mapper;

import org.mapstruct.Mapper;
import java.util.List;

import com.example.salesrelations.dto.EmployeeDTO;
import com.example.salesrelations.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO toDTO(Employee employee);

    Employee toEntity(EmployeeDTO employeeDTO);

    List<EmployeeDTO> toDTOList(List<Employee> employees);
}