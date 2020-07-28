package backend.controller;

import backend.model.Order;
import backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/app")
@CrossOrigin(origins = "http://localhost:8080")
public class OrderControllerTwo {
    private final OrderService orderService;

    @Autowired
    public OrderControllerTwo(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{item}")
    public ResponseEntity<List<Order>> getOrder(@PathVariable("item") String item) {
        try {
            List<Order> orders = orderService.getAllByItem(item);
            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                List<Order> ordersWithRightTime = new ArrayList<>();
                for (Order element : orders) {
                    if (element.getTimeInterval().compareTo(LocalDateTime.now().minusMinutes(10)) < 0) {
                        orderService.deleteOrder(element.getId());
                    } else {
                        ordersWithRightTime.add(element);
                    }
                }
                if(ordersWithRightTime.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                for (Order element : ordersWithRightTime) {
                    if (element.getQuantity() > 0) {
                        element.setQuantity(element.getQuantity() - 1);
                        orderService.saveOrder(element);
                        List<Order> newOrders = new ArrayList<>();
                        newOrders.add(element);
                        return new ResponseEntity<>(newOrders, HttpStatus.OK);
                    }
                }
                return new ResponseEntity<>(ordersWithRightTime, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

