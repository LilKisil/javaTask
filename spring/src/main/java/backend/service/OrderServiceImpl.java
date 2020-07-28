package backend.service;

import backend.model.Order;
import backend.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        Order createOrder = orderRepository.save(order);
        log.info("saveOrder() function - order: {} successfully created", createOrder);
        return createOrder;
    }

    @Override
    public Order getOrderByItem(String item){
        Order getOrder = orderRepository.findByItem(item);
        log.info("getOrder() function - order: {} successfully found", getOrder);
        return getOrder;
    }

    @Override
    public List<Order> getAllByItem(String item) {
        List<Order> result = orderRepository.findAllByItemOrderByPrice(item);
        log.info("getAllByItem() function - {} orders found", result.size());
        return result;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
        log.info("deleteTask() function - order with id: {} successfully deleted", id);
    }



}
