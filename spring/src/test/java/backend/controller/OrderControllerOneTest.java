package backend.controller;

import backend.model.Order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class OrderControllerOneTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order();
        order.setItem("Door");
        order.setQuantity(10);
        order.setPrice(75);
        order.setTimeInterval(LocalDateTime.of(2020,7,7, 12, 10));
        ResponseEntity<Order> postResponse = restTemplate.postForEntity(getRootUrl() + "/order/create", order, Order.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }




}
