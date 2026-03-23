package com.example.salesmanagement.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.example.salesmanagement.dto.EmployeeDTO;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);

    EmployeeDTO getEmployeeByName(String name);

    List<EmployeeDTO> searchEmployees(String keyword);

    EmployeeDTO getHighestSalaryEmployee();

    List<EmployeeDTO> getEmployeesByDepartmentAndSalary(String department, Double salary);

    List<EmployeeDTO> getEmployeesBySalaryRange(Double minSalary, Double maxSalary);

    EmployeeDTO getFirstEmployeeByName(String name);

    boolean checkDepartmentExists(String department);

    List<EmployeeDTO> getActiveEmployees();

    List<EmployeeDTO> getInactiveEmployees();

    List<EmployeeDTO> getEmployeesJoinedLastWeek();

    List<EmployeeDTO> getEmployeesLoggedInToday();

    List<EmployeeDTO> getTop3SalaryEmployees();
    
    Page<EmployeeDTO> getEmployeesWithPaginationAndSorting(int page, int size, String sortBy, String direction);
    
}