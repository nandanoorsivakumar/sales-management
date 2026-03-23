package com.example.salesmanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.salesmanagement.dto.EmployeeDTO;
import com.example.salesmanagement.response.ApiResponse;
import com.example.salesmanagement.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee API", description = "Operations related to Employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @Operation(summary = "Get all employees", description = "Fetch all employee records")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
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

    @Operation(summary = "Get employee by ID")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
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

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);

        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employee deleted successfully",
                "Deleted employee id: " + id
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployeeByName(@PathVariable String name) {
        EmployeeDTO employee = employeeService.getEmployeeByName(name);

        ApiResponse<EmployeeDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employee fetched successfully by name",
                employee
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/search/{keyword}")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> searchEmployees(@PathVariable String keyword) {
        List<EmployeeDTO> employees = employeeService.searchEmployees(keyword);

        ApiResponse<List<EmployeeDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employees fetched successfully by search",
                employees
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/highest-salary")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getHighestSalaryEmployee() {
        EmployeeDTO employee = employeeService.getHighestSalaryEmployee();

        ApiResponse<EmployeeDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Highest salary employee fetched successfully",
                employee
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/department/{department}/salary/{salary}")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getEmployeesByDepartmentAndSalary(
            @PathVariable String department,
            @PathVariable Double salary) {

        List<EmployeeDTO> employees = employeeService.getEmployeesByDepartmentAndSalary(department, salary);

        ApiResponse<List<EmployeeDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employees fetched successfully by department and salary",
                employees
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/salary-range")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getEmployeesBySalaryRange(
            @RequestParam Double minSalary,
            @RequestParam Double maxSalary) {

        List<EmployeeDTO> employees = employeeService.getEmployeesBySalaryRange(minSalary, maxSalary);

        ApiResponse<List<EmployeeDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employees fetched successfully by salary range",
                employees
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/first/{name}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getFirstEmployeeByName(@PathVariable String name) {
        EmployeeDTO employee = employeeService.getFirstEmployeeByName(name);

        ApiResponse<EmployeeDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "First employee fetched successfully by name",
                employee
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/department-exists/{department}")
    public ResponseEntity<ApiResponse<Boolean>> checkDepartmentExists(@PathVariable String department) {
        boolean exists = employeeService.checkDepartmentExists(department);

        ApiResponse<Boolean> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Department existence checked successfully",
                exists
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getActiveEmployees() {
        List<EmployeeDTO> employees = employeeService.getActiveEmployees();

        ApiResponse<List<EmployeeDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Active employees fetched successfully",
                employees
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/inactive")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getInactiveEmployees() {
        List<EmployeeDTO> employees = employeeService.getInactiveEmployees();

        ApiResponse<List<EmployeeDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Inactive employees fetched successfully",
                employees
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/joined-last-week")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getEmployeesJoinedLastWeek() {
        List<EmployeeDTO> employees = employeeService.getEmployeesJoinedLastWeek();

        ApiResponse<List<EmployeeDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employees joined last week fetched successfully",
                employees
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/logged-in-today")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getEmployeesLoggedInToday() {
        List<EmployeeDTO> employees = employeeService.getEmployeesLoggedInToday();

        ApiResponse<List<EmployeeDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employees logged in today fetched successfully",
                employees
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/top3-salary")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getTop3SalaryEmployees() {
        List<EmployeeDTO> employees = employeeService.getTop3SalaryEmployees();

        ApiResponse<List<EmployeeDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Top 3 salary employees fetched successfully",
                employees
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/page_ur")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getEmployeesWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Page<EmployeeDTO> employeePage =
                employeeService.getEmployeesWithPaginationAndSorting(page, size, sortBy, direction);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("content", employeePage.getContent());
        responseData.put("currentPage", employeePage.getNumber());
        responseData.put("totalItems", employeePage.getTotalElements());
        responseData.put("totalPages", employeePage.getTotalPages());
        responseData.put("pageSize", employeePage.getSize());
        responseData.put("isLast", employeePage.isLast());

        ApiResponse<Map<String, Object>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employees fetched successfully with pagination and sorting",
                responseData
        );

        return ResponseEntity.ok(response);
    }
}