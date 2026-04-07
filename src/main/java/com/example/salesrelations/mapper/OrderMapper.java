package com.example.salesrelations.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.salesrelations.dto.OrderResponseDTO;
import com.example.salesrelations.entity.Order;

@Component
public class OrderMapper {

    public OrderResponseDTO toResponseDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getOrderNumber(),
                order.getOrderDate(),
                order.getAmount(),
                order.getStatus(),
                order.getCustomer().getId(),
                order.getCustomer().getCustomerName(),
                order.getEmployee().getId(),
                order.getEmployee().getName()
        );
    }

    public List<OrderResponseDTO> toResponseDTOList(List<Order> orders) {
        return orders.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}