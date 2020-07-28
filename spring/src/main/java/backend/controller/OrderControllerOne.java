package backend.controller;

import backend.model.Order;
import backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/order")
@CrossOrigin(origins = "http://localhost:8080")
public class OrderControllerOne {
    private final OrderService orderService;

    @Autowired
    public OrderControllerOne(OrderService orderService){
        this.orderService = orderService;
    }


    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order orderRequest) {
        try {
            if (orderRequest == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Order order = new Order();
            order.setPrice(orderRequest.getPrice());
            order.setQuantity(orderRequest.getQuantity());
            order.setItem(orderRequest.getItem());
            order.setTimeInterval(LocalDateTime.now());
            orderService.saveOrder(order);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }


}
