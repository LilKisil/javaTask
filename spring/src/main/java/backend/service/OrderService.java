package backend.service;

import backend.model.Order;

import java.util.List;

public interface OrderService {
    Order saveOrder(Order order);
    List<Order> getAllByItem(String item);
    void deleteOrder(Long id);
    Order getOrderByItem(String item);
}
