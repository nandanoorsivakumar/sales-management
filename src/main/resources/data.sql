-- EMPLOYEE
INSERT INTO employee (name, department) VALUES
('Ravi', 'Sales'),
('Suresh', 'Sales'),
('Anil', 'Support');

-- CUSTOMER
INSERT INTO customer (customer_name, email, phone, city) VALUES
('Ram', 'ram@gmail.com', '9876543210', 'Hyderabad'),
('Anjali', 'anjali@gmail.com', '9123456780', 'Chennai'),
('John', 'john@gmail.com', '9988776655', 'Bangalore');

-- ORDERS
INSERT INTO orders (order_number, amount, order_date, status, customer_id, employee_id) VALUES
('ORD101', 50000, '2026-03-01', 'DELIVERED', 1, 1),
('ORD102', 25000, '2026-03-05', 'PENDING', 2, 2),
('ORD103', 15000, '2026-03-10', 'SHIPPED', 1, 3),
('ORD104', 70000, '2026-03-12', 'DELIVERED', 3, 1);