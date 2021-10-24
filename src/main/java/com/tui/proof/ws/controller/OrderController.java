package com.tui.proof.ws.controller;

import com.tui.proof.dto.OrderResponse;
import com.tui.proof.exception.ApiException;
import com.tui.proof.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Log4j2
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;


    @GetMapping("/order/{partial}")
    ResponseEntity searchOrderByClient(@PathVariable("partial") String partialSearch) throws ApiException {
        log.info("Search order as partial By Chef.");
        List<OrderResponse> searchOrderList = orderService.searchOrderAsPartial(partialSearch);
        log.info("Search order as partial By Chef is completed.");
        return ResponseEntity.ok(searchOrderList);
    }

    @GetMapping("/orders")
    ResponseEntity listAllOrder() {
        log.info("List all order");
        List<OrderResponse> orderList = orderService.listAllOrder();
        log.info("List all order is completed");
        return ResponseEntity.ok(orderList);
    }

}
