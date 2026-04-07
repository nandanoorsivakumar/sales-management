package com.example.salesrelations.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.salesrelations.dto.OrderDTO;
import com.example.salesrelations.dto.OrderResponseDTO;
import com.example.salesrelations.response.ApiResponse;
import com.example.salesrelations.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDTO>> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        OrderResponseDTO savedOrder = orderService.createOrder(orderDTO);

        ApiResponse<OrderResponseDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Order created successfully",
                savedOrder
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.getAllOrders();

        ApiResponse<List<OrderResponseDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Orders fetched successfully",
                orders
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> getOrdersByCustomer(@PathVariable Long id) {
        List<OrderResponseDTO> orders = orderService.getOrdersByCustomer(id);

        ApiResponse<List<OrderResponseDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Orders fetched successfully by customer",
                orders
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> getOrdersByEmployee(@PathVariable Long id) {
        List<OrderResponseDTO> orders = orderService.getOrdersByEmployee(id);

        ApiResponse<List<OrderResponseDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Orders fetched successfully by employee",
                orders
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> updateOrder(@PathVariable Long id,
                                                                     @Valid @RequestBody OrderDTO orderDTO) {
        OrderResponseDTO updatedOrder = orderService.updateOrder(id, orderDTO);

        ApiResponse<OrderResponseDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Order updated successfully",
                updatedOrder
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);

        ApiResponse<Object> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Order deleted successfully",
                null
        );

        return ResponseEntity.ok(response);
    }
}