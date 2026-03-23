package com.example.salesmanagement.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.salesmanagement.dto.EmployeeDTO;
import com.example.salesmanagement.entity.Employee;
import com.example.salesmanagement.exception.BadRequestException;
import com.example.salesmanagement.exception.DuplicateResourceException;
import com.example.salesmanagement.exception.ResourceNotFoundException;
import com.example.salesmanagement.mapper.EmployeeMapper;
import com.example.salesmanagement.repository.EmployeeRepository;
import com.example.salesmanagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private final static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);

        if (employee.getSalary() < 0) {
            throw new BadRequestException("Salary cannot be negative and should be greater than zero");
        }

        if (employeeRepository.existsByName(employee.getName())) {
            throw new DuplicateResourceException("Employee with this name already exists");
        }

        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found");
        }

        return employeeMapper.toDTOList(employees);
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
        existingEmployee.setActive(employeeDTO.getActive());
        existingEmployee.setJoiningDate(employeeDTO.getJoiningDate());
        existingEmployee.setLastLogin(employeeDTO.getLastLogin());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return employeeMapper.toDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        employeeRepository.delete(existingEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeByName(String name) {
    	logger.info("Fetching Employee By Name {} ",name);
        Employee employee = employeeRepository.findByNameIgnoreCase(name);
        

        if (employee == null) {
        	logger.error("Employee Not Foud   {}  ",name);
            throw new ResourceNotFoundException("Employee not found with name: " + name);
        }

        
        logger.info("Employee successfully retrieved with id: {} and name: {}", employee.getId(), employee.getName());
        return employeeMapper.toDTO(employee);
    }

    @Override
    public List<EmployeeDTO> searchEmployees(String keyword) {
        List<Employee> employees = employeeRepository.findByNameContaining(keyword);

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found containing: " + keyword);
        }

        return employeeMapper.toDTOList(employees);
    }

    @Override
    public EmployeeDTO getHighestSalaryEmployee() {
        Employee employee = employeeRepository.findTopByOrderBySalaryDesc();

        if (employee == null) {
            throw new ResourceNotFoundException("No employees found");
        }

        return employeeMapper.toDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByDepartmentAndSalary(String department, Double salary) {
        List<Employee> employees = employeeRepository.findByDepartmentAndSalaryGreaterThan(department, salary);

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No employees found in department " + department + " with salary greater than " + salary);
        }

        return employeeMapper.toDTOList(employees);
    }

    @Override
    public List<EmployeeDTO> getEmployeesBySalaryRange(Double minSalary, Double maxSalary) {
        if (minSalary < 0 || maxSalary < 0) {
            throw new BadRequestException("Salary range cannot be negative");
        }

        if (minSalary > maxSalary) {
            throw new BadRequestException("Minimum salary cannot be greater than maximum salary");
        }

        List<Employee> employees = employeeRepository.findBySalaryBetween(minSalary, maxSalary);

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No employees found between salary " + minSalary + " and " + maxSalary);
        }

        return employeeMapper.toDTOList(employees);
    }

    @Override
    public EmployeeDTO getFirstEmployeeByName(String name) {
        Employee employee = employeeRepository.findFirstByName(name);

        if (employee == null) {
            throw new ResourceNotFoundException("Employee not found with name: " + name);
        }

        return employeeMapper.toDTO(employee);
    }

    @Override
    public boolean checkDepartmentExists(String department) {
        return employeeRepository.existsByDepartment(department);
    }

    @Override
    public List<EmployeeDTO> getActiveEmployees() {
        List<Employee> employees = employeeRepository.findByActiveTrue();

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No active employees found");
        }

        return employeeMapper.toDTOList(employees);
    }

    @Override
    public List<EmployeeDTO> getInactiveEmployees() {
        List<Employee> employees = employeeRepository.findByActiveFalse();

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No inactive employees found");
        }

        return employeeMapper.toDTOList(employees);
    }

    @Override
    public List<EmployeeDTO> getEmployeesJoinedLastWeek() {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
        List<Employee> employees = employeeRepository.findByJoiningDateAfter(sevenDaysAgo);

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees joined in the last week");
        }

        return employeeMapper.toDTOList(employees);
    }

    @Override
    public List<EmployeeDTO> getEmployeesLoggedInToday() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().plusDays(1).atStartOfDay();

        List<Employee> employees = employeeRepository.findByLastLoginBetween(startOfDay, endOfDay);

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees logged in today");
        }

        return employeeMapper.toDTOList(employees);
    }

    @Override
    public List<EmployeeDTO> getTop3SalaryEmployees() {
        List<Employee> employees = employeeRepository.findTop3ByOrderBySalaryDesc();

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found");
        }

        return employeeMapper.toDTOList(employees);
    }

    @Override
    public Page<EmployeeDTO> getEmployeesWithPaginationAndSorting(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        if (employeePage.isEmpty()) {
            throw new ResourceNotFoundException("No employees found");
        }

        return employeePage.map(employeeMapper::toDTO);
    }
}