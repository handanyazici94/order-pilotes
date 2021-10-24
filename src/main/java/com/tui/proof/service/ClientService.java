package com.tui.proof.service;

import com.tui.proof.dto.ClientResponse;
import com.tui.proof.exception.ApiException;
import com.tui.proof.dto.ClientRequest;
import com.tui.proof.model.entity.Client;
import com.tui.proof.repository.ClientRepository;
import com.tui.proof.service.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public ClientResponse add (ClientRequest clientRequest) throws ApiException {

        Optional<Client> client = clientRepository.findByEmail(clientRequest.getEmail());
        if (client.isPresent()) {
            throw new ApiException("User already exists");
        }
        Client newClient = ClientMapper.mappingFromClientDtoToClient(clientRequest);
        newClient = clientRepository.save(newClient);

        return ClientMapper.mappingFromClientToClientResponse(newClient);
    }

    public Client findClientById (Long clientId) throws ApiException {
        return clientRepository.findById(clientId).orElseThrow(()-> new ApiException("Client couldn't be found"));
    }

}
