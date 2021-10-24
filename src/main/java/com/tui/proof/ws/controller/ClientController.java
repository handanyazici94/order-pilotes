package com.tui.proof.ws.controller;

import com.tui.proof.dto.ClientRequest;
import com.tui.proof.dto.ClientResponse;
import com.tui.proof.dto.OrderRequest;
import com.tui.proof.dto.OrderResponse;
import com.tui.proof.exception.ApiException;
import com.tui.proof.model.entity.Client;
import com.tui.proof.service.ClientService;
import com.tui.proof.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    OrderService orderService;

    @PostMapping("/client")
    ResponseEntity register(@Valid @RequestBody ClientRequest clientRequest) throws ApiException {
        log.info("Create new client.");
        ClientResponse clientResponse = clientService.add(clientRequest);
        log.info("Create new client is completed.");

        return ResponseEntity.ok(clientResponse);
    }

    @PostMapping("/client/{clientId}/order")
    ResponseEntity createNewOrder(@PathVariable("clientId") Long clientId, @Valid @RequestBody OrderRequest orderRequest) throws ApiException {
        log.info("Create New Order start");
        Client client = clientService.findClientById(clientId);
        OrderResponse orderResponse = orderService.createNewOrder(orderRequest, client);
        log.info("Create New Order is completed.");

        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping("/client/{clientId}/order/{orderId}")
    ResponseEntity updateOrder(@PathVariable("orderId") Long orderId, @PathVariable("clientId") Long clientId, @Valid @RequestBody OrderRequest orderRequest) throws ApiException {
        log.info("Update Order. OrderId: " + orderId + " ClientId: " + clientId);
        Client client = clientService.findClientById(clientId);
        OrderResponse orderResponse = orderService.updateOrder(orderId, client, orderRequest);
        log.info("Update Order is completed. OrderId: " + orderId + " ClientId: " + clientId);

        return ResponseEntity.ok(orderResponse);
    }

}
