package com.example.salesrelations.service;

import java.util.List;

import com.example.salesrelations.dto.OrderDTO;
import com.example.salesrelations.dto.OrderResponseDTO;

public interface OrderService {

    OrderResponseDTO createOrder(OrderDTO orderDTO);

    List<OrderResponseDTO> getAllOrders();

    List<OrderResponseDTO> getOrdersByCustomer(Long customerId);

    List<OrderResponseDTO> getOrdersByEmployee(Long employeeId);

    OrderResponseDTO updateOrder(Long id, OrderDTO orderDTO);

    void deleteOrder(Long id);
}