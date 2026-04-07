package com.example.salesrelations.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.salesrelations.dto.OrderDTO;
import com.example.salesrelations.dto.OrderResponseDTO;
import com.example.salesrelations.entity.Customer;
import com.example.salesrelations.entity.Employee;
import com.example.salesrelations.entity.Order;
import com.example.salesrelations.exception.ResourceNotFoundException;
import com.example.salesrelations.mapper.OrderMapper;
import com.example.salesrelations.repository.CustomerRepository;
import com.example.salesrelations.repository.EmployeeRepository;
import com.example.salesrelations.repository.OrderRepository;
import com.example.salesrelations.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomerRepository customerRepository,
                            EmployeeRepository employeeRepository,
                            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponseDTO createOrder(OrderDTO orderDTO) {
        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + orderDTO.getCustomerId()));

        Employee employee = employeeRepository.findById(orderDTO.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + orderDTO.getEmployeeId()));

        Order order = new Order();
        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setAmount(orderDTO.getAmount());
        order.setStatus(orderDTO.getStatus());
        order.setCustomer(customer);
        order.setEmployee(employee);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toResponseDTO(savedOrder);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderMapper.toResponseDTOList(orderRepository.findAll());
    }

    @Override
    public List<OrderResponseDTO> getOrdersByCustomer(Long customerId) {
        return orderMapper.toResponseDTOList(orderRepository.findByCustomerId(customerId));
    }

    @Override
    public List<OrderResponseDTO> getOrdersByEmployee(Long employeeId) {
        return orderMapper.toResponseDTOList(orderRepository.findByEmployeeId(employeeId));
    }

    @Override
    public OrderResponseDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + orderDTO.getCustomerId()));

        Employee employee = employeeRepository.findById(orderDTO.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + orderDTO.getEmployeeId()));

        existingOrder.setOrderNumber(orderDTO.getOrderNumber());
        existingOrder.setOrderDate(orderDTO.getOrderDate());
        existingOrder.setAmount(orderDTO.getAmount());
        existingOrder.setStatus(orderDTO.getStatus());
        existingOrder.setCustomer(customer);
        existingOrder.setEmployee(employee);

        Order updatedOrder = orderRepository.save(existingOrder);
        return orderMapper.toResponseDTO(updatedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        orderRepository.delete(existingOrder);
    }
}