package backend.controller;

import backend.model.Order;
import backend.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class OrderControllerTwoTest {
    private final OrderService orderService;
    private final TestRestTemplate restTemplate;

    @Autowired
    public OrderControllerTwoTest(OrderService orderService, TestRestTemplate restTemplate){
        this.orderService = orderService;
        this.restTemplate = restTemplate;
    }

    @Test
    public void getOrder(){
        Order expected = new Order();
        expected.setItem("Door");
        expected.setQuantity(10);
        expected.setPrice(75);
        expected.setTimeInterval(LocalDateTime.of(2020,7,7, 12, 10));
        Order actual = orderService.getOrderByItem("Door");
        assertEquals(expected, actual);
        assertNotNull(actual);
    }

    @Test
    public void getNull(){
        Order actual = orderService.getOrderByItem("thing");
        assertNull(actual);
    }

}
