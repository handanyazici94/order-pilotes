package com.tui.proof.service;

import com.tui.proof.dto.ClientResponse;
import com.tui.proof.exception.ApiException;
import com.tui.proof.dto.ClientDto;
import com.tui.proof.model.entity.Client;
import com.tui.proof.repository.ClientRepository;
import com.tui.proof.service.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public ClientResponse register (ClientDto clientDto) {
        Client registeredClient = clientRepository.findByEmail(clientDto.getEmail())
        .map(client -> {
             new ApiException("User already exists");
             return client;
        }).orElseGet(()-> {
            Client client = ClientMapper.mappingFromClientDtoToClient(clientDto);
            return clientRepository.save(client);
        });
        return ClientMapper.mappingFromClientToClientResponse(registeredClient);
    }
    public Client findClientById (Long clientId) throws ApiException {
        return clientRepository.findById(clientId).orElseThrow(()-> new ApiException("Client is not found"));
    }

    public Client checkClientExists(Long clientId, Long orderId) throws ApiException{
        return clientRepository.findByIdAndOrdersId(clientId, orderId)
            .orElseThrow(()-> new ApiException("Client is not found"));
    }

}
