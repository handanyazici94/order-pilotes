package com.tui.proof.ws.controller;


import com.tui.proof.dto.OrderDto;
import com.tui.proof.dto.OrderResponse;
import com.tui.proof.exception.ApiException;
import com.tui.proof.repository.ClientRepository;
import com.tui.proof.service.ClientService;
import com.tui.proof.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    OrderService orderService;

    @PostMapping("/client/{clientId}/order")
    ResponseEntity createNewOrder(@PathVariable("clientId") Long clientId, @Valid @RequestBody OrderDto orderDto) throws ApiException {
        log.info("Create New Order start");
        OrderResponse orderResponse = orderService.createNewOrder(orderDto, clientId);
        log.info("Create New Order finish");

        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping("/client/{clientId}/order/{orderId}")
    ResponseEntity updateOrder(@PathVariable("orderId") Long orderId, @PathVariable("clientId") Long clientId, @Valid @RequestBody OrderDto orderDto) throws ApiException {
        log.info("Update Order. OrderId: " + orderId + " ClientId: " + clientId);
        OrderResponse orderResponse = orderService.updateOrder(orderId, clientId, orderDto);

        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/search/{partial}")
    ResponseEntity searchOrderByClient(@PathVariable("partial") String partialSearch) throws ApiException {
        log.info("Search order as partial By Chef.");
        List<OrderResponse> searchOrderList = orderService.searchOrderAsPartial(partialSearch);

        return ResponseEntity.ok(searchOrderList);
    }

    @GetMapping("/list")
    ResponseEntity listAllOrder() {
        log.info("List all order");
        List<OrderResponse> orderList = orderService.listAllOrder();

        return ResponseEntity.ok(orderList);
    }

}
