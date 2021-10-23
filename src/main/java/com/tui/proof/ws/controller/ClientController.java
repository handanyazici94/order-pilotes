package com.tui.proof.ws.controller;

import com.tui.proof.dto.ClientDto;
import com.tui.proof.dto.ClientResponse;
import com.tui.proof.exception.ApiException;
import com.tui.proof.model.entity.Client;
import com.tui.proof.service.ClientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/register")
    ResponseEntity register(@Valid @RequestBody ClientDto clientDto) throws ApiException {

        ClientResponse clientResponse = clientService.register(clientDto);
        return ResponseEntity.ok(clientResponse);
    }

}
